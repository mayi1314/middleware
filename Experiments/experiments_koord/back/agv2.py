from src.harness.agentThread import AgentThread
from geometry_msgs.msg import Pose


class Agv2(AgentThread):

    def __init__(self, config, motion_config):
        super(Agv2, self).__init__(config, motion_config)

    def initialize_vars(self):
        self.locals = {}
        self.locals['state'] = "IDLE"
        self.locals['name'] = "agv2"
        self.locals['quadrant'] = 4
        self.create_aw_var('agv2_to_station', int, 1)
        self.create_aw_var('agv2_all_loaded', bool, None)
        self.create_aw_var('part_locs_on_tray', list, None)
        self.create_aw_var('target_pose', Pose, None)
        self.create_aw_var('part', Pose, None)
        self.create_aw_var('agv2_reached', bool, None)
        self.create_aw_var('agv2_kit_part_pose', Pose, None)
        self.create_aw_var('agv2_update_part_on_agv', bool, None)
        self.create_ar_var('mypos', list, None)


    def loop_body(self):
        if self.read_from_shared('process', None) == 'main':
            if self.read_from_shared('agv2_all_loaded', None) and self.locals['state'] == "IDLE":
                self.write_to_actuator('AGV.to_station', self.read_from_shared('to_station', None))
                self.locals['state'] = "MOVING"
                self.write_to_shared('agv2_reached', None, False)
                return
            if self.locals['state'] == "MOVING" and self.read_from_sensor('AGV.reached'):
                self.write_to_shared('part_locs_on_tray', None, self.read_from_sensor('Proximity.part_loc'))
                self.write_to_shared('agv2_reached', None, True)
                self.locals['state'] = "END"
                return
            if self.read_from_shared('agv2_update_part_on_agv', None):
                self.write_to_shared('part_locs_on_tray', None, self.read_from_sensor('Proximity.part_loc'))
                self.write_to_shared('agv2_update_part_on_agv', None, False)
                return

