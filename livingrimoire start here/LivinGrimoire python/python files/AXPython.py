from __future__ import annotations
from LivinGrimoire23 import *

import random


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

    def __init__(self, *skillsParams: DiSkillV2):
        super().__init__()
        self._skills: list[DiSkillV2] = []
        self._activeSkill: int = 0
        self._tempN: Neuron = Neuron()
        for i in range(0, len(skillsParams)):
            self._skills.append(skillsParams[i])

    def addSkill(self, skill: DiSkillV2) -> SkillHubAlgDispenser:
        # builder pattern
        self._skills.append(skill)
        return self

    def dispenseAlgorithm(self, ear: str, skin: str, eye: str) -> Algorithm:
        # return value to outAlg param of (external) summoner DiskillV2
        self._skills[self._activeSkill].input(ear, skin, eye)
        self._skills[self._activeSkill].output(self._tempN)
        for i in range(1, 6):
            temp: Algorithm = self._tempN.getAlg(i)
            if temp is not None:
                return temp
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


class AXFriend:
    def __init__(self, tolerance: int):
        # recommended 11
        self._active = TrgTolerance(tolerance)
        self.myName: str = "chi"
        self._friendName: str = "null"
        self._needFriend: bool = True
        self.diSkillUtil: DISkillUtils = DISkillUtils()
        self._friendIsActive: bool = False

    def reset(self):
        # should reset once a month
        self.myName = "null"
        self._needFriend = True
        self._active.disable()
        self._friendIsActive = False

    def getFriendName(self):
        return self._friendName

    def friendHandShake(self) -> Algorithm:
        # engage after reset() or at certain time of day if needsFriend, with snooze
        return self.diSkillUtil.simpleVerbatimAlgorithm("friend_request", "i am" + self.myName)

    def getFriendIsActive(self):
        return self._friendIsActive

    def handle(self, ear: str, skin: str, eye: str):
        # returns algorithm or None
        if self._needFriend and ear.__contains__("i am "):
            # register new friend
            self._active.reset()
            self._friendIsActive = self._active.trigger()
            self._friendName = ear.replace("i am ", "")
            self._needFriend = False
            return self.friendHandShake()
        if ear.__contains__(self.myName):
            self._active.reset()
            self._friendIsActive = self._active.trigger()
            return None


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

    def __init__(self, tolerance: int):
        self._algSent = False
        # problems that may result because of the last deployed algorithm:
        self.defcons: UniqueItemSizeLimitedPriorityQueue = UniqueItemSizeLimitedPriorityQueue(5)
        # major chaotic problems that may result because of the last deployed algorithm:
        self.defcon5: UniqueItemSizeLimitedPriorityQueue = UniqueItemSizeLimitedPriorityQueue(5)
        # goals the last deployed algorithm aims to achieve:
        self.goals: UniqueItemSizeLimitedPriorityQueue = UniqueItemSizeLimitedPriorityQueue(5)
        # how many failures / problems till the algorithm needs to mutate (change):
        self.trgTolerance: TrgTolerance = TrgTolerance(tolerance)

    def pendAlg(self):
        """// an algorithm has been deployed
        // call this method when an algorithm is deployed (in a DiSkillV2 object)"""
        self._algSent = True
        self.trgTolerance.trigger()

    def pendAlgWithoutConfirmation(self):
        # an algorithm has been deployed
        self._algSent = True
        '''//no need to await for a thank you or check for goal manifestation :
        // trgTolerance.trigger();
        // using this method instead of the default "pendAlg" is the same as
        // giving importance to the stick and not the carrot when learning
        // this method is mosly fitting work place situations'''

    def mutateAlg(self, input: str) -> bool:
        # recommendation to mutate the algorithm ? true/ false
        if not self._algSent:
            return False  # no alg sent=> no reason to mutate
        if self.goals.contains(input):
            self.trgTolerance.reset()
            self._algSent = False
            return False
        # goal manifested the sent algorithm is good => no need to mutate the alg
        if self.defcon5.contains(input):
            self.trgTolerance.reset()
            self._algSent = False
            return True
        '''// ^ something bad happend probably because of the sent alg
        // recommend alg mutation'''
        if self.defcons.contains(input):
            self._algSent = False
            mutate: bool = not self.trgTolerance.trigger()
            if mutate:
                self.trgTolerance.reset()
            return mutate
        # ^ negative result, mutate the alg if this occures too much
        return False


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
    def __init__(self):
        self._regexUtil: RegexUtil = RegexUtil()

    def convertToInt(self, v1: str) -> int:
        temp: str = self._regexUtil.extractEnumRegex(enumRegexGrimoire.integer, v1)
        if temp == "":
            return 0
        return int(temp)

    def convertToDouble(self, v1: str) -> float:
        temp: str = self._regexUtil.extractEnumRegex(enumRegexGrimoire.double_num, v1)
        if temp == "":
            return 0.0
        return float(temp)


class AXPassword:
    """ code # to open the gate
     while gate is open, code can be changed with: code new_number"""

    def __init__(self):
        self._isOpen: bool = False
        self._maxAttempts: int = 3
        self._loginAttempts = self._maxAttempts
        self._regexUtil: RegexUtil = RegexUtil()
        self._code = 0
        self._typeConverter: LGTypeConverter = LGTypeConverter()

    def codeUpdate(self, ear: str) -> bool:
        # while the gate is toggled on, the password code can be changed
        if not self._isOpen:
            return False
        if ear.__contains__("code"):
            temp: str = self._regexUtil.extractEnumRegex(enumRegexGrimoire.integer, ear)
            if not temp == "":
                # if not temp.isEmpty
                self._code = int(temp)
                return True
        return False

    def openGate(self, ear: str):
        if ear.__contains__("code") and self._loginAttempts > 0:
            tempCode: str = self._regexUtil.extractEnumRegex(enumRegexGrimoire.integer, ear)
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

    def closeGate(self, ear: str):
        if ear.__contains__("close"):
            self._isOpen = False

    def setMaxAttempts(self, max: int):
        self._maxAttempts = max

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

    def generatePermutations(self, *lists: list[list[str]]):
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
        self.converter: LGTypeConverter = LGTypeConverter()
        self.strings: LGFIFO = LGFIFO()
        self._stringsSource: list[str] = []
        for i in range(0, len(values)):
            self.strings.insert(values[i])
            self._stringsSource.append(values[i])

    def addElement(self, element: str):
        self.strings.insert(element)
        self._stringsSource.append(element)

    def drawAndRemove(self) -> str:
        temp: str = self.strings.getRNDElement()
        self.strings.removeItem(temp)
        return temp

    def drawAsIntegerAndRemove(self) -> int:
        temp: str = self.strings.getRNDElement()
        if temp is None:
            return 0
        self.strings.removeItem(temp)
        return self.converter.convertToInt(temp)

    def getSimpleRNDNum(self, lim: int) -> int:
        return random.randint(0, lim)

    def reset(self):
        self.strings.clear()
        for t in self._stringsSource:
            self.strings.insert(t)


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

    def getSimpleRNDNum(self, lim: int) -> int:
        return random.randint(0, lim)

    def reset(self):
        self.strings.clear()
        for t in self._stringsSource:
            self.strings.insert(t)


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
    '''remembers key inputs because they start with keyword
   // also can dispense key inputs'''

    def __init__(self, queLimit: int):
        super().__init__(queLimit)
        self._regexUtil: RegexUtil = RegexUtil()
        self.keyword: str = "say"

    # override
    def insert(self, data):
        temp: str = self._regexUtil.afterWord(self.keyword, data)
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


class InputFilter:
    """filter out non-relevant input
    or filter in relevant data"""

    def input(self, ear: str, skin: str, eye: str) -> str:
        # override me
        pass

    def filter(self, ear: str) -> AXKeyValuePair:
        # override me : key = context/category, value: param
        return AXKeyValuePair()


class Map:
    def __init__(self):
        self._pointDescription: dict[str, str] = {}
        self._descriptionPoint: dict[str, str] = {}
        self._currentPosition: LGPointInt = LGPointInt(0, 0)
        self.regexUtil: RegexUtil = RegexUtil()

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
            p1 = self.regexUtil.pointRegex(value)
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
        self._prev: str = ""

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


class TrgMinute(TrGEV3):
    # trigger true at minute once per hour
    def __init__(self):
        super().__init__()
        self._hour1: int = -1
        self._minute: int = random.randint(0, 60)
        self.pgrd: PlayGround = PlayGround()

    def setMinute(self, minute):
        if -1 < minute < 61:
            self._minute = minute

    # override
    def trigger(self) -> bool:
        temp_hour: int = self.pgrd.getHoursAsInt()
        if temp_hour != self._hour1:
            if self.pgrd.getMinutesAsInt() == self._minute:
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
        self._pl: PlayGround = PlayGround()

    def trigger(self, standBy: bool, ear: str) -> bool:
        """relies on the Kokoro standby boolean
         no input or output for a set amount of time results with a true
         and replenishing the trigger."""
        if self._pl.isNight():
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
        self._playGround: PlayGround = PlayGround()

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
        minutes: int = self._playGround.getMinutesAsInt()
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
        self._regexUtil: RegexUtil = RegexUtil()
        self._pl: PlayGround = PlayGround()
        self._alarm: bool = True

    def setTime(self, v1: str):
        self._t = self._regexUtil.extractEnumRegex(enumRegexGrimoire.simpleTimeStamp, v1)

    def alarm(self) -> bool:
        now: str = self._pl.getCurrentTimeStamp()
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
    def __init__(self):
        self.key: str = ""
        self.value: str = ""


class LGTypeConverter:
    # converts strings types to number typed variables
    def __init__(self):
        self.r1: RegexUtil = RegexUtil()

    def convertToInt(self, v1: str) -> int:
        temp: str = self.r1.extractEnumRegex(enumRegexGrimoire.integer, v1)
        if temp == "":
            return 0
        return int(temp)

    def convertToFloat(self, v1: str) -> int:
        temp: str = self.r1.extractEnumRegex(enumRegexGrimoire.double_num, v1)
        if temp == "":
            return 0
        return float(temp)

    def convertToFloat(self, v1: str, precision: int) -> int:
        # precision: how many numbers after the .
        temp: str = self.r1.extractEnumRegex(enumRegexGrimoire.double_num, v1)
        if temp == "":
            return 0
        return round(float(temp), precision)


class TODOListManager:
    '''manages to do tasks.
    q1 tasks are mentioned once, and forgotten
    backup tasks are the memory of recently mentioned tasks'''

    def __init__(self, todoLim: int):
        self._q1: UniqueItemSizeLimitedPriorityQueue = UniqueItemSizeLimitedPriorityQueue(todoLim)
        self._backup: UniqueItemSizeLimitedPriorityQueue = UniqueItemSizeLimitedPriorityQueue(todoLim)

    def addTask(self, e1: str):
        self._q1.insert(e1)

    def getTask(self) -> str:
        temp: str = self._q1.poll()
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
    def __init__(self, allStrategies: DrawRnd, strategiesLim: int):
        # bank of all strategies. out of this pool active strategies are pulled
        self._allStrategies: DrawRnd = allStrategies
        self._strategiesLim = strategiesLim
        # active strategic options
        self._activeStrategy: UniqueItemSizeLimitedPriorityQueue = UniqueItemSizeLimitedPriorityQueue(strategiesLim)

    def evolveStrategies(self):
        # add N strategic options to the active strategies bank, from the total strategy bank
        temp: str = self._allStrategies.drawAndRemove()
        for i in range(0, self._strategiesLim):
            if temp == "":
                break
            self._activeStrategy.insert(temp)
            temp = self._allStrategies.drawAndRemove()
        self._allStrategies.reset()

    def getStrategy(self) -> str:
        return self._activeStrategy.getRNDElement()


class AXStrategy:
    '''this auxiliary module is used to output strategies based on context
        can be used for battles, and games
        upon pain/lose use the evolve methode to update to different new active strategies
        check for battle state end externaly (opponent state/hits on rival counter)
    a dictionary of strategies'''

    def __init__(self):
        self.strategies: dict[str, Strategy] = {}

    def addStrategy(self, context: str, techniques: DrawRnd, lim: int):
        # add strategies per context
        # lim is the limit of active strategies, it should be less than
        # the total strategies in techniques
        temp: Strategy = Strategy(techniques, lim)
        self.strategies[context] = temp

    def evolve(self):
        # replace active strategies
        key: str = ""
        for k in self.strategies.keys():
            self.strategies[k].evolveStrategies()

    def process(self, context: str) -> str:
        # process input, return action based on game context now
        if context in self.strategies:
            return self.strategies[context].getStrategy()
        return ""


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

    def setPause(self):
        # set pause between question to wait for answer
        self._outputDripper.setLimit()


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
        if not key in self.dic:
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
class DiSysOut(DiSkillV2):
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
        self._playGround: PlayGround = PlayGround()
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
            self._timeStamp = self._playGround.getFutureInXMin(self._minutes)
            self._trgTime.setTime(self._timeStamp)
            return True
        return False

    # override
    def reset(self):
        self._timeStamp = self._playGround.getCurrentTimeStamp()


class Cron(TrGEV3):
    # triggers true, limit times, after initial time, and every minutes interval
    # counter resets at initial time, assuming trigger method was run
    def __init__(self, startTime: str, minutes: int, limit: int):
        self._playGround: PlayGround = PlayGround()
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

    def setMinutes(self, minutes: int):
        if minutes > -1:
            self._minutes = minutes

    # override
    def trigger(self) -> bool:
        # delete counter = 0 if you don't want the trigger to work the next day
        if self._counter == self._limit:
            self._trgTime.setTime(self._initislTimeStamp)
            self._counter = 0
            return False
        if self._trgTime.alarm():
            self._timeStamp = self._playGround.getFutureInXMin(self._minutes)
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


class AXTimeContextResponder:
    # output reply based on the part of day as context
    def __init__(self):
        self._pl: PlayGround = PlayGround()
        self.morning: Responder = Responder()
        self.afternoon: Responder = Responder()
        self.evening: Responder = Responder()
        self.night: Responder = Responder()
        self._responders: dict[str, Responder] = {"morning": self.morning, "afternoon": self.afternoon,
                                                  "evening": self.evening, "night": self.night}

    def respond(self) -> str:
        return self._responders[self._pl.partOfDay()].getAResponse()


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
        self.regexUtil: RegexUtil = RegexUtil()
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
        params = self.regexUtil.extractAllRegexes("(\\w+)(?= #)", result)
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
        self.wordToList[kv.getKey()].add(kv.getValue())
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
        category = self.regexUtil.afterWord(self.conjuration, s1)
        if category not in self.wordToList:
            return
        param = s1.replace("{} {}".format(self.conjuration, category), "").strip()
        self.wordToList[category].insert(param)
        self.allParamRef[param] = category
        self.loggedParams.insert(s1)

    def addParamFromAXPrompt(self, kv):
        if kv.getKey() not in self.wordToList:
            return
        self.wordToList[kv.getKey()].add(kv.getValue())
        self.allParamRef[kv.getValue()] = kv.getKey()

    def addRefreshQ(self, category, q1: RefreshQ):
        if category not in self.wordToList:
            temp = RefreshQ(1)
            return
        self.wordToList[category] = q1

    def getALoggedParam(self):
        return self.loggedParams.getRNDElement()


class Prompt:
    def __init__(self):
        self.regexUtil: RegexUtil = RegexUtil()
        self.kv: AXKeyValuePair = AXKeyValuePair()
        self.prompt: str = ""
        self.regex: str = ""
        self.kv.key = "default"

    def getPrompt(self):
        return self.prompt

    def setPrompt(self, prompt) -> str:
        self.prompt = prompt

    def process(self, in1) -> bool:
        self.kv.value = self.regexUtil.extractRegex(self.regex, in1)
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
        self.kv: AXKeyValuePair = None

    def addPrompt(self, p1):
        self.prompts.append(p1)

    def getPrompt(self) -> Prompt:
        if len(self.prompts) == 0:
            return ""
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

    def learn(self, ear: str):
        if self._q1.contains(ear):
            self._q2.insert(ear)
            return
        self._q1.insert(ear)

    def isAnnoyed(self, ear: str) -> bool:
        return self._q2.strContainsResponse(ear)


class AXNPC2(AXNPC):

    def __init__(self, replyStockLim: int, outputChance: int):
        super().__init__(replyStockLim, outputChance)
        self.annoyedQue: AnnoyedQ = AnnoyedQ(5)

    def strLearn(self, ear: str):
        # learns inputs containing strings that are repeatedly used by others
        self.annoyedQue.learn(ear)
        if self.annoyedQue.isAnnoyed(ear):
            self.responder.insert(ear)


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
        self.__questions = Responder("will i", "can i expect", "should i", "may i", "is it a good idea",
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
                self.__isActive = False;
                return True  # shout out was replied!

        # unrelated reply to shout out, shout out context is outdated
        self.__isActive = False
        return False


class AXHandshake:
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
