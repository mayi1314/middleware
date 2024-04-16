import threading
import time
from abc import ABC, abstractmethod
from threading import Thread, Event
from typing import Optional

import rclpy

from src.config.configs import AgentConfig, MoatConfig
from src.functionality.comm_funcs import send
from src.harness.comm_handler import CommHandler
from src.harness.gvh import Gvh
from src.harness.message_handler import stop_comm_msg_create, round_update_msg_create, stop_msg_create
from src.motion import utils
from src.motion.pos_types import pos3d
from src.objects.base_mutex import BaseMutex


class AgentThread(ABC, Thread):
    """
    abstract object for each agent thread/application. This contains
    the methods and fields each agent application must implement.
    """

    def __init__(self, agent_config: AgentConfig, moat_config) -> None:
        """

        :param agent_config:
        :param moat_config:
        """
        super(AgentThread, self).__init__()
        rclpy.init()

        # TODO MotionAutomaton class should provide a builder function with configs as parameters
        #  and returns an optional MotionAutomaton instance
        self.__moat = None
        # Create Motion module if specified in configurations
        if agent_config.moat_class is not None:
            # self.__moat = agent_config.moat_class(moat_config)
            # self.__moat.start()  # Start Motion thread that listens to ROS topics
            self.executor = rclpy.executors.MultiThreadedExecutor()
            self.__moat = agent_config.moat_class(moat_config)
            self.executor.add_node(self.__moat)
            threading.Thread(target=lambda node: self.executor.spin(), args=(self.__moat,)).start()

        self.__agent_gvh = Gvh(agent_config)
        self.__agent_gvh.comm_handler.start()  # Start CommHandler threads that listens to UDP messages
        self.__agent_gvh.start_mh()  # Start MutexHandler thread that sends messages to grant mutex?
        self.__stop_event = Event()

        self.log = lambda msg: print(msg, end="")  # TODO logging besides printing
        self.any = any
        self.all = all
        self.pos3d = pos3d
        self.midpoint = lambda p0, p1: (p0 + p1) / 2
        self.toList = lambda *args: list(args)

        self.requestedlocks = {}
        self.ackedlocks = {}

        self.baselocks = {}
        self.locals = {}

        self.timing = ""
        self.challenges = {}

    def check_order(self):
        if self.moat.name.startswith('floor_robot'):
            self.moat.handle_order()
        if self.locals['state'] == 'IDLE' and not self.moat.orders.empty():
            print(f'{self.moat.name}: {self.locals["state"]} entering--------------------')
            if self.moat.name.startswith('agv'):
                agv_id, part, destination, all_load = self.moat.orders.get()
                self.locals['state'] = "START"
                self.locals['destination'] = destination  # todo: map agv_id to pid
                if all_load:
                    self.write_to_shared('all_loaded', self.pid(), True)
            if self.moat.name.startswith('floor_robot'):
                agv_id, kit_starting_pose, kit_ending_pose, destination, part_nums = self.moat.orders.get()
                self.locals['state'] = "START"
                # self.write_to_shared('all_loaded', agv_id, True)
                self.locals['agv_id'] = agv_id
                self.locals['part_nums'] = part_nums
                self.locals['destination'] = destination
                if self.read_from_shared('conv_part_pose', None):
                    self.write_to_shared('kit_starting_pose', None, self.read_from_shared('conv_part_pose', None))
                else:
                    self.write_to_shared('kit_starting_pose', None, kit_starting_pose)
                self.write_to_shared('kit_ending_pose', None, kit_ending_pose)
                self.write_to_shared('conv_part_pose', None, None)
            if self.moat.name.startswith('ceiling_robot'):
                agv_id, destination, parts = self.moat.orders.get()
                self.locals['agv_id'] = agv_id
                self.locals['destination'] = destination
                self.locals['state'] = "START"
            # if self.moat.name.startswith('conveyor'):
            #     self.locals['state'] = "START"

    def check_before(self, state: str, value):
        self.timing = 'before'
        if not self.moat.check_once(state, value):
            self.moat.utils.get_pose_status()
            pass


    def check_after(self, state: str, value):
        self.timing = 'after'
        self.moat.check_once(state, value)

    def check_duration(self, state: str, value):
        self.timing = 'duration'
        self.moat.check(state, value)

    def set_challenge(self, exception, max_count):
        if exception in self.moat.challenges:
            self.challenges[exception] += 1
        else:
            self.challenges[exception] = 1
        if self.challenges[exception] <= max_count:
            self.moat.challenges[exception] = True

    def check_part_on_bin(self, part):
        return self.moat.check_part_on_bin(part)

    def reset(self):
        self.moat.reset()

    def create_ar_var(self, name, dtype, initial_value=None):
        self.agent_gvh.create_ar_var(name, dtype, initial_value)
        pass

    def create_aw_var(self, name, dtype, initial_value=None):
        self.agent_gvh.create_aw_var(name, dtype, initial_value)
        pass

    def initialize_lock(self, key):
        self.baselocks[key] = BaseMutex(key, self.agent_gvh.port_list)
        self.agent_gvh.mutex_handler.add_mutex(self.baselocks[key])
        self.baselocks[key].agent_comm_handler = self.agent_comm_handler
        self.requestedlocks[key] = False
        self.ackedlocks[key] = False
        self.agent_gvh.req_nums[key] = 0
        self.agent_gvh.ack_nums[key] = -1

    def lock(self, key: str):
        if self.agent_gvh.ack_nums[key] == self.agent_gvh.req_nums[key]:
            self.ackedlocks[key] = True

        # print("checking locking")
        if not self.requestedlocks[key]:
            self.baselocks[key].request_mutex(self.agent_gvh.req_nums[key])
            self.requestedlocks[key] = True
            return False
        elif not self.ackedlocks[key]:
            if not self.agent_gvh.mutex_handler.has_mutex(self.baselocks[key].mutex_id):
                self.baselocks[key].request_mutex(self.agent_gvh.req_nums[key])
                return False
        else:
            if not self.agent_gvh.mutex_handler.has_mutex(self.baselocks[key].mutex_id):
                return False

        self.agent_gvh.req_nums[key] += 1
        return True

    def unlock(self, key: str):
        self.baselocks[key].release_mutex()
        self.requestedlocks[key] = False
        time.sleep(0.1)

    @property
    def moat(self):
        """
        getter method for motionAutomaton
        :return:
        """
        return self.__moat

    @property
    def locals(self):
        return self.__locals

    @locals.setter
    def locals(self, locals):
        self.__locals = locals

    @property
    def agent_gvh(self) -> Gvh:
        """
        getter method for agent gvh
        :return: gvh of the current agent thread object
        """
        return self.__agent_gvh

    @property
    def agent_comm_handler(self) -> CommHandler:
        """
        getter method for agent comm handler
        :return: the communication handler of the agent thread object
        """
        return self.__agent_gvh.comm_handler

    def stop(self) -> None:
        """
         a flag to set to to safely exit the thread
        :return: None
        """
        if self.moat is not None:
            # self.moat.moat_exit_action()
            self.moat.join()
            # todo: msg = tERMINATE_MSG() best termination message
            # TODO: send(msg,"",best_post,time.time()) best termination message.
        if self.agent_gvh.mutex_handler is not None:
            if not self.agent_gvh.mutex_handler.stopped():
                self.agent_gvh.mutex_handler.stop()
        if self.agent_comm_handler is not None:
            self._broadcast(stop_comm_msg_create(self.pid(), time.time()))
            self.msg_handle()  # Let comm_handler try to stop itself first
            if not self.agent_comm_handler.stopped():
                self.agent_comm_handler.stop()
                self.agent_comm_handler.join()  # Wait until comm_handler finishes
        if not self.stopped():
            self.__stop_event.set()
            print("stopped application thread on agent", self.pid())

    def stopped(self) -> bool:
        """
        set the stop flag
        :return: true if stop event is set, false otherwise
        """
        return self.__stop_event.is_set()

    def pid(self) -> int:
        """
        get the pid of the current agent.
        uses the agent gvh to retrieve it
        :return: integer pid of the current agent
        """
        return self.agent_gvh.pid

    def num_agents(self) -> int:
        """
        method to return the number of participants in the system
        :return: integer number of participants
        """
        return self.agent_gvh.participants

    def receiver_port(self) -> int:
        """
        gets the port the application is receiving messages on
        :return: integer receiver port
        """
        return self.agent_comm_handler.r_port

    def receiver_ip(self) -> str:
        """
        method to get the ip of receiver
        :return: string ip of receiver
        """
        return self.agent_comm_handler.ip

    def msg_handle(self):
        time.sleep(0.1)
        self.agent_gvh.flush_msgs()
        time.sleep(0.1)

    @abstractmethod
    def initialize_vars(self):
        """
        abstract method to initialize variables
        :return:
        """
        pass

    @abstractmethod
    def loop_body(self):
        """
        loop body
        :return:
        """
        pass

    def write_to_shared(self, var_name, index, value):
        if index is not None:
            self.agent_gvh.put(var_name, value, index)
        else:
            self.agent_gvh.put(var_name, value)

    def read_from_shared(self, var_name, index):
        if index is not None:
            return self.agent_gvh.get(var_name, index)
        else:
            return self.agent_gvh.get(var_name)
        pass

    def read_from_sensor(self, var_name: str):
        if var_name == 'Proximity.object_detected':
            return self.moat.part_poses != []
        elif var_name == 'Proximity.part_loc':
            return self.moat.part_poses
        elif var_name == 'ConveyorBelt.enabled':
            return self.moat.enabled
        elif var_name == 'Gripper.enabled':
            return self.moat.gripper_enabled
        elif var_name == 'Gripper.attached':
            return self.moat.gripper_attached
        elif var_name == 'Arm.reached':
            return self.moat.reached
        elif var_name == 'AGV.reached':
            return self.moat.reached

        else:
            raise KeyError("Cannot find module sensor '" + var_name + "'")

    def write_to_actuator(self, var_name: str, value) -> None:
        if var_name == 'AGV.to_station':
            self.moat.move_to(value)
        elif var_name == 'ConveyorBelt.control_power':
            self.moat.set_power(value)
        elif var_name == 'Arm.reach_pos':
            self.write_to_shared('target_pose', None, value)  # write to target_pose
            self.moat.move_to(value)

        elif var_name == 'Arm.assemble':
            self.moat.assemble(self.locals['destination'], value)
        elif var_name == 'Gripper.enable_suction':
            if self.moat.name == 'floor_robot':
                part_pose = self.read_from_shared('kit_starting_pose', None)
            else:
                part_pose = self.read_from_shared('ceiling_starting_pose', None)
            if value:
                self.moat.attach(part_pose.part, part_pose.pose, None)
            else:
                self.moat.detach(part_pose.part)
        else:
            raise KeyError("Cannot find module actuator '" + var_name + "'")

    def release_unnecessary_mutexes(self):
        for i in list(self.baselocks.keys()):
            if self.agent_gvh.mutex_handler.has_mutex(self.baselocks[i].mutex_id):
                if not self.requestedlocks[i]:
                    self.baselocks[i].release_mutex()

    def trystop(self):
        stop_msg = stop_msg_create(self.pid(), self.agent_gvh.round_num, self.agent_gvh.round_num)
        self._broadcast(stop_msg)

    def run(self) -> None:
        """
        needs to be implemented for any agentThread
        :return:
        """
        # if self.moat is not None:
        #     self.moat.moat_init_action()

        # create init messages, and keep sending until leader acks, then start app thread.
        # leader only starts once everyone has received an ack.
        from src.harness.message_handler import init_msg_create
        init_msg = init_msg_create(self.pid(), self.agent_gvh.round_num)
        while not self.agent_gvh.init:
            # print("sending init", self.pid())
            self.initialize_vars()
            self.msg_handle()
            self._broadcast(init_msg)
            time.sleep(0.2)

        while not self.stopped():
            self.msg_handle()
            self.release_unnecessary_mutexes()
            if not self.agent_gvh.is_alive:
                # print("stopping app thread on ", self.pid())
                self.stop()
                continue
            # Keep sending round update message until receive confirmation from leader
            # I.e., msg_handle() will set `agent_gvh.update_round` to True only when confirmed
            if not self.agent_gvh.update_round:
                round_update_msg = round_update_msg_create(self.pid(), self.agent_gvh.round_num,
                                                           time.time())
                self._broadcast(round_update_msg)
                continue
            # else:
            # print("Agent", self.pid(), "executing round", self.agent_gvh.round_num)
            try:
                # print('-'*30, f"{self.moat.name}, process: {self.read_from_shared('process', None)}, state: {self.locals['state']}, "
                #               f"state1: {self.locals.get('state1')}")
                # tmp = self.read_from_shared('part_pose', None)
                # if tmp is not None:
                #     self.write_to_shared('part', None, tmp.part)
                # if self.timing == 'before':

                self.loop_body()
                self.check_order()
                # resetting motion automaton
                # if self.moat is not None:
                #     self.moat.reset()

                if self.timing == 'after' or self.timing == 'duration':
                    if self.moat.timer and not self.moat.timer.is_canceled():
                        self.moat.timer.cancel()
                    for err, val in self.moat.error.items():
                        if val:
                            if self.moat.name == 'floor_robot':
                                self.write_to_shared('process', None, 'floor_robot_duration')
                                self.locals['state2'] = 'START'
                                self.moat.error = {}
                            else:
                                self.write_to_shared('process', None, 'ceiling_robot_duration')
                                self.locals['state1'] = 'START'
                                self.moat.error = {}
                            break
                self.timing = ''

                self.agent_gvh.update_round = False

            except OSError as e:
                print("some unhandled error in application thread for agent", e)
                self.trystop()

            if self.agent_comm_handler.stopped():
                self.trystop()

    def _broadcast(self, msg) -> None:
        send(msg, AgentConfig.BROADCAST_ADDR, self.receiver_port())
