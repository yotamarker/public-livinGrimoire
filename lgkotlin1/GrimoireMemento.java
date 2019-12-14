package com.yotamarker.lgkotlin1;

import java.util.Hashtable;

public class GrimoireMemento {
	private Hashtable<String, String> rootToAPNumDic = new Hashtable<>();
	private Hashtable<String, AbsAlgPart> APNumToObjDic = new Hashtable<>();

	public GrimoireMemento() {
		super();
		// load DB to rootToAPNumDic
	}

	public AbsAlgPart load(AbsAlgPart obj) {
		/*
		 * load final mutation from memory of obj
		 */
		String objName = obj.getClass().getSimpleName();
		String objRoot = objName.replaceAll("\\d+", "");
		if (!rootToAPNumDic.containsKey(objRoot)) {
			rootToAPNumDic.put(objRoot, objName);
			return obj;
		}

		if (rootToAPNumDic.get(objRoot).equals(objName)) {
			return obj;
		} else {
			String APNum = rootToAPNumDic.get(objRoot);
			if (APNumToObjDic.containsKey(APNum)) {
				return APNumToObjDic.get(APNum).clone();
			} else {
				loadMutations(obj, objName, objRoot);
				return APNumToObjDic.get(APNum).clone();
			}
		}
	}

	public void reqquipMutation(String mutationAPName) {
		// save mutation
		rootToAPNumDic.put(mutationAPName.replaceAll("\\d+", ""), mutationAPName);
		// save to DB
	}

	private void loadMutations(AbsAlgPart obj, String objName, String objRoot) {
		// make sure all the AP mutation sets of obj are present
		// this assumes the last mutation mutates into the prime mutation
		Mutatable mutant;
		String end = objName;
		do {
			APNumToObjDic.put(obj.getClass().getSimpleName(), obj.clone());
			mutant = (Mutatable) obj;
			obj = mutant.mutation();
		}
		while (!end.equals(obj.getClass().getSimpleName()));
	}
}
