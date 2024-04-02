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
class SkillHubAlgDispenser {
    /// super class to output an algorithm out of a selection of skills
    ///  engage the hub with dispenseAlg and return the value to outAlg attribute
    ///  of the containing skill (which houses the skill hub)
    ///  this module enables using a selection of 1 skill for triggers instead of having the triggers engage on multible skill
    ///   the methode is ideal for learnability and behavioral modifications
    ///   use a learnability auxiliary module as a condition to run an active skill shuffle or change methode
    ///   (rndAlg , cycleAlg)
    ///   moods can be used for specific cases to change behavior of the AGI, for example low energy state
    ///   for that use (moodAlg)
    var skills:Array<DiSkillV2> = [DiSkillV2]()
    var activeSkill:Int = 0
    init(_skills:DiSkillV2...){
        for skill in _skills {
            self.skills.append(skill)
        }
    }
    @discardableResult
    func addSkill(skill:DiSkillV2) -> SkillHubAlgDispenser {
        self.skills.append(skill)
        return self
    }
    func dispenseAlgorithm(ear:String, skin:String, eye:String) -> Algorithm? {
        skills[activeSkill].input(ear: ear, skin: skin, eye: eye)
        return skills[activeSkill].outAlg
    }
    func rndAlg(){
        activeSkill = Int.random(in: 0..<skills.count)
    }
    func moodAlg (mood:Int){
        let c1:Int = skills.count
        if -1<mood && mood<c1 {
            activeSkill = mood
        }
    }
    func cycleAlg(){
        activeSkill += 1
        if activeSkill == skills.count {activeSkill = 0}
    }
    func getSize() -> Int {
        return skills.count
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
class AXKeyValuePair{
    private var key:String = ""
    private var value:String = ""
    func getKey()->String{return key}
    func getValue()->String{return value}
    func setKey(key:String){self.key = key}
    func setValue(value:String){self.value = value}
}
class InputFilter{
    // filter out non-relevant input
    // or filter in relevant data
    func filter(ear: String, skin: String, eye: String) -> String {
        /// override me
        return ""
    }
    func filter(ear:String)->AXKeyValuePair{
        // override me : key = context/category, value: param
        return AXKeyValuePair()
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
    func addResponse(s1:String){self.responses.append(s1)}
    func strContainsResponse(ear:String) -> Bool {
        var result:Bool = false
        for tempStr:String in responses{
            if ear.contains(tempStr){
                result = true
                break
            }
        }
        return result
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
    func strContains(str1:String) -> Bool {
        for line in p1.elements {
            if str1.contains(line){
                return true
            }
        }
        return false
    }
  }
class UniqueItemSizeLimitedPriorityQueue:UniqueItemsPriorityQue{
    // items in the queue are unique and do not repeat
    // the size of the queue is limited
    private var limit:Int = 5
    func getLimit()->Int{return limit}
    func setLimit(lim:Int){self.limit = lim}
    override func input(in1: String) {
        if size() == limit {
            super.poll()
        }
        super.input(in1: in1)
    }
    func getAsList()->[String]{
        return super.p1.elements
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
    // draw a random element, than take said element out
    private var strings:Array<String> = [String]()
    private var stringsSource:Array<String> = [String]()
    init(_ values:String...) {
        for temp in values {
            strings.append(temp)
            stringsSource.append(temp)
        }
    }
    func draw() -> String {
        if strings.isEmpty {return ""}
        let x:Int = Int.random(in: 0..<strings.count)
        let element:String = strings[x]
        strings.remove(at: x)
        return element
    }
    func reset(){
        let dc:DeepCopier = DeepCopier()
        strings = dc.copyList(original: stringsSource)
    }
    func getSimpleRNDNum(bound:Int)->Int{
        // return 0->bound-1
        return Int.random(in: 0...bound-1)
    }
    private let tc:LGTypeConverter = LGTypeConverter()
    func drawAsInt()->Int{
        return tc.convertToInt(v1: draw())
    }
    func addElement(_ element:String) {
        strings.append(element)
        stringsSource.append(element)
    }
    func isEmptied() -> Bool {
        return self.strings.count == 0
    }
}
class LGTypeConverter{
    func convertToInt(v1:String)->Int{
        return Int(v1) ?? 0
    }
    func convertToDouble(v1:String)->Double{
        return Double(v1) ?? 0
    }
}
// (*) learnability
class AXLearnability {
    var algSent:Bool = false
    // problems that may result because of the last deployed algorithm:
    var defcons:UniqueItemsPriorityQue = UniqueItemsPriorityQue() // default size = 5
    var goal:UniqueItemsPriorityQue = UniqueItemsPriorityQue()
    // major problems that force an alg to mutate
    var defcon5:UniqueItemsPriorityQue = UniqueItemsPriorityQue()
    let trg:TrgCountDown = TrgCountDown() // set lim
    func pendAlg() {
        // an algorithm has been deployed
        // call this method when an algorithm is deployed (in a DiSkillV2 object)
        algSent = true
        trg.countDown()
    }
    func pendAlgWithoutConfirmation() {
        // an algorithm has been deployed
        // call this method when an algorithm is deployed (in a DiSkillV2 object)
        algSent = true
        //no need to await for a thank you or check for goal manifestation :
        // trgTolerance.trigger();
        // using this method instead of the default "pendAlg" is the same as
        // giving importance to the stick and not the carrot when learning
        // this method is mosly fitting work place situations
    }
    func mutateAlg(input:String) -> Bool {
        // you can use an input filter to define defcons
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
    func resetTolerance() {
        // use when you run code to change algorithms regardless of learnability
        trg.reset()
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
class AXLHousing{
    func decorate(str1:String)->String{
        // override me
        return ""
    }
}
class Cycler{
    private var cycler:Int = 0
    private var limit:Int
    init(limit:Int) {
        self.limit = limit
        self.cycler = limit
    }
    func getLimit()->Int{
        return self.limit
    }
    func setLimit(limit:Int){
        self.limit = limit
    }
    @discardableResult
    func cycleCount()->Int{
        cycler-=1
        if(cycler < 0){
            cycler = limit
        }
        return cycler
    }
    func reset(){
        cycler = limit
    }
    func setToZero(){
        cycler = 0
    }
    func sync(n:Int){
        if (-1>n) || (n>limit) {
            return
        }
        cycler = n
    }
    func getMode() -> Int {
        return cycler
    }
}
class OutPutDripper{
    // drips true once every limit times
    // shushes the waifubot enough time to hear a reply from user
    private var cycler:Int = 0
    private var limit:Int // set to 1 for on off effect
    init(limit:Int) {
        self.limit = limit
        self.cycler = limit
    }
    func getLimit()->Int{
        return self.limit
    }
    func setLimit(limit:Int){
        self.limit = limit
    }
    func drip()->Bool{
        cycler-=1
        if(cycler < 0){
            cycler = limit
        }
        return cycler == 0
    }
    func reset(){
        cycler = limit
    }
    func setToZero(){
        cycler = 0
    }
    func sync(n:Int){
        if (-1>n) || (n>limit) {
            return
        }
        cycler = n
    }
}
class AXLHub{
    // hubs many reply decorators, language translators, encriptors and other string modifiers
    // decorate(str) to decorate string using the active string decorator
    private let cycler:Cycler
    private let drawRnd:DrawRnd = DrawRnd()
    private var size:Int = 0
    private var nyaa:Array<AXLHousing> = [AXLHousing]()
    private var activeNyaa:Int = 0
    init(_nyaa:AXLHousing...) {
        for temp in nyaa{
            self.nyaa.append(temp)
        }
        size = self.nyaa.count
        cycler = Cycler(limit: size - 1)
        cycler.setToZero()
    }
    func decorate(str1:String)->String{
        return nyaa[activeNyaa].decorate(str1: str1)
    }
    func cycleDecoration(){
        activeNyaa = cycler.cycleCount()
    }
    func randomizeDecoration(){
        activeNyaa = drawRnd.getSimpleRNDNum(bound: size)
    }
    func modeDecoratrion(mode:Int){
        if mode < 0{return}
        if mode >= nyaa.count {return}
        activeNyaa = mode
    }
}
class AXNeuroSama{
    private let nyaa:Responder = Responder(" heart", " heart", " wink", " heart heart heart")
    private let rnd:DrawRnd = DrawRnd()
    private var rate:Int
    init(rate: Int) {
        // the higher the rate the less likely to decorate outputs
        // recomended value = 3
        self.rate = rate
    }
    func decorate(output:String)->String{
        if output.isEmpty{return ""}
        if rnd.getSimpleRNDNum(bound: rate) == 0{return output + nyaa.getAResponse()}
        return output
    }
}
class AXLNeuroSama{
    private let nyaa:AXNeuroSama = AXNeuroSama(rate: 3)
    func decorate(_ str1: String) -> String {
        return self.nyaa.decorate(output: str1)
    }
}
class Strategy{
    private var activeStrategy:UniqueItemsPriorityQue // active strategic options
    private var allStrategies:DrawRnd // bank of all strategies. out of this pool active strategies are pulled
    init(allStrategies: DrawRnd) {
        // create the strategy Object with a bank of options
        self.allStrategies = allStrategies
        self.activeStrategy = UniqueItemsPriorityQue()
    }
    func evolveStrategies(strategiesLimit:Int){
        // add N strategic options to the active strategies bank, from the total strategy bank
        self.activeStrategy.queLimit = strategiesLimit
        var temp:String = allStrategies.draw()
        for _ in 0...strategiesLimit{
            if(temp.isEmpty){break}
            activeStrategy.input(in1: temp)
            temp = allStrategies.draw()
        }
        allStrategies.reset()
    }
    func getStrategy()->String{return activeStrategy.getRndItem()}
}
class AXStrategy{
    /* this auxiliary module is used to output strategies based on context
            can be used for battles, and games
            upon pain/lose use the evolve methode to update to different new active strategies
            check for battle state end externaly (opponent state/hits on rival counter)
        a dictionary of strategies*/
    private var lim:Int
    private var strategies:[String:Strategy]=[:]
    init(lim: Int) {
        // limit of active strategies (pulled from all available strategies)
        self.lim = lim
    }
    func addStrategy(context:String, techniques:DrawRnd){
        // add strategies per context
        let temp:Strategy = Strategy(allStrategies: techniques)
        temp.evolveStrategies(strategiesLimit: lim)
        self.strategies[context] = temp
    }
    func evolve(){
        // replace active strategies
        let keys = self.strategies.keys
        for key in keys{
            self.strategies[key]!.evolveStrategies(strategiesLimit: lim)
        }
    }
    func process(context:String)->String{
        // process input, return action based on game context now
        return self.strategies[context]?.getStrategy() ?? ""
    }
}

class TODOListManager{
    /* manages to do tasks.
    q1 tasks are mentioned once, and forgotten
    backup tasks are the memory of recently mentioned tasks
    * */
    var q1:UniqueItemSizeLimitedPriorityQueue = UniqueItemSizeLimitedPriorityQueue()
    var backup:UniqueItemSizeLimitedPriorityQueue = UniqueItemSizeLimitedPriorityQueue()
    init(todoLim:Int) {
        self.q1.setLimit(lim: todoLim)
        self.backup.setLimit(lim: todoLim)
    }
    func addTask(e1:String){
        q1.input(in1: e1)
    }
    func getTask()->String{
        let temp:String = self.q1.poll()
        if !temp.isEmpty {backup.input(in1: temp)}
        return temp
    }
    func getOldTask()->String{
        // task graveyard (tasks you've tackled already)
        return backup.getRndItem()
    }
    func clearAllTasks(){
        q1.clearData()
        backup.clearData()
    }
    func containsTask(task:String) -> Bool {
        return backup.contains(str: task)
    }
    func clearTask(_ task:String) {
        q1.deleteStr(str1: task)
        backup.deleteStr(str1: task)
    }
}
class PersistentQuestion{
    private var isActive:Bool = false
    private var mode:String = "yes" // key mode
    private var dic:[String:DrawRnd]=[:]
    private var outputDripper:OutPutDripper = OutPutDripper(limit: 1)
    private var loggedAnswer:String = "" // only used in log() which replaces process()
    // getters and setters
    func getLoggedAnswer()->String{
        return self.loggedAnswer
    }
    func setLoggedAnswer(loggedAnswer:String){
        // underuse
        self.loggedAnswer = loggedAnswer
    }
    func getMode()->String{
        return mode
    }
    func setMode(newMode:String){
        // dictionary contains key code:
        if dic.keys.contains(newMode){
            mode = newMode
        }
    }
    func setPause(pause:Int){
        // set pause between question to wait for answer
        self.outputDripper.setLimit(limit: pause)
    }
    func activate(){
        self.isActive = true
    }
    func deActivate(){
        self.isActive = false
        self.dic[mode]!.reset()
    }
    func getIsActive()->Bool{
        return self.isActive
    }
    // end setters and getters
    func addPath(answer:String, nags:DrawRnd){
        self.dic[answer]=nags
    }
    func process(inp:String)->String{
        // got answer?
        if dic.keys.contains(inp){
            mode = inp
            isActive = false
            dic[mode]!.reset()
            return "okay"; // can extend code to reply key, rnd finalizer
        }
        // nag for answer
        if !outputDripper.drip(){
            return ""
        }
        let result:String = dic[mode]!.draw()
        if !result.isEmpty {
            return result
        }
        dic[mode]!.reset()
        isActive = false
        return "i see"
    }
    func log(inp:String)->String{
        // got answer?
        if dic.keys.contains(inp){
            mode = inp
            loggedAnswer = inp
            isActive = false
            dic[mode]!.reset()
            return "okay"; // can extend code to reply key, rnd finalizer
        }
        if !inp.isEmpty{
            loggedAnswer = inp
            isActive = false
            dic[mode]!.reset()
            return "okay"; // can extend code to reply key, rnd finalizer
        }
        // nag for answer
        if !outputDripper.drip(){
            return ""
        }
        let result:String = dic[mode]!.draw()
        if !result.isEmpty {
            return result
        }
        dic[mode]!.reset()
        isActive = false
        return "i see"
    }
}
class AXGamification{
    // this auxiliary module can add fun to tasks, skills, and abilities simply by
    // tracking their usage, and maximum use count.
    private var counter:Int = 0
    private var max:Int = 0
    func getCounter()->Int{
        return counter
    }
    func getMax()->Int{
        return max
    }
    func resetCounter(){
        counter = 0
    }
    func resetAll(){
        counter = 0; max = 0
    }
    func increment(){
        counter += 1
        if counter > max{
            max = counter
        }
    }
    func incrementBy(amount:Int){
        counter += amount
        if counter > max{
            max = counter
        }
    }
    @discardableResult
    func reward(cost:Int)->Bool{
        // game grind points used for rewards
        // consumables, items or upgrades this makes games fun
        if cost > counter{return false}
        counter -= cost
        return true
    }
    func surplus(cost:Int)->Bool{
        // has surplus for reward?
        if cost > counter{return false}
        return true
    }
}
class Differ{
    private var powerLevel:Int = 90
    private var difference:Int = 0
    func getPoweLevel()->Int{
        return self.powerLevel
    }
    func getPowerLVDifference()->Int{
        return self.difference
    }
    func clearPowerLVDifference(){
        self.difference = 0
    }
    func samplePowerLV(pl:Int){
        // pl is the current power level
        self.difference = pl - self.powerLevel
        self.powerLevel = pl
    }
}

class TrgTolerance {
    // this boolean gate will return true till depletion or reset()
    var maxRepeats:Int
    var repeats:Int = 0
    init(maxRepeats:Int) {
        self.maxRepeats = maxRepeats
    }
    func setMaxRepeats(maxRepeats:Int){
        self.maxRepeats = maxRepeats
        reset()
    }
    @discardableResult
    func trigger() -> Bool{
        // will return true till depletion or reset()
        repeats -= 1
        if (repeats > 0) {
            return true
        }
        return false
    }
    func reset() {
        // refill trigger
        self.repeats = self.maxRepeats
    }
    func disable(){
        repeats = 0
    }
}
// command auxiliary modules collection
class AXCmdBreaker{
    // separate command parameter from the command
    var conjuration:String
    init(conjuration: String) {
        self.conjuration = conjuration
    }
    func extractCmdParam(s1:String)->String{
        if s1.contains(conjuration){
            return s1.replacingOccurrences(of: conjuration, with: "").trimmingCharacters(in: .whitespacesAndNewlines)
        }
        return ""
    }
}

class AXInputWaiter{
    // wait for any input
    private var trgTolerance:TrgTolerance
    init(tolerance: Int) {
        self.trgTolerance = TrgTolerance(maxRepeats: tolerance)
        self.trgTolerance.reset()
    }
    func reset(){
        self.trgTolerance.reset()
    }
    func wait(s1:String)->Bool{
        // return true till any input detected or till x times of no input detection
        if !s1.isEmpty {
            trgTolerance.disable()
            return false
        }
        return self.trgTolerance.trigger()
    }
    func setWait(timesToWait:Int){
        trgTolerance.setMaxRepeats(maxRepeats: timesToWait)
    }
}

class AXMachineCode{
    // common code lines used in machine code to declutter machine code
    var dic:[String:Int] = [:]
    @discardableResult
    func addKeyValuePair(key:String,value:Int)->AXMachineCode{
        dic[key] = value
        return self
    }
    func getMachineCodeFor(key:String)->Int{
        return dic[key, default: -1]
    }
}

class AXContextCmd{
    // engage on commands
    // when commands are engaged, context commans can also engage
    public var commands:UniqueItemSizeLimitedPriorityQueue = UniqueItemSizeLimitedPriorityQueue()
    public var contextCommands:UniqueItemSizeLimitedPriorityQueue = UniqueItemSizeLimitedPriorityQueue()
    private var trgTolerance:Bool = false
    func engageCommand(ear:String) -> Bool {
        if ear.isEmpty{return false}
        if contextCommands.contains(str: ear){
            trgTolerance = true
            return true
        }
        if trgTolerance && !commands.contains(str: ear){
            trgTolerance = false
            return false
        }
        return trgTolerance
    }
    func disable(){
        // context commands are disabled till next engagement with a command
        trgTolerance = false
    }
}
// command auxiliary modules collection end
class DiSysOut:DiSkillV2{
    // hello world skill for testing purposes
    override func input(ear: String, skin: String, eye: String) {
        if(!ear.isEmpty && !ear.contains("#")){
            print(ear)
        }
    }
}
class AXLSpeechModifier:AXLHousing{
    public var dic:[String:String] = [:]
    init(dic:[String:String]){
        self.dic = dic
    }
    override func decorate(str1: String) -> String {
        var result:String = ""
        let words = str1.components(separatedBy: " ")
        for item in words{
            result = result + " " + dic[item, default: item]
        }
        return result.trimmingCharacters(in: NSCharacterSet.whitespaces)
    }
}
class Responder1Word{
    // learns 1 word inputs
    // outputs learned recent words
    var q:UniqueItemsPriorityQue = UniqueItemsPriorityQue()
    init() {
        self.q.input(in1: "chi")
        self.q.input(in1: "gugu")
        self.q.input(in1: "gaga")
        self.q.input(in1: "baby")
    }
    func listen(ear:String){
        if(!(ear.contains(" ") || ear.isEmpty)){
            q.input(in1: ear)
        }
    }
    func getAResponse()->String{
        return q.getRndItem()
    }
    func contains(ear:String)->Bool{
        return q.contains(str: ear)
    }
}
class TimeAccumulator{
    // accumulator ++ each tick minutes interval
    private let timeGate:TimeGate
    private var accumulator:Int = 0
    init(tick:Int) {
        // accumulation ticker
        timeGate = TimeGate(pause: tick)
        timeGate.openGate()
    }
    func setTick(tick:Int){
        timeGate.setPause(pause: tick)
    }
    func tick(){
        if timeGate.isClosed(){
            timeGate.openGate()
            accumulator+=1
        }
    }
    func getAccumulator()->Int{
        return accumulator
    }
    func reset(){
        accumulator = 0
    }
    func decAccumulator(){
        if accumulator > 0 {
            accumulator -= 1
        }
    }
}
class TrgTime{
    var t:String = "null"
    let regexUtil:RegexUtil = RegexUtil()
    var pl:PlayGround = PlayGround()
    private var alarm:Bool = true
    func setTime(v1:String){
        t = regexUtil.regexChecker(theRegex: enumRegexGrimoire.simpleTimeStamp, str2Check: v1)
    }
    func trigger()->Bool{
        let now:String = pl.getCurrentTimeStamp()
        if alarm{
            if now == t{
                alarm = false
                return true
            }
        }
        if !(now == t){
            alarm = true
        }
        return false
    }
}
class TrgEveryNMinutes:TrGEV3{
    // trigger returns true every minutes interval, post start time
    private var minutes:Int // minute interval between triggerings
    private let pl:PlayGround = PlayGround()
    private var trgTime:TrgTime
    private var timeStamp:String = ""
    init(startTime:String, minutes:Int) {
        self.minutes = minutes
        self.timeStamp = startTime
        trgTime = TrgTime()
        trgTime.setTime(v1: startTime)
    }
    func setMinutes(minutes:Int){
        // set interval between trigger times
        if minutes > -1{
            self.minutes = minutes
        }
    }
    override func trigger() -> Bool {
        if trgTime.trigger() {
            timeStamp = pl.getFutureInXMin(extra_minutes: minutes)
            trgTime.setTime(v1: timeStamp)
            return true
        }
        return false
    }
    override func reset() {
        timeStamp = pl.getCurrentTimeStamp()
        trgTime.setTime(v1: timeStamp)
    }
}
class Cron:TrGEV3{
    // triggers true, limit times, after initial time, and every minutes interval
    // counter resets at initial time, assuming trigger method was run
    private var minutes:Int // minute interval between triggerings
    private let pl:PlayGround = PlayGround()
    private var trgTime:TrgTime
    private var timeStamp:String = ""
    private var initialTimeStamp:String = ""
    private var limit:Int
    private var counter:Int = 0
    init(startTime:String, minutes:Int, limit:Int) {
        self.minutes = minutes
        self.timeStamp = startTime
        self.initialTimeStamp = startTime
        trgTime = TrgTime()
        trgTime.setTime(v1: startTime)
        self.limit = limit
        if limit<0{
            self.limit = 1
        }
    }
    func getIimit()->Int{
        return limit
    }
    func setLimit(lim:Int){
        if lim > -1{
            limit = lim
        }
    }
    func getCounter()->Int{
        return self.counter
    }
    func setMinutes(minutes:Int){
        // set interval between trigger times
        if minutes > -1{
            self.minutes = minutes
        }
    }
    override func trigger() -> Bool {
        // delete counter = 0 if you don't want the trigger to work the next day
        if counter == limit{
            trgTime.setTime(v1: initialTimeStamp)
            counter = 0
            return false
        }
        if trgTime.trigger() {
            timeStamp = pl.getFutureInXMin(extra_minutes: minutes)
            trgTime.setTime(v1: timeStamp)
            counter += 1
            return true
        }
        return false
    }
    func triggerWithoutRenewal() -> Bool {
        if counter == limit{
            trgTime.setTime(v1: initialTimeStamp)
            return false
        }
        if trgTime.trigger() {
            timeStamp = pl.getFutureInXMin(extra_minutes: minutes)
            trgTime.setTime(v1: timeStamp)
            counter += 1
            return true
        }
        return false
    }
    override func reset() {
        // manual trigger reset
        counter = 0
    }
    func setStartTime(t1:String){
        initialTimeStamp = t1
        timeStamp = t1
        trgTime.setTime(v1: t1)
        counter = 0
    }
    func turnOff() {
        counter = limit
    }
}
class AXStrOrDefault{
    func getOrDefault(str1:String, default1:String) -> String {
        return str1.isEmpty ? default1 : str1
    }
}
class AXStringSplit{
    // may be used to prepare data before saving or after loading
    // the advantage is less data fields. for example: {skills: s1_s2_s3}
    private var separator:Character = "_"
    func setSeparator(separator:Character) {
        self.separator = separator
    }
    func splitStr(s1:String)->Array<String>{
        var result:Array<String> = [String]()
        let temp = s1.split(separator: self.separator)
        for item in temp{
            result.append(String(item))
        }
        return result
    }
    func stringBuilder(strArrList:Array<String>) -> String {
        return strArrList.joined(separator: String(separator))
    }
}
class RefreshQ:UniqueItemSizeLimitedPriorityQueue{
    func removeItem(item:String) {
        super.p1.elements.removeAll(where: { $0 == item })
    }
    override func input(in1: String) {
        // FILO
        if super.contains(str: in1){
            removeItem(item: in1)
        }
        super.input(in1: in1)
    }
}
class PercentDripper {
    private let dr:DrawRnd = DrawRnd()
    private var limis:Int = 35
    
    func setLimis(_ limis: Int) {
        self.limis = limis
    }
    
    func drip() -> Bool {
        return dr.getSimpleRNDNum(bound: 100) < limis
    }
    
    func dripPlus(_ plus: Int) -> Bool {
        return dr.getSimpleRNDNum(bound: 100) < limis + plus
    }
}
class AXNPC {
    public var responder:RefreshQ = RefreshQ()
    public var dripper:PercentDripper = PercentDripper()
    public var cmdBreaker:AXCmdBreaker = AXCmdBreaker(conjuration: "say")
    init(replyStockLim:Int,outputChance:Int) {
        responder.setLimit(lim: replyStockLim)
        if 0 < outputChance && outputChance < 101{
            dripper.setLimis(outputChance)
        }
    }
    
    func respond() -> String {
        if dripper.drip() {
            return responder.getRndItem()
        }
        return ""
    }
    func respondPlus(plus:Int) -> String {
        if dripper.dripPlus(plus) {
            return responder.getRndItem()
        }
        return ""
    }
    @discardableResult
    func learn(ear:String) -> Bool{
        // say hello there : hello there is learned
        let temp:String = cmdBreaker.extractCmdParam(s1: ear)
        if temp.isEmpty {return false}
        responder.input(in1: temp)
        return true
    }
    func strRespond(ear:String) -> String {
        // respond if ear contains a learned input
        if ear.isEmpty {
            return ""
        }
        if dripper.drip() && responder.strContains(str1: ear){
            return responder.getRndItem()
        }
        return ""
    }
    func forceRespond() -> String {
        return responder.getRndItem()
    }
    func setConjuration(conjuration:String) {
        self.cmdBreaker.conjuration = conjuration
    }
}
class AXTimeContextResponder {
    private var pl:PlayGround = PlayGround()
    var morning:Responder = Responder()
    var afternoon:Responder = Responder()
    var evening:Responder = Responder()
    var night:Responder = Responder()
    fileprivate var responders = [String: Responder]()
    
    init() {
        responders["morning"] = morning
        responders["afternoon"] = afternoon
        responders["evening"] = evening
        responders["night"] = night
    }
    
    func respond() -> String {
        return responders[pl.partOfDay()]?.getAResponse() ?? ""
    }
}
class ChatBot {
    /*
     let chatbot:ChatBot = ChatBot(logParamLim: 5)
     chatbot.addParam("name", "jinpachi")
     chatbot.addParam("name", "sakura")
     chatbot.addParam("verb", "eat")
     chatbot.addParam("verb", "code")

     chatbot.addSentence("i can verb #")

     chatbot.learnParam("ryu is a name")
     chatbot.learnParam("ken is a name")
     chatbot.learnParam("drink is a verb")
     chatbot.learnParam("rest is a verb")

     chatbot.learnV2("hello ryu i like to code")
     chatbot.learnV2("greetings ken")
     for _ in 0...10{
         print(chatbot.talk())
     }
     */
    var sentences = RefreshQ()
    var wordToList = [String: RefreshQ]()
    private var regexUtil = RegexUtil()
    var allParamRef = [String: String]()
    var paramLim = 5
    var loggedParams = RefreshQ()
    private var conjuration = "is a"
    
    init(logParamLim: Int) {
        loggedParams.setLimit(lim: logParamLim)
    }
    
    func setConjuration(_ conjuration: String) {
        self.conjuration = conjuration
    }
    
    func setSentencesLim(_ lim: Int) {
        sentences.setLimit(lim: lim)
    }
    
    func setParamLim(_ paramLim: Int) {
        self.paramLim = paramLim
    }
    
    func getWordToList() -> [String: RefreshQ] {
        return wordToList
    }
    
    func talk() -> String {
        let result = sentences.getRndItem()
        return clearRecursion(result)
    }
    
    private func clearRecursion(_ result: String) -> String {
        var tempResult:String = result
        var params = [String]()
        params = regexUtil.extractAllRegexResults(regex: "(\\w+)(?= #)", text: result)
        for strI in params {
            guard let temp = wordToList[strI] else { continue }
            let s1 = temp.getRndItem()
            tempResult = tempResult.replacingOccurrences(of: strI + " #", with: s1)
        }
        if !tempResult.contains("#") {
            return tempResult
        } else {
            return clearRecursion(tempResult)
        }
    }
    
    func addParam(_ category: String, _ value: String) {
        if !(wordToList.keys.contains(category)) {
            let temp = RefreshQ()
            temp.setLimit(lim: paramLim)
            wordToList[category] = temp
        }
        wordToList[category]?.input(in1: value)
        allParamRef[value] = category
    }
    // same as the addParam but only the latest parameter is saved
    // used for topics, names, cases where 1 latest parameter is needed
    func addSubject(_ category: String, _ value: String) {
        if !(wordToList.keys.contains(category)) {
            let temp = RefreshQ()
            temp.setLimit(lim: 1)
            wordToList[category] = temp
        }
        wordToList[category]?.input(in1: value)
        allParamRef[value] = category
    }
    
    func addParam(_ kv: AXKeyValuePair) {
        if !(wordToList.keys.contains(kv.getKey())) {
            let temp = RefreshQ()
            temp.setLimit(lim: paramLim)
            wordToList[kv.getKey()] = temp
        }
        wordToList[kv.getKey()]?.input(in1: kv.getValue())
        allParamRef[kv.getValue()] = kv.getKey()
    }
    
    func addSentence(_ sentence: String) {
        sentences.input(in1: sentence)
    }
    
    func learn(_ s1: String) {
        var s1 = " " + s1
        for key in wordToList.keys {
            s1 = s1.replacingOccurrences(of: " " + key, with: " \(key) #")
        }
        sentences.input(in1: s1.trimmingCharacters(in: .whitespaces))
    }
    @discardableResult
    func learnV2(_ s1: String) -> Bool {
        let OGStr = s1
        var s1 = " " + s1
        for key in allParamRef.keys {
            s1 = s1.replacingOccurrences(of: " " + key, with: " \(allParamRef[key]!) #")
        }
        s1 = s1.trimmingCharacters(in: .whitespaces)
        if OGStr != s1 {
            sentences.input(in1: s1)
            return true
        }
        return false
    }
    
    func learnParam(_ s1: String) {
        guard s1.contains(conjuration) else { return }
        let category = regexUtil.afterWord(word: conjuration, str2Check: s1)
        guard wordToList.keys.contains(category) else { return }
        let param = s1.replacingOccurrences(of: conjuration + " " + category, with: "").trimmingCharacters(in: .whitespaces)
        wordToList[category]?.input(in1: param)
        allParamRef[param] = category
        loggedParams.input(in1: s1)
    }
    // add key value pair collected by an AXPrompt object
    func addParamFromAXPrompt(_ kv: AXKeyValuePair) {
        if !(wordToList.keys.contains(kv.getKey())) {
            return
        }
        wordToList[kv.getKey()]?.input(in1: kv.getValue())
        allParamRef[kv.getValue()] = kv.getKey()
    }
    // load entire RefreshQ of parameters
    // example : list of nicknames per name
    // this special use case requires a specialized Object to retain
    // a set topic(name) and {category, param1#param2#...}(converted into a que)
    func addRefreshQ(_ category: String, _ q1: RefreshQ) {
        if !(wordToList.keys.contains(category)) {
            return
        }
        wordToList[category]! = q1
    }
    
    func getALoggedParam() -> String {
        return loggedParams.getRndItem()
    }
}
class Prompt {
    let regexUtil:RegexUtil = RegexUtil()
    var kv:AXKeyValuePair = AXKeyValuePair()
    var prompt:String = ""
    var regex:String = ""
    
    init() {
        kv.setKey(key: "default")
    }
    
    func getPrompt() -> String {
        return prompt
    }
    
    func setPrompt(prompt: String) {
        self.prompt = prompt
    }
    
    func process(in1: String) -> Bool {
        kv.setValue(value: regexUtil.regexChecker(theRegex: regex, str2Check: in1))
        return kv.getValue().isEmpty
    }
    
    func getKv() -> AXKeyValuePair {
        return kv
    }
    
    func setRegex(regex: String) {
        self.regex = regex
    }
}
class AXPrompt {
    var isActive: Bool = false
    var index: Int = 0
    var prompts: Array<Prompt> = [Prompt]()
    var kv: AXKeyValuePair? = nil
    
    func addPrompt(_ p1: Prompt) {
        prompts.append(p1)
    }
    
    func getPrompt() -> String {
        if prompts.isEmpty {
            return ""
        }
        return prompts[index].getPrompt()
    }
    
    func process(_ in1: String) {
        if prompts.isEmpty || !isActive {
            return
        }
        let b1 = prompts[index].process(in1: in1)
        if !b1 {
            kv = prompts[index].getKv()
            index += 1
        }
        if index == prompts.count {
            isActive = false
        }
    }
    
    func getActive() -> Bool {
        return isActive
    }
    
    func getKv() -> AXKeyValuePair? {
        if kv == nil {
            return nil
        }
        let temp = AXKeyValuePair()
        temp.setKey(key: kv!.getKey())
        temp.setValue(value: kv!.getValue())
        kv = nil
        return temp
    }
    
    func activate() {
        isActive = true
        index = 0
    }
    
    func deactivate() {
        isActive = false
        index = 0
    }
}
class AXPassword {
    private var isOpen: Bool = false
    private var maxAttempts: Int = 3
    private var loginAttempts: Int = 3
    private var regexUtil: RegexUtil = RegexUtil()
    private var code: Int = 0
    
    func codeUpdate(ear: String) -> Bool {
        if !isOpen {
            return false
        }
        if ear.contains("code") {
            let temp = regexUtil.regexChecker(theRegex: enumRegexGrimoire.integer, str2Check: ear)
            if !temp.isEmpty {
                code = Int(temp)!
                return true
            }
        }
        return false
    }
    
    func openGate(ear: String) {
        if ear.contains("code") && loginAttempts > 0 {
            let noCode = regexUtil.regexChecker(theRegex: enumRegexGrimoire.integer, str2Check: ear)
            if noCode.isEmpty {
                return
            }
            let tempCode = Int(noCode)!
            if tempCode == code {
                loginAttempts = maxAttempts
                isOpen = true
            } else {
                loginAttempts -= 1
            }
        }
    }
    
    func isGateOpen() -> Bool {
        return isOpen
    }
    
    func resetAttempts() {
        loginAttempts = maxAttempts
    }
    
    func getLoginAttempts() -> Int {
        return loginAttempts
    }
    
    func closeGate() {
        isOpen = false
    }
    
    func closeGate(ear: String) {
        if ear.contains("close") {
            isOpen = false
        }
    }
    
    func setMaxAttempts(maxAttempts: Int) {
        self.maxAttempts = maxAttempts
    }
    
    func getCode() -> Int {
        if isOpen {
            return code
        }
        return -1
    }
    
    func randomizeCode(lim: Int, minimumLim: Int) {
        code = DrawRnd().getSimpleRNDNum(bound: lim) + minimumLim
    }
    
    func getCodeEvent() -> Int {
        return code
    }
}
class AnnoyedQue{
    private var q1:RefreshQ
    private var q2:RefreshQ
    init(queLim:Int) {
        self.q1 = RefreshQ(queLimit: queLim)
        self.q2 = RefreshQ(queLimit: queLim)
    }
    func learn(ear:String) {
        if q1.contains(str: ear){
            q2.input(in1: ear)
            return
        }
        q1.input(in1: ear)
    }
    func isAnnoyed(ear:String) -> Bool {
        return q2.strContains(str1: ear)
    }
}
class AXNPC2:AXNPC{
    public var annoyedQue:AnnoyedQue = AnnoyedQue(queLim: 5)
    func strLearn(ear:String) {
        // learns inputs containing strings that are repeatedly used by others
        annoyedQue.learn(ear: ear)
        if annoyedQue.isAnnoyed(ear: ear){
            responder.input(in1: ear)
        }
    }
}
class TrgArgue{
    public var commands:UniqueItemSizeLimitedPriorityQueue = UniqueItemSizeLimitedPriorityQueue()
    public var contextCommands:UniqueItemSizeLimitedPriorityQueue = UniqueItemSizeLimitedPriorityQueue()
    private var trgTolerance:Bool = false
    private var counter:Int = 0
    func getCounter() -> Int {
        return counter
    }
    @discardableResult
    func engageCommand(ear:String) -> Int {
        // 0-> no engagement
        // 1-> engaged boolean gate (request made)
        // 2-> engaged argument : consecutive request made (request in succession after a previous request)
        if ear.isEmpty{return 0}
        if contextCommands.contains(str: ear){
            if trgTolerance{
                counter += 1
            }
            trgTolerance = true
            return 1
        }
        if trgTolerance {
            if !commands.strContains(str1: ear){
                trgTolerance = false
                counter = 0
                return 0
            }else{
                counter += 1
                return 2
            }
        }
        return 0
    }
    func disable(){
        // context commands are disabled till next engagement with a command
        trgTolerance = false
    }
}
class Magic8Ball {
    private var questions:Responder = Responder()
    private var answers:Responder = Responder()
    
    func setQuestions(_ questions: Responder) {
        self.questions = questions
    }
    
    func setAnswers(_ answers: Responder) {
        self.answers = answers
    }
    
    func getQuestions() -> Responder {
        return questions
    }
    
    func getAnswers() -> Responder {
        return answers
    }
    
    init() {
        // answers :
        // Affirmative Answers
        answers.addResponse(s1: "It is certain")
        answers.addResponse(s1: "It is decidedly so")
        answers.addResponse(s1: "Without a doubt")
        answers.addResponse(s1: "Yes definitely")
        answers.addResponse(s1: "You may rely on it")
        answers.addResponse(s1: "As I see it, yes")
        answers.addResponse(s1: "Most likely")
        answers.addResponse(s1: "Outlook good")
        answers.addResponse(s1: "Yes")
        answers.addResponse(s1: "Signs point to yes")
        // Non – Committal Answers
        answers.addResponse(s1: "Reply hazy, try again")
        answers.addResponse(s1: "Ask again later")
        answers.addResponse(s1: "Better not tell you now")
        answers.addResponse(s1: "Cannot predict now")
        answers.addResponse(s1: "Concentrate and ask again")
        // Negative Answers
        answers.addResponse(s1: "Don’t count on it")
        answers.addResponse(s1: "My reply is no")
        answers.addResponse(s1: "My sources say no")
        answers.addResponse(s1: "Outlook not so good")
        answers.addResponse(s1: "Very doubtful")
        // questions :
        questions = Responder("will i", "can i expect", "should i", "may i","is it a good idea","will it be a good idea for me to","is it possible","future hold","will there be")
    }
    
    func engage(_ ear: String) -> Bool {
        if ear.isEmpty {
            return false
        }
        if questions.strContainsResponse(ear: ear) {
            return true
        }
        return false
    }
    
    func reply() -> String {
        return answers.getAResponse()
    }
}
class AXShoutOut {
    private var isActive: Bool = false
    var handshake:Responder = Responder()
    
    func activate() {
        // make engage-able
        isActive = true
    }
    
    func engage(ear: String) -> Bool {
        if ear.isEmpty {
            return false
        }
        
        if isActive {
            if handshake.strContainsResponse(ear: ear) {
                isActive = false
                return true // shout out was replied!
            }
        }
        // unrelated reply to shout out, shout out context is outdated
        isActive = false
        return false
    }
}
class AXHandshake {
    private var trgTime:TrgTime = TrgTime()
    private var trgTolerance:TrgTolerance = TrgTolerance(maxRepeats: 10)
    private var shoutOut:AXShoutOut = AXShoutOut()
    private var user_name:String = "user"
    private var dripper:PercentDripper = PercentDripper()
    
    init() {
        // default handshakes (valid reply to shout out)
        shoutOut.handshake = Responder("what", "yes", "i am here")
    }
    // setters
    @discardableResult
    func setTimeStamp(_ time_stamp: String) -> AXHandshake {
        // when will the shout-out happen?
        // example time stamp: 9:15
        trgTime.setTime(v1: time_stamp)
        return self
    }
    @discardableResult
    func setShoutOutLim(_ lim: Int) -> AXHandshake {
        // how many times should user be called for, per shout out?
        trgTolerance.setMaxRepeats(maxRepeats: lim)
        return self
    }
    @discardableResult
    func setHandShake(_ responder: Responder) -> AXHandshake {
        // which responses would acknowledge the shout-out?
        // such as *see default handshakes for examples suggestions
        shoutOut.handshake = responder
        return self
    }
    @discardableResult
    func setDripperPercent(_ n: Int) -> AXHandshake {
        // when shout out to user how frequent will it be?
        dripper.setLimis(n)
        return self
    }
    
    func setUser_name(_ user_name: String) {
        self.user_name = user_name
    }
    // getters
    func getUser_name() -> String {
        return user_name
    }
    
    func engage(_ ear: String) -> Bool {
        if trgTime.trigger() {
            trgTolerance.reset()
        }
        // stop shout out
        if shoutOut.engage(ear: ear) {
            trgTolerance.disable()
            return true
        }
        
        return false
    }
    
    func trigger() -> Bool {
        if trgTolerance.trigger() {
            if dripper.drip() {
                shoutOut.activate()
                return true
            }
        }
        
        return false
    }
}
class DrawRndDigits {
    // draw a random integer, than takes said element out
    private var strings:Array<Int> = [Int]()
    private var stringsSource:Array<Int> = [Int]()
    init(_ values:Int...) {
        for temp in values {
            strings.append(temp)
            stringsSource.append(temp)
        }
    }
    func draw() -> Int {
        if strings.isEmpty {return -1}
        let x:Int = Int.random(in: 0..<strings.count)
        let element:Int = strings[x]
        strings.remove(at: x)
        return element
    }
    func reset(){
        let dc:DeepCopier = DeepCopier()
        strings = dc.copyListOfInts(original: stringsSource)
    }
    func getSimpleRNDNum(bound:Int)->Int{
        // return 0->bound-1
        return Int.random(in: 0...bound-1)
    }
    func addElement(element:Int) {
        strings.append(element)
        stringsSource.append(element)
    }
}

class Eliza {
    static let reflections = [
        "am": "are",
        "was": "were",
        "i": "you",
        "i'd": "you would",
        "i've": "you have",
        "my": "your",
        "are": "am",
        "you've": "I have",
        "you'll": "I will",
        "your": "my",
        "yours": "mine",
        "you": "i",
        "me": "you"
    ]

    class PhraseMatcher {
        let matcher: NSRegularExpression
        let responses: [String]
        var context: String = ""  // last speech context (subject or pattern)
        var param: String = ""  // last param extracted
        var infoRequest: String = ""  // request more info on input

        init(matcher: String, responses: [String]) {
            self.matcher = try! NSRegularExpression(pattern: matcher, options: [])
            self.responses = responses
        }

        func matches(_ str: String) -> Bool {
            let range = NSRange(location: 0, length: str.utf16.count)
            return matcher.firstMatch(in: str, options: [], range: range) != nil
        }

        func respond(_ str: String) -> String {
            let range = NSRange(location: 0, length: str.utf16.count)
            guard let m = matcher.firstMatch(in: str, options: [], range: range) else { return "" }
            context = matcher.pattern  // context
            var p = randomPhrase()
            for i in 0..<m.numberOfRanges {
                let s = reflect(getParam(string2: str, string1: context))
                param = s  // param
                infoRequest = p  // more info request
                p = p.replacingOccurrences(of: "{\(i)}", with: s)
            }
            return p
        }
        func getParam(string2:String,string1:String) -> String {

            let words1 = string1.split(separator: " ").map(String.init)
            let words2 = string2.split(separator: " ").map(String.init)

            let difference = words2.filter { !words1.contains($0) }
            let differenceAsString = difference.joined(separator: " ")
            return differenceAsString
        }
        func reflect(_ s: String) -> String {
            var words = s.split(separator: " ")
            for i in 0..<words.count {
                if let reflection = Eliza.reflections[String(words[i])] {
                    words[i] = Substring(reflection)
                }
            }
            return words.joined(separator: " ")
        }

        func randomPhrase() -> String {
            return responses[Int.random(in: 0..<responses.count)]
        }

        var description: String {
            return "\(matcher.pattern): \(responses)"
        }
    }

    var babble = [
        PhraseMatcher(matcher: "i need (.*)", responses: ["Why do you need {0}?",
                                                          "Would it really help you to get {0}?",
                                                          "Are you sure you need {0}?"])
    ]

    func respond(_ msg: String) -> String {
        for pm in babble {
            if pm.matches(msg) {
                return pm.respond(msg.lowercased())
            }
        }
        return ""
    }
}
class RailChatBot {
    private var dic: [String: RefreshQ] = [:]
    private var context: String = "default"

    init() {
        self.dic[context] = RefreshQ()
    }

    func setContext(context: String) {
        if context.isEmpty { return }
        self.context = context
    }

    func respondMonolog(ear: String) -> String {
        if ear.isEmpty { return "" }
        if dic[ear] == nil {
            dic[ear] = RefreshQ()
        }
        let temp: String = dic[ear]?.getRndItem() ?? ""
        if !temp.isEmpty { context = temp }
        return temp
    }

    func learn(ear: String) {
        if ear.isEmpty { return }
        if dic[ear] == nil {
            dic[ear] = RefreshQ()
            dic[context]?.input(in1: ear)
            context = ear
            return
        }
        dic[context]?.input(in1: ear)
        context = ear
    }

    func monolog() -> String {
        return respondMonolog(ear: context)
    }

    func respondDialog(ear: String) -> String {
        if ear.isEmpty { return "" }
        if dic[ear] == nil {
            dic[ear] = RefreshQ()
        }
        let temp: String = dic[ear]?.getRndItem() ?? ""
        return temp
    }
}
class OnOffSwitch {
    private var mode: Bool = false
    private var timeGate: TimeGate = TimeGate(pause: 5)
    private var on: Responder = Responder("on","talk to me")
    private var off: Responder = Responder("off","stop","shut up", "shut it","whatever","whateva")

    func setPause(minutes: Int) {
        self.timeGate.setPause(pause: minutes)
    }

    func setOn(on: Responder) {
        self.on = on
    }

    func setOff(off: Responder) {
        self.off = off
    }

    func getMode(ear: String) -> Bool {
        if on.contains(str: ear) {
            timeGate.openGate()
            mode = true
            return true
        } else if off.contains(str: ear) {
            timeGate.closeGate()
            mode = false
        }
        if timeGate.isClosed() { mode = false }
        return mode
    }
}
class AXFunnel {
    // Funnel many inputs to fewer or one input
    // Allows using command variations in skills

    private var dic: [String: String]
    private var defaultStr: String

    init() {
        dic = [:]
        defaultStr = "default"
    }

    func setDefault(_ defaultStr: String) {
        self.defaultStr = defaultStr
    }

    @discardableResult
    func addKV(key: String, value: String) -> Self {
        // Add key-value pair
        dic[key] = value
        return self
    }

    @discardableResult
    func addK(key: String) -> Self {
        // Add key with default value
        dic[key] = defaultStr
        return self
    }

    func funnel(_ key: String) -> String {
        // Get value from dictionary or return the key itself as default
        return dic[key] ?? key
    }
}
class ChangeDetector {
    private var A: String
    private var B: String
    private var prev: Int = -1

    init(a: String, b: String) {
        A = a
        B = b
    }

    func detectChange(ear: String) -> Int {
        // a->b return 2; b->a return 1; else return 0
        if ear.isEmpty {
            return 0
        }
        var current: Int = -1
        if ear.contains(A) {
            current = 1
        } else if ear.contains(B) {
            current = 2
        } else {
            return 0
        }
        var result: Int = 0
        if current == 1 && prev == 2 {
            result = 1
        }
        if current == 2 && prev == 1 {
            result = 2
        }
        prev = current
        return result
    }
}
