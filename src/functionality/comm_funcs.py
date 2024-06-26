import pickle
import socket

from src.objects.message import Message


def send(msg: Message, ip: str, port: int, resend = 1) -> None:
    """
    :param msg: message to be sent
    :param ip: ip to be sent to
    :param port: port to be sent to
    :return: Nothing
    """
    with socket.socket(socket.AF_INET, socket.SOCK_DGRAM) as client_sock:
        client_sock.setsockopt(socket.SOL_SOCKET, socket.SO_BROADCAST, 1)
        client_sock.sendto(pickle.dumps(msg), (ip, port))
