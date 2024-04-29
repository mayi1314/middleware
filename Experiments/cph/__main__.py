"""Main entry point for deployment or simulation"""

from cph.konfig import Konfig


def deploy_main(app_py: str, conf: Konfig) -> None:
    from cph import deploy_irl

    device_map = deploy_irl.get_device_map()
    print("Discovered devices:\n" + '\n'.join([str(d) for d in device_map]))  # TODO Logging

    # TODO Use device list to generate/update global config file

    for local_cfg in conf.gen_all_local_configs():
        device = local_cfg['device']
        if device['bot_name'] not in device_map:
            print("[WARNING] Device \"" + device['bot_name'] + "\" is not available. " +
                  "Agents on this device are not deployed.")
            continue

        # FIXME Use library to handle filesystem paths
        start_bash_path = "start_" + device['bot_name'] + ".bash"

        deploy_irl.dump_start_script(app_py, local_cfg, start_bash_path)
        deploy_irl.upload_and_exec(
            device_addr=device['ip'],
            username=device['username'],
            password=device['password'],
            local_path=start_bash_path,
            remote_path=start_bash_path)

    # TODO Wait until shutdown?
    pass


def simulate_main(app_py: str, conf: Konfig) -> None:
    from socketserver import DatagramRequestHandler, ThreadingUDPServer
    from threading import Thread

    class SimUDPBCastHandler(DatagramRequestHandler):
        def handle(self):
            data = self.request[0].strip()
            socket = self.request[1]
            for addr in conf.gen_all_agent_addrs():
                socket.sendto(data, addr)

    server = ThreadingUDPServer(conf.udp_bcast_addr, SimUDPBCastHandler)
    server_thread = Thread(target=server.serve_forever)
    try:
        server_thread.daemon = True
        server_thread.start()  # Start server before all other agent processes

        simulate_agents(app_py, conf)
    finally:
        server.shutdown()
        print("Shuting down simulated broadcast server thread...")
        server_thread.join(timeout=5.0)
        if server_thread.is_alive():
            print("Simulated broadcast server is still alive. Terminate with main thread.")

        server.server_close()


def simulate_agents(app_py, conf: Konfig) -> None:
    import multiprocessing as mp
    from src.scripts.run import main as run_app

    mp.set_start_method('spawn')

    agent_proc_list = []

    for local_cfg in conf.gen_all_local_configs():
        local_cfg['agent']['plist'] = None  # FIXME plist is going to be removed
        device = local_cfg['device']
        # Generate a process object for each Agent
        device['ros_node_prefix'] = \
            device['bot_name'] + '/waypoint_node'  # FIXME Handle topic names uniformly
        proc = mp.Process(
            name=device['bot_name'],
            target=run_app,
            args=(app_py[device['bot_name']], local_cfg)
        )

        agent_proc_list.append(proc)

    for agent in agent_proc_list:
        agent.start()  # Start each agent
    try:
        for agent in agent_proc_list:
            agent.join()  # Wait for each agent to finish
    except KeyboardInterrupt:
        print("User sent SIGINT. Sending SIGINT to all agent processes...")
        for agent in agent_proc_list:
            agent.join(2.0)  # Wait for each agent for 2 seconds
    finally:
        for agent in agent_proc_list:
            if agent.is_alive():
                print(agent.name, "is still alive. Escalate to SIGTERM")
                agent.terminate()


def main(argv) -> None:
    # TODO Parse arguments better
    app_krd = argv[1]
    global_cfg_filename = argv[2]
    
    import os
    # TODO Call Koord Compiler
    app_py = {}
    for file in os.listdir(app_krd):
    	app_py[file.split('.')[0]] = os.path.join(app_krd, file)
    # files = [os.path.join(app_krd, file) for file in os.listdir(app_krd)]


    with open(global_cfg_filename, 'r') as f:
        conf = Konfig(f)

    # TODO Run simulation or deployment scripts
    if False:
        deploy_main(app_py, conf)
    else:  # Do simulation
        simulate_main(app_py, conf)


if __name__ == "__main__":
    import sys
    main(sys.argv)
