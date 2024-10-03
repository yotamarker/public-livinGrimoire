package Auxiliary_Modules;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// returns expression of type theRegex from the string str2Check
public class RegexUtil {
    static Hashtable<enumRegexGrimoire, String> regexDictionary = new Hashtable<>();

    static {
        regexDictionary.put(enumRegexGrimoire.email, "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}");
        regexDictionary.put(enumRegexGrimoire.timeStamp, "[0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}");
        regexDictionary.put(enumRegexGrimoire.simpleTimeStamp, "[0-9]{1,2}:[0-9]{1,2}");
        regexDictionary.put(enumRegexGrimoire.secondlessTimeStamp, "[0-9]{1,2}:[0-9]{1,2}");
        regexDictionary.put(enumRegexGrimoire.fullDate, "[0-9]{1,4}/[0-9]{1,2}/[0-9]{1,2} [0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}");
        regexDictionary.put(enumRegexGrimoire.date, "[0-9]{1,4}/[0-9]{1,2}/[0-9]{1,2}");
        regexDictionary.put(enumRegexGrimoire.double_num, "[-+]?[0-9]*[.,][0-9]*");
        regexDictionary.put(enumRegexGrimoire.integer, "[-+]?[0-9]{1,13}");
        regexDictionary.put(enumRegexGrimoire.repeatedWord, "\\b([\\w\\s']+) \\1\\b");
        regexDictionary.put(enumRegexGrimoire.phone, "[0]\\d{9}");
        regexDictionary.put(enumRegexGrimoire.trackingID, "[A-Z]{2}[0-9]{9}[A-Z]{2}");
        regexDictionary.put(enumRegexGrimoire.IPV4, "([0-9].){4}[0-9]*");
        regexDictionary.put(enumRegexGrimoire.domain, "[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}");
        regexDictionary.put(enumRegexGrimoire.number, "\\d+(\\.\\d+)?");
        regexDictionary.put(enumRegexGrimoire.duplicateWord, "\\b(\\w+)\\b(?=.*\\b\\1\\b)");
        regexDictionary.put(enumRegexGrimoire.firstWord, "^\\w+");
        regexDictionary.put(enumRegexGrimoire.lastWord, "\\w+$");
        regexDictionary.put(enumRegexGrimoire.surname, "\\s+[^\\s]+");
        regexDictionary.put(enumRegexGrimoire.realNumber, "[-+]?[0-9]*[.,][0-9]*"); // -30.77 / 40.05
    }

    public static String extractRegex(String theRegex, String str2Check) {
        Pattern checkRegex = Pattern.compile(theRegex);
        Matcher regexMatcher = checkRegex.matcher(str2Check);
        while (regexMatcher.find()) {
            if (regexMatcher.group().length() != 0) {
                return regexMatcher.group().trim();
            }
        }
        return "";
    }

    public static String extractRegex(enumRegexGrimoire theRegex, String str2Check) {
        Pattern checkRegex = Pattern.compile(regexDictionary.get(theRegex));
        Matcher regexMatcher = checkRegex.matcher(str2Check);
        while (regexMatcher.find()) {
            if (regexMatcher.group().length() != 0) {
                return regexMatcher.group().trim();
            }
        }
        return "";
    }

    public static ArrayList<String> extractAllRegexes(String theRegex, String str2Check) {
        // return a list of all matches
        ArrayList<String> list = new ArrayList<>();
        Pattern checkRegex = Pattern.compile(theRegex);
        Matcher regexMatcher = checkRegex.matcher(str2Check);
        while (regexMatcher.find()) {
            if (regexMatcher.group().length() != 0) {
                list.add(regexMatcher.group().trim());
            }
        }
        return list;
    }

    @SuppressWarnings("unused")
    public static ArrayList<String> extractAllRegexes(enumRegexGrimoire theRegex, String str2Check) {
        // return a list of all matches
        ArrayList<String> list = new ArrayList<>();
        Pattern checkRegex = Pattern.compile(regexDictionary.get(theRegex));
        Matcher regexMatcher = checkRegex.matcher(str2Check);
        while (regexMatcher.find()) {
            if (regexMatcher.group().length() != 0) {
                list.add(regexMatcher.group().trim());
            }
        }
        return list;
    }

    public static Point pointRegex(String str2Check) {
        // "[-+]?[0-9]{1,13}(\\.[0-9]*)?" for double numbers
        String theRegex = "[-+]?[0-9]{1,13}";
        Point result = new Point(0, 0);
        Pattern checkRegex = Pattern.compile(theRegex);
        Matcher regexMatcher = checkRegex.matcher(str2Check);
        while (regexMatcher.find()) {
            if (regexMatcher.group().length() != 0) {
                result.y = Integer.parseInt(regexMatcher.group().trim());
            }
        }
        String phase2 = str2Check.replace(result.y + "", "");
        phase2 = extractRegex(enumRegexGrimoire.number, phase2);
        result.x = Integer.parseInt(phase2);
        return result;
    }
}
