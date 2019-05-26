Docker Selenium Notes 2019-05-18-001

Reference:
* https://github.com/SeleniumHQ/docker-selenium


selenium/standalone-chrome-debug: Selenium Standalone with Chrome installed and runs a VNC server


TigerVNC

https://tigervnc.org/
https://bintray.com/tigervnc/stable/tigervnc/1.9.0


selenium/base: Base image which includes Java runtime and Selenium Server JAR file
selenium/hub: Image for running a Grid Hub
selenium/node-base: Base image for Grid Nodes which includes a virtual desktop environment
selenium/node-chrome: Grid Node with Chrome installed, needs to be connected to a Grid Hub
selenium/node-firefox: Grid Node with Firefox installed, needs to be connected to a Grid Hub
selenium/node-chrome-debug: Grid Node with Chrome installed and runs a VNC server, needs to be connected to a Grid Hub
selenium/node-firefox-debug: Grid Node with Firefox installed and runs a VNC server, needs to be connected to a Grid Hub
selenium/standalone-chrome: Selenium Standalone with Chrome installed
selenium/standalone-firefox: Selenium Standalone with Firefox installed
selenium/standalone-chrome-debug: Selenium Standalone with Chrome installed and runs a VNC server
selenium/standalone-firefox-debug: Selenium Standalone with Firefox installed and runs a VNC server

$ docker run -d -p 4444:4444 -v /dev/shm:/dev/shm selenium/standalone-chrome:3.141.59-neon


$ docker run -d -p 4444:4444 -v /dev/shm:/dev/shm selenium/standalone-chrome:3.141.59-neon
$ docker run -d -p 4444:4444 -v /dev/shm:/dev/shm selenium/standalone-chrome-debug:3.141.59-neon

$ docker run -d -p 4444:4444 -p <port4VNC>:5900 -v /dev/shm:/dev/shm selenium/standalone-chrome-debug:3.141.59-neon

$ docker run -d -p 4444:4444 -p 5900:5900 -v /dev/shm:/dev/shm selenium/standalone-chrome-debug:3.141.59-neon

You can acquire the port that the VNC server is exposed to by running: (Assuming that we mapped the ports like this: 49338:5900)

$ docker port <container-name|container-id> 5900
$ docker port elegant_stonebraker 5900

All output is sent to stdout so it can be inspected by running:

$ docker logs -f <container-id|container-name>
$ docker logs -f elegant_stonebraker

Connect via vnc


When you are prompted for the password it is `secret`. If you wish to change this then you should either change it in the /NodeBase/Dockerfile and build the images yourself, or you can define a Docker image that derives from the posted ones which reconfigures it:
