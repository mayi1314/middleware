from queue import Queue

import rclpy
from ariac_msgs.msg import Part, Order, BinParts, AGVStatus, AdvancedLogicalCameraImage
from ariac_msgs.srv import AgvPartPose, MoveAGV, PerformQualityCheck, PickPart

from geometry_msgs.msg import Quaternion, Pose
from rclpy.callback_groups import MutuallyExclusiveCallbackGroup, ReentrantCallbackGroup
from rclpy.node import Node
from rclpy.parameter import Parameter
from std_srvs.srv import Trigger
import ariac_msgs.msg

from src.motion.utils import Utils

_quad_offsets = {
    ariac_msgs.msg.KittingPart.QUADRANT1: (-0.08, 0.12),
    ariac_msgs.msg.KittingPart.QUADRANT2: (0.08, 0.12),
    ariac_msgs.msg.KittingPart.QUADRANT3: (-0.08, -0.12),
    ariac_msgs.msg.KittingPart.QUADRANT4: (0.08, -0.12),
}

_as_pose = {
    1: Pose()
}

_agv_home_pose = {
    'agv1': [-2.27, 4.80, 0.01],
    'agv2': [-2.27, 1.20, 0.01],
    'agv3': [-2.27, -1.20, 0.01],
    'agv4': [-2.27, -4.80, 0.01]
}


class AGV(Node):
    def __init__(self, node_name: str):
        # Thread.__init__(self)
        super().__init__(node_name)
        sim_time = Parameter(
            "use_sim_time",
            rclpy.Parameter.Type.BOOL,
            True
        )
        self.name = self.get_name()
        self.model_name = f'{self.name}_base'
        self.set_parameters([sim_time])

        cb_callback = ReentrantCallbackGroup()
        sc_callback = MutuallyExclusiveCallbackGroup()
        self._agv_sensor_cli = self.create_client(AgvPartPose, f"/ariac/{self.name}_sensor", callback_group=cb_callback)
        self._move_cli = self.create_client(MoveAGV, f'/ariac/move_{self.name}', callback_group=cb_callback)
        self._lock_tray_cli = self.create_client(Trigger, f'/ariac/{self.name}_lock_tray', callback_group=cb_callback)
        self.first_move = True
        self._part_poses = None
        self.utils = Utils()

        self.last_target = None
        self._reached = 0

        # monitor

        self._faulty_part_cli = self.create_client(PerformQualityCheck, '/ariac/perform_quality_check',
                                                   callback_group=cb_callback)

        self._order_sub = self.create_subscription(Order, '/ariac/orders', self._order_cb, 10,
                                                   callback_group=cb_callback)
        self._bin_parts_sub = self.create_subscription(BinParts, '/ariac/bin_parts', self._bin_parts, 1,
                                                       callback_group=cb_callback)

        # self._agv_status_sub = []
        # for i in range(1, 5):
        #     self._agv_status_sub.append(
        #         self.create_subscription(AGVStatus, f'/ariac/agv{i}_status', self._agv_status, 1,
        #                                  callback_group=cb_callback))
        self._agv_status_sub = self.create_subscription(AGVStatus, f'/ariac/{self.name}_status', self._agv_status, 1,
                                                        callback_group=cb_callback)
        # self._agv_tray_cb = self.create_subscription(AdvancedLogicalCameraImage, f'/ariac/sensors/{self.name}/image',
        #                                              self._agv_tray_cb,
        #                                              1, callback_group=cb_callback)

        self.orders = Queue()
        self.order_id = None

    def _order_cb(self, order: Order):
        if order.type == Order.KITTING:
            agv_id = order.kitting_task.agv_number
            parts = order.kitting_task.parts  # part: part, quadrant
            destination = order.kitting_task.destination

            # print(f'order: {order.type}, {order.kitting_task}')
            if int(self.name[-1]) == agv_id:
                for part in parts:
                    self.orders.put((agv_id, part.part, destination, False))
        elif order.type == Order.ASSEMBLY:
            agv_ids = order.assembly_task.agv_numbers
            destination = order.assembly_task.station
            parts = order.assembly_task.parts
            # print(f'received an order: {order.id}, {order.type}')
            for agv in agv_ids:
                self.orders.put((agv, parts, destination, True))

    def _agv_status(self, msg: AGVStatus):
        self._reached = msg.location

    def _bin_parts(self, msg):
        pass

    def check_faulty_part(self, order_id: str):
        req = PerformQualityCheck.Request()
        req.order_id = order_id
        future = self._faulty_part_cli.call_async(req)
        rclpy.spin_until_future_complete(self, future, timeout_sec=3)

        if future.done():
            if future.result().all_passed:
                self.get_logger().info(f'check part state: all passed!')
                return True
        else:
            self.get_logger().warn(f'check part state: {future.result()}')
        return False

    def reset(self):
        pass

    def check(self, variable, except_val):
        pass

    def check_once(self, variable):
        if variable == "faulty_part":
            if self.check_faulty_part(self.order_id):  # todo order_id
                return True
            else:
                return False

    def get_part_pose_by_sensor(self, agv_id=None):
        request = AgvPartPose.Request()
        if agv_id is not None:
            request.id = agv_id
        else:
            request.id = int(self.name[-1])
        future = self._agv_sensor_cli.call_async(request)
        rclpy.spin_until_future_complete(self, future, timeout_sec=3)

        if future.done():
            # if res.success:
            self.get_logger().info(f'get {self.name} parts and pos: {future.result().parts}')
            pass

        res = []
        pump = None
        for part in future.result().parts:
            if part.part.type == 12:  # sensor
                res.insert(0, part)
            elif part.part.type == 11:
                pump = part
            else:
                res.append(part)
        if pump:
            res.append(pump)
        return res

    def move_to(self, destination: int):
        # self._reached = False
        if self.first_move:
            self._lock_agv_tray()
            self.first_move = False
        request = MoveAGV.Request()
        request.location = destination
        future = self._move_cli.call_async(request)
        rclpy.spin_until_future_complete(self, future)
        if future.result().success:
            self.get_logger().info(f'move {self.name} to destination {destination} !')
            self.last_target = destination
            # self._reached = True
            return True
        else:
            self.get_logger().warn(future.result().message)
        return False

    @property
    def reached(self):
        return self._reached

    @property
    def part_poses(self):
        self._part_poses = self.get_part_pose_by_sensor()
        return self._part_poses

    def _lock_agv_tray(self):
        request = Trigger.Request()
        future = self._lock_tray_cli.call_async(request)
        rclpy.spin_until_future_complete(self, future, timeout_sec=3)
        if future.result().success:
            # if res.success:
            self.get_logger().info(f'lock {self.name} tray......')
