package chobit;

import java.util.ArrayList;
import java.util.HashMap;

public class InstaConvo {
	private ZeroTimeGate timeGate = new ZeroTimeGate();
	private ArrayList<String> contexts = new ArrayList<String>();
	private String activeContext = "";
	private HashMap<String, String> pairs = new HashMap<>();

	public InstaConvo() {
		super();
		pairs.put("", "");
	}

	public InstaConvo loadBullet(String context, String in1, String reply) {
		contexts.add(context);
		pairs.put(context + in1, reply);
		return this;
	}

	public InstaConvo loadBullet(String in1, String reply) {
		pairs.put(in1, reply);
		return this;
	}

	public String converse(String ear) {
		String temp = strContains(ear);
		if (!temp.isEmpty()) {
			activeContext = temp;
			timeGate.open(2);
			return pairs.getOrDefault(ear, "");
		}
		if (!timeGate.isOpen()) {
			activeContext = "";
		}
		if (!ear.isEmpty()) {
			String tempest = pairs.getOrDefault(activeContext + ear, "");
			if (tempest.isEmpty()) {
				tempest = pairs.getOrDefault(ear, "");
			}
			return tempest;
		}
		return "";
	}

	private String strContains(String ear) {
		for (String temp : contexts) {
			if (ear.contains(temp)) {
				return temp;
			}
		}
		return "";
	}

	private void clearMemory() {
		pairs.clear();
		contexts.clear();
	}
}
