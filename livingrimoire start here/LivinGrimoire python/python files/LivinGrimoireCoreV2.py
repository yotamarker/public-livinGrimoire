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
        """TODO set to return null as default if key not found !!!"""
        pass


''' ENUMFAIL CLASS '''


class enumFail(Enum):
    fail = "fail"  # no input
    requip = "requip"  # item should be added
    dequip = "dequip"
    cloudian = "cloudian"  # algorithm goes to stand by in its Dclass
    ok = "ok"  # no fail


''' MUTATABLE CLASS '''


class Mutatable(ABC):
    # one part of an algorithm, it is a basic simple action or sub goal
    @abstractmethod
    def action(self, ear: str, skin: str, eye: str) -> str:
        """Returns action string"""
        pass

    @abstractmethod
    def failure(self, input: str) -> enumFail:
        """Failure type only mutatable may use enumFail.fail"""
        pass

    @abstractmethod
    def completed(self) -> bool:
        """Has finished ?"""
        pass

    @abstractmethod
    def clone(self) -> Mutatable:
        pass

    def getMutationLimit(self) -> int:
        # override this to the number of mutations a mutation series can perform, so at
        # least to 1 if you want mutations enabled.
        return 0

    def myName(self) -> str:
        """Returns the class name"""
        return self.__class__.__name__

    def mutation(self) -> Mutatable:
        self.clone()


class GrimoireMemento:
    def __init__(self, absDictionaryDB: AbsDictionaryDB) -> None:
        super().__init__()
        self.rootToAPNumDic = {}  # string, string
        self.APNumToObjDic = {}  # string, Mutatable
        self.absDictionaryDB = absDictionaryDB

    def load(self, obj: Mutatable) -> Mutatable:
        '''load final mutation from memory of obj'''
        objName = obj.__class__.__name__
        objRoot = re.sub(r'\d+', '', objName)
        # if not in active DB try adding from external DB
        if not self.rootToAPNumDic.keys().__contains__(objRoot):
            temp = self.absDictionaryDB.load(objRoot)
            if temp != "null":
                self.rootToAPNumDic[objRoot] = temp

        if (not self.rootToAPNumDic.keys().__contains__(objRoot)):
            self.rootToAPNumDic[objRoot] = objName
            return obj

        if (self.rootToAPNumDic[objRoot] == objName):
            # the mutatable does not have mutations
            return obj
        else:
            APNum = self.rootToAPNumDic[objRoot]
            if (self.APNumToObjDic.keys().__contains__(APNum)):
                return self.APNumToObjDic[APNum].clone()
            else:
                self.loadMutations(obj, objName, objRoot)
                return self.APNumToObjDic[APNum].clone()

    def reqquipMutation(self, mutationAPName: str):
        '''save mutation'''
        self.rootToAPNumDic[re.sub(r'\d+', '', mutationAPName)] = mutationAPName
        self.absDictionaryDB.save(re.sub(r'\d+', '', mutationAPName), mutationAPName)

    def loadMutations(self, obj: Mutatable, objName: str, objRoot: str):
        '''
        make sure all the AP mutation sets of obj are present
        self assumes the last mutation mutates into the prime mutation
        '''
        mutant = obj
        end = objName
        while (True):
            self.APNumToObjDic[obj.__class__.__name__] = obj.clone()
            mutant = obj
            obj = mutant.mutation()
            if (end == obj.__class__.__name__):
                break

    def simpleLoad(self, key: str) -> str:
        return self.absDictionaryDB.load(key)

    def simpleSave(self, key: str, value: str):
        if (key.startswith("AP") or key == "" or value == ""):
            return
        self.absDictionaryDB.save(key, value)


'''
all action data goes through here
 * detects negatives such as : repetition, pain on various levels and failures
 * serves as a database for memories, convos and alg generations
 * can trigger revenge algs
 * checks for % of difference in input for exploration type algs
'''


class Kokoro:
    def __init__(self, absDictionaryDB: AbsDictionaryDB):
        self.emot = ""
        self.pain: dict[str, int] = {}
        self.grimoireMemento = GrimoireMemento(absDictionaryDB)
        self.toHeart: dict[str, str] = {}
        self.fromHeart: dict[str, str] = {}
        self.standBy = False

    def getEmot(self) -> str:
        return self.emot

    def setEmot(self, emot: str):
        self.emot = emot

    def getPain(self, BijuuName: str) -> int:
        try:
            value = self.pain[BijuuName]
        except:
            value = 0
        return value

    def inside(self, chi: MemoryMutatable):
        pass

    def outside(self, isCompleted: bool, failure: enumFail):
        pass


class MemoryMutatable(Mutatable):
    '''
        an adaptor pattern to the Mutatable (algorithm part)
      the object will load the last mutated state which is the last and optimized
      mutation used.
      upon mutation the new last mutation is saved so it can be loaded for the next time
      mutations happen in the DExplorer class and triggered when the Mutatbles' failure method
      returns enumFail.failure
      you can code said enumFail.failure to return under chosen conditions in the action method of
      the MemoryMutatable object. (sub class of this class)
    '''

    def __init__(self, kokoro: Kokoro, ofSkill: str, aPart: Mutatable):
        super().__init__()
        self.kokoro = kokoro
        self.ofSkill = ofSkill
        self.aPart = kokoro.grimoireMemento.load(aPart)

    def actualAction(self, ear: str, skin: str, eye: str) -> str:
        return self.aPart.action(ear, skin, eye)

    # Override
    def action(self, ear: str, skin: str, eye: str) -> str:
        self.kokoro.inside(self)
        result = self.actualAction(ear, skin, eye)
        self.kokoro.outside(self.completed(), self.failure(""))
        return result

    # Override
    def failure(self, input: str) -> enumFail:
        return self.aPart.failure(input)

    # Override
    def completed(self) -> bool:
        return self.aPart.completed()

    # Override
    def clone(self) -> Mutatable:
        return MemoryMutatable(self.kokoro, self.ofSkill, self.aPart.clone())

    # Override
    def getMutationLimit(self) -> int:
        return self.aPart.getMutationLimit()

    # Override
    def mutation(self) -> Mutatable:
        mutant = self.aPart
        tempAP = mutant.mutation()
        self.kokoro.grimoireMemento.reqquipMutation(tempAP.__class__.__name__)
        return MemoryMutatable(self.kokoro, self.ofSkill, tempAP)

    # Override
    def myName(self) -> str:
        return self.aPart.myName()


'''
it speaks something x times
a most basic skill.
also fun to make the chobit say what you want
'''


class APSay(Mutatable):
    def __init__(self, at: int, param: str) -> None:
        super().__init__()
        if (at > 10):
            at = 10
        self.at = at
        self.param = param

    def action(self, ear: str, skin: str, eye: str) -> str:
        '''TODO Auto-generated method stub'''
        axnStr = ""
        if (self.at > 0):
            if (ear.lower() != self.param.lower()):
                axnStr = self.param
                self.at -= 1
        return axnStr

    def failure(self, input: str) -> enumFail:
        return enumFail.ok

    def completed(self) -> bool:
        return self.at < 1

    def clone(self) -> Mutatable:
        return APSay(self.at, self.param)


class APVerbatim(Mutatable):
    '''this algorithm part says each past param verbatim'''

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
    def failure(self, input: str) -> enumFail:
        return enumFail.ok

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
            if (isinstance(words[0], list)):
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
        if (self.at < len(self.sentences)):
            axnStr = self.sentences[self.at]
            self.at += 1

        self.cldBool.setModeActive(not (self.at >= len(self.sentences)))
        return axnStr

    # Override
    def failure(self, input: str) -> enumFail:
        return enumFail.ok

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
        '''This method returns the current time (hh:mm)'''
        right_now = datetime.datetime.now()
        temp_minute: int = right_now.minute
        tempstr: str = ""
        if temp_minute < 10:
            tempstr = "0" + str(right_now.minute)
        else:
            tempstr = str(right_now.minute)
        return str(right_now.hour) + ":" + tempstr

    def getMonthAsInt(self) -> int:
        '''This method returns the current month (MM)'''
        right_now = datetime.datetime.now()
        return right_now.month

    def getDayOfTheMonthAsInt(self) -> int:
        '''This method returns the current day (dd)'''
        right_now = datetime.datetime.now()
        return right_now.day

    def getYearAsInt(self) -> int:
        '''This method returns the current year (yyyy)'''
        right_now = datetime.datetime.now()
        return right_now.year

    def getDayAsInt(self) -> int:
        '''This method returns the current day of the week (1, 2, ... 7)'''
        right_now = datetime.datetime.now()
        return right_now.isoweekday()

    def getMinutes(self) -> str:
        '''This method returns the current minutes (mm)'''
        right_now = datetime.datetime.now()
        return str(right_now.minute)

    def getSeconds(self) -> str:
        '''This method returns the current seconds (ss)'''
        right_now = datetime.datetime.now()
        return str(right_now.second)

    def getDayOfDWeek(self) -> str:
        '''This method returns the current day of the week as a word (monday, ...)'''
        right_now = datetime.datetime.now()
        return calendar.day_name[right_now.weekday()]

    def translateMonthDay(self, day: int) -> str:
        '''This method returns the current day of the month as a word (first_of, ...)'''
        return self.dayOfMonth.get(day, "")

    def getSpecificTime(self, time_variable: enumTimes) -> str:
        '''This method returns the current specific date in words (eleventh_of June 2021, ...)'''
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
        '''This method returns the current seconds'''
        right_now = datetime.datetime.now()
        return right_now.second

    def getMinutesAsInt(self) -> int:
        '''This method returns the current minutes'''
        right_now = datetime.datetime.now()
        return right_now.minute

    def getHoursAsInt(self) -> int:
        '''This method returns the current hour'''
        right_now = datetime.datetime.now()
        return right_now.hour

    def getFutureInXMin(self, extra_minutes: int) -> str:
        '''This method returns the date in x minutes'''
        right_now = datetime.datetime.now()
        final_time = right_now + datetime.timedelta(minutes=extra_minutes)
        regex: RegexUtil = RegexUtil()
        s1: str = str(final_time)
        s1 = regex.extractEnumRegex(enumRegexGrimoire.simpleTimeStamp, s1)
        if s1[0] == '0':
            s1 = s1[1:]
        return s1

    def getPastInXMin(self, less_minutes: int) -> str:
        '''This method returns the date x minutes before'''
        right_now = datetime.datetime.now()
        final_time = right_now - datetime.timedelta(minutes=less_minutes)
        regex: RegexUtil = RegexUtil()
        s1: str = str(final_time)
        s1 = regex.extractEnumRegex(enumRegexGrimoire.simpleTimeStamp, s1)
        if s1[0] == '0':
            s1 = s1[1:]
        return s1

    def getFutureHour(self, startHour: int, addedHours: int) -> int:
        '''This method returns the hour in x hours from the starting hour'''
        return (startHour + addedHours) % 24

    def getFutureFromXInYMin(self, to_add: int, start: str) -> str:
        '''This method returns the time (hh:mm) in x minutes the starting time (hh:mm)'''
        values = start.split(":")
        times_to_add = (int(values[1]) + to_add) // 60
        new_minutes = (int(values[1]) + to_add) % 60
        new_time = str((int(values[0]) + times_to_add) % 24) + ":" + str(new_minutes)
        return new_time

    def timeInXMinutes(self, x: int) -> str:
        '''This method returns the time (hh:mm) in x minutes'''
        right_now = datetime.datetime.now()
        final_time = right_now + datetime.timedelta(minutes=x)
        return str(final_time.hour) + ":" + str(final_time.minute)

    def isDayTime(self) -> bool:
        '''This method returns true if it's daytime (6-18)'''
        return 5 < datetime.datetime.now().hour < 19

    def smallToBig(self, *a) -> bool:
        for i in range(len(a) - 1):
            if a[i] > a[i + 1]:
                return False
        return True

    def partOfDay(self) -> str:
        '''This method returns which part of the day it is (morning, ...)'''
        hour: int = self.getHoursAsInt()
        if self.smallToBig(5, hour, 12):
            return "morning"
        elif self.smallToBig(11, hour, 17):
            return "afternoon"
        elif self.smallToBig(16, hour, 21):
            return "evening"
        return "night"

    def convertToDay(self, number: int) -> str:
        '''This method converts the week number to the weekday name'''
        return self.week_days.get(number, "")

    def isNight(self) -> bool:
        '''This method returns true if it's night (21-5)'''
        hour: int = self.getHoursAsInt()
        return hour > 20 or hour < 6

    def getTomorrow(self) -> str:
        '''This method returns tomorrow'''
        right_now = datetime.datetime.now()
        if (right_now.weekday() == 6):
            return "sunday"
        return calendar.day_name[right_now.weekday() + 1]

    def getYesterday(self) -> str:
        '''This method returns yesterday'''
        right_now = datetime.datetime.now()
        if right_now.weekday == 0:
            return "Sunday"
        return calendar.day_name[right_now.weekday() - 1]

    def getGMT(self) -> int:
        '''This method returns the local GMT'''
        right_now = datetime.datetime.now()
        timezone = int(str(right_now.astimezone())[-6:-3])
        return timezone

    def getLocal(self) -> str:
        '''This method returns the local time zone'''
        return datetime.datetime.now(datetime.timezone.utc).astimezone().tzinfo


# A step-by-step plan to achieve a goal
class Algorithm:

    def __init__(self, goal: str, representation: str, algParts: list[Mutatable]):  # list of Mutatable
        super().__init__()
        self.goal = "unknown" if (goal == "") else goal
        self.representation = "unknown" if (representation == "") else representation
        self.algParts = algParts

    # *constract with string and goal
    @property
    def getGoal(self) -> str:
        return self.goal

    @property
    def getRepresentation(self) -> str:
        return self.representation

    @property
    def getAlgParts(self) -> list[Mutatable]:
        return self.algParts

    def getSize(self) -> int:
        return len(self.algParts)

    def clone(self) -> Algorithm:
        parts = []  # list of Mutatable
        for mutatable in self.algParts:
            parts.append(mutatable.clone())
        return Algorithm(self.goal, self.getRepresentation, parts)


''' NEURON CLASS '''


# used to transport algorithms to other classes
class Neuron:
    def __init__(self) -> None:
        self.algParts: list[Algorithm] = []
        self.negativeAlgParts: list[Algorithm] = []

    def empty(self):
        self.algParts.clear()
        self.negativeAlgParts.clear()


''' DISKILLUTILS CLASS '''


class DISkillUtils:
    def verbatimGorithmOne(self, itte: Mutatable) -> Algorithm:
        # returns a simple algorithm containing 1 alg part
        representation = "util"
        algParts1: list[Mutatable] = []
        algParts1.append(itte)
        algorithm = Algorithm("util", representation, algParts1)
        return algorithm

    def verbatimGorithmTwo(self, algMarker: str, itte: Mutatable) -> Algorithm:
        # returns a simple algorithm for saying sent parameter
        representation = "util"
        algParts1: list[Mutatable] = []
        algParts1.append(itte)
        algorithm = Algorithm("util", representation, algParts1)
        return algorithm

    def customizedVerbatimGorithm(self, algMarker: str, itte: Mutatable) -> Algorithm:
        # the most stable and advanced algorithm builder
        # returns a simple algorithm containing 1 alg part
        representation = "r_" + algMarker
        algParts1: list[Mutatable] = []
        algParts1.append(itte)
        algorithm = Algorithm(algMarker, representation, algParts1)
        return algorithm

    def customizedVerbatimGorithm(self, algMarker: str, *itte: Mutatable) -> Algorithm:
        # the most stable and advanced algorithm builder
        # returns a simple algorithm containing 1 alg part
        representation = "r_" + algMarker
        algParts1: list[Mutatable] = []
        for i in range(len(itte)):
            algParts1.append(itte[i])
        algorithm = Algorithm(algMarker, representation, algParts1)
        return algorithm

    def simpleVerbatimAlgorithm(self, algMarker: str, *sayThis) -> Algorithm:
        # returns alg that says the word string (sayThis)
        return self.customizedVerbatimGorithm(algMarker, APVerbatim(*sayThis))

    def simpleCloudiandVerbatimAlgorithm(self, cldBool: CldBool, algMarker: str, *sayThis) -> Algorithm:
        # returns alg that says the word string (sayThis)
        return self.customizedVerbatimGorithm(algMarker, APCldVerbatim(cldBool, *sayThis))

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
        self._outAlg = None  # skills output

    # skill triggers and algorithmic logic
    def input(self, ear: str, skin: str, eye: str):
        pass

    # extraction of skill algorithm to run (if there is one)
    def output(self, noiron: Neuron):
        if (self._outAlg != None):
            noiron.algParts.append(self._outAlg)
            self._outAlg = None

    def setKokoro(self, kokoro: Kokoro):
        # use this for telepathic communication between different chobits objects
        self._kokoro = kokoro


''' DISKILLV3 CLASS '''


class DiSkillV3(DiSkillV2):
    # algorithm will be loaded with priority to run
    # use for fight or flight type skills
    # negativeAlgParts take priority to run over algParts
    # (used in the super class DiSkillV2)
    def output(self, noiron: Neuron):
        if (self._outAlg != None):
            noiron.negativeAlgParts.append(self._outAlg)
            self._outAlg = None


class DiHelloWorld(DiSkillV2):
    # Override
    def input(self, ear: str, skin: str, eye: str):
        if ear == "hello":
            self._outAlg = self._diSkillUtils.simpleVerbatimAlgorithm("test", "hello world")


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
        return (self.openedGate < datetime.datetime.now())

    def isOpen(self) -> bool:
        return (not (self.openedGate < datetime.datetime.now()))

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


''' CERABELLUM CLASS '''


class Cerabellum:
    # runs an algorithm
    def __init__(self) -> None:
        self.fin: int = None
        self.at: int = None
        self.failType: enumFail = None
        self.incrementAt: bool = False
        self.alg: Algorithm = None
        self.isActive: bool = False
        self.emot: str = ""

    def advanceInAlg(self):
        if (self.incrementAt):
            self.incrementAt = False
            self.at += 1
            if (self.at == self.fin):
                self.isActive = False

    def getAt(self) -> int:
        return self.at

    def getEmot(self) -> str:
        return self.emot

    def setAlgorithm(self, algorithm: Algorithm) -> bool:
        if (not (self.isActive) and (algorithm.getAlgParts != None)):
            self.alg = algorithm
            self.at = 0
            self.fin = algorithm.getSize()
            self.isActive = True
            self.emot = self.alg.getAlgParts[self.at].myName()  # updated line
            return False
        return True

    def isActiveMethod(self) -> bool:
        return self.isActive

    def setActiveOne(self, b1: bool) -> bool:
        return self.isActive == b1

    def setActiveTwo(self, isActive_temp: bool):
        self.isActive = isActive_temp

    def act(self, ear: str, skin: str, eye: str) -> str:
        axnStr: str = ""
        if (not self.isActive):
            return axnStr
        if (self.at < self.fin):
            axnStr = self.alg.getAlgParts[self.at].action(ear, skin, eye)
            self.emot = self.alg.getAlgParts[self.at].myName()
            if (self.alg.getAlgParts[self.at].completed()):
                self.incrementAt = True
        return axnStr

    def getMutationLimitOfActiveAlgPart(self) -> int:
        return self.alg.getAlgParts[self.at].getMutationLimit()

    def getFailType(self) -> enumFail:
        return self.alg.getAlgParts[self.at].failure("")


''' DEXPLORER CLASS '''


class DExplorer(DiSkillV2):
    '''
    D class responsible for mutations of algParts (Mutatable)
    '''

    def __init__(self):
        self.failureCounter: int = 0
        self.prevAP: str = ""

    # Override
    def output(self, noiron: Neuron):
        # TODO Auto-generated method stub
        pass

    # Override
    def input(self, ear: str, skin: str, eye: str):
        # TODO Auto-generated method stub
        pass

    def mutate(self, cera: Cerabellum, failure: enumFail):
        AP: str = cera.getEmot()
        '''
        group relies on a naming convention each class in a mutation series must have
        the same class name concated with a number : APMoan1, APMoan2, APMaon3 ...
        '''
        AP = re.sub(r'\d+', '', AP)  # remove numbers from alg part name to account for overall mutations
        # give up ? :
        if ((self.prevAP.count(AP) != 0) and not (failure.name == enumFail.ok.name)):
            self.failureCounter += 1
            if (self.failureCounter > cera.getMutationLimitOfActiveAlgPart()):
                cera.setActiveTwo(False)
                # this.failureCounter = 0;
        else:
            if (not (self.prevAP.count(AP) != 0)):
                self.failureCounter = 0
        self.prevAP = AP
        if (failure.name == "fail"):
            mutant: Mutatable = cera.alg.getAlgParts().get(cera.getAt())
            cera.alg.getAlgParts().set(cera.getAt(), mutant.mutation())
        elif (failure.name == "cloudian"):
            cera.setActiveTwo(False)


''' PRIORITYQUEUE CLASS '''


# A simple implementation of Priority Queue
# using Queue.
class PriorityQueue(object):
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


''' FUSION CLASS '''


class Fusion:
    '''
     * fuses algorithms and sends needed algorithm to a designated cerabellum
     * object for activation
    '''

    def __init__(self, algDurations: dict[str, int]):
        self.AlgDurations: dict[str, int] = None
        self.AlgDurations2: dict[str, int] = {}
        self.emot: str = ""
        self.algQueue: PriorityQueue = PriorityQueue()  # PriorityQueue[Algorithm]
        self.dangerAlgQueue: PriorityQueue = PriorityQueue()  # PriorityQueue[Algorithm]
        self.reqOverload: bool = False  # too many requests
        self.repReq: bool = False  # chobit has already accepted this request
        self.represantations: list[str] = []
        self.dExplorer: DExplorer = DExplorer()
        self.goalsToTrack: list[str] = ["", ""]  # dangerCera, mainCera
        # cerabellums :
        self.dangerCera: Cerabellum = Cerabellum()
        # requip cera
        # fusionCera = (Cerabellum) (new FusionCera(algQueue));
        self.fusionCera: FusionCera = FusionCera(self.algQueue)
        self.mainCera: Cerabellum = Cerabellum()
        # home cera
        # cera = { dangerCera, fusionCera, mainCera };
        self.cera: list[Cerabellum] = [self.dangerCera, self.fusionCera, self.mainCera]
        # end cerabellums
        self.AlgDurations: dict[str, int] = algDurations

    def setAlgQueue(self, shinkei: Neuron):
        # populate cerabellums with algorithms from queues
        for algorithm in shinkei.negativeAlgParts:
            if (len(self.dangerAlgQueue.queue) < 1):
                self.dangerAlgQueue.insert(algorithm.clone())
            else:
                break

        self.repReq = False
        for algorithm in shinkei.algParts:
            self.updateRepresentations()
            if (self.represantations.count(algorithm.getRepresentation) > 0):
                self.repReq = True
                # System.out.println("again with this poo ?");
                continue
            if (len(self.algQueue.queue) < 5):
                self.algQueue.insert(algorithm.clone())
            else:
                break

        self.reqOverload = len(self.algQueue.queue) > 4 and len(shinkei.algParts) > 0
        # empty Neuron
        shinkei.empty()
        if (not (self.dangerCera.isActiveMethod()) and (not (len(self.dangerAlgQueue.queue) == 0))):
            self.dangerCera.setAlgorithm(self.dangerAlgQueue.poll())
            self.goalsToTrack[0] = self.dangerCera.alg.getGoal
            self.goalTrack(self.goalsToTrack[0])
        if (not self.mainCera.isActiveMethod() and not (len(self.algQueue.queue) == 0)):
            self.mainCera.setAlgorithm(self.algQueue.poll())
            self.goalsToTrack[1] = self.mainCera.alg.getGoal
            self.goalTrack(self.goalsToTrack[1])
        self.fuze()

    def getRepReq(self) -> bool:
        # were the same algorithms repeatedly requested ? were the AI negged ?
        return self.repReq

    def getReqOverload(self) -> bool:
        # too many algorithms requested for the time being, queue overlaod (>5 algs)
        return self.reqOverload

    def act(self, ear: str, skin: str, eye: str) -> str:
        result: str = ""
        for i in range(len(self.cera)):
            if (self.cera[i].isActiveMethod()):
                result = self.cera[i].act(ear, skin, eye)
                self.dExplorer.mutate(self.cera[i], self.cera[i].getFailType())
                self.cera[i].advanceInAlg()
                self.emot = self.cera[i].getEmot()
                if (i > 1):
                    n1: int = self.AlgDurations2[self.cera[i].alg.getGoal]
                    self.AlgDurations2[self.cera[i].alg.getGoal] = n1 + 1
                break
            # else(cera notactive) try go home
        return result

    def getEmot(self) -> str:
        return self.emot

    def updateRepresentations(self):
        for algorithm in self.algQueue.queue:
            self.represantations.append(algorithm.getRepresentation)

    def goalTrack(self, goal: str):
        if (not goal in self.AlgDurations2):
            # try to load, if failed :
            self.AlgDurations[goal] = 0
            self.AlgDurations2[goal] = 0
        else:
            self.AlgDurations[goal] = self.AlgDurations2[goal]

    def goalTrackReset(self, goal: str) -> str:
        if (not goal == ""):
            self.AlgDurations2[goal] = 0
        return ""

    def fuze(self):
        if (self.mainCera.isActiveMethod() and not self.fusionCera.isActiveMethod()):
            algRunTime: int = self.AlgDurations[self.mainCera.alg.getGoal]
            algRunTime = algRunTime / 2
            alg1: Algorithm = None
            g1: str = ""
            time1: int = 0
            iterator: Iterable[Algorithm] = iter(self.algQueue.queue)  # Iterator<Algorithm>
            for element in iterator:
                alg1 = element
                g1 = alg1.getGoal
                self.goalTrack(g1)
                time1 = self.AlgDurations[g1]
                if (time1 < algRunTime):
                    self.fusionCera.setAlgorithm(alg1)
                    self.algQueue.queue.remove(alg1)
                    self.fusionCera.setAbort(time1)
                    self.goalTrackReset(g1)
                    break

        self.goalsToTrack[0] = self.goalTrackReset(self.goalsToTrack[0])
        self.goalsToTrack[1] = self.goalTrackReset(self.goalsToTrack[1])


''' FUSIONCERA CLASS '''


class FusionCera(Cerabellum):

    def __init__(self, algQueue: PriorityQueue):
        super().__init__()
        self.abort: int = 0
        self.algQueue: PriorityQueue = algQueue  # PriorityQueue<Algorithm>

    def setAbort(self, abort: int):
        self.abort = abort + 1

    # Override
    def act(self, ear: str, skin: str, eye: str) -> str:
        # TODO Auto-generated method stub
        result: str = super().act(ear, skin, eye)
        self.abort -= 1
        if (self.abort < 1):
            self.setActiveTwo(False)
        else:
            if (not self.isActiveMethod()):
                try:
                    self.algQueue.queue.remove(self.alg)
                except:
                    pass
        return result


''' Thinkable CLASS '''


class Thinkable:
    def think(self, ear: str, skin: str, eye: str) -> str:
        """override me"""
        return ""


'''*********
 *intro *
 ********

 up until now, the LivinGrimoire was on par with the matrix learn scene.
 one line of code to add one skill.

 that is great, that is sci-fi turned real, that is the most significant coding achievement in the history of time.

 but hey why stop there? why only be on par with the matrix and the human brain ?
 what is beyond the matrix level? you already know

 cyberpunk>the matrix.
 one line of code to add a skill, but ALSO! 1 line of code to add a hardware capability.

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
        # algorithms fusion (polymarization)
        self._algDurations: dict[str, int] = {}
        self._fusion: Fusion = Fusion(self._algDurations)
        self._noiron: Neuron = Neuron()
        # added :
        self._kokoro: Kokoro = Kokoro(AbsDictionaryDB())  # soul
        self._lastOutput: str = ""
        # standBy phase 260320
        self._timeGate: TimeGate = TimeGate(5)

    '''set the chobit database
        the database is built as a key value dictionary
        the database can be used with by the Kokoro attribute'''

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

    def setPause(self, pause: int):
        # set standby timegate pause.
        # pause time without output from the chobit
        # means the standby attribute will be true for a moment.
        # it is the equivelant of the chobit being bored
        # the standby attribute can be accessed via the kokoro
        # object within a skill if needed
        self._timeGate.setPause(pause)

    # override
    def think(self, ear: str, skin: str, eye: str) -> str:
        ear = self.translateIn(ear)
        for dCls in self._dClasses:
            self.inOut(dCls, ear, skin, eye)
        self._fusion.setAlgQueue(self._noiron)
        return self.translateOut(self._fusion.act(ear, skin, eye))

    def getSoulEmotion(self) -> str:
        return self._fusion.emot

    def inOut(self, dClass: DiSkillV2, ear: str, skin: str, eye: str):
        dClass.input(ear, skin, eye)  # new
        dClass.output(self._noiron)

    def translateIn(self, earIn: str) -> str:
        # makes sure the chobit doesn't feedback on her own output
        if (earIn == self._lastOutput):
            return ""
        return earIn

    def translateOut(self, outResult: str) -> str:
        # save last output served
        if (outResult != ""):
            self._lastOutput = outResult
            self._timeGate.open(5)
            self._kokoro.standBy = False
        # standBy :
        else:
            if (self._timeGate.isClosed()):
                self._kokoro.standBy = True
                self._timeGate.open(5)
            else:
                self._kokoro.standBy = False
        return outResult

    def getStandby(self) -> bool:
        # this is an under use method
        # only use this for testing
        return self._kokoro.standBy

    def getKokoro(self) -> Kokoro:
        '''several chobits can use the same soul
         this enables telepathic communications
         between chobits in the same project'''
        return self._kokoro

    def setKokoro(self, kokoro: Kokoro):
        # use this for telepathic communication between different chobits objects
        self._kokoro = kokoro

    def getAlgDurations(self) -> dict[str, int]:
        return self._algDurations

    def setAlgDurations(self, algDurations: dict[str, int]):
        '''think cycles run duration per algorithm
         use this method for saving run times if you wish
         algDurations are shallow ref to Fusions' algDurations
         shorter algDurations give algorithms run priority in case several algorithms(sent by skills) want to run
         at the same time'''
        self._algDurations = algDurations

    def getFusion(self) -> Fusion:
        return self._fusion
