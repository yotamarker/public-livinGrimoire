package chobit;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// returns expression of type theRegex from the string str2Check
public class RegexUtil {
    public String regexChecker(String theRegex, String str2Check) {
        Pattern checkRegex = Pattern.compile(theRegex);
        Matcher regexMatcher = checkRegex.matcher(str2Check);
        while (regexMatcher.find()) {
            if (regexMatcher.group().length() != 0) {
                return regexMatcher.group().trim();
            }
        }
        return "";
    }

	public ArrayList<String> regexChecker2(String theRegex, String str2Check) {
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
}
