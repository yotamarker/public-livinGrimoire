from __future__ import annotations

from LivinGrimoire23 import *
from enum import Enum, auto
import calendar
import re
from math import sqrt
import random
import time
import datetime
from datetime import timedelta


class DeepCopier:
    @staticmethod
    def copyList(original: list[str]) -> list[str]:
        deepCopy: list[str] = []
        for item in original:
            deepCopy.append(item)
        return deepCopy

    @staticmethod
    def copyListOfInts(original: list[int]) -> list[int]:
        deepCopy: list[int] = []
        for item in original:
            deepCopy.append(item)
        return deepCopy


# the PlayGround cls is not a core class but very useful
class enumTimes(Enum):
    DATE = auto()
    DAY = auto()
    YEAR = auto()
    HOUR = auto()
    MINUTES = auto()
    SECONDS = auto()


class TimeUtils:
    week_days = {1: 'sunday',
                 2: 'monday',
                 3: 'tuesday',
                 4: 'wednesday',
                 5: 'thursday',
                 6: 'friday',
                 7: 'saturday',
                 }
    dayOfMonth = {1: "first_of", 2: "second_of", 3: "third_of", 4: "fourth_of", 5: "fifth_of", 6: "sixth_of",
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

    @staticmethod
    def getCurrentTimeStamp() -> str:
        """This method returns the current time (hh:mm)"""
        right_now = datetime.datetime.now()
        temp_minute: int = right_now.minute
        tempstr: str
        if temp_minute < 10:
            tempstr = "0" + str(right_now.minute)
        else:
            tempstr = str(right_now.minute)
        return str(right_now.hour) + ":" + tempstr

    @staticmethod
    def getMonthAsInt() -> int:
        """This method returns the current month (MM)"""
        right_now = datetime.datetime.now()
        return right_now.month

    @staticmethod
    def getDayOfTheMonthAsInt() -> int:
        """This method returns the current day (dd)"""
        right_now = datetime.datetime.now()
        return right_now.day

    @staticmethod
    def getYearAsInt() -> int:
        """This method returns the current year (yyyy)"""
        right_now = datetime.datetime.now()
        return right_now.year

    @staticmethod
    def getDayAsInt() -> int:
        """This method returns the current day of the week (1, 2, ... 7)"""
        right_now = datetime.datetime.now()
        return right_now.isoweekday()

    @staticmethod
    def getMinutes() -> str:
        """This method returns the current minutes (mm)"""
        right_now = datetime.datetime.now()
        return str(right_now.minute)

    @staticmethod
    def getSeconds() -> str:
        """This method returns the current seconds (ss)"""
        right_now = datetime.datetime.now()
        return str(right_now.second)

    @staticmethod
    def getDayOfDWeek() -> str:
        """This method returns the current day of the week as a word (monday, ...)"""
        right_now = datetime.datetime.now()
        return calendar.day_name[right_now.weekday()]

    @staticmethod
    def translateMonthDay(day: int) -> str:
        """This method returns the current day of the month as a word (first_of, ...)"""
        return TimeUtils.dayOfMonth.get(day, "")

    @staticmethod
    def getSpecificTime(time_variable: enumTimes) -> str:
        """This method returns the current specific date in words (eleventh_of June 2021, ...)"""
        enum_temp = time_variable.name
        if enum_temp == "DATE":
            right_now = datetime.datetime.now()
            output = TimeUtils.translateMonthDay(right_now.day) + " " + calendar.month_name[
                right_now.month] + " " + str(
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

    @staticmethod
    def getSecondsAsInt() -> int:
        """This method returns the current seconds"""
        right_now = datetime.datetime.now()
        return right_now.second

    @staticmethod
    def getMinutesAsInt() -> int:
        """This method returns the current minutes"""
        right_now = datetime.datetime.now()
        return right_now.minute

    @staticmethod
    def getHoursAsInt() -> int:
        """This method returns the current hour"""
        right_now = datetime.datetime.now()
        return right_now.hour

    @staticmethod
    def getFutureInXMin(extra_minutes: int) -> str:
        """This method returns the date in x minutes"""
        right_now = datetime.datetime.now()
        final_time = right_now + datetime.timedelta(minutes=extra_minutes)
        s1: str = str(final_time)
        s1 = RegexUtil.extractEnumRegex(enumRegexGrimoire.simpleTimeStamp, s1)
        if s1[0] == '0':
            s1 = s1[1:]
        return s1

    @staticmethod
    def getPastInXMin(less_minutes: int) -> str:
        """This method returns the date x minutes before"""
        right_now = datetime.datetime.now()
        final_time = right_now - datetime.timedelta(minutes=less_minutes)
        s1: str = str(final_time)
        s1 = RegexUtil.extractEnumRegex(enumRegexGrimoire.simpleTimeStamp, s1)
        if s1[0] == '0':
            s1 = s1[1:]
        return s1

    @staticmethod
    def getFutureHour(startHour: int, addedHours: int) -> int:
        """This method returns the hour in x hours from the starting hour"""
        return (startHour + addedHours) % 24

    @staticmethod
    def getFutureFromXInYMin(to_add: int, start: str) -> str:
        """This method returns the time (hh:mm) in x minutes the starting time (hh:mm)"""
        values = start.split(":")
        times_to_add = (int(values[1]) + to_add) // 60
        new_minutes = (int(values[1]) + to_add) % 60
        new_time = str((int(values[0]) + times_to_add) % 24) + ":" + str(new_minutes)
        return new_time

    @staticmethod
    def timeInXMinutes(x: int) -> str:
        """This method returns the time (hh:mm) in x minutes"""
        right_now = datetime.datetime.now()
        final_time = right_now + datetime.timedelta(minutes=x)
        return str(final_time.hour) + ":" + str(final_time.minute)

    @staticmethod
    def isDayTime() -> bool:
        """This method returns true if it's daytime (6-18)"""
        return 5 < datetime.datetime.now().hour < 19

    @staticmethod
    def smallToBig(*a) -> bool:
        for i in range(len(a) - 1):
            if a[i] > a[i + 1]:
                return False
        return True

    @staticmethod
    def partOfDay() -> str:
        """This method returns which part of the day it is (morning, ...)"""
        hour: int = TimeUtils.getHoursAsInt()
        if TimeUtils.smallToBig(5, hour, 12):
            return "morning"
        elif TimeUtils.smallToBig(11, hour, 17):
            return "afternoon"
        elif TimeUtils.smallToBig(16, hour, 21):
            return "evening"
        return "night"

    @staticmethod
    def convertToDay(number: int) -> str:
        """This method converts the week number to the weekday name"""
        return TimeUtils.week_days.get(number, "")

    @staticmethod
    def isNight() -> bool:
        """This method returns true if it's night (21-5)"""
        hour: int = TimeUtils.getHoursAsInt()
        return hour > 20 or hour < 6

    @staticmethod
    def getTomorrow() -> str:
        """This method returns tomorrow"""
        right_now = datetime.datetime.now()
        if right_now.weekday() == 6:
            return "sunday"
        return calendar.day_name[right_now.weekday() + 1]

    @staticmethod
    def getYesterday() -> str:
        """This method returns yesterday"""
        right_now = datetime.datetime.now()
        if right_now.weekday == 0:
            return "Sunday"
        return calendar.day_name[right_now.weekday() - 1]

    @staticmethod
    def getGMT() -> int:
        """This method returns the local GMT"""
        right_now = datetime.datetime.now()
        timezone = int(str(right_now.astimezone())[-6:-3])
        return timezone

    @staticmethod
    def getCurrentMonthName() -> str:
        month: int = TimeUtils.getMonthAsInt()
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

    @staticmethod
    def getCurrentMonthDay():
        return TimeUtils.dayOfMonth.get(TimeUtils.getDayOfTheMonthAsInt(), "")

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
            if TimeUtils.isLeapYear(year):
                if day > 29:
                    return ""
            if day > 28:
                return ""
        return datetime.date(year, month, day).strftime("%A").lower()

    @staticmethod
    def nxtDayOnDate(dayOfMonth: int) -> str:
        # get the weekday on the next dayOfMonth
        today: int = TimeUtils().getDayOfTheMonthAsInt()
        if today <= dayOfMonth:
            return TimeUtils.findDay(TimeUtils().getMonthAsInt(), dayOfMonth, TimeUtils().getYearAsInt())
        elif not (TimeUtils().getMonthAsInt() == 12):
            return TimeUtils.findDay(TimeUtils().getMonthAsInt() + 1, dayOfMonth, TimeUtils().getYearAsInt())
        return TimeUtils.findDay(1, dayOfMonth, TimeUtils().getYearAsInt() + 1)

    @staticmethod
    def isLeapYear(year: int):
        # divisible by 4
        isLeapyear: bool = year % 4 == 0
        # divisible by 4, not by 100, or divisible by 400
        return isLeapyear and (year % 100 != 0 or year % 400 == 0)


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

    @staticmethod
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
    duplicatWord = "\\b(\\w+)\\b(?=.*\\b\\1\\b)"
    firstWord = "^\\w+"
    lastWord = "\\w+$"
    surname = "\\s+[^\\s]+"
    realNumber = "[-+]?[0-9]*[.,][0-9]*"  # -30.77 / 40.05
    numberStripper = "[^\\d]+"


''' REGEXUTIL CLASS'''


# returns expression of type theRegex from the string str2Check
class RegexUtil:
    @staticmethod
    def extractRegex(theRegex: str, str2Check: str) -> str:
        regexMatcher = re.search(theRegex, str2Check)
        if regexMatcher is not None:
            return regexMatcher.group(0).strip()
        return ""

    @staticmethod
    def extractEnumRegex(theRegex: enumRegexGrimoire, str2Check: str) -> str:
        # example usage:
        # print(regexUtil.extractEnumRegex(enumRegexGrimoire.domain,"the site is creamedcorn.com ok?"))
        regexMatcher = re.search(theRegex.value.__str__(), str2Check)
        if regexMatcher is not None:
            return regexMatcher.group(0).strip()
        return ""

    @staticmethod
    def extractAllRegexes(theRegex: str, str2Check: str) -> list[str]:
        p = re.compile(theRegex)
        return p.findall(str2Check)
        # mylist: list[str] = str2Check.split()
        # r = re.compile(theRegex)
        # l_final = list(filter(r.match, mylist))
        # return l_final

    @staticmethod
    def extractAllEnumRegexes(theRegex: enumRegexGrimoire, str2Check: str) -> list[str]:
        # return a list of all matches
        p = re.compile(theRegex.value.__str__())
        return p.findall(str2Check)

    @staticmethod
    def pointRegex(str2Check: str) -> LGPointInt:
        # "[-+]?[0-9]{1,13}(\\.[0-9]*)?" for double numbers
        theRegex: str = "[-+]?[0-9]{1,13}"
        result: LGPointInt = LGPointInt(0, 0)
        regexMatcher = re.search(theRegex, str2Check)
        if regexMatcher is not None:
            result.y = int(regexMatcher.group(0).strip())
        str2Check = str2Check[str2Check.index(f'{result.y}') + 1:len(str2Check)]
        phase2 = str2Check
        phase2 = RegexUtil.extractEnumRegex(enumRegexGrimoire.integer, phase2)
        if phase2 == "":
            return LGPointInt(result.y, 0)

        result.x = int(phase2)
        return LGPointInt(result.y, result.x)

    @staticmethod
    def afterWord(word: str, str2Check: str) -> str:
        # return a list of all matches
        theRegex = r"(?<=" + word + r")(.*)"
        regexMatcher = re.search(theRegex, str2Check)
        if regexMatcher is not None:
            return regexMatcher.group(0).strip()
        return ""


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

    def open_for_n_seconds(self, seconds: int):
        now: datetime.date = datetime.datetime.now()
        self.openedGate = now + timedelta(seconds=seconds)

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
        return int(diff.total_seconds())

    def close(self):
        self.openedGate: datetime.date = datetime.datetime.now()


class AlgDispenser:

    # super class to output an algorithm out of a selection of algorithms
    def __init__(self, *algorithms: Algorithm):
        super().__init__()
        self._algs: list[Algorithm] = []
        self._activeAlg: int = 0
        for i in range(0, len(algorithms)):
            self._algs.append(algorithms[i])

    def addAlgorithm(self, alg: Algorithm) -> AlgDispenser:
        # builder pattern
        self._algs.append(alg)
        return self

    def dispenseAlgorithm(self) -> Algorithm:
        return self._algs[self._activeAlg]

    def rndAlg(self) -> Algorithm:
        # return a random algorithm
        return self._algs[random.randint(0, len(self._algs) - 1)]

    def moodAlg(self, mood: int):
        # set output algorithm based on number representing mood
        if len(self._algs) > mood > -1:
            self._activeAlg = mood

    def algUpdate(self, mood: int, alg: Algorithm):
        # update an algorithm
        if not (len(self._algs) > mood > -1):
            return
        self._algs[mood] = alg

    def algRemove(self, mood: int):
        # remove an algorithm
        if not (len(self._algs) > mood > -1):
            return
        self._algs.pop(mood)

    def cycleAlg(self):
        self._activeAlg += 1
        if self._activeAlg == len(self._algs):
            self._activeAlg += 0


class SkillHubAlgDispenser:
    # super class to output an algorithm out of a selection of skills
    """
      engage the hub with dispenseAlg and return the value to outAlg attribute
      of the containing skill (which houses the skill hub)
      this module enables using a selection of 1 skill for triggers instead of having the triggers engage on multible skill
       the methode is ideal for learnability and behavioral modifications
       use a learnability auxiliary module as a condition to run an active skill shuffle or change methode
       (rndAlg , cycleAlg)
       moods can be used for specific cases to change behavior of the AGI, for example low energy state
       for that use (moodAlg)"""

    def __init__(self, *skillsParams: Skill):
        super().__init__()
        self._skills: list[Skill] = []
        self._activeSkill: int = 0
        self._tempN: Neuron = Neuron()
        self._kokoro = Kokoro(AbsDictionaryDB())
        for i in range(0, len(skillsParams)):
            skillsParams[i].setKokoro(self._kokoro)
            self._skills.append(skillsParams[i])

    def set_kokoro(self, kokoro):
        self._kokoro = kokoro
        for skill in self._skills:
            skill.setKokoro(self._kokoro)

    def addSkill(self, skill: Skill) -> SkillHubAlgDispenser:
        # builder pattern
        skill.setKokoro(self._kokoro)
        self._skills.append(skill)
        return self

    def dispenseAlgorithm(self, ear: str, skin: str, eye: str):
        # returns Algorithm? (or None)
        # return value to outAlg param of (external) summoner DiskillV2
        self._skills[self._activeSkill].input(ear, skin, eye)
        self._skills[self._activeSkill].output(self._tempN)
        for i in range(1, 6):
            temp: Algorithm = self._tempN.getAlg(i)
            if temp:
                return AlgorithmV2(i, temp)
        return None

    def randomizeActiveSkill(self):
        self._activeSkill = random.randint(0, len(self._skills) - 1)

    def setActiveSkillWithMood(self, mood: int):
        # mood integer represents active skill
        # different mood = different behavior
        if -1 < mood < len(self._skills) - 1:
            self._activeSkill = mood

    def cycleActiveSkill(self):
        # changes active skill
        # I recommend this method be triggered with a Learnability or SpiderSense object
        self._activeSkill += 1
        if self._activeSkill == len(self._skills):
            self._activeSkill = 0

    def getSize(self) -> int:
        return len(self._skills)

    def active_skill_ref(self) -> Skill:
        return self._skills[self._activeSkill]


class TrGEV3:
    # advanced boolean gates with internal logic
    # these ease connecting common logic patterns, as triggers
    def reset(self):
        pass

    def input(self, ear: str, skin: str, eye: str):
        pass

    def trigger(self) -> bool:
        return False


class TrgTolerance(TrGEV3):
    # this boolean gate will return true till depletion or reset()
    def __init__(self, maxrepeats: int):
        self._maxrepeats: int = maxrepeats
        self._repeates: int = 0

    def setMaxRepeats(self, maxRepeats: int):
        self._maxrepeats = maxRepeats
        self.reset()

    def reset(self):
        # refill trigger
        self._repeates = self._maxrepeats

    def trigger(self) -> bool:
        # will return true till depletion or reset()
        self._repeates -= 1
        if self._repeates > 0:
            return True
        return False

    def disable(self):
        self._repeates = 0


''' PRIORITYQUEUE CLASS '''


# A simple implementation of Priority Queue
# using Queue.

class LGFIFO:
    def __init__(self):
        self.queue = []

    def __str__(self):
        return ' '.join([str(i) for i in self.queue])

    # for checking if the queue is empty
    def isEmpty(self):
        return len(self.queue) == 0

    def peak(self):
        if self.isEmpty():
            return None
        return self.queue[0]

    # for inserting an element in the queue
    def insert(self, data):
        self.queue.append(data)

    # for popping an element based on Priority
    def poll(self) -> object:
        if not len(self.queue) == 0:
            result0 = self.queue[0]
            del self.queue[0]
            return result0
        return None

    def size(self) -> int:
        return len(self.queue)

    def clear(self):
        self.queue.clear()

    def removeItem(self, item):
        if self.queue.__contains__(item):
            self.queue.remove(item)

    def getRNDElement(self):
        if self.isEmpty():
            return None
        else:
            return self.queue[random.randint(0, len(self.queue) - 1)]

    def contains(self, item) -> bool:
        return self.queue.__contains__(item)


class UniqueItemsPriorityQue(LGFIFO):
    # a priority queue without repeating elements
    # override
    def insert(self, data):
        if not self.queue.__contains__(data):
            self.queue.append(data)

    # override
    def peak(self) -> str:
        # returns string
        temp = super().peak()
        if temp is None:
            return ""
        return temp

    def strContainsResponse(self, item: str) -> bool:
        for response in self.queue:
            if len(response) == 0:
                continue
            if item.__contains__(response):
                return True
        return False


class UniqueItemSizeLimitedPriorityQueue(UniqueItemsPriorityQue):
    # items in the queue are unique and do not repeat
    # the size of the queue is limited
    # this cls can also be used to detect repeated elements (nagging or reruns)
    def __init__(self, limit: int):
        super().__init__()
        self._limit = limit

    def getLimit(self) -> int:
        return self._limit

    def setLimit(self, limit: int):
        self._limit = limit

    # override
    def insert(self, data):
        if super().size() == self._limit:
            super().poll()
        super().insert(data)

    # override
    def poll(self):
        # returns string
        temp = super().poll()
        if temp is None:
            return ""
        return temp

    # override
    def getRNDElement(self):
        temp = super().getRNDElement()
        if temp is None:
            return ""
        return temp

    def getAsList(self) -> list[str]:
        return self.queue


class AXLearnability:
    def __init__(self, tolerance):
        self.algSent = False
        # Problems that may result because of the last deployed algorithm:
        self.defcons = set()
        # Major chaotic problems that may result because of the last deployed algorithm:
        self.defcon5 = set()
        # Goals the last deployed algorithm aims to achieve:
        self.goals = set()
        # How many failures / problems till the algorithm needs to mutate (change)
        self.trgTolerance = TrgTolerance(tolerance)
        self.trgTolerance.reset()

    def pendAlg(self):
        # An algorithm has been deployed
        # Call this method when an algorithm is deployed (in a DiSkillV2 object)
        self.algSent = True
        self.trgTolerance.trigger()

    def pendAlgWithoutConfirmation(self):
        # An algorithm has been deployed
        self.algSent = True
        # No need to await for a thank you or check for goal manifestation:
        # self.trgTolerance.trigger()
        # Using this method instead of the default "pendAlg" is the same as
        # giving importance to the stick and not the carrot when learning
        # This method is mostly fitting workplace situations

    def mutateAlg(self, input):
        # Recommendation to mutate the algorithm? true/false
        if not self.algSent:
            return False  # No alg sent => no reason to mutate
        if input in self.goals:
            self.trgTolerance.reset()
            self.algSent = False
            return False
        # Goal manifested; the sent algorithm is good => no need to mutate the alg
        if input in self.defcon5:
            self.trgTolerance.reset()
            self.algSent = False
            return True
        # ^ Something bad happened probably because of the sent alg
        # Recommend alg mutation
        if input in self.defcons:
            self.algSent = False
            mutate = not self.trgTolerance.trigger()
            if mutate:
                self.trgTolerance.reset()
            return mutate
        # ^ Negative result, mutate the alg if this occurs too much
        return False

    def resetTolerance(self):
        # Use when you run code to change algorithms regardless of learnability
        self.trgTolerance.reset()


class AXNightRider:
    # night rider display simulation for LED lights count up than down
    def __init__(self, limit: int):
        self._mode: int = 0
        self._position: int = 0
        self._lim = 0
        if limit > 0:
            self._lim = limit
        self._direction = 1

    def setLim(self, lim: int):
        # number of LEDs
        self._lim = lim

    def setMode(self, mode: int):
        # room for more modes to be added
        if 10 > mode > -1:
            self._mode = mode

    def getPosition(self) -> int:
        match self._mode:
            case 0:
                self.mode0()
        return self._position

    def mode0(self):
        # clasic night rider display
        self._position += self._direction
        if self._direction < 1:
            if self._position < 1:
                self._position = 0
                self._direction = 1
        else:
            if self._position > self._lim - 1:
                self._position = self._lim
                self._direction = -1


class LGTypeConverter:
    @staticmethod
    def convertToInt(v1: str) -> int:
        temp: str = RegexUtil.extractEnumRegex(enumRegexGrimoire.integer, v1)
        if temp == "":
            return 0
        return int(temp)

    @staticmethod
    def convertToDouble(v1: str) -> float:
        temp: str = RegexUtil.extractEnumRegex(enumRegexGrimoire.double_num, v1)
        if temp == "":
            return 0.0
        return float(temp)

    @staticmethod
    def convertToFloat(v1: str) -> float:
        temp: str = RegexUtil.extractEnumRegex(enumRegexGrimoire.double_num, v1)
        if temp == "":
            return 0
        return float(temp)

    @staticmethod
    def convertToFloatV2(v1: str, precision: int) -> float:
        # precision: how many numbers after the .
        temp: str = RegexUtil.extractEnumRegex(enumRegexGrimoire.double_num, v1)
        if temp == "":
            return 0
        return round(float(temp), precision)


class AXPassword:
    """ code # to open the gate
     while gate is open, code can be changed with: code new_number"""

    def __init__(self):
        self._isOpen: bool = False
        self._maxAttempts: int = 3
        self._loginAttempts = self._maxAttempts
        self._code = 0

    def codeUpdate(self, ear: str) -> bool:
        # while the gate is toggled on, the password code can be changed
        if not self._isOpen:
            return False
        if ear.__contains__("code"):
            temp: str = RegexUtil.extractEnumRegex(enumRegexGrimoire.integer, ear)
            if not temp == "":
                # if not temp.isEmpty
                self._code = int(temp)
                return True
        return False

    def openGate(self, ear: str):
        if ear.__contains__("code") and self._loginAttempts > 0:
            tempCode: str = RegexUtil.extractEnumRegex(enumRegexGrimoire.integer, ear)
            if not tempCode == "":
                code_x: int = int(tempCode)
                if code_x == self._code:
                    self._loginAttempts = self._maxAttempts
                    self._isOpen = True
                else:
                    self._loginAttempts -= 1

    def isOpen(self):
        return self._isOpen

    def resetAttempts(self):
        # should happen once a day or hour to prevent hacking
        self._loginAttempts = self._maxAttempts

    def getLoginAttempts(self):
        # return remaining login attempts
        return self._loginAttempts

    def closeGate(self):
        self._isOpen = False

    def closeGateV2(self, ear: str):
        if ear.__contains__("close"):
            self._isOpen = False

    def setMaxAttempts(self, maximum: int):
        self._maxAttempts = maximum

    def getCode(self) -> int:
        if self._isOpen:
            return self._code
        return -1

    def randomizeCode(self, lim: int, minimumLim: int):
        # event feature
        self._code = DrawRnd().getSimpleRNDNum(lim) + minimumLim

    def getCodeEvent(self) -> int:
        # event feature
        # get the code during weekly/monthly event after it has been randomized
        return self._code


class ButtonEngager:
    """ detect if a button was pressed
     this class disables phisical button engagement while it remains being pressed"""

    def __init__(self):
        self._prev_state: bool = False

    def engage(self, btnState: bool) -> bool:
        # send true for pressed state
        if self._prev_state != btnState:
            self._prev_state = btnState
            if btnState:
                return True
        return False


class CombinatoricalUtils:
    # combo related algorithmic tools
    def __init__(self):
        self.result: list[str] = []

    def _generatePermutations(self, lists: list[list[str]], result: list[str], depth: int, current: str):
        # this function has a private modifier (the "_" makes it so)
        if depth == len(lists):
            result.append(current)
            return
        for i in range(0, len(lists) + 1):
            self._generatePermutations(lists, result, depth + 1, current + lists[depth][i])

    def generatePermutations(self, lists: list[list[str]]):
        # generate all permutations between all string lists in lists, which is a list of lists of strings
        self.result = []
        self._generatePermutations(lists, self.result, 0, "")

    def generatePermutations_V2(self, *lists: list[list[str]]):
        # this is the varargs vertion of this function
        # example method call: cu.generatePermutations(l1,l2)
        temp_lists: list[list[str]] = []
        for i in range(0, len(lists)):
            temp_lists.append(lists[i])
        self.result = []
        self._generatePermutations(temp_lists, self.result, 0, "")


class Cycler:
    # cycles through numbers limit to 0 non-stop
    def __init__(self, limit: int):
        self.limit: int = limit
        self._cycler: int = limit

    def cycleCount(self) -> int:
        self._cycler -= 1
        if self._cycler < 0:
            self._cycler = self.limit
        return self._cycler

    def reset(self):
        self._cycler = self.limit

    def setToZero(self):
        self._cycler = 0

    def sync(self, n: int):
        if n < -1 or n > self.limit:
            return
        self._cycler = n

    def getMode(self) -> int:
        return self._cycler


class DrawRnd:
    # draw a random element, then take said element out
    def __init__(self, *values: str):
        self.strings: LGFIFO = LGFIFO()
        self._stringsSource: list[str] = []
        for i in range(0, len(values)):
            self.strings.insert(values[i])
            self._stringsSource.append(values[i])

    def addElement(self, element: str):
        self.strings.insert(element)
        self._stringsSource.append(element)

    def drawAndRemove(self) -> str:
        if len(self.strings.queue) == 0:
            return ""
        temp: str = self.strings.getRNDElement()
        self.strings.removeItem(temp)
        return temp

    def drawAsIntegerAndRemove(self) -> int:
        temp: str = self.strings.getRNDElement()
        if temp is None:
            return 0
        self.strings.removeItem(temp)
        return LGTypeConverter.convertToInt(temp)

    @staticmethod
    def getSimpleRNDNum(lim: int) -> int:
        return random.randint(0, lim)

    def reset(self):
        self.strings.clear()
        for t in self._stringsSource:
            self.strings.insert(t)

    def isEmptied(self) -> bool:
        return self.strings.size() == 0

    def renewableDraw(self) -> str:
        if len(self.strings.queue) == 0:
            self.reset()
        temp: str = self.strings.getRNDElement()
        self.strings.removeItem(temp)
        return temp


class DrawRndDigits:
    # draw a random integer, then take said element out
    def __init__(self, *values: int):
        self.strings: LGFIFO = LGFIFO()
        self._stringsSource: list[int] = []
        for i in range(0, len(values)):
            self.strings.insert(values[i])
            self._stringsSource.append(values[i])

    def addElement(self, element: int):
        self.strings.insert(element)
        self._stringsSource.append(element)

    def drawAndRemove(self) -> int:
        temp: int = self.strings.getRNDElement()
        self.strings.removeItem(temp)
        return temp

    @staticmethod
    def getSimpleRNDNum(lim: int) -> int:
        return random.randint(0, lim)

    def reset(self):
        self.strings.clear()
        for t in self._stringsSource:
            self.strings.insert(t)

    def isEmptied(self) -> bool:
        return self.strings.size() == 0

    def resetIfEmpty(self):
        if len(self.strings.queue) == 0:
            self.reset()

    def containsElement(self, element: int) -> bool:
        return self._stringsSource.__contains__(element)

    def CurrentlyContainsElement(self, element: int) -> bool:
        return self.strings.contains(element)

    def removeItem(self, element: int):
        if self.strings.contains(element):
            self.strings.removeItem(element)


class Responder:
    # simple random response dispenser
    def __init__(self, *replies: str):
        self.responses: list[str] = []
        for response in replies:
            self.responses.append(response)

    def getAResponse(self) -> str:
        if not self.responses:
            return ""
        return self.responses[random.randint(0, len(self.responses) - 1)]

    def responsesContainsStr(self, item: str) -> bool:
        return self.responses.__contains__(item)

    def strContainsResponse(self, item: str) -> bool:
        for response in self.responses:
            if len(response) == 0:
                continue
            if item.__contains__(response):
                return True
        return False

    def addResponse(self, s1: str):
        self.responses.append(s1)


class EmoDetectorCurious(Responder):
    def __init__(self):
        super().__init__("why", "where", "when", "how", "who", "which", "whose", "what")

    def isCurious(self, item: str):
        return self.strContainsResponse(item)


class EmoDetectorHappy(Responder):
    def __init__(self):
        super().__init__("good", "awesome", "great", "happy")

    def isHappy(self, item: str):
        return self.strContainsResponse(item)


class EmoDetectorStressed(Responder):
    def __init__(self):
        super().__init__("ouch", "help", "dough")

    def isStressed(self, item: str):
        return self.strContainsResponse(item)


class ForcedLearn(UniqueItemSizeLimitedPriorityQueue):
    """remembers key inputs because they start with keyword
   // also can dispense key inputs"""

    def __init__(self, queLimit: int):
        super().__init__(queLimit)
        self.keyword: str = "say"

    # override
    def insert(self, data):
        temp: str = RegexUtil.afterWord(self.keyword, data)
        if not temp == "":
            super().insert(temp)


class EV3DaisyChainAndMode(TrGEV3):
    # this class connects several logic gates triggers together
    def __init__(self, *gates: TrGEV3):
        self._trgGates: list[TrGEV3] = []
        for gate in gates:
            self._trgGates.append(gate)

    # override
    def input(self, ear: str, skin: str, eye: str):
        for gate in self._trgGates:
            gate.input(ear, skin, eye)

    # override
    def reset(self):
        for gate in self._trgGates:
            gate.reset()

    # override
    def trigger(self) -> bool:
        for gate in self._trgGates:
            if not gate.trigger():
                # not all gates return true
                return False
            # all gates return true
            return True


class EV3DaisyChainOrMode(TrGEV3):
    # this class connects several logic gates triggers together
    def __init__(self, *gates: TrGEV3):
        self._trgGates: list[TrGEV3] = []
        for gate in gates:
            self._trgGates.append(gate)

    # override
    def input(self, ear: str, skin: str, eye: str):
        for gate in self._trgGates:
            gate.input(ear, skin, eye)

    # override
    def reset(self):
        for gate in self._trgGates:
            gate.reset()

    # override
    def trigger(self) -> bool:
        for gate in self._trgGates:
            if gate.trigger():
                # at least 1 gate is engaged
                return True
            # all gates are not engaged
            return False


class Map:
    def __init__(self):
        self._pointDescription: dict[str, str] = {}
        self._descriptionPoint: dict[str, str] = {}
        self._currentPosition: LGPointInt = LGPointInt(0, 0)

    def reset(self):
        # sleep location is considered (0,0) location
        self._currentPosition.reset()

    def moveBy(self, x: int, y: int):
        # shift current position
        self._currentPosition.shift(x, y)

    def moveTo(self, location: str):
        # use this when the AI is returning home
        if self._descriptionPoint.__contains__(location):
            value: str = self._descriptionPoint[location]
            p1 = RegexUtil.pointRegex(value)
            self._currentPosition.x = p1.x
            self._currentPosition.y = p1.y

    def write(self, description):
        # location name or item description will be
        # saved on the map on the current point position
        pointStr: str = self._currentPosition.__repr__()
        self._pointDescription[pointStr] = description
        self._descriptionPoint[description] = pointStr

    def read(self) -> str:
        # read place description
        temp: str = self._currentPosition.__repr__()
        if not self._pointDescription.__contains__(temp):
            return "null"
        return self._pointDescription[temp]

    def readPoint(self, p1: LGPointInt) -> str:
        # used for predition of upcoming locations
        # returns: what is the location name at point
        temp: str = p1.__repr__()
        if not self._pointDescription.__contains__(temp):
            return "null"
        return self._pointDescription[temp]

    def locationCoordinate(self, description):
        # get location coordinate
        if not self._descriptionPoint.__contains__(description):
            return "null"
        return self._descriptionPoint[description]


class Catche:
    # limited sized dictionary
    def __init__(self, size: int):
        super().__init__()
        self._limit: int = size
        self._keys: UniqueItemSizeLimitedPriorityQueue = UniqueItemSizeLimitedPriorityQueue(size)
        self._d1: dict[str, str] = {}

    def insert(self, key: str, value: str):
        # update
        if self._d1.__contains__(key):
            self._d1[key] = value
            return
        # insert:
        if self._keys.size() == self._limit:
            temp = self._keys.peak()
            del self._d1[temp]
        self._keys.insert(key)
        self._d1[key] = value

    def clear(self):
        self._keys.clear()
        self._d1.clear()

    def read(self, key: str) -> str:
        if not self._d1.__contains__(key):
            return "null"
        return self._d1[key]


class SpiderSense:
    # enables event prediction
    def __init__(self, lim: int):
        super().__init__()
        self._spiderSense: bool = False
        self._events: UniqueItemSizeLimitedPriorityQueue = UniqueItemSizeLimitedPriorityQueue(lim)
        self._alerts: UniqueItemSizeLimitedPriorityQueue = UniqueItemSizeLimitedPriorityQueue(lim)
        self._prev: str = "null"

    def addEvent(self, event: str):
        # builder pattern
        self._events.insert(event)
        return self

    """input param  can be run through an input filter prior to this function
     weather related data (sky state) only for example for weather events predictions"""

    """side note:
     use separate spider sense for data learned by hear say in contrast to actual experience
     as well as lies (false predictions)"""

    def learn(self, in1: str):
        if len(in1) == 0:
            return
        # simple prediction of an event from the events que :
        if self._alerts.contains(in1):
            self._spiderSense = True
            return
        # event has occured, remember what lead to it
        if self._events.contains(in1):
            self._alerts.insert(self._prev)
            return
        # nothing happend
        self._prev = in1

    def getSpiderSense(self) -> bool:
        # spider sense is tingling? event predicted?
        temp: bool = self._spiderSense
        self._spiderSense = False
        return temp

    def getAlertsShallowCopy(self):
        # return shallow copy of alerts list
        return self._alerts.queue

    def getAlertsClone(self) -> list[str]:
        # return deep copy of alerts list
        l_temp: list[str] = []
        for item in self._alerts.queue:
            l_temp.append(item)
        return l_temp

    def clearAlerts(self):
        """this can for example prevent war, because say once a month or a year you stop
         being on alert against a rival"""
        self._alerts.clear()

    def eventTriggered(self, in1:str) -> bool:
        return self._events.contains(in1)


class TrgMinute(TrGEV3):
    # trigger true at minute once per hour
    def __init__(self):
        super().__init__()
        self._hour1: int = -1
        self._minute: int = random.randint(0, 60)

    def setMinute(self, minute):
        if -1 < minute < 61:
            self._minute = minute

    # override
    def trigger(self) -> bool:
        temp_hour: int = TimeUtils.getHoursAsInt()
        if temp_hour != self._hour1:
            if TimeUtils.getMinutesAsInt() == self._minute:
                self._hour1 = temp_hour
                return True
        return False

    # override
    def reset(self):
        self._hour1 = -1


class TrgParrot:
    # simulates a parrot chirp trigger mechanism
    # as such this trigger is off at night
    # in essence this trigger says: I am here, are you here? good.
    def __init__(self, limit: int):
        super().__init__()
        temp_lim: int = 3
        if limit > 0:
            temp_lim = limit
        self._tolerance: TrgTolerance = TrgTolerance(temp_lim)
        self._silencer: Responder = Responder("ok", "okay", "stop", "shut up", "quiet")

    def trigger(self, standBy: bool, ear: str) -> bool:
        """relies on the Kokoro standby boolean
         no input or output for a set amount of time results with a true
         and replenishing the trigger."""
        if TimeUtils.isNight():
            # is it night? I will be quite
            return False
        # you want the bird to shut up?
        if self._silencer.responsesContainsStr(ear):
            self._tolerance.disable()
            return False
        # no input or output for a while?
        if standBy:
            # I will chirp!
            self._tolerance.reset()
            return True
        # we are handshaking?
        if not ear == "":
            # I will reply chirp till it grows old for me (a set amount of times till reset)
            if self._tolerance.trigger():
                return True
        return False


class TrgSnooze(TrGEV3):
    # this boolean gate will return true per minute interval
    # max repeats times.
    def __init__(self, maxRepeats: int):
        super().__init__()
        self._repeats: int = 0
        self._maxRepeats: int = maxRepeats
        self._snooze: bool = True
        self._snoozeInterval: int = 5

    def setSnoozeInterval(self, snoozeInterval):
        if 1 < snoozeInterval < 11:
            self._snoozeInterval = snoozeInterval

    # override
    def reset(self):
        # refill trigger
        # engage this code when an alarm clock was engaged to enable snoozing
        self._repeats = self._maxRepeats

    def setMaxrepeats(self, max_repeats: int):
        self._maxRepeats = max_repeats
        self.reset()

    # override
    def trigger(self) -> bool:
        # trigger a snooze alarm?
        minutes: int = TimeUtils.getMinutesAsInt()
        # non interval minute case:
        if minutes % self._snoozeInterval != 0:
            self._snooze = True
            return False
        # 1 time activation per snooze interval minute:
        if self._repeats > 0 and self._snooze:
            self._snooze = False
            self._repeats -= 1
            return True
        return False

    def disable(self):
        # engage this method to stop the snoozing
        self._repeats = 0


class TrgTime:
    def __init__(self):
        super().__init__()
        self._t = "null"
        self._alarm: bool = True

    def setTime(self, v1: str):
        if v1.startswith("0"):
            v1 = v1[1:]
        self._t = RegexUtil.extractEnumRegex(enumRegexGrimoire.simpleTimeStamp, v1)

    def alarm(self) -> bool:
        now: str = TimeUtils.getCurrentTimeStamp()
        if self._alarm:
            if now == self._t:
                self._alarm = False
                return True
        if now != self._t:
            self._alarm = True
        return False


class AXGamification:
    # this auxiliary module can add fun to tasks, skills, and abilities simply by
    # tracking their usage, and maximum use count.
    def __init__(self):
        self._counter: int = 0
        self._max: int = 0

    def getCounter(self) -> int:
        return self._counter

    def getMax(self) -> int:
        return self._max

    def resetCount(self):
        self._counter = 0

    def resetAll(self):
        self._counter = 0
        self._max = 0

    def increment(self):
        self._counter += 1
        if self._counter > self._max:
            self._max = self._counter

    def incrementBy(self, n: int):
        self._counter += n
        if self._counter > self._max:
            self._max = self._counter

    def reward(self, cost: int) -> bool:
        # game grind points used for rewards
        # consumables, items or upgrades this makes games fun
        if cost < self._counter:
            self._counter -= cost
            return True
        return False

    def surplus(self, cost: int) -> bool:
        if cost < self._counter:
            return True
        return False


class AXKeyValuePair:
    def __init__(self, key: str = "", value: str = "") -> None:
        self.key: str = key
        self.value: str = value

    def get_key(self) -> str:
        return self.key

    def set_key(self, key: str) -> None:
        self.key = key

    def get_value(self) -> str:
        return self.value

    def set_value(self, value: str) -> None:
        self.value = value

    def __str__(self) -> str:
        return f"{self.key};{self.value}"


class TODOListManager:
    """manages to do tasks.
    q1 tasks are mentioned once, and forgotten
    backup tasks are the memory of recently mentioned tasks"""

    def __init__(self, todoLim: int):
        self._q1: UniqueItemSizeLimitedPriorityQueue = UniqueItemSizeLimitedPriorityQueue(todoLim)
        self._backup: UniqueItemSizeLimitedPriorityQueue = UniqueItemSizeLimitedPriorityQueue(todoLim)

    def addTask(self, e1: str):
        self._q1.insert(e1)

    def getTask(self) -> str:
        temp: str = str(self._q1.poll())
        if not temp == "":
            self._backup.insert(temp)
        return temp

    def getOldTask(self):
        # task graveyard (tasks you've tackled already)
        return self._backup.getRNDElement()

    def clearAllTasks(self):
        self._q1.clear()
        self._backup.clear()

    def clearTask(self, task: str):
        self._q1.removeItem(task)
        self._backup.removeItem(task)

    def containsTask(self, task: str) -> bool:
        return self._backup.contains(task)


class AXNeuroSama:
    def __init__(self, rate: int):
        # the higher the rate the less likely to decorate outputs
        self._rate: int = rate
        self._nyaa: Responder = Responder(" heart", " heart", " wink", " heart heart heart")
        self._rnd: DrawRnd = DrawRnd()

    def decorate(self, output: str):
        if output == "":
            return output
        if self._rnd.getSimpleRNDNum(self._rate) == 0:
            return output + self._nyaa.getAResponse()
        return output


class AXLHousing:
    def decorate(self, str1: str) -> str:
        # override me
        return ""


class AXLNeuroSama(AXLHousing):
    def __init__(self):
        super().__init__()
        self._nyaa: AXNeuroSama = AXNeuroSama(3)

    def decorate(self, str1: str) -> str:
        return self._nyaa.decorate(str1)


class AXLHub:
    # hubs many reply decorators, language translators, encriptors and other string modifiers
    # decorate(str) to decorate string using the active string decorator
    def __init__(self, *nyaa: AXLHousing):
        self._nyaa: list[AXLHousing] = []
        size: int = len(nyaa)
        for i in range(0, size):
            self._nyaa.append(nyaa[i])
        self._cycler: Cycler = Cycler(size - 1)
        self._cycler.setToZero()
        self._drawRnd: DrawRnd = DrawRnd()
        self._activeNyaa = 0

    def decorate(self, str1: str) -> str:
        return self._nyaa[self._activeNyaa].decorate(str1)

    def cycleDecoration(self):
        self._activeNyaa = self._cycler.cycleCount()

    def randomizeDecoration(self):
        self._activeNyaa = self._drawRnd.getSimpleRNDNum(len(self._nyaa))

    def modeDecoration(self, mode: int):
        if mode < -1 or mode >= len(self._nyaa):
            return
        self._activeNyaa = mode


class OutputDripper(Cycler):
    # drips true once every limit times
    # shushes the waifubot enough time to hear a reply from user
    def __init__(self, limit: int):
        # set limit to 1 for on off effect
        super().__init__(limit)

    def drip(self) -> bool:
        return self.cycleCount() == 0

    def setLimit(self, lim: int):
        self.limit = lim


class Strategy:
    def __init__(self, allStrategies: UniqueResponder, strategiesLim: int):
        # bank of all strategies. out of this pool active strategies are pulled
        self._allStrategies: UniqueResponder = allStrategies
        self._strategiesLim = strategiesLim
        # active strategic options
        self._activeStrategy: UniqueItemSizeLimitedPriorityQueue = UniqueItemSizeLimitedPriorityQueue(strategiesLim)
        for i in range(0, self._strategiesLim):
            self._activeStrategy.insert(self._allStrategies.getAResponse())

    def evolveStrategies(self):
        for i in range(0, self._strategiesLim):
            self._activeStrategy.insert(self._allStrategies.getAResponse())

    def getStrategy(self) -> str:
        return self._activeStrategy.getRNDElement()


class PersistentQuestion:
    def __init__(self):
        self._isActive: bool = False
        self._mode = "yes"  # answer as context for question phrasing
        self.dic: dict[str, DrawRnd] = {}
        self._outputDripper: OutputDripper = OutputDripper(1)
        self.loggedAnswer: str = ""  # only used in log() which replaces process()

    def addPath(self, answer: str, nags: DrawRnd):
        self.dic[answer] = nags

    def activate(self):
        self._isActive = True

    def isActive(self):
        return self._isActive

    def deActivate(self):
        self._isActive = False
        self.dic[self._mode].reset()

    def process(self, inp: str) -> str:
        # got answer?
        if inp in self.dic:
            self._mode = inp
            self._isActive = False
            self.dic[self._mode].reset()
            return "okay"  # can extend code to reply key, rnd finalizer
        # nag for answer
        if not self._outputDripper.drip():
            return ""
        result: str = self.dic[self._mode].drawAndRemove()
        if not result == "":
            return result
        self.dic[self._mode].reset()
        self._isActive = False
        return "i see"

    def log(self, inp: str) -> str:
        # got answer?
        if inp in self.dic:
            self._mode = inp
            self.loggedAnswer = inp
            self._isActive = False
            self.dic[self._mode].reset()
            return "okay"  # can extend code to reply key, rnd finalizer
        if not inp == "":
            self.loggedAnswer = inp
            self._isActive = False
            self.dic[self._mode].reset()
            return "okay"  # can extend code to reply key, rnd finalizer
        # nag for answer
        if not self._outputDripper.drip():
            return ""
        result: str = self.dic[self._mode].drawAndRemove()
        if not result == "":
            return result
        self.dic[self._mode].reset()
        self._isActive = False
        return "i see"

    def getMode(self):
        return self._mode

    def setMode(self, mode: str):
        if mode in self.dic:
            self._mode = mode

    def setPause(self, pause: int):
        # set pause between question to wait for answer
        self._outputDripper.setLimit(pause)


class Differ:
    def __init__(self):
        self._powerLevel = 90
        self._difference = 0

    def getPowerLevel(self) -> int:
        return self._powerLevel

    def getPowerLVDifference(self) -> int:
        return self._difference

    def clearPowerLVDifference(self):
        self._difference = 0

    def samplePowerLV(self, pl: int):
        # pl is the current power level
        self._difference = pl - self._powerLevel
        self._powerLevel = pl


# command auxiliary modules collection

class AXMachineCode:
    # common code lines used in machine code to declutter machine code
    # also simplified extensions for common dictionary actions
    def __init__(self):
        self.dic: dict[str, int] = {}

    def addKeyValuePair(self, key: str, value: int) -> AXMachineCode:
        self.dic[key] = value
        return self

    def getMachineCodeFor(self, key: str) -> int:
        # dictionary get or default
        if key not in self.dic:
            return -1
        return self.dic[key]


class AXInputWaiter:
    # wait for any input
    def __init__(self, tolerance: int):
        self._trgTolerance: TrgTolerance = TrgTolerance(tolerance)
        self._trgTolerance.reset()

    def reset(self):
        self._trgTolerance.reset()

    def wait(self, s1: str) -> False:
        # return true till any input detected or till x times of no input detection
        if not s1 == "":
            self._trgTolerance.disable()
            return False
        return self._trgTolerance.trigger()

    def setWait(self, timesToWait: int):
        self._trgTolerance.setMaxRepeats(timesToWait)


class AXCmdBreaker:
    def __init__(self, conjuration: str):
        self.conjuration: str = conjuration

    def extractCmdParam(self, s1: str) -> str:
        if self.conjuration in s1:
            return s1.replace(self.conjuration, "").strip()
        return ""


class AXContextCmd:
    # engage on commands
    # when commands are engaged, context commans can also engage
    def __init__(self):
        self.commands: UniqueItemSizeLimitedPriorityQueue = UniqueItemSizeLimitedPriorityQueue(5)
        self.contextCommands: UniqueItemSizeLimitedPriorityQueue = UniqueItemSizeLimitedPriorityQueue(5)
        self.trgTolerance: bool = False

    def engageCommand(self, s1: str) -> bool:
        if len(s1) == 0:
            return False
        # active context
        if self.contextCommands.contains(s1):
            self.trgTolerance = True
            return True
        # exit context:
        if self.trgTolerance and not self.commands.contains(s1):
            self.trgTolerance = False
            return False
        return self.trgTolerance

    def disable(self):
        # context commands are disabled till next engagement with a command
        self.trgTolerance = False


# command auxiliary modules collection end
class DiSysOut(Skill):
    # Override
    def input(self, ear: str, skin: str, eye: str):
        if not ear == "" and not ear.__contains__("#"):
            print(ear)


class AXLSpeechModifier(AXLHousing):
    def __init__(self, dic: [str, str]):
        self.dic = dic

    # Override
    def decorate(self, str1: str) -> str:
        result: str = ""
        words = str1.split()
        for item in words:
            result = result + " " + self.dic.get(item, item)
        return result.strip()


class TimeAccumulator:
    # accumulator ++ each tick minutes interval
    def __init__(self, tick: int):
        # accumulation ticker
        self._timeGate: TimeGate = TimeGate(tick)
        self._accumulator: int = 0
        self._timeGate.openForPauseMinutes()

    def setTick(self, tick: int):
        self._timeGate.setPause(tick)

    def getAccumulator(self) -> int:
        return self._accumulator

    def reset(self):
        self._accumulator = 0

    def tick(self):
        if self._timeGate.isClosed():
            self._timeGate.openForPauseMinutes()
            self._accumulator += 1

    def decAccumulator(self):
        if self._accumulator > 0:
            self._accumulator -= 1


class Responder1Word:
    # learns 1 word input
    # outputs learned recent words
    def __init__(self):
        self.q: UniqueItemSizeLimitedPriorityQueue = UniqueItemSizeLimitedPriorityQueue(5)
        self.q.insert("chi")
        self.q.insert("gaga")
        self.q.insert("gugu")
        self.q.insert("baby")

    def listen(self, ear: str):
        if not (ear.__contains__(" ") or ear == ""):
            self.q.insert(ear)

    def getAResponse(self) -> str:
        return self.q.getRNDElement()

    def contains(self, ear: str) -> bool:
        return self.q.contains(ear)


class TrgEveryNMinutes(TrGEV3):
    # trigger returns true every minutes interval, post start time
    def __init__(self, startTime: str, minutes: int):
        self._minutes: int = minutes  # minute interval between triggerings
        self._timeStamp = startTime
        self._trgTime: TrgTime = TrgTime()
        self._trgTime.setTime(startTime)

    def setMinutes(self, minutes: int):
        if minutes > -1:
            self._minutes = minutes

    # override
    def trigger(self) -> bool:
        if self._trgTime.alarm():
            self._timeStamp = TimeUtils.getFutureInXMin(self._minutes)
            self._trgTime.setTime(self._timeStamp)
            return True
        return False

    # override
    def reset(self):
        self._timeStamp = TimeUtils.getCurrentTimeStamp()


class Cron(TrGEV3):
    # triggers true, limit times, after initial time, and every minutes interval
    # counter resets at initial time, assuming trigger method was run
    def __init__(self, startTime: str, minutes: int, limit: int):
        self._minutes: int = minutes  # minute interval between triggerings
        self._timeStamp = startTime
        self._initislTimeStamp = startTime
        self._trgTime: TrgTime = TrgTime()
        self._trgTime.setTime(startTime)
        self._counter: int = 0
        self._limit: int = limit
        if limit < 1:
            self._limit = 1

    def setMinutes(self, minutes: int):
        if minutes > -1:
            self._minutes = minutes

    def getLimit(self) -> int:
        return self._limit

    def setLimit(self, limit: int):
        if limit > 0:
            self._limit = limit

    def getCounter(self) -> int:
        return self._counter

    # override
    def trigger(self) -> bool:
        # delete counter = 0 if you don't want the trigger to work the next day
        if self._counter == self._limit:
            self._trgTime.setTime(self._initislTimeStamp)
            self._counter = 0
            return False
        if self._trgTime.alarm():
            self._timeStamp = TimeUtils.getFutureInXMin(self._minutes)
            self._trgTime.setTime(self._timeStamp)
            self._counter += 1
            return True
        return False

    def triggerWithoutRenewal(self) -> bool:
        if self._counter == self._limit:
            self._trgTime.setTime(self._initislTimeStamp)
            return False
        if self._trgTime.alarm():
            self._timeStamp = TimeUtils.getFutureInXMin(self._minutes)
            self._trgTime.setTime(self._timeStamp)
            self._counter += 1
            return True
        return False

    # override
    def reset(self):
        # manual trigger reset
        self._counter = 0

    def setStartTime(self, t1: str):
        self._initislTimeStamp = t1
        self._timeStamp = t1
        self._trgTime.setTime(t1)
        self._counter = 0

    def turnOff(self):
        self._counter = self._limit


''' PRIORITYQUEUE CLASS '''


# A simple implementation of Priority Queue
# using Queue.
class PriorityQueueVer0(object):
    def __init__(self):
        self.queue = []

    def __str__(self):
        return ' '.join([str(i) for i in self.queue])

    # for checking if the queue is empty
    def isEmpty(self):
        return len(self.queue) == 0

    # for inserting an element in the queue
    def insert(self, data):
        self.queue.append(data)

    # for popping an element based on Priority
    def poll(self) -> object:
        if not len(self.queue) == 0:
            result0 = self.queue[0]
            del self.queue[0]
            return result0
        return None


# class AXStrOrDefault:
def getOrDefault(str1: str, default1: str) -> str:
    return default1 if (str1 == "") else str1


class AXStringSplit:
    # may be used to prepare data before saving or after loading
    # the advantage is less data fields. for example: {skills: s1_s2_s3}
    def __init__(self):
        self._saparator: str = "_"

    def setSaparator(self, saparator: str):
        self._saparator = saparator

    def split(self, str1: str) -> list[str]:
        return str1.split(self._saparator)

    def stringBuilder(self, l1: list[str]) -> str:
        sTemp = self._saparator
        return sTemp.join(l1)


class RefreshQ(UniqueItemSizeLimitedPriorityQueue):
    def __init__(self, limit: int):
        super().__init__(limit)

    def removeItem(self, item: str):
        super().getAsList().remove(item)

    def insert(self, data):
        # FILO 1st in last out
        if super().contains(data):
            self.removeItem(data)
        super().insert(data)

    def stuff(self, data):
        # FILO 1st in last out
        if super().size() == self._limit:
            super().poll()
        self.queue.append(data)


class AXTimeContextResponder:
    # output reply based on the part of day as context
    def __init__(self):
        self.morning: Responder = Responder()
        self.afternoon: Responder = Responder()
        self.evening: Responder = Responder()
        self.night: Responder = Responder()
        self._responders: dict[str, Responder] = {"morning": self.morning, "afternoon": self.afternoon,
                                                  "evening": self.evening, "night": self.night}

    def respond(self) -> str:
        return self._responders[TimeUtils.partOfDay()].getAResponse()


class PercentDripper:
    def __init__(self):
        self.__dr: DrawRnd = DrawRnd()
        self.__limis: int = 35

    def setLimit(self, limis):
        self.__limis = limis

    def drip(self) -> bool:
        return self.__dr.getSimpleRNDNum(100) < self.__limis

    def dripPlus(self, plus: int) -> bool:
        return self.__dr.getSimpleRNDNum(100) < self.__limis + plus


class AXNPC:
    def __init__(self, replyStackLim: int, outputChance: int):
        self.responder: RefreshQ = RefreshQ(replyStackLim)
        self.dripper = PercentDripper()
        if 0 < outputChance < 101:
            self.dripper.setLimit(outputChance)
        self.cmdBreaker: AXCmdBreaker = AXCmdBreaker("say")

    def respond(self) -> str:
        if self.dripper.drip():
            return self.responder.getRNDElement()
        return ""

    def respondPlus(self, plus) -> str:
        if self.dripper.dripPlus(plus):
            return self.responder.getRNDElement()
        return ""

    def learn(self, ear: str) -> bool:
        # say hello there : hello there is learned
        temp: str = self.cmdBreaker.extractCmdParam(ear)
        if len(temp) == 0:
            return False
        self.responder.insert(temp)
        return True

    def strRespond(self, ear: str) -> str:
        # respond if ear contains a learned input
        if len(ear) == 0:
            return ""
        if self.dripper.drip() and self.responder.strContainsResponse(ear):
            return self.responder.getRNDElement()
        return ""

    def forceRespond(self) -> str:
        return self.responder.getRNDElement()

    def setConjuration(self, conjuration: str):
        self.cmdBreaker.conjuration = conjuration

    def forceLearn(self, ear):
        self.responder.insert(ear)


class ChatBot:
    """
chatbot = ChatBot(5)

chatbot.addParam("name", "jinpachi")
chatbot.addParam("name", "sakura")
chatbot.addParam("verb", "eat")
chatbot.addParam("verb", "code")

chatbot.addSentence("i can verb #")

chatbot.learnParam("ryu is a name")
chatbot.learnParam("ken is a name")
chatbot.learnParam("drink is a verb")
chatbot.learnParam("rest is a verb")

chatbot.learnV2("hello ryu i like to code")
chatbot.learnV2("greetings ken")
for i in range(1, 10):
    print(chatbot.talk())
    print(chatbot.getALoggedParam())
"""

    def __init__(self, logParamLim):
        self.sentences: RefreshQ = RefreshQ(5)
        self.wordToList: dict[str, RefreshQ] = {}
        self.rand = random.Random()
        self.allParamRef: dict[str, str] = {}
        self.paramLim: int = 5
        self.loggedParams: RefreshQ = RefreshQ(5)
        self.conjuration: str = "is a"
        self.loggedParams.setLimit(logParamLim)

    def setConjuration(self, conjuration):
        self.conjuration = conjuration

    def setSentencesLim(self, lim):
        self.sentences.setLimit(lim)

    def setParamLim(self, paramLim):
        self.paramLim = paramLim

    def getWordToList(self):
        return self.wordToList

    def talk(self):
        result = self.sentences.getRNDElement()
        return self.clearRecursion(result)

    def clearRecursion(self, result):
        params = RegexUtil.extractAllRegexes("(\\w+)(?= #)", result)
        for strI in params:
            temp = self.wordToList.get(strI)
            s1 = temp.getRNDElement()
            result = result.replace(strI + " #", s1)
        if "#" not in result:
            return result
        else:
            return self.clearRecursion(result)

    def addParam(self, category, value):
        if category not in self.wordToList:
            temp = RefreshQ(self.paramLim)
            self.wordToList[category] = temp
        self.wordToList[category].insert(value)
        self.allParamRef[value] = category

    def addKeyValueParam(self, kv):
        if kv.getKey() not in self.wordToList:
            temp = RefreshQ(self.paramLim)
            self.wordToList[kv.getKey()] = temp
        self.wordToList[kv.getKey()].insert(kv.getValue())
        self.allParamRef[kv.getValue()] = kv.getKey()

    def addSubject(self, category, value):
        if category not in self.wordToList:
            temp = RefreshQ(1)
            self.wordToList[category] = temp
        self.wordToList[category].insert(value)
        self.allParamRef[value] = category

    def addSentence(self, sentence):
        self.sentences.insert(sentence)

    def learn(self, s1):
        s1 = " " + s1
        for key in self.wordToList.keys():
            s1 = s1.replace(" " + key, " {} #".format(key))
        self.sentences.insert(s1.strip())

    def learnV2(self, s1) -> bool:
        # returns true if sentence has params
        # meaning sentence has been learnt
        OGStr: str = s1
        s1 = " " + s1
        for key in self.allParamRef.keys():
            s1 = s1.replace(" " + key, " {} #".format(self.allParamRef[key]))
        s1 = s1.strip()
        if not OGStr == s1:
            self.sentences.insert(s1)
            return True
        return False

    def learnParam(self, s1):
        if self.conjuration not in s1:
            return
        category = RegexUtil.afterWord(self.conjuration, s1)
        if category not in self.wordToList:
            return
        param = s1.replace("{} {}".format(self.conjuration, category), "").strip()
        self.wordToList[category].insert(param)
        self.allParamRef[param] = category
        self.loggedParams.insert(s1)

    def addParamFromAXPrompt(self, kv):
        if kv.getKey() not in self.wordToList:
            return
        self.wordToList[kv.getKey()].insert(kv.getValue())
        self.allParamRef[kv.getValue()] = kv.getKey()

    def addRefreshQ(self, category, q1: RefreshQ):
        self.wordToList[category] = q1

    def getALoggedParam(self):
        return self.loggedParams.getRNDElement()


class Prompt:
    def __init__(self):
        self.kv: AXKeyValuePair = AXKeyValuePair()
        self.prompt: str = ""
        self.regex: str = ""
        self.kv.key = "default"

    def getPrompt(self):
        return self.prompt

    def setPrompt(self, prompt):
        self.prompt = prompt

    def process(self, in1) -> bool:
        self.kv.value = RegexUtil.extractRegex(self.regex, in1)
        return self.kv.value == ""

    def getKv(self) -> AXKeyValuePair:
        return self.kv

    def setRegex(self, regex):
        self.regex = regex


class AXPrompt:
    def __init__(self):
        self.isActive: bool = False
        self.index = 0
        self.prompts: list[Prompt] = []
        self.kv: AXKeyValuePair = AXKeyValuePair()

    def addPrompt(self, p1):
        self.prompts.append(p1)

    def getPrompt(self) -> str:
        if len(self.prompts) == 0:
            return ""
        # return self.prompts[self.index].getPrompt()
        return self.prompts[self.index].getPrompt()

    def process(self, in1):
        if len(self.prompts) == 0 or not self.isActive:
            return
        b1 = self.prompts[self.index].process(in1)
        if not b1:
            self.kv = self.prompts[self.index].getKv()
            self.index += 1
        if self.index == len(self.prompts):
            self.isActive = False

    def getActive(self) -> bool:
        return self.isActive

    def getKv(self):
        if self.kv is None:
            return None
        temp: AXKeyValuePair = AXKeyValuePair()
        temp.key = self.kv.key
        temp.value = self.kv.value
        self.kv = None
        return temp

    def activate(self):
        self.isActive = True
        self.index = 0

    def deactivate(self):
        self.isActive = False
        self.index = 0


class AnnoyedQ:

    def __init__(self, queLim: int):
        self._q1: RefreshQ = RefreshQ(queLim)
        self._q2: RefreshQ = RefreshQ(queLim)
        self._stuffedQue: RefreshQ = RefreshQ(queLim)

    def learn(self, ear: str):
        if self._q1.contains(ear):
            self._q2.insert(ear)
            self._stuffedQue.stuff(ear)
            return
        self._q1.insert(ear)

    def isAnnoyed(self, ear: str) -> bool:
        return self._q2.strContainsResponse(ear)

    def reset(self):
        # Insert unique throwaway strings to reset the state
        for i in range(self._q1.getLimit()):
            self.learn(f"throwaway_string_{i}")

    def AnnoyedLevel(self, ear: str, level: int) -> bool:
        return self._stuffedQue.queue.count(ear) > level


class AXNPC2(AXNPC):

    def __init__(self, replyStockLim: int, outputChance: int):
        super().__init__(replyStockLim, outputChance)
        self.annoyedQue: AnnoyedQ = AnnoyedQ(5)

    def strLearn(self, ear: str) -> bool:
        # learns inputs containing strings that are repeatedly used by others
        self.annoyedQue.learn(ear)
        if self.annoyedQue.isAnnoyed(ear):
            self.responder.insert(ear)
            return True
        return False


class TrgArgue:
    def __init__(self):
        self.commands: UniqueItemSizeLimitedPriorityQueue = UniqueItemSizeLimitedPriorityQueue(5)
        self.contextCommands: UniqueItemSizeLimitedPriorityQueue = UniqueItemSizeLimitedPriorityQueue(5)
        self.trgTolerance: bool = False
        self._counter: int = 0  # count argues/requests made in succession
        # (breaking point of argument can be established (argue till counter == N))

    def getCounter(self) -> int:
        return self._counter

    def engageCommand(self, s1: str) -> int:
        # 0-> no engagement
        # 1-> engaged boolean gate (request made)
        # 2-> engaged argument : consecutive request made (request in succession after a previous request)
        if len(s1) == 0:
            return 0
        if self.contextCommands.contains(s1):
            if self.trgTolerance:
                self._counter += 1
            self.trgTolerance = True
            return 1
        if self.trgTolerance:
            if not self.commands.strContainsResponse(s1):
                self.trgTolerance = False
                self._counter = 0
                return 0
            else:
                self._counter += 1
                return 2
        return 0

    def disable(self):
        # context commands are disabled till next engagement with a command
        self.trgTolerance = False


class Magic8Ball:
    def __init__(self):
        self.__questions: Responder = Responder()
        self.__answers: Responder = Responder()
        # answers:
        # Affirmative answers
        self.__answers.addResponse("It is certain")
        self.__answers.addResponse("It is decidedly so")
        self.__answers.addResponse("Without a doubt")
        self.__answers.addResponse("Yes definitely")
        self.__answers.addResponse("You may rely on it")
        self.__answers.addResponse("As I see it, yes")
        self.__answers.addResponse("Most likely")
        self.__answers.addResponse("Outlook good")
        self.__answers.addResponse("Yes")
        self.__answers.addResponse("Signs point to yes")
        # Non – Committal answers
        self.__answers.addResponse("Reply hazy, try again")
        self.__answers.addResponse("Ask again later")
        self.__answers.addResponse("Better not tell you now")
        self.__answers.addResponse("Cannot predict now")
        self.__answers.addResponse("Concentrate and ask again")
        # Negative answers
        self.__answers.addResponse("Don’t count on it")
        self.__answers.addResponse("My reply is no")
        self.__answers.addResponse("My sources say no")
        self.__answers.addResponse("Outlook not so good")
        self.__answers.addResponse("Very doubtful")
        # questions:
        self.__questions = Responder("will i", "can i expect", "should i", "is it a good idea",
                                     "will it be a good idea for me to", "is it possible", "future hold",
                                     "will there be")

    def setQuestions(self, q: Responder):
        self.__questions = q

    def setAnswers(self, answers: Responder):
        self.__answers = answers

    def getQuestions(self) -> Responder:
        return self.__questions

    def getAnswers(self) -> Responder:
        return self.__answers

    def engage(self, ear: str) -> bool:
        if len(ear) == 0:
            return False
        if self.__questions.strContainsResponse(ear):
            return True
        return False

    def reply(self) -> str:
        return self.__answers.getAResponse()


class AXShoutOut:
    def __init__(self):
        self.__isActive: bool = False
        self.handshake: Responder = Responder()

    def activate(self):
        # make engage-able
        self.__isActive = True

    def engage(self, ear: str) -> bool:
        if len(ear) == 0:
            return False
        if self.__isActive:
            if self.handshake.strContainsResponse(ear):
                self.__isActive = False
                return True  # shout out was replied!

        # unrelated reply to shout out, shout out context is outdated
        self.__isActive = False
        return False


class AXHandshake:
    """
    example use:
            if self.__handshake.engage(ear): # ear reply like: what do you want?/yes
            self.setVerbatimAlg(4, "now I know you are here")
            return
        if self.__handshake.trigger():
            self.setVerbatimAlg(4, self.__handshake.getUser_name()) # user, user!
    """

    def __init__(self):
        self.__trgTime: TrgTime = TrgTime()
        self.__trgTolerance: TrgTolerance = TrgTolerance(10)
        self.__shoutout: AXShoutOut = AXShoutOut()
        # default handshakes (valid reply to shout out)
        self.__shoutout.handshake = Responder("what", "yes", "i am here")
        self.__user_name: str = ""
        self.__dripper: PercentDripper = PercentDripper()

    # setters
    def setTimeStamp(self, time_stamp: str) -> AXHandshake:
        # when will the shout out happen?
        # example time stamp: 9:15
        self.__trgTime.setTime(time_stamp)
        return self

    def setShoutOutLim(self, lim: int) -> AXHandshake:
        # how many times should user be called for, per shout out?
        self.__trgTolerance.setMaxRepeats(lim)
        return self

    def setHandShake(self, responder: Responder) -> AXHandshake:
        # which responses would acknowledge the shout-out?
        # such as *see default handshakes for examples suggestions
        self.__shoutout.handshake = responder
        return self

    def setDripperPercent(self, n: int) -> AXHandshake:
        # hen shout out to user how frequent will it be?
        self.__dripper.setLimit(n)
        return self

    def setUser_name(self, user_name: str) -> AXHandshake:
        self.__user_name = user_name
        return self

    # getters
    def getUser_name(self) -> str:
        return self.__user_name

    def engage(self, ear: str) -> bool:
        if self.__trgTime.alarm():
            self.__trgTolerance.reset()
        # stop shout out
        if self.__shoutout.engage(ear):
            self.__trgTolerance.disable()
            return True
        return False

    def trigger(self) -> bool:
        if self.__trgTolerance.trigger():
            if self.__dripper.drip():
                self.__shoutout.activate()
                return True
        return False


class RailChatBot:
    def __init__(self, limit=5):
        self.dic = {}
        self.context = "default"
        self.dic[self.context] = RefreshQ(limit)
        self._limit = limit

    def setContext(self, context):
        if context == "":
            return
        self.context = context

    def respondMonolog(self, ear):
        # monolog mode
        # recommended use of filter for the output results
        if ear == "":
            return ""
        if ear not in self.dic:
            self.dic[ear] = RefreshQ(self._limit)
        temp = self.dic[ear].getRNDElement()
        if temp != "":
            self.context = temp
        return temp

    def learn(self, ear):
        if ear == "" or ear == self.context:
            return
        if ear not in self.dic:
            self.dic[ear] = RefreshQ(self._limit)
            self.dic[self.context].insert(ear)
            self.context = ear
            return
        self.dic[self.context].insert(ear)
        self.context = ear

    def monolog(self):
        # succession of outputs without input involved
        return self.respondMonolog(self.context)

    def respondDialog(self, ear):
        # dialog mode
        # recommended use of filter for the output results
        if ear == "":
            return ""
        if ear not in self.dic:
            self.dic[ear] = RefreshQ(self._limit)
        temp = self.dic[ear].getRNDElement()
        return temp

    def learn_key_value(self, context: str, reply: str) -> None:
        # Learn questions and answers/key values
        if context not in self.dic:
            self.dic[context] = RefreshQ(self._limit)
        if reply not in self.dic:
            self.dic[reply] = RefreshQ(self._limit)
        self.dic[context].insert(reply)

    def feed_key_value_pairs(self, kv_list: list[AXKeyValuePair]) -> None:
        if not kv_list:
            return
        for kv in kv_list:
            self.learn_key_value(kv.get_key(), kv.get_value())


class TextEditingSeries:
    @staticmethod
    def add_new_lines(text: str, n1: int):
        words = text.split(' ')
        lines = []
        current_line = []
        current_length = 0

        for word in words:
            if current_length + len(word) + len(current_line) > n1:
                lines.append(' '.join(current_line))
                current_line = [word]
                current_length = len(word)
            else:
                current_line.append(word)
                current_length += len(word)

        lines.append(' '.join(current_line))
        return '\n'.join(lines)


class OnOffSwitch:
    def __init__(self):
        self._mode = False
        self._timeGate: TimeGate = TimeGate(5)
        self._on = Responder("on", "talk to me")
        self._off = Responder("off", "stop", "shut up", "shut it", "whatever", "whateva")

    def setPause(self, minutes):
        self._timeGate.setPause(minutes)

    def setOn(self, on: Responder):
        self._on = on

    def setOff(self, off):
        self._off = off

    def getMode(self, ear):
        if self._on.responsesContainsStr(ear):
            self._timeGate.openForPauseMinutes()
            self._mode = True
            return True
        elif self._off.responsesContainsStr(ear):
            self._timeGate.close()
            self._mode = False
        if self._timeGate.isClosed():
            self._mode = False
        return self._mode


class AXFunnel:
    # funnel all sorts of strings to fewer or other strings
    def __init__(self, default: str = "default"):
        self.dic: dict[str, str] = {}
        self.default: str = default

    def setDefault(self, default: str):
        self.default = default

    def addKV(self, key: str, value: str) -> AXFunnel:
        # add key value pair
        self.dic[key] = value
        return self

    def addK(self, key: str) -> AXFunnel:
        # add key default pair
        self.dic[key] = self.default
        return self

    def funnel(self, key: str) -> str:
        # dictionary get or default(key)
        if key not in self.dic:
            return key
        return self.dic[key]

    def funnel_or_empty(self, key: str) -> str:
        # dictionary get or default("")
        if key not in self.dic:
            return ""
        return self.dic[key]


class ChangeDetector:
    def __init__(self, a, b):
        self.A = a
        self.B = b
        self.prev = -1

    def detect_change(self, ear):
        # a->b return 2; b->a return 1; else return 0
        if not ear:
            return 0
        current: int
        if self.A in ear:
            current = 1
        elif self.B in ear:
            current = 2
        else:
            return 0
        result = 0
        if (current == 1) and (self.prev == 2):
            result = 1
        if (current == 2) and (self.prev == 1):
            result = 2
        self.prev = current
        return result


class ElizaDeducer:
    """
    This class populates a special chat dictionary
    based on the matches added via its add_phrase_matcher function.
    See subclass ElizaDeducerInitializer for example:
    ed = ElizaDeducerInitializer(2)  # 2 = limit of replies per input
    """
    def __init__(self, lim: int):
        self.babble2: list['PhraseMatcher'] = []
        self.pattern_index: dict[str, list['PhraseMatcher']] = {}
        self.response_cache: dict[str, list['AXKeyValuePair']] = {}
        self.ec2 = EventChatV2(lim)  # Chat dictionary, use getter for access. Hardcoded replies can also be added

    def get_ec2(self) -> 'EventChatV2':
        return self.ec2

    def learn(self, msg: str) -> None:
        # Populate EventChat dictionary
        # Check cache first
        if msg in self.response_cache:
            self.ec2.add_key_values(list(self.response_cache[msg]))

        # Search for matching patterns
        potential_matchers = self.get_potential_matchers(msg)
        for pm in potential_matchers:
            if pm.matches(msg):
                response = pm.respond(msg)
                self.response_cache[msg] = response
                self.ec2.add_key_values(response)

    def learned_bool(self, msg: str) -> bool:
        # Same as learn method but returns true if it learned new replies
        learned = False
        # Populate EventChat dictionary
        # Check cache first
        if msg in self.response_cache:
            self.ec2.add_key_values(list(self.response_cache[msg]))
            learned = True

        # Search for matching patterns
        potential_matchers = self.get_potential_matchers(msg)
        for pm in potential_matchers:
            if pm.matches(msg):
                response = pm.respond(msg)
                self.response_cache[msg] = response
                self.ec2.add_key_values(response)
                learned = True
        return learned

    def respond(self, str1: str) -> str:
        return self.ec2.response(str1)

    def respond_latest(self, str1: str) -> str:
        # Get most recent reply/data
        return self.ec2.response_latest(str1)

    def get_potential_matchers(self, msg: str) -> list['PhraseMatcher']:
        potential_matchers = []
        for key in self.pattern_index:
            if key in msg:
                potential_matchers.extend(self.pattern_index[key])
        return potential_matchers

    def add_phrase_matcher(self, pattern: str, *kv_pairs: str) -> None:
        kvs = [AXKeyValuePair(kv_pairs[i], kv_pairs[i + 1]) for i in range(0, len(kv_pairs), 2)]
        matcher = PhraseMatcher(pattern, kvs)
        self.babble2.append(matcher)
        self.index_pattern(pattern, matcher)

    def index_pattern(self, pattern: str, matcher: 'PhraseMatcher') -> None:
        for word in pattern.split():
            self.pattern_index.setdefault(word, []).append(matcher)

class PhraseMatcher:
    def __init__(self, matcher: str, responses: list['AXKeyValuePair']):
        self.matcher = re.compile(matcher)
        self.responses = responses

    def matches(self, str: str) -> bool:
        m = self.matcher.match(str)
        return m is not None

    def respond(self, str: str) -> list['AXKeyValuePair']:
        m = self.matcher.match(str)
        result = []
        if m:
            tmp = len(m.groups())
            for kv in self.responses:
                temp_kv = AXKeyValuePair(kv.key, kv.value)
                for i in range(tmp):
                    s = m.group(i + 1)
                    temp_kv.key = temp_kv.key.replace(f"{{{i}}}", s).lower()
                    temp_kv.value = temp_kv.value.replace(f"{{{i}}}", s).lower()
                result.append(temp_kv)
        return result


class ElizaDeducerInitializer(ElizaDeducer):
    def __init__(self, lim: int):
        # Recommended lim = 5; it's the limit of responses per key in the EventChat dictionary
        # The purpose of the lim is to make saving and loading data easier
        super().__init__(lim)
        self.initialize_babble2()

    def initialize_babble2(self) -> None:
        self.add_phrase_matcher(
            r"(.*) is (.*)",
            "what is {0}", "{0} is {1}",
            "explain {0}", "{0} is {1}"
        )

        self.add_phrase_matcher(
            r"if (.*) or (.*) than (.*)",
            "{0}", "{2}",
            "{1}", "{2}"
        )

        self.add_phrase_matcher(
            r"if (.*) and (.*) than (.*)",
            "{0}", "{1}"
        )

        self.add_phrase_matcher(
            r"(.*) because (.*)",
            "{1}", "i guess {0}"
        )

class ElizaDBWrapper:
    # This (function wrapper) class adds save load functionality to the ElizaDeducer Object
    """
    ElizaDeducer ed = ElizaDeducerInitializer(2)
    ed.get_ec2().add_from_db("test", "one_two_three")  # Manual load for testing
    kokoro = Kokoro(AbsDictionaryDB())  # Use skill's kokoro attribute
    ew = ElizaDBWrapper()
    print(ew.respond("test", ed.get_ec2(), kokoro))  # Get reply for input, tries loading reply from DB
    print(ew.respond("test", ed.get_ec2(), kokoro))  # Doesn't try DB load on second run
    ed.learn("a is b")  # Learn only after respond
    ew.sleep_n_save(ed.get_ec2(), kokoro)  # Save when bot is sleeping, not on every skill input method visit
    """

    def __init__(self):
        self.modified_keys: set[str] = set()

    def respond(self, in1: str, ec: EventChatV2, kokoro: 'Kokoro') -> str:
        if in1 in self.modified_keys:
            return ec.response(in1)
        self.modified_keys.add(in1)
        # Load
        ec.add_from_db(in1, kokoro.grimoireMemento.simple_load(in1))
        return ec.response(in1)

    def respond_latest(self, in1: str, ec: EventChatV2, kokoro: 'Kokoro') -> str:
        if in1 in self.modified_keys:
            return ec.response_latest(in1)
        self.modified_keys.add(in1)
        # Load and get latest reply for input
        ec.add_from_db(in1, kokoro.grimoireMemento.simple_load(in1))
        return ec.response_latest(in1)

    def sleep_n_save(self, ecv2: EventChatV2, kokoro: 'Kokoro') -> None:
        for element in ecv2.get_modified_keys():
            kokoro.grimoireMemento.simple_save(element, ecv2.get_save_str(element))


class AXFunnelResponder:
    def __init__(self):
        self.dic: dict[str, Responder] = {}

    def add_kv(self, key: str, value: Responder) -> None:
        # Add key-value pair
        self.dic[key] = value

    def add_kv_builder_pattern(self, key: str, value: Responder) -> AXFunnelResponder:
        # Add key-value pair
        self.dic[key] = value
        return self

    def funnel(self, key: str) -> str:
        # Default funnel = key
        if key in self.dic:
            return self.dic[key].getAResponse()
        return key

    def funnel_or_nothing(self, key: str) -> str:
        # Default funnel = ""
        if key in self.dic:
            return self.dic[key].getAResponse()
        return ""

    def funnel_walrus_operator(self, key: str):
        # Default funnel = None
        if key in self.dic:
            return self.dic[key].getAResponse()
        return None


class Notes:
    def __init__(self):
        self._log: list[str] = []
        self._index: int = 0

    def add(self, s1: str):
        self._log.append(s1)

    def clear(self):
        self._log.clear()

    def getNote(self) -> str:
        if len(self._log) == 0:
            return "zero notes"
        return self._log[self._index]

    def get_next_note(self) -> str:
        if len(self._log) == 0:
            return "zero notes"
        self._index += 1
        if self._index == len(self._log):
            self._index = 0
        return self._log[self._index]


class Excluder:
    """
    exclude will return true if the string starts with or ends with the defined starts or ends or strings
    this helps exclude input from certain skills so as to not overlap with other skills
    e1: Excluder = Excluder()
    e1.add_starts_with("tell me")
    e1.add_ends_with("over")
    e1.add_ends_with("babe")
    print(e1.exclude("tell me hello"))
    print(e1.exclude("tell me"))
    print(e1.exclude("hello world")) # only false example
    print(e1.exclude("hello babe"))
    print(e1.exclude("hello over"))
    print(e1.exclude("tell me something babe"))"""

    def __init__(self):
        self._starts_with: list[str] = []
        self._ends_with: list[str] = []

    def add_starts_with(self, s1: str):
        if self._starts_with.__contains__(f'^({s1}).*'):
            return
        self._starts_with.append(f'^({s1}).*')

    def add_ends_with(self, s1: str):
        if self._ends_with.__contains__(f'(.*)(?={s1})'):
            return
        self._ends_with.append(f'(.*)(?={s1})')

    def exclude(self, ear: str) -> bool:
        for temp_str in self._starts_with:
            if len(RegexUtil.extractRegex(temp_str, ear)) > 0:
                return True
        for temp_str in self._ends_with:
            if len(RegexUtil.extractRegex(temp_str, ear)) > 0:
                return True
        return False


class TimedMessages:
    """
        check for new messages if you get any input, and it feels like
        the code was waiting for you to tell you something.
        tm = TimedMessages()
        # Print the initial message status (should be False)
        print(tm.getMsg())
        # Add reminders
        tm.addMSG("remind me to work out at 1:24")
        tm.addMSG("remind me to drink water at 11:25")
        # Check if any reminders match the current time
        tm.tick()
        # make sure a fresh new message was loaded before using it
        print(tm.getMsg())
        # Get the last reminder message
        print(tm.getLastMSG())
        # tick each think cycle to load new reminders
        tm.tick()
        print(tm.getMsg()) # becomes true after .getLastMSG
        # Get the last reminder message again
        print(tm.getLastMSG())
    """

    def __init__(self) -> None:
        self.messages: dict[str, str] = {}
        self.lastMSG: str = "nothing"
        self.msg: bool = False

    def addMSG(self, ear: str) -> None:
        tempMSG: str = RegexUtil.extractRegex(r"(?<=remind me to).*?(?=at)", ear)
        if tempMSG:
            timeStamp: str = RegexUtil.extractEnumRegex(enumRegexGrimoire.simpleTimeStamp, ear)
            if timeStamp:
                self.messages[timeStamp] = tempMSG

    def addMSGV2(self, timeStamp: str, msg: str) -> None:
        self.messages[timeStamp] = msg

    def sprinkleMSG(self, msg: str, amount: int) -> None:
        for _ in range(amount):
            self.messages[self.generateRandomTimestamp()] = msg

    @staticmethod
    def generateRandomTimestamp() -> str:
        minutes: int = random.randint(0, 59)
        m = f"{minutes:02d}"
        hours: int = random.randint(0, 11)
        return f"{hours}:{m}"

    def clear(self):
        self.messages.clear()

    def tick(self) -> None:
        now: str = TimeUtils.getCurrentTimeStamp()
        if now in self.messages:
            if self.lastMSG != self.messages[now]:
                self.lastMSG = self.messages[now]
                self.msg = True

    def getLastMSG(self) -> str:
        self.msg = False
        return self.lastMSG

    def getMsg(self) -> bool:
        return self.msg


class AlgorithmV2:
    def __init__(self, priority, alg):
        self.priority = priority
        self.alg = alg

    def get_priority(self):
        return self.priority

    def set_priority(self, priority):
        self.priority = priority

    def get_alg(self):
        return self.alg

    def set_alg(self, alg):
        self.alg = alg


class AXSkillBundle:
    def __init__(self, *skills_params: Skill):
        self.skills: list[Skill] = []
        self.tempN: Neuron = Neuron()
        self.kokoro: Kokoro = Kokoro(AbsDictionaryDB())

        for skill in skills_params:
            skill.setKokoro(self.kokoro)
            self.skills.append(skill)

    def set_kokoro(self, kokoro):
        self.kokoro = kokoro
        for skill in self.skills:
            skill.setKokoro(self.kokoro)

    def add_skill(self, skill) -> AXSkillBundle:
        # Builder pattern
        skill.setKokoro(self.kokoro)
        self.skills.append(skill)
        return self

    def dispense_algorithm(self, ear, skin, eye):
        for skill in self.skills:
            skill.input(ear, skin, eye)
            skill.output(self.tempN)
            for j in range(1, 6):
                temp = self.tempN.getAlg(j)
                if temp:
                    return AlgorithmV2(j, temp)

        return None

    def get_size(self):
        return len(self.skills)


class AXFunnelResponder1(AXFunnelResponder):
    def __init__(self):
        super().__init__()
        r1: Responder = Responder("yes", "lick my feet", "i need you to lick my feet")
        self.add_kv("may i lick your feet", r1)
        self.add_kv("let me lick your feet", r1)
        r1 = Responder("i love you more", "i love you too", "sweet baby", "uwu", "owo", "oooweee")
        self.add_kv("i love you", r1)

class UniqueRandomGenerator:
    def __init__(self, n1: int):
        self.n1 = n1
        self.numbers = list(range(n1))
        self.remaining_numbers = []  # Declare here to avoid the error
        self.reset()

    def reset(self):
        self.remaining_numbers = self.numbers.copy()
        random.shuffle(self.remaining_numbers)

    def get_unique_random(self) -> int:
        if not self.remaining_numbers:
            self.reset()
        return self.remaining_numbers.pop()

class UniqueResponder:
    # simple random response dispenser
    def __init__(self, *replies: str):
        # Ensure replies is not empty to avoid range issues
        self.responses: list[str] = []
        self.urg: UniqueRandomGenerator = UniqueRandomGenerator(len(replies))
        for response in replies:
            self.responses.append(response)

    def getAResponse(self) -> str:
        if not self.responses:
            return ""
        return self.responses[self.urg.get_unique_random()]

    def responsesContainsStr(self, item: str) -> bool:
        return self.responses.__contains__(item)

    def strContainsResponse(self, item: str) -> bool:
        for response in self.responses:
            if len(response) == 0:
                continue
            if item.__contains__(response):
                return True
        return False

    def addResponse(self, s1: str):
        if not self.responses.__contains__(s1):
            self.responses.append(s1)
            self.urg: UniqueRandomGenerator = UniqueRandomGenerator(len(self.responses))

class EventChat:
    # funnel input to a unique response bundle
    def __init__(self, ur: UniqueResponder, *args):
        self._dic = {arg: ur for arg in args}

    def add_items(self, ur: UniqueResponder, *args):
        for arg in args:
            self._dic[arg] = ur

    def add_key_value(self, key: str, value: str):
        if key in self._dic:
            self._dic[key].addResponse(value)
        else:
            self._dic[key]: UniqueResponder = UniqueResponder(value)

    def response(self, in1: str) -> str:
        return self._dic.get(in1, "").getAResponse() if in1 in self._dic else ""


class AXStandBy:
    def __init__(self, pause: int):
        self._tg:TimeGate = TimeGate(pause)
        self._tg.openForPauseMinutes()

    def standBy(self, ear: str) -> bool:
        # only returns true after pause minutes of no input
        if len(ear) > 0:
            # restart count
            self._tg.openForPauseMinutes()
            return False
        if self._tg.isClosed():
            # time out without input, stand by is true
            self._tg.openForPauseMinutes()
            return True

class LimUniqueResponder:
    def __init__(self, lim: int):
        self.responses: list[str] = []
        self.lim = lim
        self.urg = UniqueRandomGenerator(0)

    def get_a_response(self) -> str:
        if not self.responses:
            return ""
        return self.responses[self.urg.get_unique_random()]

    def responses_contains_str(self, item: str) -> bool:
        return item in self.responses

    def str_contains_response(self, item: str) -> bool:
        return any(response and response in item for response in self.responses)

    def add_response(self, s1: str) -> None:
        if s1 in self.responses:
            self.responses.remove(s1)
            self.responses.append(s1)
            return
        if len(self.responses) > self.lim - 1:
            self.responses.pop(0)
        else:
            self.urg = UniqueRandomGenerator(len(self.responses)+1)
        self.responses.append(s1)


    def add_responses(self, *replies: str) -> None:
        for value in replies:
            self.add_response(value)

    def get_savable_str(self) -> str:
        return "_".join(self.responses)

    def get_last_item(self) -> str:
        if not self.responses:
            return ""
        return self.responses[-1]

    def clone(self) -> LimUniqueResponder:
        cloned_responder = LimUniqueResponder(self.lim)  # Create a new instance with the same limit
        cloned_responder.responses = self.responses.copy()  # Copy the responses list
        cloned_responder.urg = UniqueRandomGenerator(
            len(cloned_responder.responses))  # Recreate the UniqueRandomGenerator
        return cloned_responder


class EventChatV2:
    def __init__(self, lim: int):
        self.dic: dict[str, LimUniqueResponder] = {}
        self.modified_keys: set[str] = set()
        self.lim = lim

    def get_modified_keys(self) -> set[str]:
        return self.modified_keys

    def key_exists(self, key: str) -> bool:
        # if the key was active true is returned
        return key in self.modified_keys

    # Add items
    def add_items(self, ur: LimUniqueResponder, *args: str) -> None:
        for arg in args:
            self.dic[arg] = ur.clone()

    def add_from_db(self, key: str, value: str) -> None:
        if not value or value == "null":
            return
        values = value.split("_")  # assuming AXStringSplit splits on "_"
        if key not in self.dic:
            self.dic[key] = LimUniqueResponder(self.lim)
        for item in values:
            self.dic[key].add_response(item)

    # Add key-value pair
    def add_key_value(self, key: str, value: str) -> None:
        self.modified_keys.add(key)
        if key in self.dic:
            self.dic[key].add_response(value)
        else:
            self.dic[key] = LimUniqueResponder(self.lim)
            self.dic[key].add_response(value)

    def add_key_values(self, eliza_results: list) -> None:
        for pair in eliza_results:
            # Access the key and value of each AXKeyValuePair object
            self.add_key_value(pair.get_key(), pair.get_value())

    # Get response
    def response(self, in1: str) -> str:
        return self.dic[in1].get_a_response() if in1 in self.dic else ""

    def response_latest(self, in1: str) -> str:
        return self.dic[in1].get_last_item() if in1 in self.dic else ""

    def get_save_str(self, key: str) -> str:
        return self.dic[key].get_savable_str() if key in self.dic else ""


class ElizaDeducerInitializer2(ElizaDeducer):
    def __init__(self, lim: int):
        # Recommended lim = 5; it's the limit of responses per key in the EventChat dictionary
        # The purpose of the lim is to make saving and loading data easier
        super().__init__(lim)
        self.initialize_babble2()

    def initialize_babble2(self) -> None:
        # Adding phrase matchers for various patterns and responses to enhance conversation logic

        # Description
        # self.add_phrase_matcher(
        #     r"(.*) is (.*)",
        #     "what is {0}", "{0} is {1}",
        #     "explain {0}", "{0} is {1}"
        # )

        # Comparison
        # self.add_phrase_matcher(
        #     r"(.*) are (.*) than (.*)",
        #     "who is {1} {0} or {2}", "{0}",
        #     "who is {1} {2} or {0}", "{0}",
        #     "who are {1} {2} or {0}", "{0}",
        #     "who are {1} {0} or {2}", "{0}"
        # )

        # Why
        self.add_phrase_matcher(
            r"(.*) because (.*)",
            "tell me why {0}", "{1}",
            "explain why {0}", "{0} because {1}"
        )

        # Triple OR
        # self.add_phrase_matcher(
        #     r"if (.*) or (.*) or (.*) than (.*)",
        #     "{0}", "{3}",
        #     "{1}", "{3}",
        #     "{2}", "{3}"
        # )

        # OR
        # self.add_phrase_matcher(
        #     r"if (.*) or (.*) than (.*)",
        #     "{0}", "{2}",
        #     "{1}", "{2}"
        # )

        # How
        self.add_phrase_matcher(
            r"to (.*) simply (.*)",
            "explain how to {0}", "{1}"
        )

        # XOR
        # self.add_phrase_matcher(
        #     r"if (.*) xor (.*) than (.*)",
        #     "{0} and not {1}", "{2}",
        #     "{1} and not {0}", "{2}"
        # )

        # If
        # self.add_phrase_matcher(
        #     r"if (.*) than (.*)",
        #     "{0}", "{1}"
        # )

        # say
        self.add_phrase_matcher(
            r"say (.*)",
            "speak", "{0}"
        )

        # Reverse If
        # self.add_phrase_matcher(
        #     r"(.*) if (.*)",
        #     "{1}", "{0}",
        #     "{1}", "than {0} I guess"
        # )

        # Likes
        self.add_phrase_matcher(
            r"(.*) like (.*)",
            "what do {0} like", "{0} like {1}"
        )
        self.add_phrase_matcher(
            r"(.*) likes (.*)",
            "what does {0} like", "{0} likes {1}"
        )
        # Anti-bully 1
        # self.add_phrase_matcher(
        #     r"you are just a (.*)",
        #     "you are just a {0}", "i will be the best {0} then",
        #     "you are just a {0}", "kiss my {0} butt",
        #     "you are just a {0}", "shiku shiku"
        # )

        # Anti-bully 2
        # self.add_phrase_matcher(
        #     r"you damn (.*)",
        #     "you damn {0}", "but i am the best {0}",
        #     "you damn {0}", "kiss my {0} butt",
        #     "you damn {0}", "meanie"
        # )

        # contacts
        self.add_phrase_matcher(
            r"(.*) owns the email (.*)",
            "email {0}", "{1}",
            "what is the email for {0}", "{1}"
        )

        # phone
        self.add_phrase_matcher(
            r"(.*)'s phone is (.*)",
            "phone {0}", "{1}",
            "what is the phone for {0}", "{1}"
        )

class QuestionChecker:
    QUESTION_WORDS = {
        "what", "who", "where", "when", "why", "how",
        "is", "are", "was", "were", "do", "does", "did",
        "can", "could", "would", "will", "shall", "should",
        "have", "has", "am", "may", "might"
    }

    @staticmethod
    def is_question(input_text):
        if not input_text or not input_text.strip():
            return False

        trimmed = input_text.strip().lower()

        # Check for question mark
        if trimmed.endswith("?"):
            return True

        # Extract the first word
        first_space = trimmed.find(' ')
        first_word = trimmed if first_space == -1 else trimmed[:first_space]

        # Check for contractions like "who's"
        if "'" in first_word:
            first_word = first_word.split("'")[0]

        # Check if the first word is a question word
        return first_word in QuestionChecker.QUESTION_WORDS

class PhraseInflector:
    # Maps for pronoun and verb inflection
    inflection_map = {
        "i": "you",
        "me": "you",
        "my": "your",
        "mine": "yours",
        "you": "i",  # Default inflection
        "your": "my",
        "yours": "mine",
        "am": "are",
        "are": "am",
        "was": "were",
        "were": "was",
        "i'd": "you would",
        "i've": "you have",
        "you've": "I have",
        "you'll": "I will"
    }

    @staticmethod
    def is_verb(word):
        verbs = {"am", "are", "was", "were", "have", "has", "had", "do", "does", "did"}
        return word in verbs

    @staticmethod
    def inflect_phrase(phrase):
        words = phrase.split()
        result = []

        for i, word in enumerate(words):
            lower_word = word.lower()
            inflected_word = word  # Default to original word

            # Check if word needs inflection
            if lower_word in PhraseInflector.inflection_map:
                inflected_word = PhraseInflector.inflection_map[lower_word]

                # Special case for "you"
                if lower_word == "you":
                    if i == len(words) - 1 or (i > 0 and PhraseInflector.is_verb(words[i - 1].lower())):
                        inflected_word = "me"
                    else:
                        inflected_word = "I"

            # Preserve capitalization
            if word[0].isupper():
                inflected_word = inflected_word.capitalize()

            result.append(inflected_word)

        return " ".join(result)

class RailBot:
    def __init__(self, limit=5):
        self.ec = EventChatV2(limit)
        self.context = "stand by"
        self.eliza_wrapper = None  # Starts as None (no DB)

    def enable_db_wrapper(self):
        """Enables database features. Must be called before any save/load operations."""
        if self.eliza_wrapper is None:
            self.eliza_wrapper = ElizaDBWrapper()

    def disable_db_wrapper(self):
        """Disables database features."""
        self.eliza_wrapper = None

    def set_context(self, context):
        """Sets the current context."""
        if not context:
            return
        self.context = context

    def respond_monolog(self, ear):
        """Private helper for monolog response."""
        if not ear:
            return ""
        temp = self.ec.response(ear)
        if temp:
            self.context = temp
        return temp

    def learn(self, ear):
        """Learns a new response for the current context."""
        if not ear or ear == self.context:
            return
        self.ec.add_key_value(self.context, ear)
        self.context = ear

    def monolog(self):
        """Returns a monolog based on the current context."""
        return self.respond_monolog(self.context)

    def respond_dialog(self, ear):
        """Responds to a dialog input."""
        return self.ec.response(ear)

    def respond_latest(self, ear):
        """Responds to the latest input."""
        return self.ec.response_latest(ear)

    def learn_key_value(self, context, reply):
        """Adds a new key-value pair to the memory."""
        self.ec.add_key_value(context, reply)

    def feed_key_value_pairs(self, kv_list):
        """Feeds a list of key-value pairs into the memory."""
        if not kv_list:
            return
        for kv in kv_list:
            self.learn_key_value(kv.get_key(), kv.get_value())

    def save_learned_data(self, kokoro):
        """Saves learned data using the provided Kokoro instance."""
        if self.eliza_wrapper is None:
            return
        self.eliza_wrapper.sleep_n_save(self.ec, kokoro)

    def loadable_monolog_mechanics(self, ear, kokoro):
        """Private helper for loadable monolog mechanics."""
        if not ear:
            return ""
        temp = self.eliza_wrapper.respond(ear, self.ec, kokoro)
        if temp:
            self.context = temp
        return temp

    def loadable_monolog(self, kokoro):
        """Returns a loadable monolog based on the current context."""
        if self.eliza_wrapper is None:
            return self.monolog()
        return self.loadable_monolog_mechanics(self.context, kokoro)

    def loadable_dialog(self, ear, kokoro):
        """Returns a loadable dialog response."""
        if self.eliza_wrapper is None:
            return self.respond_dialog(ear)
        return self.eliza_wrapper.respond(ear, self.ec, kokoro)