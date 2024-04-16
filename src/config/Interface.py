interface_dict = dict()

device = {'floor_robot': {
    'detected': 'Arm.reach_pos',
    'pickup': 'Gripper.enable_suction',
    'transfer': 'Arm.reach_pos',
    'drop': 'Gripper.enable_suction',
    'reset': 'all_loaded'
}}

''''AGV.to_station': move_to
'AGV.reached':

'ConveyorBelt.control_power': set_power
'ConveyorBelt.enabled':
'Proximity.object_detected': part_poses != []
'Proximity.part_loc': part_poses

'Arm.reach_pos': move_to
'Arm.reached'

'Gripper.enable_suction': attach, detach
'Gripper.enabled':
'Gripper.attached':'''

msg_type_dict = dict()

bot_type_dict = dict()

mutex_handler_dict = dict()
