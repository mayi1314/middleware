import math
from abc import abstractmethod, ABC
from threading import Thread
from typing import Union, List

import ariac_msgs.msg
import rclpy
from ariac_msgs.msg import Part, VacuumGripperState, PartPose, AdvancedLogicalCameraImage
from ariac_msgs.srv import Attach, SetJointValueTarget, MoveToPos
from geometry_msgs.msg import Pose
from rclpy.node import Node
from rclpy.parameter import Parameter
from rclpy.qos import qos_profile_sensor_data
from std_srvs.srv import Trigger

from src.motion import utils
from src.motion.utils import Utils

_ceiling_as1 = {
    "gantry_x_axis_joint": 1,
    "gantry_y_axis_joint": -3,
    "gantry_rotation_joint": 1.571,
    "ceiling_shoulder_pan_joint": 0,
    "ceiling_shoulder_lift_joint": -2.37,
    # "ceiling_elbow_joint": 2.37,
    # "ceiling_wrist_1_joint": 3.14,
    # "ceiling_wrist_2_joint": -1.57,
    # "ceiling_wrist_3_joint": 0
}
_floor_kt_js = {
    # "linear_actuator_joint": -4.0,
    # "floor_shoulder_pan_joint": -1.57,
    "floor_shoulder_lift_joint": -1.57,
    "floor_elbow_joint": 1.57,
    "floor_wrist_1_joint": -1.57,
    "floor_wrist_2_joint": -1.57,
    "floor_wrist_3_joint": 0.0
}
_part_heights = {
    ariac_msgs.msg.Part.BATTERY: 0.04,
    ariac_msgs.msg.Part.PUMP: 0.12,
    ariac_msgs.msg.Part.REGULATOR: 0.07,
    ariac_msgs.msg.Part.SENSOR: 0.07
}
_battery_grip_offset = -0.05
_pick_offset = 0.003
_drop_height = 0.102


class Robot(Node, ABC):
    def __init__(self, node_name: str):
        # Thread.__init__(self)
        super().__init__(node_name)

        sim_time = Parameter(
            "use_sim_time",
            rclpy.Parameter.Type.BOOL,
            True
        )
        self.name = self.get_name()
        self.model_name = ''
        self.set_parameters([sim_time])

        self.last_target = None
        self._reached = False
        self._gripper_enabled = None
        self._gripper_attached = None
        self._gripper_type = None

        self._reset_cli = self.create_client(Trigger, f'/competitor/move_{self.name}_home')
        self._set_joint_value_cli = self.create_client(SetJointValueTarget, "/ariac/set_joint_value_target")
        self._wait_for_attach_cli = self.create_client(Attach, "/ariac/wait_for_attach")
        self._detach_object_cli = self.create_client(Attach, '/ariac/detach_object')
        self._move_cli = self.create_client(MoveToPos, "/ariac/move_to_pos")
        self._gripper_state = self._conveyor_state_sub = self.create_subscription(VacuumGripperState,
                                                                                  f"/ariac/{self.name}_gripper_state",
                                                                                  self._gripper_state_cb, 1)

        self.left_parts = None
        self.left_camera = None
        self.right_parts = None
        self.right_camera = None
        self._right_bin_camera_sub = self.create_subscription(AdvancedLogicalCameraImage,
                                                              "/ariac/sensors/right_bins_camera/image",
                                                              self._right_bin_camera_cb, qos_profile_sensor_data)
        self._left_bin_camera_sub = self.create_subscription(AdvancedLogicalCameraImage,
                                                             "/ariac/sensors/left_bins_camera/image",
                                                             self._left_bin_camera_cb, qos_profile_sensor_data)

        # self.utils = Utils()
        self.reset()

    def _right_bin_camera_cb(self, msg: AdvancedLogicalCameraImage):
        self.right_parts = msg.part_poses
        self.right_camera = msg.sensor_pose

    def _left_bin_camera_cb(self, msg: AdvancedLogicalCameraImage):
        self.left_parts = msg.part_poses
        self.left_camera = msg.sensor_pose

    def reset(self):
        request = Trigger.Request()

        if not self._reset_cli.wait_for_service(timeout_sec=1.0):
            self.get_logger().error('Robot commander node not running')
            return
        future = self._reset_cli.call_async(request)
        # Wait until the service call is completed
        rclpy.spin_until_future_complete(self, future)
        if future.result().success:
            self.get_logger().info(f'Moved {self.name} to home position')
        else:
            self.get_logger().warn(future.result().message)

    def _gripper_state_cb(self, msg: VacuumGripperState):
        self._gripper_enabled = msg.enabled
        self._gripper_attached = msg.attached
        self._gripper_type = msg.type

    @property
    def gripper_enabled(self):
        return self._gripper_enabled

    @property
    def gripper_attached(self):
        return self._gripper_attached

    @property
    def gripper_type(self):
        return self._gripper_type

    def set_joint_value(self, joint: str, value):
        request = SetJointValueTarget.Request()
        request.robot_name = self.name
        request.joint = joint
        request.value = value
        future = self._set_joint_value_cli.call_async(request)
        rclpy.spin_until_future_complete(self, future)
        if future.result().success:
            # if res.success:
            self.get_logger().info(f'set {self.name} joint: {joint} value: {value}!')
            return True
        else:
            self.get_logger().warn(future.result().message)
        return False

    def wait_for_attach(self, part: Part, pose: Pose, timeout):
        """
        attach object
        :param part: the object to attach
        :param pose: the position of object
        :param timeout: timeout
        """
        request = Attach.Request()
        request.robot_name = self.name
        request.part = part
        request.pose = pose
        request.timeout = timeout
        future = self._wait_for_attach_cli.call_async(request)
        self.get_logger().info(f'wait for {self.name} robot attach part!')
        rclpy.spin_until_future_complete(self, future)
        if future.result().success:
            # if res.success:
            self.get_logger().info(f'{self.name} robot has attached part!')
        else:
            self.get_logger().warn(future.result().message)

    def wait_for_detach(self, part: Part):
        request = Attach.Request()
        request.robot_name = self.name
        request.part = part
        future = self._detach_object_cli.call_async(request)
        self.get_logger().info(f'wait for {self.name} robot detach part!')
        rclpy.spin_until_future_complete(self, future)
        if future.result().success:
            # if res.success:
            self.get_logger().info(f'{self.name} robot has detached part!')
        else:
            self.get_logger().warn(future.result().message)

    def move_to(self, waypoints: Union[Pose, PartPose, List[Pose]], avoid_collisions=True, pre_action=True):
        """
        path_type: point, cartesian. if use point, waypoints is a single pose
        """
        self._reached = False
        request = MoveToPos.Request()
        path_type = 'cartesian'
        part = None
        if type(waypoints) == PartPose:
            part = waypoints.part
            waypoints = waypoints.pose
        if type(waypoints) == Pose:
            pose_yaw = utils.get_yaw(waypoints)
            start_pos = utils.build_pose(x=waypoints.position.x, y=waypoints.position.y, z=waypoints.position.z + 0.3,
                                         orientation=utils.set_robot_orientation(pose_yaw))
            if part is not None:
                if self.name == 'ceiling_robot' and part.type == ariac_msgs.msg.Part.BATTERY:
                    dx = _battery_grip_offset * math.cos(pose_yaw)
                    dy = _battery_grip_offset * math.sin(pose_yaw)
                    end_pos = utils.build_pose(waypoints.position.x + dx, waypoints.position.y + dy,
                                               waypoints.position.z + _part_heights[part.type] + _pick_offset,
                                               utils.set_robot_orientation(pose_yaw))
                else:
                    end_pos = utils.build_pose(x=waypoints.position.x, y=waypoints.position.y,
                                               z=waypoints.position.z + _part_heights[part.type] + _pick_offset,
                                               orientation=utils.set_robot_orientation(pose_yaw))
            else:
                end_pos = utils.build_pose(x=waypoints.position.x, y=waypoints.position.y,
                                           z=waypoints.position.z + 0.2,
                                           orientation=utils.set_robot_orientation(pose_yaw))
            waypoints = [start_pos, end_pos]

        request.waypoints = waypoints
        self.last_target = waypoints[-1]

        if pre_action:
            if self.name == 'floor_robot':
                self.set_joint_value('linear_actuator_joint', -1 * self.last_target.position.y + 0.1)
                if self.last_target.position.x > -1.3:  # floor robot shoulder
                    self.set_joint_value('floor_shoulder_pan_joint', -3.14159)
                else:
                    self.set_joint_value('floor_shoulder_pan_joint', 0.0)
            elif self.name == 'ceiling_robot':
                for joint, value in _ceiling_as1.items():
                    self.set_joint_value(joint, float(value))

        request.robot_name = self.name
        request.type = path_type
        request.avoid_collisions = avoid_collisions
        future = self._move_cli.call_async(request)
        rclpy.spin_until_future_complete(self, future)
        if future.result().success:
            # if res.success:
            self.get_logger().info(f'move {self.name} to {waypoints}!')
            self._reached = True
            return True
        else:
            self.get_logger().warn(f'{self.name} can not move to {waypoints}......')
        return False

    @property
    def reached(self):
        return self._reached

    @abstractmethod
    def attach(self, part: Part, part_pose: Pose, after_pose: Pose, timeout=3.0):
        pass

    @abstractmethod
    def detach(self, part: Part):
        pass
