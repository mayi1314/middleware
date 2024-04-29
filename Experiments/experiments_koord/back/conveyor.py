from src.harness.agentThread import AgentThread
from geometry_msgs.msg import Pose


class Conveyor(AgentThread):

    def __init__(self, config, motion_config):
        super(Conveyor, self).__init__(config, motion_config)

    def initialize_vars(self):
        self.locals = {}
        self.locals['state'] = "STARTED"
        self.create_aw_var('process', str, 'main')
        self.create_aw_var('exit', bool, False)
        self.create_aw_var('part_pose', Pose, None)
        self.create_aw_var('target_pose', Pose, None)
        self.create_aw_var('part', Pose, None)
        self.create_ar_var('mypos', list, None)

    def loop_body(self):
        # if  not self.read_from_sensor('ConveyorBelt.enabled') and  not self.read_from_sensor('Proximity.object_detected'):
        #print('conveyor enter into loop..........................')
        if self.read_from_shared('process', None) == 'main' or self.read_from_shared('process', None) == 'ceiling_robot_duration':
            if self.locals['state'] == "STOPPED" and  not self.read_from_sensor('Proximity.object_detected'):
                self.write_to_actuator('ConveyorBelt.control_power', 0.0)
                self.locals['state'] = "STARTED"
                return
            # if self.read_from_sensor('ConveyorBelt.enabled') and self.read_from_sensor('Proximity.object_detected'):
            if self.locals['state'] == "STARTED" and self.read_from_sensor('Proximity.object_detected'):
                self.write_to_actuator('ConveyorBelt.control_power', 0.0)
                self.write_to_shared('part_pose', None, self.read_from_sensor('Proximity.part_loc')[0])
                self.locals['state'] = "STOPPED"
                return
