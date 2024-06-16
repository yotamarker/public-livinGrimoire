package Auxiliary_Modules;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// returns expression of type theRegex from the string str2Check
public class RegexUtil {
    Hashtable<enumRegexGrimoire,String> regexDictionary = new Hashtable<>();
    public RegexUtil(){
        regexDictionary.put(enumRegexGrimoire.email,"[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}");
        regexDictionary.put(enumRegexGrimoire.timeStamp,"[0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}");
        regexDictionary.put(enumRegexGrimoire.simpleTimeStamp,"[0-9]{1,2}:[0-9]{1,2}");
        regexDictionary.put(enumRegexGrimoire.secondlessTimeStamp,"[0-9]{1,2}:[0-9]{1,2}");
        regexDictionary.put(enumRegexGrimoire.fullDate,"[0-9]{1,4}/[0-9]{1,2}/[0-9]{1,2} [0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}");
        regexDictionary.put(enumRegexGrimoire.date,"[0-9]{1,4}/[0-9]{1,2}/[0-9]{1,2}");
        regexDictionary.put(enumRegexGrimoire.double_num,"[-+]?[0-9]*[.,][0-9]*");
        regexDictionary.put(enumRegexGrimoire.integer,"[-+]?[0-9]{1,13}");
        regexDictionary.put(enumRegexGrimoire.repeatedWord,"\\b([\\w\\s']+) \\1\\b");
        regexDictionary.put(enumRegexGrimoire.phone,"[0]\\d{9}");
        regexDictionary.put(enumRegexGrimoire.trackingID,"[A-Z]{2}[0-9]{9}[A-Z]{2}");
        regexDictionary.put(enumRegexGrimoire.IPV4,"([0-9].){4}[0-9]*");
        regexDictionary.put(enumRegexGrimoire.domain,"[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}");
        regexDictionary.put(enumRegexGrimoire.number,"\\d+(\\.\\d+)?");
    }
    public String extractRegex(String theRegex, String str2Check) {
        Pattern checkRegex = Pattern.compile(theRegex);
        Matcher regexMatcher = checkRegex.matcher(str2Check);
        while (regexMatcher.find()) {
            if (regexMatcher.group().length() != 0) {
                return regexMatcher.group().trim();
            }
        }
        return "";
    }
    public String extractRegex(enumRegexGrimoire theRegex, String str2Check) {
        Pattern checkRegex = Pattern.compile(regexDictionary.get(theRegex));
        Matcher regexMatcher = checkRegex.matcher(str2Check);
        while (regexMatcher.find()) {
            if (regexMatcher.group().length() != 0) {
                return regexMatcher.group().trim();
            }
        }
        return "";
    }
    public ArrayList<String> extractAllRegexes(String theRegex, String str2Check) {
        // return a list of all matches
        ArrayList<String> list = new ArrayList<String>();
        Pattern checkRegex = Pattern.compile(theRegex);
        Matcher regexMatcher = checkRegex.matcher(str2Check);
        while (regexMatcher.find()) {
            if (regexMatcher.group().length() != 0) {
                list.add(regexMatcher.group().trim());
            }
        }
        return list;
    }
    public ArrayList<String> extractAllRegexes(enumRegexGrimoire theRegex, String str2Check) {
        // return a list of all matches
        ArrayList<String> list = new ArrayList<String>();
        Pattern checkRegex = Pattern.compile(regexDictionary.get(theRegex));
        Matcher regexMatcher = checkRegex.matcher(str2Check);
        while (regexMatcher.find()) {
            if (regexMatcher.group().length() != 0) {
                list.add(regexMatcher.group().trim());
            }
        }
        return list;
    }
    public Point pointRegex(String str2Check) {
        // "[-+]?[0-9]{1,13}(\\.[0-9]*)?" for double numbers
        String theRegex = "[-+]?[0-9]{1,13}";
        Point result = new Point(0, 0);
        ArrayList<String> list = new ArrayList<String>();
        Pattern checkRegex = Pattern.compile(theRegex);
        Matcher regexMatcher = checkRegex.matcher(str2Check);
        while (regexMatcher.find()) {
            if (regexMatcher.group().length() != 0) {
                result.y = Integer.parseInt(regexMatcher.group().trim());
            }
        }
        String phase2 = str2Check.replace(result.y + "", "");
        phase2 = extractRegex(enumRegexGrimoire.number,phase2);
        result.x = Integer.parseInt(phase2);
        return result;
    }

    public String contactRegex(String str2Check) {
        // return a list of all matches
        String theRegex = "(?<=contact)(.*)";
        ArrayList<String> list = new ArrayList<String>();
        Pattern checkRegex = Pattern.compile(theRegex);
        Matcher regexMatcher = checkRegex.matcher(str2Check);
        while (regexMatcher.find()) {
            if (regexMatcher.group().length() != 0) {
                return regexMatcher.group().trim();
            }
        }
        return "";
    }
    public String duplicateRegex(String str2Check) {
        // return a list of all matches
        // String theRegex = "\\b(\\w+)(\\b\\W+\\b\\1\\b)*";
        String theRegex = "\\b([\\w\\s']+) \\1\\b"; // set to 1 repeat of a word like hadoken hadoken
        ArrayList<String> list = new ArrayList<String>();
        Pattern checkRegex = Pattern.compile(theRegex);
        Matcher regexMatcher = checkRegex.matcher(str2Check);
        while (regexMatcher.find()) {
            if (regexMatcher.group().length() != 0) {
                return uniqueWord(regexMatcher.group().trim());
            }
        }
        return "";
    }
    public String uniqueWord(String str) {
        ArrayList<String> list = new ArrayList<String>();
        String s[] = str.split(" ");

        String p = s[0];
        list.add(p);

        for (int i = 1; i < s.length; i++) {

            if (!(p == s[i])) {
                list.add(s[i]);
            }
            p = s[i];
        } // i

        return list.get(0);
    }
    public String afterWord(String word, String str2Check) {
        // return a list of all matches
        String theRegex = "(?<=" + word + ")(.*)";
        ArrayList<String> list = new ArrayList<String>();
        Pattern checkRegex = Pattern.compile(theRegex);
        Matcher regexMatcher = checkRegex.matcher(str2Check);
        while (regexMatcher.find()) {
            if (regexMatcher.group().length() != 0) {
                return regexMatcher.group().trim();
            }
        }
        return "";
    }
    public String firstWord(String str2Check) {
        String arr[] = str2Check.split(" ", 2);
        String firstWord = arr[0]; // the
        return firstWord;
    }
}
