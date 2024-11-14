from AXPython import RegexUtil
from LivinGrimoire23 import Skill, Brain
import serial
import time
import atexit
import serial.tools.list_ports


# terminal: pip install pyserial

def is_port_available(param):
    ports = serial.tools.list_ports.comports()
    for port in ports:
        if port.device == param:
            return True
    return False


name_of_port = 'COM3'
if is_port_available(name_of_port):
    ser = serial.Serial(name_of_port, 9600, timeout=0.1)
    print("Arduino connected successfully.")
else:
    ser = None
    print("Arduino not connected. Please check the connection.")


def close():
    if ser:
        ser.close()


class SerialReader:

    @staticmethod
    def read_serial_data(num_readings=10) -> str:
        try:
            for _ in range(num_readings):
                if ser.in_waiting > 0:
                    line = ser.readline().decode('utf-8').rstrip()
                    line = RegexUtil.extractRegex("[-+]?[0-9]{1,3}", line)
                    if len(line) > 0 and line != "0":
                        return f'{int(line) - 10}'
                time.sleep(1)  # Delay between readings
        except serial.SerialException as e:
            return f"Error reading serial data: {e}"
        return "i do not know"


class DiArduinoTemperature(Skill):
    # example skill for reading Arduino data
    def __init__(self):
        super().__init__()

    # Override
    def input(self, ear: str, skin: str, eye: str):
        if ear == "check temperature":
            self.setVerbatimAlg(4, SerialReader.read_serial_data())


class DiBlinker(Skill):
    # blinks the Arduino led, this is an example of sending commands to the Arduino
    def __init__(self):
        super().__init__()

    # Override
    def input(self, ear: str, skin: str, eye: str):
        if ear == "blink":
            self.setVerbatimAlg(4, "blinking")
            ser.write(b'1')


def add_DLC_skills(brain: Brain):
    if ser is None:
        return
    atexit.register(close)  # wrap up serial object when program closes
    brain.add_logical_skill(DiArduinoTemperature())
    brain.add_logical_skill(DiBlinker())
