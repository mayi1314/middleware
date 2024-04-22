import re


def extract_content(pddl_action):
    """
    使用正则表达式匹配预条件和效果的部分
    """
    # 找到precondition的内容
    pre_match = re.search(r':precondition(.*?):', pddl_action, re.DOTALL)
    if pre_match:
        precondition = pre_match.group(1)
        # 从precondition中提取出谓词和参数
        pre_items = parse_content(precondition)
    else:
        print("not found precondition")
    effect_str = pddl_action.split(":effect")[1]
    # 从effect中提取出谓词和参数
    effect_items = parse_content(effect_str)
    return pre_items, effect_items


def parse_content(content):
    # 使用正则表达式匹配每一项的内容
    result = []
    item_pattern = r'\((.*?) (.*?)\)'
    item_pattern2 = r'\((.*?) (.*)'
    items = re.findall(item_pattern, content)
    for predicate, parameters in items:
        if predicate == 'not':
            type = 'not'
            for tmp_predicate, tmp_parameters in re.findall(item_pattern2, parameters):
                # print(f"Type: {type}, Predicate: {tmp_predicate}, Parameters: {tmp_parameters}")
                result.append((type, tmp_predicate, tmp_parameters.split()))
        else:
            type = ''
            # print(f"Type: {type}, Predicate: {predicate}, Parameters: {parameters}")
            result.append((type, predicate, parameters.split()))
    return result


def analyze_action(action, pre_items, effect_items):
    """
    解析pddl的precondition和effect
    """
    sensor_dict = {
        'attached': 'Gripper.attached',
    }
    actuator_dict = {
        'at': {'robot': 'Arm.reach', 'agv': 'AGV.move'},
        'attached': {'robot': 'Gripper.enable_suction'},
        'assembled': {'robot': 'Arm.assembled'},
    }

    koord = {action: {'pre': [], 'eff': []}}
    for item in pre_items:
        if item[1] in ['attached', ]:  # 传感器相关的谓词
            # koord += f'{item[0]} {sensor_dict[item[1]]}'
            koord[action]['pre'].append(f'{item[0]} {sensor_dict[item[1]]}')

    for item in effect_items:
        if item[1] in ['attached', 'assembled', 'at']:  # 需要关注的谓词
            robot_name = item[-1][0].strip('?')
            if item[1] == 'at':
                if item[0] == 'not' or robot_name not in ['robot', 'agv']:  # 去掉not，非机器人:
                    continue
            robot_name = item[-1][0].strip('?')
            actuator = actuator_dict[item[1]][robot_name]
            koord[action]['eff'].append(f'{actuator} = {item[-1][-1]}')
    return koord


def extract_plan(plan):
    """
    解析pddl的plan, 提取出动作和参数
    """
    plan = plan.strip()
    result = []
    for action in plan.splitlines():
        action = action.split('(', 1)[-1]
        action_name, robot, _ = action.split(' ', 2)
        result.append((action_name, robot))
    return result


# 拼接koord代码
def generate_koord_code(action_name, koord_dict, additional_code):
    """
    生成koord代码
    """
    # pre_items, effect_items = koord_dict[action_name]['pre'], koord_dict[action_name]['eff']
    pre_items = koord_dict[action_name]['pre'] + additional_code['pre']
    effect_items = koord_dict[action_name]['eff'] + additional_code['eff']
    delimiter = "   "
    koord = action_name + ":\n" + delimiter + "pre: "
    # precondition
    koord += " && ".join(pre_items)

    # effect
    koord += "\n" + delimiter + "eff: \n"

    for item in effect_items:
        koord += 2 * delimiter + f'{item}\n'
    return koord


def main(domain_file: str):
    pddl_action = """
    (:action move
        :parameters ( ?robot - gripping_robot ?part - part ?from - location ?to - location
        )
        :precondition (and
            (can_work_at ?robot ?to)
            (at ?robot ?from)
            (not (attached ?robot ?part))
        )
        :effect (and
            (at ?robot ?to)
            (not (at ?robot ?from))
        )
    )

    """

    #     plan = """
    # (MOVE FLOOR_ROBOT PART_B ROBOT_HOME_LOC PART_BIN_LOC)
    # (PICK FLOOR_ROBOT PART_A PART_BIN PART_BIN_LOC)
    # (AGV_MOVE AGV AGV_FRONT_LOC AGV_HOME_LOC)
    # (TRANSFER FLOOR_ROBOT PART_A PART_BIN_LOC AGV_HOME_LOC)
    # (DROP FLOOR_ROBOT PART_A AGV_HOME_LOC AGV)
    # (AGV_MOVE AGV AGV_HOME_LOC AGV_FRONT_LOC)
    # (MOVE CEILING_ROBOT PART_B ROBOT_HOME_LOC AGV_FRONT_LOC)
    # (PICK CEILING_ROBOT PART_A AGV AGV_FRONT_LOC)
    #     """
    plan = """
0.00000: (MOVE FLOOR_ROBOT PART_B ROBOT_HOME_LOC PART_BIN_LOC)
0.00100: (PICK FLOOR_ROBOT PART_B PART_BIN PART_BIN_LOC)
0.00200: (TRANSFER FLOOR_ROBOT PART_B PART_BIN_LOC AGV_HOME_LOC)
0.00300: (DROP FLOOR_ROBOT PART_B AGV_HOME_LOC AGV)
"""
    with open(domain_file, 'r') as f:  # 读取domain文件
        domain_content = f.read()
    # with open('problem.pddl', 'r') as f:  # 读取problem文件
    #     problem_content = f.read()

    # 解析domain文件中的所有action
    action_dict = {}
    pddl_actions = domain_content.split(':action')[1:]
    for pddl_action in pddl_actions:
        action_name = pddl_action.split('\n')[0].strip()
        pre_conditiion, effect = extract_content(pddl_action)
        # print(pre_conditiion, effect)

        action_dict[action_name] = analyze_action(action_name, pre_conditiion, effect)

    # 解析plan文件中的动作序列
    action_seq = extract_plan(plan)
    code = ''
    last_robot = ''
    shared = {}
    st_cnt, sd_cnt, last_state, last_shared = 0, 0, 'state0', 'shared0'
    for index, (action_name, robot) in enumerate(action_seq):
        action_name = action_name.lower()
        additional_code = {'pre': [], 'eff': []}
        if last_robot == '' or last_robot == robot:
            additional_code['pre'].append(f'state == {last_state}')
            st_cnt += 1
            last_state = f'state{st_cnt}'

        else:
            additional_code['pre'].append(f'{last_shared}')
            sd_cnt += 1
            last_shared = f'shared{sd_cnt}'

        last_robot = robot

        if index < len(action_seq) - 1:
            _, next_robot = action_seq[index + 1]
            if next_robot == robot:
                additional_code['eff'].append(f'state = {last_state}')
            else:
                shared[last_shared] = (robot, next_robot)
                additional_code['eff'].append(f'{last_shared} = true')

        else:
            additional_code['eff'].append(f'exit = true')

        code += generate_koord_code(action_name, action_dict[action_name], additional_code)

    print(code)
    print(shared)


if __name__ == '__main__':
    main('domain.pddl')
