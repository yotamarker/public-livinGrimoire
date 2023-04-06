from __future__ import annotations
from LivinGrimoireCoreV2 import *

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

    def dispenseAlgorithm(self, ear: str, skin: str, eye: str):
        # return value to outAlg param of (external) summoner DiskillV2
        self._skills[self._activeSkill].input(ear, skin, eye)
        self._tempN.empty()
        self._skills[self._activeSkill].output(self._tempN)
        if not self._tempN.negativeAlgParts:
            return self._tempN.negativeAlgParts[0]
        if not self._tempN.algParts:
            return self._tempN.algParts[0]
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
        self._repeates: int = maxrepeats

    def reset(self):
        # refill trigger
        self._repeates = self._maxrepeats

    def trigger(self) -> bool:
        # will return true till depletion or reset()
        if self._repeates > 0:
            self._repeates -= 1
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


class UniqueItemSizeLimitedPriorityQueue(UniqueItemsPriorityQue):
    # items in the queue are unique and do not repeat
    # the size of the queue is limited
    # this cls can also be used to detect repeated elements (nagging or reruns)
    def __init__(self, limit: int):
        super().__init__()
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
            return self.trgTolerance.trigger()
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
        if (self._isOpen):
            return self._code
        return -1


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


class DrawRnd:
    # draw a random element, than take said element out
    def __init__(self, *values: str):
        self.converter: LGTypeConverter = LGTypeConverter()
        self.strings: LGFIFO = LGFIFO()
        for i in range(0, len(values)):
            self.strings.insert(values[i])

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

    def getSimpleRNDNum(self: int) -> int:
        return random.randint(0, self)


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
