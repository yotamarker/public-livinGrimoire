//
//  auxiliary_module.swift
//  LivinGrimoireSwiftV1
//
//  Created by moti barski on 16/08/2022.
//

import Foundation
// (*)Algorithm Dispensers
class AlgDispenser {
    // super class to output an algorithm out of a selection of algorithms
    var algs:Array<Algorithm> = [Algorithm]()
    var activeAlg:Int = 0
    init(_algorithms:Algorithm...){
        for alg in _algorithms {
            algs.append(alg)
        }
    }
    func addAlgorithm(alg:Algorithm) -> AlgDispenser {
        algs.append(alg)
        return self
    }
    func dispenseAlgorithm() -> Algorithm {
        return algs[activeAlg]
    }
    func rndAlg(){
        activeAlg = Int.random(in: 0..<algs.count)
    }
    func moodAlg (mood:Int){
        let c1:Int = algs.count
        if -1<mood && mood<c1 {
            activeAlg = mood
        }
    }
    func cycleAlg(){
        activeAlg += 1
        if activeAlg == algs.count {activeAlg = 0}
    }
}
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
// (*) input filters
class InputFilter{
    /// filter out non relevant input
    func filter(ear: String, skin: String, eye: String) -> String {
        /// override me
        return ""
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
    var defaultResponse:Responder = Responder("hadouken","talk","chi3","chi4","shoryuken")
    let repeatedResponder:RepeatedElements = RepeatedElements()
    let forcedResponses:ForcedLearn = ForcedLearn()
    let replenisher:String = "hey"
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
class UniqueItemsPriorityQue{
    /// a priority queue without repeating elements
    var p1:PriorityQueue<String> = PriorityQueue<String>()
    var queLimit:Int = 5
    init() {
    }
    init(queLimit:Int) {
        self.queLimit = queLimit
    }
    init(_items:String...) {
        self.queLimit = _items.count
        for item in _items{
            p1.insert(item)
        }
    }
    /// insert an item into the queue
    func input(in1:String) {
        if !p1.elements.contains(in1){
            p1.insert(in1)
            if p1.size() > queLimit {p1.poll()}
        }
    }
    func contains(str:String) -> Bool {
        return p1.elements.contains(str)
    }
    @discardableResult
    func poll() -> String {
        guard !p1.isEmpty() else {
        return ""
      }
        return p1.elements.removeFirst()
    }
      func size() -> Int {
          return p1.size()
      }
    @discardableResult
    func peak() -> String {
        guard !p1.isEmpty() else {
        return ""
      }
        return p1.elements[0]
    }
    func deleteStr(str1:String){
        for i in 0...p1.size()-1 {
            if p1.elements[i] == str1 {
                p1.elements.remove(at: i)
                break
            }
        }
    }
    func getRndItem()-> String{
        guard !p1.isEmpty() else {
        return ""
      }
        return p1.elements.randomElement()!
    }
    func clearData() {
        p1 = PriorityQueue<String>()
    }
  }
class TrgCountDown {
    var maxCount:Int = 2
    var count:Int
    init() {
        count = maxCount
    }
    init(limit:Int) {
        count = limit
        maxCount = limit
    }
    @discardableResult
    func countDown() -> Bool{
        count -= 1
        if (count == 0) {
            reset()
            return true
        }
        return false
    }
    func reset() {
        count = maxCount
    }
}
// (*) misc
class DrawRnd {
    private var numbers:Array<Int> = [Int]()
    init(size:Int){
        for index in 1...size{
            numbers.append(index)
        }
    }
    init(_ markers:Int...) {
        for num in markers {
            numbers.append(num)
        }
    }
    func draw() -> Int {
        if numbers.isEmpty {return 0}
        let x:Int = Int.random(in: 0..<numbers.count)
        let element:Int = numbers[x]
        numbers.remove(at: x)
        return element
    }
}
// (*) learnability
class AXLearnability {
    var defcons:UniqueItemsPriorityQue = UniqueItemsPriorityQue()
    var algSent:Bool = false
    var goal:UniqueItemsPriorityQue = UniqueItemsPriorityQue()
    var defcon5:UniqueItemsPriorityQue = UniqueItemsPriorityQue()
    let trg:TrgCountDown = TrgCountDown() // set lim
    func pendAlg() {
        // an algorithm has been deployed
        algSent = true
        trg.countDown()
    }
    func mutateAlg(input:String) -> Bool {
        // recommendation to mutate the algorithm ? true/ false
        if !algSent {return false} // no alg sent=> no reason to mutate
        if goal.contains(str: input){trg.reset();algSent = false;return false}
        // goal manifested the sent algorithm is good => no need to mutate the alg
        if defcon5.contains(str: input) {trg.reset();algSent = false; return true}
        // something bad happend probably because of the sent alg
        // recommend alg mutation
        if defcons.contains(str: input){algSent = false;return trg.countDown()}
        // negative result, mutate the alg if this occures too much
        return false
    }
}
class SpiderSense {
    /// enables event prediction
    private var spiderSense:Bool = false
    var events:UniqueItemsPriorityQue = UniqueItemsPriorityQue()
    var alerts:UniqueItemsPriorityQue = UniqueItemsPriorityQue()
    var prev:String = ""
    init() {
    }
    /// event's predictions become event's predictions, enabling  earlier but less
    ///  aqurate predictions, these can be used in detective work
    init(lv1:SpiderSense) {
        self.events = lv1.events
    }
    /// input param  can be run through an input filter prior to this function
    /// weather related data (sky state) only for example for weather events predictions
    func learn(in1:String) {
        // simple prediction of an event from the events que :
        if alerts.contains(str: in1){
            spiderSense = true;return
        }
        // event has occured, remember what lead to it
        if events.contains(str: in1){
            alerts.input(in1: prev);return
        }
        // nothing happend
        prev = in1
    }
    func getSpiderSense() -> Bool {
        // spider sense is tingling
        let t = spiderSense;spiderSense = false
        return t
    }
}
class Log:UniqueItemsPriorityQue {
    /// chronological log of inputs
    private var lastWithdrawal:String = ""
    override func peak() -> String {
        /// get last memory
        lastWithdrawal = super.peak()
        return lastWithdrawal
    }
    override func getRndItem() -> String {
        /// get random memory
        lastWithdrawal = super.getRndItem()
        return lastWithdrawal
    }
    @discardableResult
    func removeLastWithdrawal() ->String{
        /// remove last processed memory
        deleteStr(str1: self.lastWithdrawal)
        let temp = lastWithdrawal
        lastWithdrawal = ""
        return temp
    }
    override func poll() -> String {
        return peak()
    }
}
class AXListeaNLearn{
    /// this module learn places, items and persons relevant to the skill's goal
    ///  by listening to people
    let logNegative:Log
    let logPossitive:Log
    init(logMinus:Log, logPlus:Log) {
        self.logNegative = logMinus
        self.logPossitive = logPlus
    }
    // learnability mutation = true
    func forget() {
        /// an algorithm using the item, failed (reported by a Learnability class or a cloudian object)
        ///  and so it will be moved to the lies Log
        let temp = logPossitive.removeLastWithdrawal()
        logNegative.input(in1: temp)
    }
    func clearNegativeLog() {
        /// clear memory of lies and failed items
        ///  this can be done once in a time period
        ///   allowing for reconsideration of items that didn't work in the past
        ///   but may work in the future
        logNegative.clearData()
    }
    func peak() -> String {
        // get 1st item from assumed working items, log
        return logPossitive.peak()
    }
    func RndPeak() -> String {
        // get random item from assumed working items, log
        return logPossitive.getRndItem()
    }
    /// new data should be aquired via a regex for the verb/verb that correspond to
    ///  the skill using the module
    func insert(str1:String) {
        // failed data AKA a lie is not accepted
        if !logNegative.contains(str: str1){
            logPossitive.input(in1: str1)
        }
    }
}
// maps
class Map {
    private var pointDescription:[String:String] = [:]
    private var descriptionPoint:[String:String] = [:]
    private var currentPosition:LGPointInt = LGPointInt()
    private let regexUtil:RegexUtil = RegexUtil()
    func reset() {
        currentPosition.x = 0
        currentPosition.y = 0
    }
    func moveBy(x:Int, y:Int) {
        currentPosition.shift(x: x, y: y)
    }
    func moveTo(location:String) {
        if let safeOptional = descriptionPoint[location]{
            let text:String = safeOptional
            let tempPoint:LGPointInt = regexUtil.intPointRegex(text: text)
            currentPosition.x = tempPoint.x
            currentPosition.y = tempPoint.y
        }
    }
    func write(description:String) {
        let pointStr = currentPosition.toString()
        pointDescription[pointStr] = description
        descriptionPoint[description] = pointStr
    }
    func read() -> String {
        return pointDescription[currentPosition.toString()] ?? "null"
    }
    func read(p1:LGPointInt) -> String {
        // used for predition
        return pointDescription[p1.toString()] ?? "null"
    }
    func read(description:String) -> String {
        return descriptionPoint[description] ?? "null"
    }
}
