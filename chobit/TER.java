package chobit;
public class TER {
	// thousand eyes restrict
	// @ used with Hashtable<String, TER> pegasus = new Hashtable<>();
	// handles a part of an item
	private static final String FEEL = "#";
	private String itemKey = "";
	private String engageOn = "";// see item
	private String toFeel = "";

	public String getItemKey() {
		return itemKey;
	}

	public TER(String itemKey, String engageOn, String toFeel) {
		super();
		this.itemKey = itemKey;
		this.engageOn = engageOn;
		this.toFeel = toFeel;
		if (engageOn.isEmpty()) {
			engageOn = "%&^**()!!@#$";
		}
	}

	public DCStrPair<String> autoEngage(String str1) {
		// send image to grab + grab part
		// or what happens at condition x
		DCStrPair<String> result = new DCStrPair<>();
		if (str1.contains(engageOn) && !engageOn.equals("")) {
			result.key = FEEL;
			result.value = toFeel;
			return result;
		}
		result.key = this.itemKey;
		result.value = str1;
		return result;
	}

	public enumFail summonMutation(String ear, String skin, String eye, enumFail eFail) {
		// if no feel at needed area or no visual of Mcode
		// return fail.mutation
		if (!skin.contains(toFeel) && skin.contains(FEEL) && !eye.contains(engageOn)) {
			return enumFail.requip;
		} // *counter
		return eFail;
	}
}
