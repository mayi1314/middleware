from src.harness.agentThread import AgentThread
from geometry_msgs.msg import Pose


class Agv2(AgentThread):

    def __init__(self, config, motion_config):
        super(Agv2, self).__init__(config, motion_config)

    def initialize_vars(self):
        self.locals = {}
        self.locals['state'] = "IDLE"
        self.locals['state1'] = "IDLE"
        self.locals['i'] = 0
        self.create_aw_var('process', str, 'main')
        self.create_aw_var('exit', bool, False)
        self.locals['quadrant'] = 4
        self.locals['destination'] = 0
        self.create_aw_var('all_loaded', list, None)
        self.create_aw_var('part_locs_on_tray', list, None)
        self.create_aw_var('update_part_on_agv', list, None)
        self.create_aw_var('part_pose', Pose, None)
        self.create_aw_var('target_pose', Pose, None)
        self.create_aw_var('part', Pose, None)
        self.create_aw_var('agv_reached', list, None)
        #self.create_aw_var('kit_ending_pose', Pose, None)
        #self.write_to_shared('kit_ending_pose', None, self.moat.utils.compute_tray_loc(self.locals['name'], self.locals['quadrant']))
        #self.write_to_shared('destination', None, 1)
        #self.write_to_shared('all_loaded', None, [False]*4)
        self.create_aw_var('agv_has_part', bool, None)
        self.create_aw_var('need_to_pick', bool, None)
        self.create_aw_var('agv2_reached', bool, None)


    def loop_body(self):
        if self.read_from_shared('process', None) == 'main':
            if self.read_from_shared('all_loaded', self.pid()) and self.locals['state'] == "START":
                self.write_to_actuator('AGV.to_station', self.locals['destination'])
                self.locals['state'] = "MOVING"
                return
            if self.locals['state'] == "MOVING" and self.read_from_sensor('AGV.reached') == self.locals['destination']:
                self.write_to_shared('part_locs_on_tray', self.pid(), self.read_from_sensor('Proximity.part_loc'))
                self.write_to_shared('agv_reached', self.pid(), self.locals['destination'])
                self.locals['state'] = "IDLE"
                return
            if self.read_from_shared('update_part_on_agv', self.pid()):
                self.write_to_shared('part_locs_on_tray', self.pid(), self.read_from_sensor('Proximity.part_loc'))
                self.write_to_shared('update_part_on_agv', self.pid(), False)
                return
