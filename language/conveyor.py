from src.harness.agentThread import AgentThread
from geometry_msgs.msg import Pose


class Conveyor(AgentThread):

    def __init__(self, config, motion_config):
        super(Conveyor, self).__init__(config, motion_config)

    def initialize_vars(self):
        self.locals = {}
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
        if  not self.read_from_sensor('ConveyorBelt.enabled') and  not self.read_from_sensor('Proximity.object_detected'):
            self.write_to_actuator('ConveyorBelt.control_power', 10.0)
            return
        if self.read_from_sensor('ConveyorBelt.enabled') and self.read_from_sensor('Proximity.object_detected'):
            self.write_to_actuator('ConveyorBelt.control_power', 0.0)
            self.write_to_shared('current_part_pose', None, self.read_from_sensor('Proximity.part_loc'))
            return
