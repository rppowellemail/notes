Reference:

* https://github.com/SeleniumHQ/docker-selenium

selenium/standalone-chrome-debug: Selenium Standalone with Chrome installed and runs a VNC server


# Quickstart

Start docker/selenium-chrome container with debug/VNC:

```
$ docker run -d -p 4444:4444 -p 5900:5900 -v /dev/shm:/dev/shm selenium/standalone-chrome-debug
```

You can acquire the port that the VNC server is exposed to by running: (Assuming that we mapped the ports like this: 49338:5900)

```
MT-206707:~ rppowell$ docker container ls
CONTAINER ID   IMAGE                              COMMAND                  CREATED              STATUS              PORTS                                                                                  NAMES
213cf651b954   selenium/standalone-chrome-debug   "/opt/bin/entry_poin…"   About a minute ago   Up About a minute   0.0.0.0:4444->4444/tcp, :::4444->4444/tcp, 0.0.0.0:5900->5900/tcp, :::5900->5900/tcp   sweet_blackwell
MT-206707:~ rppowell$ 
```

```
MT-206707:~ rppowell$ docker port 213cf651b954
4444/tcp -> 0.0.0.0:4444
4444/tcp -> :::4444
5900/tcp -> 0.0.0.0:5900
5900/tcp -> :::5900
MT-206707:~ rppowell$ 
```
or
```
MT-206707:~ rppowell$ docker port sweet_blackwell
4444/tcp -> 0.0.0.0:4444
4444/tcp -> :::4444
5900/tcp -> 0.0.0.0:5900
5900/tcp -> :::5900
MT-206707:~ rppowell$ 
```

When you are prompted for the password it is `secret`. If you wish to change this then you should either change it in the /NodeBase/Dockerfile and build the images yourself, or you can define a Docker image that derives from the posted ones which reconfigures it

# Selenium Examples

## Selenium Python Example

```python
from datetime import datetime
from selenium import webdriver
from selenium.webdriver.common.desired_capabilities import DesiredCapabilities
from selenium.webdriver.common.keys import Keys

# driver = webdriver.Firefox()

driver = webdriver.Remote(
   command_executor='http://127.0.0.1:4444/wd/hub',
   desired_capabilities=DesiredCapabilities.CHROME)

driver.get("http://www.python.org")
assert "Python" in driver.title
driver.save_screenshot('screenshot-001-' + datetime.now().isoformat() + '.png')
elem = driver.find_element_by_name("q")
elem.clear()
elem.send_keys("pycon")
elem.send_keys(Keys.RETURN)
assert "No results found." not in driver.page_source
driver.save_screenshot('screenshot-002-' + datetime.now().isoformat() + '.png')
driver.close()
```


# Selenium Notes

Use the url for the running selenium instance:

* http://127.0.0.1:4444/wd/hub/static/resource/hub.html



