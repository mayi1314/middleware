import time

from src.harness.agentThread import AgentThread
from src.motion.pos_types import pos3d


class BasicFollowApp(AgentThread):

    def __init__(self, agent_config, moat_config):
        super(BasicFollowApp, self).__init__(agent_config, moat_config)

    def initialize_vars(self):
        self.locals['dest1'] = pos3d(2., 2., 0.)  # Pos(np.array([2., 1., 0.]))
        self.locals['dest2'] = pos3d(2., -2., 0.)  # Pos(np.array([-2., 1., 0.]))
        self.locals['dest3'] = pos3d(-2., -2., 0.)  # Pos(np.array([2., -1., 0.]))
        self.locals['dest4'] = pos3d(-2., 2., 0.)  # Pos(np.array([2., -1., 0.]))
        self.locals['obstacles'] = []
        self.locals['tries'] = 1

    def loop_body(self):
        if self.locals['tries'] == 1:
            print("going to point 1")
            if self.moat.position is None:
                print("not positioned yet")
                return

            path = self.moat.planner.find_path(self.moat.position, self.locals['dest2'],
                                                         self.locals['obstacles'])
            if path is None:
                print("no path for 1 to 2 ")
                self.locals['tries'] = 2
                return
            print("first path from 2,1,0 to -2, 1 , 0", path)
            self.moat.follow_path(path)
            time.sleep(2)
            self.locals['tries'] = 2
            return
        if self.locals['tries'] == 2 and self.moat.reached:
            self.locals['tries'] = 3
            return
        if self.locals['tries'] == 3 and self.moat.reached:
            print("going to point 2")

            path = self.moat.planner.find_path(self.moat.position, self.locals['dest4'],
                                                         self.locals['obstacles'])
            print("first path from 2,1,0 to -2, 1 , 0", path)
            if path is None:
                print("no path for 3 to 4 ")
                self.locals['tries'] = 4
                return
            self.moat.follow_path(path)
            time.sleep(3)
            self.locals['tries'] = 4
            return
        if self.locals['tries'] == 4 and self.moat.reached:
            print("going to point 3")
            path = self.moat.planner.find_path(self.moat.position, self.locals['dest1'],
                                                         self.locals['obstacles'])
            if path is None:
                print("no path for 1 to 2 ")
                self.locals['tries'] = 5
                return
            print("first path from -2,1,0 to 2, -1 , 0", path)
            self.moat.follow_path(path)
            time.sleep(3)
            self.locals['tries'] = 5
            return
        if self.locals['tries'] == 5 and self.moat.reached:
            print("going to point 4")

            path = self.moat.planner.find_path(self.moat.position, self.locals['dest3'],
                                                         self.locals['obstacles'])
            if path is None:
                print("no path for 1 to 3 ")
                self.locals['tries'] = 6
                return
            print("first path from 2,1,0 to -2, 1 , 0", path)
            self.moat.follow_path(path)
            time.sleep(2)
            self.locals['tries'] = 6
            return
        if self.locals['tries'] == 6 and self.moat.reached:
            print("going to point 5")

            path = self.moat.planner.find_path(self.moat.position, self.locals['dest4'],
                                                         self.locals['obstacles'])
            print("first path from 2,1,0 to -2, 1 , 0", path)
            if path is None:
                print("no path for 3 to 4 ")
                self.locals['tries'] = 8
                return
            self.moat.follow_path(path)
            time.sleep(3)
            self.locals['tries'] = 8
            return
        if self.locals['tries'] == 8 and self.moat.reached:
            print("going to point 6")
            path = self.moat.planner.find_path(self.moat.position, self.locals['dest1'],
                                                         self.locals['obstacles'])
            if path is None:
                print("no path for 1 to 2 ")
                self.locals['tries'] = 9
                return
            print("first path from -2,1,0 to 2, -1 , 0", path)
            self.moat.follow_path(path)
            time.sleep(3)
            self.locals['tries'] = 9
            return
        if self.locals['tries'] == 9 and self.moat.reached:
            print("going to point 7")

            path = self.moat.planner.find_path(self.moat.position, self.locals['dest4'],
                                                         self.locals['obstacles'])
            print("first path from 2,1,0 to -2, 1 , 0", path)
            if path is None:
                print("no path for 6 to 7 ")
                self.locals['tries'] = 10
                return
            self.moat.follow_path(path)
            time.sleep(3)
            self.locals['tries'] = 10
            return
        if self.locals['tries'] == 10 and self.moat.reached:
            path = self.moat.planner.find_path(self.moat.position, self.locals['dest1'],
                                                         self.locals['obstacles'])
            if path is None:
                self.locals['tries'] = 11
                return
            self.moat.follow_path(path)
            time.sleep(3)
            self.locals['tries'] = 11
            return
        if self.locals['tries'] == 11 and self.moat.reached:
            print("going to point 9")

            path = self.moat.planner.find_path(self.moat.position, self.locals['dest3'],
                                                         self.locals['obstacles'])
            if path is None:
                print("no path for 1 to 3 ")
                self.locals['tries'] = 12
                return
            print("first path from 2,1,0 to -2, 1 , 0", path)
            self.moat.follow_path(path)
            time.sleep(2)
            self.locals['tries'] = 12
            return
        if self.locals['tries'] == 12 and self.moat.reached:
            print("going to point 10")

            path = self.moat.planner.find_path(self.moat.position, self.locals['dest1'],
                                                         self.locals['obstacles'])
            if path is None:
                print("no path for 1 to 3 ")
                self.locals['tries'] = 13
                return
            print("first path from 2,1,0 to -2, 1 , 0", path)
            self.moat.follow_path(path)
            time.sleep(2)
            self.locals['tries'] = 13
            return

        if self.locals['tries'] == 13 and self.moat.reached:
            print("going to point 11")

            path = self.moat.planner.find_path(self.moat.position, self.locals['dest4'],
                                                         self.locals['obstacles'])
            print("first path from 2,1,0 to -2, 1 , 0", path)
            if path is None:
                print("no path for 3 to 4 ")
                self.locals['tries'] = 13
                return
            self.moat.follow_path(path)
            time.sleep(3)
            self.locals['tries'] = 13
            return
