# Fibonacci numbers module
import sys
sys.path.append("/Users/jjwu/Documents/workspaces/xdeveloper/xdeveloper-test/target/xdeveloper-test-1.0.0.jar")
from jjwu.xdeveloper.test import Controller
from jjwu.xdeveloper.test import DispatcherCallBack

controller = Controller()

class MyCallBack(DispatcherCallBack):
    
    def __init__(self):
        self.notice=""
        self.result = ""
    
    def onResult(self,result):
        self.result = result
    
    def onNotice(self,notice):
        self.notice = notice
        controller.printConsole(self.notice)

callback = MyCallBack()

def main():

    controller.dispatcher_command("nodeid",callback)
    
    return callback.result
