from geometry_msgs.msg import PoseStamped
from std_msgs.msg import String

from src.config.configs import BotType
from src.functionality.base_mutex_handler import BaseMutexHandler

from src.motion.agv import AGV
from src.motion.ceiling_robot import CeilingRobot
from src.motion.conveyor import Conveyor
from src.motion.demo_planner import DemoPlan
from src.motion.floor_robot import FloorRobot

from src.motion.rrt_star import RRT as RRT_STAR_CAR
from src.motion.rrt_drone import RRT as RRT_DRONE
from src.motion.rrt_star_drone import RRT as RRT_STAR_DRONE
from src.motion.rrt_star_dubins import RRT_DUBINS as RRT_DUB_CAR
from src.motion.rrt_car import RRT as RRT_CAR
from src.motion.simpleplanner import SimplePlanner


planner_dict = dict()
planner_dict['SimplePlanner'] = SimplePlanner
planner_dict['RRT_STAR_CAR'] = RRT_STAR_CAR
planner_dict['RRT_DRONE'] = RRT_DRONE
planner_dict['RRT_STAR_DRONE'] = RRT_STAR_DRONE
planner_dict['RRT_DUB_CAR'] = RRT_DUB_CAR
planner_dict['RRT_CAR'] = RRT_CAR
planner_dict['DemoPlan'] = DemoPlan

msg_type_dict = dict()
msg_type_dict['PoseStamped'] = PoseStamped
msg_type_dict['String'] = String

bot_type_dict = dict()
bot_type_dict['CAR'] = BotType.CAR
bot_type_dict['QUAD'] = BotType.QUAD
bot_type_dict['agv'] = BotType.agv
bot_type_dict['ceiling_robot'] = BotType.ceiling_robot
bot_type_dict['floor_robot'] = BotType.floor_robot
bot_type_dict['conveyor'] = BotType.conveyor

mutex_handler_dict = dict()
mutex_handler_dict['BaseMutexHandler'] = BaseMutexHandler

moat_class_dict = dict()

moat_class_dict['agv'] = AGV
moat_class_dict['ceiling_robot'] = CeilingRobot
moat_class_dict['floor_robot'] = FloorRobot
moat_class_dict['conveyor'] = Conveyor
