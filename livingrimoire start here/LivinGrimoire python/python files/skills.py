from __future__ import annotations
from LivinGrimoire23 import *
from AXPython import *


class DiMisser(DiSkillV2):
    def __init__(self):
        self._pl: PlayGround = PlayGround()
        self._cron: Cron = Cron("15:00", 50, 2)
        self._responder: Responder = Responder("welcome", "i have missed you", "welcome back")
        super().__init__()

    # Override
    def input(self, ear: str, skin: str, eye: str):
        if ear == "i am home":
            self._cron.setStartTime(self._pl.getPastInXMin(10))
            self.setVerbatimAlg(4, self._responder.getAResponse())
            return
        if self._cron.trigger():
            n: int = self._cron.getCounter()
            match n:
                case _:
                    self.setVerbatimAlg(4, f'hmph {n}')


class DiBurper(DiSkillV2):
    def __init__(self, burps_per_hour: int):
        self._burpsPerHour = 2
        if 60 > burps_per_hour > 0:
            self._burpsPerHour = burps_per_hour
        self._trgMinute: TrgMinute = TrgMinute()
        self._trgMinute.setMinute(0)
        self._responder1: Responder = Responder("burp", "burp2", "burp3")
        self._draw: DrawRndDigits = DrawRndDigits()
        self._burpMinutes: LGFIFO = LGFIFO()
        self._pl: PlayGround = PlayGround()
        for i in range(1, 60):
            self._draw.addElement(i)
        for i in range(0, burps_per_hour):
            self._burpMinutes.insert(self._draw.drawAndRemove())
        super().__init__()

    # Override
    def input(self, ear: str, skin: str, eye: str):
        # night? do not burp
        if self._pl.partOfDay() == "night":
            return
        # reset burps
        if self._trgMinute.trigger():
            self._burpMinutes.clear()
            self._draw.reset()
            for i in range(0, self._burpsPerHour):
                self._burpMinutes.insert(self._draw.drawAndRemove())
            return
        # burp
        now_minutes: int = self._pl.getMinutesAsInt()
        if self._burpMinutes.contains(now_minutes):
            self._burpMinutes.removeItem(now_minutes)
            self.setVerbatimAlg(4, self._responder1.getAResponse())


class DiSneezer(DiSkillV2):
    # the skill simulates sneezing as a result of cold temperature
    def __init__(self, sneezes_per_hour: int):
        self._sneezesPerHour = 2
        if 60 > sneezes_per_hour > 0:
            self._sneezesPerHour = sneezes_per_hour
        self._trgMinute: TrgMinute = TrgMinute()
        self._trgMinute.setMinute(0)
        self._responder1: Responder = Responder("sneeze", "achoo", "atchoo", "achew", "atisshoo")
        self._draw: DrawRndDigits = DrawRndDigits()
        self._sneezeMinutes: LGFIFO = LGFIFO()
        self._pl: PlayGround = PlayGround()
        for i in range(1, 60):
            self._draw.addElement(i)
        for i in range(0, sneezes_per_hour):
            self._sneezeMinutes.insert(self._draw.drawAndRemove())
        super().__init__()

    # Override
    def input(self, ear: str, skin: str, eye: str):
        # reset sneezes
        if ear.__contains__("cold"):
            self._sneezeMinutes.clear()
            self._draw.reset()
            for i in range(0, self._sneezesPerHour):
                self._sneezeMinutes.insert(self._draw.drawAndRemove())
            return
        # burp
        now_minutes: int = self._pl.getMinutesAsInt()
        if self._sneezeMinutes.contains(now_minutes):
            self._sneezeMinutes.removeItem(now_minutes)
            self.setVerbatimAlg(4, self._responder1.getAResponse())


class DiPetv3(DiSkillV2):
    # chirp, learn replies and reply back occasionally.
    def __init__(self, chirps_per_hour: int):
        self._chirpsPerHour = 2
        if 60 > chirps_per_hour > 0:
            self._chirpsPerHour = chirps_per_hour
        self._trgMinute: TrgMinute = TrgMinute()
        self._trgMinute.setMinute(0)
        self._responder1: Responder1Word = Responder1Word()
        self._allMinutes: DrawRndDigits = DrawRndDigits()
        self._chirpMinutes: LGFIFO = LGFIFO()
        self._pl: PlayGround = PlayGround()
        for i in range(1, 60):
            self._allMinutes.addElement(i)
        for i in range(0, chirps_per_hour):
            self._chirpMinutes.insert(self._allMinutes.drawAndRemove())
        super().__init__()

    # Override
    def input(self, ear: str, skin: str, eye: str):
        self._responder1.listen(ear)
        # night? do not burp
        if self._pl.partOfDay() == "night":
            return
        # reset chirps as hour starts
        if self._trgMinute.trigger():
            self._chirpMinutes.clear()
            self._allMinutes.reset()
            for i in range(0, self._chirpsPerHour):
                self._chirpMinutes.insert(self._allMinutes.drawAndRemove())
            return
        # chirp
        now_minutes: int = self._pl.getMinutesAsInt()
        if self._chirpMinutes.contains(now_minutes):
            self._chirpMinutes.removeItem(now_minutes)
            self.setVerbatimAlg(3, self._responder1.getAResponse())


class DiReplier(DiSkillV2):
    # chirp, learn replies and reply back occasionally.
    def __init__(self):
        self._responder1: Responder1Word = Responder1Word()
        self._rnd: DrawRnd = DrawRnd()
        super().__init__()

    # Override
    def input(self, ear: str, skin: str, eye: str):
        # night? return;
        if self._responder1.contains(ear):
            n: int = self._rnd.getSimpleRNDNum(100)
            if n < 100:
                temp: str = self._responder1.getAResponse()
                self.setVerbatimAlg(4, temp)
        # listen n learn recent single words
        self._responder1.listen(ear)


class DiHabit(DiSkillV2):
    def __init__(self):
        super().__init__()
        # setter params:
        # habit params
        self._habitsPositive: UniqueItemSizeLimitedPriorityQueue = UniqueItemSizeLimitedPriorityQueue(15)
        self._habitP: AXCmdBreaker = AXCmdBreaker("i should")
        self._temp: str = ""
        # bad habits
        self._habitsNegative: UniqueItemSizeLimitedPriorityQueue = UniqueItemSizeLimitedPriorityQueue(5)
        self._habitN: AXCmdBreaker = AXCmdBreaker("i must not")
        # dailies
        self._dailies: UniqueItemSizeLimitedPriorityQueue = UniqueItemSizeLimitedPriorityQueue(3)
        self._dailyCmdBreaker: AXCmdBreaker = AXCmdBreaker("i out to")
        # weekends
        self._weekends: UniqueItemSizeLimitedPriorityQueue = UniqueItemSizeLimitedPriorityQueue(3)
        self._weekendCmdBreaker: AXCmdBreaker = AXCmdBreaker("i have to")
        # expirations
        self._expirations: UniqueItemSizeLimitedPriorityQueue = UniqueItemSizeLimitedPriorityQueue(3)
        self._expirationsCmdBreaker: AXCmdBreaker = AXCmdBreaker("i got to")
        # to-do list
        self._todo: TODOListManager = TODOListManager(5)
        self._toDoCmdBreaker: AXCmdBreaker = AXCmdBreaker("i need to")
        self._clearCmdBreaker: AXCmdBreaker = AXCmdBreaker("clear")
        # getter param
        self._getterCmdBreaker: AXCmdBreaker = AXCmdBreaker("random")
        # gamification modules for shallow ref in other skills
        self._gamification: AXGamification = AXGamification()
        self._punishments: AXGamification = AXGamification()

    def getGamification(self) -> AXGamification:
        return self._gamification

    def getPunishments(self) -> AXGamification:
        return self._punishments

    # Override
    def input(self, ear: str, skin: str, eye: str):
        if ear == "":
            return
        # setters
        if ear.__contains__("i"):
            self._temp = self._habitP.extractCmdParam(ear)
            if not (self._temp == ""):
                self._habitsPositive.insert(self._temp)
                self._temp = ""
                self.setVerbatimAlg(4, "habit registered")
                return
            self._temp = self._habitN.extractCmdParam(ear)
            if not (self._temp == ""):
                self._habitsNegative.insert(self._temp)
                self._temp = ""
                self.setVerbatimAlg(4, "bad habit registered")
                return
            self._temp = self._dailyCmdBreaker.extractCmdParam(ear)
            if not (self._temp == ""):
                self._dailies.insert(self._temp)
                self._temp = ""
                self.setVerbatimAlg(4, "daily registered")
                return
            self._temp = self._weekendCmdBreaker.extractCmdParam(ear)
            if not (self._temp == ""):
                self._weekends.insert(self._temp)
                self._temp = ""
                self.setVerbatimAlg(4, "prep registered")
                return
            self._temp = self._expirationsCmdBreaker.extractCmdParam(ear)
            if not (self._temp == ""):
                self._expirations.insert(self._temp)
                self._temp = ""
                self.setVerbatimAlg(4, "expiration registered")
                return
            self._temp = self._toDoCmdBreaker.extractCmdParam(ear)
            if not (self._temp == ""):
                self._todo.addTask(self._temp)
                self._temp = ""
                self.setVerbatimAlg(4, "task registered")
                return
        # getters
        self._temp = self._getterCmdBreaker.extractCmdParam(ear)
        if not (self._temp == ""):
            match self._temp:
                case 'habit':
                    self.setVerbatimAlg(4, getOrDefault(self._habitsPositive.getRNDElement(), "no habits registered"))
                    return
                case 'bad habit':
                    self.setVerbatimAlg(4,
                                        getOrDefault(self._habitsNegative.getRNDElement(), "no bad habits registered"))
                    return
                case 'daily':
                    self.setVerbatimAlg(4, getOrDefault(self._dailies.getRNDElement(), "no dailies registered"))
                    return
                case "prep":
                    self.setVerbatimAlg(4, getOrDefault(self._weekends.getRNDElement(), "no preps registered"))
                    return
                case "expiration":
                    if len(self._expirations.getAsList()) == 0:
                        self.setVerbatimAlg(4, "no expirations registered")
                        return
                    l1: list[str] = []
                    q1 = self._expirations.getAsList()
                    for i in range(0, len(q1)):
                        l1.append(q1[i])
                    self.setVebatimAlgFromList(4, l1)
                    return
                case "task":
                    self.setVerbatimAlg(4, getOrDefault(self._todo.getTask(), "no new tasks registered"))
                    return
                case "to do":
                    self.setVerbatimAlg(4, getOrDefault(self._todo.getOldTask(), "no tasks registered"))
                    return
        # engagers
        if ear.__contains__("completed"):
            if not (self._diSkillUtils.strContainsList(ear, self._habitsPositive.getAsList()) == ""):
                self._gamification.increment()
                self.setVerbatimAlg(4, "good boy")
                return
            if not (self._diSkillUtils.strContainsList(ear, self._habitsNegative.getAsList()) == ""):
                self._punishments.increment()
                self.setVerbatimAlg(4, "bad boy")
                return
            if not (self._diSkillUtils.strContainsList(ear, self._dailies.getAsList()) == ""):
                self._gamification.increment()
                self.setVerbatimAlg(4, "daily engaged")
                return
            if not (self._diSkillUtils.strContainsList(ear, self._weekends.getAsList()) == ""):
                self.setVerbatimAlg(4, "prep engaged")
                return
            # expiration gamification redacted
        # clear specific fields
        match ear:
            case 'clear habits':
                self._habitsPositive.clear()
                self.setVerbatimAlg(4, "habits cleared")
                return
            case 'clear bad habits':
                self._habitsNegative.clear()
                self.setVerbatimAlg(4, "bad habits cleared")
                return
            case 'clear dailies':
                self._dailies.clear()
                self.setVerbatimAlg(4, "dailies cleared")
                return
            case 'clear preps':
                self._weekends.clear()
                self.setVerbatimAlg(4, "preps cleared")
                return
            case 'clear expirations':
                self._expirations.clear()
                self.setVerbatimAlg(4, "expirations cleared")
                return
            case 'clear tasks':
                self._todo.clearAllTasks()
                self.setVerbatimAlg(4, "tasks cleared")
                return
            case 'clear all habits':
                self._habitsPositive.clear()
                self._habitsNegative.clear()
                self._dailies.clear()
                self._weekends.clear()
                self._expirations.clear()
                self._todo.clearAllTasks()
                self.setVerbatimAlg(4, "all habits cleared")
                return
            case _:
                if ear.__contains__("clear"):
                    self._temp = self._clearCmdBreaker(ear)
                    if self._todo.containsTask(self._temp):
                        self._todo.clearTask(self._temp)
                        self.setVerbatimAlg(4, f'{self._temp} task cleared')
                        self._temp = ""
