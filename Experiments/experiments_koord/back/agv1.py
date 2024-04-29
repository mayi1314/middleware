from src.harness.agentThread import AgentThread
from geometry_msgs.msg import Pose


class Agv1(AgentThread):

    def __init__(self, config, motion_config):
        super(Agv1, self).__init__(config, motion_config)

    def initialize_vars(self):
        self.locals = {}
        self.locals['state'] = "IDLE"
        self.locals['state1'] = "IDLE"
        self.locals['i'] = 0
        self.create_aw_var('process', str, 'main')
        self.create_aw_var('exit', bool, False)
        self.locals['name'] = "agv1"
        self.locals['quadrant'] = 4
        self.create_aw_var('to_station', int, None)
        self.create_aw_var('all_loaded', bool, None)
        self.create_aw_var('part_locs_on_tray', list, None)
        self.create_aw_var('update_part_on_agv', bool, None)
        self.create_aw_var('part_pose', Pose, None)
        self.create_aw_var('target_pose', Pose, None)
        self.create_aw_var('part', Pose, None)
        self.create_aw_var('agv_reached', bool, None)
        self.create_aw_var('kit_part_pose', Pose, None)
        self.create_ar_var('mypos', list, None)
        self.write_to_shared('kit_part_pose', None, self.moat.utils.compute_tray_loc(self.locals['name'], self.locals['quadrant']))
        self.write_to_shared('to_station', None, 1)
        self.create_aw_var('agv_has_part', bool, None)
        self.create_aw_var('need_to_pick', bool, None)
        self.create_aw_var('agv1_reached', bool, None)

    def loop_body(self):
        if self.read_from_shared('process', None) == 'main':
            if self.read_from_shared('all_loaded', None) and self.locals['state'] == "IDLE":
                self.write_to_actuator('AGV.to_station', self.read_from_shared('to_station', None))
                self.locals['state'] = "MOVING"
                self.write_to_shared('agv_reached', None, False)
                return
            if self.locals['state'] == "MOVING" and self.read_from_sensor('AGV.reached'):
                self.write_to_shared('part_locs_on_tray', None, self.read_from_sensor('Proximity.part_loc'))
                self.write_to_shared('agv_reached', None, True)
                self.locals['state'] = "END"
                return
            if self.read_from_shared('update_part_on_agv', None):
                self.write_to_shared('part_locs_on_tray', None, self.read_from_sensor('Proximity.part_loc'))
                self.write_to_shared('update_part_on_agv', None, False)
                return

        if self.read_from_shared('process', None) == 'ceiling_robot_duration':
            if self.read_from_shared('exit', None):
                self.write_to_shared('process', None, 'main')
                return
            if self.locals['state1'] == "IDLE":
                if self.read_from_sensor('Proximity.object_detected') and self.locals['i'] < len(self.read_from_sensor('Proximity.part_loc')):
                    if self.read_from_sensor('Proximity.part_loc')[self.locals['i']].part.type == self.read_from_shared('part', None).type and self.read_from_sensor('Proximity.part_loc')[self.locals['i']].part.color == self.read_from_shared('part', None).color:
                        self.write_to_shared('agv_has_part', None, True)
                        self.locals['state1'] = "WAITED"
                    if self.locals['state1'] == "IDLE" and self.locals['i'] == len(self.read_from_sensor('Proximity.part_loc')) - 1:
                        self.locals['state1'] = "WAIT"
                    self.locals['i'] = self.locals['i'] + 1
                else:
                    self.locals['state1'] = "WAIT"
                return
            if self.locals['state1'] == "WAIT":
                self.write_to_actuator('AGV.to_station', 0)
                self.write_to_shared('need_to_pick', None, True)
                self.locals['state1'] = "WAITED"
                return
            if self.locals['state1'] == "WAITED" and self.read_from_shared('agv_has_part', None):
                self.locals['state1'] = "MOVE"
                self.write_to_actuator('AGV.to_station', 1)
                return
            if self.locals['state1'] == "MOVE":
                self.locals['state1'] = "END"
                self.write_to_shared('part_locs_on_tray', None, self.read_from_sensor('Proximity.part_loc'))
                self.write_to_shared('agv1_reached', None, True)
                return


