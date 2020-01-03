package com.yotamarker.lgkotlin1;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

public class Joker {
	/*
	 * extend me and add sentences and lists for parameters in the sentences in the
	 * sub classes c'tor. see JokerIncel class for exammple see Joker Util class for
	 * editing a large amount of data faster B4 adding it in the subclasses c'tor
	 */
	protected ArrayList<String> sentences = new ArrayList<String>();
	protected Hashtable<String, ArrayList<String>> wordToList = new Hashtable<>();
	protected Random rand = new Random();
	private RegexUtil regexUtil = new RegexUtil();
	public Joker() {
		super();
	}

	public String generateJoke() {
		int x = rand.nextInt(sentences.size());
		String result = sentences.get(x);
		return clearRecursion(result);
	}

	private String clearRecursion(String result) {
		int x;
		ArrayList<String> params = new ArrayList<String>();
		params = regexUtil.regexChecker2("(\\w+)(?= #)", result);
		for (String strI : params) {
			ArrayList<String> temp = wordToList.get(strI);
			int n = temp.size();
			x = rand.nextInt(n);
			String s1 = temp.get(x);
			result = result.replace(strI + " #", s1);
		}
		if (!result.contains("#")) {
			return result;
		} else {
			return clearRecursion(result);
		}
		// return "";
	}
}
