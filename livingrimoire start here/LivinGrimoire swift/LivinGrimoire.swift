//
//  LivinGrimoire.swift
//  LivinGrimoireSwiftV1
//
//  Created by moti barski on 21/06/2022.
//

import Foundation
open class AbsDictionaryDB{
    func save(key:String, value: String){
        // save to DB (override me)
    }
    func load(key:String)->String{
        // override me
        return "null"
    }
}
enum enumFail{
    case fail, requip, dequip, cloudian, ok
}
open class Mutatable{
    func action(ear: String, skin: String, eye: String) -> String{
        return ""
    }
    func failure(input: String) -> enumFail{
        // Failure type only mutatable may use enumFail.fail
        return enumFail.ok
    }
    func completed() -> Bool{
        //Has finished ?
        return false
    }
    func clone() -> Mutatable{
        return Mutatable()
    }
    
    func getMutationLimit() -> Int{
        return 0
    }
    
    func myName() -> String{
        // Returns the class name
        return String(describing: type(of: self))
                      }
    
    func mutation() -> Mutatable{
        return Mutatable()
    }
}
class MemoryMutatable:Mutatable{
    /*
         * an adaptor pattern to the Mutatable (algorithm part)
         * the object will load the last mutated state which is the last and optimized
         * mutation used.
         * upon mutation the new last mutation is saved so it can be loaded for the next time
         * mutations happen in the DExplorer class and triggered when the Mutatbles' failure method
         * returns enumFail.failure
         * you can code said enumFail.failure to return under chosen conditions in the action method of
         * the MemoryMutatable object. (sub class of this class)
         */
    private(set) var kokoro:Kokoro
    private(set) var aPart:Mutatable
    init(kokoro:Kokoro, aPart:Mutatable) {
        self.kokoro = kokoro
        // load the last saved mutatable
        self.aPart = kokoro.grimoireMemento.load(obj: aPart)
    }
    override func action(ear: String, skin: String, eye: String) -> String {
        kokoro.in1(chi: self)
        let result:String = aPart.action(ear: ear, skin: skin, eye: eye)
        kokoro.out(isCompleted: completed(), failure: failure(input: ""))
        return result
    }
    override func failure(input: String) -> enumFail {
        return aPart.failure(input: input)
    }
    override func completed() -> Bool {
        return aPart.completed()
    }
    override func clone() -> Mutatable {
        return MemoryMutatable(kokoro: kokoro, aPart: aPart.clone())
    }
    override func getMutationLimit() -> Int {
        return aPart.getMutationLimit()
    }
    override func mutation() -> Mutatable {
        // upon mutation the last mutation is saved
        let mutant:Mutatable = aPart
        let tempAP = mutant.mutation()
        kokoro.grimoireMemento.reqquipMutation(mutationAPName: tempAP.myName())
        return MemoryMutatable(kokoro: kokoro, aPart: tempAP)
    }
    override func myName() -> String {
        return aPart.myName()
    }
}
class T1:Mutatable{
    var isCompleted:Bool = false
    override func mutation() -> Mutatable {
        print("T1 mutating into T2")
        return T2() // modify to t2
    }
    override func clone() -> Mutatable {
        print("T1 cloning another T1 object")
        return T1()
    }
    override func action(ear: String, skin: String, eye: String) -> String {
        self.isCompleted = true
        return "I am a T1 object"
    }
    override func completed() -> Bool {
        return self.isCompleted
    }
    override func getMutationLimit() -> Int {
        return 1
    }
}
class T2:Mutatable{
    var isCompleted:Bool = false
    override func mutation() -> Mutatable {
        print("T2 mutating into T1")
        return T1() // modify to t2
    }
    override func clone() -> Mutatable {
        print("T2 cloning another T2 object")
        return T2()
    }
    override func action(ear: String, skin: String, eye: String) -> String {
        self.isCompleted = true
        return "I am a T2 object"
    }
    override func completed() -> Bool {
        return self.isCompleted
    }
    override func getMutationLimit() -> Int {
        return 1
    }
}
/*
 it speaks something x times
 a most basic skill.
 also fun to make the chobit say what you want */
class APsay:Mutatable{
    var at:Int=10
    var param:String="hmm"
    convenience init(repetitions:Int, param:String) {
        self.init()
        if repetitions<at {self.at=repetitions}
        self.param=param
    }
    override func action(ear: String, skin: String, eye: String) -> String {
        var axnStr=""
        if self.at>0{
            if ear.lowercased() != self.param{
                axnStr=self.param
                self.at -= 1
            }
        }
        return axnStr
    }
    override func completed() -> Bool {
        return self.at < 1
    }
    override func clone() -> Mutatable {
        return APsay(repetitions: self.at, param: self.param)
    }
}
class DeepCopier{
    func copyList(original: Array<String>)->Array<String>{
        var deepCopy: Array<String> = [String]()
        for item in original{
            deepCopy.append(item)
        }
        return deepCopy
    }
}
class APVerbatim:Mutatable{
    // this algorithm part says each past param verbatim
    var at:Int=0
    var sentences: Array<String> = [String]()
    init(_ sentences:String...){
        for item in sentences{
            self.sentences.append(item)
        }
        if sentences.isEmpty {
            self.at = 30
        }
    }
    init(sentences: Array<String>){
        for item in sentences{
            self.sentences.append(item)
        }
        if sentences.isEmpty {
            self.at = 30
        }
    }
    override func action(ear: String, skin: String, eye: String) -> String {
        var axnStr = ""
        if self.at < self.sentences.count{
            axnStr=self.sentences[at]
            self.at += 1
        }
        return axnStr
    }
    override func completed() -> Bool {
        return self.at == self.sentences.count
    }
    override func clone() -> Mutatable {
        let deepCoper = DeepCopier()
        return APVerbatim(sentences: deepCoper.copyList(original: sentences))
    }
}
enum enumTimes: Int {
    case date
    case day
    case year
    case hour
    case minutes
    case seconds
}



class PlayGround {
    
    var right_now = Date()
    let calendar = Calendar.current
    var dateComponent = DateComponents()
    
    var week_days: [Int:String]
    var dayOfMonth : [Int: String]
    init() {
        self.week_days = [1: "sunday",
                          2: "monday",
                          3: "tuesday",
                          4: "wednesday",
                          5: "thursday",
                          6: "friday",
                          7: "saturday"
                          ]
        self.dayOfMonth = [1: "first_of", 2: "second_of", 3: "third_of", 4: "fourth_of", 5: "fifth_of", 6: "sixth_of",
                           7: "seventh_of",
                           8: "eighth_of", 9: "nineth_of", 10: "tenth_of", 11: "eleventh_of", 12: "twelveth_of",
                           13: "thirteenth_of",
                           14: "fourteenth_of", 15: "fifteenth_of", 16: "sixteenth_of", 17: "seventeenth_of",
                           18: "eighteenth_of",
                           19: "nineteenth_of", 20: "twentyth_of", 21: "twentyfirst_of", 22: "twentysecond_of",
                           23: "twentythird_of",
                           24: "twentyfourth_of", 25: "twentyfifth_of", 26: "twentysixth_of", 27: "twentyseventh_of",
                           28: "twentyeighth_of",
                           29: "twentynineth_of", 30: "thirtyth_of", 31: "thirtyfirst_of"]
    }

    func getCurrentTimeStamp() -> String {
       // '''This method returns the current time (hh:mm)'''
  
        return String(calendar.component(.hour, from: right_now)) + ":" + String(calendar.component(.minute, from: right_now))
    }

    func getMonthAsInt() -> Int {
       // '''This method returns the current month (MM)'''
       
        return calendar.component(.month, from: right_now)
    }

    func getDayOfTheMonthAsInt() -> Int {
       // '''This method returns the current day (dd)'''
       
        return calendar.component(.day, from: right_now)
    }

    func getYearAsInt() -> Int {
      //  '''This method returns the current year (yyyy)'''
       
        return calendar.component(.year, from: right_now)
    }

    func getDayAsInt() -> Int {
       // '''This method returns the current day of the week (1, 2, ... 7)'''

        return calendar.component(.weekday, from: right_now)
    }

    func getMinutes() -> String {
       // '''This method returns the current minutes (mm)'''
       
        return right_now.minute() ?? ""
    }

    func getSeconds() -> String {
      //  '''This method returns the current seconds (ss)'''
        
        return String(calendar.component(.second, from: right_now))
    }

    func getDayOfDWeek() -> String {
      //  '''This method returns the current day of the week as a word (monday, ...)'''
    
        return right_now.dayOfWeek()!
    }

    func translateMonthDay() -> String {
       // '''This method returns the current day of the month as a word (first_of, ...)'''
        let currentDay_number = calendar.component(.day, from: right_now)
        let currentDay_string = dayOfMonth[currentDay_number] ?? "?"
        return currentDay_string
        
    }

    func getSpecificTime(time_variable: enumTimes) -> String {
//        '''This method returns the current specific date in words (eleventh_of June 2021, ...)'''

        
       let enum_temp = time_variable
        switch enum_temp {
        case .date:
            return translateMonthDay() + " " + (right_now.month() ?? "/") + " " + (right_now.year() ?? "/")
        case .hour:
            return right_now.hour() ?? "/"
        case .minutes:
           return right_now.minute() ?? "/"
        case .seconds:
           return right_now.second() ?? "/"
        case .year:
            return right_now.year() ?? "/"
        default:
            break
        }
        return ""
    }

    func getSecondsAsInt() -> Int {
       // '''This method returns the current seconds'''
       
        return calendar.component(.second, from: right_now)
    }

    func getMinutesAsInt() -> Int {
       // '''This method returns the current minutes'''
      
        return calendar.component(.minute, from: right_now)
    }

    func getHoursAsInt() -> Int {
      //  '''This method returns the current hour'''
        
        return calendar.component(.hour, from: right_now)
    }

    func getFutureInXMin(extra_minutes: Int) -> String {
          //  '''This method returns the date in x minutes'''
          
        if extra_minutes > 1440 {return "hmm"}
        let nowSum = getHoursAsInt()*60 + getMinutesAsInt()
        var dif = nowSum + extra_minutes
        if dif > 1440 {dif -= 1440}
        let minutes = dif % 60
        if minutes<10 {return "\(dif/60):0\(minutes)"}
        return "\(dif/60):\(minutes)"
        }

    func getPastInXMin(less_minutes: Int) -> String {
        if less_minutes > 1440 {return "hmm"}
        let nowSum = getHoursAsInt()*60 + getMinutesAsInt()
        var dif = nowSum - less_minutes
        if dif < 0 {dif = 1440 - dif}
        let minutes = dif % 60
        if minutes<10 {return "\(dif/60):0\(minutes)"}
        return "\(dif/60):\(minutes)"
    }
       
    

    func getFutureHour(startHour: Int, addedHours: Int) -> Int {
       // '''This method returns the hour in x hours from the starting hour'''
        return (startHour + addedHours) % 24
   
    }

    func getFutureFromXInYMin(to_add: Int, start: String) -> String {
       // '''This method returns the time (hh:mm) in x minutes the starting time (hh:mm)'''
        
        let values = start.components(separatedBy: ":")
        let times_to_add = floor(Double(((Int(values[1]) ?? 0) + to_add) / 60))
        let new_minutes = ((Int(values[1]) ?? 0) + to_add) % 60
        let newTimeHours = ((Int(values[0]) ?? 0) + Int(times_to_add)) % 24
        let new_time = String(newTimeHours) + ":" + String(new_minutes)
       return new_time
    }

    func timeInXMinutes(x: Int) -> String {
       // '''This method returns the time (hh:mm) in x minutes'''
        
        // reset datecomponents
       dateComponent = DateComponents()
        dateComponent.minute = x
        let final_time = Calendar.current.date(byAdding: dateComponent, to: right_now)
        return String(calendar.component(.hour, from: final_time ?? Date())) + ":" + String(calendar.component(.minute, from: final_time ?? Date()))
    
    }
    func isDayTime() -> Bool {
      //  '''This method returns true if it's daytime (6-18)'''
    return 5 < calendar.component(.hour, from: right_now)  &&  calendar.component(.hour, from: right_now) < 19
    }

    func smallToBig(_ a:Int...) -> Bool {
        for i in 0..<a.count {
    
            guard i + 1 < a.count else {
                return true
                
            }
            if a[i] > a[i + 1]  {
                return false
            }
      
        }
        return true
    }
    

    func partOfDay() -> String {
       // '''This method returns which part of the day it is (morning, ...)'''
       let hour: Int = self.getHoursAsInt()
        if self.smallToBig(5, hour, 12) {
                  return "morning"
        } else if self.smallToBig(11, hour, 17) {
                  return "afternoon"
        } else if self.smallToBig(16, hour, 21) {
                  return "evening"
        } else { return "night"
                }

    }

    func convertToDay(number: Int) -> String {
       // '''This method converts the week number to the weekday name'''
     
        return week_days[number] ?? ""
    }

    func isNight() -> Bool {
      //  '''This method returns true if it's night (21-5)'''
       let hour: Int = self.getHoursAsInt()
        return hour > 20 || hour < 6
    }

    func getTomorrow() -> String {
       // '''This method returns tomorrow'''
        
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "EEEE"
        return dateFormatter.string(from: nowPlusOneDay()).capitalized
       
        
    }

    func getYesterday() -> String {
       // '''This method returns yesterday'''
   
       let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "EEEE"
        print(calendar.component(.weekday, from: nowPlusOneDay()))
        return dateFormatter.string(from: nowMinusOneDay()).capitalized
    }

    func getGMT() -> Date {
       // '''This method returns the local GMT'''
      
        return right_now.localToGMT()
        
    }

    func getLocal() -> String {
       // '''This method returns the local time zone'''
        return TimeZone.current.identifier 
    }
}
                      
extension Date {
    func second() -> String? {
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "ss"
        return dateFormatter.string(from: self).capitalized
    }
    func minute() -> String? {
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "mm"
        return dateFormatter.string(from: self).capitalized
    }
    func hour() -> String? {
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "HH"
        return dateFormatter.string(from: self).capitalized
    }
    func dayOfWeek() -> String? {
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "EEEE"
        return dateFormatter.string(from: self).capitalized
        // or use capitalized(with: locale) if you want
    }
    func month() -> String? {
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "MMMM"
        return dateFormatter.string(from: self).capitalized
    }
    func year() -> String? {
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "YYYY"
        return dateFormatter.string(from: self).capitalized
    }
    func localToGMT() -> Date {
        let date = Date()
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "EEE, dd MMM yyyy HH:mm:ss z"
        dateFormatter.locale = .current
        dateFormatter.timeZone = TimeZone(abbreviation: "GMT")
       let strDate = dateFormatter.string(from: date)
        return dateFormatter.date(from: strDate) ?? Date()
    }
    
}

extension PlayGround {
    func nowPlusOneDay() -> Date {
        // reset datecomponents
        dateComponent = DateComponents()
      dateComponent.day = 1
        return Calendar.current.date(byAdding: dateComponent, to: right_now) ?? Date()
    }
    func nowMinusOneDay() -> Date {
        // reset datecomponents
        dateComponent = DateComponents()
      dateComponent.day = -1
        return Calendar.current.date(byAdding: dateComponent, to: right_now) ?? Date()
    }
    
}
// grimoirememento
class GrimoireMemento{
    var rootToAPNumDic:[String:String] = [:] // APname : APName3 (final current mutation)
    var APNumToObjDic:[String:Mutatable] = [:] // APName3 : actual object
    private(set) var absDictionaryDB:AbsDictionaryDB
    init(absDictionaryDB:AbsDictionaryDB){
        self.absDictionaryDB = absDictionaryDB
    }
    func load(obj:Mutatable)->Mutatable{
        /*
         load final mutation from memory of obj
         */
        let objName:String = getSimpleClassName(obj: obj) // APName3
        let objRoot = filterOutDigits(str1: objName) // APName
        // if not in active DB try adding from external DB
        if !rootToAPNumDic.keys.contains(objRoot){
            let temp = self.absDictionaryDB.load(key: objRoot)
            if temp != "null"{
                self.rootToAPNumDic[objRoot]=temp
            }
        }
        if !rootToAPNumDic.keys.contains(objRoot){
            rootToAPNumDic[objRoot]=objName
            return obj
        }
        if rootToAPNumDic[objRoot]==objName{return obj}
        else{
            let APNum = rootToAPNumDic[objRoot]
            if APNumToObjDic.keys.contains(APNum!){return APNumToObjDic[APNum!]!.clone()}
            else{
                loadMutations(obj: obj, objName: objName, objRoot: objRoot)
                return APNumToObjDic[APNum!]!.clone()
            }
        }
    }
    func reqquipMutation(mutationAPName: String){
        // save mutation
        let rootName:String=filterOutDigits(str1: mutationAPName)
        self.rootToAPNumDic[rootName] = mutationAPName
        self.absDictionaryDB.save(key: rootName, value: mutationAPName)
    }
    func filterOutDigits(str1:String)->String{
        return str1.trimmingCharacters(in: CharacterSet(charactersIn: "0123456789"))
    }
    func getSimpleClassName(obj: Mutatable)->String{
        return String(describing: type(of: obj))
    }
    func loadMutations(obj: Mutatable, objName: String, objRoot: String){
        /*
         make sure all the AP mutation sets of obj are present
         self assumes the last mutation mutates into the prime mutation
         */
        var tempObj = obj.clone()
        var mutant:Mutatable = tempObj
        let end:String = objName
        repeat{
            let objectName:String = getSimpleClassName(obj: tempObj)
            APNumToObjDic[objectName] = tempObj.clone()
            mutant = tempObj
            tempObj = mutant.mutation()
        }
        while(end != getSimpleClassName(obj: tempObj))
    }
    func simpleLoad(key:String)->String{
        return self.absDictionaryDB.load(key: key)
    }
    func simpleSave(key:String,value:String){
        if(key.starts(with: "AP") || key.isEmpty || value.isEmpty){return}
        self.absDictionaryDB.save(key: key, value: value)
    }
}
// a step by step plan to achieve a goal
class Algorithm{
    private var goal:String = ""
    private var representation:String = ""
    var algParts: Array<Mutatable> = [Mutatable]()
    init(goal:String, representation:String, algParts: Array<Mutatable>) {
        self.goal = goal.isEmpty ? "unknown" : goal
        self.representation = representation.isEmpty ? "unknown" : representation
        self.algParts = algParts
    }
    func getGoal()->String{
        return self.goal
    }
    func getRepresentation()->String{
        return self.representation
    }
    func getSize() -> Int {
        return algParts.count
    }
    func clone()->Algorithm{
        var deepCopy: Array<Mutatable> = [Mutatable]()
        for item in self.algParts{
            deepCopy.append(item)
        }
        return Algorithm(goal: goal, representation: representation, algParts: deepCopy)
    }
}
class CldBool{
    private var modeActive:Bool = false
    func getModeActive()->Bool{
        return self.modeActive
    }
    func setModeActive(modeActive:Bool){
        self.modeActive = modeActive
    }
}
class APCldVerbatim:APVerbatim{
    private var cldBool:CldBool = CldBool()
    init(sentences: Array<String>, cldBool:CldBool) {
        super.init(sentences: sentences)
        self.cldBool = cldBool
    }
    override func action(ear: String, skin: String, eye: String) -> String {
        var axnStr = ""
        if self.at < self.sentences.count{
            axnStr=self.sentences[at]
            self.at += 1
        }
        cldBool.setModeActive(modeActive: !self.completed() )
        return axnStr
    }
    override func clone() -> Mutatable {
        let deepCoper = DeepCopier()
        return APCldVerbatim(sentences: deepCoper.copyList(original: sentences), cldBool: self.cldBool)
    }
}
class Kokoro{
    private var emot:String = ""
    var pain:[String:Int] = [:]
    private(set) var grimoireMemento:GrimoireMemento
    var toHeart:[String:String] = [:]
    var fromHeart:[String:String] = [:]
    var standBy:Bool = false
    init(absDictionaryDB: AbsDictionaryDB) {
        self.grimoireMemento = GrimoireMemento(absDictionaryDB: absDictionaryDB)
    }
    func getPain(biJuuName: String) -> Int {
        return pain[biJuuName] ?? 0
    }
    func in1(chi:MemoryMutatable){}
    func out(isCompleted:Bool, failure:enumFail){}
}
// used to transport algorithms to other classes
class Neuron{
    var algParts: Array<Algorithm> = [Algorithm]()
    var negativeAlgParts: Array<Algorithm> = [Algorithm]()
    func empty(){
        self.algParts.removeAll()
        self.negativeAlgParts.removeAll()
    }
}
class DiSkillUtils{
    func onePartAlgorithm(algMarker:String, algPart:Mutatable)->Algorithm{
        // returns an algorithm composed of 1 algorithm part
        let representasion:String = "rep_\(algMarker)"
        var algParts1: Array<Mutatable> = [Mutatable]()
        algParts1.append(algPart)
        let result:Algorithm = Algorithm(goal: algMarker, representation: representasion, algParts: algParts1)
        return result
    }
    func algBuilder(algMarker:String, algParts:Mutatable...)->Algorithm{
        // builds an algorithm out of alg parts
        let representasion:String = "rep_\(algMarker)"
        var algParts1: Array<Mutatable> = [Mutatable]()
        for algPart in algParts{
            algParts1.append(algPart)
        }
        let result:Algorithm = Algorithm(goal: algMarker, representation: representasion, algParts: algParts1)
        return result
    }
    func simpleVerbatimAlgorithm(algMarker:String, sayThis:String...)->Algorithm{
        // builds an algorithm of sentences to say
        return onePartAlgorithm(algMarker: algMarker, algPart: APVerbatim(sentences: sayThis))
    }
    func simpleCloudianVerbatimAlgorithm(cldBool:CldBool, algMarker:String, sayThis:String...)->Algorithm{
        // builds an algorithm of sentences to say
        // the cloudian reference can prevent sending algorithms while this one is running (prevention done at the skill input method)
        return onePartAlgorithm(algMarker: algMarker, algPart: APCldVerbatim(sentences: sayThis, cldBool: cldBool))
    }
    func onePartAlgorithm(algRepresantation:String, algMarker:String, algPart:Mutatable)->Algorithm{
        // returns an algorithm composed of 1 algorithm part
        let representasion:String = algRepresantation
        var algParts1: Array<Mutatable> = [Mutatable]()
        algParts1.append(algPart)
        let result:Algorithm = Algorithm(goal: algMarker, representation: representasion, algParts: algParts1)
        return result
    }
    func algBuilder(algRepresantation:String, algMarker:String, algParts:Mutatable...)->Algorithm{
        // builds an algorithm out of alg parts
        let representasion:String = algRepresantation
        var algParts1: Array<Mutatable> = [Mutatable]()
        for algPart in algParts{
            algParts1.append(algPart)
        }
        let result:Algorithm = Algorithm(goal: algMarker, representation: representasion, algParts: algParts1)
        return result
    }
    func simpleVerbatimAlgorithm(algRepresantation:String,algMarker:String, sayThis:String...)->Algorithm{
        // builds an algorithm of sentences to say
        return onePartAlgorithm(algRepresantation:algRepresantation, algMarker: algMarker, algPart: APVerbatim(sentences: sayThis))
    }
    func simpleCloudianVerbatimAlgorithm(algRepresantation:String, cldBool:CldBool, algMarker:String, sayThis:String...)->Algorithm{
        // builds an algorithm of sentences to say
        // the cloudian reference can prevent sending algorithms while this one is running (prevention done at the skill input method)
        return onePartAlgorithm(algRepresantation: algRepresantation,algMarker: algMarker, algPart: APCldVerbatim(sentences: sayThis, cldBool: cldBool))
    }
    func stringContainsListElement(str1:String, items:Array<String>)->String{
        // returns the 1st match between words in a string and values in a list.
        for item in items{
            if str1.contains(item){return item}
        }
        return ""
    }
}
open class DiSkillV2{
    private(set) var kokoro:Kokoro = Kokoro(absDictionaryDB: AbsDictionaryDB())
    let diSkillUtills:DiSkillUtils = DiSkillUtils()
    var outAlg:Algorithm? = nil
    init() {}
    func input(ear:String, skin:String, eye:String){
    }
    func output(noiron:Neuron){
        if let notNilAlg = self.outAlg{
            noiron.algParts.append(notNilAlg)
            self.outAlg = nil
        }
    }
    func setKokoro(kokoro:Kokoro){
        // use this for telepathic communication between different chobits objects
        self.kokoro = kokoro
    }
}
open class DiSkillV3:DiSkillV2{
    // algorithm will be loaded with priority to run
    // use for fight or flight type skills
    override func output(noiron:Neuron){
        if let notNilAlg = self.outAlg{
            noiron.negativeAlgParts.append(notNilAlg)
            self.outAlg = nil
        }
    }
}
class DiHelloWorld:DiSkillV2{
    // hello world skill for testing purposes
    override func input(ear: String, skin: String, eye: String) {
        switch (ear)  {
          case "hello":
            self.outAlg = diSkillUtills.simpleVerbatimAlgorithm(algMarker: "test2", sayThis: "hello world")
          case "incantation 0":
            // cancel running algorithm entirely at any alg part point
            self.outAlg = diSkillUtills.simpleVerbatimAlgorithm(algRepresantation: "test 0", algMarker: "incantation0", sayThis: "fly","bless of magic caster","infinity wall", "magic ward holy","life essence")
        default:
            return
        }
    }
}
class LGPointDouble{
    var x:Double = 0
    var y:Double = 0
    init() {
    }
    init(x:Double,y:Double) {
        self.x = x
        self.y = y
    }
    func shift(x:Double,y:Double){
        self.x += x;self.y += y
    }
    func toString()->String{
        return "coordinate(\(self.x),\(self.y))"
    }
    func distance(a:LGPointDouble,b:LGPointDouble)->Double{
        return sqrt(pow((a.x - b.x), 2) + pow((a.y - b.y), 2))
    }
}
class LGPointInt{
    var x:Int = 0
    var y:Int = 0
    init() {
    }
    init(x:Int,y:Int) {
        self.x = x
        self.y = y
    }
    func shift(x:Int,y:Int){
        self.x += x;self.y += y
    }
    func toString()->String{
        return "coordinate(\(self.x),\(self.y))"
    }
    func distance(a:LGPointInt,b:LGPointInt)->Double{
        return sqrt(pow(Double((a.x - b.x)), 2) + pow(Double((a.y - b.y)), 2))
    }
}
enum enumRegexGrimoire{
    case email, timeStamp, integer, double, repeatedWord, phone, trackingID, IPV4, domain, number,
    secondlessTimeStamp, date, fullDate
}
class RegexUtil{
    var regexDictionary:[enumRegexGrimoire:String] = [:]
    init() {
        regexDictionary[enumRegexGrimoire.email] = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}"
        regexDictionary[enumRegexGrimoire.timeStamp] = "[0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}"
        regexDictionary[enumRegexGrimoire.secondlessTimeStamp] = "[0-9]{1,2}:[0-9]{1,2}"
        regexDictionary[enumRegexGrimoire.fullDate] = "[0-9]{1,4}/[0-9]{1,2}/[0-9]{1,2} [0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}"
        regexDictionary[enumRegexGrimoire.date] = "[0-9]{1,4}/[0-9]{1,2}/[0-9]{1,2}"
        regexDictionary[enumRegexGrimoire.double] = "[-+]?[0-9]*[.,][0-9]*"
        regexDictionary[enumRegexGrimoire.integer] = "[-+]?[0-9]{1,13}"
        regexDictionary[enumRegexGrimoire.repeatedWord] = "\\b([\\w\\s']+) \\1\\b"
        regexDictionary[enumRegexGrimoire.phone] = "[0]\\d{9}"
        regexDictionary[enumRegexGrimoire.trackingID] = "[A-Z]{2}[0-9]{9}[A-Z]{2}"
        regexDictionary[enumRegexGrimoire.IPV4] = "([0-9].){4}[0-9]*"
        regexDictionary[enumRegexGrimoire.domain] = "[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}"
        regexDictionary[enumRegexGrimoire.number] = "\\d+(\\.\\d+)?"
    }
    func extractAllRegexResults(regex:String, text: String) -> [String] {
        var results = [String]()

        let emailRegex = regex
        let nsText = text as NSString
        do {
            let regExp = try NSRegularExpression(pattern: emailRegex, options: .caseInsensitive)
            let range = NSMakeRange(0, text.count)
            let matches = regExp.matches(in: text, options: .reportProgress, range: range)

            for match in matches {
                let matchRange = match.range
                results.append(nsText.substring(with: matchRange))
            }
        } catch (_) {
    
        }
        if results.isEmpty {
            results.append("")
        }
        return results
    }
    func extractAllRegexResults(regex:enumRegexGrimoire, text: String) -> [String] {
        return extractAllRegexResults(regex: regexDictionary[regex]!, text: text)
    }
    func regexChecker(theRegex:String, str2Check:String)->String{
        // the regex : regex pattern str2check = the input string in which to search for the regex pattern
        var results = [String]()

        let emailRegex = theRegex
        let nsText = str2Check as NSString
        do {
            let regExp = try NSRegularExpression(pattern: emailRegex, options: .caseInsensitive)
            let range = NSMakeRange(0, str2Check.count)
            let matches = regExp.matches(in: str2Check, options: .reportProgress, range: range)

            for match in matches {
                let matchRange = match.range
                results.append(nsText.substring(with: matchRange))
            }
        } catch (_) {
            return ""
        }
        if results.isEmpty {return ""}
        return results[0]
    }
    func regexChecker(theRegex:enumRegexGrimoire, str2Check:String)->String{
        return regexChecker(theRegex: regexDictionary[theRegex]!, str2Check: str2Check)
    }
    // return all regex results
    func extractEmailAddrIn(text: String) -> [String] {
        return extractAllRegexResults(regex: "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}", text: text)
    }
    func pointRegex(text: String) -> LGPointDouble {
        let result = extractAllRegexResults(regex: "[-+]?[0-9]{1,13}(\\.[0-9]*)?", text: text)
        if result.count == 2 {return LGPointDouble(x: Double(result[0]) ?? 0, y: Double(result[1]) ?? 0)}
        return LGPointDouble()
    }
    func intPointRegex(text: String) -> LGPointInt {
        let result = extractAllRegexResults(regex: "[-+]?[0-9]{1,13}", text: text)
        if result.count == 2 {return LGPointInt(x: Int(result[0]) ?? 0, y: Int(result[1]) ?? 0)}
        return LGPointInt()
    }
    func timeStampRegexes(text: String) -> [String] {
        return extractAllRegexResults(regex: "[0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}", text: text)
    }
    func numberRegexes(text: String) -> [String] {
        return extractAllRegexResults(regex: "[-+]?[0-9]*[.,][0-9]*", text: text)
    }
    func intRegexes(text: String) -> [String] {
        return extractAllRegexResults(regex: "[-+]?[0-9]{1,13}", text: text)
    }
    func doubleRegexes(text: String) -> [String] {
        return extractAllRegexResults(regex: "[-+]?[0-9]{1,13}(\\.[0-9]*)?", text: text)
    }
    func repeatedWords(text: String) -> [String] {
        return extractAllRegexResults(regex: "\\b([\\w\\s']+) \\1\\b", text: text)
    }
    // return 1st regex result
    func numberRegex(str2Check:String)->String{
        return regexChecker(theRegex: "[-+]?[0-9]*[.,][0-9]*", str2Check: str2Check)
    }
    func timeStampRegex(str2Check:String)->String{
        return regexChecker(theRegex: "[0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}", str2Check: str2Check)
    }
    func intRegex(str2Check:String)->String{
        return regexChecker(theRegex: "[-+]?[0-9]{1,13}", str2Check: str2Check)
    }
    func intRegexAsInt(str2Check:String)->Int{
        return Int(regexChecker(theRegex: "[-+]?[0-9]{1,13}", str2Check: str2Check)) ?? 0
    }
    func doubleRegex(str2Check:String)->String{
        return regexChecker(theRegex: "[-+]?[0-9]{1,13}(\\.[0-9]*)?", str2Check: str2Check)
    }
    func doubleRegexAsDouble(str2Check:String)->Double{
        return Double(regexChecker(theRegex: "[-+]?[0-9]{1,13}(\\.[0-9]*)?", str2Check: str2Check)) ?? 0
    }
    func phoneRegex(str2Check:String)->String{
        return regexChecker(theRegex: "[0]\\d{2}\\d{4}\\d{3}$", str2Check: str2Check)
    }
    func phoneRegex2(str2Check:String)->String{
        return regexChecker(theRegex: "[0]\\d{9}", str2Check: str2Check)
    }
    func firtNameLastName(str2Check:String)->String{
        // Fukurou, Slime
        return regexChecker(theRegex: "([\\w\\-]+)\\s*,\\s*(\\w+)\\s*", str2Check: str2Check)
    }
    func trackingID(str2Check:String)->String{
        //
        return regexChecker(theRegex: "[A-Z]{2}[0-9]{9}[A-Z]{2}", str2Check: str2Check)
    }
    func endsWith(endingOfWord:String, str2Check:String)->String{
        //
        return regexChecker(theRegex: "[a-z]*(\(endingOfWord))", str2Check: str2Check)
    }
    func startsWith(startingingOfWord:String, str2Check:String)->String{
        //
        return regexChecker(theRegex: "(\(startingingOfWord))[a-z]*", str2Check: str2Check)
    }
    func ipV4Regex(str2Check:String)->String{
        //
        return regexChecker(theRegex: "([0-9].){4}[0-9]*", str2Check: str2Check)
    }
    func domainRegex(str2Check:String)->String{
        //
        return regexChecker(theRegex: "[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}", str2Check: str2Check)
    }
    func urlRegex(str2Check:String)->String{
        //
        let tempStr = regexChecker(theRegex: "[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}", str2Check: str2Check)
        let wordsArr = str2Check.components(separatedBy: " ")
        if !wordsArr.isEmpty{
            for i in 0..<wordsArr.count {
                if wordsArr[i].contains(tempStr){return wordsArr[i]}
            }
        }
        return ""
    }
    func urlsRegex(str2Check:String)->[String]{
        //
        var results = [String]()
        let wordsArr = str2Check.components(separatedBy: " ")
        if !wordsArr.isEmpty{
            for i in 0..<wordsArr.count {
                if !regexChecker(theRegex: "[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}", str2Check: wordsArr[i]).isEmpty{results.append(wordsArr[i])}
            }
        }
        return results
    }
    func integersRegex(str2Check:String)->[Int]{
        //
        var results = [Int]()
        let wordsArr = str2Check.components(separatedBy: " ")
        if !wordsArr.isEmpty{
            for i in 0..<wordsArr.count {
                if !regexChecker(theRegex: regexDictionary[enumRegexGrimoire.integer]!, str2Check: wordsArr[i]).isEmpty{
                    let result = Int(wordsArr[i]) ?? 0
                    if result != 0{results.append(Int(wordsArr[i]) ?? 0)}
                    }
            }
        }
        return results
    }
    func doublesRegex(str2Check:String)->[Double]{
        //
        var results = [Double]()
        let wordsArr = str2Check.components(separatedBy: " ")
        if !wordsArr.isEmpty{
            for i in 0..<wordsArr.count {
                if !regexChecker(theRegex: regexDictionary[enumRegexGrimoire.double]!, str2Check: wordsArr[i]).isEmpty{results.append(Double(wordsArr[i])!)}
            }
        }
        return results
    }
    func numbersRegex(str2Check:String)->[Double]{
        //
        var results = [Double]()
        let wordsArr = str2Check.components(separatedBy: " ")
        if !wordsArr.isEmpty{
            for i in 0..<wordsArr.count {
                if !regexChecker(theRegex: regexDictionary[enumRegexGrimoire.number]!, str2Check: wordsArr[i]).isEmpty{results.append(Double(wordsArr[i])!)}
            }
        }
        return results
    }
    func afterWord(word:String, str2Check:String)->String{
        return regexChecker(theRegex: "(?<=\(word))(.*)", str2Check: str2Check).trimmingCharacters(in: .whitespacesAndNewlines)
    }
    func beforeWord(word:String, str2Check:String)->String{
        return regexChecker(theRegex: "(.*?)\(word)", str2Check: str2Check).replacingOccurrences(of: word, with: "")
    }
    func betweenWords(word1:String, word2:String, str2Check:String)->String{
        let result = afterWord(word: word1, str2Check: str2Check)
        return beforeWord(word: word2, str2Check: result)
    }
    func repeatedWord(str2Check:String)->String{
        return regexChecker(theRegex: "\\b([\\w\\s']+) \\1\\b", str2Check: str2Check)
    }
    func firstWord(str2Check:String)->String{
        let wordsArr = str2Check.components(separatedBy: " ")
        if !wordsArr.isEmpty{
            return wordsArr[0]
        }
        return ""
    }
    func lastWord(str2Check:String)->String{
        let wordsArr = str2Check.components(separatedBy: " ")
        if !wordsArr.isEmpty{
            return wordsArr[wordsArr.count - 1]
        }
        return ""
    }
    func uniqueWord(str2Check:String)->String{
        if str2Check.isEmpty{return ""}
        let wordsArr = str2Check.components(separatedBy: " ")
        var p:String = wordsArr[0]
        var result = p
        for i in 1..<wordsArr.count {
            if(p != wordsArr[i]){
                result = "\(result) \(wordsArr[i])"
                p = wordsArr[i]
            }
        }
        return result
    }
    // other functions
    func stripAwayNumbers(str1:String)->String{
        return str1.trimmingCharacters(in: CharacterSet(charactersIn: "0123456789"))
    }
    func extractNumber(str1:String)->String{
        return str1.trimmingCharacters(in: CharacterSet(charactersIn: "0123456789.").inverted)
    }
    func removeSpaces(str1:String)->String{
        return String(str1.unicodeScalars.filter(CharacterSet.whitespaces.inverted.contains))
    }
}
class TimeGate{
    //time boolean gate
    // gate goes open (pause minutes time)-> closed
    private var pause:Double = 5.0
    private var openDate:Date = Date()
    private var checkPoint:Date = Date()
    init() {
        openGate()
    }
    init(pause:Double) {
        if pause < 0 || pause > 60 {return}
        self.pause = pause
        openGate()
    }
    func setPause(pause:Double) {
        if pause < 0 || pause > 60 {return}
        self.pause = pause
    }
    func openGate() {
        // the gate will stay open for pause minutes
        openDate.addTimeInterval(TimeInterval(pause * 60.0))
    }
    func isOpen() -> Bool {
        return Date() < openDate
    }
    func isClosed() -> Bool {
        return !isOpen()
    }
    func closeGate(){
        // force closes the gate
        openDate = Date()
    }
    func resetCheckPoint() {
        checkPoint = Date()
    }
    func timeFromCheckPoint() -> DateComponents{
        // get the time between reset check point and now
        let diffComponents:DateComponents = Calendar.current.dateComponents([.minute, .second], from: checkPoint, to: Date())
//        let minutes = diffComponents.minute ?? 0
//        let seconds = diffComponents.second ?? 0
        return diffComponents
    }
}
class Cerabellum{
    // runs an algorithm
    private var fin:Int = 0
    private(set) var at:Int = 0
    private var failType:enumFail = enumFail.ok
    private var incrementAt:Bool = false
    var alg:Algorithm?
    private var isActive1:Bool = false
    private(set) var emot:String = ""
    func advanceInAlg() {
        if incrementAt {
            incrementAt = false
            at += 1
            if at == fin {
                isActive1 = false
            }
        }
    }
    func setAlgorithm(algorithm:Algorithm) {
        if(!isActive1) && (!algorithm.algParts.isEmpty){
            self.alg = algorithm
            at = 0;fin = algorithm.getSize();isActive1 = true
            emot = alg!.algParts[at].myName()
        }
    }
    func isActive() -> Bool {
        return isActive1
    }
    func setActive(isActive:Bool) {
        self.isActive1 = isActive
    }
    func act(ear: String, skin: String, eye: String) -> String {
        if !isActive1 {return ""}
        var axnStr:String = ""
        if at < fin {
        let algPart:Mutatable = alg!.algParts[at]
            axnStr = algPart.action(ear: ear, skin: skin, eye: eye)
            self.emot = algPart.myName()
            if algPart.completed(){
                incrementAt = true
            }
        }
        return axnStr
    }
    func getMutationLimitOfActiveAlgPart() -> Int {
        return alg!.algParts[at].getMutationLimit()
    }
    func getFailType() -> enumFail {
        return alg!.algParts[at].failure(input: "")
    }
}
class DExplorer {
    // class responsible for mutations of algParts (Mutatable)
    private var failureCounter:Int = 0
    private var prevAP:String = ""
    private let regexUtil:RegexUtil = RegexUtil()
    func mutate(cera:Cerabellum, failure:enumFail) {
        let AP:String = regexUtil.stripAwayNumbers(str1: cera.emot)
        /*
                 * group relies on a naming convention each class in a mutation series must have
                 * the same class name concated with a number : APMoan1, APMoan2, APMaon3 ...
         */
        // give up ?
        if prevAP.contains(AP) && !(failure == enumFail.ok){
            failureCounter += 1
            if failureCounter > cera.getMutationLimitOfActiveAlgPart(){
                cera.setActive(isActive: false)
                // failureCounter = 0 // enable mutation even on next algpart assuming it will be the same type (example alg: APx APx)
            }
            else{
                if !prevAP.contains(AP){
                    failureCounter = 0
                }
            }
        }
        prevAP = AP
        // set AP to return fail from it's failure func to enable mutation
        switch (failure)  {
          case .fail:
            let mutant:Mutatable = cera.alg!.algParts[cera.at]
            cera.alg!.algParts[cera.at] = mutant.mutation()
          case .cloudian:
            // cancel running algorithm entirely at any alg part point
            cera.setActive(isActive: false)
        default:
            return
        }
    }
}
class PriorityQueue<T> {
  var elements: [T] = []

  func insert(_ value: T) {
    elements.append(value)
  }
  @discardableResult
  func poll() -> T? {
    guard !elements.isEmpty else {
      return nil
    }
    return elements.removeFirst()
  }

  var head: T? {
    return elements.first
  }

  var tail: T? {
    return elements.last
  }
    func isEmpty() -> Bool {
        return elements.isEmpty
    }
    func size() -> Int {
        return elements.count
    }
}
class FusionCera:Cerabellum {
    private var abort:Int = 0
    private(set) var algQueue:PriorityQueue<Algorithm>
    init(algQueue:PriorityQueue<Algorithm>) {
        self.algQueue = algQueue
    }
    func setAbort(abort:Int) {
        self.abort = abort + 1
    }
    override func act(ear: String, skin: String, eye: String) -> String {
        let result:String = super.act(ear: ear, skin: skin, eye: eye)
        abort -= 1
        if abort < 1 {
            super.setActive(isActive: false)
        }else{
            if !super.isActive(){
                let rep = super.alg!.getRepresentation()
                for i in 1..<algQueue.size() {
                    if rep == algQueue.elements[i].getRepresentation(){
                        algQueue.elements.remove(at: i); break
                    }
                }
            }
        }
        return result
    }
}
class Fusion {
    /*
     * fuses algorithms and sends needed algorithm to a designated cerabellum
     * cobject for activation
     */
    private var algDurations:[String:Int] // run duration of alg, think cycles #
    private var algDurations2:[String:Int] = [:]
    private var emot:String = "" // emotion represented by the active alg part (Mutatable)
    private var algQueue:PriorityQueue<Algorithm> = PriorityQueue<Algorithm>() // regular algs in stand by to run
    private var dangerAlgQueue:PriorityQueue<Algorithm> = PriorityQueue<Algorithm>() // fight or flight algorithm in stand by to run
    private var reqOverLoad:Bool = false // too many requests
    private var repReq:Bool = false // chobit has already accepted this request
    private var represantations:Set<String> = Set<String>() // algs representation attribute
    private let dExplorer:DExplorer = DExplorer()
    private var goalsToTrack : [String] = ["",""] // // dangerCera, mainCera string array
    // cerabellums :
    private let dangerCera:Cerabellum = Cerabellum()
    // requip ceras
    private(set) var fusionCera : FusionCera
    private let mainCera:Cerabellum = Cerabellum()
    private(set) var cera:[Cerabellum]
    init(algDurations:[String:Int]) {
        fusionCera = FusionCera(algQueue: self.algQueue)
        cera = [dangerCera,fusionCera,mainCera]
        self.algDurations = algDurations
    }
    func setAlgQueue(shinkei:Neuron) {
        for algorithm:Algorithm in shinkei.negativeAlgParts {
            if self.dangerAlgQueue.isEmpty(){
                self.dangerAlgQueue.elements.append(algorithm.clone())
            }else{break}
        }
        self.repReq = false
        for algorithm:Algorithm in shinkei.algParts{
            updateRepresentations() // update all standby algs list (to hashset)
            let tempAlgRepresentation = algorithm.getRepresentation()
            let existinAlg:Bool = self.represantations.contains(tempAlgRepresentation)
            if existinAlg {
                // this requested alg is already in stand by, this is negging !
                self.repReq = true
                continue
            }
            if self.algQueue.size() < 5 {
                // add alg if there is room in the alg queue
                self.algQueue.insert(algorithm.clone())
            }else{
                break
            }
        }
        // full of algs to do and another one is requested ? that is an overload
        self.reqOverLoad = self.algQueue.size() > 4 && !shinkei.algParts.isEmpty
        // empty neuron
        shinkei.empty()
        if !dangerCera.isActive() && !dangerAlgQueue.isEmpty(){
            dangerCera.setAlgorithm(algorithm: dangerAlgQueue.poll()!)
            goalsToTrack[0] = dangerCera.alg!.getRepresentation()
            goalTrack(algRepresantation: goalsToTrack[0])
        }
        if !mainCera.isActive() && !algQueue.isEmpty(){
            mainCera.setAlgorithm(algorithm: algQueue.poll()!)
            goalsToTrack[1] = mainCera.alg!.getRepresentation()
            goalTrack(algRepresantation: goalsToTrack[1])
        }
        fuze()
    }
    func getRepReq() -> Bool {
        // was a request repeatedly asked for ?
        return self.repReq
    }
    func getReqOverload() -> Bool {
        // too many requests ?
        return self.reqOverLoad
    }
    func getEmot() -> String {
        // get active AlgPart representing the emotion
        return self.emot
    }
    private func goalTrack(algRepresantation:String) {
        // make sure algDurations has this alg
        if algDurations2[algRepresantation] == nil {
            algDurations[algRepresantation] = 0
            algDurations2[algRepresantation] = 0
        }else{
            algDurations[algRepresantation] = algDurations2[algRepresantation]
        }
    }
    private func updateRepresentations() {
        self.represantations = Set<String>()
        for algorithm:Algorithm in self.algQueue.elements {
            self.represantations.insert(algorithm.getRepresentation())
        }
    }
    private func goalTrackReset(algRepresantation:String) {
        if !algRepresantation.isEmpty {
            algDurations2[algRepresantation] = 0
        }
    }
    private func fuze() {
        if mainCera.isActive() && !fusionCera.isActive(){
            var algRunTime:Int = algDurations[mainCera.alg!.getRepresentation()]!
            algRunTime = algRunTime / 2
            var gRep1:String = ""; var time1:Int = 0
            for alg1:Algorithm in algQueue.elements{
                gRep1 = alg1.getRepresentation()
                goalTrack(algRepresantation: gRep1)
                time1 = algDurations[gRep1]!
                // alg in queue is faster than the one running ?
                if time1 < algRunTime {
                    fusionCera.setAlgorithm(algorithm: alg1)
                    removeAlg(represantation: gRep1) // remove alg from queue, its gonna be running on the fusionCera
                    fusionCera.setAbort(abort: time1)
                    goalTrackReset(algRepresantation: gRep1)
                    break
                }
            }
        }
        goalTrackReset(algRepresantation: goalsToTrack[0]);goalsToTrack[0] = ""
        goalTrackReset(algRepresantation: goalsToTrack[1]);goalsToTrack[1] = ""
    }
    private func removeAlg(represantation:String) {
        for i in 0..<algQueue.size() {
            if represantation == algQueue.elements[i].getRepresentation(){
                algQueue.elements.remove(at: i); break
            }
        }
    }
    func act(ear:String, skin:String, eye:String) -> String {
        var result:String = ""
        for i in 0..<cera.count {
            if cera[i].isActive(){
                result = cera[i].act(ear: ear, skin: skin, eye: eye)
                dExplorer.mutate(cera: cera[i], failure: cera[i].getFailType())
                cera[i].advanceInAlg()
                self.emot = cera[i].emot
                if i > 1 {
                    let temp:String = cera[i].alg!.getRepresentation()
                    let n1:Int = algDurations2[temp]!
                    algDurations2[temp] = n1 + 1
                }
                break
            }
        }
        return result
    }
}
class Thinkable {
    func think(ear: String, skin: String, eye: String) -> String {
        // override me
        return ""
    }
}
class Chobits:Thinkable {
    fileprivate(set) var emot = "" // emotion represented by the current active alg part
    var kokoro:Kokoro // consciousness
    private var dClasses: Array<DiSkillV2> // skills of the chobit
    var algDurations:[String:Int]
    fileprivate var fusion:Fusion // multitasking center
    fileprivate var noiron:Neuron = Neuron() // algorithms transporter
    fileprivate var lastOutput = ""
    fileprivate var timeGate:TimeGate = TimeGate() // circadian pacemaker
    override init () {
        self.algDurations = [:]
        self.fusion = Fusion(algDurations: algDurations)
        self.dClasses = [DiSkillV2]()
        self.kokoro = Kokoro(absDictionaryDB: AbsDictionaryDB())
    }
    /* set the chobit database
            the database is built as a key value dictionary
            the database can be used with by the Kokoro attribute
        * */
    func setDataBase(absDictionaryDB:AbsDictionaryDB) {
        self.kokoro = Kokoro(absDictionaryDB: absDictionaryDB)
    }
    @discardableResult
    func addSkill(skill:DiSkillV2) -> Chobits {
        // add a skill (builder design patterned func))
        skill.setKokoro(kokoro: self.kokoro)
        self.dClasses.append(skill)
        return self
    }
    func clearSkills() {
        // remove all skills
        dClasses.removeAll()
    }
    func addSkills(skills:DiSkillV2...) {
        for skill in skills{
            skill.setKokoro(kokoro: self.kokoro)
            dClasses.append(skill)
        }
    }
    func setPause(pause:Double) {
        // set standby timegate pause.
        // pause time without output from the chobit
        // means the standby attribute will be true for a moment.
        // it is the equivelant of the chobit being bored
        // the standby attribute can be accessed via the kokoro
        // object within a skill if needed
        self.timeGate.setPause(pause: pause)
    }
    override func think(ear: String, skin: String, eye: String) -> String {
        // the input will be processed by the chobits' skills
        let tempEar:String = translateIn(earIn: ear)
        for skill:DiSkillV2 in self.dClasses {
            inOut(dClass: skill, ear: tempEar, skin: skin, eye: eye)
        }
        fusion.setAlgQueue(shinkei: self.noiron)
        return translateOut(outResult: fusion.act(ear: tempEar, skin: skin, eye: eye))
    }
    func getSoulEmotion() -> String {
        // get the last active AlgPart name
        // the AP is an action, and it also represents
        // an emotion
        return fusion.getEmot()
    }
    fileprivate func translateIn(earIn:String) -> String {
        // makes sure the chobit doesn't feedback on her own output
        return earIn == lastOutput ? "" : earIn
    }
    fileprivate func translateOut(outResult:String) -> String {
        // save last output served
        if !outResult.isEmpty {
            lastOutput = outResult
            self.timeGate.openGate()
            self.kokoro.standBy = false
        }
        // standBy :
        else{
            if self.timeGate.isClosed() {
                self.kokoro.standBy = true
                self.timeGate.openGate()
            }else{
                self.kokoro.standBy = false
            }
        }
        return outResult
    }
    private func inOut(dClass:DiSkillV2,ear:String,skin:String,eye:String) {
        dClass.input(ear: ear, skin: skin, eye: eye)
        dClass.output(noiron: self.noiron)
    }
    func getStandBy() -> Bool {
        // this is an under use method
        // only use this for testing
        return kokoro.standBy
    }
    func getFusion() -> Fusion {
        return self.fusion
    }
}
/* Brain class
 *********
 *intro *
 ********

 up until now, the LivinGrimoire was on par with the matrix learn scene.
 one line of code to add one skill.

 that is great, that is sci-fi turned real, that is the most significant coding achievement in the history of time.

 but hey why stop there? why only be on par with the matrix and the human brain ?
 what is beyond the matrix level? you already know

 cyberpunk>the matrix.
 one line of code to add a skill, but ALSO! 1 line of code to add a hardware capability.

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

 see Brain main for example use of the cyberpunk Software Design Pattern
 */
class Brain {
    public var logicChobit:Chobits
    public var hardwareChobit:Chobits
    private var emotion:String = ""
    private var bodyInfo:String = ""
    private var logicChobitOutput:String = ""
    public func getEmotion()->String{return emotion}
    public func getLogicChobitOutput()->String{return logicChobitOutput}
    public func getBodyInfo()->String{return bodyInfo}
    init() {
        self.logicChobit = Chobits()
        self.hardwareChobit = Chobits()
    }
    func doIt(ear:String, skin:String, eye:String) {
        if !bodyInfo.isEmpty{
            logicChobitOutput = logicChobit.think(ear: ear, skin: bodyInfo, eye: eye)
        }
        else{
            logicChobitOutput = logicChobit.think(ear: ear, skin: skin, eye: eye)
        }
        emotion = logicChobit.getSoulEmotion()
        bodyInfo = hardwareChobit.think(ear: logicChobitOutput, skin: skin, eye: ear)
    }
}
