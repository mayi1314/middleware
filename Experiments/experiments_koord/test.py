from src.harness.agentThread import AgentThread


class Lineform(AgentThread):

    def __init__(self, config, motion_config):
        super(Lineform, self).__init__(config, motion_config)

    def initialize_vars(self):
        self.locals = {}
        self.locals['i'] = 0
        self.create_ar_var('mypos', list, None)
        self.write_to_shared('mypos', self.pid(), self.read_from_sensor('Motion.position'))
        self.write_to_actuator('Motion.target', self.read_from_sensor('Motion.position') + self.pos3d(0.0, 0.0, 1.0))

    def loop_body(self):
        if self.locals['i'] < 100:
            self.locals['i'] = self.locals['i'] + 1
            self.write_to_shared('mypos', self.pid(), self.read_from_sensor('Motion.position'))
            if (self.pid() != 0 and self.pid() != self.num_agents() - 1):
                self.write_to_actuator('Motion.target', self.midpoint(self.read_from_shared('mypos', self.pid() + 1), self.read_from_shared('mypos', self.pid() - 1)))

            return
        if True:
            self.trystop()
            return
