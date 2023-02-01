package lgkt

import java.awt.Point
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern


// returns expression of type theRegex from the string str2Check
class RegexUtil {
    var regexDictionary = Hashtable<enumRegexGrimoire, String>()

    init {
        regexDictionary[enumRegexGrimoire.email] = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}"
        regexDictionary[enumRegexGrimoire.timeStamp] = "[0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}"
        regexDictionary[enumRegexGrimoire.secondlessTimeStamp] = "[0-9]{1,2}:[0-9]{1,2}"
        regexDictionary[enumRegexGrimoire.fullDate] =
            "[0-9]{1,4}/[0-9]{1,2}/[0-9]{1,2} [0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}"
        regexDictionary[enumRegexGrimoire.date] = "[0-9]{1,4}/[0-9]{1,2}/[0-9]{1,2}"
        regexDictionary[enumRegexGrimoire.double_num] = "[-+]?[0-9]*[.,][0-9]*"
        regexDictionary[enumRegexGrimoire.integer] = "[-+]?[0-9]{1,13}"
        regexDictionary[enumRegexGrimoire.repeatedWord] = "\\b([\\w\\s']+) \\1\\b"
        regexDictionary[enumRegexGrimoire.phone] = "[0]\\d{9}"
        regexDictionary[enumRegexGrimoire.trackingID] = "[A-Z]{2}[0-9]{9}[A-Z]{2}"
        regexDictionary[enumRegexGrimoire.IPV4] = "([0-9].){4}[0-9]*"
        regexDictionary[enumRegexGrimoire.domain] = "[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}"
        regexDictionary[enumRegexGrimoire.number] = "\\d+(\\.\\d+)?"
    }

    fun extractRegex(theRegex: String, str2Check: String): String {
        val checkRegex: Pattern = Pattern.compile(theRegex)
        val regexMatcher: Matcher = checkRegex.matcher(str2Check)
        while (regexMatcher.find()) {
            if (regexMatcher.group().length !== 0) {
                return regexMatcher.group().trim()
            }
        }
        return ""
    }

    fun extractRegex(theRegex: enumRegexGrimoire, str2Check: String): String {
        val checkRegex: Pattern = Pattern.compile(regexDictionary[theRegex])
        val regexMatcher: Matcher = checkRegex.matcher(str2Check)
        while (regexMatcher.find()) {
            if (regexMatcher.group().length !== 0) {
                return regexMatcher.group().trim()
            }
        }
        return ""
    }

    fun extractAllRegexes(theRegex: String, str2Check: String): ArrayList<String> {
        // return a list of all matches
        val list = ArrayList<String>()
        val checkRegex: Pattern = Pattern.compile(theRegex)
        val regexMatcher: Matcher = checkRegex.matcher(str2Check)
        while (regexMatcher.find()) {
            if (regexMatcher.group().length !== 0) {
                list.add(regexMatcher.group().trim())
            }
        }
        return list
    }

    fun extractAllRegexes(theRegex: enumRegexGrimoire, str2Check: String): ArrayList<String> {
        // return a list of all matches
        val list = ArrayList<String>()
        val checkRegex: Pattern = Pattern.compile(regexDictionary[theRegex])
        val regexMatcher: Matcher = checkRegex.matcher(str2Check)
        while (regexMatcher.find()) {
            if (regexMatcher.group().length !== 0) {
                list.add(regexMatcher.group().trim())
            }
        }
        return list
    }

    fun pointRegex(str2Check: String): Point {
        // "[-+]?[0-9]{1,13}(\\.[0-9]*)?" for double numbers
        val theRegex = "[-+]?[0-9]{1,13}"
        val result = Point(0, 0)
        val list = ArrayList<String>()
        val checkRegex: Pattern = Pattern.compile(theRegex)
        val regexMatcher: Matcher = checkRegex.matcher(str2Check)
        while (regexMatcher.find()) {
            if (regexMatcher.group().length !== 0) {
                result.y = regexMatcher.group().trim().toInt()
            }
        }
        var phase2: String = str2Check.replace(result.y.toString(), "")
        phase2 = extractRegex(enumRegexGrimoire.number, phase2)
        result.x = phase2.toInt()
        return result
    }

    fun contactRegex(str2Check: String): String {
        // return a list of all matches
        val theRegex = "(?<=contact)(.*)"
        val list = ArrayList<String>()
        val checkRegex: Pattern = Pattern.compile(theRegex)
        val regexMatcher: Matcher = checkRegex.matcher(str2Check)
        while (regexMatcher.find()) {
            if (regexMatcher.group().length !== 0) {
                return regexMatcher.group().trim()
            }
        }
        return ""
    }

    fun duplicateRegex(str2Check: String): String {
        // return a list of all matches
        // String theRegex = "\\b(\\w+)(\\b\\W+\\b\\1\\b)*";
        val theRegex = "\\b([\\w\\s']+) \\1\\b" // set to 1 repeat of a word like hadoken hadoken
        val list = ArrayList<String>()
        val checkRegex: Pattern = Pattern.compile(theRegex)
        val regexMatcher: Matcher = checkRegex.matcher(str2Check)
        while (regexMatcher.find()) {
            if (regexMatcher.group().length !== 0) {
                return uniqueWord(regexMatcher.group().trim())
            }
        }
        return ""
    }

    fun uniqueWord(str: String): String {
        val list = ArrayList<String>()
        val s = str.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        var p = s[0]
        list.add(p)
        for (i in 1 until s.size) {
            if (p !== s[i]) {
                list.add(s[i])
            }
            p = s[i]
        } // i
        return list[0]
    }

    fun afterWord(word: String, str2Check: String): String {
        // return a list of all matches
        val theRegex = "(?<=$word)(.*)"
        val list = ArrayList<String>()
        val checkRegex: Pattern = Pattern.compile(theRegex)
        val regexMatcher: Matcher = checkRegex.matcher(str2Check)
        while (regexMatcher.find()) {
            if (regexMatcher.group().length !== 0) {
                return regexMatcher.group().trim()
            }
        }
        return ""
    }

    fun firstWord(str2Check: String): String {
        val arr = str2Check.split(" ".toRegex(), limit = 2).toTypedArray()
        return arr[0]
    }
}