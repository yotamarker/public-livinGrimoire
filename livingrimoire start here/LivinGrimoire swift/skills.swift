//
//  skills.swift
//  LivinGrimoireSwiftV1
//
//  Created by moti barski on 31/08/2022.
//

import Foundation

class DiTime:DiSkillV2{
    private let pl:TimeUtils = TimeUtils()
    // hello world skill for testing purposes
    override func input(ear: String, skin: String, eye: String) {
        switch (ear)  {
          case "what is the date":
            setVerbatimAlg(priority: 4, sayThis: "\(pl.getCurrentMonthDay()) \(pl.getCurrentMonthName()) \(pl.getYearAsInt())")
          case "what is the time":
//            setVerbatimAlg(priority: 3, sayThis: pl.getCurrentTimeStamp())
            setSimpleAlg(sayThis: pl.getCurrentTimeStamp())
            break
          case "which day is it":
            setSimpleAlg(sayThis: pl.getDayOfDWeek())
            break
          case "good morning","good afternoon","good evening","good night":
            setSimpleAlg(sayThis: pl.partOfDay())
            break
          case "which month is it":
            setSimpleAlg(sayThis: pl.getCurrentMonthName())
            break
          case "which year is it":
            setSimpleAlg(sayThis: "\(pl.getYearAsInt())")
            break
          case "what is your time zone":
            setSimpleAlg(sayThis: pl.getLocal())
            break
          case "when is the first":
            setSimpleAlg(sayThis: pl.nxtDayOnDate(dayOfMonth: 1))
            break
          case "when is the second":
            setSimpleAlg(sayThis: pl.nxtDayOnDate(dayOfMonth: 2))
            break
          case "when is the third":
            setSimpleAlg(sayThis: pl.nxtDayOnDate(dayOfMonth: 3))
            break
        case "when is the fourth":
          setSimpleAlg(sayThis: pl.nxtDayOnDate(dayOfMonth: 4))
          break
        case "when is the fifth":
          setSimpleAlg(sayThis: pl.nxtDayOnDate(dayOfMonth: 5))
          break
        case "when is the sixth":
          setSimpleAlg(sayThis: pl.nxtDayOnDate(dayOfMonth: 6))
          break
        case "when is the seventh":
          setSimpleAlg(sayThis: pl.nxtDayOnDate(dayOfMonth: 7))
          break
        case "when is the eighth":
          setSimpleAlg(sayThis: pl.nxtDayOnDate(dayOfMonth: 8))
          break
        case "when is the ninth":
          setSimpleAlg(sayThis: pl.nxtDayOnDate(dayOfMonth: 9))
          break
        case "when is the tenth":
          setSimpleAlg(sayThis: pl.nxtDayOnDate(dayOfMonth: 10))
          break
        case "when is the eleventh":
          setSimpleAlg(sayThis: pl.nxtDayOnDate(dayOfMonth: 11))
          break
        case "when is the twelfth":
          setSimpleAlg(sayThis: pl.nxtDayOnDate(dayOfMonth: 12))
          break
        case "when is the thirteenth":
          setSimpleAlg(sayThis: pl.nxtDayOnDate(dayOfMonth: 13))
          break
        case "when is the fourteenth":
          setSimpleAlg(sayThis: pl.nxtDayOnDate(dayOfMonth: 14))
          break
        case "when is the fifteenth":
          setSimpleAlg(sayThis: pl.nxtDayOnDate(dayOfMonth: 15))
          break
        case "when is the sixteenth":
          setSimpleAlg(sayThis: pl.nxtDayOnDate(dayOfMonth: 16))
          break
        case "when is the seventeenth":
          setSimpleAlg(sayThis: pl.nxtDayOnDate(dayOfMonth: 17))
          break
        case "when is the eighteenth":
          setSimpleAlg(sayThis: pl.nxtDayOnDate(dayOfMonth: 18))
          break
        case "when is the nineteenth":
          setSimpleAlg(sayThis: pl.nxtDayOnDate(dayOfMonth: 19))
          break
        case "when is the twentieth":
          setSimpleAlg(sayThis: pl.nxtDayOnDate(dayOfMonth: 20))
          break
        case "when is the twenty first":
          setSimpleAlg(sayThis: pl.nxtDayOnDate(dayOfMonth: 21))
          break
        case "when is the twenty second":
          setSimpleAlg(sayThis: pl.nxtDayOnDate(dayOfMonth: 22))
          break
        case "when is the twenty third":
          setSimpleAlg(sayThis: pl.nxtDayOnDate(dayOfMonth: 23))
          break
        case "when is the twenty fourth":
          setSimpleAlg(sayThis: pl.nxtDayOnDate(dayOfMonth: 24))
          break
        case "when is the twenty fifth":
          setSimpleAlg(sayThis: pl.nxtDayOnDate(dayOfMonth: 25))
          break
        case "when is the twenty sixth":
          setSimpleAlg(sayThis: pl.nxtDayOnDate(dayOfMonth: 26))
          break
        case "when is the twenty seventh":
          setSimpleAlg(sayThis: pl.nxtDayOnDate(dayOfMonth: 27))
          break
        case "when is the twenty eighth":
          setSimpleAlg(sayThis: pl.nxtDayOnDate(dayOfMonth: 28))
          break
        case "when is the twenty ninth":
            setSimpleAlg(sayThis: pl.nxtDayOnDate(dayOfMonth: 29) == "" ? "never":pl.nxtDayOnDate(dayOfMonth: 29))
          break
        case "when is the thirtieth":
            setSimpleAlg(sayThis: pl.nxtDayOnDate(dayOfMonth: 30) == "" ? "never":pl.nxtDayOnDate(dayOfMonth: 30))
          break
        case "when is the thirty first":
            setSimpleAlg(sayThis: pl.nxtDayOnDate(dayOfMonth: 31) == "" ? "never":pl.nxtDayOnDate(dayOfMonth: 31))
          break
          case "incantation 0":
            // cancel running algorithm entirely at any alg part point
            super.setVerbatimAlg(priority: 4, sayThis: "fly","bless of magic caster","infinity wall", "magic ward holy","life essence")
            break
        default:
            return
        }
    }
}
class DiMagic8Ball: DiSkillV2 {
    public var magic8Ball = Magic8Ball()
    // skill toggle params:
    public var skillToggler = AXContextCmd()
    private var isActive = true
    
    override init() {
        skillToggler.contextCommands.input(in1: "toggle eliza")
        skillToggler.contextCommands.input(in1: "toggle magic 8 ball")
        skillToggler.contextCommands.input(in1: "toggle magic eight ball")
        skillToggler.commands.input(in1: "again")
        skillToggler.commands.input(in1: "repeat")
    }
    
    override func input(ear: String, skin: String, eye: String) {
        // toggle the skill off/on
        if skillToggler.engageCommand(ear: ear) {
            isActive = !isActive
            setSimpleAlg(sayThis: isActive ? "skill activated" : "skill inactivated")
            return
        }
        
        if !isActive {
            return
        }
        // skill logic:
        if magic8Ball.engage(ear) {
            setSimpleAlg(sayThis: magic8Ball.reply())
        }
    }
}
class DiCron:DiSkillV2{
    private var sound:String = "snore"
    private var cron:Cron = Cron(startTime: "12:05", minutes: 40, limit: 2)
    // setters
    func setSound(sound:String) -> DiCron {
        self.sound = sound
        return self
    }
    func setCron(cron:Cron) -> DiCron {
        self.cron = cron
        return self
    }
    override func input(ear: String, skin: String, eye: String) {
        if cron.trigger(){
            setSimpleAlg(sayThis: sound)
        }
    }
}
class DIBlabber: DiSkillV2 {
    private var isActive: Bool = true // skill toggler
    var skillToggler: AXContextCmd = AXContextCmd()
    // chat mode select
    var modeSwitch: AXContextCmd = AXContextCmd()
    var mode: Cycler = Cycler(limit: 1)
    // engage chatbot
    var engage: AXContextCmd = AXContextCmd()
    // chatbots
    var chatbot1: AXNPC2 = AXNPC2(replyStockLim: 30, outputChance: 90) // pal mode chat module
    var chatbot2: AXNPC2 = AXNPC2(replyStockLim: 30, outputChance: 90) // discreet mode chat module
    // auto mode
    private var autoEngage: Responder = Responder("engage automatic mode", "automatic mode", "auto mode")
    private var shutUp: Responder = Responder("stop", "shut up", "silence", "be quite", "be silent")
    private var tg: TimeGate = TimeGate(pause: 5)
    private var nPCPlus: Int = 5 // increase rate of output in self auto reply mode
    private var nPCNeg: Int = -10 // decrease rate of output in self auto reply mode
    
    init(skill_name: String) {
        skillToggler.contextCommands.input(in1: "toggle \(skill_name)")
        skillToggler.commands.input(in1: "again")
        skillToggler.commands.input(in1: "repeat")
        modeSwitch.contextCommands.input(in1: "switch \(skill_name) mode")
        modeSwitch.commands.input(in1: "again")
        modeSwitch.commands.input(in1: "repeat")
        engage.contextCommands.input(in1: "engage \(skill_name)")
        engage.commands.input(in1: "talk")
        engage.commands.input(in1: "talk to me")
        engage.commands.input(in1: "again")
        engage.commands.input(in1: "repeat")
        mode.setToZero()
    }
    
    override func input(ear: String, skin: String, eye: String) {
        // skill toggle:
        if skillToggler.engageCommand(ear: ear) {
            isActive = !isActive
        }
        if !isActive {
            return
        }
        // chat-bot mode switch mode
        if modeSwitch.engageCommand(ear: ear) {
            mode.cycleCount()
            setSimpleAlg(sayThis: talkMode())
            return
        }
        
        switch mode.getMode() {
        case 0:
            mode0(ear)
        case 1:
            mode1(ear)
        default:
            break
        }
    }
    
    private func mode0(_ ear: String) {
        if !kokoro.toHeart["diblabber", default: ""].isEmpty {
            kokoro.toHeart["diblabber"] = ""
            setSimpleAlg(sayThis: chatbot1.forceRespond())
            return
        }
        NPCUtilization(chatbot1, ear)
    }
    
    private func mode1(_ ear: String) {
        // auto engage
        if autoEngage.strContainsResponse(ear: ear) {
            tg.openGate()
            setSimpleAlg(sayThis: "auto NPC mode engaged")
            return
        }
        if shutUp.strContainsResponse(ear:ear) {
            tg.closeGate()
            setSimpleAlg(sayThis:  "auto NPC mode disengaged")
            return
        }
        if tg.isOpen() {
            var plus = nPCNeg
            if !ear.isEmpty {
                plus = nPCPlus
            }
            let result = chatbot2.respondPlus(plus: plus)
            if !result.isEmpty {
                setSimpleAlg(sayThis: result)
                return
            }
        }
        // end auto engage code snippet
        NPCUtilization(chatbot2, ear)
    }
    
    private func talkMode() -> String {
        switch mode.getMode() {
        case 0:
            return "friend mode"
        case 1:
            return "discreet mode"
        default:
            return "mode switched"
        }
    }
    // auto mode setters
    func setNPCTimeSpan(_ n: Int) {
        tg.setPause(pause: n)
    }
    
    func setNPCNeg(_ n: Int) {
        // lower NPC auto output chance
        nPCNeg = n
    }
    
    func setNPCPlus(_ n: Int) {
        // increase NPC auto output chance
        nPCPlus = n
    }
    // chat module common tasks
    private func NPCUtilization(_ npc: AXNPC2, _ ear: String) {
        var result = ""
        // engage
        if engage.engageCommand(ear: ear) {
            result = npc.respond()
            if !result.isEmpty {
                setSimpleAlg(sayThis: result)
                return
            }
        }
        // str engage
        result = npc.strRespond(ear: ear)
        if !result.isEmpty {
            setSimpleAlg(sayThis: result)
        }
        // forced learn (say n)
        if !npc.learn(ear: ear) {
            // strlearn
            npc.strLearn(ear: ear)
        }
    }
}
class DiEngager: DiSkillV2 {
    private var burpsPerHour = 2
    private var trgMinute = TrgMinute(minute: 0)
    private var skillToEngage = "unknown"
    private var draw = DrawRndDigits()
    private var burpMinutes:Array<Int> = [Int]()
    private var pl = TimeUtils()
    
    init(burpsPerHour: Int, skillToEngage: String) {
        super.init()
        if burpsPerHour > 0 && burpsPerHour < 60 {
            self.burpsPerHour = burpsPerHour
        }
        for i in 1..<60 {
            draw.addElement(element: i)
        }
        for _ in 0..<burpsPerHour {
            burpMinutes.append(draw.draw())
        }
        self.skillToEngage = skillToEngage
    }
    
    func setSkillToEngage(skillToEngage: String) {
        self.skillToEngage = skillToEngage
    }
    
    override func input(ear: String, skin: String, eye: String) {
        if pl.partOfDay() == "night" {
            return
        }
        
        if trgMinute.trigger() {
            burpMinutes.removeAll()
            draw.reset()
            for _ in 0..<burpsPerHour {
                burpMinutes.append(draw.draw())
            }
            return
        }
        
        let nowMinutes = pl.getMinutesAsInt()
        if burpMinutes.contains(nowMinutes) {
            // snippet of code : remove item from array list
            burpMinutes.removeAll {value in return value == nowMinutes}
            self.kokoro.toHeart[skillToEngage] = "engage"
        }
    }
}
class DiBurper: DiSkillV2 {
    private var burpsPerHour = 2
    private var trgMinute = TrgMinute(minute: 0)
    private var burps:Responder = Responder("burp","burp2","burp3")
    private var draw = DrawRndDigits()
    private var burpMinutes:Array<Int> = [Int]()
    private var pl = TimeUtils()
    
    init(burpsPerHour: Int) {
        super.init()
        if burpsPerHour > 0 && burpsPerHour < 60 {
            self.burpsPerHour = burpsPerHour
        }
        for i in 1..<60 {
            draw.addElement(element: i)
        }
        for _ in 0..<burpsPerHour {
            burpMinutes.append(draw.draw())
        }
    }
    func setBurps(burps:Responder) {
        self.burps = burps
    }
    
    override func input(ear: String, skin: String, eye: String) {
        if pl.partOfDay() == "night" {
            return
        }
        
        if trgMinute.trigger() {
            burpMinutes.removeAll()
            draw.reset()
            for _ in 0..<burpsPerHour {
                burpMinutes.append(draw.draw())
            }
            return
        }
        
        let nowMinutes = pl.getMinutesAsInt()
        if burpMinutes.contains(nowMinutes) {
            // snippet of code : remove item from array list
            burpMinutes.removeAll {value in return value == nowMinutes}
            setSimpleAlg(sayThis: burps.getAResponse())
        }
    }
}
class DiHabit: DiSkillV2 {
    private var habitsPositive = UniqueItemSizeLimitedPriorityQueue()
    private var habitP = AXCmdBreaker(conjuration: "i should")
    private var temp = ""
    
    private var habitsNegative = UniqueItemSizeLimitedPriorityQueue()
    private var habitN = AXCmdBreaker(conjuration: "i must not")
    
    private var dailies = UniqueItemSizeLimitedPriorityQueue()
    private var dailyCmdBreaker = AXCmdBreaker(conjuration: "i out to")
    
    private var weekends = UniqueItemSizeLimitedPriorityQueue()
    private var weekendCmdBreaker = AXCmdBreaker(conjuration: "i have to")
    
    private var expirations = UniqueItemSizeLimitedPriorityQueue()
    private var expirationsCmdBreaker = AXCmdBreaker(conjuration: "i got to")
    
    private var todo = TODOListManager(todoLim: 5)
    private var toDoCmdBreaker = AXCmdBreaker(conjuration: "i need to")
    private var clearCmdBreaker = AXCmdBreaker(conjuration: "clear")
    
    private var getterCmdBreaker = AXCmdBreaker(conjuration: "random")
    private var strOrDefault = AXStrOrDefault()
    
    private var gamification = AXGamification()
    private var punishments = AXGamification()
    
    override init() {
        habitsPositive.setLimit(lim: 15)
        habitsNegative.setLimit(lim: 5)
        dailies.setLimit(lim: 3)
        weekends.setLimit(lim: 3)
        expirations.setLimit(lim: 3)
    }
    
    func getGamification() -> AXGamification {
        return gamification
    }
    
    func getPunishments() -> AXGamification {
        return punishments
    }
    
    override func input(ear: String, skin: String, eye: String) {
        if ear.isEmpty { return }
        
        if ear.contains("i") {
            temp = habitP.extractCmdParam(s1: ear)
            if !temp.isEmpty {
                habitsPositive.input(in1: temp)
                temp = ""
                setSimpleAlg(sayThis: "habit registered")
                return
            }
            temp = habitN.extractCmdParam(s1: ear)
            if !temp.isEmpty {
                habitsNegative.input(in1: temp)
                temp = ""
                setSimpleAlg(sayThis: "bad habit registered")
                return
            }
            temp = dailyCmdBreaker.extractCmdParam(s1: ear)
            if !temp.isEmpty {
                dailies.input(in1: temp)
                temp = ""
                setSimpleAlg(sayThis: "daily registered")
                return
            }
            temp = weekendCmdBreaker.extractCmdParam(s1: ear)
            if !temp.isEmpty {
                weekends.input(in1: temp)
                temp = ""
                setSimpleAlg(sayThis: "prep registered")
                return
            }
            temp = expirationsCmdBreaker.extractCmdParam(s1: ear)
            if !temp.isEmpty {
                expirations.input(in1: temp)
                temp = ""
                setSimpleAlg(sayThis: "expiration registered")
                return
            }
            temp = toDoCmdBreaker.extractCmdParam(s1: ear)
            if !temp.isEmpty {
                todo.addTask(e1: temp)
                temp = ""
                setSimpleAlg(sayThis: "task registered")
                return
            }
        }
        
        temp = getterCmdBreaker.extractCmdParam(s1: ear)
        if !temp.isEmpty {
            switch temp {
            case "habit":
                setSimpleAlg(sayThis: AXStrOrDefault().getOrDefault(str1: habitsPositive.getRndItem(), default1: "no habits registered"))
                return
            case "bad habit":
                setSimpleAlg(sayThis: AXStrOrDefault().getOrDefault(str1: habitsNegative.getRndItem(), default1: "no bad habits registered"))
                return
            case "daily":
                setSimpleAlg(sayThis: AXStrOrDefault().getOrDefault(str1: dailies.getRndItem(), default1: "no dailies registered"))
                return
            case "weekend", "prep":
                setSimpleAlg(sayThis: AXStrOrDefault().getOrDefault(str1: weekends.getRndItem(), default1: "no preps registered"))
                return
            case "expirations", "expiration":
                if expirations.getAsList().isEmpty {
                    setSimpleAlg(sayThis: "no expirations registered")
                    return
                }
                setVerbatimAlgFromList(priority: 4, sayThis: expirations.getAsList())
                return
            case "task":
                setSimpleAlg(sayThis: AXStrOrDefault().getOrDefault(str1: todo.getTask(), default1: "no new tasks registered"))
                return
            case "to do":
                setSimpleAlg(sayThis: AXStrOrDefault().getOrDefault(str1: todo.getOldTask(), default1: "no tasks registered"))
                return
            default:
                break
            }
        }
        
        if ear.contains("completed") {
            if !diSkillUtills.stringContainsListElement(str1: ear, items: habitsPositive.getAsList()).isEmpty {
                gamification.increment()
                setSimpleAlg(sayThis: "good boy")
                return
            }
            if !diSkillUtills.stringContainsListElement(str1: ear, items: habitsNegative.getAsList()).isEmpty {
                punishments.increment()
                setSimpleAlg(sayThis: "bad boy")
                return
            }
            if !diSkillUtills.stringContainsListElement(str1:ear, items: dailies.getAsList()).isEmpty {
                gamification.increment()
                setSimpleAlg(sayThis: "daily engaged")
                return
            }
            if !diSkillUtills.stringContainsListElement(str1:ear, items:weekends.getAsList()).isEmpty {
                setSimpleAlg(sayThis: "prep engaged")
                return
            }
        }
        
        switch ear {
        case "clear habits":
            habitsPositive.clearData()
            setSimpleAlg(sayThis: "habits cleared")
        case "clear bad habits":
            habitsNegative.clearData()
            setSimpleAlg(sayThis: "bad habits cleared")
        case "clear dailies":
            dailies.clearData()
            setSimpleAlg(sayThis: "dailies cleared")
        case "clear preps", "clear weekends":
            weekends.clearData()
            setSimpleAlg(sayThis: "preps cleared")
        case "clear expirations":
            expirations.clearData()
            setSimpleAlg(sayThis: "expirations cleared")
        case "clear tasks", "clear task", "clear to do":
            todo.clearAllTasks()
            setSimpleAlg(sayThis: "tasks cleared")
        case "clear all habits":
            habitsPositive.clearData()
            habitsNegative.clearData()
            dailies.clearData()
            weekends.clearData()
            expirations.clearData()
            todo.clearAllTasks()
            setSimpleAlg(sayThis: "all habits cleared")
        default:
            if ear.contains("clear") {
                temp = clearCmdBreaker.extractCmdParam(s1: ear)
                if todo.containsTask(task: temp) {
                    todo.clearTask(temp)
                    setSimpleAlg(sayThis: temp + " task cleared")
                    temp = ""
                }
            }
        }
    }
}
class DiSayer: DiSkillV2 {
    var cmdBreaker = AXCmdBreaker(conjuration: "say")
    var command = ""

    override func input(ear: String, skin: String, eye: String) {
        command = cmdBreaker.extractCmdParam(s1: ear)
        if !command.isEmpty {
            setSimpleAlg(sayThis: command)
            command = ""
        }
    }
}
class DiSmoothie0: DiSkillV2 {
    private var draw = DrawRnd("grapefruits", "oranges",  "apples", "peaches", "melons", "pears", "carrot")
    private var cmd = AXContextCmd()

    override init() {
        super.init()
        cmd.contextCommands.input(in1: "recommend a smoothie")
        cmd.commands.input(in1: "yuck")
        cmd.commands.input(in1: "lame")
        cmd.commands.input(in1: "nah")
        cmd.commands.input(in1: "no")
    }

    override func input(ear: String, skin: String, eye: String) {
        if cmd.engageCommand(ear: ear) {
            setSimpleAlg(sayThis: "\(draw.draw()) and \(draw.draw())")
            draw.reset()
        }
    }
}
class DiSmoothie1: DiSkillV2 {
    private var base = Responder("grapefruits", "oranges",  "apples", "peaches", "melons", "pears", "carrot")
    private var thickeners = DrawRnd("bananas", "mango", "strawberry", "pineapple", "dates")
    private var cmd = AXContextCmd()

    override init() {
        super.init()
        cmd.contextCommands.input(in1: "recommend a smoothie")
        cmd.commands.input(in1: "yuck")
        cmd.commands.input(in1: "lame")
        cmd.commands.input(in1: "nah")
        cmd.commands.input(in1: "no")
    }

    override func input(ear: String, skin: String, eye: String) {
        if cmd.engageCommand(ear: ear) {
            setSimpleAlg(sayThis: "use \(base.getAResponse()) as a base than add \(thickeners.draw()) and \(thickeners.draw())")
            thickeners.reset()
        }
    }
}
class DiJumbler: DiSkillV2{
    private var cmdBreaker = AXCmdBreaker(conjuration: "jumble the name")
        private var temp = ""
    override func input(ear: String, skin: String, eye: String) {
        temp = cmdBreaker.extractCmdParam(s1: ear)
        if temp.isEmpty {return}
        setSimpleAlg(sayThis: jumbleString(input: temp))
        temp = ""
    }
    func jumbleString(input: String) -> String {
        var characters = Array(input)
        characters.shuffle()
        return String(characters)
    }
}
class SkillBranch: DiSkillV2 {
    // unique skill used to bind similar skills
    /*
    * contains collection of skills
    * mutates active skill if detects conjuration
    * mutates active skill if algorithm results in
    * negative feedback
    * positive feedback negates active skill mutation
    * */
    private var skillRef: [String: Int] = [:]
    private var skillHub = SkillHubAlgDispenser()
    private var ml: AXLearnability

    init(tolerance: Int) {
        ml = AXLearnability()
        ml.trg.maxCount = tolerance
    }

    override func input(ear: String, skin: String, eye: String) {
        // conjuration alg morph
        if let skillIndex = skillRef[ear] {
            skillHub.setActiveSkillWithMood(skillIndex)
            setSimpleAlg(sayThis: "hmm")
        }
        // machine learning alg morph
        if ml.mutateAlg(input: ear) {
            skillHub.cycleActiveSkill()
            setSimpleAlg(sayThis: "hmm")
        }
        // alg engage
        if let a1 = skillHub.dispenseAlgorithm(ear: ear, skin: skin, eye: eye) {
            self.outAlg = a1.getAlg()
            self.outpAlgPriority = a1.getPriority()
            ml.pendAlg()
        }
    }

    func addSkill(_ skill:DiSkillV2) {
        skillHub.addSkill(skill)
    }

    func addReferencedSkill(skill: DiSkillV2, conjuration: String) {
        // the conjuration string will engage its respective skill
        skillHub.addSkill(skill)
        skillRef[conjuration] = skillHub.getSize()
    }

    // learnability params
    func addDefcon(_ defcon: String){ml.defcons.input(in1: defcon)}
    func addGoal(_ goal: String){ml.goal.input(in1: goal)}
    // while alg is pending, cause alg mutation ignoring learnability tolerance:
    func addDefconLV5(_ defcon5: String){ml.defcon5.input(in1: defcon5)}

    override func setKokoro(kokoro: Kokoro) {
        super.setKokoro(kokoro: kokoro)
        skillHub.setKokoro(kokoro)
    }
}
class DiAware: DiSkillV2 {
    private var chobit: Chobits
    private var name: String
    private var summoner: String = "user"
    private var skills: [String] = []

    init(chobit: Chobits, name: String, summoner: String) {
        self.chobit = chobit
        self.name = name
        self.summoner = summoner
    }

    override func input(ear: String, skin: String, eye: String) {
        switch ear {
            case "list skills":
                skills = chobit.getSkillList()
                setVerbatimAlgFromList(priority: 4, sayThis: skills)
            case "what is your name":
            setSimpleAlg(sayThis: name)
            case "name summoner":
            setSimpleAlg(sayThis: summoner)
            case "how do you feel":
                // handle in hardware skill in hardwer chobit
                kokoro.toHeart["last_ap"] = chobit.getSoulEmotion()
            default:
                break
        }
    }
}
class DiBicameral: DiSkillV2 {
    /*
     *   let bicameral = DiBicameral()
         bicameral.msgCol.addMSGV2("02:57", "test run ok")
         add # for messages that engage other skills
     */
    public var msgCol:TimedMessages = TimedMessages()

    override func input(ear: String, skin: String, eye: String) {
        msgCol.tick()
        if kokoro.toHeart["dibicameral"] != "null" {
            kokoro.toHeart["dibicameral"] = "null"
        }
        if msgCol.getMsg() {
            let temp = msgCol.getLastMSG()
            if !temp.contains("#") {
                setSimpleAlg(sayThis: temp)
            } else {
                kokoro.toHeart["dibicameral"] = temp.replacingOccurrences(of: "#", with: "")
            }
        }
    }
    override func setKokoro(kokoro: Kokoro) {
        super.setKokoro(kokoro: kokoro)
        kokoro.toHeart["dibicameral"] = "null"
    }
}
class DiSkillBundle: DiSkillV2 {
    fileprivate let axSkillBundle = AXSkillBundle()

    override func input(ear: String, skin: String, eye: String) {
        if let a1 = axSkillBundle.dispenseAlgorithm(ear: ear, skin: skin, eye: eye) {
            self.outAlg = a1.getAlg()
            self.outpAlgPriority = a1.getPriority()
        }
    }

    override func setKokoro(kokoro: Kokoro) {
        super.setKokoro(kokoro: kokoro)
        axSkillBundle.setKokoro(kokoro)
    }

    func addSkill(_ skill: DiSkillV2) {
        axSkillBundle.addSkill(skill)
    }
}
// gamification classes
class GamiPlus: DiSkillV2 {
    // The grind side of the game; see GamificationN for the reward side
    private let gain: Int
    private let skill: DiSkillV2
    private let axGamification: AXGamification

    init(skill: DiSkillV2, axGamification: AXGamification, gain: Int) {
        self.skill = skill
        self.axGamification = axGamification
        self.gain = gain
    }

    override func input(ear: String, skin: String, eye: String) {
        skill.input(ear: ear, skin: skin, eye: eye)
    }

    override func output(noiron: Neuron) {
        // Skill activation increases gaming credits
        if skill.pendingAlgorithm() {
            axGamification.incrementBy(amount: gain)
        }
        skill.output(noiron: noiron)
    }
}
class GamiMinus: DiSkillV2 {
    private let axGamification: AXGamification
    private let cost: Int
    private let skill: DiSkillV2

    init(skill: DiSkillV2, axGamification: AXGamification, cost: Int) {
        self.skill = skill
        self.axGamification = axGamification
        self.cost = cost
    }

    override func input(ear: String, skin: String, eye: String) {
        // Engage skill only if a reward is possible
        if axGamification.surplus(cost: cost) {
            skill.input(ear: ear, skin: skin, eye: eye)
        }
    }

    override func output(noiron: Neuron) {
        // Charge reward if an algorithm is pending
        if skill.pendingAlgorithm() {
            axGamification.reward(cost: cost)
            skill.output(noiron: noiron)
        }
    }
}
class DiGamificationSkillBundle: DiSkillBundle {
    private let axGamification = AXGamification()
    private var gain = 1
    private var cost = 3

    func setGain(_ gain: Int) {
        if gain > 0 {
            self.gain = gain
        }
    }

    func setCost(_ cost: Int) {
        if cost > 0 {
            self.cost = cost
        }
    }

    func addGrindSkill(_ skill: DiSkillV2) {
        axSkillBundle.addSkill(GamiPlus(skill: skill, axGamification: axGamification, gain: gain))
    }

    func addCostlySkill(_ skill: DiSkillV2) {
        axSkillBundle.addSkill(GamiMinus(skill: skill, axGamification: axGamification, cost: cost))
    }
    func getAxGamification() -> AXGamification {
        return axGamification
    }
}
class DiGamificationScouter: DiSkillV2 {
    private var lim = 2 // minimum for mood
    private let axGamification: AXGamification
    private let noMood = Responder("bored", "no emotions detected", "neutral", "machine")
    private let yesMood = Responder("operational", "efficient", "mission ready", "awaiting orders")

    init(axGamification: AXGamification) {
        self.axGamification = axGamification
    }

    func setLim(_ lim: Int) {
        self.lim = lim
    }

    override func input(ear: String, skin: String, eye: String) {
        if ear != "how are you" {
            return
        }
        if axGamification.getCounter() > lim {
            setSimpleAlg(sayThis: yesMood.getAResponse())
        } else {
            setSimpleAlg(sayThis: noMood.getAResponse())
        }
    }
}
