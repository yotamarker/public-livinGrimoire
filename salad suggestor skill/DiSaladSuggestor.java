package com.yotamarker.lgkotlin1;

public class DiSaladSuggestor extends DISkill {
	private int variety = 3;
	private DISkillUtils diSkillUtils = new DISkillUtils();
	private RegexUtil regexUtil = new RegexUtil();
	private SaladSuggestorWDB saladSuggestorWDB;
	private SaladSuggestor saladSuggestor = new SaladSuggestor();
	private String outStr = "";

	public DiSaladSuggestor(Kokoro kokoro) {
		super(kokoro);
		String v1 = kokoro.grimoireMemento.simpleLoad("salad_db");
		String v2 = kokoro.grimoireMemento.simpleLoad("veg_db");
		saladSuggestorWDB = new SaladSuggestorWDB(v1, v2);
	}

	@Override
	public void input(String ear, String skin, String eye) {
		/*
		 * c'tor(Str: ingredients,Str: vegilist) default() suggestSalad(int variety)
		 * addVegi(String vegi) removeVegi(String vegi) addIngredient(String vegi)
		 * removeIngredient(String vegi)
		 */
		if (ear.isEmpty()) {
			return;
		}
		if (ear.equals("suggest a breakfast")) {
			outStr = saladSuggestor.suggestSalad();
			return;
		}
		if (ear.contains("elements")) {
			String numTemp = regexUtil.numberRegex(ear);
			if (numTemp.isEmpty()) {
				return;
			}
			variety = Integer.valueOf(numTemp);
			outStr = "ok";
			return;
		}
		if (ear.equals("suggest a salad")) {
			outStr = saladSuggestorWDB.suggestSalad(variety);
			return;
		}
		if (ear.equals("default salad")) {
			variety = 3;
			outStr = "yes you majesty";
			saladSuggestorWDB.defaultValues();
			kokoro.grimoireMemento.simpleSave("salad_db","");
			kokoro.grimoireMemento.simpleSave("veg_db","");
			return;
		}
		if (ear.contains("is a vegetable")) {
			String veg1 = regexUtil.firstWord(ear);
			kokoro.grimoireMemento.simpleSave("veg_db",saladSuggestorWDB.addVegi(veg1));
			outStr = "yes";
			return;
		}
		if (ear.contains("is not a vegetable")) {
			String veg1 = regexUtil.firstWord(ear);
			kokoro.grimoireMemento.simpleSave("veg_db",saladSuggestorWDB.removeVegi(veg1));
			outStr = "bwahaha";
			return;
		}
		if (ear.contains("does not taste good")) {
			String veg1 = regexUtil.firstWord(ear);
			kokoro.grimoireMemento.simpleSave("salad_db",saladSuggestorWDB.removeIngredient(veg1));
			outStr = "chii";
			return;
		}
		if (ear.contains("tastes good")||ear.contains("taste good")) {
			String veg1 = regexUtil.firstWord(ear);
			kokoro.grimoireMemento.simpleSave("salad_db",saladSuggestorWDB.addIngredient(veg1));
			outStr = "ok";
			return;
		}
	}

	@Override
	public void output(Neuron noiron) {
		if (!outStr.isEmpty()) {
			String temp = outStr;
			outStr = "";
			noiron.algParts.add(diSkillUtils.verbatimGorithm("salad", new APVerbatim(temp)));
		}
	}
}
