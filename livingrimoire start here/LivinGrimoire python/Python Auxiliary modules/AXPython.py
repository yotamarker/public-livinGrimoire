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
        return self._algs[random.randint(0, len(self._algs))]

    def moodAlg(self, mood: int) -> Algorithm:
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

    def handle(self, ear: str, skin: str, eye: str) -> Algorithm:
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
    def __init__(self, limit: int):
        super().__init__()
        self._limit = limit

    # override
    def insert(self, data):
        if super().size() < self._limit:
            super().insert(data)

    # override
    def poll(self) -> str:
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
        '''// an algorithm has been deployed
        // call this method when an algorithm is deployed (in a DiSkillV2 object)'''
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
