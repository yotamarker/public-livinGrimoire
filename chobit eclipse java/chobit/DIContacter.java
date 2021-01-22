package chobit;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.Set;

public class DIContacter extends DISkill {
	private Hashtable<String, String> friends = new Hashtable<>(); // key : name ,value: phone number
	private Hashtable<String, String> personUtil = new Hashtable<>(); // skill, personUtil name
	private Hashtable<String, Integer> rank = new Hashtable<>(); // name, rank
	// map : name, location
	// personVisual : visual representation Str, name : dictionary
	private String activePerson = "";
	private Hashtable<String, String> phoneBook = new Hashtable<>();// name, phone number
	private int algMode = 0;
	private int outputAlg = 0;
	private TimeGate timeGate = new TimeGate();
	private DISkillUtils diSkillUtil = new DISkillUtils();
	private RegexUtil regexUtil = new RegexUtil();
	private String tempStr = "";
	private String lastSummoned = "";
	private String lastSkill = "";
	// burst alg vars
	private String[] conditions = new String[3];
	// SMS shoud use #prefix
	private String myNumber = "0506667788";
	public DIContacter(Kokoro kokoro) {
		super(kokoro);
		Arrays.fill(conditions, "");
	}

	@Override
	public void input(String ear, String skin, String eye) {
		// data provision to external persons :
		if (ear.contains("what is your number")) {
			Set<String> keys = personUtil.keySet();
			outputAlg = 9002;
			for (String key : keys) {
				if (personUtil.get(key).isEmpty()) {
					outputAlg = 9001;
					break;
				}
				;
			}
		}
		// ***test initial summon
		/*
		 * this if is to be deleted and instead use, see marker 1
		 */
		if (ear.contains("t1")) {
			algMode = 400;
			timeGate.close(9);
			outputAlg = 400;
		}
		// add contact like in the show chobits
		if (ear.contains("contact")) {
			tempStr = this.regexUtil.contactRegex(ear);
			this.algMode = 11;
			timeGate.close(2);
		}
		// handle order from soul(other diskill)
		String order = kokoro.toHeart.getOrDefault("DIContacter", "");
		// if (order.contains("sms")) {
		if (order.contains("sms")) {
			// external skill can summon smsing
			String target = regexUtil.afterWord("sms", order);
			if (phoneBook.getOrDefault(target, "").isEmpty()) {
				outputAlg = 100;
			} else {
				lastSummoned = target;
				outputAlg = 101;
			}
		} else {
		switch (order) {
		case "ok":
			rank.put(lastSummoned, 2);
			// diskilled alg was successful, the friend should rank up
		case "fail":
			// diskilled alg was a failure, the friend should rank down
			int temp = rank.get(lastSummoned);
			temp--;
			if (temp < 1) {
				rank.remove(lastSummoned);
				personUtil.remove(lastSkill);
			}
			break;
		default:
			// summon or set time to make friend
			if (!personUtil.getOrDefault(order, "").isEmpty() && !order.isEmpty()) {
				outputAlg = inviteOrGoTo(order);
				lastSummoned = friends.get(personUtil.get(order));
				lastSkill = order;
			} else {// make a friend
				/*
				 * marker 1 see algmode = 400 can also schedule a time to run friend make alg
				 */
			}

			break;
			}
		}
		// alg set up
		switch (algMode) {
		case 11:
			if (!this.timeGate.isClosed()) {
				algMode = 0;
			} else {
				String number = regexUtil.phoneRegex1(ear);
				if (!number.isEmpty()) {
					phoneBook.put(tempStr, number);
					outputAlg = 1;
				}
			}
			break;
		case 400:
		case 401:
		case 402:
			if (!this.timeGate.isClosed()) {
				algMode = 0;
			} else {
				if (eye.contains("movement")) {
					conditions[0] = "true";
				}
				String regexTemp = ""; // check on data fields with overide for new regex compatible inputs
				regexTemp = regexUtil.afterWord("name is", ear);
				if (regexTemp != "") {
					conditions[1] = regexTemp;
				} // per condition
				regexTemp = regexUtil.phoneRegex1(ear);
				if (regexTemp != "") {
					conditions[2] = regexTemp;
				} // per condition
				if (!conditions[algMode % 100].isEmpty()) {
					algMode = nextAlg();
					outputAlg = algMode;
					int save = 400 + conditions.length - 1;
					if (save == 402) {
						friends.put(conditions[1], conditions[2]);
						phoneBook.put(conditions[1], conditions[2]);
					}
				}
			}
			break;
		default:
			break;
		}
	}

	private int nextAlg() {
		int x = algMode % 100;
		for (int i = algMode % 100; i < conditions.length; i++) {
			if (!conditions[i].isEmpty()) {
				x = i + 1;
			} else {
				break;
			}
		}
		x = algMode - algMode % 100 + x;
		return x;
	}
	@Override
	public void output(Neuron noiron) {
		switch (outputAlg) {
		case 1:
			noiron.algParts.add(diSkillUtil
					.verbatimGorithm(new APVerbatim("contact added")));
			outputAlg = 0;
			break;
		case 2:
			noiron.algParts.add(diSkillUtil.verbatimGorithm(new APVerbatim("invited " + lastSummoned)));
			outputAlg = 0;
			break;
		case 3:
			// inform, go to + cancel if requested, sms #prefix
			noiron.algParts.add(diSkillUtil.verbatimGorithm(new APVerbatim("asked to go to  " + lastSummoned)));
			outputAlg = 0;
			break;
		case 100:
			noiron.algParts.add(diSkillUtil.verbatimGorithm(new APVerbatim("cope, not in my contacts")));
			outputAlg = 0;
			break;
		case 101:
			noiron.algParts.add(diSkillUtil.verbatimGorithm(new APVerbatim("smsing", lastSummoned)));
			outputAlg = 0;
			break;
		case 400:
			// inform, go to + cancel if requested, sms #prefix
			noiron.algParts.add(diSkillUtil.verbatimGorithm(new APVerbatim("seeking friend")));
			outputAlg = 0;
			break;
		case 401:
			// inform, go to + cancel if requested, sms #prefix
			noiron.algParts.add(diSkillUtil.verbatimGorithm(new APVerbatim("what is your name")));
			outputAlg = 0;
			break;
		case 402:
			// inform, go to + cancel if requested, sms #prefix
			noiron.algParts.add(diSkillUtil.verbatimGorithm(new APVerbatim("what is your number")));
			outputAlg = 0;
			break;
		case 403:
			// inform, go to + cancel if requested, sms #prefix
			noiron.algParts.add(diSkillUtil.verbatimGorithm(new APVerbatim("aquired friend")));
			outputAlg = 0;
			break;
		case 9001:
			// inform, go to + cancel if requested, sms #prefix
			noiron.algParts.add(diSkillUtil.verbatimGorithm(new APVerbatim(this.myNumber)));
			outputAlg = 0;
			break;
		case 9002:
			// inform, go to + cancel if requested, sms #prefix
			noiron.algParts.add(diSkillUtil.verbatimGorithm(new APVerbatim("I don't need a friend at the moment")));
			outputAlg = 0;
			break;
		default:
			break;
		}
	}

	private int inviteOrGoTo(String diskill) {
		switch (diskill) {
		case "ditraveler":
			return 3;// go to
		default:
			return 2;// invite
		}
	}
}
