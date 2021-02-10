package com.yotamarker.lgkotlin1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class SaladSuggestor {
	private HashSet<String> ingredientsSet = new HashSet();

	public SaladSuggestor() {
		ingredientsSet.add("tuna mayo and hot pepper");
		ingredientsSet.add("omolet");
		ingredientsSet.add("avocado and fried shrooms");
		ingredientsSet.add("hummus with shrooms");
		ingredientsSet.add("hummus with zhug");
		ingredientsSet.add("egg salad with mayo and dill");
		ingredientsSet.add("shakshuka");
		ingredientsSet.add("tahini cookies with green tea");
		ingredientsSet.add("pesto pasta");
	}

	public String suggestSalad() {
		// anti glitch variety size
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < ingredientsSet.size(); i++) {
			list.add(new Integer(i));
		}
		Collections.shuffle(list);
		int x = list.get(0);
		return getIngredient(x);
	}

	private String getIngredient(int num) {
		// antiglitch
		String[] ingredients = new String[ingredientsSet.size()];
		ingredientsSet.toArray(ingredients);
		return ingredients[num];
	}
}
