//
//  auxiliary_module.swift
//  LivinGrimoireSwiftV1
//
//  Created by moti barski on 16/08/2022.
//

import Foundation
// DETECTORS
class EmoRecognizer {
    let happy = Responder("good","awesome","great","wonderful","sweet","happy")
    let curious = Responder("why","where","when","how","who","which","whose")
    let angry = Responder("hiyas","fudge","angry","waste","stupid","retard")
    let excited = Responder("to")
    func strContains(str1:String, r:Responder) -> Bool {
        for line in r.responses {
            if str1.contains(line){
                return true
            }
        }
        return false
    }
    func isExcited(in1:String) -> Bool {
        return strContains(str1: in1, r: excited)
    }
    func isAngry(in1:String) -> Bool {
        return strContains(str1: in1, r: angry)
    }
    func isHappy(in1:String) -> Bool {
        return strContains(str1: in1, r: happy)
    }
    func isCurious(in1:String) -> Bool {
        return strContains(str1: in1, r: curious)
    }
}

// RESPONDERS
class RepeatedElements{
    // detects repeating elements
    let p1:PriorityQueue<String> = PriorityQueue<String>()
    let p2:PriorityQueue<String> = PriorityQueue<String>()
    var queLimit:Int = 5
    func input(in1:String) {
        if p1.elements.contains(in1){
            p2.insert(in1)
            if p2.size() > queLimit {p2.poll()}
        }
        p1.insert(in1)
        if p1.size() > queLimit {p1.poll()}
    }
    func getRandomElement()->String{
        return p2.elements.randomElement() ?? ""
    }
    func contains(str:String) -> Bool {
        return p2.elements.contains(str)
    }
}
class ForcedLearn{
    var keyWord:String = "say"
    let rUtil:RegexUtil = RegexUtil()
    let p1:PriorityQueue<String> = PriorityQueue<String>()
    var queLimit:Int = 5
    func input(in1:String) {
        if rUtil.firstWord(str2Check: in1).elementsEqual(keyWord){
            p1.insert(in1.replacingOccurrences(of: keyWord, with: "").trimmingCharacters(in: .whitespacesAndNewlines))
            if p1.size() > queLimit {p1.poll()}
        }
    }
    func getRandomElement()->String{
        return p1.elements.randomElement() ?? ""
    }
    func contains(str:String) -> Bool {
        return p1.elements.contains(str)
    }
}
class Responder{
    // simple random response dispenser
    var responses: Array<String> = [String]()
    init(_ replies:String...) {
        for str1:String in replies {
            responses.append(str1)
        }
    }
    func getAResponse() -> String {
        return responses.randomElement() ?? ""
    }
    func contains(str:String) -> Bool {
        return responses.contains(str)
    }
}
// TRIGGERS
public class TrGEV3 {
    // advanced boolean gates with internal logic
    // these ease connecting common logic patterns, as triggers
    func reset() {
    }

    func input(ear:String, skin:String, eye:String) {
    }

    func trigger() -> Bool{
        return false;
    }
}
class EV3DaisyChain {
    // this class connects several logic gates triggers together
    var trgGates:Array<TrGEV3> = [TrGEV3]()
    func EV3DaisyChain(_ gates:TrGEV3...) {
        for g8t:TrGEV3 in gates{
            trgGates.append(g8t)
        }
    }
    func reset() {
        for g8t:TrGEV3 in trgGates{
            g8t.reset()
        }
    }

    func input(ear:String, skin:String, eye:String) {
        for g8t:TrGEV3 in trgGates{
            g8t.input(ear: ear, skin: skin, eye: eye)
        }
    }

    func trigger() -> Bool{
        var result:Bool = true;
        for g8t:TrGEV3 in trgGates{
            result = result && g8t.trigger()
        }
        return result;
    }
}
class TrgRnd:TrGEV3{
    var reps:Int = 0
    var maxReps:Int = 2
    override init() {
    }
    init(maxReps:Int) {
        self.maxReps = maxReps
    }
    override func reset() {
        // refill trigger
        reps = Int.random(in: 0...maxReps)
    }
    override func trigger() -> Bool {
        // connect to input filter with trigger 2
        if reps > 0 {
            reps-=1
            return true
        }
        return false
    }
}
class TrgMinute:TrGEV3{
    // trigger true at minute once per hour
    var hour1:Int = -1
    let minute:Int
    override init() {
        minute = Int.random(in: 0...59)
    }
    init(minute:Int) {
        self.minute = minute
    }
    let pl:PlayGround = PlayGround()
    override func trigger() -> Bool {
        let tempHour:Int = pl.getHoursAsInt()
        if tempHour != hour1 {
            if pl.getMinutesAsInt() == minute {
                hour1 = tempHour
                return true
            }
        }
        return false
    }
    override func reset() {
        hour1 = -1
    }
}
class TrgParrot:TrGEV3{
    var maxTolerance:Int = 10
    var tolerance:Int = 10
    var kokoro:Kokoro
    // responses
    var defaultResponse:Responder = Responder("hadouken","chi2","chi3","chi4","shoryuken")
    let repeatedResponder:RepeatedElements = RepeatedElements()
    let forcedResponses:ForcedLearn = ForcedLearn()
    let replenisher:String = "talk to me"
    private(set) var output:String = ""
    var convo:Bool = false
    let pl:PlayGround = PlayGround()
    var trgMinute:TrgMinute = TrgMinute()
    let shutUp:Responder = Responder("ok","okay","stop","shut up","quiet")
    let emoRecognizer:EmoRecognizer = EmoRecognizer()
    init(kokoro:Kokoro) {
        self.kokoro = kokoro
    }
    override func reset() {
        // replenish
        tolerance = maxTolerance
    }
    override func trigger() -> Bool {
        return false
    }
    override func input(ear: String, skin: String, eye: String) {
        // learn new responses
        if !ear.contains(forcedResponses.keyWord){
            repeatedResponder.input(in1: ear)}
        forcedResponses.input(in1: ear)
        if pl.isNight() {return}
        // force convo
        if ear == replenisher {
            reset();calcResponse(ear: ear)
            convo = true
            return
        }
        if kokoro.standBy {
            reset()
            convo = false
            return
        }
        // shut up ()
        if shutUp.contains(str: ear){
            reset()
            convo = false
            return
        }
        // engage or continue convo
        if detectedInput(ear: ear) {
            if tolerance == 0 {convo = false}
            else if !ear.isEmpty {tolerance -= 1;calcResponse(ear: ear);return}
        }
        if trgMinute.trigger() {
            calcResponse(ear: ear)
        }
    }
    func calcResponse(ear:String) {
        let path:Int = Int.random(in: 0...2)
        if emoRecognizer.isAngry(in1: ear) {
            output = "chii angry";return
        }
        if emoRecognizer.isCurious(in1: ear) {
            output = "chii Curious";return
        }
        if emoRecognizer.isHappy(in1: ear) {
            output = "chii Happy";return
        }
        switch (path)  {
        case 1:
            output = forcedResponses.getRandomElement()
        case 2:
            output = repeatedResponder.getRandomElement()
        default:
            output = defaultResponse.getAResponse()
        }
    }
    func detectedInput(ear: String) -> Bool {
        if defaultResponse.contains(str: ear) || forcedResponses.contains(str: ear) || repeatedResponder.contains(str: ear){
            convo = true
        }
        return convo
    }
    func getOutput() -> String{
        let temp = output;output = ""
        return temp
    }
    func setMaxTolerance(newMaxTolerance:Int) {
        maxTolerance = newMaxTolerance
    }
}
