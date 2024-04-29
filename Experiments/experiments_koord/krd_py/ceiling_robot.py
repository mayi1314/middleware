from src.harness.agentThread import AgentThread
from geometry_msgs.msg import Pose


class Ceiling_robot(AgentThread):

    def __init__(self, config, motion_config):
        super(Ceiling_robot, self).__init__(config, motion_config)

    def initialize_vars(self):
        self.locals = {}
        self.locals['state'] = "IDLE"
        self.locals['part_count'] = 0
        self.locals['agv_id'] = 1
        self.create_aw_var('process', str, 'main')
        self.create_aw_var('exit', bool, False)
        self.create_aw_var('part_locs_on_tray', list, None)
        self.create_aw_var('update_part_on_agv', list, None)
        self.create_aw_var('ceiling_starting_pose', Pose, None)
        self.create_aw_var('target_pose', Pose, None)
        self.create_aw_var('part', Pose, None)
        self.locals['destination'] = 0
        self.create_aw_var('agv_reached', list, None)
        #self.create_aw_var('part_to_pick_loc', Pose, None)
        self.create_aw_var('agv1_reached', bool, None)
        self.start = 0

    def loop_body(self):
        if self.read_from_shared('process', None) == 'main':
            #print(f"ceiling robot state {self.locals['state']}.with agv.{self.locals['agv_id']}....{self.read_from_shared('agv_reached', self.locals['agv_id']), self.locals['destination']}")
            if self.locals['state'] == "START" and self.read_from_shared('part_locs_on_tray', self.locals['agv_id']) and self.read_from_shared('agv_reached', self.locals['agv_id']) == self.locals['destination']:
                self.write_to_shared('ceiling_starting_pose', None, self.read_from_shared('part_locs_on_tray', self.locals['agv_id'])[self.locals['part_count']])
                # print('*'*30,f"ceiling_starting_pose: {self.read_from_shared('part_locs_on_tray', self.locals['agv_id'])}")
                self.write_to_actuator('Arm.reach_pos', self.read_from_shared('ceiling_starting_pose', None))
                
                self.locals['state'] = "PICK"
                return
            if self.locals['state'] == "PICK" and self.read_from_sensor('Arm.reached'):
                self.write_to_actuator('Gripper.enable_suction', True)
                self.locals['state'] = "PICKED"
                return
            if self.locals['state'] == "PICKED" and self.read_from_sensor('Gripper.enabled'):
                #self.set_challenge('assemble_gripper_faulty', 1)
                self.check_duration('Gripper.attached', True)
                self.write_to_actuator('Arm.assemble', self.read_from_shared('ceiling_starting_pose', None).part)
                self.locals['state'] = "DROP"
                return
            if self.locals['state'] == "DROP" and self.read_from_sensor('Gripper.attached'):
                self.write_to_actuator('Gripper.enable_suction', False)
                #self.write_to_shared('update_part_on_agv', self.locals['agv_id'], True)
                self.locals['state'] = "DROPPED"
                return
            if self.locals['state'] == "DROPPED":
                self.locals['state'] = "START"
                self.locals['part_count'] = self.locals['part_count'] + 1
                if self.locals['part_count'] == len(self.read_from_shared('part_locs_on_tray', self.locals['agv_id'])):
                #self.reset()
                    self.locals['count'] = 0
                    self.locals['state'] = "IDLE"
                return
        if self.read_from_shared('process', None) == 'ceiling_robot_duration':
            if self.read_from_shared('exit', None):
                self.locals['state'] = "PICKED"
                self.write_to_shared('exit', None, False)
                self.write_to_shared('process', None, 'main')
                return
            if self.locals['state1'] == "START" and self.read_from_shared('agv1_reached', None):
                self.write_to_shared('ceiling_starting_pose', None, self.read_from_shared('part_locs_on_tray', 1)[0]) # agv_id
                self.write_to_actuator('Arm.reach_pos', self.read_from_shared('ceiling_starting_pose', None))
                self.locals['state1'] = "PICK"
                return
            if self.locals['state1'] == "PICK":
                self.write_to_actuator('Gripper.enable_suction', True)
                self.locals['state1'] = "IDLE"
                self.write_to_shared('exit', None, True)
                return


