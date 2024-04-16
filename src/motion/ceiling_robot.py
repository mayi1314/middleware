import math
from queue import Queue
from typing import List

import rclpy
import ariac_msgs.msg
from ariac_msgs.msg import Part, PartPose, Order
from ariac_msgs.srv import Assemble, VacuumGripperControl, GetPreAssemblyPoses

from geometry_msgs.msg import Pose
from rclpy.callback_groups import MutuallyExclusiveCallbackGroup, ReentrantCallbackGroup
from std_srvs.srv import Trigger

from src.motion import utils
from src.motion.robot import Robot
from src.motion.utils import Utils

PI = math.pi
_battery_grip_offset = -0.05
assembly_cnf = {
    ariac_msgs.msg.Part.REGULATOR: {'assembled_pose': [0.175, -0.223, 0.215, PI / 2, 0, -PI / 2],
                                    'assembly_direction': [0, 0, -1]},
    ariac_msgs.msg.Part.BATTERY: {'assembled_pose': [-0.15, 0.035, 0.043, 0, 0, PI / 2],
                                  'assembly_direction': [0, 1, 0]},
    ariac_msgs.msg.Part.PUMP: {'assembled_pose': [0.14, 0.0, 0.02, 0, 0, -PI / 2],
                               'assembly_direction': [0, 0, -1]},
    ariac_msgs.msg.Part.SENSOR: {'assembled_pose': [-0.1, 0.395, 0.045, 0, 0, -PI / 2],
                                 'assembly_direction': [0, -1, 0]},
}

_part_heights = {
    ariac_msgs.msg.Part.BATTERY: 0.04,
    ariac_msgs.msg.Part.PUMP: 0.12,
    ariac_msgs.msg.Part.REGULATOR: 0.07,
    ariac_msgs.msg.Part.SENSOR: 0.07
}
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


class CeilingRobot(Robot):
    def __init__(self, node_name: str):
        super().__init__(node_name)

        self.model_name = 'ceiling_gripper'
        self._wait_for_assemble_cli = self.create_client(Assemble, '/ariac/wait_for_assemble')
        self._ceiling_robot_gripper_enable = self.create_client(VacuumGripperControl,
                                                                "/ariac/ceiling_robot_enable_gripper")
        self._move_to_home_cli = self.create_client(Trigger, '/competitor/move_ceiling_robot_home')
        self._get_pre_assembly_pose_cli = self.create_client(GetPreAssemblyPoses, '/ariac/get_pre_assembly_poses')

        self.timer = None
        self.error = {'faulty_gripper': False}
        cb_callback = ReentrantCallbackGroup()
        self._order_sub = self.create_subscription(Order, '/ariac/orders', self._order_cb, 10,
                                                   callback_group=cb_callback)
        self.orders = Queue()
        self.utils = Utils()
        self.challenges = {}

    def _order_cb(self, order: Order):
        if order.type == Order.ASSEMBLY:
            agv_ids = order.assembly_task.agv_numbers
            destination = order.assembly_task.station
            parts = order.assembly_task.parts
            # agv position
            for agv in agv_ids:
                self.orders.put((agv, destination, parts))

    def move_to_home(self):
        request = Trigger.Request()
        future = self._move_to_home_cli.call_async(request)
        rclpy.spin_until_future_complete(self, future)
        if future.result().success:
            self.get_logger().info(f'Moved {self.name} to home position')
        else:
            self.get_logger().warn(future.result().message)

    def get_pre_assembly_pose(self, order_id):
        request = GetPreAssemblyPoses.Request()
        request.order_id = order_id
        self._get_pre_assembly_pose_cli.call_async(request)
        future = self._wait_for_assemble_cli.call_async(request)
        rclpy.spin_until_future_complete(self, future)
        if future.done():
            self.get_logger().info(f'wait for get pre assembly pose: {future.result()}')
        return future.result().agv_at_station, future.result().parts

    def wait_for_assemble(self, station: int, part: Part, install: List[float]):
        request = Assemble.Request()
        request.robot_name = self.name
        request.station = station
        request.part = part
        request.install_direction = install
        future = self._wait_for_assemble_cli.call_async(request)
        rclpy.spin_until_future_complete(self, future)
        if future.result() and future.result().success:
            # if res.success:
            self.get_logger().info(f'wait for assemble on station{station}, part: {part.color}_{part.type}')

    def _set_gripper_state(self, enable: bool):
        request = VacuumGripperControl.Request()
        request.enable = enable
        future = self._ceiling_robot_gripper_enable.call_async(request)
        self.get_logger().info(f'ceiling_robot set gripper {enable}')
        rclpy.spin_until_future_complete(self, future)
        return future.result().success

    def attach(self, part: Part, part_pose: Pose, after_pose: Pose, timeout=3.0):
        """
        grip function
        """
        self._set_gripper_state(True)
        self.wait_for_attach(part, part_pose, timeout)
        part_rotation = utils.get_yaw(part_pose)
        # start_pose = utils.build_pose(x=part_pose.position.x, y=part_pose.position.y, z=part_pose.position.z + 0.1,
        #                               orientation=utils.set_robot_orientation(part_rotation))
        # after_pose = utils.build_pose(x=part_pose.position.x, y=part_pose.position.y, z=part_pose.position.z + 0.1,
        #                               orientation=utils.set_robot_orientation(part_rotation))
        # self.move_to([after_pose], pre_action=False)

    def detach(self, part: Part):
        self._set_gripper_state(False)
        self.wait_for_detach(part)

    def assemble(self, station: int, part):
        if isinstance(part, PartPose):
            part = part.part

        # inject faulty
        if self.challenges.get('assemble_gripper_faulty'):
            self.move_to([utils.build_pose(-7.7, 4.0, 1.5, utils.set_robot_orientation(PI, PI, 0))], pre_action=False)
            self.detach(part)
            self.challenges['assemble_gripper_faulty'] = False

        for joint, value in _ceiling_as1.items():
            # if self.challenges.get('assemble_gripper_faulty') and joint == "gantry_x_axis_joint":
            #     self.detach(part)
            #     self.challenges['assemble_gripper_faulty'] = False
            self.set_joint_value(joint, float(value))

        cnf = assembly_cnf.get(part.type)
        insert_frame_name = "as1_insert_frame"

        insert_frame = utils.Frame()
        insert_frame.frame_from_pose(self.utils.static_transform('world', insert_frame_name))
        vec = cnf.get('assembled_pose')
        part_assemble_frame = utils.Frame()
        part_assemble_frame.frame_from_pose(
            utils.build_pose(vec[0], vec[1], vec[2], utils.set_robot_orientation(vec[5], vec[3], vec[4])))  # y, r, p
        install = cnf.get('assembly_direction')
        part_to_gripper_frame = utils.Frame()
        part_to_gripper_frame.frame_from_pose(
            utils.build_pose(0., 0., _part_heights[part.type], utils.set_robot_orientation(PI, PI, 0)))
        up = utils.Frame(p=[0, 0, 0.1])
        install_frame = utils.Frame(p=list(map(lambda x: x * -0.1, install)))
        if part.type == ariac_msgs.msg.Part.BATTERY:
            install_frame = utils.Frame(p=list(map(lambda x: x * -0.06, install)))
            part_to_gripper_frame.frame_from_pose(
                utils.build_pose(_battery_grip_offset, 0., _part_heights[part.type],
                                 utils.set_robot_orientation(PI, PI, 0)))
            start_pos = utils.multiply_frame(
                [insert_frame, up, install_frame, part_assemble_frame, part_to_gripper_frame])
            end_pos = utils.multiply_frame([insert_frame, install_frame, part_assemble_frame, part_to_gripper_frame])
            self.move_to([start_pos.frame_to_pose(), end_pos.frame_to_pose()], pre_action=False)
        else:
            end_pos = utils.multiply_frame([insert_frame, install_frame, part_assemble_frame, part_to_gripper_frame])
            self.move_to([end_pos.frame_to_pose()], pre_action=False)

        install_frame = utils.Frame(p=list(map(lambda x: x * -0.003, install)))
        end_pos = utils.multiply_frame([insert_frame, install_frame, part_assemble_frame, part_to_gripper_frame])
        self.move_to([end_pos.frame_to_pose()], pre_action=False)

        #  WaitForAssemble
        self.wait_for_assemble(station, part, list(map(float, install)))

        # # detach part
        # self.detach(part)

    # monitor
    def check_once(self, variable, except_val):
        if variable == "Gripper.attached":
            if except_val != self.gripper_attached:
                self._set_gripper_state(True)  # attach or enable_suck
        elif variable == "part":
            if except_val == "target_pose":
                pass

    def check(self, variable, except_val):
        self.get_logger().info(f'check variable: {variable}')
        if variable == "Gripper.attached":
            self.timer = self.create_timer(0.2, lambda: self.check_gripper(except_val),
                                           callback_group=MutuallyExclusiveCallbackGroup())

    def check_gripper(self, expect_state):
        self.get_logger().info(f'gripper except_val: {expect_state}, cur_val: {self.gripper_attached}......')
        if expect_state != self.gripper_attached:
            self.timer.cancel()
            self.error['faulty_gripper'] = True
