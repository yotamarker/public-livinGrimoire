package LG_Core;

import java.util.ArrayList;
import java.util.Hashtable;

public class PersonalityLight {
	/*
	 * this class is used in the ChobitV2 c'tor. it enables loading a complete skill
	 * set (a sub class of the personality class) using 1 line of code. of course
	 * you can also select specific skills to add from the subclasses c'tor. see
	 * also Personality1 for example.
	 */
	protected Kokoro kokoro; // soul
	protected ArrayList<AbsCmdReq> dClassesLv1 = new ArrayList<>();// can engage with anyone
	protected Hashtable<String, Integer> AlgDurations = new Hashtable<>();
	protected Fusion fusion = new Fusion(AlgDurations);

	// fusion.getReqOverload() // an overload of requests on the brain
	// fusion.getRepReq() // someone is negging and asking the same thing over and
	// over again
	/*
	 * flight or fight skills may need access to the above fusion class booleans on
	 * the output methode of a skill this skills will load algorithms to the highest
	 * priority of the noiron which carries algorithms :
	 * noiron.negativeAlgParts.add(Algorithm)
	 */
	public PersonalityLight(AbsDictionaryDB absDictionaryDB) {
		this.kokoro = new Kokoro(absDictionaryDB);
	}

	public PersonalityLight() {
		this.kokoro = new Kokoro(new AbsDictionaryDBShadow());
	}

	public ArrayList<AbsCmdReq> getdClassesLv1() {
		return dClassesLv1;
	}

	public Kokoro getKokoro() {
		return kokoro;
	}

	public Hashtable<String, Integer> getAlgDurations() {
		return AlgDurations;
	}

	public Fusion getFusion() {
		return fusion;
	}
}
