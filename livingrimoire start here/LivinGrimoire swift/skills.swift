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

