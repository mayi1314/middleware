from src.harness.agentThread import AgentThread
from geometry_msgs.msg import Pose


class Agv1(AgentThread):

    def __init__(self, config, motion_config):
        super(Agv1, self).__init__(config, motion_config)

    def initialize_vars(self):
        self.locals = {}
        self.locals['state'] = "IDLE"
        self.locals['state1'] = "START"
        self.locals['state2'] = "START"
        self.locals['i'] = 0
        self.create_aw_var('process', str, 'main')
        self.create_aw_var('exit', bool, False)
        self.locals['name'] = "agv1"
        self.locals['quadrant'] = 4
        self.locals['destination'] = 0
        self.create_aw_var('all_loaded', list, None)
        self.create_aw_var('part_locs_on_tray', list, None)
        self.create_aw_var('update_part_on_agv', list, None)
        self.create_aw_var('ceiling_starting_pose', Pose, None)
        self.create_aw_var('target_pose', Pose, None)
        self.create_aw_var('part', Pose, None)
        self.create_aw_var('agv_reached', list, None)
        #self.create_aw_var('kit_ending_pose', Pose, None)
        #self.write_to_shared('kit_ending_pose', None, self.moat.utils.compute_tray_loc(self.locals['name'], self.locals['quadrant']))
        #self.write_to_shared('destination', None, 1)
        self.write_to_shared('all_loaded', None, [False]*4)
        self.write_to_shared('agv_reached', None, [0]*4)
        self.write_to_shared('part_locs_on_tray', None, [False]*4)
        self.write_to_shared('update_part_on_agv', None, [False]*4)
        self.create_aw_var('agv_has_part', bool, None)
        self.create_aw_var('need_to_pick', bool, None)
        self.create_aw_var('agv1_reached', bool, None)

    def loop_body(self):
        if self.read_from_shared('process', None) == 'main':
            #print(f"agv..{self.pid()}....{self.locals['destination']}, agv_reached: {self.read_from_sensor('AGV.reached')}")
            if self.read_from_shared('all_loaded', self.pid()) and self.locals['state'] == "START":
                self.write_to_actuator('AGV.to_station', self.locals['destination'])
                self.locals['state'] = "MOVING"
                return
            if self.locals['state'] == "MOVING": # and self.read_from_sensor('AGV.reached') == self.locals['destination']:
                #self.check_before('faulty_part', True)
                self.write_to_shared('part_locs_on_tray', self.pid(), self.read_from_sensor('Proximity.part_loc'))
                self.write_to_shared('agv_reached', self.pid(), self.locals['destination'])
                self.locals['state'] = "IDLE"
                return
            if self.read_from_shared('update_part_on_agv', self.pid()):
                self.write_to_shared('part_locs_on_tray', self.pid(), self.read_from_sensor('Proximity.part_loc'))
                self.write_to_shared('update_part_on_agv', self.pid(), False)
                return

        if self.read_from_shared('process', None) == 'ceiling_robot_duration':
            if self.read_from_shared('exit', None):
                self.write_to_shared('process', None, 'main')
                return
            if self.locals['state1'] == "START":
                if self.read_from_sensor('Proximity.object_detected'):
                    for i in range(len(self.read_from_sensor('Proximity.part_loc'))):
                        self.locals['tmp_part'] = self.read_from_sensor('Proximity.part_loc')[i]
                        if self.locals['tmp_part'].part.type == self.read_from_shared('ceiling_starting_pose', None).part.type and self.locals['tmp_part'].part.color == self.read_from_shared('ceiling_starting_pose', None).part.color:
                            self.write_to_shared('agv_has_part', None, True)
                            self.locals['state1'] = "WAITED"
                            return
                    self.locals['state1'] = "WAIT"
                    return
                # if self.read_from_sensor('Proximity.object_detected') and self.locals['i'] < len(self.read_from_sensor('Proximity.part_loc')):
                #     if self.read_from_sensor('Proximity.part_loc')[self.locals['i']].part.type == self.read_from_shared('part', None).type and self.read_from_sensor('Proximity.part_loc')[self.locals['i']].part.color == self.read_from_shared('part', None).color:
                #         self.write_to_shared('agv_has_part', None, True)
                #         self.locals['state1'] = "WAITED"
                #     if self.locals['state1'] == "IDLE" and self.locals['i'] == len(self.read_from_sensor('Proximity.part_loc')) - 1:
                #         self.locals['state1'] = "WAIT"
                #     self.locals['i'] = self.locals['i'] + 1
                # else:
                #     self.locals['state1'] = "WAIT"
                # return
            if self.locals['state1'] == "WAIT":
                self.write_to_actuator('AGV.to_station', 0)
                self.write_to_shared('need_to_pick', None, True)
                self.locals['state1'] = "WAITED"
                return
            if self.locals['state1'] == "WAITED" and self.read_from_shared('agv_has_part', None):
                self.locals['state1'] = "MOVE"
                self.write_to_actuator('AGV.to_station', self.locals['destination'])
                return
            if self.locals['state1'] == "MOVE":
                self.locals['state1'] = "IDLE"
                self.write_to_shared('part_locs_on_tray', self.pid(), self.read_from_sensor('Proximity.part_loc'))
                self.write_to_shared('agv1_reached', None, True)
                return
        if self.read_from_shared('process', None) == 'faulty_part_exception':
            if self.read_from_shared('exit', None):
                self.locals['state'] = "MOVING"
                self.write_to_shared('exit', None, False)
                self.write_to_shared('process', None, 'main')
                return
            if self.locals['state2'] == "START":
                self.write_to_actuator('AGV.to_station', 0)
                self.locals['state2'] = "RETURN"
                return
            if self.locals['state2'] == "RETURN":
                part_pose = self.moat.check_faulty_part()
                self.write_to_shared('faulty_part', None, part_pose)
                self.locals['state2'] = "IDLE"
                self.write_to_shared('exit', None, True)
                return
            if self.locals['state2'] == "IDLE" and self.read_from_shared('handled', None):
                self.locals['state2'] = "END"
                self.write_to_shared('exit', None, True)
                return

