import time

from src.harness.agentThread import AgentThread
from src.motion.pos_types import pos3d, Pos


class BasicFollowApp(AgentThread):

    def __init__(self, agent_config, moat_config):
        super(BasicFollowApp, self).__init__(agent_config, moat_config)
        self.start()

    def initialize_vars(self):
        self.create_ar_var('pos', Pos, self.moat.position)
        self.initialize_lock('singlelock')
        self.locals['current_dest'] = -1
        self.locals['obstacles'] = []
        self.locals['dest'] = [pos3d(2., 1.5, 0.), pos3d(1., -1., 1), pos3d(-0.75, 0., 0), pos3d(2., -0.5, 0.),
                               pos3d(-2., -2., 1.), pos3d(2, 0, 0), pos3d(1., 1.5, 0.),
                               pos3d(0, -1.5, 0), pos3d(-2., .75, 1), pos3d(2., -2., 0.),
                               pos3d(-1., -0.75, 0.), pos3d(-1., 0., 0.), pos3d(2, -1, 1)]
        self.create_aw_var('pointnum', list, [0 for i in range(len(self.locals['dest']))])
        self.locals['going'] = False
        self.locals['path'] = None

    def loop_body(self):
        time.sleep(1)
        self.locals['obstacles'] = []
        for vehicle in range(self.num_agents()):
            if vehicle == self.pid():
                continue
            if self.read_from_shared('pos', vehicle) is None:
                print("vehicle", vehicle, "hasn't published its position")
                pass
            else:
                self.locals['obstacles'].append(
                    self.read_from_shared('pos', vehicle).to_obs(0.5, self.moat.position.z))
        # print("my obstacle list is", [i.to_pos() for i in self.locals['obstacles']])

        if sum(self.read_from_shared('pointnum', None)) == len(self.locals['dest']):
            self.stop()
            return

        if not self.lock('singlelock'):
            return

        if not self.locals['going']:
            print("list is", self.read_from_shared('pointnum', None))
            for i in range(len(self.read_from_shared('pointnum', None))):
                if self.read_from_shared('pointnum', None)[i] == 0:
                    self.locals['current_dest'] = i
                else:
                    continue

                self.locals['path'] = self.moat.planner.find_path(self.moat.position,
                                                                            self.locals['dest'][
                                                                                self.locals['current_dest']],
                                                                            self.locals['obstacles'])
                if self.locals['path'] is None:
                    print("no path for current point, trying next ")
                else:
                    break

            if self.locals['path'] is None:
                self.unlock('singlelock')
                print('no path found')
                return

            print("path is", self.locals['path'])
            self.locals['pointnum'] = self.read_from_shared('pointnum', None)
            self.locals['pointnum'][self.locals['current_dest']] = 1
            self.write_to_shared('pointnum', None, self.locals['pointnum'])
            self.moat.follow_path(self.locals['path'])

            # self.moat.goTo(self.locals['dest'][self.read_from_shared('pointnum', None)])
            self.locals['going'] = True

        if self.moat.reached:
            print("here, next point")
            self.write_to_shared('pos', self.pid(), self.moat.position)

            time.sleep(0.1)
            self.locals['going'] = False
            self.unlock('singlelock')