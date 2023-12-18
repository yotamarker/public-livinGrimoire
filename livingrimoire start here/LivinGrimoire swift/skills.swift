//
//  skills.swift
//  LivinGrimoireSwiftV1
//
//  Created by moti barski on 31/08/2022.
//

import Foundation

class DiTime:DiSkillV2{
    private let pl:PlayGround = PlayGround()
    // hello world skill for testing purposes
    override func input(ear: String, skin: String, eye: String) {
        switch (ear)  {
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
