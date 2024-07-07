//
//  auxiliary_module.swift
//  LivinGrimoireSwiftV1
//
//  Created by moti barski on 16/08/2022.
//

import Foundation
class DeepCopier{
    func copyList(original: Array<String>)->Array<String>{
        var deepCopy: Array<String> = [String]()
        for item in original{
            deepCopy.append(item)
        }
        return deepCopy
    }
    func copyListOfInts(original: Array<Int>)->Array<Int>{
        var deepCopy: Array<Int> = [Int]()
        for item in original{
            deepCopy.append(item)
        }
        return deepCopy
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



class TimeUtils {
    
    var right_now = Date()
    var calendar = Calendar.current
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
        right_now = Date()
        let minutes:Int = calendar.component(.minute, from: right_now)
        let m:String = minutes<10 ? "0"+String(minutes):String(minutes)
        return String(calendar.component(.hour, from: right_now)) + ":" + m
    }

    func getMonthAsInt() -> Int {
       // '''This method returns the current month (MM)'''
        right_now = Date()
        return calendar.component(.month, from: right_now)
    }

    func getDayOfTheMonthAsInt() -> Int {
       // '''This method returns the current day (dd)'''
        right_now = Date()
        return calendar.component(.day, from: right_now)
    }

    func getYearAsInt() -> Int {
      //  '''This method returns the current year (yyyy)'''
        right_now = Date()
        return calendar.component(.year, from: right_now)
    }

    func getDayAsInt() -> Int {
       // '''This method returns the current day of the week (1, 2, ... 7)'''
        right_now = Date()
        return calendar.component(.weekday, from: right_now)
    }

    func getMinutes() -> String {
       // '''This method returns the current minutes (mm)'''
        right_now = Date()
        return right_now.minute() ?? ""
    }

    func getSeconds() -> String {
      //  '''This method returns the current seconds (ss)'''
        right_now = Date()
        return String(calendar.component(.second, from: right_now))
    }

    func getDayOfDWeek() -> String {
      //  '''This method returns the current day of the week as a word (monday, ...)'''
        right_now = Date()
        return right_now.dayOfWeek()!
    }

    func translateMonthDay(_ day_num:Int) -> String {
       // '''This method returns the current day of the month as a word (first_of, ...)'''
        let currentDay_string = dayOfMonth[day_num] ?? "?"
        return currentDay_string
    }

    func getSpecificTime(time_variable: enumTimes) -> String {
//        '''This method returns the current specific date in words (eleventh_of June 2021, ...)'''

        right_now = Date()
       let enum_temp = time_variable
        switch enum_temp {
        case .date:
            return getCurrentMonthDay() + " " + (right_now.month() ?? "/") + " " + (right_now.year() ?? "/")
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
        right_now = Date()
        return calendar.component(.second, from: right_now)
    }

    func getMinutesAsInt() -> Int {
       // '''This method returns the current minutes'''
        right_now = Date()
        return calendar.component(.minute, from: right_now)
    }

    func getHoursAsInt() -> Int {
      //  '''This method returns the current hour'''
        right_now = Date()
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
        right_now = Date()
        // reset datecomponents
       dateComponent = DateComponents()
        dateComponent.minute = x
        let final_time = Calendar.current.date(byAdding: dateComponent, to: right_now)
        return String(calendar.component(.hour, from: final_time ?? Date())) + ":" + String(calendar.component(.minute, from: final_time ?? Date()))
    
    }
    func isDayTime() -> Bool {
        right_now = Date()
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
        right_now = Date()
        return right_now.localToGMT()
        
    }

    func getLocal() -> String {
       // '''This method returns the local time zone'''
        return TimeZone.current.identifier
    }
    func findDay(month:Int,day:Int,year:Int) -> String {
        // gets weekday from date
        if day > 31 {
            return ""
        }
        if (day > 30){
            if ((month == 4)||(month == 6)||(month == 9)||(month == 11)){return ""}
        }
        if(month == 2){
            if(isLeapYear(year: getYearAsInt())){
                if (day > 29){
                    return ""
                }
            }
            if(day > 28){
                return ""
            }
        }
        // convert string to date
        let today:String = "\(year)-\(month)-\(day)"
        let formatter  = DateFormatter()
        formatter.dateFormat = "yyyy-MM-dd"
        guard let todayDate = formatter.date(from: today) else { return "" }
        let myCalendar = Calendar(identifier: .gregorian)
        let weekDay = myCalendar.component(.weekday, from: todayDate)
        return self.week_days[weekDay] ?? ""
    }
    func nxtDayOnDate(dayOfMonth:Int) -> String {
        // get the weekday on the next dayOfMonth
        let today:Int = getDayOfTheMonthAsInt()
        if today <= dayOfMonth {
            return findDay(month: getMonthAsInt(), day: dayOfMonth, year: getYearAsInt())
        }else if (!(getMonthAsInt() == 12)){
            return findDay(month: getMonthAsInt() + 1, day: dayOfMonth, year: getYearAsInt())
        }
        return findDay(month: 1, day: dayOfMonth, year: getYearAsInt() + 1)
    }
    func isLeapYear(year:Int) -> Bool {
        var isLeapYear:Bool
        isLeapYear = (year % 4 == 0)
        return isLeapYear && (year % 100 != 0 || year % 400 == 0)
    }
    func getCurrentMonthName() -> String {
        switch (getMonthAsInt()){
                    case 1:
                        return "january"
                    case 2:
                        return "february"
                    case 3:
                        return "march"
                    case 4:
                        return "april"
                    case 5:
                        return "may"
                    case 6:
                        return "june"
                    case 7:
                        return "july"
                    case 8:
                        return "august"
                    case 9:
                        return "november"
                    case 10:
                        return "october"
                    case 11:
                        return "november"
                    case 12:
                        return "december"
                    default:
                        return ""
        }
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

extension TimeUtils {
    func nowPlusOneDay() -> Date {
        // reset datecomponents
        right_now = Date()
        dateComponent = DateComponents()
      dateComponent.day = 1
        return Calendar.current.date(byAdding: dateComponent, to: right_now) ?? Date()
    }
    func nowMinusOneDay() -> Date {
        right_now = Date()
        // reset datecomponents
        dateComponent = DateComponents()
      dateComponent.day = -1
        return Calendar.current.date(byAdding: dateComponent, to: right_now) ?? Date()
    }
    func getCurrentMonthDay() -> String {
        right_now = Date()
        let currentDay_number = calendar.component(.day, from: right_now)
        return translateMonthDay(currentDay_number)
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
    secondlessTimeStamp, date, fullDate, simpleTimeStamp
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
        regexDictionary[enumRegexGrimoire.simpleTimeStamp] = "[0-9]{1,2}:[0-9]{1,2}"
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
    private var pause:Int = 5
    private var openDate:Date = Date()
    private var checkPoint:Date = Date()
    init() {
        openGate()
    }
    init(pause:Int) {
        if pause < 0 || pause > 60 {return}
        self.pause = pause
        openGate()
    }
    func setPause(pause:Int) {
        if pause < 0 || pause > 60 {return}
        self.pause = pause
    }
    func openGate() {
        // the gate will stay open for pause minutes
        openDate.addTimeInterval(TimeInterval(pause * 60))
    }
    func openGateforNSeconds(_ n:Int) {
        // the gate will stay open for n seconds
        openDate.addTimeInterval(TimeInterval(n))
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
    // Super class to output an algorithm out of a selection of skills
    // Engage the hub with dispenseAlg and return the value to outAlg attribute
    // of the containing skill (which houses the skill hub)
    // This module enables using a selection of 1 skill for triggers instead of having the triggers engage on multiple skills
    // The method is ideal for learnability and behavioral modifications
    // Use a learnability auxiliary module as a condition to run an active skill shuffle or change method
    // (rndAlg, cycleAlg)
    // Moods can be used for specific cases to change behavior of the AGI, for example low energy state
    // For that use (moodAlg)
   
    private var skills: [DiSkillV2] = []
    private var activeSkill: Int = 0
    private let tempN = Neuron()
    private let rand = Int.random(in: 0..<Int.max)
    private var kokoro = Kokoro(absDictionaryDB: AbsDictionaryDB())
   
    init(skillsParams: DiSkillV2...) {
        for skill in skillsParams {
            skill.setKokoro(kokoro: kokoro)
            skills.append(skill)
        }
    }
   
    func setKokoro(_ kokoro: Kokoro) {
        self.kokoro = kokoro
        for skill in skills {
            skill.setKokoro(kokoro: kokoro)
        }
    }
   
    @discardableResult
    func addSkill(_ skill: DiSkillV2) -> SkillHubAlgDispenser {
        // Builder pattern
        skill.setKokoro(kokoro: kokoro)
        skills.append(skill)
        return self
    }
   
    func dispenseAlgorithm(ear: String, skin: String, eye: String) -> AlgorithmV2? {
        // Return value to outAlg param of (external) summoner DiSkillV2
        skills[activeSkill].input(ear: ear, skin: skin, eye: eye)
        skills[activeSkill].output(noiron: tempN)
        for i in 1..<6 {
            if let temp = tempN.getAlg(defcon: i) {
                return AlgorithmV2(priority: i, alg: temp)
            }
        }
        return nil
    }
   
    func randomizeActiveSkill() {
        activeSkill = Int.random(in: 0..<skills.count)
    }
   
    func setActiveSkillWithMood(_ mood: Int) {
        // Mood integer represents active skill
        // Different mood = different behavior
        if mood > -1 && mood < skills.count {
            activeSkill = mood
        }
    }
   
    func cycleActiveSkill() {
        // Changes active skill
        // I recommend this method be triggered with a Learnability or SpiderSense object
        activeSkill += 1
        if activeSkill == skills.count {
            activeSkill = 0
        }
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
class AXKeyValuePair {
    var key: String
    var value: String

    init(key: String = "", value: String = "") {
        self.key = key
        self.value = value
    }

    func getKey() -> String {
        return key
    }

    func setKey(_ key: String) {
        self.key = key
    }

    func getValue() -> String {
        return value
    }

    func setValue(_ value: String) {
        self.value = value
    }

    var description: String {
        return "\(key);\(value)"
    }
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
    let pl:TimeUtils = TimeUtils()
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
    func renewableDraw() -> String {
        if strings.isEmpty {reset()}
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
    var pl:TimeUtils = TimeUtils()
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
    private let pl:TimeUtils = TimeUtils()
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
    private let pl:TimeUtils = TimeUtils()
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
    private var pl:TimeUtils = TimeUtils()
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
        kv.setKey("default")
    }
    
    func getPrompt() -> String {
        return prompt
    }
    
    func setPrompt(prompt: String) {
        self.prompt = prompt
    }
    
    func process(in1: String) -> Bool {
        kv.setValue(regexUtil.regexChecker(theRegex: regex, str2Check: in1))
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
        temp.setKey(kv!.getKey())
        temp.setValue(kv!.getValue())
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
    func learnKeyValue(context: String, reply: String) {
        // Learn questions and answers/key values
        if dic[context] == nil {
            dic[context] = RefreshQ()
        }
        if dic[reply] == nil {
            dic[reply] = RefreshQ()
        }
        dic[context]!.input(in1: reply)
    }

    func feedKeyValuePairs(_ kvList: [AXKeyValuePair]) {
        guard !kvList.isEmpty else {
            return
        }
        
        for kv in kvList {
            learnKeyValue(context: kv.key, reply: kv.value)
        }
    }
    func learnV2(_ ear: String, _ elizaDeducer: ElizaDeducer) {
        feedKeyValuePairs(elizaDeducer.respond(ear))
        learn(ear: ear)
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
class ElizaDeducer {
    var babble2: [PhraseMatcher]

    init() {
        // init values in subclass
        // see ElizaDeducerInitializer for example
        // example input ountput based on ElizaDeducerInitializer values :
        // elizaDeducer.respond("a is a b")
        // [what is a a;a is a b, explain a;a is a b]
        self.babble2 = []
    }

    func respond(_ msg: String) -> [AXKeyValuePair] {
        for pm in babble2 {
            if pm.matches(msg) {
                return pm.respond(msg)
            }
        }
        return []
    }

    class PhraseMatcher {
        let matcher: NSRegularExpression
        let responses: [AXKeyValuePair]
        let regex: String

        init(matcher: String, responses: [AXKeyValuePair]) {
            do {
                self.matcher = try NSRegularExpression(pattern: matcher, options: [])
            } catch {
                fatalError("Error creating regular expression: \(error)")
            }
            self.responses = responses
            self.regex = matcher
        }

        func matches(_ str: String) -> Bool {
            let range = NSRange(location: 0, length: str.utf16.count)
            return matcher.firstMatch(in: str, options: [], range: range) != nil
        }
        func getMatchedString(for pattern: String, in input: String) -> String {
            do {
                // Create an NSRegularExpression instance
                let regex = try NSRegularExpression(pattern: pattern, options: [])

                // Find all matches in the input string
                let matches = regex.matches(in: input, options: [], range: NSRange(location: 0, length: input.utf16.count))

                // Extract and concatenate the matched substrings
                var result = ""
                for match in matches {
                    for i in 1..<match.numberOfRanges {
                        if let range = Range(match.range(at: i), in: input) {
                            result += input[range] + "_"
                        }
                    }
                }
                result = String(result.dropLast()) // Remove the trailing space
                return result
            } catch {
                print("Error creating regular expression: \(error)")
                return ""
            }
        }
        func respond(_ str: String) -> [AXKeyValuePair] {
            var result: [AXKeyValuePair] = []
            for kv in responses {
                let tempKV = AXKeyValuePair(key: kv.key, value: kv.value)
                let s1: String = getMatchedString(for: self.regex, in: str)
                let sa = s1.split(separator: "_").map { String($0) }
                for i in 0..<sa.count {
                        let s = sa[i]
                        tempKV.key = tempKV.key.replacingOccurrences(of: "{\(i)}", with: s.lowercased())
                        tempKV.value = tempKV.value.replacingOccurrences(of: "{\(i)}", with: s.lowercased())
                    }
                result.append(tempKV)
            }
            return result
        }
    }
}
class ElizaDeducerInitializer: ElizaDeducer {
    override init() {
        super.init()
        var babbleTmp: [PhraseMatcher] = []
        var kvs: [AXKeyValuePair] = []
        kvs.append(AXKeyValuePair(key: "what is a {0}", value: "{0} is {1}"))
        kvs.append(AXKeyValuePair(key: "explain {0}", value: "{0} is {1}"))
        babbleTmp.append(PhraseMatcher(matcher: "(.*) is (.*)", responses: kvs))
        babble2 = babbleTmp
    }
}
class Excluder {
    private var startsWith:Array<String> = [String]()
    private var endsWith:Array<String> = [String]()

    func addStartsWith(_ s1: String) {
        if !startsWith.contains("^(" + s1 + ").*") {
            startsWith.append("^(" + s1 + ").*")
        }
    }

    func addEndsWith(_ s1: String) {
        if !endsWith.contains("(.*)(?=" + s1 + ")") {
            endsWith.append("(.*)(?=" + s1 + ")")
        }
    }

    func exclude(_ ear: String) -> Bool {
        let r1 = RegexUtil() // Assuming RegexUtil is defined elsewhere
        for tempStr in startsWith {
            if r1.regexChecker(theRegex: tempStr, str2Check: ear).count > 0 {
                return true
            }
        }
        for tempStr in endsWith {
            if r1.regexChecker(theRegex: tempStr, str2Check: ear).count > 0 {
                return true
            }
        }
        return false
    }
}
class TimedMessages {
    var messages: [String: String] = [:]
    private let playGround :TimeUtils = TimeUtils()
    private var lastMSG = "nothing"
    private var msg:Bool = false

    func addMSG(_ ear: String) {
        let ru1 = RegexUtil()
        let tempMSG = ru1.regexChecker(theRegex:"(?<=remind me to).*?(?=at)", str2Check: ear)
        if tempMSG.isEmpty { return }
        let timeStamp = ru1.regexChecker(theRegex: enumRegexGrimoire.simpleTimeStamp, str2Check: ear)
        if timeStamp.isEmpty { return }
        messages[timeStamp] = tempMSG
    }

    func addMSGV2(_ timeStamp: String, _ msg: String) {
        messages[timeStamp] = msg
    }

    func sprinkleMSG(_ msg: String, amount: Int) {
        for _ in 0..<amount {
            messages[Self.generateRandomTimestamp()] = msg
        }
    }

    static func generateRandomTimestamp() -> String {
        let minutes = Int.random(in: 0..<60)
        let hours = Int.random(in: 0..<12)
        let m = String(format: "%02d", minutes)
        let h = String(format: "%02d", hours)
        return "\(h):\(m)"
    }

    func clear() {
        messages = [:]
    }

    func tick() {
        let now = playGround.getCurrentTimeStamp()
        if let message = messages[now], lastMSG != message {
            lastMSG = message
            msg = true
        }
    }

    func getLastMSG() -> String {
        msg = false
        return lastMSG
    }

    func getMsg() -> Bool {
        return msg
    }
}
class AlgorithmV2 {
    private var priority: Int = 4
    private var alg: Algorithm?

    init(priority: Int, alg: Algorithm) {
        self.priority = priority
        self.alg = alg
    }

    func getPriority() -> Int {
        return priority
    }

    func setPriority(_ priority: Int) {
        self.priority = priority
    }

    func getAlg() -> Algorithm? {
        return alg
    }

    func setAlg(_ alg: Algorithm) {
        self.alg = alg
    }
}
class AXSkillBundle {
    private var skills: [DiSkillV2] = []
    private let tempN = Neuron()
    private var kokoro = Kokoro(absDictionaryDB: AbsDictionaryDB())

    func setKokoro(_ kokoro: Kokoro) {
        self.kokoro = kokoro
        for skill in skills {
            skill.setKokoro(kokoro: self.kokoro)
        }
    }

    init(skillsParams: DiSkillV2...) {
        for skill in skillsParams {
            skill.setKokoro(kokoro: self.kokoro)
            skills.append(skill)
        }
    }

    @discardableResult
    func addSkill(_ skill: DiSkillV2) -> AXSkillBundle {
        // builder pattern
        skill.setKokoro(kokoro: self.kokoro)
        skills.append(skill)
        return self
    }

    func dispenseAlgorithm(ear: String, skin: String, eye: String) -> AlgorithmV2? {
        for skill in skills {
            skill.input(ear: ear, skin: skin, eye: eye)
            skill.output(noiron: tempN)
            for j in 1..<6 {
                if let temp = tempN.getAlg(defcon: j) {
                    return AlgorithmV2(priority: j, alg: temp)
                }
            }
        }
        return nil
    }

    func getSize() -> Int {
        return skills.count
    }
}
