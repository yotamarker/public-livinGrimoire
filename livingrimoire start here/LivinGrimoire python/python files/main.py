# This is a sample Python script.
from LivinGrimoireCoreV2 import *
from AXPython import *

# Press Shift+F10 to execute it or replace it with your code.
# Press Double Shift to search everywhere for classes, files, tool windows, actions, and settings.
# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    b1: Brain = Brain()
    b1.add_logical_skill(DiHelloWorld())
    b1.add_hardware_skill(DiSysOut())
    b1.doIt("hello", "", "")
# See PyCharm help at https://www.jetbrains.com/help/pycharm/
