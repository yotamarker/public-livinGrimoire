from __future__ import annotations
from abc import ABC, abstractmethod
from enum import Enum, auto
import datetime
from datetime import timedelta
import calendar
import re
from typing import Match, Pattern, Iterable
from collections import Counter
from math import sqrt

"""CREDITS :


the living grimoire was created by Moti Barski

Translation of code from java to python : Marco Vavassori, Moti Barski"""


class DeepCopier:
    def copyList(self, original: list[str]) -> list[str]:
        deepCopy: list[str] = []
        for item in original:
            deepCopy.append(item)
        return deepCopy

    def copyListOfInts(self, original: list[int]) -> list[int]:
        deepCopy: list[int] = []
        for item in original:
            deepCopy.append(item)
        return deepCopy


class AbsDictionaryDB:
    def save(self, key: str, value: str):
        """Returns action string"""
        pass

    def load(self, key: str) -> str:
        return "null"


''' MUTATABLE CLASS '''


class Mutatable(ABC):
    # one part of an algorithm, it is a basic simple action or sub goal
    def __init__(self):
        # set True to stop the entire running active Algorithm
        self.algKillSwitch: bool = False

    @abstractmethod
    def action(self, ear: str, skin: str, eye: str) -> str:
        """Returns action string"""
        pass

    @abstractmethod
    def completed(self) -> bool:
        """Has finished ?"""
        pass

    @abstractmethod
    def clone(self) -> Mutatable:
        pass

    def myName(self) -> str:
        """Returns the class name"""
        return self.__class__.__name__


class GrimoireMemento:
    def __init__(self, absDictionaryDB: AbsDictionaryDB) -> None:
        super().__init__()
        self.absDictionaryDB = absDictionaryDB

    def simpleLoad(self, key: str) -> str:
        return self.absDictionaryDB.load(key)

    def simpleSave(self, key: str, value: str):
        if (key.startswith("AP") or key == "" or value == ""):
            return
        self.absDictionaryDB.save(key, value)


'''
the Kokoro clss enables: using a database, inter skill communication and action log monitoring
'''


class Kokoro:
    def __init__(self, absDictionaryDB: AbsDictionaryDB):
        self.emot = ""
        self.grimoireMemento = GrimoireMemento(absDictionaryDB)
        self.toHeart: dict[str, str] = {}

    def getEmot(self) -> str:
        return self.emot

    def setEmot(self, emot: str):
        self.emot = emot


'''
speak something x times:
'''


class APSay(Mutatable):
    def __init__(self, at: int, param: str) -> None:
        super().__init__()
        if at > 10:
            at = 10
        self.at = at
        self.param = param

    def action(self, ear: str, skin: str, eye: str) -> str:
        '''TODO Auto-generated method stub'''
        axnStr = ""
        if self.at > 0:
            if ear.lower() != self.param.lower():
                axnStr = self.param
                self.at -= 1
        return axnStr

    def completed(self) -> bool:
        return self.at < 1

    def clone(self) -> Mutatable:
        return APSay(self.at, self.param)


class APVerbatim(Mutatable):
    """this algorithm part says each past param verbatim"""

    def __init__(self, *args) -> None:
        super().__init__()
        self.sentences: list[str] = []
        self.at = 0

        try:
            if isinstance(args[0], list):
                self.sentences = args[0]
                if 0 == len(self.sentences):
                    self.at = 30
            else:
                for i in range(len(args)):
                    self.sentences.append(args[i])
        except:
            self.at = 30

    # Override
    def action(self, ear: str, skin: str, eye: str) -> str:
        axnStr = ""
        if self.at < len(self.sentences):
            axnStr = self.sentences[self.at]
            self.at += 1
        return axnStr

    # Override
    def completed(self) -> bool:
        return self.at >= len(self.sentences)

    # Override
    def clone(self) -> Mutatable:
        return APVerbatim(DeepCopier().copyList(self.sentences))


class CldBool:
    # cloudian : this class is used to provide shadow reference to a boolean variable
    def __init__(self):
        self.modeActive = False

    @property
    def getModeActive(self) -> bool:
        return self.modeActive

    def setModeActive(self, modeActive: bool):
        self.modeActive = modeActive


class APCldVerbatim(Mutatable):
    '''this algorithm part says each string param verbatim'''

    def __init__(self, cldBool: CldBool, *words):
        super().__init__()
        self.sentences = []
        self.at = 0
        self.cldBool = cldBool

        try:
            if isinstance(words[0], list):
                self.sentences = words[0]
                self.cldBool.setModeActive(True)
            else:
                for i in range(len(words)):
                    self.sentences.append(words[i])
                self.cldBool.setModeActive(True)
        except:
            self.at = 30

    # Override
    def action(self, ear: str, skin: str, eye: str) -> str:
        axnStr = ""
        if self.at < len(self.sentences):
            axnStr = self.sentences[self.at]
            self.at += 1

        self.cldBool.setModeActive(not (self.at >= len(self.sentences)))
        return axnStr

    # Override
    def completed(self) -> bool:
        return self.at >= len(self.sentences)

    # Override
    def clone(self) -> Mutatable:
        return APCldVerbatim(self.cldBool, DeepCopier().copyList(self.sentences))


# the PlayGround cls is not a core class but very useful
class enumTimes(Enum):
    DATE = auto()
    DAY = auto()
    YEAR = auto()
    HOUR = auto()
    MINUTES = auto()
    SECONDS = auto()


class PlayGround:
    def __init__(self):
        self.week_days = {1: 'sunday',
                          2: 'monday',
                          3: 'tuesday',
                          4: 'wednesday',
                          5: 'thursday',
                          6: 'friday',
                          7: 'saturday',
                          }
        self.dayOfMonth = {1: "first_of", 2: "second_of", 3: "third_of", 4: "fourth_of", 5: "fifth_of", 6: "sixth_of",
                           7: "seventh_of",
                           8: "eighth_of", 9: "nineth_of", 10: "tenth_of", 11: "eleventh_of", 12: "twelveth_of",
                           13: "thirteenth_of",
                           14: "fourteenth_of", 15: "fifteenth_of", 16: "sixteenth_of", 17: "seventeenth_of",
                           18: "eighteenth_of",
                           19: "nineteenth_of", 20: "twentyth_of", 21: "twentyfirst_of", 22: "twentysecond_of",
                           23: "twentythird_of",
                           24: "twentyfourth_of", 25: "twentyfifth_of", 26: "twentysixth_of", 27: "twentyseventh_of",
                           28: "twentyeighth_of",
                           29: "twentynineth_of", 30: "thirtyth_of", 31: "thirtyfirst_of"}

    def getCurrentTimeStamp(self) -> str:
        """This method returns the current time (hh:mm)"""
        right_now = datetime.datetime.now()
        temp_minute: int = right_now.minute
        tempstr: str = ""
        if temp_minute < 10:
            tempstr = "0" + str(right_now.minute)
        else:
            tempstr = str(right_now.minute)
        return str(right_now.hour) + ":" + tempstr

    def getMonthAsInt(self) -> int:
        """This method returns the current month (MM)"""
        right_now = datetime.datetime.now()
        return right_now.month

    def getDayOfTheMonthAsInt(self) -> int:
        """This method returns the current day (dd)"""
        right_now = datetime.datetime.now()
        return right_now.day

    def getYearAsInt(self) -> int:
        """This method returns the current year (yyyy)"""
        right_now = datetime.datetime.now()
        return right_now.year

    def getDayAsInt(self) -> int:
        """This method returns the current day of the week (1, 2, ... 7)"""
        right_now = datetime.datetime.now()
        return right_now.isoweekday()

    def getMinutes(self) -> str:
        """This method returns the current minutes (mm)"""
        right_now = datetime.datetime.now()
        return str(right_now.minute)

    def getSeconds(self) -> str:
        """This method returns the current seconds (ss)"""
        right_now = datetime.datetime.now()
        return str(right_now.second)

    def getDayOfDWeek(self) -> str:
        """This method returns the current day of the week as a word (monday, ...)"""
        right_now = datetime.datetime.now()
        return calendar.day_name[right_now.weekday()]

    def translateMonthDay(self, day: int) -> str:
        """This method returns the current day of the month as a word (first_of, ...)"""
        return self.dayOfMonth.get(day, "")

    def getSpecificTime(self, time_variable: enumTimes) -> str:
        """This method returns the current specific date in words (eleventh_of June 2021, ...)"""
        enum_temp = time_variable.name
        if enum_temp == "DATE":
            right_now = datetime.datetime.now()
            output = self.translateMonthDay(right_now.day) + " " + calendar.month_name[right_now.month] + " " + str(
                right_now.year)
        elif enum_temp == "HOUR":
            output = str(datetime.datetime.now().hour)
        elif enum_temp == "SECONDS":
            output = str(datetime.datetime.now().second)
        elif enum_temp == "MINUTES":
            output = str(datetime.datetime.now().minute)
        elif enum_temp == "YEAR":
            output = str(datetime.datetime.now().year)
        else:
            output = ""
        return output

    def getSecondsAsInt(self) -> int:
        """This method returns the current seconds"""
        right_now = datetime.datetime.now()
        return right_now.second

    def getMinutesAsInt(self) -> int:
        """This method returns the current minutes"""
        right_now = datetime.datetime.now()
        return right_now.minute

    def getHoursAsInt(self) -> int:
        """This method returns the current hour"""
        right_now = datetime.datetime.now()
        return right_now.hour

    def getFutureInXMin(self, extra_minutes: int) -> str:
        """This method returns the date in x minutes"""
        right_now = datetime.datetime.now()
        final_time = right_now + datetime.timedelta(minutes=extra_minutes)
        regex: RegexUtil = RegexUtil()
        s1: str = str(final_time)
        s1 = regex.extractEnumRegex(enumRegexGrimoire.simpleTimeStamp, s1)
        if s1[0] == '0':
            s1 = s1[1:]
        return s1

    def getPastInXMin(self, less_minutes: int) -> str:
        """This method returns the date x minutes before"""
        right_now = datetime.datetime.now()
        final_time = right_now - datetime.timedelta(minutes=less_minutes)
        regex: RegexUtil = RegexUtil()
        s1: str = str(final_time)
        s1 = regex.extractEnumRegex(enumRegexGrimoire.simpleTimeStamp, s1)
        if s1[0] == '0':
            s1 = s1[1:]
        return s1

    def getFutureHour(self, startHour: int, addedHours: int) -> int:
        """This method returns the hour in x hours from the starting hour"""
        return (startHour + addedHours) % 24

    def getFutureFromXInYMin(self, to_add: int, start: str) -> str:
        """This method returns the time (hh:mm) in x minutes the starting time (hh:mm)"""
        values = start.split(":")
        times_to_add = (int(values[1]) + to_add) // 60
        new_minutes = (int(values[1]) + to_add) % 60
        new_time = str((int(values[0]) + times_to_add) % 24) + ":" + str(new_minutes)
        return new_time

    def timeInXMinutes(self, x: int) -> str:
        """This method returns the time (hh:mm) in x minutes"""
        right_now = datetime.datetime.now()
        final_time = right_now + datetime.timedelta(minutes=x)
        return str(final_time.hour) + ":" + str(final_time.minute)

    def isDayTime(self) -> bool:
        """This method returns true if it's daytime (6-18)"""
        return 5 < datetime.datetime.now().hour < 19

    def smallToBig(self, *a) -> bool:
        for i in range(len(a) - 1):
            if a[i] > a[i + 1]:
                return False
        return True

    def partOfDay(self) -> str:
        """This method returns which part of the day it is (morning, ...)"""
        hour: int = self.getHoursAsInt()
        if self.smallToBig(5, hour, 12):
            return "morning"
        elif self.smallToBig(11, hour, 17):
            return "afternoon"
        elif self.smallToBig(16, hour, 21):
            return "evening"
        return "night"

    def convertToDay(self, number: int) -> str:
        """This method converts the week number to the weekday name"""
        return self.week_days.get(number, "")

    def isNight(self) -> bool:
        """This method returns true if it's night (21-5)"""
        hour: int = self.getHoursAsInt()
        return hour > 20 or hour < 6

    def getTomorrow(self) -> str:
        """This method returns tomorrow"""
        right_now = datetime.datetime.now()
        if right_now.weekday() == 6:
            return "sunday"
        return calendar.day_name[right_now.weekday() + 1]

    def getYesterday(self) -> str:
        """This method returns yesterday"""
        right_now = datetime.datetime.now()
        if right_now.weekday == 0:
            return "Sunday"
        return calendar.day_name[right_now.weekday() - 1]

    def getGMT(self) -> int:
        """This method returns the local GMT"""
        right_now = datetime.datetime.now()
        timezone = int(str(right_now.astimezone())[-6:-3])
        return timezone

    def getCurrentMonthName(self) -> str:
        month: int = self.getMonthAsInt()
        match month:
            case 1:
                return "january"
            case 2:
                return "february"
            case 3:
                return "march"
            case 4:
                return "april"
            case 5:
                return "may"
            case 6:
                return "june"
            case 7:
                return "july"
            case 8:
                return "august"
            case 9:
                return "november"
            case 10:
                return "october"
            case 11:
                return "november"
            case 12:
                return "december"
        return ""

    def getCurrentMonthDay(self):
        return self.dayOfMonth.get(self.getDayOfTheMonthAsInt(), "")

    @staticmethod
    def getLocal() -> str:
        """This method returns the local time zone"""
        return f'{datetime.datetime.now(datetime.timezone.utc).astimezone().tzinfo}'

    @staticmethod
    def findDay(month: int, day: int, year: int) -> str:
        # get weekday from date
        if day > 31:
            return ""
        # april, june, sep, nov case:
        if day > 30:
            if (month == 4) or (month == 6) or (month == 9) or (month == 11):
                return ""
        # feb case:
        if month == 2:
            if PlayGround.isLeapYear(year):
                if day > 29:
                    return ""
            if day > 28:
                return ""
        return datetime.date(year, month, day).strftime("%A").lower()

    @staticmethod
    def nxtDayOnDate(dayOfMonth: int) -> str:
        # get the weekday on the next dayOfMonth
        today: int = PlayGround().getDayOfTheMonthAsInt()
        if today <= dayOfMonth:
            return PlayGround.findDay(PlayGround().getMonthAsInt(), dayOfMonth, PlayGround().getYearAsInt())
        elif not (PlayGround().getMonthAsInt() == 12):
            return PlayGround.findDay(PlayGround().getMonthAsInt() + 1, dayOfMonth, PlayGround().getYearAsInt())
        return PlayGround.findDay(1, dayOfMonth, PlayGround().getYearAsInt() + 1)

    @staticmethod
    def isLeapYear(year: int):
        # divisible by 4
        isLeapyear: bool = year % 4 == 0
        # divisible by 4, not by 100, or divisible by 400
        return isLeapyear and (year % 100 != 0 or year % 400 == 0)


# A step-by-step plan to achieve a goal
class Algorithm:

    def __init__(self, algParts: list[Mutatable]):  # list of Mutatable
        super().__init__()
        self.algParts: list[Mutatable] = algParts

    # *constract with string and goal

    @property
    def getAlgParts(self) -> list[Mutatable]:
        return self.algParts

    def getSize(self) -> int:
        return len(self.algParts)

    def clone(self) -> Algorithm:
        parts = []  # list of Mutatable
        for mutatable in self.algParts:
            parts.append(mutatable.clone())
        return Algorithm(parts)


''' NEURON CLASS '''


# used to transport algorithms to other classes
class Neuron:
    def __init__(self) -> None:
        self._defcons: dict[int, list[Algorithm]] = {}
        for i in range(1, 6):
            self._defcons[i] = []

    def insertAlg(self, priority: int, alg: Algorithm):
        if 0 < priority < 6:
            if len(self._defcons[priority]) < 4:
                self._defcons[priority].append(alg)

    def getAlg(self, defcon: int) -> Algorithm:
        if len(self._defcons[defcon]) > 0:
            temp = self._defcons[defcon].pop(0)
            return temp.clone()
        return None


''' DISKILLUTILS CLASS '''


class DISkillUtils:
    # alg part based algorithm building methods
    # var args param
    def algBuilder(self, *itte: Mutatable) -> Algorithm:
        # returns an algorithm built with the algPart varargs
        algParts1: list[Mutatable] = []
        for i in range(0, len(itte)):
            algParts1.append(itte[i])
        algorithm: Algorithm = Algorithm(algParts1)
        return algorithm

    # String based algorithm building methods
    def simpleVerbatimAlgorithm(self, *sayThis) -> Algorithm:
        # returns alg that says the word string (sayThis)
        return self.algBuilder(APVerbatim(*sayThis))

    # String part based algorithm building methods with cloudian (shallow ref object to inform on alg completion)
    def simpleCloudiandVerbatimAlgorithm(self, cldBool: CldBool, *sayThis) -> Algorithm:
        # returns alg that says the word string (sayThis)
        return self.algBuilder(APCldVerbatim(cldBool, *sayThis))

    def strContainsList(self, str1: str, items: list[str]) -> str:
        # returns the 1st match between words in a string and values in a list.
        for temp in items:
            if (str1.count(temp) > 0):
                return temp
        return ""


''' DISKILLV2 CLASS '''


class DiSkillV2:
    def __init__(self):
        # The variables start with an underscore (_) because they are protected
        self._kokoro = Kokoro(AbsDictionaryDB())  # consciousness, shallow ref class to enable interskill communications
        self._diSkillUtils = DISkillUtils()
        self._outAlg: Algorithm = None  # skills output
        self._outpAlgPriority: int = -1  # defcon 1->5

    def setOutalg(self, alg: Algorithm):
        self._outAlg = alg

    def getOutAlg(self) -> Algorithm:
        return self._outAlg

    def setOutAlgPriority(self, priority):
        self._outpAlgPriority = priority

    # skill triggers and algorithmic logic
    def input(self, ear: str, skin: str, eye: str):
        pass

    # extraction of skill algorithm to run (if there is one)
    def output(self, noiron: Neuron):
        if self._outAlg is not None:
            noiron.insertAlg(self._outpAlgPriority, self._outAlg)
            self._outpAlgPriority = -1
            self._outAlg = None

    def setKokoro(self, kokoro: Kokoro):
        # use this for telepathic communication between different chobits objects
        self._kokoro = kokoro

    def getKokoro(self):
        return self._kokoro

    # in skill algorithm building shortcut methods:
    def setVerbatimAlg(self, priority: int, *sayThis: str):
        # build a simple output algorithm to speak string by string per think cycle
        # uses varargs param
        temp: list[str] = []
        for i in range(0, len(sayThis)):
            temp.append(sayThis[i])
        self._outAlg = self._diSkillUtils.simpleVerbatimAlgorithm(temp)
        self._outpAlgPriority = priority  # 1->5 1 is the highest algorithm priority

    def setSimpleAlg(self, *sayThis: str):
        # based on the setVerbatimAlg method
        # build a simple output algorithm to speak string by string per think cycle
        # uses varargs param
        temp: list[str] = []
        for i in range(0, len(sayThis)):
            temp.append(sayThis[i])
        self._outAlg = self._diSkillUtils.simpleVerbatimAlgorithm(temp)
        self._outpAlgPriority = 4  # 1->5 1 is the highest algorithm priority

    def setVebatimAlgFromList(self, priority: int, sayThis: list[str]):
        # build a simple output algorithm to speak string by string per think cycle
        # uses list param
        self._outAlg = self._diSkillUtils.algBuilder(APVerbatim(sayThis))
        self._outpAlgPriority = priority  # 1->5 1 is the highest algorithm priority

    def algPartsFusion(self, priority: int, *algParts: Mutatable):
        # build a custom algorithm out of a chain of algorithm parts(actions)
        self._outAlg = self._diSkillUtils.algBuilder(algParts)
        self._outpAlgPriority = priority  # 1->5 1 is the highest algorithm priority

    def pendingAlgorithm(self) -> bool:
        # is an algorithm pending?
        return self._outAlg is not None


class DiHelloWorld(DiSkillV2):
    # Override
    def input(self, ear: str, skin: str, eye: str):
        if ear == "hello":
            self.setVerbatimAlg(4, "hello world")  # # 1->5 1 is the highest algorithm priority


''' ----------------- REGEXUTIL ---------------- '''


class LGPointInt:
    def __init__(self, x_init: int, y_init: int):
        self.x: int = x_init
        self.y: int = y_init

    def shift(self, x: int, y: int):
        self.x += x
        self.y += y

    def setPosition(self, x: int, y: int):
        self.x = x
        self.y = y

    def reset(self):
        self.x = 0
        self.y = 0

    def __repr__(self):
        return "".join(["Point(", str(self.x), ",", str(self.y), ")"])


def distance(a, b):
    return sqrt((a.x - b.x) ** 2 + (a.y - b.y) ** 2)


class LGPointFloat:
    def __init__(self, x_init: float, y_init: float):
        self.x: float = x_init
        self.y: float = y_init

    def shift(self, x: float, y: float):
        self.x += x
        self.y += y

    def __repr__(self):
        return "".join(["Point(", str(self.x), ",", str(self.y), ")"])


def distance(a, b):
    return sqrt((a.x - b.x) ** 2 + (a.y - b.y) ** 2)


class enumRegexGrimoire(Enum):
    email = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}"
    timeStamp = "[0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}"
    simpleTimeStamp = "[0-9]{1,2}:[0-9]{1,2}"
    integer = "[-+]?[0-9]{1,13}"
    double_num = "[-+]?[0-9]*[.,][0-9]*"
    repeatedWord = "\\b([\\w\\s']+) \\1\\b"
    phone = "[0]\\d{9}"
    trackingID = "[A-Z]{2}[0-9]{9}[A-Z]{2}"
    IPV4 = "([0-9].){4}[0-9]*"
    domain = "[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}"
    number = "\\d+(\\.\\d+)?"
    secondlessTimeStamp = "[0-9]{1,2}:[0-9]{1,2}"
    date = "[0-9]{1,4}/[0-9]{1,2}/[0-9]{1,2}"
    fullDate = "[0-9]{1,4}/[0-9]{1,2}/[0-9]{1,2} [0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}"


''' REGEXUTIL CLASS'''


# returns expression of type theRegex from the string str2Check
class RegexUtil:
    def extractRegex(self, theRegex: str, str2Check: str) -> str:
        regexMatcher = re.search(theRegex, str2Check)
        if (regexMatcher != None):
            return regexMatcher.group(0).strip()
        return ""

    def extractEnumRegex(self, theRegex: enumRegexGrimoire, str2Check: str) -> str:
        # example usage:
        # print(regexUtil.extractEnumRegex(enumRegexGrimoire.domain,"the site is creamedcorn.com ok?"))
        regexMatcher = re.search(theRegex.value, str2Check)
        if (regexMatcher != None):
            return regexMatcher.group(0).strip()
        return ""

    def extractAllRegexes(self, theRegex: str, str2Check: str) -> list[str]:
        p = re.compile(theRegex)
        return p.findall(str2Check)
        # mylist: list[str] = str2Check.split()
        # r = re.compile(theRegex)
        # l_final = list(filter(r.match, mylist))
        # return l_final

    def extractAllEnumRegexes(self, theRegex: enumRegexGrimoire, str2Check: str) -> list[str]:
        # return a list of all matches
        p = re.compile(theRegex)
        return p.findall(str2Check)

    def pointRegex(self, str2Check: str) -> LGPointInt:
        # "[-+]?[0-9]{1,13}(\\.[0-9]*)?" for double numbers
        theRegex: str = "[-+]?[0-9]{1,13}"
        result: LGPointInt = LGPointInt(0, 0)
        list1: list[str] = []
        regexMatcher = re.search(theRegex, str2Check)
        if (regexMatcher != None):
            result.y = int(regexMatcher.group(0).strip())
        str2Check = str2Check[str2Check.index(f'{result.y}') + 1:len(str2Check)]
        phase2 = str2Check
        phase2 = self.intRegex(phase2)
        if (phase2 == ""):
            return LGPointInt(result.y, 0)

        result.x = int(phase2)
        return LGPointInt(result.y, result.x)

    def contactRegex(self, str2Check: str) -> str:
        # return a list of all matches
        theRegex = r"(?<=contact)(.*)"
        list1: list[str] = []
        regexMatcher = re.search(theRegex, str2Check)
        if (regexMatcher != None):
            return regexMatcher.group(0).strip()
        return ""

    def duplicateRegex(self, input: str) -> str:
        # first split given string separated by space
        # into words
        words = input.split(' ')

        # now convert list of words into dictionary
        dict = Counter(words)

        # traverse list of words and check which first word
        # has frequency > 1
        for key in words:
            if dict[key] > 1:
                return key
        return ""

    def uniqueWord(self, str1: str) -> str:
        list1: list[str] = []  # of strings
        s = str1.split(" ")
        p = s[0]
        list1.append(p)
        # i
        for i in range(1, len(s)):
            if (not (p == s[i])):
                list1.append(s[i])
            p = s[i]
        return list1[0]

    def afterWord(self, word: str, str2Check: str) -> str:
        # return a list of all matches
        theRegex = r"(?<=" + word + r")(.*)"
        list1: list[str] = []
        regexMatcher = re.search(theRegex, str2Check)
        if (regexMatcher != None):
            return regexMatcher.group(0).strip()
        return ""

    def phoneRegex1(self, str2Check: str) -> str:
        return self.regexChecker(r"[0]\d{2}\d{4}\d{3}$", str2Check)

    def firstWord(self, str2Check: str) -> str:
        arr: list[str] = str2Check.split(" ", 2)
        firstWord = arr[0]  # the
        return firstWord

    def stripAwayNumbers(self, str1: str) -> str:
        return re.sub(r'\d+', '', str1)


''' --------------- TIMEGATE --------------- '''
import time
import datetime

''' --------------- TIMEGATE --------------- '''
import time
import datetime
from datetime import timedelta

''' TIMEGATE CLASS '''


class TimeGate:
    # a gate that only opens x minutes after it has been set

    def __init__(self, minutes) -> None:
        self.pause: int = 1
        self.openedGate: datetime.date = datetime.datetime.now()
        self.checkPoint: datetime.date = datetime.datetime.now()

        try:
            self.pause = minutes
            try:
                time.sleep(0.1)
            except Exception as e:
                # TODO Auto-generated catch block
                # e.printStackTrace()
                pass
        except:
            pass

    def isClosed(self) -> bool:
        return self.openedGate < datetime.datetime.now()

    def isOpen(self) -> bool:
        return not (self.openedGate < datetime.datetime.now())

    def open(self, minutes: int):
        now: datetime.date = datetime.datetime.now()
        self.openedGate = now + timedelta(minutes=minutes)

    def openForPauseMinutes(self):
        now: datetime.date = datetime.datetime.now()
        self.openedGate = now + timedelta(minutes=self.pause)

    def setPause(self, pause: int):
        if 60 > pause > 0:
            self.pause = pause

    def resetCheckPoint(self):
        self.checkPoint = datetime.datetime.now()

    def getRunTimeTimeDifInSecondes(self) -> int:
        # used to measure code snippets run time
        now: datetime.date = datetime.datetime.now()
        diff: datetime.timedelta = self.checkPoint - now
        return diff.total_seconds()

    def close(self):
        self.openedGate: datetime.date = datetime.datetime.now()


''' CERABELLUM CLASS '''


class Cerabellum:
    # runs an algorithm
    def __init__(self) -> None:
        self.fin: int = None
        self.at: int = None
        self.incrementAt: bool = False
        self.alg: Algorithm = None
        self.isActive: bool = False
        self.emot: str = ""

    def advanceInAlg(self):
        if self.incrementAt:
            self.incrementAt = False
            self.at += 1
            if self.at == self.fin:
                self.isActive = False

    def getAt(self) -> int:
        return self.at

    def getEmot(self) -> str:
        return self.emot

    def setAlgorithm(self, algorithm: Algorithm) -> bool:
        if not self.isActive and (algorithm.getAlgParts is not None):
            self.alg = algorithm
            self.at = 0
            self.fin = algorithm.getSize()
            self.isActive = True
            self.emot = self.alg.getAlgParts[self.at].myName()  # updated line
            return False
        return True

    def isActiveMethod(self) -> bool:
        return self.isActive

    def act(self, ear: str, skin: str, eye: str) -> str:
        axnStr: str = ""
        if not self.isActive:
            return axnStr
        if self.at < self.fin:
            axnStr = self.alg.getAlgParts[self.at].action(ear, skin, eye)
            self.emot = self.alg.getAlgParts[self.at].myName()
            if self.alg.getAlgParts[self.at].completed():
                self.incrementAt = True
        return axnStr

    def deActivateAlg(self):
        # stop the entire running active Algorithm
        self.isActive = self.isActive and not self.alg.getAlgParts[self.at].algKillSwitch


''' FUSION CLASS '''


class Fusion:
    def __init__(self):
        self._emot: str = ""
        self.ceraArr: list[Cerabellum] = [Cerabellum() for i in range(5)]
        self._result: str = ""

    def getEmot(self) -> str:
        return self._emot

    def loadAlgs(self, neuron: Neuron):
        for i in range(1, 6):
            if not self.ceraArr[i - 1].isActive:
                temp: Algorithm = neuron.getAlg(i)
                if not temp is None:
                    self.ceraArr[i - 1].setAlgorithm(temp)

    def runAlgs(self, ear: str, skin: str, eye: str) -> str:
        self._result = ""
        for i in range(5):
            if not self.ceraArr[i].isActive:
                continue
            self._result = self.ceraArr[i].act(ear, skin, eye)
            self.ceraArr[i].advanceInAlg()
            self._emot = self.ceraArr[i].getEmot()
            self.ceraArr[i].deActivateAlg()  # deactivation if Mutatable.algkillswitch = true
            return self._result
        self._emot = ""
        return self._result


''' Thinkable CLASS '''


class Thinkable:
    def think(self, ear: str, skin: str, eye: str) -> str:
        """override me"""
        return ""


'''*********
 *intro *
 ********

 cyberpunk>the matrix.
 one line of code to add a skill(logical skill), 
 but ALSO! 1 line of code to add a hardware capability(hardware skill).

 ***********
 *Atributes*
 ***********

 the logicChobit is a Chobits attribute with logic skills. these skills have algorithmic logic,
 and thinking patterns.

 the hardwareChobit is a Chobit attribute with hardware skills. these skills access the
 hardware capabilities of the machine.
 for example: output printing, sending mail, sending SMS, making a phone call, taking
 a photo, accessing GPIO pins, opening a program, fetching the weather and so on.

 ********************
 *special attributes*
 ********************

 in some cases the hardware chobit may want to send a message to the logic chobit,
 for example to give feedback on hardware components. this is handled by the bodyInfo
 String.

 the emot attribute is the chobit's current emotion.

 the logicChobitOutput is the chobit's last output.

 **********************
 *hardware skill types*
 **********************

 assembly style: these skills are triggered by strings with certain wild card characters
 for example: #open browser

 funnel: these are triggered by strings without wild cards.
 for example: "hello world"->prints hello world

 *************
 *example use*
 *************
 DiSysOut is an example of a hardware skill

 see Brain main for example use of the cyberpunk Software Design Pattern'''


class Brain:
    def __init__(self):
        self._emotion: str = ""
        self._bodyInfo: str = ""
        self._logicChobitOutput: str = ""
        self.logicChobit: Chobits = Chobits()
        self.hardwareChobit: Chobits = Chobits()
        self.hardwareChobit.setKokoro(self.logicChobit.getKokoro())

    def getEmotion(self) -> str:
        return self._emotion

    def getBodyInfo(self) -> str:
        return self._bodyInfo

    def getLogicChobitOutput(self) -> str:
        return self._logicChobitOutput

    def doIt(self, ear: str, skin: str, eye: str):
        if not self._bodyInfo == "":
            self._logicChobitOutput = self.logicChobit.think(ear, self._bodyInfo, eye)
        else:
            self._logicChobitOutput = self.logicChobit.think(ear, skin, eye)
        self._emotion = self.logicChobit.getSoulEmotion()
        self._bodyInfo = self.hardwareChobit.think(self._logicChobitOutput, skin, eye)


''' Chobits CLASS '''


class Chobits(Thinkable):

    def __init__(self):
        super().__init__()
        self._dClasses: list[DiSkillV2] = []  # _ is a private access modifier
        self._fusion: Fusion = Fusion()
        self._noiron: Neuron = Neuron()
        self._kokoro: Kokoro = Kokoro(AbsDictionaryDB())  # soul

    '''set the chobit database
        the database is built as a key value dictionary
        the database can be using the kokoro attribute'''

    def setDatabase(self, absDictionaryDB: AbsDictionaryDB):
        self._kokoro = Kokoro(absDictionaryDB)

    def addSkill(self, skill: DiSkillV2) -> Chobits:
        # add a skill (builder design patterned func))
        skill.setKokoro(self._kokoro)
        self._dClasses.append(skill)
        return self

    def clearSkills(self):
        # remove all skills
        self._dClasses.clear()

    def addSkills(self, *skills: DiSkillV2):
        for skill in skills:
            skill.setKokoro(self._kokoro)
            self._dClasses.append(skill)

    def removeSkill(self, skill: DiSkillV2):
        self._dClasses.remove(skill)

    def containsSkill(self, skill: DiSkillV2) -> bool:
        return self._dClasses.__contains__(skill)

    # override
    def think(self, ear: str, skin: str, eye: str) -> str:
        for dCls in self._dClasses:
            self.inOut(dCls, ear, skin, eye)
        self._fusion.loadAlgs(self._noiron)
        return self._fusion.runAlgs(ear, skin, eye)

    def getSoulEmotion(self) -> str:
        return self._fusion.getEmot()

    def inOut(self, dClass: DiSkillV2, ear: str, skin: str, eye: str):
        dClass.input(ear, skin, eye)  # new
        dClass.output(self._noiron)

    def getKokoro(self) -> Kokoro:
        # several chobits can use the same soul
        return self._kokoro

    def setKokoro(self, kokoro: Kokoro):
        # use this for telepathic communication between different chobits objects
        self._kokoro = kokoro

    def getFusion(self) -> Fusion:
        return self._fusion

    def get_skill_list(self) -> list[str]:
        result: list[str] = []
        for skill in self._dClasses:
            result.append(skill.__class__.__name__)
        return result
