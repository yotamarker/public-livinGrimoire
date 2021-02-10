package com.yotamarker.lgkotlin1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class SaladSuggestorWDB {
	private HashSet<String> ingredientsSet = new HashSet();
	private HashSet<String> vegiSet = new HashSet();

	public SaladSuggestorWDB(String ingredientsString, String vegiString) {
		if (ingredientsString.isEmpty() || ingredientsString.equals("null")) {
			defaultValues();
			return;
		}
		// c'tor from strings of DB
		String[] strParts = ingredientsString.split(",");
		// convert array to the List using asList method
		List<String> listParts = Arrays.asList(strParts);
		// create HashSet from the List using constructor
		ingredientsSet = new HashSet<String>(listParts);
		// vegiList population :
		String[] strParts2 = vegiString.split(",");
		// convert array to the List using asList method
		List<String> listParts2 = Arrays.asList(strParts2);
		// create HashSet from the List using constructor
		vegiSet = new HashSet<String>(listParts2);
	}

	public String defaultValues() {
		ingredientsSet = new HashSet();
		vegiSet = new HashSet();
		ingredientsSet.add("tomatoe");
		ingredientsSet.add("cucumber");
		ingredientsSet.add("onion");
		ingredientsSet.add("carrot");
		vegiSet.add("tomatoe");
		vegiSet.add("cucumber");
		vegiSet.add("onion");
		vegiSet.add("carrot");
		return "null";
	}
	public String suggestSalad(int variety) {
		// anti glitch variety size
		if (variety > ingredientsSet.size() || variety < 1) {
			variety = ingredientsSet.size();
		}
		// get unique random numbers :
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < ingredientsSet.size(); i++) {
			list.add(new Integer(i));
		}
		Collections.shuffle(list);
		String result = "";
		// convert resulting unique numbers to salad ingredients
		for (int i = 0; i < variety - 1; i++) {
			int x = list.get(i);
			result += getIngredient(x) + " ";
		}
		int x = list.get(variety - 1);
		result += "and " + getIngredient(x);
		return result;
	}

	private String getIngredient(int num) {
		String[] ingredients = new String[ingredientsSet.size()];
		ingredientsSet.toArray(ingredients);
		return ingredients[num];
	}

	public String addVegi(String vegi) {
		vegiSet.add(vegi);
		return hashsetToString(vegiSet);
	}

	public String removeVegi(String vegi) {
		vegiSet.remove(vegi);
		return hashsetToString(vegiSet);
	}

	public String addIngredient(String vegi) {
		if (!vegiSet.contains(vegi)) {
			return "";
		}
		ingredientsSet.add(vegi);
		return hashsetToString(ingredientsSet);
	}

	public String removeIngredient(String vegi) {
		ingredientsSet.remove(vegi);
		return hashsetToString(ingredientsSet);
	}

	private String hashsetToString(HashSet<String> hs) {
		String result = "";
		for (String string : hs) {
			result += ",";
			result += string;
		}
		result = result.replaceFirst(",", "");
		return result;
	}
}
