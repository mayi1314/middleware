from src.harness.agentThread import AgentThread
from src.motion.pos_types import pos3d


class BasicFollowApp(AgentThread):

    def __init__(self, agent_config, moat_config):
        super(BasicFollowApp, self).__init__(agent_config, moat_config)
        self.start()

    def initialize_vars(self):
        self.locals['dest1'] = pos3d(2., 1., 0.)
        self.locals['dest2'] = pos3d(-2., 1., 0.)
        self.locals['dest3'] = pos3d(2., -1., 0.)
        self.locals['tries'] = 1

    def loop_body(self):
        if self.locals['tries'] == 1:
            self.moat.goTo(self.locals['dest1'])
            self.locals['tries'] = 2
            return
        if self.locals['tries'] == 2 and self.moat.reached:
            self.moat.goTo(self.locals['dest2'])
            self.locals['tries'] = 3
            return
        if self.locals['tries'] == 3 and self.moat.reached:
            self.moat.goTo(self.locals['dest3'])
            self.locals['tries'] = 4
            self.stop()
            return
