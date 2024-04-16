from functools import wraps
from queue import Queue
from threading import Thread, Event

import rclpy
from ariac_msgs.msg import AdvancedLogicalCameraImage, ConveyorBeltState, PartPose, Order
from rclpy.callback_groups import ReentrantCallbackGroup
from rclpy.node import Node
from rclpy.parameter import Parameter
from ariac_msgs.srv import ConveyorBeltControl
from rclpy.qos import qos_profile_sensor_data

from src.motion import utils

interceptor_dict = {
    "detach": False,
    "set_power": False
}

func_statement_map = {
    "Gripper.enable_suction==false": "detach",
    "Gripper.enable_suction==true": "attach",
}


class Conveyor(Node):
    def __init__(self, node_name: str):
        # Thread.__init__(self)
        super().__init__(node_name)

        sim_time = Parameter(
            "use_sim_time",
            rclpy.Parameter.Type.BOOL,
            True
        )

        self.name = self.get_name()
        self.set_parameters([sim_time])

        # self.id = conveyor_id
        self._part_poses = None
        self._sensor_pose = None
        self._enabled = None
        self._power = None
        self._set_power = self.create_client(ConveyorBeltControl, "/ariac/set_conveyor_power")
        self._conveyor_sensor_sub = self.create_subscription(AdvancedLogicalCameraImage,
                                                             "/ariac/sensors/conveyor_bins_camera/image",
                                                             self._conveyor_sensor_cb,
                                                             qos_profile_sensor_data)
        self._conveyor_state_sub = self.create_subscription(ConveyorBeltState, "/ariac/conveyor_state",
                                                            self._conveyor_state_cb, 10)

        cb_callback = ReentrantCallbackGroup()
        self._order_sub = self.create_subscription(Order, '/ariac/orders', self._order_cb, 10,
                                                   callback_group=cb_callback)
        self.orders = Queue()

    def _order_cb(self, order: Order):
        pass

    def reset(self):
        pass

    def set_power(self, power: float):
        request = ConveyorBeltControl.Request()
        request.power = power
        if not self._set_power.wait_for_service(timeout_sec=1.0):
            self.get_logger().error('conveyor belt node not running')
            return
        future = self._set_power.call_async(request)
        rclpy.spin_until_future_complete(self, future)
        if future.result().success:
            self.get_logger().info(f'Set conveyor power to {power}')

    def _conveyor_sensor_cb(self, msg: AdvancedLogicalCameraImage):
        # self.get_logger().info("Received data from conveyor_bins_camera")
        self._part_poses = msg.part_poses
        self._sensor_pose = msg.sensor_pose

    def _conveyor_state_cb(self, msg):
        self._power = msg.power
        self._enabled = msg.power != 0.0

    @property
    def part_poses(self):
        res = []
        if self._part_poses:
            for item in self._part_poses:
                res.append(PartPose(part=item.part, pose=utils.multiply_pose([self.sensor_pose, item.pose])))
        return res

    @property
    def sensor_pose(self):
        return self._sensor_pose

    @property
    def enabled(self):
        return self._enabled

    @property
    def power(self):
        return self._power
