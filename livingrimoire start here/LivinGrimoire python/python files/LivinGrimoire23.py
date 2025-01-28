from __future__ import annotations
from abc import ABC, abstractmethod

"""CREDITS :


the living grimoire was created by Moti Barski

Translation of code from java to python : Marco Vavassori, Moti Barski"""


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
        if key == "" or value == "":
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
        """TODO Auto-generated method stub"""
        axnStr = ""
        if self.at > 0:
            if ear.lower() != self.param.lower():
                axnStr = self.param
                self.at -= 1
        return axnStr

    def completed(self) -> bool:
        return self.at < 1


class APVerbatim(Mutatable):
    """this algorithm part says each past param verbatim"""

    def __init__(self, *args) -> None:
        super().__init__()
        self.sentences: list[str] = []
        self.at = 0
        try:
            if isinstance(args[0], list):
                self.sentences = args[0]
                if len(self.sentences) == 0:
                    self.at = 30
            else:
                for i in range(len(args)):
                    self.sentences.append(args[i])
        except IndexError:
            # Handle the case where args[0] does not exist
            self.at = 30
        except AttributeError:
            # Handle the case where self.sentences is not initialized
            self.at = 30
        except Exception as e:
            # Log or handle other exceptions
            print(f"An unexpected error occurred: {e}")
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

    def getAlg(self, defcon: int) -> Algorithm | None:
        if len(self._defcons[defcon]) > 0:
            temp = self._defcons[defcon].pop(0)
            return temp
        return None


''' Skill CLASS '''


class Skill:
    def __init__(self):
        # The variables start with an underscore (_) because they are protected
        self._kokoro = None  # consciousness, shallow ref class to enable interskill communications
        self._outAlg: Algorithm  # skills output
        self._outAlg = None
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
        self._outAlg = self.simpleVerbatimAlgorithm(temp)
        self._outpAlgPriority = priority  # 1->5 1 is the highest algorithm priority

    def setSimpleAlg(self, *sayThis: str):
        # based on the setVerbatimAlg method
        # build a simple output algorithm to speak string by string per think cycle
        # uses varargs param
        temp: list[str] = []
        for i in range(0, len(sayThis)):
            temp.append(sayThis[i])
        self._outAlg = self.simpleVerbatimAlgorithm(temp)
        self._outpAlgPriority = 4  # 1->5 1 is the highest algorithm priority

    def setVebatimAlgFromList(self, priority: int, sayThis: list[str]):
        # build a simple output algorithm to speak string by string per think cycle
        # uses list param
        self._outAlg = self.algBuilder(APVerbatim(sayThis))
        self._outpAlgPriority = priority  # 1->5 1 is the highest algorithm priority

    def algPartsFusion(self, priority: int, *algParts: Mutatable):
        # build a custom algorithm out of a chain of algorithm parts(actions)
        algParts1: list[Mutatable] = []
        for i in range(0, len(algParts)):
            algParts1.append(algParts[i])
        self._outAlg = Algorithm(algParts1)
        self._outpAlgPriority = priority  # 1->5 1 is the highest algorithm priority

    def pendingAlgorithm(self) -> bool:
        # is an algorithm pending?
        return self._outAlg is not None

    # skill utils
    # alg part based algorithm building methods
    # var args param
    # noinspection PyMethodMayBeStatic
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

    # noinspection PyMethodMayBeStatic
    def strContainsList(self, str1: str, items: list[str]) -> str:
        # returns the 1st match between words in a string and values in a list.
        for temp in items:
            if str1.count(temp) > 0:
                return temp
        return ""

    def skillNotes(self, param: str) -> str:
        return "notes unknown"


class DiHelloWorld(Skill):
    # Override
    def input(self, ear: str, skin: str, eye: str):
        if ear == "hello":
            self.setVerbatimAlg(4, "hello world")  # # 1->5 1 is the highest algorithm priority

    def skillNotes(self, param: str) -> str:
        if param == "notes":
            return "plain hello world skill"
        elif param == "triggers":
            return "say hello"
        return "note unavalible"


''' ----------------- REGEXUTIL ---------------- '''

''' CERABELLUM CLASS '''


class Cerabellum:
    # runs an algorithm
    def __init__(self) -> None:
        self.fin: int
        self.fin = None
        self.at: int
        self.at = None
        self.incrementAt: bool = False
        self.alg: Algorithm
        self.alg = None
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
        self.ceraArr: list[Cerabellum] = [Cerabellum() for _ in range(5)]
        self._result: str = ""

    def getEmot(self) -> str:
        return self._emot

    def loadAlgs(self, neuron: Neuron):
        for i in range(1, 6):
            if not self.ceraArr[i - 1].isActive:
                temp: Algorithm = neuron.getAlg(i)
                if temp is not None:
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

    def add_logical_skill(self, skill):
        self.logicChobit.addSkill(skill)

    def add_hardware_skill(self, skill):
        self.hardwareChobit.addSkill(skill)

    def add_skillAware(self, skill):
        # add a skill with Chobit in its c'tor(has Chobit attribute)
        self.logicChobit.addSkillAware(skill)


''' Chobits CLASS '''


class Chobits:

    def __init__(self):
        super().__init__()
        self._dClasses: list[Skill] = []  # _ is a private access modifier
        self._fusion: Fusion = Fusion()
        self._noiron: Neuron = Neuron()
        self._kokoro: Kokoro = Kokoro(AbsDictionaryDB())  # soul
        self._isThinking: bool = False
        self._awareSkills: list[Skill] = []  # self awareness skills. Chobit Object in their c'tor

    '''set the chobit database
        the database is built as a key value dictionary
        the database can be using the kokoro attribute'''

    def setDatabase(self, absDictionaryDB: AbsDictionaryDB):
        self._kokoro = Kokoro(absDictionaryDB)

    def addSkill(self, skill: Skill) -> Chobits:
        # add a skill (builder design patterned func))
        if self._isThinking:
            return self
        skill.setKokoro(self._kokoro)
        self._dClasses.append(skill)
        return self

    def addSkillAware(self, skill: Skill) -> Chobits:
        # add a skill with Chobit Object in their c'tor
        skill.setKokoro(self._kokoro)
        self._awareSkills.append(skill)
        return self

    def clearSkills(self):
        # remove all skills
        if self._isThinking:
            return
        self._dClasses.clear()

    def addSkills(self, *skills: Skill):
        if self._isThinking:
            return
        for skill in skills:
            skill.setKokoro(self._kokoro)
            self._dClasses.append(skill)


    def removeSkill(self, skill: Skill):
        if self._isThinking:
            return
        if skill not in self._dClasses:
            return
        self._dClasses.remove(skill)

    def containsSkill(self, skill: Skill) -> bool:
        return self._dClasses.__contains__(skill)

    def think(self, ear: str, skin: str, eye: str) -> str:
        # main skill loop
        self._isThinking = True
        for dCls in self._dClasses:
            self.inOut(dCls, ear, skin, eye)
        self._isThinking = False
        # loop for skills with access to the Chobit Object:
        for dCls2 in self._awareSkills:
            self.inOut(dCls2, ear, skin, eye)
        self._fusion.loadAlgs(self._noiron)
        return self._fusion.runAlgs(ear, skin, eye)

    def getSoulEmotion(self) -> str:
        return self._fusion.getEmot()

    def inOut(self, dClass: Skill, ear: str, skin: str, eye: str):
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
