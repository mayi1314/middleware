# middleware

## Todo List
***
- [ ] Language: 
- - [ ] : Translate robot collaboration description language to python.
- [ ] Middleware: 
- - [ ] : Connect the exception handling module to the middleware.
- [x] ok

Requirements
============

+ Java Development Kit 12 (JDK 12)
+ Maven
+ Python 3.5 or above for testing

Installation
=====

1. Clone this Experiments repository

2. Clone and install middleware under any directory.
   ```shell
   pip3 install --user -e middleware/
   ```
3. Compile JAR package from source code

The parser is written in Java and uses Antlr.
This project uses Maven.

Run following command to build and test the JAR package file:

    $ mvn package

The created JAR file should be under ``target`` folder following the name
pattern ``koord-*-jar-with-dependencies.jar``.
With the JAR file, please follow the instructions in the previous section to run
 compiler.


Given a Koord program ``app.krd``, run the following command to generate Python code
``app.py``::

    $ java -jar /path/to/koord-*-jar-with-dependencies.jar app.krd app.py

Run experiment scripts for applications
=============================================
1. move translated python codes to ``Experiments/experiments_koord/``
2. run start scripts:
```shell
ros2 launch ariac_gazebo ariac.launch.py trial_name:=tutorial competitor_pkg:=ariac_test
ros2 launch ariac_test robot_commander.launch.py
./experiment_skip_koord.sh /home/guest/Documents/cym/CyPhyHouseExperiments/experiments_koord/krd_py config.yaml```