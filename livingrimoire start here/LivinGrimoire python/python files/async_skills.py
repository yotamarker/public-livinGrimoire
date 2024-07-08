import requests
import threading

from AXPython import AXFunnel
from LivinGrimoire23 import DiSkillV2


class ShorniSplash(DiSkillV2):
    def __init__(self):
        super().__init__()
        self._result: str = ""

    def trigger(self, ear, skin, eye) -> bool:
        if ear == "shorni":
            return True
        # override me
        return False

    @staticmethod
    def _async_func(this_cls):
        this_cls._result = "result"

    def input(self, ear: str, skin: str, eye: str):
        if self.trigger(ear, skin, eye):
            my_thread = threading.Thread(target=self._async_func, args=(self,))
            my_thread.daemon = True
            my_thread.start()
            print("thread summoned")

        if len(self._result) > 0:
            print("thread result:")
            self.output_result()
            self._result = ""

    def output_result(self):
        self.setSimpleAlg(self._result)


class DaRainAlerts(ShorniSplash):
    def __init__(self, city: str):
        super().__init__()
        self.city: str = city
        self.apikey: str = ""  # your https://openweathermap.org/api api key. place it in a
        # weather_apikey.txt in the python project's source dir
        with open('weather_apikey.txt', 'r') as f:
            self.apikey = f.read()
        self._funnel: AXFunnel = AXFunnel()
        self._funnel.setDefault("temp")
        self._funnel.addK("so hot today").addK("so cold today").addK("what is the temperature?").addK("temperature")
        self.cmd: str = "nothing"

    def trigger(self, ear, skin, eye) -> bool:
        if len(ear) == 0:
            return False
        self.cmd = self._funnel.funnel(ear)
        return len(self.cmd) > 0

    @staticmethod
    def _async_func(this_cls):
        match this_cls.cmd:
            case "rain alerts":
                this_cls._result = this_cls.getRainAlerts(this_cls.apikey)
            case "get weather":
                this_cls._result = this_cls.get_weather(this_cls.apikey)
            case "temp":
                this_cls._result = this_cls.get_temperature_in_celsius(this_cls.apikey)

    def getRainAlerts(self, api_key) -> str:
        base_url = "https://api.openweathermap.org/data/2.5/weather"
        params = {
            "q": self.city,
            "appid": api_key,
            "units": "metric",  # You can change to "imperial" for Fahrenheit
        }

        response = requests.get(base_url, params=params)
        data = response.json()

        if "weather" in data:
            weather_description = data["weather"][0]["description"]
            if "rain" in weather_description.lower():
                return f"It's going to rain in {self.city}! ☔"
            else:
                return f"No rain expected in {self.city}. Enjoy the weather! ☀️"
        else:
            return "Unable to fetch weather data."

    def get_weather(self, api_key):
        base_url = "http://api.openweathermap.org/data/2.5/weather?"

        complete_url = base_url + f"appid={api_key}&q={self.city}"
        response = requests.get(complete_url)
        weather_data = response.json()

        if weather_data["cod"] != "404":
            main_info = weather_data["main"]
            current_temperature_kelvin = main_info["temp"]
            current_temperature_celsius = current_temperature_kelvin - 273.15
            current_pressure = main_info["pressure"]
            current_humidity = main_info["humidity"]

            weather_description = weather_data["weather"][0]["description"]

            result_string = f"Temperature: {current_temperature_celsius:.2f}°C\n"
            result_string += f"Atmospheric pressure (in hPa): {current_pressure}\n"
            result_string += f"Humidity (in percentage): {current_humidity}\n"
            result_string += f"Description: {weather_description}"
            return result_string
        else:
            return "City Not Found"

    def get_temperature_in_celsius(self, api_key):
        base_url = "http://api.openweathermap.org/data/2.5/weather?"

        complete_url = base_url + f"appid={api_key}&q={self.city}"
        response = requests.get(complete_url)
        weather_data = response.json()

        if weather_data["cod"] != "404":
            main_info = weather_data["main"]
            current_temperature_kelvin = main_info["temp"]
            current_temperature_celsius = current_temperature_kelvin - 273.15
            return f"{current_temperature_celsius:.2f}°C"
        else:
            return "City Not Found"
