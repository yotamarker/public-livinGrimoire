from __future__ import annotations
# from LivinGrimoire23 import *
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

    def setBurps(self, burpings: Responder) -> DiBurper:
        # set sounds of burp events
        self._responder1 = burpings
        return self

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
            if n < 35:
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
                    self._temp = self._clearCmdBreaker.extractCmdParam(ear)
                    if self._todo.containsTask(self._temp):
                        self._todo.clearTask(self._temp)
                        self.setVerbatimAlg(4, f'{self._temp} task cleared')
                        self._temp = ""


class TheShell(DiSkillV2):
    def __init__(self, b1: ShBrain):
        super().__init__()
        self.shellChobit: Chobits = Chobits()
        self.logicChobit: Chobits = b1.logicChobit
        self.hardwareChobit: Chobits = b1.hardwareChobit
        self.shellChobit.addSkill(self)
        self.logicSkills: [str, DiSkillV2] = {}
        self.hardwareSkills: [str, DiSkillV2] = {}
        self.installer: AXCmdBreaker = AXCmdBreaker("install")
        self.uninstaller: AXCmdBreaker = AXCmdBreaker("abolish")
        self.temp: str = ""

    def addLogicalSkill(self, skillName: str, skill: DiSkillV2):
        self.logicSkills[skillName] = skill

    def addHardwareSkill(self, skillName: str, skill: DiSkillV2):
        self.hardwareSkills[skillName] = skill

    # shell methods
    def _sh_addSkill(self, skillKey) -> int:
        if not (skillKey in self.logicSkills or skillKey in self.hardwareSkills):
            return 0  # skill does not exist
        # find the skill
        if skillKey in self.logicSkills:
            ref: DiSkillV2 = self.logicSkills[skillKey]
            if self.logicChobit.containsSkill(ref):
                return 1  # logic skill already installed
            self.logicChobit.addSkill(ref)
            return 2  # logic skill has been installed
        ref: DiSkillV2 = self.hardwareSkills[skillKey]
        if self.hardwareChobit.containsSkill(ref):
            return 3  # hardware skill already installed
        self.hardwareChobit.addSkill(ref)
        return 4  # hardware skill has been installed

    def _sh_removeSkill(self, skillKey) -> int:
        if not (skillKey in self.logicSkills or skillKey in self.hardwareSkills):
            return 0  # skill does not exist
        if skillKey in self.logicSkills:
            ref: DiSkillV2 = self.logicSkills[skillKey]
            if self.logicChobit.containsSkill(ref):
                self.logicChobit.removeSkill(ref)
                return 1  # logic skill has been uninstalled
            return 2  # logic skill is not installed
        ref: DiSkillV2 = self.hardwareSkills[skillKey]
        # ref: DiSkillV2 = self.hardwareChobit[skillKey]
        if self.hardwareChobit.containsSkill(ref):
            self.hardwareChobit.removeSkill(ref)
            return 3  # hardware skill has been uninstalled
        return 4  # hardware skill is not installed

    def _sh_removeAllSkills(self):
        self.logicChobit.clearSkills()
        self.hardwareChobit.clearSkills()

    # Override
    def input(self, ear: str, skin: str, eye: str):
        if ear == "remove all skills":
            self._sh_removeAllSkills()
        self.temp = self.installer.extractCmdParam(ear)
        if self.temp:  # string is not empty?
            match (self._sh_addSkill(self.temp)):
                case 0:
                    self.setVerbatimAlg(4, "skill does not exist")
                case 1:
                    self.setVerbatimAlg(4, "logic skill already installed")
                case 2:
                    self.setVerbatimAlg(4, "logic skill has been installed")
                case 3:
                    self.setVerbatimAlg(4, "hardware skill already installed")
                case 4:
                    self.setVerbatimAlg(4, "hardware skill has been installed")
            self.temp = ""
            return
        self.temp = self.uninstaller.extractCmdParam(ear)
        if self.temp:  # string is not empty?
            match (self._sh_removeSkill(self.temp)):
                case 0:
                    self.setVerbatimAlg(4, "skill does not exist")
                case 1:
                    self.setVerbatimAlg(4, "logic skill has been uninstalled")
                case 2:
                    self.setVerbatimAlg(4, "logic skill is not installed")
                case 3:
                    self.setVerbatimAlg(4, "hardware skill has been uninstalled")
                case 4:
                    self.setVerbatimAlg(4, "hardware skill is not installed")
            self.temp = ""


class ShBrain(Brain):
    def __init__(self):
        super().__init__()
        self._shell: TheShell = TheShell(self)
        self._temp: str = ""

    def addLogicalSkill(self, skillName: str, skill: DiSkillV2):
        self._shell.addLogicalSkill(skillName, skill)

    def addHardwareSkill(self, skillName: str, skill: DiSkillV2):
        self._shell.addHardwareSkill(skillName, skill)

    def setShell(self, newShell: TheShell):
        #  for using TheShell skill subclass objects with different input
        #  method logic
        self._shell = newShell

    # Override
    def doIt(self, ear: str, skin: str, eye: str):
        self._temp = self._shell.shellChobit.think(ear, skin, eye)
        if not self._temp:  # is empty?
            super().doIt(ear, skin, eye)
            return
        self.hardwareChobit.think(self._temp, skin, eye)


class DiMagic8Ball(DiSkillV2):
    def __init__(self):
        super().__init__()
        self.magic8Ball: Magic8Ball = Magic8Ball()

    # Override
    def input(self, ear: str, skin: str, eye: str):
        # skill logic:
        if self.magic8Ball.engage(ear):
            self.setVerbatimAlg(4, self.magic8Ball.reply())


class DiTime(DiSkillV2):
    def __init__(self):
        super().__init__()
        self.__pl: PlayGround = PlayGround()

    # Override
    def input(self, ear: str, skin: str, eye: str):
        match ear:
            case "what is the date":
                self.setVerbatimAlg(4,
                                    f'{self.__pl.getCurrentMonthDay()} {self.__pl.getCurrentMonthName()} {self.__pl.getYearAsInt()}')
            case "what is the time":
                self.setVerbatimAlg(4, self.__pl.getCurrentTimeStamp())
            case "honey bunny":
                self.setVerbatimAlg(4, "bunny honey")
            case "i am sleepy":
                self.setSimpleAlg(
                    "Chi… Chi knows it’s late. Sleep, sleep is good. When you sleep, you can dream. Dreams, dreams are nice. Tomorrow, lots to do. If sleep now, can do best tomorrow. So, let’s sleep. Good night… zzz…")
            case "which day is it":
                self.setVerbatimAlg(4, self.__pl.getDayOfDWeek())
            case "good morning":
                self.setVerbatimAlg(4, f'good {self.__pl.partOfDay()}')  # fstring
            case "good night":
                self.setVerbatimAlg(4, f'good {self.__pl.partOfDay()}')  # fstring
            case "good afternoon":
                self.setVerbatimAlg(4, f'good {self.__pl.partOfDay()}')  # fstring
            case "good evening":
                self.setVerbatimAlg(4, f'good {self.__pl.partOfDay()}')  # fstring
            case "which month is it":
                self.setVerbatimAlg(4, self.__pl.getCurrentMonthName())
            case "which year is it":
                self.setVerbatimAlg(4, f'{self.__pl.getYearAsInt()}')
            case "what is your time zone":
                self.setVerbatimAlg(4, self.__pl.getLocal())
            case "when is the first":
                self.setVerbatimAlg(4, self.__pl.nxtDayOnDate(1))
            case "when is the second":
                self.setVerbatimAlg(4, self.__pl.nxtDayOnDate(2))
            case "when is the third":
                self.setVerbatimAlg(4, self.__pl.nxtDayOnDate(3))
            case "when is the fourth":
                self.setVerbatimAlg(4, self.__pl.nxtDayOnDate(4))
            case "when is the fifth":
                self.setVerbatimAlg(4, self.__pl.nxtDayOnDate(5))
            case "when is the sixth":
                self.setVerbatimAlg(4, self.__pl.nxtDayOnDate(6))
            case "when is the seventh":
                self.setVerbatimAlg(4, self.__pl.nxtDayOnDate(7))
            case "when is the eighth":
                self.setVerbatimAlg(4, self.__pl.nxtDayOnDate(8))
            case "when is the ninth":
                self.setVerbatimAlg(4, self.__pl.nxtDayOnDate(9))
            case "when is the tenth":
                self.setVerbatimAlg(4, self.__pl.nxtDayOnDate(10))
            case "when is the eleventh":
                self.setVerbatimAlg(4, self.__pl.nxtDayOnDate(11))
            case "when is the twelfth":
                self.setVerbatimAlg(4, self.__pl.nxtDayOnDate(12))
            case "when is the thirteenth":
                self.setVerbatimAlg(4, self.__pl.nxtDayOnDate(13))
            case "when is the fourteenth":
                self.setVerbatimAlg(4, self.__pl.nxtDayOnDate(14))
            case "when is the fifteenth":
                self.setVerbatimAlg(4, self.__pl.nxtDayOnDate(15))
            case "when is the sixteenth":
                self.setVerbatimAlg(4, self.__pl.nxtDayOnDate(16))
            case "when is the seventeenth":
                self.setVerbatimAlg(4, self.__pl.nxtDayOnDate(17))
            case "when is the eighteenth":
                self.setVerbatimAlg(4, self.__pl.nxtDayOnDate(18))
            case "when is the nineteenth":
                self.setVerbatimAlg(4, self.__pl.nxtDayOnDate(19))
            case "when is the twentieth":
                self.setVerbatimAlg(4, self.__pl.nxtDayOnDate(20))
            case "when is the twenty first":
                self.setVerbatimAlg(4, self.__pl.nxtDayOnDate(21))
            case "when is the twenty second":
                self.setVerbatimAlg(4, self.__pl.nxtDayOnDate(22))
            case "when is the twenty third":
                self.setVerbatimAlg(4, self.__pl.nxtDayOnDate(23))
            case "when is the twenty fourth":
                self.setVerbatimAlg(4, self.__pl.nxtDayOnDate(24))
            case "when is the twenty fifth":
                self.setVerbatimAlg(4, self.__pl.nxtDayOnDate(25))
            case "when is the twenty sixth":
                self.setVerbatimAlg(4, self.__pl.nxtDayOnDate(26))
            case "when is the twenty seventh":
                self.setVerbatimAlg(4, self.__pl.nxtDayOnDate(27))
            case "when is the twenty eighth":
                self.setVerbatimAlg(4, self.__pl.nxtDayOnDate(28))
            case "when is the twenty ninth":
                self.setVerbatimAlg(4, self.__pl.nxtDayOnDate(29) if (self.__pl.nxtDayOnDate(29) != "") else "never")
            case "when is the thirtieth":
                self.setVerbatimAlg(4, self.__pl.nxtDayOnDate(30) if (self.__pl.nxtDayOnDate(30) != "") else "never")
            case "when is the thirty first":
                self.setVerbatimAlg(4, self.__pl.nxtDayOnDate(31) if (self.__pl.nxtDayOnDate(31) != "") else "never")
            case "incantation 0":
                self.setVerbatimAlg(5, "fly", "bless of magic caster", "infinity wall", "magic ward holy",
                                    "life essence")
            case "evil laugh":
                self.setSimpleAlg("bwahaha mwahaha")


class DiCron(DiSkillV2):
    def __init__(self):
        super().__init__()
        self.__sound: str = "snore"
        self.__cron: Cron = Cron("12:05", 40, 2)

    # setters
    def setSound(self, sound: str) -> DiCron:
        self.__sound = sound
        return self

    def setCron(self, cron: Cron) -> DiCron:
        self.__cron = cron
        return self

    # Override
    def input(self, ear: str, skin: str, eye: str):
        if self.__cron.trigger():
            self.setSimpleAlg(self.__sound)


class DiBlabber(DiSkillV2):
    def __init__(self, skill_name: str):
        super().__init__()
        # skill toggle
        self.__isActive: bool = True
        self.skillToggler: AXContextCmd = AXContextCmd()
        self.skillToggler.contextCommands.insert(f'toggle {skill_name}')
        self.skillToggler.commands.insert("again")
        self.skillToggler.commands.insert("repeat")
        # chat mode select
        self.modeSwitch: AXContextCmd = AXContextCmd()
        self.modeSwitch.contextCommands.insert(f'switch {skill_name} mode')
        self.modeSwitch.commands.insert("again")
        self.modeSwitch.commands.insert("repeat")
        self._mode: Cycler = Cycler(1)
        self._mode.setToZero()  # default mode : pal chatbot (chatbot1)
        # engage chatbot
        self.engage: AXContextCmd = AXContextCmd()
        self.engage.contextCommands.insert(f'engage {skill_name}')
        self.engage.commands.insert("talk to me")
        self.engage.commands.insert("again")
        self.engage.commands.insert("repeat")
        # chatbots
        self.chatbot1: AXNPC2 = AXNPC2(30, 90)  # pal mode chat module
        self.chatbot2: AXNPC2 = AXNPC2(30, 90)  # discreet mode chat module
        # auto mode
        self.__autoEngage: Responder = Responder("engage automatic mode", "automatic mode", "auto mode")
        self.__shutUp: Responder = Responder("stop", "shut up", "silence", "be quite", "be silent")
        self.__tg: TimeGate = TimeGate(5)
        self.__nPCPlus: int = 5  # increase rate of output in self auto reply mode
        self.__nPCNeg: int = -10  # decrease rate of output in self auto reply mode

    # Override
    def input(self, ear: str, skin: str, eye: str):
        # skill toggle:
        if self.skillToggler.engageCommand(ear):
            self.__isActive = not self.__isActive
        if not self.__isActive:
            return
        # chatbot mode: switch mode
        if self.modeSwitch.engageCommand(ear):
            self._mode.cycleCount()
            self.setSimpleAlg(self.talkMode())
            return
        match self._mode.getMode():
            case 0:
                self.mode0(ear)
            case 1:
                self.mode1(ear)

    def talkMode(self) -> str:
        match self._mode.getMode():
            case 0:
                return "friend mode"
            case 1:
                return "discreet mode"
        return "mode switched"

    # chat module common tasks
    def NPCUtilization(self, npc: AXNPC2, ear: str):
        result: str = ""
        # engage
        if self.engage.engageCommand(ear):
            result = npc.respond()
            if not result == "":
                self.setSimpleAlg(result)
                return
        # str engage
        result = npc.strRespond(ear)
        if not result == "":
            self.setSimpleAlg(result)
        # forced learn (say n)
        if not npc.learn(ear):
            # str learn
            npc.strLearn(ear)

    def mode0(self, ear: str):
        if not len(super().getKokoro().toHeart.get("diblabber", "")) == 0:
            super().getKokoro().toHeart["diblabber"] = ""
            self.setSimpleAlg(self.chatbot1.forceRespond())
            return
        self.NPCUtilization(self.chatbot1, ear)

    def mode1(self, ear: str):
        # auto engage:
        if self.__autoEngage.responsesContainsStr(ear):
            self.__tg.open(self.__tg.pause)
            self.setSimpleAlg("auto NPC mode engaged")
            return
        if self.__shutUp.responsesContainsStr(ear):
            self.__tg.close()
            self.setSimpleAlg("auto NPC mode disengaged")
            return
        if self.__tg.isOpen():
            plus: int = self.__nPCNeg
            if not (len(ear) == 0):
                plus = self.__nPCPlus
            result: str = self.chatbot2.respondPlus(plus)
            if not (len(result) == 0):
                self.setSimpleAlg(result)
                return
        # end auto engage code snippet
        self.NPCUtilization(self.chatbot2, ear)

    # auto mode setters
    def setNPCTimeSpan(self, n: int):
        self.__tg.setPause(n)

    def setNPCNeg(self, n: int):
        # lower NPC auto output chance
        self.__nPCNeg = n

    def setNPCPlus(self, n: int):
        # increase NPC auto output chance
        self.__nPCPlus = n


class DiEngager(DiSkillV2):
    def __init__(self, burps_per_hour: int, skillToEngage: str):
        self._burpsPerHour = 2
        if 60 > burps_per_hour > 0:
            self._burpsPerHour = burps_per_hour
        self._trgMinute: TrgMinute = TrgMinute()
        self._trgMinute.setMinute(0)
        self._draw: DrawRndDigits = DrawRndDigits()
        self._burpMinutes: LGFIFO = LGFIFO()
        self._pl: PlayGround = PlayGround()
        self._skillToEngage: str = skillToEngage
        for i in range(1, 60):
            self._draw.addElement(i)
        for i in range(0, burps_per_hour):
            self._burpMinutes.insert(self._draw.drawAndRemove())
        super().__init__()

    def setSkillToEngage(self, skillToEngage: str) -> DiEngager:
        self._skillToEngage = skillToEngage
        return self

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
            self.getKokoro().toHeart[self._skillToEngage] = "engage"


class GamificationP(DiSkillV2):
    # the grind side of the game, see GamificationN for the reward side
    def __init__(self, skill: DiSkillV2):
        super().__init__()
        self._gain: int = 1
        self._skill: DiSkillV2 = skill
        self._axGamification: AXGamification = AXGamification()

    def setGain(self, gain):
        if gain > 0:
            self._gain = gain

    def getAxGamification(self) -> AXGamification:
        # shallow ref
        return self._axGamification

    def input(self, ear, skin, eye):
        self._skill.input(ear, skin, eye)

    def output(self, noiron):
        # skill activation increases gaming credits
        if self._skill.pendingAlgorithm():
            self._axGamification.incrementBy(self._gain)
        self._skill.output(noiron)


class GamificationN(DiSkillV2):
    def __init__(self, skill: DiSkillV2, rewardBank: GamificationP):
        super().__init__()
        self._axGamification: AXGamification = rewardBank.getAxGamification()
        self._cost: int = 3
        self._skill = skill

    def setCost(self, cost: int):
        self._cost = cost
        return self

    def input(self, ear, skin, eye):
        # engage skill only if a reward is possible
        if self._axGamification.surplus(self._cost):
            self._skill.input(ear, skin, eye)

    def output(self, noiron):
        # charge reward if an algorithm is pending
        if self._skill.pendingAlgorithm():
            self._axGamification.reward(self._cost)
            self._skill.output(noiron)


class DiSayer(DiSkillV2):
    def __init__(self):
        super().__init__()
        self.cmdBreaker = AXCmdBreaker("say")
        self.command = ""

    def input(self, ear, skin, eye):
        if len(ear) == 0:
            return
        if ear == "say it":
            self.setSimpleAlg(self.getKokoro().grimoireMemento.simpleLoad(f'disayer'))
            return

        self.command = self.cmdBreaker.extractCmdParam(ear)
        if self.command:
            self.getKokoro().grimoireMemento.simpleSave(f'disayer', self.command)
            self.setSimpleAlg(self.command)
            self.command = ""


# the smoothie skills are simple skills for testing purposes,
# such as testing the BranchSkill. but they have their own merit
# in suggesting smoothies and have a good alg base for other recipe skills
class DiSmoothie0(DiSkillV2):
    def __init__(self):
        super().__init__()
        self.draw = DrawRnd("grapefruits", "oranges", "apples", "peaches", "melons", "pears", "carrot")
        self.cmd = AXContextCmd()
        self.cmd.contextCommands.insert("recommend a smoothie")
        self.cmd.commands.insert("yuck")
        self.cmd.commands.insert("lame")
        self.cmd.commands.insert("nah")
        self.cmd.commands.insert("no")

    def input(self, ear, skin, eye):
        if self.cmd.engageCommand(ear):
            self.setSimpleAlg(f"{self.draw.drawAndRemove()} and {self.draw.drawAndRemove()}")
            self.draw.reset()


class DiSmoothie1(DiSkillV2):
    def __init__(self):
        super().__init__()
        self.base = Responder("grapefruits", "oranges", "apples", "peaches", "melons", "pears", "carrot")
        self.thickeners = DrawRnd("bananas", "mango", "strawberry", "pineapple", "dates")
        self.cmd = AXContextCmd()
        self.cmd.contextCommands.insert("recommend a smoothie")
        self.cmd.commands.insert("yuck")
        self.cmd.commands.insert("lame")
        self.cmd.commands.insert("nah")
        self.cmd.commands.insert("no")

    def input(self, ear, skin, eye):
        if self.cmd.engageCommand(ear):
            self.setSimpleAlg(
                f"use {self.base.getAResponse()} as a base than add {self.thickeners.drawAndRemove()}\n and {self.thickeners.drawAndRemove()}")
            self.thickeners.reset()


class DiJumbler(DiSkillV2):
    # jumble a string
    def __init__(self):
        super().__init__()
        self.cmdBreaker: AXCmdBreaker = AXCmdBreaker("jumble the name")
        self.__temp: str = ""

    def input(self, ear, skin, eye):
        temp = self.cmdBreaker.extractCmdParam(ear)
        if not temp:  # In Python, an empty string is considered False in a boolean context
            return
        self.setSimpleAlg(self.jumble_string(temp))
        temp = ""

    @staticmethod
    def jumble_string(s: str) -> str:
        # Convert the string to a list (because strings in Python are immutable)
        list_s = list(s)

        # Use random.shuffle() to shuffle the list
        random.shuffle(list_s)

        # Convert the list back to a string
        jumbled_s = ''.join(list_s)

        return jumbled_s


class SkillBranch(DiSkillV2):
    # unique skill used to bind similar skills
    """
    * contains collection of skills
    * mutates active skill if detects conjuration
    * mutates active skill if algorithm results in
    * negative feedback
    * positive feedback negates active skill mutation
    * """

    def __init__(self, tolerance):
        super().__init__()
        self._skillRef: dict[str, int] = {}
        self._skillHub: SkillHubAlgDispenser = SkillHubAlgDispenser()
        self._ml: AXLearnability = AXLearnability(tolerance)
        self._priority: int = 4  # algPriority

    def setBranchAlgPriority(self, algPriority):
        self._priority = algPriority

    def input(self, ear, skin, eye):
        # conjuration alg morph
        if ear in self._skillRef:
            self._skillHub.setActiveSkillWithMood(self._skillRef[ear])
            self.setSimpleAlg("hmm")
        # machine learning alg morph
        if self._ml.mutateAlg(ear):
            self._skillHub.cycleActiveSkill()
            self.setSimpleAlg("hmm")
        # alg engage
        self.setOutalg(self._skillHub.dispenseAlgorithm(ear, skin, eye))
        if self.getOutAlg() is not None:
            self._ml.pendAlg()
            self.setOutAlgPriority(self._priority)

    def addSkill(self, skill):
        self._skillHub.addSkill(skill)

    def addReferencedSkill(self, skill, conjuration):
        # the conjuration string will engage it's respective skill
        self._skillHub.addSkill(skill)
        self._skillRef[conjuration] = self._skillHub.getSize()

    # learnability params
    def addDefcon(self, defcon):
        self._ml.defcons.insert(defcon)

    def addGoal(self, goal):
        self._ml.defcons.insert(goal)

    # while alg is pending, cause alg mutation ignoring learnability tolerance:
    def addDefconLV5(self, defcon5):
        self._ml.defcons.insert(defcon5)


class DiBlabberV2(DiSkillV2):
    def __init__(self, skill_name: str):
        super().__init__()
        # skill toggle
        self.__isActive: bool = True
        self.skillToggler: AXContextCmd = AXContextCmd()
        self.skillToggler.contextCommands.insert(f'toggle {skill_name}')
        self.skillToggler.commands.insert("again")
        self.skillToggler.commands.insert("repeat")
        # chat mode select
        self.modeSwitch: AXContextCmd = AXContextCmd()
        self.modeSwitch.contextCommands.insert(f'switch {skill_name} mode')
        self.modeSwitch.commands.insert("again")
        self.modeSwitch.commands.insert("repeat")
        self._mode: Cycler = Cycler(1)
        self._mode.setToZero()  # default mode : pal chatbot (chatbot1)
        # engage chatbot
        self.engage: AXContextCmd = AXContextCmd()
        self.engage.contextCommands.insert(f'engage {skill_name}')
        self.engage.commands.insert("talk to me")
        self.engage.commands.insert("again")
        self.engage.commands.insert("repeat")
        # chatbots
        self.chatbot1: AXNPC2 = AXNPC2(30, 90)  # pal mode chat module
        self.chatbot2: AXNPC2 = AXNPC2(30, 90)  # discreet mode chat module
        # auto mode
        self.__autoEngage: Responder = Responder("engage automatic mode", "automatic mode", "auto mode")
        self.__shutUp: Responder = Responder("stop", "shut up", "silence", "be quite", "be silent")
        self.__tg: TimeGate = TimeGate(5)
        self.__nPCPlus: int = 5  # increase rate of output in self auto reply mode
        self.__nPCNeg: int = -10  # decrease rate of output in self auto reply mode

    # Override
    def input(self, ear: str, skin: str, eye: str):
        # skill toggle:
        if self.skillToggler.engageCommand(ear):
            self.__isActive = not self.__isActive
        if not self.__isActive:
            return
        # chatbot mode: switch mode
        if self.modeSwitch.engageCommand(ear):
            self._mode.cycleCount()
            self.setSimpleAlg(Eliza.PhraseMatcher.reflect(self.talkMode()))
            return
        match self._mode.getMode():
            case 0:
                self.mode0(ear)
            case 1:
                self.mode1(ear)

    def talkMode(self) -> str:
        match self._mode.getMode():
            case 0:
                return "friend mode"
            case 1:
                return "discreet mode"
        return "mode switched"

    # chat module common tasks
    def NPCUtilization(self, npc: AXNPC2, ear: str):
        result: str = ""
        # engage
        if self.engage.engageCommand(ear):
            result = npc.respond()
            if not result == "":
                self.setSimpleAlg(Eliza.PhraseMatcher.reflect(result))
                return
        # str engage
        result = npc.strRespond(ear)
        if not result == "":
            self.setSimpleAlg(Eliza.PhraseMatcher.reflect(result))
        # forced learn (say n)
        if not npc.learn(ear):
            # str learn
            npc.strLearn(ear)

    def mode0(self, ear: str):
        if not len(super().getKokoro().toHeart.get("diblabber", "")) == 0:
            super().getKokoro().toHeart["diblabber"] = ""
            self.setSimpleAlg(Eliza.PhraseMatcher.reflect(self.chatbot1.forceRespond()))
            return
        self.NPCUtilization(self.chatbot1, ear)

    def mode1(self, ear: str):
        # auto engage:
        if self.__autoEngage.responsesContainsStr(ear):
            self.__tg.open(self.__tg.pause)
            self.setSimpleAlg("auto NPC mode engaged")
            return
        if self.__shutUp.responsesContainsStr(ear):
            self.__tg.close()
            self.setSimpleAlg("auto NPC mode disengaged")
            return
        if self.__tg.isOpen():
            plus: int = self.__nPCNeg
            if not (len(ear) == 0):
                plus = self.__nPCPlus
            result: str = self.chatbot2.respondPlus(plus)
            if not (len(result) == 0):
                self.setSimpleAlg(Eliza.PhraseMatcher.reflect(result))
                return
        # end auto engage code snippet
        self.NPCUtilization(self.chatbot2, ear)

    # auto mode setters
    def setNPCTimeSpan(self, n: int):
        self.__tg.setPause(n)

    def setNPCNeg(self, n: int):
        # lower NPC auto output chance
        self.__nPCNeg = n

    def setNPCPlus(self, n: int):
        # increase NPC auto output chance
        self.__nPCPlus = n


class DiBlabberV3(DiSkillV2):
    def __init__(self, skill_name: str):
        super().__init__()
        # skill toggle
        self.__isActive: bool = True
        self.skillToggler: AXContextCmd = AXContextCmd()
        self.skillToggler.contextCommands.insert(f'toggle {skill_name}')
        self.skillToggler.commands.insert("again")
        self.skillToggler.commands.insert("repeat")
        # chat mode select
        self.modeSwitch: AXContextCmd = AXContextCmd()
        self.modeSwitch.contextCommands.insert(f'switch {skill_name} mode')
        self.modeSwitch.commands.insert("again")
        self.modeSwitch.commands.insert("repeat")
        self._mode: Cycler = Cycler(1)
        self._mode.setToZero()  # default mode : pal chatbot (chatbot1)
        # engage chatbot
        self.engage: AXContextCmd = AXContextCmd()
        self.engage.contextCommands.insert(f'engage {skill_name}')
        self.engage.commands.insert("talk to me")
        self.engage.commands.insert("again")
        self.engage.commands.insert("repeat")
        # chatbots
        self.chatbot1: AXNPC2 = AXNPC2(30, 90)  # pal mode chat module
        self.chatbot2: AXNPC2 = AXNPC2(30, 90)  # discreet mode chat module
        # auto mode
        self.__autoEngage: Responder = Responder("engage automatic mode", "automatic mode", "auto mode")
        self.__shutUp: Responder = Responder("stop", "shut up", "silence", "be quite", "be silent")
        self.__tg: TimeGate = TimeGate(5)
        self.__nPCPlus: int = 5  # increase rate of output in self auto reply mode
        self.__nPCNeg: int = -10  # decrease rate of output in self auto reply mode

    # Override
    def input(self, ear: str, skin: str, eye: str):
        # modification patch diblabber->diblabberV3:
        temp1: str = ear
        if temp1.__contains__("tell me"):
            temp1 = temp1.replace("tell me", "say")
            temp1 = Eliza.PhraseMatcher.reflect(temp1)
        # skill toggle:
        if self.skillToggler.engageCommand(temp1):
            self.__isActive = not self.__isActive
        if not self.__isActive:
            return
        # chatbot mode: switch mode
        if self.modeSwitch.engageCommand(temp1):
            self._mode.cycleCount()
            self.setSimpleAlg(self.talkMode())
            return
        match self._mode.getMode():
            case 0:
                self.mode0(temp1)
            case 1:
                self.mode1(temp1)

    def talkMode(self) -> str:
        match self._mode.getMode():
            case 0:
                return "friend mode"
            case 1:
                return "discreet mode"
        return "mode switched"

    # chat module common tasks
    def NPCUtilization(self, npc: AXNPC2, ear: str):
        result: str = ""
        # engage
        if self.engage.engageCommand(ear):
            result = npc.respond()
            if not result == "":
                self.setSimpleAlg(result)
                return
        # str engage
        result = npc.strRespond(ear)
        if not result == "":
            self.setSimpleAlg(result)
        # forced learn (say n)
        if not npc.learn(ear):
            # str learn
            npc.strLearn(ear)

    def mode0(self, ear: str):
        if not len(super().getKokoro().toHeart.get("diblabber", "")) == 0:
            super().getKokoro().toHeart["diblabber"] = ""
            self.setSimpleAlg(self.chatbot1.forceRespond())
            return
        self.NPCUtilization(self.chatbot1, ear)

    def mode1(self, ear: str):
        # auto engage:
        if self.__autoEngage.responsesContainsStr(ear):
            self.__tg.open(self.__tg.pause)
            self.setSimpleAlg("auto NPC mode engaged")
            return
        if self.__shutUp.responsesContainsStr(ear):
            self.__tg.close()
            self.setSimpleAlg("auto NPC mode disengaged")
            return
        if self.__tg.isOpen():
            plus: int = self.__nPCNeg
            if not (len(ear) == 0):
                plus = self.__nPCPlus
            result: str = self.chatbot2.respondPlus(plus)
            if not (len(result) == 0):
                self.setSimpleAlg(result)
                return
        # end auto engage code snippet
        self.NPCUtilization(self.chatbot2, ear)

    # auto mode setters
    def setNPCTimeSpan(self, n: int):
        self.__tg.setPause(n)

    def setNPCNeg(self, n: int):
        # lower NPC auto output chance
        self.__nPCNeg = n

    def setNPCPlus(self, n: int):
        # increase NPC auto output chance
        self.__nPCPlus = n


class DiActivity(DiSkillV2):
    def __init__(self):
        super().__init__()
        self.activities: list[DrawRnd] = []
        self.index = -1
        self.start = "start activity"
        self.stop = "stop"
        self.skip = Responder("skip", "next", "ok")
        self.doNext = ""

    def setStart(self, start):
        self.start = start

    def setStop(self, stop):
        self.stop = stop

    def setSkip(self, skip):
        self.skip = skip

    def addActivity(self, drawRnd):
        self.activities.append(drawRnd)

    def input(self, ear, skin, eye):
        if ear == self.start:
            self.index = 0
            for i in range(len(self.activities)):
                self.activities[i].reset()
        if self.index > -1:
            if ear == self.stop:
                self.index = -1
                return
            elif self.skip.responsesContainsStr(ear) or self.activities[self.index].isEmptied():
                self.index += 1
            if self.index > len(self.activities) - 1:
                self.index = -1
                return
            self.doNext = self.activities[self.index].drawAndRemove()
        if self.doNext == "":
            return
        else:
            self.setSimpleAlg(self.doNext)
            self.doNext = ""


class DiHuggyWuggy:
    def __init__(self):
        self.o1 = DiActivity()
        self.o1.addActivity(DrawRnd("approaches you", "wide grin"))
        self.o1.addActivity(DrawRnd("hugs you"))
        self.o1.addActivity(DrawRnd("hugs you tighter", "nuzzles", "snuggles", "plays with your hair"))

    def retSkill(self):
        return self.o1


class DiArguer(DiSkillV2):
    def __init__(self):
        super().__init__()
        self.argue = TrgArgue()
        self.r1 = Responder()  # replies against argument
        self.r2 = Responder()  # replies for insistence
        self.finale = "number"  # replies after argueLim insistances
        self.argueLim = 13

    def setArgue(self, argue):
        self.argue = argue

    def setR1(self, r1):
        self.r1 = r1

    def setR2(self, r2):
        self.r2 = r2

    def setFinale(self, finale):
        self.finale = finale

    def setArgueLim(self, argueLim):
        self.argueLim = argueLim

    def input(self, ear, skin, eye):
        if self.argue.engageCommand(ear) == 0:
            return
        elif self.argue.engageCommand(ear) == 1:
            self.setSimpleAlg(self.r1.getAResponse())
        else:
            if self.argueLim < self.argue.getCounter() < self.argueLim + 5:
                self.setSimpleAlg(self.finale.replace("number", str(self.argue.getCounter() + 5)))
                return
            self.setSimpleAlg(self.r2.getAResponse())


class DiRailChatBot(DiSkillV2):
    def __init__(self):
        super().__init__()
        self.rcb: RailChatBot = RailChatBot()
        self.dialog: AXCmdBreaker = AXCmdBreaker("babe")
        self.filter: UniqueItemSizeLimitedPriorityQueue = UniqueItemSizeLimitedPriorityQueue(5)
        self.bads: AXCmdBreaker = AXCmdBreaker("is bad")
        self.goods: AXCmdBreaker = AXCmdBreaker("is good")
        self.filterTemp: str = ""

    def setQueLim(self, lim):
        self.filter.setLimit(lim)

    def input(self, ear, skin, eye):
        # filter learn:
        self.filterTemp = self.bads.extractCmdParam(ear)
        if self.filterTemp:
            self.filter.insert(self.filterTemp)
            self.filterTemp = ""
            self.setSimpleAlg("i will keep that in mind")
            return
        self.filterTemp = self.goods.extractCmdParam(ear)
        if self.filterTemp:
            self.filter.removeItem(self.filterTemp)
            self.filterTemp = ""
            self.setSimpleAlg("understood")
            return
        if self.filter.strContainsResponse(ear):
            return  # filter in
        temp = self.dialog.extractCmdParam(ear)
        if temp:
            result = self.rcb.respondDialog(temp)
            if self.filter.strContainsResponse(result):
                return  # filter out
            self.setSimpleAlg(Eliza.PhraseMatcher.reflect(result))
            return
        self.rcb.learn(ear)


class DiBlueCrystal(DiSkillV2):
    def __init__(self):
        super().__init__()
        # language learning game skill
        self._categories: list[dict[str, str]] = []
        self._quiz: DrawRnd = DrawRnd()
        self._keyList: list[str] = []
        self._categoryIndex: int = 0
        self._teach: AXContextCmd = AXContextCmd()
        self._quizMe: AXContextCmd = AXContextCmd()
        self._expectedSolution: str = ""
        self._itMeans: AXCmdBreaker = AXCmdBreaker("it means")
        self._score: int = 0
        self._lvUpAt: int = 10

        self._teach.contextCommands.insert("teach")
        self._teach.commands.insert("more")
        self._teach.commands.insert("next")
        self._teach.commands.insert("again")
        self._quizMe.contextCommands.insert("quiz")
        self._quizMe.commands.insert("more")
        self._quizMe.commands.insert("next")
        self._quizMe.commands.insert("again")

    def setLvUpAt(self, lvUpAt: int):
        self._lvUpAt = lvUpAt

    def addCategory(self, category: dict[str, str]):
        # at least one category should be added
        self._categories.append(category)
        if len(self._categories) > 1:
            return
        self._keyList = list(self._categories[0].keys())

    def input(self, ear: str, skin: str, eye: str):
        if not ear:
            return
        # get word gem
        if self._teach.engageCommand(ear):
            randomIndex = random.randint(0, len(self._keyList) - 1)
            wordGem = self._keyList[randomIndex]
            translation = self._categories[self._categoryIndex][wordGem]
            self._quiz.addElement(wordGem)
            self.setSimpleAlg(f"{wordGem} means {translation}")
            return
        # manual category change:
        if ear == "next category":
            self._nxtCategory()
            self.setSimpleAlg("ok")
            return
        if ear == "randomize category":
            self._rndCategory()
            self.setSimpleAlg("category randomized")
            return
        # quiz user
        if self._quizMe.engageCommand(ear):
            question = self._quiz.drawAndRemove()
            if not question:
                question = self._keyList[random.randint(0, len(self._keyList) - 1)]
            self.setSimpleAlg(f"what does {question} mean")
            self._expectedSolution = "it means " + self._categories[self._categoryIndex][question]
            return
        # get score
        if ear == "score":
            self.setSimpleAlg(f"your score is {self._score} of {self._lvUpAt}")
            return
        # get current level
        if ear == "level":
            self.setSimpleAlg(f"your level is {self._categoryIndex}")
            return
        # answer
        if "it means" in ear and self._expectedSolution:
            if ear == self._expectedSolution:
                self._expectedSolution = ""
                # correct solution
                self._score += 1
                # level up?
                if self._score == self._lvUpAt:
                    self._score = 0
                    self._nxtCategory()
                    self.setSimpleAlg("leveled up")
                else:
                    self.setSimpleAlg("correct")
            else:
                self._expectedSolution = ""
                self._score = 0
                self.setSimpleAlg("bu bu wrong answer")
            return

    def _nxtCategory(self):
        self._quiz = DrawRnd()
        self._score = 0
        self._categoryIndex += 1
        if self._categoryIndex == len(self._categories):
            self._categoryIndex = 0
        if len(self._categories) == 0:
            return
        self._keyList = list(self._categories[self._categoryIndex].keys())

    def _rndCategory(self):
        self._quiz = DrawRnd()
        self._score = 0
        self._categoryIndex = DrawRnd().getSimpleRNDNum(len(self._categories))
        if len(self._categories) == 0:
            return
        self._keyList = list(self._categories[self._categoryIndex].keys())


class DiHoneyBunny(DiSkillV2):
    def __init__(self):
        super().__init__()  # Call the parent class constructor
        self.regex_util: RegexUtil = RegexUtil()
        self.on_off_switch: OnOffSwitch = OnOffSwitch()
        self.on_off_switch.setOn(Responder("honey bunny"))
        self.user = "user"
        self.drip: PercentDripper = PercentDripper()
        self.responses: Responder = Responder("user", "i love you user", "hadouken", "shoryuken",
                                              "user is a honey bunny", "hadoken user", "shoryukens user",
                                              "i demand attention", "hey user", "uwu")
        self._buffer = 10
        self._buffer_counter = 0
        self._bool1: bool = False

    def set_buffer(self, buffer):
        self._buffer = buffer

    def input(self, ear, skin, eye):
        if len(ear) > 0:
            self._buffer_counter = 0
            temp = self.regex_util.extractRegex(r'(?<=my name is\s)(.*)', ear)
            if temp:
                self.user = temp
                self.algPartsFusion(4, APHappy(f"got it {self.user}"))
                return
        elif self._bool1 and self._buffer_counter < self._buffer:
            self._buffer_counter += 1
        self._bool1 = self.on_off_switch.getMode(ear)
        if self._bool1 and self.drip.drip():
            if self._buffer_counter > self._buffer - 1:
                self.algPartsFusion(4, APSad(self.responses.getAResponse().replace("user", self.user)))


class DiAlarmer(DiSkillV2):
    def __init__(self):
        super().__init__()
        self.off: Responder = Responder("alarm off", "cancel alarm")
        self.regexUtil: RegexUtil = RegexUtil()
        self._cron: Cron = Cron("", 3, 3)

    def setCron(self, cron):
        self._cron = cron

    def input(self, ear, skin, eye):
        # Turn off alarm
        if self.off.responsesContainsStr(ear):
            self._cron.turnOff()
            self.setSimpleAlg("alarm is now off")
            return

        temp = self.regexUtil.extractRegex(r"(?<=set alarm to\s)([0-9]{1,2}:[0-9]{1,2})", ear)
        if not len(temp) == 0:
            self._cron.setStartTime(temp)
            self.setSimpleAlg(f"alarm set to {temp}")
            return

        if self._cron.triggerWithoutRenewal():
            self.setSimpleAlg("beep")


class DiMemoryGame(DiSkillV2):
    def __init__(self):
        super().__init__()
        self.score = 0
        self.game_on = False
        self.game_str = ""
        self.game_chars: Responder = Responder("r", "g", "b", "y")
        self.regexUtil: RegexUtil = RegexUtil()

    def input(self, ear, skin, eye):
        if ear == "memory game on":
            self.game_on = True
            self.score = 0
            self.game_str = self.game_chars.getAResponse()
            self.setSimpleAlg(self.game_str)

        if self.game_on:
            temp = self.regexUtil.extractRegex("^[rgby]+$", ear)
            if temp:
                if temp == self.game_str:
                    temp = self.game_chars.getAResponse()
                    self.game_str += temp
                    self.score += 1
                    self.setSimpleAlg(temp)
                else:
                    self.game_on = False
                    self.setSimpleAlg(f"you scored {self.score}")
                    self.score = 0


class DiOneWorder(DiSkillV2):
    def __init__(self, phrase: str = "chi"):
        super().__init__()  # Call the superclass constructor
        self.cry: str = f'{phrase} '
        self.toggler: str = phrase
        self.drip: PercentDripper = PercentDripper()  # Assuming PercentDripper is implemented
        self.mode: bool = False

    def set_cry(self, cry):
        self.cry = cry + " "

    def set_toggler(self, toggler):
        self.toggler = toggler

    def set_drip_percent(self, n: int):
        self.drip.setLimit(n)

    def input(self, ear, skin, eye):
        if not ear:
            return
        if ear == self.toggler:
            self.mode = not self.mode
            self.setSimpleAlg("toggled")
            return
        if self.mode and self.drip.drip():
            self.setSimpleAlg(self.convert_to_chi(ear))

    def convert_to_chi(self, input_str):
        # Split the input string into words
        words = input_str.split()

        # Initialize an empty result string
        result = ""

        # Iterate through each word
        for word in words:
            # Append "chi" to the result
            result += self.cry

        # Remove the trailing space
        if result:
            result = result[:-1]

        return result


class DiAware(DiSkillV2):
    def __init__(self, chobit: Chobits, name: str, summoner="user"):
        super().__init__()
        self.chobit: Chobits = chobit
        self.name: str = name
        self.summoner: str = summoner
        self.skills: list[str] = []
        self.replies: Responder = Responder("Da, what’s happening?", f'You speak to {self.name}?',
                                            f"Slav {self.name} at your service!", "What’s cooking, comrade?",
                                            f"{self.name} is listening!", "Yes, babushka?",
                                            f"Who summons the {self.name}?", "Speak, friend, and enter!",
                                            f"{self.name} hears you loud and clear!", "What’s on the menu today?",
                                            "Ready for action, what’s the mission?",
                                            f"{self.name}’s here, what’s the party?",
                                            f"did someone call for a {self.name}?", "Adventure time, or nap time?",
                                            "Reporting for duty, what’s the quest?",
                                            f"{self.name}’s in the house, what’s up?",
                                            "Is it time for vodka and dance?", f"{self.name}’s ready, what’s the plan?",
                                            f"Who dares to disturb the mighty {self.name}?",
                                            "What’s the buzz, my spud?", "Is it a feast, or just a tease?",
                                            f"{self.name}’s awake, what’s at stake?", "What’s the word, bird?",
                                            "Is it a joke, or are we broke?",
                                            f"{self.name}’s curious, what’s so serious?",
                                            "Is it a game, or something lame?", "What’s the riddle, in the middle?",
                                            f"{self.name}’s all ears, what’s the cheers?",
                                            "Is it a quest, or just a test?", "What’s the gig, my twig?",
                                            "Is it a prank, or am I high rank?", "What’s the scoop, my group?",
                                            "Is it a tale, or a sale?", "What’s the drill, my thrill?",
                                            "Is it a chat, or combat?", "What’s the plot, my tot?",
                                            "Is it a trick, or something slick?", "What’s the deal, my peel?",
                                            "Is it a race, or just a chase?", "What’s the story, my glory?")
        self.ggReplies: Responder = Responder("meow", "oooweee", "chi", "yes i am", "nuzzles you", "thanks", "prrr")
        self._call: str = f'hey {self.name}'
        self._ggFunnel: AXFunnel = AXFunnel("good girl")
        self._ggFunnel.addK("you are a good girl").addK("such a good girl").addK("you are my good girl")

    def input(self, ear, skin, eye):
        match self._ggFunnel.funnel(ear):
            case "list skills":
                self.skills = self.chobit.get_skill_list()
                self.setVebatimAlgFromList(4, self.skills)
            case "what is your name":
                self.setSimpleAlg(self.name)
            case "name summoner":
                self.setSimpleAlg(self.summoner)
            case "how do you feel":
                self.getKokoro().toHeart["last_ap"] = self.chobit.getSoulEmotion()
            case self.name:
                self.setSimpleAlg(self.replies.getAResponse())
            case "test":
                self.setSimpleAlg(self.replies.getAResponse())
            case "good girl":
                self.algPartsFusion(4, APHappy(self.ggReplies.getAResponse()))
            case self._call:
                self.setSimpleAlg(self.replies.getAResponse())


class DiBlabberV3(DiSkillV2):
    def __init__(self, memory_size: int = 9, reply_chance: int = 90):
        super().__init__()
        self.npc: AXNPC2 = AXNPC2(memory_size, reply_chance)
        self._temp_str: str = ""
        self.splitter: AXStringSplit = AXStringSplit()
        self._initialized: bool = False

    def input(self, ear: str, skin: str, eye: str):
        if len(ear) == 0:
            return
        if not self._initialized:
            self.npc.responder.queue = self.splitter.split(self.getKokoro().grimoireMemento.simpleLoad("blabberv3"))
            self._initialized = True
        self._temp_str = self.npc.strRespond(ear)
        if len(self._temp_str) > 0:
            self.setSimpleAlg(Eliza.PhraseMatcher.reflect(self.npc.forceRespond()))
        if not self.npc.learn(ear):
            # str learn
            if not self.npc.strLearn(ear):
                return
        self.getKokoro().grimoireMemento.simpleSave("blabberv3", self.splitter.stringBuilder(self.npc.responder.queue))


class DiBlabberV4(DiSkillV2):
    def __init__(self, responder: Responder, memory_size: int = 15, reply_chance: int = 90, ):
        super().__init__()
        self.npc: AXNPC2 = AXNPC2(memory_size, reply_chance)
        self._temp_str: str = ""
        self.splitter: AXStringSplit = AXStringSplit()
        self._initialized: bool = False
        self.filter: Responder = responder
        self._autoTalk: OnOffSwitch = OnOffSwitch()
        self._autoTalk.setOn(Responder("auto talk"))
        self._excluder: Excluder = Excluder()
        self._excluder.add_starts_with("tell me")
        self._excluder.add_ends_with("over")

    def input(self, ear: str, skin: str, eye: str):
        if self._excluder.exclude(ear):
            return
        if not self._initialized:
            self.npc.responder.queue = self.splitter.split(self.getKokoro().grimoireMemento.simpleLoad("blabberv4"))
            self._initialized = True
        # talk to me
        if self._autoTalk.getMode(ear):
            t = self.npc.respond()
            if len(t) > 0:
                self.algPartsFusion(4, APMad(t))
                return
        if len(ear) == 0:
            return
        # auto
        if not len(super().getKokoro().toHeart.get("diblabber", "")) == 0:
            super().getKokoro().toHeart["diblabber"] = ""
            t = self.npc.forceRespond()
            if len(t) == 0:
                t = "hadouken"
            self.algPartsFusion(4, APMad(t))
            return
        # filter escape
        if not self.filter.strContainsResponse(ear):
            return
        # blabber
        self._temp_str = self.npc.strRespond(ear)
        if len(self._temp_str) > 0:
            self.algPartsFusion(4, APMad(Eliza.PhraseMatcher.reflect(self.npc.forceRespond())))
        if not self.npc.learn(ear):
            # str learn
            if not self.npc.strLearn(ear):
                return
        self.getKokoro().grimoireMemento.simpleSave("blabberv4", self.splitter.stringBuilder(self.npc.responder.queue))


class DiBlabberV5(DiSkillV2):
    def __init__(self, memory_size: int = 15, reply_chance: int = 90):
        super().__init__()
        self.npc: AXNPC2 = AXNPC2(memory_size, reply_chance)
        self.npc.cmdBreaker = AXCmdBreaker("tell me")
        self._temp_str: str = ""
        self._autoTalk: OnOffSwitch = OnOffSwitch()
        self._autoTalk.setOn(Responder("filth on"))
        self._funnel: str = ""
        self.cntxtcmd: AXContextCmd = AXContextCmd()
        self.cntxtcmd.contextCommands.insert("princess")
        self.cntxtcmd.commands.insert("more")
        self.cntxtcmd.commands.insert("again")
        self.cntxtcmd.commands.insert("please")

    def addResponses(self, *responses: str) -> DiBlabberV5:
        for str1 in responses:
            self.npc.responder.queue.insert(str1)
        return self

    def setResponses(self, *responses: str) -> DiBlabberV5:
        self.npc.responder.queue = []
        for str1 in responses:
            self.npc.responder.queue.insert(str1)
        return self

    def input(self, ear: str, skin: str, eye: str):
        # auto talk mode
        if self._autoTalk.getMode(ear):
            t = self.npc.respond()
            if len(t) > 0:
                self.algPartsFusion(4, APShy(Eliza.PhraseMatcher.reflect(t)))
                return
        if len(ear) == 0:
            return
        if self.cntxtcmd.engageCommand(ear):
            self.algPartsFusion(4, APShy(Eliza.PhraseMatcher.reflect(self.npc.respond())))
            return
        # funnel
        self._funnel = ear.replace("tell me how", "tell me")
        self._funnel = self._funnel.replace("tell me to", "tell me")
        # blabber
        self._temp_str = self.npc.strRespond(self._funnel)
        if len(self._temp_str) > 0:
            self.algPartsFusion(4, APShy(Eliza.PhraseMatcher.reflect(self.npc.forceRespond())))
        self.npc.learn(self._funnel)


class DiBurstEliza(DiSkillV2):
    def __init__(self):
        super().__init__()
        self.eliza: Eliza = Eliza()
        self.trig: str = "listen"
        self.off: Responder = Responder("chill", "shut up")
        self.timeGate: TimeGate = TimeGate(5)

    def set_burst_duration(self, t: int) -> None:
        if t < 1:
            return
        self.timeGate.setPause(t)

    def input(self, ear: str, skin: str, eye: str) -> None:
        if ear == self.trig:
            self.timeGate.openForPauseMinutes()
            self.setSimpleAlg("listening")
            return
        if self.timeGate.isOpen():
            if self.off.strContainsResponse(ear):
                self.setSimpleAlg("got it")
                self.timeGate.close()
                return
            if not ear:
                return
            self.setSimpleAlg(self.eliza.respond(ear))


class DiDeducer(DiSkillV2):
    def __init__(self, deducer: ElizaDeducer):
        super().__init__()
        self.rcb: RailChatBot = RailChatBot()
        self.dialog: AXCmdBreaker = AXCmdBreaker("babe")
        self.filter: UniqueItemSizeLimitedPriorityQueue = UniqueItemSizeLimitedPriorityQueue(5)
        self.bads: AXCmdBreaker = AXCmdBreaker("is bad")
        self.goods: AXCmdBreaker = AXCmdBreaker("is good")
        self.filterTemp: str = ""
        self.elizaDeducer: ElizaDeducer = deducer

    def setQueLim(self, lim):
        self.filter.setLimit(lim)

    def input(self, ear, skin, eye):
        # filter learn:
        self.filterTemp = self.bads.extractCmdParam(ear)
        if self.filterTemp:
            self.filter.insert(self.filterTemp)
            self.filterTemp = ""
            self.setSimpleAlg("i will keep that in mind")
            return
        self.filterTemp = self.goods.extractCmdParam(ear)
        if self.filterTemp:
            self.filter.removeItem(self.filterTemp)
            self.filterTemp = ""
            self.setSimpleAlg("understood")
            return
        if self.filter.strContainsResponse(ear):
            return  # filter in
        temp = self.dialog.extractCmdParam(ear)
        if temp:
            result = self.rcb.respondDialog(temp)
            if self.filter.strContainsResponse(result):
                return  # filter out
            self.setSimpleAlg(Eliza.PhraseMatcher.reflect(result))
            return
        self.rcb.learnV2(ear, self.elizaDeducer)


class DiBlabberV6(DiSkillV2):
    def __init__(self, funnel: AXFunnelResponder):
        super().__init__()
        self.funnel: AXFunnelResponder = funnel

    def input(self, ear: str, skin: str, eye: str):
        # walrus operator:
        if n := self.funnel.funnel_walrus_operator(ear):
            self.setSimpleAlg(n)


class DiNoteTaker(DiSkillV2):
    def __init__(self):
        super().__init__()
        self.notes: Notes = Notes()

    # Override
    def input(self, ear: str, skin: str, eye: str):
        if len(ear) == 0:
            return
        match ear:
            case "get note":
                self.setSimpleAlg(self.notes.getNote())
            case "clear notes":
                self.notes.clear()
                self.setSimpleAlg("notes cleared")
            case "next note":
                self.setSimpleAlg(self.notes.get_next_note())

        first_word = ear.partition(' ')[0]
        if first_word == "note":
            self.notes.add(ear.replace("note", ""))
            self.setSimpleAlg("noted")


class APMad(Mutatable):
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
        return APMad(DeepCopier().copyList(self.sentences))


class APShy(Mutatable):
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
        return APShy(DeepCopier().copyList(self.sentences))


class APHappy(Mutatable):
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
        return APHappy(DeepCopier().copyList(self.sentences))


class APSad(Mutatable):
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
        return APSad(DeepCopier().copyList(self.sentences))


class DiBurperV2(DiSkillV2):
    def __init__(self, burps_per_hour: int = 3):
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

    def setBurps(self, burpings: Responder) -> DiBurperV2:
        # set sounds of burp events
        self._responder1 = burpings
        return self

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
            self.algPartsFusion(4, APHappy(self._responder1.getAResponse()))


class DiBicameral(DiSkillV2):
    def __init__(self):
        super().__init__()
        self.msgCol:TimedMessages = TimedMessages()

    def input(self, ear, skin, eye):
        self.msgCol.tick()
        if self.getKokoro().toHeart.get("dibicameral", "null") != "null":
            self.getKokoro().toHeart["dibicameral"] = "null"
        if self.msgCol.getMsg():
            temp = self.msgCol.getLastMSG()
            if "#" not in temp:
                self.setSimpleAlg(temp)
            else:
                self.getKokoro().toHeart["dibicameral"] = temp.replace("#", "")
