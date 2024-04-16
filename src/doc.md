# CyHouse
## config
### config_dicts.py
这是一个 Python 模块，包含了许多字典对象。这些字典对象提供了不同的映射关系，用于解析字符串，创建相应的 Python 对象。

在这个实现中，存在几个字典对象。例如，`planner_dict` 字典将字符串与规划器类的类型进行映射；`msg_type_dict` 将字符串与 ROS 消息类型进行映射；`bot_type_dict` 将字符串与机器人类型进行映射；`mutex_handler_dict` 将字符串与互斥锁处理程序进行映射；`moat_class_dict` 将字符串与机器人对象进行映射。

总之，这些字典对象提供了一种方便的方式来解析字符串，并创建相应的 Python 对象。在分布式系统中，这些映射关系可以用来动态地加载不同的组件，以满足特定的需求。

### config_funcs.py
这是一个 Python 模块，包含了一些函数和字典对象，用于解析配置文件并创建相应的配置对象。

在这个实现中，主要的函数是 `get_configs`。该函数接受一个包含配置信息的字典，并返回一个元组，其中第一个元素是代理配置（`AgentConfig`）对象，第二个元素是运动自动机配置（`MoatConfig`）对象。

在实现过程中，首先从配置字典中提取出代理配置和设备配置的字典对象。然后，根据代理配置中是否指定了运动自动机类别来决定是否创建运动自动机配置对象。如果没有指定，则返回值中的第二项为 None。如果有指定，则使用设备配置中的信息来创建运动自动机配置对象。

最后，从提取的代理配置和设备配置信息中创建代理配置对象和运动自动机配置对象，并将它们作为元组返回。

总之，这个模块提供了一种方便的方法来解析配置文件，并创建相应的配置对象。这些配置对象可以用于配置分布式系统中的不同组件。

### configs.py
这是一个 Python 模块，定义了一些配置对象类。其中包括`AgentConfig`和`MoatConfig`两个主要的配置对象类。

`AgentConfig`类保存代理节点（agent）运行时所需的参数。它包括代理节点的进程 ID、代理数量、IP地址和端口号、互斥锁处理程序、代理节点是否为领导节点、运动自动机对象等信息。

`BotType`是一个枚举类，表示机器人类型，目前支持 QUAD 和 CAR 两种类型。

`Topic`类表示 ROS 中的一个话题（topic），包括话题名称和消息类型。

`MoatConfig`类保存运动自动机（moat）在运行时所需的参数。它包括运动自动机绑定的机器人的名称、机器人的类型、ROS节点名称、位置消息的话题名称、到达消息的话题名称、位置消息的类型、到达消息的类型、规划器对象以及队列大小等信息。

`gen_positioning_params()`、`gen_reached_params()`和`gen_waypoint_params()`函数分别返回位置、到达和路径点话题的名称和类型。

`default_car_moat_config()`和`default_qc_moat_config()`函数是辅助函数，用于生成默认的模拟汽车和四旋翼飞行器的运动自动机配置。

总之，这个模块定义了各种配置对象的类和辅助函数，提供了一种方便的方式来管理和配置不同的组件。这些配置对象可以在分布式系统中使用，以方便地进行配置和管理。

***
***
## functionality
### base_mutex_handler.py
这段 Python 代码实现了 `BaseMutexHandler` 类，继承自 `MutexHandler` 类。它包含一个构造函数 `__init__(self, leader: bool, pid: int)` 和一些方法：

- `add_mutex(self, mutex)`：添加一个互斥量（mutex）到互斥量列表中；
- `add_request(self, index: int, pid: int, req_num: int)`：向互斥量请求列表中添加新的请求，并标记该请求已被确认；
- `find_mutex_index(self, mutex_id: int) -> Union[int, None]`：查找互斥量在互斥量列表中的索引；
- `grant_available_mutexes(self, mutex_nums: list)`：将可用的互斥量授予进程；
- `has_mutex(self, mutex_id)`：判断互斥量是否被当前进程占有；
- `run(self)`：重写父类 `run()` 方法，循环运行并调用 `grant_available_mutexes()` 方法。

其中，类变量 `__leader` 表示当前进程是否为领导者，而 `__pid` 表示当前进程的 ID。另外还有三个私有变量 `__mutex_nums`、`__mutexes` 和 `__grant_acked` 分别表示互斥量数目、互斥量列表和确认的互斥量。这些变量可以通过相应的属性访问器进行访问。

注释行 `# test comment` 是一个单行注释，不会影响程序执行。最后，`time.sleep(0.1)` 可以使程序每隔 0.1 秒循环一次。

***
### base_synchronizer.py
这段代码定义了一个名为 `BasicSynchronizer` 的类，它提供了一种基本的同步方法，用于在分布式系统中对代理进行同步。同步方法的原理是让代理等待所有其他代理的消息穿过屏障，然后再继续下一轮执行。

该类有几个属性，包括 `__participants`、`__sync_list`、`__round_num`、`__ip_port_list` 和 `__retries`。这些属性用于跟踪参与者数量、已经同步的代理列表、当前轮数、IP 地址和端口列表以及同步重试次数。

`send_sync_message()` 方法向系统中所有其他代理发送同步消息。`synchronize_wait()` 方法等待所有其他代理的同步消息到达，并相应地更新 `__sync_list` 和 `__round_num` 属性。`add_sync()` 和 `reset_synclist()` 方法分别将代理添加到同步列表并重置同步列表。

最后，该代码还定义了一个自定义异常 `RoundSyncError`，当同步出现异常时会引发此异常。

***
### comm_funcs.py
这段代码定义了一个 `send()` 函数，用于将消息发送到指定的 IP 地址和端口。该函数接受一个 `Message` 类型的参数 `msg`，将其序列化后使用 UDP 协议将其发送给指定的 IP 和端口。

在函数中，首先创建了一个套接字 `client_sock`，使用 `socket.AF_INET` 和 `socket.SOCK_DGRAM` 分别表示该套接字使用 IPv4 地址和 UDP 协议。然后使用 `setsockopt` 方法设置套接字选项，将 `SO_BROADCAST` 设置为 1，表示启用广播模式。接下来，通过调用套接字对象的 `sendto()` 方法向指定的 IP 和端口发送序列化后的消息。

***
### mutex_handler.py
这段代码定义了一个名为 `MutexHandler` 的抽象基类，该类继承自 `Thread` 和 `ABC`。`MutexHandler` 类提供了一些方法和属性，用于处理互斥锁。

在 `__init__()` 方法中，首先调用父类的构造函数初始化线程对象，然后创建了一个 `Event` 对象 `__stop_event`，用于设置停止标志以安全地退出线程。

`stop()` 方法用于设置停止标志，以便安全退出线程；`stopped()` 方法则用于检查停止标志是否已经被设置。

`run()` 方法是一个纯虚函数，需要在子类中实现，它将包含执行线程所需的代码。由于这是一个抽象基类，因此不能直接实例化 `MutexHandler` 类的对象，而必须通过继承并实现 `run()` 方法来创建子类对象。

***
### synchronizer.py
这段代码定义了一个名为 `Synchronizer` 的抽象基类，该类继承自 `ABC`。`Synchronizer` 类提供了一些属性和抽象方法，用于实现某种基于轮次的同步算法。

在 `__init__()` 方法中，首先设置了一个布尔类型的 `__is_synced` 属性，并将其初始化为 `False`，表示当前未完成同步。

`is_synced` 和 `is_synced.setter` 方法分别是 `__is_synced` 属性的 getter 和 setter 方法，用于获取和设置当前的同步状态。

`Synchronizer` 类还定义了两个纯虚方法：`synchronize_wait()` 和 `handle_sync_message()`。任何继承自 `Synchronizer` 类的子类都必须实现这两个方法。其中，`synchronize_wait()` 方法用于执行同步操作，而 `handle_sync_message()` 方法则用于处理同步消息。

由于这是一个抽象基类，因此不能直接实例化 `Synchronizer` 类的对象，而必须通过继承并实现相应的方法来创建子类对象。

***
### value_plotter.py
None

***
***
## harness
### agentThread.py
这段代码定义了一个名为 `AgentThread` 的抽象类，该类继承自 Python 标准库中的 `Thread` 类和抽象基类 `ABC`。`AgentThread` 类提供了一些方法和属性，用于实现一个代理线程应用程序。

在 `__init__()` 方法中，首先创建了一个名为 `moat` 的私有属性，用于存储 MotionAutomaton 对象（如果在配置文件中指定了相应的类）。然后创建了一个名为 `agent_gvh` 的私有属性，该属性是 Gvh 类对象，用于协调代理之间的通信。此外，还创建了一个名为 `stop_event` 的私有属性，用于标记是否停止当前代理线程。

类中还定义了许多其他方法，例如 `create_ar_var()` 和 `create_aw_var()` 用于创建共享变量，`lock()` 和 `unlock()` 用于对锁进行操作，`read_from_sensor()` 和 `write_to_actuator()` 用于从传感器读取数据并向执行器写入数据等。这些方法中的大部分都是抽象方法，需要在子类中进行实现。

`run()` 方法是 `Thread` 类中的一个关键方法，需要在子类中进行实现。在实现时，通过发送初始化消息来协调所有代理的启动顺序，并在确认前持续发送轮更新消息。一旦确认，就会进入主循环，执行 `loop_body()` 方法中的代码。如果出现错误或通信中断，会尝试发送 `stop` 消息来停止当前代理线程。

***
### comm_handler.py
这段代码定义了一个名为 `CommHandler` 的类，该类继承自 Python 标准库中的 `Thread` 类。`CommHandler` 类提供了一些方法和属性，用于实现代理之间的通信。

在 `__init__()` 方法中，首先创建了一些私有属性，例如代理的 pid、是否是 leader、接收消息的 ip 和端口等。然后创建了一个名为 `stop_event` 的私有属性，用于标记是否停止当前线程。此外，还创建了一个名为 `recv_msg_queue` 的私有属性，该属性是 `Queue` 类对象，用于存储从其他代理接收到的消息。

类中还定义了许多其他方法和属性，例如 `timeout`、`pid`、`is_leader`、`r_port`、`ip` 等 getter 方法，以及 `stop()`、`stopped()` 等用于控制线程的方法。

在 `run()` 方法中，首先通过 `socket.socket()` 创建一个 UDP socket，并将其设置为广播模式。然后通过调用 `bind()` 方法将 socket 绑定到指定的 IP 和端口。之后不断地尝试从 socket 中接收数据，并将接收到的消息放入 `recv_msg_queue` 队列中。如果在接收过程中出现超时或 OSError 异常，则退出线程。

需要注意的是，在接收消息前会先调用 `__check_receiving_buffer()` 方法检查 socket 缓存中是否存在数据，以避免线程在缺少数据时空转。

***
### gvh.py
TODO

***
### message_handler.py
这些函数都是用来创建不同类型的消息对象的。每个函数都接受一些参数，根据这些参数创建一个特定类型的 Message 对象，然后返回该对象。这些函数的作用包括表示轮次更新、停止消息、互斥锁请求、授权等场景。这些消息将在分布式系统中进行通信和协调，以实现各种功能和保证系统的正确性。

***
***
## motion
### cylobs.py
这段代码实现了一个圆柱形障碍物类 `CylObs`，继承自 `Obstacle` 类。它有三个属性：障碍物中心的位置坐标 `point`、半径 `radius` 和高度 `height`（默认为1.0）。在初始化时，它将坐标的z值设为0，并调用父类 `Obstacle` 的 `__init__()` 方法来初始化对象。

`CylObs` 类实现了两个方法 `_isdisjoint_point()` 和 `_isdisjoint_seg()` 来检查点和线段是否与该圆柱体相离（即没有碰撞）。如果点和圆柱体相离，则返回 True；否则返回 False。同理，如果线段和圆柱体相离，则返回 True；否则返回 False。

总之，`CylObs` 类用于表示圆柱形障碍物，并提供了一些方法来检查是否与点或线段发生碰撞。

***
### deconflict.py
这段代码包含了一些函数，用于解决路径去重的问题。具体来说：

- `is_collinear()` 函数检查三个点是否共线。
- `line_to_pt_dist()` 函数计算线段到点的距离。
- `seg_distance_3d()` 函数计算两个线段在三维空间中的距离。
- `is_close()` 函数定义了两个对象（点或线段）的“接近程度”，并返回一个布尔值表示它们是否足够接近。
- `get_path_segs()` 函数将由多个点组成的路径转换为由多个线段组成的路径。
- `path_is_close()` 函数检查两个路径是否存在接近的线段。
- `clear_path()` 函数检查给定的路径集合与建议路径是否有碰撞，如果没有则返回 True，否则返回 False。

这些函数都是为了解决路径去重的问题而设计的，可以确保生成的路径不会与已经存在的路径发生碰撞。

***
### demo_planner.py
这段代码包含了一个名为 `DemoPlan` 的类，它继承自抽象基类 `Planner`，并实现了其中的 `find_path()` 方法。`DemoPlan` 类用于演示路径规划的过程，并提供了一个简单的方法来生成一个由三个点组成的路径。

在初始化时，`DemoPlan` 类接受一些参数，包括随机采样区域、扩展距离、目标采样率和最大迭代次数等，这些参数默认值已经设定好了。`find_path()` 方法接受起始点、结束点和障碍物列表作为输入，并返回由三个点组成的路径（起始点、中间点和结束点）。

注释掉的代码 `a = DemoPlan()` 和 `c = a.find_path(Pos(np.array([0, 0, 0])), Pos(np.array([1, 1, 0])))` 可以用于测试 `DemoPlan` 类，它将输出一个路径。

***
### dubins_path_planning.py
Dubins路径规划算法是一种用于在包含障碍物的平面环境中生成两个点之间最短路径的算法。这是一个Python实现，它将机器人的起始和结束位置及方向作为输入，并生成一系列动作（左转、右转或直行），使机器人能够到达目标并避开障碍物。函数的输出包括沿生成路径的x-y位置和方向，以及采取的动作序列。该代码部分修改自Atsushi Sakai原始实现。

这是一个使用 Python 实现 Dubins 路径规划算法的模块。它提供了生成两个二维空间中的点之间Dubins路径的函数，给定起点和终点、它们的方向以及曲率值。

`dubins_path_planning` 函数接受六个参数：`sx`、`sy`、`syaw`、`ex`、`ey`、`eyaw` 和 `c`。这些参数表示起点的 x 和 y 坐标 (`sx`、`sy`)、其方向(`syaw`)、终点的x和y坐标(`ex`,` ey`)及其方向(`eyaw`)，以及曲率值`c`。

该算法计算不同类型的路径（LSL，RSR，LSR，RSL，RLR，LRL），并选择长度最短的路径，即代价最小的路径。生成的路径以 x 和 y 坐标，偏航角和路径组件的列表形式返回。

例如，在机器人需要避开障碍物时，可以使用此代码将机器人从一个点移动到另一个点。

***
### moat_hector_quadrotor.py
这是针对Hector Quadrotor ROS软件包中的运动自动机的实现，用于控制四旋翼无人机。它使用了订阅器和动作客户端来与ROS通信，并提供了一些方法来执行不同的动作。例如，可以使用"goTo"方法将四旋翼飞往指定位置，使用"follow_path"方法跟随给定路径。此外，它还利用了一个线程锁来确保在多个线程之间共享的__path变量的安全性，并使用SimpleActionClient类来发送动作目标并等待其完成。该代码还具有错误处理和调试输出。

***
### moat_hector_quadrotor_simplified.py
这是一个简化版的MoatHectorQuadrotor实现，用于控制四旋翼无人机。它使用了一个消息队列来存储待执行的路径点，并使用一个发布器将它们发送到ROS系统中。在运行时，它会对当前位置和目标位置之间的距离进行检查，以确保无人机朝着正确的方向前进。如果当前位置足够接近目标位置，则认为已经到达目标，并继续处理下一个路径点。这个版本的代码还提供了takeoff()和land()方法，用于使无人机起飞和降落。它还使用了ROS服务来启用无人机的电机，并包括错误处理和调试输出。

***
### moat_test_car.py
这是一个用于控制测试汽车的MotionAutomaton实现。它使用了getPositioning()和getReached()方法来接收当前位置和到达状态信息，并在需要时更新相关变量。与之前的示例不同，这个版本的代码使用ROS的发布器（self.pub）来将目标位置发送到系统中，而不是使用一个消息队列。它还提供了goTo()和follow_path()方法来控制无人车前进到指定的位置，并包括错误处理和调试输出。最后，它使用了ROS的rospy.spin()函数来让程序保持运行状态，以等待接收到新的消息并响应它们。

***
### moat_test_drone.py
这是一个用于控制测试无人机的MotionAutomaton实现。它继承自基类MotionAutomaton，并使用getPositioning()和getReached()方法来获取当前位置和到达状态信息。与之前的实现不同，这个版本的代码包括takeoff()和land()方法，用于使无人机起飞和降落。在moat_init_action()方法中，它调用了takeoff()方法，并等待无人机起飞并到达目标高度之后才继续执行。在moat_exit_action()方法中，它调用了land()方法，并在无人机降落并到达地面之后退出程序。此外，该代码还提供了goTo()和follow_path()方法来控制无人机前往指定位置，并使用ROS发布器（self.pub）将目标位置发送到系统中。最后，它使用了ROS的rospy.spin()函数来让程序保持运行状态，以等待接收到新的消息并响应它们。

***
### moat_withlidar.py
这是一个使用激光雷达获取车辆周围障碍物信息的MotionAutomaton实现。它继承自MoatTestCar类，并通过ApproximateTimeSynchronizer来接收激光雷达和当前位置信息，然后将它们存储在队列中以便后续处理。该代码还包括了一个私有方法_quaternion_to_euler()，用于将四元数转换成欧拉角。在回调函数_get_scan_at_pos()中，它将收到的激光雷达数据转换为(x, y)坐标系下的点，并将它们与当前位置信息一起打包成一个组合数据，并放入队列中。这样，我们就可以在执行路径规划时使用这些数据来避免车辆撞上障碍物。此外，代码还提供了reset()方法，用于清除存储队列中的所有数据。

***
### motionautomaton.py
这是一个抽象类MotionAutomaton，它提供了一些基本的方法和属性以用于实现不同的运动控制算法。该类继承自Python中的线程类threading.Thread和抽象基类ABC（Abstract Base Class）。该类包含有关机器人状态和位置的信息，使用ROS发布器（self.pub）将目标位置发送到系统中，并使用ROS订阅器（self.__sub_reached）获取到达状态信息。该类还实现了reached()、position()、waypoint_count()等属性，用于存储机器人当前是否已到达目标点、位置信息以及路径上每个点的顺序编号等信息。此外，该类还定义了抽象方法getPositioning()、getReached()、goTo()和follow_path()，用于获取当前位置、接收到达状态信息、控制机器人前往指定位置以及跟踪预定义路径。在该类中，run()方法是一个抽象方法，需要由子类实现。该方法运行在单独的线程中，用于启动运动控制算法并执行操作。因为MotionAutomaton类是一个抽象类，所以不能直接实例化该类，必须创建一个新的类来继承该类并重写必要的方法，以满足特定应用场景的需求。

***
### obstacle.py
这是一个抽象类Obstacle，用于表示机器人周围可能存在的障碍物。该类具有两个私有属性：position和size，它们分别表示障碍物的位置和尺寸。该类还提供了isdisjoint()方法，用于检查机器人是否与当前障碍物发生冲突（碰撞）。如果isdisjoint()方法返回True，则表示障碍物与机器人没有相交部分，否则表示它们发生了碰撞，需要采取避免策略。此外，该类还实现了__eq__()方法，用于比较两个障碍物对象是否相等。由于Obstacle类是一个抽象类，因此不能直接实例化。需要创建一个新的类继承Obstacle，并实现必要的方法来处理不同类型的障碍物。

***
### planner.py
这是一个抽象类Planner，用于规划从起点到终点的路径。该类提供了find_path()方法，用于计算从起点到终点的路径，并使用障碍物列表来避免碰撞。此外，该类还实现了一些属性和方法，例如min_rand、max_rand、min_xrand、max_xrand等，用于设置和获取随机区域的范围。Planner类继承自Python中的抽象基类ABC（Abstract Base Class），因此不能直接实例化。必须创建一个新的类，并通过重写find_path()方法来实现特定的路径规划算法。

***
### pos_types.py
这是一个Python模块，它包含了一些用于处理2D和3D位置、路径规划和障碍物的类和函数。其中最重要的类是Pos，代表三维向量（位置）。该类具有许多用于计算向量长度、方向、加法、减法、点积、叉积等等的方法。此外，还有一个Node类，代表在优化路径规划中使用的节点。还有一个RoundObs类，表示圆形障碍物。其他类包括Seg（路径段）和pos3d（Koord中使用的三维位置）。该模块还提供了一些辅助函数，例如distance（计算两个位置之间的距离）、to_node（将Pos转换为Node）和ros_pose_to_pos3d（将ROS姿态消息转换为pos3d）。

***
### rectobs.py
这是一个Python模块，其中包含名为RectObs和cross_product的两个函数。

RectObs是Obstacle类的子类，它表示一个矩形障碍物。它在初始化时需要中心点和长度、宽度和高度（x，y和z）这三个参数。该类还实现了_isdisjoint_point和_isdisjoint_seg两个方法，用于检查点和路径线段是否与矩形障碍物相离。

cross_product函数计算两个向量（以numpy数组的形式给出）的叉积。具体地说，它计算前两个分量（x和y）的乘积之差。

***
### reeds_shepp_planner.py
这是一个Python模块，其中包含Reeds_Shepp_Planner类和find_path方法，用于执行Reeds-Shepp路径规划。

Reeds-Shepp路径规划算法是一种用于计算车辆在二维平面上移动的最短路径的算法，该算法考虑了车辆的转弯半径。这个算法实现了Planner类，并覆盖了其find_path方法。当给定起始点、目标点和可能的障碍物列表时，它可以计算出Reeds-Shepp路径。路径以Node序列的形式返回，每个Node包含x、y和yaw三个属性。

在初始化时，Reeds_Shepp_Planner需要两个参数：rho和step_size。rho是车辆的转弯半径，而step_size定义了生成路径中相邻节点之间的距离。 

该模块还提供了一个示例测试代码，展示如何使用Reeds_Shepp_Planner计算从起始点到目标点的最短路径。

***
### roundobs.py
这是一个Python模块，其中包含RoundObs类，用于表示圆形障碍物。

RoundObs类是Obstacle的子类，它在初始化时需要中心点和半径这两个参数。该类还实现了_isdisjoint_point和_isdisjoint_seg方法，用于检查点和路径线段是否与圆形障碍物相离。

在_isdisjoint_point方法中，该类计算给定点到圆心的距离，并将其与半径进行比较，以确定该点是否在圆形障碍物之外。

在_isdisjoint_seg方法中，该类首先将路径线段表示为起点、终点和方向的向量，并计算出沿着该向量的长度。然后，它使用向量代数计算出路径线段与圆形障碍物之间的距离。最后，它比较该距离与路径长度，以确定路径线段是否与圆形障碍物相交。

该模块还提供了示例测试代码，展示如何在给定路径线段和圆形障碍物的情况下，使用RoundObs类执行碰撞检测并输出结果。

***
### rrt_car.py
这是一个路径规划算法RRT*的实现，用于搜索从起点到终点的可行路径。

该类的构造函数接受一些可选参数，可以通过这些参数调整算法的行为。其中包括：

- rand_area: 表示随机采样点的范围，格式为[min_x, max_x, min_y, max_y]。
- expand_dis: 表示每个迭代步骤中新节点与最近邻节点之间的距离。
- goal_sample_rate: 表示在采样过程中从目标点进行采样的频率。
- max_iter: 表示算法执行的最大迭代次数。

该类实现了find_path方法，该方法接受起点、终点和障碍物列表作为输入，并返回从起点到终点的可行路径。

在实现中，该算法使用RRT*的技术来创建树形结构并搜索有效路径。它通过随机采样来生成新节点，并通过扩展距离参数限制新节点与树中最近邻节点之间的距离。然后它使用steer方法在两个节点之间限制距离，并检查中间路径是否与障碍物相交。如果路径没有相交，则该节点被添加到树结构中。最后，当新节点足够接近目标节点时，算法将检查直接连接新节点和目标节点的路径是否有效。

该类还实现了其他一些辅助方法，例如get_nearest_list_index和check_collision方法，用于计算最近邻节点和检查路径是否与障碍物相交。

***
### rrt_drone.py
该代码实现了使用RRT*算法进行路径规划。主要逻辑如下：

1. 定义了一个 RRT 类，继承了 Planner 类。
2. 在构造函数中初始化一些参数，例如采样范围（rand_area），最大迭代次数（max_iter）和目标点采样率（goal_sample_rate）等。
3. find_path 函数接受起点、终点和障碍物列表作为输入，并返回从起点到终点的可行路径。
4. 在 find_path 中，首先将起点加入节点列表中。然后，循环执行以下步骤，直到找到可行路径或达到最大迭代次数：
   - 从随机采样得到一个新点
   - 找到节点列表中距离新点最近的节点
   - 使用 steer 方法将新点连接到节点列表中的最近节点上
   - 检查从最近节点到新节点的路径是否与障碍物相交
   - 如果路径不相交，则将新节点添加到节点列表中
   - 如果新节点足够接近目标节点，则检查直接连接新节点和目标节点的路径是否有效，并返回可行路径
5. steer 方法将新节点连接到树结构上。它通过计算从最近节点到新节点之间的半径为 expand_dis 的球形空间，并在该空间内寻找最接近新节点的点。然后它使用 steer 技术将最接近点连接到新节点上，并返回一个新的节点。
6. get_random_point 方法用于从采样范围内随机生成一个点。如果随机数小于目标点采样率，它将返回终点；否则，它将返回在采样范围内随机生成的点。
7. check_collision 方法用于检查一个线段路径是否与障碍物列表中的任何障碍物相交。
8. get_nearest_list_index 方法返回离列表中给定点最近的节点索引。
9. gen_final_course 方法用于生成从起点到终点的路径。从最后一个节点开始，一直遍历它的父节点，直到根节点，将路径点添加到路径列表中。
10. calc_dist_to_goal 方法计算从当前点到终点的距离。
11. path_smoothing 方法对路径进行平滑处理，以去除路径上的抖动和多余的轨迹。

此代码可以在仿真环境中用于规划无人机的路径。

***
### rrt_star.py
这是一个使用RRT*路径规划算法的Python实现，由Amelia Gosse修改以在CyPhyHouse中使用。 RRT*算法是一种流行的基于采样的运动规划算法，可以处理高维状态空间和复杂环境。

类`RRT`是实现RRT*算法的主要规划器类。它的`find_path()`方法接受起始点和终止点、障碍物列表等参数，还包括最大迭代次数等参数。它返回一个位置序列，表示从起点到终点的计划路径，如果找不到有效的路径，则返回`None`。

`steer()`方法通过从最近的节点朝向随机采样的离目标一定距离的点来计算新节点（位置）。`choose_parent()`方法基于到每个潜在父节点的成本确定新节点在节点树中的父节点。`rewire()`方法执行RRT*特有的重连步骤，以改善最终路径的质量。最后，还有几个辅助方法，用于各种任务，如碰撞检查和查找靠近给定节点的节点。

***
### rrt_star_drone.py
这段代码是使用RRT*路径规划算法实现的路径规划示例代码。它实现了一个名为`RRT`的类，该类包含了RRT*算法中常见的方法和属性，如`find_path()`、`node_selection()`、`get_random_point()`、`choose_parent()`、`rewire()`等。

在这个示例中，`find_path()`方法接受起始点、终止点和障碍物列表等参数，并返回一条从起点到终点的路径。这个方法还可以接受`search_until_max_iter`参数，以决定是否搜索到最大迭代次数以后就停止寻找路径，还是在达到最大迭代次数之后继续搜索以提高路径质量。在路径生成过程中，算法会通过调用`node_selection()`方法选择最近的节点，并使用`steer()`方法扩展树，使用`choose_parent()`方法确定新节点的父节点，在树上扩展路径。最后，使用`rewire()`方法优化新路径。

***
### rrt_star_dubins.py
这是一个使用RRT*算法和Dubins路径规划实现的路径规划示例代码。它使用了一些帮助类，如`Obstacle`、`Node`和`Pos`。在这个示例中，`find_path()`方法接收起始点、终止点和障碍物列表等参数，并返回从起点到终点的路径。该方法使用RRT*算法，在向树中添加新节点时使用Dubins路径规划来保证路径连续和可行性。

在RRT*算法的实现过程中，该类包含了许多基本的方法和属性，如`get_nearest_list_index()`、`collision_check()`、`rewire()`、`find_near_nodes()`、`gen_final_course()`等。这些方法用于计算最近节点、检查碰撞、重连、搜索附近节点、生成最终路径等操作。

此外，该类还有一些特定的方法，如`choose_parent()`、`steer()`、`get_random_point()`、`get_best_last_index()`等。这些方法都是为了实现Dubins路径规划而设计的，以确保在扩展树时产生平滑连续的路径。

总之，这个示例展示了如何使用RRT*算法和Dubins路径规划来进行路径规划。

***
### simpleplanner.py
这是一个简单的路径规划器示例代码，它使用直线路径段连接起始点和终止点。该类实现了`Planner`接口，其中包括`__init__()`,`num_segs`属性、`find_path()`等方法。

在`find_path()`方法中，根据给定的参数，计算出沿着起点到终点的方向上每一段的距离，并将其分为`num_segs`个部分。然后从起点开始，按照方向和距离依次连接路径点，最后返回完整路径。

此外，该类还包含了许多其他有用的方法和属性，如`distance()`、`Seg`、`Obstacle`等，这些都是来自于帮助类库。总之，这个示例展示了如何实现一个简单的路径规划器，以及如何使用接口规范化路径规划器的实现。

***
***
## objects
### base_mutex.py
这是一个名为 `BaseMutex` 的 Python 类，它是分布式系统的互斥锁实现。它继承自另一个名为 `Mutex` 的类。

该类具有多个属性，例如 `mutex_id`、`mutex_request_list`、`mutex_holder`、`ip_port_list` 和 `agent_comm_handler`。还有几种设置器方法，可以设置这些属性。

该类还有多种方法，例如 `request_mutex`、`ack_request`、`grant_mutex` 和 `release_mutex`。这些方法用于请求、确认、授予和释放互斥锁。

该实现似乎使用消息传递在不同机器上的不同实例之间进行通信。`send` 方法用于向其他实例发送消息。

总体而言，这似乎是一种标准的使用消息传递的分布式系统互斥锁的实现。

***
### dsm.py
这是一个名为 `DSM` 的 Python 类，它代表了分布式共享内存。该类的构造函数接受多个参数，包括名称、数据类型、大小、进程 ID 和值等。

在类的实现中，存在许多属性和方法。例如，`updated` 属性表示上次更新的时间戳，`last_update` 方法用于获取最后一次更新的时间戳，`set_update` 方法用于设置更新的时间戳。此外，还有 `name`、`dtype`、`value` 和 `owner` 等属性，以及访问这些属性的 getter 和 setter 方法。

该类还包含 `get_val` 和 `set_val` 方法来获取和设置共享内存中的值。如果共享内存的所有者是 aw（写操作），则 value 是一个值，否则 value 是一个列表。

这种实现方式似乎使用了列表来表示共享内存的值，并且可以通过进程 ID 来访问和修改共享内存中的特定值。总体而言，这是一个用于分布式系统中处理共享内存的常见模式。

***
### message.py
这是一个名为 `Message` 的 Python 类，代表了分布式系统中传递的消息。该类的构造函数接受多个参数，包括发送者、消息类型、内容和时间戳等。

在这个实现中，存在许多属性和方法。例如，`sender` 属性表示消息发送者的进程 ID，`message_type` 表示消息的类型，`content` 表示消息的内容，`timestamp` 表示消息的时间戳。此外，还有访问这些属性的 getter 和 setter 方法。

该类还实现了一个字符串表示，用于调试和打印消息。总体而言，这个实现看起来是一个简单的消息对象，可以在分布式系统中使用。

***
### message.py
这是一个名为 `Mutex` 的 Python 抽象类，代表了互斥锁的抽象概念。该类包含几个抽象方法，包括 `request_mutex`、`grant_mutex` 和 `release_mutex` 等。

在这个实现中，所有方法都是抽象的，因此需要子类来实现具体的方法。这个抽象类提供了一个基本框架，可以让子类继承并实现它们自己特定的互斥锁算法。

总之，这个类提供了一个模板方法模式，允许子类实现自定义的互斥锁算法，并通过重写抽象方法来实现它们独特的行为。

***
### udt.py
这是一个名为 `Task` 的 Python 类，代表了分布式系统中的任务。该类包含几个属性，包括位置、任务 ID、是否分配和被分配给哪个 agent 等。

在这个实现中，存在一个 `assign` 方法，用于将任务分配给特定的 agent，方法接受一个整数参数表示将任务分配给哪个 agent。此外，还有一个名为 `get_tasks` 的函数，可以从文件中读取任务，并返回一个任务列表。

总之，这个类提供了一个简单的任务对象，可以在分布式系统中使用。通过 `assign` 方法，可以将任务分配给不同的 agent，以便并行处理任务。该函数 `get_tasks` 可以从文件中读取所有任务，并创建相应的 Task 对象。

***
***
## scripts
### run.py
这是一个 Python 脚本，用于运行指定的分布式系统应用程序。该脚本接受两个命令行参数，包括应用程序文件和配置文件。

在脚本中，存在几个主要函数和方法。首先，`get_opt_args` 函数解析命令行参数，并返回应用程序文件和配置文件的名称。然后，`get_app_name` 函数从应用程序文件中获取应用程序的名称，以便之后进行动态导入。接着，`import_app` 函数从应用程序文件中动态导入应用程序类。最后，`run_app` 函数使用配置文件来实例化应用程序对象，并将其作为 `AgentThread` 类的实例返回。

主函数 `main` 负责启动应用程序并监控信号，如果用户发送 SIGINT 信号，则停止应用程序。最后，在 `__main__` 块中，解析命令行参数并调用主函数 `main` 来运行指定的应用程序。

总之，这是一个简单的脚本，可以使用命令行参数来运行指定的分布式系统应用程序。