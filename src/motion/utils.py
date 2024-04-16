import math
import threading
from typing import List

import ariac_msgs.msg
import rclpy
from ariac_gazebo.utilities import quaternion_from_euler, euler_from_quaternion
from ariac_msgs.srv import PoseStatus

from gazebo_msgs.srv import GetEntityState
from geometry_msgs.msg import Pose, Quaternion
from rclpy.callback_groups import ReentrantCallbackGroup
from rclpy.node import Node
from rclpy.parameter import Parameter

from std_srvs.srv import Trigger
from tf2_py import TransformException
from tf2_ros import Buffer, TransformListener

from rclpy import time, duration

_quad_offsets = {
    ariac_msgs.msg.KittingPart.QUADRANT1: (-0.08, 0.12),
    ariac_msgs.msg.KittingPart.QUADRANT2: (0.08, 0.12),
    ariac_msgs.msg.KittingPart.QUADRANT3: (-0.08, -0.12),
    ariac_msgs.msg.KittingPart.QUADRANT4: (0.08, -0.12),
}
_model_name = {
    'agv1': 'agv1_base',
    'ceiling_robot': 'ceiling_gripper',
    'floor_robot': 'floor_gripper'
}


# executor = concurrent.futures.ThreadPoolExecutor()
def singleton(cls):
    instance = None

    def wrapper(*args, **kwargs):
        nonlocal instance
        if not instance:
            instance = cls.__new__(cls)
            instance.__init__(*args, **kwargs)
        return instance

    return wrapper


class Utils(Node):
    def __init__(self, node_name='utils'):
        if not hasattr(self, "_init_done"):  # Only initialize once
            self._init_done = True
            super().__init__(node_name)
            sim_time = Parameter(
                "use_sim_time",
                rclpy.Parameter.Type.BOOL,
                True
            )
            self.name = self.get_name()
            self.set_parameters([sim_time])
            self.executor = rclpy.executors.MultiThreadedExecutor()

            self.tf_buffer = Buffer()
            self.tf_listener = TransformListener(self.tf_buffer, self)
            self.cb_group = ReentrantCallbackGroup()

            self._start_competition_client = self.create_client(Trigger, '/ariac/start_competition',
                                                                callback_group=self.cb_group)
            self.gazebo_client = self.create_client(GetEntityState, '/gazebo/get_entity_state',
                                                    callback_group=self.cb_group)

            self.status_cli = self.create_client(PoseStatus, '/ariac/get_pose_status')
            # start
            self.start_competition()

    def start_competition(self):
        """Function to start the competition.
        """
        self.get_logger().info('Waiting for competition to be ready')

        if not self._start_competition_client.wait_for_service(timeout_sec=3.0):
            self.get_logger().error('Service \'/ariac/start_competition\' is not available.')
            return
        # Create trigger request and call starter service
        request = Trigger.Request()
        future = self._start_competition_client.call_async(request)
        # Wait until the service call is completed
        rclpy.spin_until_future_complete(self, future)
        if future.result().success:
            self.get_logger().info('Started competition.')
        else:
            self.get_logger().warn('Unable to start competition')

    def static_transform(self, to_frame_rel, from_frame_rel):
        try:
            future = self.tf_buffer.wait_for_transform_async(to_frame_rel,
                                                             from_frame_rel,
                                                             rclpy.time.Time())
            rclpy.spin_until_future_complete(self, future, timeout_sec=5)
            t = None
            if future.result():
                t = self.tf_buffer.lookup_transform(
                    to_frame_rel,
                    from_frame_rel,
                    rclpy.time.Time(), rclpy.duration.Duration(seconds=3))
        except TransformException as ex:
            self.get_logger().info(f'Could not transform {to_frame_rel} to {from_frame_rel}, {ex}')
            return

        pose = Pose()
        pose.position.x = t.transform.translation.x
        pose.position.y = t.transform.translation.y
        pose.position.z = t.transform.translation.z
        pose.orientation = t.transform.rotation
        return pose

    def get_model_state(self, model: str, frame='world'):
        """
         model state _fields_and_field_types = {
         'name': 'string',
         'pose': 'geometry_msgs/Pose',
         'twist': 'geometry_msgs/Twist',
         'reference_frame': 'string',
        }
        """
        request = GetEntityState.Request()
        request.name = model
        request.reference_frame = frame
        future = self.gazebo_client.call_async(request)
        rclpy.spin_until_future_complete(self, future, timeout_sec=3)
        if future.result().success:
            # if res.success:
            self.get_logger().info(f'get model:{model} state: {future.result().state}')
            return future.result().state

    def compute_tray_loc(self, name: str, quadrant: int):

        agv_tray_pose = self.static_transform('world', f'{name}_tray')
        if not agv_tray_pose:
            self.get_logger().info('can not compute tray loc......')

        part_drop_offset = build_pose(_quad_offsets[quadrant][0], _quad_offsets[quadrant][1], 0.0, Quaternion())
        part_drop_pose = multiply_pose([agv_tray_pose, part_drop_offset])
        return part_drop_pose

    def get_pose_status(self, robot, part, agv_id):
        request = PoseStatus.Request()
        request.part = part
        request.robot = robot
        request.agv_id = agv_id
        future = self.status_cli.call_async(request)
        rclpy.spin_until_future_complete(self, future)
        if future.done():
            return future.result()


class Frame:

    def __init__(self, m=None, p=None):
        if p is None:
            p = [0, 0, 0]
        if m is None:
            m = [1, 0, 0, 0, 1, 0, 0, 0, 1]
        self._p = p
        self._m = m

    @property
    def m(self):
        return self._m

    @m.setter
    def m(self, val):
        self._m = val

    @property
    def p(self):
        return self._p

    @p.setter
    def p(self, val):
        self._p = val

    def frame_from_pose(self, pose: Pose):
        self.p = [pose.position.x, pose.position.y, pose.position.z]
        self.m = quaternion_to_matrix(pose.orientation)

    def frame_to_pose(self):
        quaternion = Quaternion()
        q = get_quaternion(self.m)
        quaternion.x = q[0]
        quaternion.y = q[1]
        quaternion.z = q[2]
        quaternion.w = q[3]
        return build_pose(self.p[0], self.p[1], self.p[2], quaternion)


def set_robot_orientation(rotation: float, roll=0, pitch=3.14159):
    quaternion = Quaternion()
    q = quaternion_from_euler(roll, pitch, rotation)
    quaternion.w = q[0]
    quaternion.x = q[1]
    quaternion.y = q[2]
    quaternion.z = q[3]
    return quaternion


def get_yaw(pose: Pose) -> float:
    roll, pitch, yaw = euler_from_quaternion(pose.orientation)
    return yaw


def build_pose(x, y, z, orientation):
    pose = Pose()
    pose.position.x = x
    pose.position.y = y
    pose.position.z = z
    pose.orientation = orientation
    return pose


# frame --------------------------------------
def rotation_mul_vector(r, v):
    res_0 = r[0] * v[0] + r[1] * v[1] + r[2] * v[2]
    res_1 = r[3] * v[0] + r[4] * v[1] + r[5] * v[2]
    res_2 = r[6] * v[0] + r[7] * v[1] + r[8] * v[2]
    return [res_0, res_1, res_2]


def vector_plus(v1, v2):
    return [v1[0] + v2[0], v1[1] + v2[1], v1[2] + v2[2]]


def quaternion_to_matrix(q):
    x, y, z, w = q.x, q.y, q.z, q.w
    x2, y2, z2, w2 = x * x, y * y, z * z, w * w
    matrix = [w2 + x2 - y2 - z2, 2 * x * y - 2 * w * z, 2 * x * z + 2 * w * y,
              2 * x * y + 2 * w * z, w2 - x2 + y2 - z2, 2 * y * z - 2 * w * x,
              2 * x * z - 2 * w * y, 2 * y * z + 2 * w * x, w2 - x2 - y2 + z2]
    return matrix


def get_quaternion(data):
    trace = data[0 * 3 + 0] + data[1 * 3 + 1] + data[2 * 3 + 2]
    epsilon = 1E-12
    x, y, z, w = 0, 0, 0, 0

    if trace > epsilon:
        s = 0.5 / math.sqrt(trace + 1.0)
        w = 0.25 / s
        x = (data[2 * 3 + 1] - data[1 * 3 + 2]) * s
        y = (data[0 * 3 + 2] - data[2 * 3 + 0]) * s
        z = (data[1 * 3 + 0] - data[0 * 3 + 1]) * s
    else:
        if data[0 * 3 + 0] > data[1 * 3 + 1] and data[0 * 3 + 0] > data[2 * 3 + 2]:
            s = 2.0 * math.sqrt(1.0 + data[0 * 3 + 0] - data[1 * 3 + 1] - data[2 * 3 + 2])
            w = (data[2 * 3 + 1] - data[1 * 3 + 2]) / s
            x = 0.25 * s
            y = (data[0 * 3 + 1] + data[1 * 3 + 0]) / s
            z = (data[0 * 3 + 2] + data[2 * 3 + 0]) / s
        elif data[1 * 3 + 1] > data[2 * 3 + 2]:
            s = 2.0 * math.sqrt(1.0 + data[1 * 3 + 1] - data[0 * 3 + 0] - data[2 * 3 + 2])
            w = (data[0 * 3 + 2] - data[2 * 3 + 0]) / s
            x = (data[0 * 3 + 1] + data[1 * 3 + 0]) / s
            y = 0.25 * s
            z = (data[1 * 3 + 2] + data[2 * 3 + 1]) / s
        else:
            s = 2.0 * math.sqrt(1.0 + data[2 * 3 + 2] - data[0 * 3 + 0] - data[1 * 3 + 1])
            w = (data[1 * 3 + 0] - data[0 * 3 + 1]) / s
            x = (data[0 * 3 + 2] + data[2 * 3 + 0]) / s
            y = (data[1 * 3 + 2] + data[2 * 3 + 1]) / s
            z = 0.25 * s

    return x, y, z, w


def rotation_multiply(lhs, rhs):
    return [lhs[0] * rhs[0] + lhs[1] * rhs[3] + lhs[2] * rhs[6],
            lhs[0] * rhs[1] + lhs[1] * rhs[4] + lhs[2] * rhs[7],
            lhs[0] * rhs[2] + lhs[1] * rhs[5] + lhs[2] * rhs[8],
            lhs[3] * rhs[0] + lhs[4] * rhs[3] + lhs[5] * rhs[6],
            lhs[3] * rhs[1] + lhs[4] * rhs[4] + lhs[5] * rhs[7],
            lhs[3] * rhs[2] + lhs[4] * rhs[5] + lhs[5] * rhs[8],
            lhs[6] * rhs[0] + lhs[7] * rhs[3] + lhs[8] * rhs[6],
            lhs[6] * rhs[1] + lhs[7] * rhs[4] + lhs[8] * rhs[7],
            lhs[6] * rhs[2] + lhs[7] * rhs[5] + lhs[8] * rhs[8]]


def multiply_frame(frames: List[Frame]) -> Frame:
    tmp = frames[0]
    for i in range(1, len(frames)):
        m = rotation_multiply(tmp.m, frames[i].m)
        p = vector_plus(rotation_mul_vector(tmp.m, frames[i].p), tmp.p)
        tmp = Frame(m, p)
    return tmp


def multiply_pose(poses: List[Pose]):
    f1 = Frame()
    f2 = Frame()
    f1.frame_from_pose(poses[0])
    for i in range(1, len(poses)):
        f2.frame_from_pose(poses[i])
        res = multiply_frame([f1, f2])
        f1 = res
    return f1.frame_to_pose()
