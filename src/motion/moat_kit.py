import rclpy

import numpy as np
import std_srvs.srv

from src.motion.motionautomaton import MotionAutomaton
from src.motion.pos_types import Pos


class MoatKit(MotionAutomaton):

    def __init__(self, config):
        super(MoatKit, self).__init__(config)
        self.__suck_cli = self.node.create_client(std_srvs.srv.SetBool, 'suck')
        # 每隔一秒检查服务端是否运行,若没有运行就等待
        while not self.__suck_cli.wait_for_service(timeout_sec=1.0):
            print('等待服务suck的服务端运行...')
        # 创建服务消息类型对象
        self.req = std_srvs.srv.SetBool.Request()

    def _getPositioning(self, data) -> None:
        self.position = Pos(np.array([data.pose.position.x, data.pose.position.y, data.pose.position.z]))

    def _getReached(self, data) -> None:
        a = str(data).upper()
        if 'TRUE' in a:
            self.reached = True

    def moat_init_action(self):
        super().moat_init_action()
        # while not self.reached:
        #     time.sleep(0.1)
        # pass

    def moat_exit_action(self):
        # TODO: maybe incorporate call to best here?
        super().moat_exit_action()
        pass

    def send_request(self, apply_result):
        self.req.data = apply_result  # 设置申请结果为真，让服务端判断为可以返回检测结果
        future = self.__suck_cli.call_async(self.req)  # 异步方式发送服务请求
        rclpy.spin_until_future_complete(self.node, future)  # 阻塞直到self.future完成
        return future.result()  # 返回结果

    def goTo(self, dest: Pos, wp_type: int = None) -> None:
        print("going to point", dest)
        if wp_type is not None:
            frame_id = str(wp_type)
        else:
            frame_id = '1'

        from geometry_msgs.msg import PoseStamped
        from rclpy import time

        pose = PoseStamped()
        pose.header.stamp = rclpy.time.Time()
        pose.header.frame_id = frame_id
        pose.pose = dest.to_pose()

        self.reached = False

        self.waypoint_count += 1
        # print(self.pub)
        self.pub.publish(pose)

    def follow_path(self, path: list) -> None:
        for wp in path[:-1]:
            self.goTo(wp, 0)
        self.goTo(path[-1], 1)

    def run(self):
        rclpy.spin(self.node)
