#!/usr/bin/env python3
import argparse
import yaml

from src.config.config_funcs import get_configs
from src.harness.agentThread import AgentThread


def get_opt_args():
    """
    pass a config file and an appname as command line arguments.
    :return:
    """
    parser = argparse.ArgumentParser()
    group = parser.add_argument_group()
    group.add_argument('-a', '--app', action='store')
    group.add_argument('-c', '--config', action='store')
    args = parser.parse_args()
    appfile = args.app
    configfile = args.config
    return (appfile, configfile)


def get_app_name(appfile):
    """
    get the appname from the app file.
    :param appfile:
    :return:
    """
    appcode = open(appfile).read()
    end = appcode.find("(AgentThread)")
    begin = appcode[:end].rfind("class ") + 6
    appname = appcode[begin:end].strip()
    return appname


def import_app(appfile, appname):
    """
    dynamic import of the appname from the appfile.
    :param appfile:
    :param appname:
    :return:
    """
    from importlib import machinery
    loader = machinery.SourceFileLoader(appname, appfile)
    mod = loader.load_module()
    app = getattr(mod, appname)
    return app


def run_app(appfile, cfg) -> AgentThread:
    """
    run the app specified in the appfile , with configuration from the configfile.
    :param appfile:
    :param cfg:
    :return: instantiated AgentThread
    """
    appname = get_app_name(appfile)
    app = import_app(appfile, appname)
    ac, mc = get_configs(cfg)
    return app(ac, mc)


def main(appfile, cfg) -> None:
    try:
        agent_thread = run_app(appfile, cfg)
        agent_thread.start()
        agent_thread.join()
    except KeyboardInterrupt:
        print("User sent SIGINT. Stopping agent thread")
        agent_thread.stop()
    finally:
        agent_thread.join()


if __name__ == '__main__':
    """
    script to call the run_app functions
    """
    appfile = None
    configfile = None
    try:
        appfile = get_opt_args()[0]
        configfile = get_opt_args()[1]
        # TODO Both exception handling and loading files should be integrated to ArgParser
        if appfile is None or configfile is None:
            print("invalid app and config input. Use -h for help")
        else:
            with open(configfile, 'r') as f:
                cfg = yaml.safe_load(f)
                main(appfile, cfg)
    except IndexError:
        print("invalid app and config input. Use -h for help")


