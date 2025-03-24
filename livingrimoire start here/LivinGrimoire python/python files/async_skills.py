import feedparser
import requests
import threading
from subprocess import call  # for calc skill DiCalculator

from AXPython import AXFunnel
from LivinGrimoire23 import Skill


class ShorniSplash(Skill):
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

        if len(self._result) > 0:
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
        self._funnel.addKV("temp", "temp")
        self._funnel.addKV("rain alerts", "rain alerts")
        self._funnel.addKV("get weather", "get weather")
        self.cmd: str = "nothing"

    def trigger(self, ear, skin, eye) -> bool:
        if len(ear) == 0:
            return False
        self.cmd = self._funnel.funnel_or_empty(ear)
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

    def skillNotes(self, param: str) -> str:
        if param == "notes":
            return "weather information"
        elif param == "triggers":
            return "temp for temperature. rain alerts and get weather"
        return "note unavailable"


class DaRSSFeed(ShorniSplash):
    def __init__(self, rss_URL: str):
        super().__init__()
        self.rss_feed: str = rss_URL
        self._list_result: list[str] = []

    def trigger(self, ear, skin, eye) -> bool:
        return ear == "rss feed"

    @staticmethod
    def _async_func(this_cls):
        temp: list[str] = DaRSSFeed.get_rss_titles(this_cls.rss_feed)
        temp2: list[str] = []
        for item in temp:
            temp2.append(item)
            temp2.append('')
            if len(item) > 20:
                temp2.append('')
        this_cls._list_result = temp2

    @staticmethod
    def get_rss_titles(rss_url) -> list[str]:
        feed = feedparser.parse(rss_url)
        return [entry.title for entry in feed.entries]  # [:15]

    def input(self, ear: str, skin: str, eye: str):
        if self.trigger(ear, skin, eye):
            my_thread = threading.Thread(target=self._async_func, args=(self,))
            my_thread.daemon = True
            my_thread.start()

        if len(self._list_result) > 0:
            self.setVebatimAlgFromList(4, self._list_result)
            self._list_result = []


class DaExePath(Skill):
    def __init__(self):
        super().__init__()
        self._funnel: AXFunnel = AXFunnel()
        self._funnel.addKV("open calculator", "calculator")
        self._funnel.addKV("calculator", "calculator")
        self._funnel.addKV("open notepad", "notepad")
        self._funnel.addKV("notepad", "notepad")

    def input(self, ear: str, skin: str, eye: str):
        match self._funnel.funnel(ear):
            case "calculator":
                self.setSimpleAlg("calculator engaged")
                self.thread_caller(self._funnel.funnel(ear))
            case "notepad":
                self.setSimpleAlg("notepad engaged")
                self.thread_caller(self._funnel.funnel(ear))

    @staticmethod
    def _async_func(command: str):
        match command:
            case "calculator":
                call(["calc.exe"])
            case "notepad":
                call(["notepad.exe"])

    def thread_caller(self, command: str):
        my_thread = threading.Thread(target=self._async_func, args=(command,))
        my_thread.daemon = True
        my_thread.start()

    def skillNotes(self, param: str) -> str:
        if param == "notes":
            return "engages external programs"
        elif param == "triggers":
            return "say 'calculator' or 'notepad'."
        return "note unavailable"

class DaDeepseekRun(ShorniSplash):
    def __init__(self):
        super().__init__()
        self.input_text = ""  # Temporary storage for input text

    def trigger(self, ear: str, skin: str, eye: str) -> bool:
        # Check if the ear string ends with the word "run"
        return ear.strip().endswith("run")

    @staticmethod
    def _async_func(this_cls):
        # Use the stored input text
        input_text = this_cls.input_text

        # Call the Deepseek API (replace with actual API endpoint and logic)
        try:
            response = this_cls.call_deepseek_api(input_text)
            this_cls._result = response
        except Exception as e:
            this_cls._result = f"Error calling Deepseek API: {str(e)}"

    def input(self, ear: str, skin: str, eye: str):
        # Check if the skill should trigger
        if self.trigger(ear, skin, eye):
            # Remove the last word "run" from the ear string
            self.input_text = ear.rsplit(" ", 1)[0].strip()

            # Start the async operation in a daemon thread
            my_thread = threading.Thread(
                target=self._async_func,
                args=(self,)  # Pass self as the only argument
            )
            my_thread.daemon = True
            my_thread.start()

        # Output the result if available
        if len(self._result) > 0:
            self.output_result()
            self._result = ""

    @staticmethod
    def call_deepseek_api(input_text: str) -> str:
        # Replace this with the actual Deepseek API call logic
        # Example:
        # api_url = "https://api.deepseek.com/chat"
        # payload = {"input": input_text}
        # headers = {"Authorization": "Bearer YOUR_API_KEY"}
        # response = requests.post(api_url, json=payload, headers=headers)
        # return response.json().get("response", "No response from API")

        # For now, just return a mock response
        return f"Deepseek response to: {input_text}"
