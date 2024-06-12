package auxiliary_modules

import java.awt.Point
import java.util.*
import java.util.regex.Pattern

// returns expression of type theRegex from the string str2Check
class RegexUtil {
    var regexDictionary: Hashtable<enumRegexGrimoire, String> = Hashtable<enumRegexGrimoire, String>()

    init {
        regexDictionary[enumRegexGrimoire.Email] = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}"
        regexDictionary[enumRegexGrimoire.TimeStamp] = "[0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}"
        regexDictionary[enumRegexGrimoire.SimpleTimeStamp] = "[0-9]{1,2}:[0-9]{1,2}"
        regexDictionary[enumRegexGrimoire.SecondlessTimeStamp] = "[0-9]{1,2}:[0-9]{1,2}"
        regexDictionary[enumRegexGrimoire.FullDate] =
            "[0-9]{1,4}/[0-9]{1,2}/[0-9]{1,2} [0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}"
        regexDictionary[enumRegexGrimoire.Date] = "[0-9]{1,4}/[0-9]{1,2}/[0-9]{1,2}"
        regexDictionary[enumRegexGrimoire.Double_num] = "[-+]?[0-9]*[.,][0-9]*"
        regexDictionary[enumRegexGrimoire.Integer] = "[-+]?[0-9]{1,13}"
        regexDictionary[enumRegexGrimoire.RepeatedWord] = "\\b([\\w\\s']+) \\1\\b"
        regexDictionary[enumRegexGrimoire.Phone] = "[0]\\d{9}"
        regexDictionary[enumRegexGrimoire.TrackingID] = "[A-Z]{2}[0-9]{9}[A-Z]{2}"
        regexDictionary[enumRegexGrimoire.IPV4] = "([0-9].){4}[0-9]*"
        regexDictionary[enumRegexGrimoire.Domain] = "[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}"
        regexDictionary[enumRegexGrimoire.Number] = "\\d+(\\.\\d+)?"
    }

    fun extractRegex(theRegex: String, str2Check: String): String {
        val checkRegex = Pattern.compile(theRegex)
        val regexMatcher = checkRegex.matcher(str2Check)
        while (regexMatcher.find()) {
            if (regexMatcher.group().isNotEmpty()) {
                return regexMatcher.group().trim { it <= ' ' }
            }
        }
        return ""
    }

    fun extractRegex(theRegex: enumRegexGrimoire, str2Check: String): String {
        val checkRegex = regexDictionary[theRegex]?.let { Pattern.compile(it) } ?: return ""
        val regexMatcher = checkRegex.matcher(str2Check)
        while (regexMatcher.find()) {
            if (regexMatcher.group().isNotEmpty()) {
                return regexMatcher.group().trim { it <= ' ' }
            }
        }
        return ""
    }

    fun extractAllRegexes(theRegex: String, str2Check: String): ArrayList<String> {
        // return a list of all matches
        val list = ArrayList<String>()
        val checkRegex = Pattern.compile(theRegex)
        val regexMatcher = checkRegex.matcher(str2Check)
        while (regexMatcher.find()) {
            if (regexMatcher.group().isNotEmpty()) {
                list.add(regexMatcher.group().trim { it <= ' ' })
            }
        }
        return list
    }

    fun extractAllRegexes(theRegex: enumRegexGrimoire, str2Check: String): ArrayList<String> {
        // return a list of all matches
        val list = ArrayList<String>()
        val checkRegex = regexDictionary[theRegex]?.let { Pattern.compile(it) } ?: return list
        val regexMatcher = checkRegex.matcher(str2Check)
        while (regexMatcher.find()) {
            if (regexMatcher.group().isNotEmpty()) {
                list.add(regexMatcher.group().trim { it <= ' ' })
            }
        }
        return list
    }

    fun pointRegex(str2Check: String): Point {
        // "[-+]?[0-9]{1,13}(\\.[0-9]*)?" for double numbers
        val theRegex = "[-+]?[0-9]{1,13}"
        val result = Point(0, 0)
        val list = ArrayList<String>()
        val checkRegex = Pattern.compile(theRegex)
        val regexMatcher = checkRegex.matcher(str2Check)
        while (regexMatcher.find()) {
            if (regexMatcher.group().isNotEmpty()) {
                result.y = regexMatcher.group().trim { it <= ' ' }.toInt()
            }
        }
        var phase2 = str2Check.replace(result.y.toString() + "", "")
        phase2 = extractRegex(enumRegexGrimoire.Number, phase2)
        result.x = phase2.toInt()
        return result
    }

    fun contactRegex(str2Check: String): String {
        // return a list of all matches
        val theRegex = "(?<=contact)(.*)"
        val list = ArrayList<String>()
        val checkRegex = Pattern.compile(theRegex)
        val regexMatcher = checkRegex.matcher(str2Check)
        while (regexMatcher.find()) {
            if (regexMatcher.group().isNotEmpty()) {
                return regexMatcher.group().trim { it <= ' ' }
            }
        }
        return ""
    }

    fun duplicateRegex(str2Check: String): String {
        // return a list of all matches
        // String theRegex = "\\b(\\w+)(\\b\\W+\\b\\1\\b)*";
        val theRegex = "\\b([\\w\\s']+) \\1\\b" // set to 1 repeat of a word like hadoken hadoken
        val list = ArrayList<String>()
        val checkRegex = Pattern.compile(theRegex)
        val regexMatcher = checkRegex.matcher(str2Check)
        while (regexMatcher.find()) {
            if (regexMatcher.group().isNotEmpty()) {
                return uniqueWord(regexMatcher.group().trim { it <= ' ' })
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
        val checkRegex = Pattern.compile(theRegex)
        val regexMatcher = checkRegex.matcher(str2Check)
        while (regexMatcher.find()) {
            if (regexMatcher.group().isNotEmpty()) {
                return regexMatcher.group().trim { it <= ' ' }
            }
        }
        return ""
    }

    fun firstWord(str2Check: String): String {
        val arr = str2Check.split(" ".toRegex(), limit = 2).toTypedArray()
        return arr[0]
    }
}