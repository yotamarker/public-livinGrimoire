package chobit;

import java.util.Hashtable;

public class GrimoireMemento {
	private Hashtable<String, String> rootToAPNumDic = new Hashtable<>();
	private Hashtable<String, AbsAlgPart> APNumToObjDic = new Hashtable<>();
	private AbsDictionaryDB absDictionaryDB;

	public GrimoireMemento(AbsDictionaryDB absDictionaryDB) {
		super();
		this.absDictionaryDB = absDictionaryDB;
	}

	public AbsAlgPart load(AbsAlgPart obj) {
		/*
		 * load final mutation from memory of obj
		 */
		String objName = obj.getClass().getSimpleName();
		String objRoot = objName.replaceAll("\\d+", "");
		// if not in active DB try adding from external DB
		if (!rootToAPNumDic.containsKey(objRoot)) {
			String temp = this.absDictionaryDB.load(objRoot);
			if (this.absDictionaryDB.getExistsInDB()) {
				rootToAPNumDic.put(objRoot, temp);
			}
		}
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
		this.absDictionaryDB.save(mutationAPName.replaceAll("\\d+", ""), mutationAPName);
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
