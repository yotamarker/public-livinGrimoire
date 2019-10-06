package com.yotamarker.lgkotlin1;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

/* represents all possible requipable items
 * activeSkills : type of item of item set for example :
 * bicycle with chain lock of transport items
 * */
public class GameShark {
	public Hashtable<String, McodeSet> allSkills = new Hashtable<>();
	public Hashtable<String, Mcode> activeSkills = new Hashtable<>();

	public Algorithm requipAlg = new Algorithm("disabled","", null);
	public Hashtable<String, TER> pegasus;
	public GameShark() {
		super();
		createMCodeSet("APSay", createMCode(new TER("nyaa", "", ""), new APSummonSkill(1, "kuchiyouse no jutsu")));
		addMCode("APSay", createMCode(new TER("buhi", "", ""), new APSummonSkill(1, "kuchiyouse no jutsu")));
	}

	public void setGameShark() {
		pegasus = new Hashtable<>();
		Set<String> keys = activeSkills.keySet();
        for(String key: keys){
			pegasus.put(key, activeSkills.get(key).ter);
        }
		if (pegasus.isEmpty()) {
		}
		reset();
	}
	public void addMcode(String AP) {
		Boolean boolean1 = allSkills.containsKey(AP);
		if (boolean1) {
			activeSkills.put(AP, allSkills.get(AP).activeMcode);
		if(requipAlg.getGoal().equals("disabled")) {requipAlg = allSkills.get(AP).activeMcode.algorithm;}
		else{requipAlg.getAlgParts().addAll(allSkills.get(AP).activeMcode.algorithm.getAlgParts());}}
	}
	public Algorithm getRequipAlg() {
        return requipAlg;
	}

	private void reset() {
		activeSkills = new Hashtable<>();
		requipAlg = new Algorithm("disabled","", null);
	}

	public DCStrPair<String> autoEngage(String AP, String input) {
		DCStrPair<String> result = new DCStrPair<String>();
		if (pegasus.containsKey(AP)) {
			result = pegasus.get(AP).autoEngage(input);
		} else {
			result.key = "itemLess";
			result.value = input;
		}
		return result;
	}

	public void outerMutate(String APCls) {
		if (allSkills.containsKey(APCls)) {
			if (!allSkills.get(APCls).skills.isEmpty()) {
				allSkills.get(APCls).marker++;
				if (allSkills.get(APCls).marker > allSkills.get(APCls).skills.size() - 1) {
					allSkills.get(APCls).marker = 0;
				}
				int x = allSkills.get(APCls).marker;
				allSkills.get(APCls).activeMcode = allSkills.get(APCls).skills.get(x);
			}
		}
	}

	public enumFail finalMutation(String AP, String ear, String skin, String eye, enumFail fail1) {
		enumFail result = fail1;
		if (this.pegasus.containsKey(AP)) {
			result = pegasus.get(AP).summonMutation(ear, skin, eye, fail1);
		}
		return result;
	}

	private void createMCodeSet(String AP, Mcode activeMCode) {
		McodeSet mcodeSet1 = new McodeSet();
		mcodeSet1.skills.add(activeMCode);
		mcodeSet1.activeMcode = activeMCode;
		mcodeSet1.name = "APSay";
		allSkills.put(mcodeSet1.name, mcodeSet1);
	}

	private Mcode createMCode(TER ter1, AbsAlgPart aPart) {
		Mcode mcode1 = new Mcode();
		mcode1.ter = ter1;
		ArrayList<AbsAlgPart> algParts1 = new ArrayList<>();
		algParts1.add(aPart);
		mcode1.algorithm = new Algorithm(ter1.getItemKey(), ter1.getItemKey(), algParts1);
		return mcode1;
	}

	private void addMCode(String AP, Mcode mCodeNxt) {
		allSkills.get(AP).skills.add(mCodeNxt);
	}
}
