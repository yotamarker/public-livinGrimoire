package com.yotamarker.lgkotlin1;


import java.awt.Point;
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

	public String numberRegex(String str2Check) {
		String theRegex = "[-+]?[0-9]{1,13}(\\.[0-9]*)?";
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

	public String timeStampRegex(String str2Check) {
		String theRegex = "(([0-9]|[0-1][0-9]|[2][0-3]):([0-5][0-9])$)|(^([0-9]|[1][0-9]|[2][0-3])$)";
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
	public String intRegex(String str2Check) {
		String theRegex = "[-+]?[0-9]{1,13}";
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
		phase2 = numberRegex(phase2).replaceAll("[^0-9]", "");
		if (phase2.isEmpty()) {
			return new Point(result.y, 0);
		}
		result.x = Integer.parseInt(phase2);
		return result;
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

	public String emailRegex(String str2Check) {
		// return a list of all matches
		String theRegex = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
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

	public String between(String word, String word2, String str2Check) {
		// return a list of all matches
		String theRegex = "(?<=" + word + ")(.*)(?=" + word2 + ")";
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
	public String phoneRegex1(String str2Check) {
		return regexChecker("[0]\\d{2}\\d{4}\\d{3}$", str2Check);
	}

	public String phoneRegex2(String str2Check) {
		return regexChecker("[0]\\d{9}", str2Check);
	}
	public String firstWord(String str2Check) {
		String arr[] = str2Check.split(" ", 2);
		String firstWord = arr[0]; // the
		return firstWord;
	}
}

