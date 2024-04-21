from src.harness.agentThread import AgentThread
from geometry_msgs.msg import Pose


class Floor_robot(AgentThread):

    def __init__(self, config, motion_config):
        super(Floor_robot, self).__init__(config, motion_config)

    def initialize_vars(self):
        self.locals = {}
        self.locals['state'] = "IDLE"
        self.locals['part_count'] = 0
        self.create_aw_var('all_loaded', bool, None)
        self.create_aw_var('part_locs_on_tray', list, None)
        self.create_aw_var('nums_part_on_tray', int, None)
        self.create_aw_var('assembly_pos', list, None)
        self.create_aw_var('current_part_pose', Pose, None)
        self.create_aw_var('target_pose', Pose, None)
        self.create_aw_var('part', Pose, None)
        self.create_aw_var('agv_reached', bool, None)
        self.create_aw_var('part_to_pick_loc', Pose, None)
        self.create_ar_var('mypos', list, None)

    def loop_body(self):
        if self.locals['state'] == "IDLE" and self.read_from_shared('current_part_pose', None) != None:
            self.write_to_actuator('Arm.reach_pos', self.read_from_shared('current_part_pose', None))
            self.locals['state'] = "PICK"
            return
        if self.locals['state'] == "PICK" and self.read_from_sensor('Arm.reached'):
            self.write_to_actuator('Gripper.enable_suction', True)
            self.locals['state'] = "PICKED"
            return
        if self.locals['state'] == "PICKED" and self.read_from_sensor('Gripper.enabled') and self.read_from_sensor('Gripper.attached'):
            self.write_to_actuator('Arm.reach_pos', self.read_from_shared('part_to_pick_loc', None))
            self.locals['state'] = "DROP"
            return
        if self.locals['state'] == "DROP" and self.read_from_sensor('Arm.reached'):
            self.write_to_actuator('Gripper.enable_suction', False)
            self.locals['state'] = "DROPPED"
            return
        if self.locals['state'] == "DROPPED":
            self.locals['state'] = "END"
            self.write_to_shared('all_loaded', None, True)
            return
