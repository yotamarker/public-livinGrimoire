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
class PerChance{
    /*
     * extend me and add sentences and lists for parameters in the sentences in the
     * sub classes c'tor.
      replicate speech paterns, generate movie scripts or books and enjoy
     */
    var sentences:Array<String> = [String]()
    var wordToList:[String:UniqueItemSizeLimitedPriorityQueue]=[:]
    private let regexUtil:RegexUtil = RegexUtil()
    func generateJoke()->String{
        let result:String = sentences.randomElement() ?? ""
        return clearRecursion(result: result)
    }
    private func clearRecursion(result:String)->String{
        var result2:String = ""
        var params:Array<String> = [String]()
        params = regexUtil.extractAllRegexResults(regex: "(\\w+)(?= #)", text: result)
        for strI in params{
            let temp:UniqueItemSizeLimitedPriorityQueue = wordToList[strI]!
            let s1:String = temp.getRndItem()
            result2 = result.replacingOccurrences(of: strI + " #", with: s1)
        }
        if !result2.contains("#"){return result2}
        return clearRecursion(result: result2)
    }
    func addParam(category:String, value1:String){
        wordToList[category]?.input(in1: value1)
    }
    func addParam(kv:AXKeyValuePair){
        wordToList[kv.getKey()]?.input(in1: kv.getValue())
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
    func getOldAnTask()->String{
        // task graveyard (tasks you've tackled already)
        return backup.getRndItem()
    }
    func clearAllTasks(){
        q1.clearData()
        backup.clearData()
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
    func reward(cost:Int)->Bool{
        // game grind points used for rewards
        // consumables, items or upgrades this makes games fun
        if cost > counter{return false}
        counter -= cost
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
    private var trgTolerance:TrgTolerance = TrgTolerance(maxRepeats: 3)
    func engageCommand(ear:String) -> Bool {
        if commands.contains(str: ear){
            trgTolerance.reset()
            return true
        }
        if !trgTolerance.trigger(){
            return false
        }
        return contextCommands.contains(str: ear)
    }
    func setInputWait(thinkCycles:Int){
        trgTolerance.setMaxRepeats(maxRepeats: thinkCycles)
    }
    func disable(){
        // context commands are disabled till next engagement with a command
        trgTolerance.disable()
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
}
