"""
(define (problem p)
    (:domain action)
    (:objects
        agv- agv
        floor_robot - floor_robot
        part_A - part
    )
    (:init
        (on part_A agv)
    )
    (goal:
        (and
            (attached floor_robot part_A)
        )
    )
)
"""

import re


def extract_content(text):
    problem_name = re.search(r'\(define \(problem (\w+)\)', text).group(1)
    domain = re.search(r'\(\:domain (\w+)\)', text).group(1)
    objects = re.search(r'\(\:objects([^)]*)\)', text, re.DOTALL).group(1).strip().split('\n')
    init = re.search(r'\(\:init([^)]*)\)', text, re.DOTALL).group(1).strip().split('\n')
    goal = re.search(r'\(goal:\n(.*?)\)\n\)', text, re.DOTALL).group(1).strip()

    return problem_name, domain, objects, init, goal


text = """
(define (problem p)
    (:domain action)
    (:objects
        agv- agv
        floor_robot - floor_robot
        part_A - part
    )
    (:init
        (on part_A agv)
    )
    (goal:
        (and
            (attached floor_robot part_A)
        )
    )
)
"""

problem_name, domain, objects, init, goal = extract_content(text)
print(f"Problem Name: {problem_name}")
print(f"Domain: {domain}")
print(f"Objects: {objects}")
print(f"Init: {init}")
print(f"Goal: {goal}")
