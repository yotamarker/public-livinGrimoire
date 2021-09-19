package com.yotamarker.lgkotlinfull.LGCore

import android.graphics.Point
import java.util.*
import java.util.regex.Pattern


// returns expression of type theRegex from the string str2Check
class RegexUtil {
    fun regexChecker(theRegex: String?, str2Check: String?): String {
        val checkRegex = Pattern.compile(theRegex)
        val regexMatcher = checkRegex.matcher(str2Check)
        while (regexMatcher.find()) {
            if (regexMatcher.group().length != 0) {
                return regexMatcher.group().trim { it <= ' ' }
            }
        }
        return ""
    }

    fun numberRegex(str2Check: String?): String {
        val theRegex = "[-+]?[0-9]{1,13}(\\.[0-9]*)?"
        val list = ArrayList<String>()
        val checkRegex = Pattern.compile(theRegex)
        val regexMatcher = checkRegex.matcher(str2Check)
        while (regexMatcher.find()) {
            if (regexMatcher.group().length != 0) {
                return regexMatcher.group().trim { it <= ' ' }
            }
        }
        return ""
    }

    fun timeStampRegex(str2Check: String?): String {
        val theRegex = "(([0-9]|[0-1][0-9]|[2][0-3]):([0-5][0-9])$)|(^([0-9]|[1][0-9]|[2][0-3])$)"
        val list = ArrayList<String>()
        val checkRegex = Pattern.compile(theRegex)
        val regexMatcher = checkRegex.matcher(str2Check)
        while (regexMatcher.find()) {
            if (regexMatcher.group().length != 0) {
                return regexMatcher.group().trim { it <= ' ' }
            }
        }
        return ""
    }

    fun intRegex(str2Check: String?): String {
        val theRegex = "[-+]?[0-9]{1,13}"
        val list = ArrayList<String>()
        val checkRegex = Pattern.compile(theRegex)
        val regexMatcher = checkRegex.matcher(str2Check)
        while (regexMatcher.find()) {
            if (regexMatcher.group().length != 0) {
                return regexMatcher.group().trim { it <= ' ' }
            }
        }
        return ""
    }

    fun pointRegex(str2Check: String): Point {
        // "[-+]?[0-9]{1,13}(\\.[0-9]*)?" for double numbers
        val theRegex = "[-+]?[0-9]{1,13}"
        val result: Point = Point(0, 0)
        val list = ArrayList<String>()
        val checkRegex = Pattern.compile(theRegex)
        val regexMatcher = checkRegex.matcher(str2Check)
        while (regexMatcher.find()) {
            if (regexMatcher.group().length != 0) {
                result.y = regexMatcher.group().trim { it <= ' ' }.toInt()
            }
        }
        var phase2: String = str2Check.replace(result.y.toString() + "", "")
        phase2 = numberRegex(phase2)
        result.x = phase2.toInt()
        return result
    }

    fun regexChecker2(theRegex: String?, str2Check: String?): ArrayList<String> {
        // return a list of all matches
        val list = ArrayList<String>()
        val checkRegex = Pattern.compile(theRegex)
        val regexMatcher = checkRegex.matcher(str2Check)
        while (regexMatcher.find()) {
            if (regexMatcher.group().length != 0) {
                list.add(regexMatcher.group().trim { it <= ' ' })
            }
        }
        return list
    }

    fun contactRegex(str2Check: String?): String {
        // return a list of all matches
        val theRegex = "(?<=contact)(.*)"
        val list = ArrayList<String>()
        val checkRegex = Pattern.compile(theRegex)
        val regexMatcher = checkRegex.matcher(str2Check)
        while (regexMatcher.find()) {
            if (regexMatcher.group().length != 0) {
                return regexMatcher.group().trim { it <= ' ' }
            }
        }
        return ""
    }

    fun emailRegex(str2Check: String?): String {
        // return a list of all matches
        val theRegex = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$"
        val list = ArrayList<String>()
        val checkRegex = Pattern.compile(theRegex)
        val regexMatcher = checkRegex.matcher(str2Check)
        while (regexMatcher.find()) {
            if (regexMatcher.group().length != 0) {
                return regexMatcher.group().trim { it <= ' ' }
            }
        }
        return ""
    }

    fun duplicateRegex(str2Check: String?): String {
        // return a list of all matches
        // String theRegex = "\\b(\\w+)(\\b\\W+\\b\\1\\b)*";
        val theRegex = "\\b([\\w\\s']+) \\1\\b" // set to 1 repeat of a word like hadoken hadoken
        val list = ArrayList<String>()
        val checkRegex = Pattern.compile(theRegex)
        val regexMatcher = checkRegex.matcher(str2Check)
        while (regexMatcher.find()) {
            if (regexMatcher.group().length != 0) {
                return uniqueWord(regexMatcher.group().trim { it <= ' ' })
            }
        }
        return ""
    }

    fun uniqueWord(str: String): String {
        val list = ArrayList<String>()
        val s = str.split(" ").toTypedArray()
        var p = s[0]
        list.add(p)
        for (i in 1 until s.size) {
            if (!(p === s[i])) {
                list.add(s[i])
            }
            p = s[i]
        } // i
        return list[0]
    }

    fun afterWord(word: String, str2Check: String?): String {
        // return a list of all matches
        val theRegex = "(?<=$word)(.*)"
        val list = ArrayList<String>()
        val checkRegex = Pattern.compile(theRegex)
        val regexMatcher = checkRegex.matcher(str2Check)
        while (regexMatcher.find()) {
            if (regexMatcher.group().length != 0) {
                return regexMatcher.group().trim { it <= ' ' }
            }
        }
        return ""
    }

    fun phoneRegex1(str2Check: String?): String {
        return regexChecker("[0]\\d{2}\\d{4}\\d{3}$", str2Check)
    }

    fun firstWord(str2Check: String): String {
        val arr: Array<String> = str2Check.split(" ").toTypedArray()
        return arr[0]
    }
}