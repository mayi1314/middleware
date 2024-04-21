from src.harness.agentThread import AgentThread
from geometry_msgs.msg import Pose


class Agv1(AgentThread):

    def __init__(self, config, motion_config):
        super(Agv1, self).__init__(config, motion_config)

    def initialize_vars(self):
        self.locals = {}
        self.locals['state'] = "IDLE"
        self.locals['name'] = "agv1"
        self.locals['quadrant'] = 4
        self.locals['to_station_loc'] = 1
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
        self.write_to_shared('part_locs_on_tray', None, self.moat.utils.compute_tray_loc(self.locals['name'], self.locals['quadrant']))

    def loop_body(self):
        if self.read_from_shared('all_loaded', None) and self.locals['state'] == "IDLE":
            self.write_to_actuator('AGV.to_station', self.locals['to_station_loc'])
            self.locals['state'] = "MOVING"
            self.write_to_shared('agv_reached', None, False)
            return
        if self.locals['state'] == "MOVING" and self.read_from_sensor('AGV.reached'):
            self.write_to_shared('part_locs_on_tray', None, self.read_from_sensor('Proximity.part_loc'))
            self.write_to_shared('agv_reached', None, True)
            self.locals['state'] = "END"
            return
