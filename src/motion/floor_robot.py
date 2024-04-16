from queue import Queue

import rclpy
from ariac_msgs.msg import Part, AdvancedLogicalCameraImage, PartPose, Order
from ariac_msgs.srv import VacuumGripperControl

from geometry_msgs.msg import Pose
from rclpy.callback_groups import MutuallyExclusiveCallbackGroup, ReentrantCallbackGroup
from std_srvs.srv import Trigger

from src.motion import utils
from src.motion.robot import Robot
from src.motion.utils import Utils

_floor_kt_js = {
    # "linear_actuator_joint": -4.0,
    # "floor_shoulder_pan_joint": -1.57,
    "floor_shoulder_lift_joint": -1.57,
    "floor_elbow_joint": 1.57,
    # "floor_wrist_1_joint": -1.57,
    # "floor_wrist_2_joint": -1.57,
    # "floor_wrist_3_joint": 0.0
}


class FloorRobot(Robot):
    def __init__(self, node_name: str):
        super().__init__(node_name)
        self.model_name = 'floor_gripper'
        self._floor_robot_gripper_enable = self.create_client(VacuumGripperControl, "/ariac/floor_robot_enable_gripper")
        self._move_to_home_cli = self.create_client(Trigger, '/competitor/move_floor_robot_home')
        self.timer = None
        self.challenge_timer = None
        # self._bin_parts_sub = self.create_subscription(BinParts, "/ariac/bin_parts", self._left_bin_parts_cb, 10)

        self.error = {'faulty_gripper': False}
        cb_callback = ReentrantCallbackGroup()
        self._order_sub = self.create_subscription(Order, '/ariac/orders', self._order_cb, 10,
                                                   callback_group=cb_callback)
        self.orders = Queue()
        self._orders = Queue()
        self.utils = Utils()

    def _order_cb(self, order: Order):
        if order.type == Order.KITTING:
            self._orders.put(order)
            print(f'order: {order.type}, {order.kitting_task}')
            # agv_id = order.kitting_task.agv_number
            # parts = order.kitting_task.parts
            # for part in parts:
            #     kit_starting_pose = self.check_part_on_bin(part.part)
            #     print(f'kit_starting_pose {kit_starting_pose}')
            #     kit_ending_pose = self.utils.compute_tray_loc(self.name, part.quadrant)
            #     print(f'kit_starting_pose {kit_starting_pose}, kit_ending_pose: {kit_ending_pose}')
            #     self.orders.put((agv_id, kit_starting_pose, kit_ending_pose))

    def handle_order(self):
        if not self._orders.empty():
            order = self._orders.get()
            if order.type == Order.KITTING:
                agv_id = order.kitting_task.agv_number
                parts = order.kitting_task.parts
                destination = order.kitting_task.destination
                num = len(parts)
                for part in parts:
                    kit_starting_pose = self.check_part_on_bin(part.part)
                    # print(f'kit_starting_pose {kit_starting_pose}')
                    kit_ending_pose = self.utils.compute_tray_loc(f'agv{agv_id}', part.quadrant)
                    # print(f'kit_starting_pose {kit_starting_pose}, kit_ending_pose: {kit_ending_pose}')
                    self.orders.put((agv_id, kit_starting_pose, kit_ending_pose, destination, num))

    def move_to_home(self):
        request = Trigger.Request()
        future = self._move_to_home_cli.call_async(request)
        rclpy.spin_until_future_complete(self, future)
        if future.result().success:
            self.get_logger().info(f'Moved {self.name} to home position')
        else:
            self.get_logger().warn(future.result().message)

    def _set_gripper_state(self, enable: bool):
        request = VacuumGripperControl.Request()
        request.enable = enable
        future = self._floor_robot_gripper_enable.call_async(request)
        self.get_logger().info(f'floor robot set gripper {enable}')
        rclpy.spin_until_future_complete(self, future)
        return future.result().success

    def attach(self, part: Part, part_pose: Pose, after_pose: Pose, timeout=5.0):
        """
        grip function
        """
        self._set_gripper_state(True)
        self.wait_for_attach(part, part_pose, timeout)

    def detach(self, part: Part):
        self._set_gripper_state(False)
        self.wait_for_detach(part)
        # for joint, value in _floor_kt_js.items():
        #     self.set_joint_value(joint, float(value))

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

    def check_part_on_bin(self, part):
        if isinstance(part, PartPose):
            part = part.part
        if self.left_parts:
            for part_pose in self.left_parts:
                _part, _pose = part_pose.part, part_pose.pose
                if part.type == _part.type and part.color == _part.color:
                    pose = PartPose(part=part, pose=utils.multiply_pose([self.left_camera, _pose]))
                    return pose
        if self.right_parts:
            for part_pose in self.right_parts:
                _part, _pose = part_pose.part, part_pose.pose
                if part.type == _part.type and part.color == _part.color:
                    # transform
                    pose = PartPose(part=part, pose=utils.multiply_pose([self.right_camera, _pose]))
                    return pose
        self.get_logger().warn(f'part bin has no part: {part}........')
        return
