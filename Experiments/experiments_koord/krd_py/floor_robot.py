from src.harness.agentThread import AgentThread
from geometry_msgs.msg import Pose


class Floor_robot(AgentThread):

    def __init__(self, config, motion_config):
        super(Floor_robot, self).__init__(config, motion_config)

    def initialize_vars(self):
        self.locals = {}
        self.locals['state'] = "IDLE"
        self.locals['state1'] = "START"
        self.locals['state2'] = "IDLE"
        self.locals['agv_id'] = 1
        self.locals['count'] = 0
        self.locals['destination'] = 0
        self.create_aw_var('process', str, 'main')
        self.create_aw_var('exit', bool, False)
        self.locals['process'] = 'main'
        self.locals['part_nums'] = 0
        self.create_aw_var('all_loaded', list, None)
        #self.create_aw_var('part_locs_on_tray', list, None)
        #self.create_aw_var('update_part_on_agv', bool, None)
        self.create_aw_var('kit_starting_pose', Pose, None)
        self.create_aw_var('target_pose', Pose, None)
        self.create_aw_var('conv_part_pose', Pose, None)
        self.create_aw_var('ceiling_starting_pose', Pose, None)
        #self.create_aw_var('agv_reached', bool, None)
        self.create_aw_var('kit_ending_pose', Pose, None)
        self.create_aw_var('need_to_pick', bool, None)
        self.create_aw_var('agv_reached', list, None)
        self.create_aw_var('agv_has_part', bool, None)
        self.start = 0

    def loop_body(self):
        #print(f"floor robot.....{self.locals['destination']}, agv_reached{self.locals['agv_id']}: {self.read_from_shared('agv_reached', self.locals['agv_id'])}")
        if self.read_from_shared('process', None) == 'main':
            if self.locals['state'] == "START" and self.read_from_shared('kit_starting_pose', None) != None:
                self.write_to_actuator('Arm.reach_pos', self.read_from_shared('kit_starting_pose', None))
                self.locals['state'] = "PICK"
                return
            if self.locals['state'] == "PICK" and self.read_from_sensor('Arm.reached'):
                self.write_to_actuator('Gripper.enable_suction', True)
                self.locals['state'] = "PICKED"
                return
            if self.locals['state'] == "PICKED" and self.read_from_shared('agv_reached', self.locals['agv_id']) == 0:
                self.check_duration('Gripper.attached', True)
                self.write_to_actuator('Arm.reach_pos', self.read_from_shared('kit_ending_pose', None))  
                self.locals['state'] = "DROP"
                return
            if self.locals['state'] == "DROP" and self.read_from_sensor('Arm.reached'):
                self.write_to_actuator('Gripper.enable_suction', False)
                self.locals['state'] = "DROPPED"
                return
            if self.locals['state'] == "DROPPED":
                self.locals['state'] = "IDLE"
                self.locals['count'] = self.locals['count'] + 1
                if self.locals['count'] == self.locals['part_nums']:
                #self.reset()
                    self.locals['count'] = 0
                    self.write_to_shared('all_loaded', self.locals['agv_id'], True)
                #print(f"agv: {self.locals['agv_id']}, {self.read_from_shared('all_loaded', None)}")
                return
        if self.read_from_shared('process', None) == 'floor_robot_duration':
            if self.read_from_shared('exit', None):
                self.locals['state'] = "PICKED"
                self.write_to_shared('exit', None, False)
                self.write_to_shared('process', None, 'main')
                return
            if self.locals['state2'] == 'START':
                self.write_to_actuator('Gripper.enable_suction', False)
                self.write_to_shared('kit_starting_pose', None, self.check_part_on_bin(self.read_from_shared('kit_starting_pose', None)))
                if self.read_from_shared('kit_starting_pose', None):
                    print('*'*50, self.read_from_shared('kit_starting_pose', None))
                    self.write_to_actuator('Arm.reach_pos', self.read_from_shared('kit_starting_pose', None))
                    self.locals['state2'] = 'PICK'
                else:
                    self.write_to_shared('exit', None, True)
                return
            if self.locals['state2'] == 'PICK':
                self.write_to_actuator('Gripper.enable_suction', True)
                self.locals['state2'] = "PICKED"
                return
            if self.locals['state2'] == "PICKED":
                self.locals['state2'] = "IDLE"
                self.write_to_shared('exit', None, True)
                return
        if self.read_from_shared('process', None) == 'ceiling_robot_duration':
            if self.read_from_shared('exit', None):
                #self.write_to_shared('process', None, 'main')
                return
            if self.locals['state1'] == 'START' and self.read_from_shared('need_to_pick', None):
                self.locals['target_pose'] = self.check_part_on_bin(self.read_from_shared('ceiling_starting_pose', None).part)
                if self.locals['target_pose']:
                    self.write_to_actuator('Arm.reach_pos', self.locals['target_pose'])
                    self.locals['state1'] = 'PICK'
                else:
                    self.write_to_shared('exit', None, True)
                return
            if self.locals['state1'] == 'PICK':
                self.write_to_actuator('Gripper.enable_suction', True)
                self.locals['state1'] = "PICKED"
                return
            if self.locals['state1'] == 'PICKED':
                self.write_to_actuator('Arm.reach_pos', self.moat.utils.compute_tray_loc('agv1', 2))
                print('-'*50, self.moat.utils.compute_tray_loc('agv1', 4))
                self.locals['state1'] = "DROP"
                return
            if self.locals['state1'] == 'DROP' and self.read_from_sensor('Arm.reached'):
                self.write_to_actuator('Gripper.enable_suction', False)
                self.locals['state1'] = "DROPPED"
                return
            if self.locals['state1'] == "DROPPED":
                self.locals['state1'] = "IDLE"
                self.write_to_shared('agv_has_part', None, True)
                self.reset()
                return
        if self.read_from_shared('process', None) == 'faulty_part_exception':
            if self.read_from_shared('exit', None):
                self.write_to_shared('process', None, 'main')
                return
            if self.locals['state3'] == "START":
                part_pose = self.read_from_shared('faulty_part', None)
                self.write_to_actuator('Arm.reach_pos', part_pose)
                self.locals['state3'] = "PICK"
                return
            if self.locals['state3'] == 'PICK':
                self.write_to_actuator('Gripper.enable_suction', True)
                self.locals['state3'] = "PICKED"
                return
            if self.locals['state3'] == 'PICKED':
                self.write_to_actuator('Arm.reach_pos', self.moat.utils.compute_tray_loc('agv1', 4)) # trash
                self.locals['state3'] = "DROP"
                return
            if self.locals['state3'] == 'DROP' and self.read_from_sensor('Arm.reached'):
                self.write_to_actuator('Gripper.enable_suction', False)
                self.locals['state3'] = "DROPPED"
                return
            if self.locals['state3'] == 'DROPPED':
                self.write_to_shared('kit_starting_pose', None, self.check_part_on_bin(self.read_from_shared('part', None)))
                if self.read_from_shared('kit_starting_pose', None):
                    self.write_to_actuator('Arm.reach_pos', self.read_from_shared('kit_starting_pose', None))
                    self.locals['state3'] = 'REPICK'
                else:
                    self.write_to_shared('exit', None, True)
                return
            if self.locals['state3'] == 'REPICK':
                self.write_to_actuator('Gripper.enable_suction', True)
                self.locals['state3'] = "REPICKED"
                return
            if self.locals['state3'] == 'REPICKED':
                self.write_to_actuator('Arm.reach_pos', self.read_from_shared('faulty_part', None)) # origin
                self.locals['state3'] = "REDROP"
                return
            if self.locals['state3'] == "REDROP":
                self.locals['state3'] = "IDLE"
                self.write_to_shared('handled', None, True)
                return

