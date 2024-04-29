from src.harness.agentThread import AgentThread


class Leader(AgentThread):

    def __init__(self, config, motion_config):
        super(Leader, self).__init__(config, motion_config)

    def initialize_vars(self):
        self.locals = {}
        self.locals['voted'] = False
        self.locals['i'] = 0
        self.locals['leader'] = None
        self.create_aw_var('candidate', int,  -1)
        self.initialize_lock('voted')

    def loop_body(self):
        if  not self.locals['voted']:
            if not self.lock('voted'):
                return
            if self.read_from_shared('candidate', None) < self.pid():
                self.write_to_shared('candidate', None, self.pid())
            else:
                self.locals['leader'] = self.read_from_shared('candidate', None)

            self.locals['voted'] = True
            self.unlock('voted')
            return
        if self.locals['voted']:
            if self.locals['i'] < self.num_agents():
                self.locals['i'] = self.locals['i'] + 1
                self.locals['leader'] = self.read_from_shared('candidate', None)
            else:
                self.log("Agent ")
                self.log(self.pid())
                self.log(": leader = ")
                self.log(self.locals['leader'])
                self.log("\n")

                self.trystop()

            return
