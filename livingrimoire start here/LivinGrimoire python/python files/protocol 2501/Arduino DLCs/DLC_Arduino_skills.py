from AXPython import RegexUtil
from LivinGrimoire23 import Skill, Brain
import serial
import time
import atexit


# terminal: pip install pyserial


class SerialReader:
    def __init__(self, port='COM3', baud_rate=9600, timeout=0.1):
        self.ser = serial.Serial(port, baud_rate, timeout=timeout)
        atexit.register(self.close)  # Register the close method to be called on exit

    def read_serial_data(self, num_readings=10) -> str:
        for _ in range(num_readings):
            if self.ser.in_waiting > 0:
                line = self.ser.readline().decode('utf-8').rstrip()
                line = RegexUtil.extractRegex("[-+]?[0-9]{1,3}", line)
                # readings.append(line)
                if len(line) > 0 and line != "0":
                    return f'{int(line) - 20}'
            time.sleep(1)  # Delay between readings
        return "i do not know"

    def close(self):
        self.ser.close()


class DiArduinoTemperature(Skill):
    def __init__(self):
        super().__init__()
        self.reader = SerialReader()

    # Override
    def input(self, ear: str, skin: str, eye: str):
        if ear == "check temperature":
            self.setVerbatimAlg(4, self.reader.read_serial_data())


def add_DLC_skills(brain: Brain):
    brain.add_logical_skill(DiArduinoTemperature())
