package chobit;

import java.util.ArrayList;

public class RUSureGate {
	// usage : if !ruSureGate.loadBullet("").isEmpty boolean = getUnlocked?
	private Boolean unlocked = false;
	private String[] resignations;
	private ZeroTimeGate tGate = new ZeroTimeGate(0);
	private String init = "";
	private String response1 = "";
	private int i = -1;
	private ArrayList<String> affirmations = new ArrayList<String>();
	// add confirmations list instead of yes
	public RUSureGate(String response1, String init, String... resignations) {
		this.resignations = resignations.clone();
		this.init = init;
		this.response1 = response1;
		affirmations.add("yes");
	}

	public void setAffirmations(String... affirmations) {
		this.affirmations.clear();
		this.affirmations.add("yes");
		for (int i = 0; i < affirmations.length; i++) {
			this.affirmations.add(affirmations[i]);
		}
	}
	public String loadBullet(String input) {
		if (input.contains(init)) {
			tGate.open(2);
			return response1;
		}
		if (tGate.isClosed() || unlocked) {
			unlocked = false;
			i = -1;
			return "";
		} else if (strContainsList(input, affirmations)) // tGate is open
		{
			i++;
			if (i == resignations.length - 1) {
				unlocked = true;
			}
			return resignations[i];
		}
		return "";
	}

	public Boolean getUnlocked() {
		return unlocked;
	}

	public static Boolean strContainsList(String str1, ArrayList<String> items) {
		for (String temp : items) {
			if (str1.contains(temp)) {
				return true;
			}
		}
		return false;
	}

}
