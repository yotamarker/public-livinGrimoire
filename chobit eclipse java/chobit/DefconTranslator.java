package chobit;

import java.util.ArrayList;
import java.util.Stack;

public class DefconTranslator {
	private String input = "";
	private Stack<String> inputStack = new Stack<String>();
	private ArrayList<String> extraDefcons = new ArrayList<String>();// extra defcons
	private ZeroTimeGate zeroTimeGate = new ZeroTimeGate(1);
	private RegexUtil regexUtil = new RegexUtil();
	private String target = "";

	public DefconTranslator(ArrayList<String> extraDefcons) {
		super();
		this.extraDefcons = extraDefcons;
	}

	public String getSpecificDefcom(String ear, String skin, String eye) {
		for (String item : extraDefcons) {
			if (eye.contains(item)) {
				return item;
			}
		}
		for (String item : extraDefcons) {
			if (skin.contains(item)) {
				return item;
			}
		}
		for (String item : extraDefcons) {
			if (ear.contains(item)) {
				return item;
			}
		}
		return "";
	}

	public String aquiredTarget() {
		// for (String item : inputStack) {
		// if (eye.contains(item)) {
		// return item;
		// }
		// }
		// for (String item : inputStack) {
		// if (skin.contains(item)) {
		// return item;
		// }
		// }
		// for (String item : inputStack) {
		// if (ear.contains(item)) {
		// return item;
		// }
		// }
		return target;
	}
	// get unique defcon
	public int getDefcon(String ear, String skin, String eye) {
		// override this methode in subclass per skill
		this.target = "";
		if (zeroTimeGate.isClosed()) {
			if (listInStrEye(ear, skin, eye)) {
				zeroTimeGate.open();
				target = regexUtil.firstWord(eye);
				inputStack.push(target);
				return 1;
			}
			if (listInStrEar(ear, skin, eye)) {
				zeroTimeGate.open();
				target = regexUtil.afterWord("it is", ear);
				if (!target.isEmpty()) {
					inputStack.push(target);
				}
				return 2;
			}
			if (listInStrLearnedEar(ear, skin, eye)) {
				zeroTimeGate.open();
				// set target from regex
				// send alg
				// stuck clear;return alg
				target = "deduced: " + regexUtil.afterWord("it is", ear);
				if (target.isEmpty()) {
					return 3;
				}
				inputStack.clear();
			}
			if (listInStrLearnedEye(ear, skin, eye)) {
				zeroTimeGate.open();
				target = "deduced: " + regexUtil.firstWord(eye);
				inputStack.clear();
				return 4;
				// set target from regex
				// send alg
				// stuck clear;return alg
			}
		}
		return 0;
	}

	private Boolean listInStrEye(String ear, String skin, String eye) {
		for (String string : extraDefcons) {
			if (skin.contains(string)) {
				return true;
			}
		}
		for (String string : extraDefcons) {
			if (eye.contains(string)) {
				return true;
			}
		}
		return false;
	}

	private Boolean listInStrEar(String ear, String skin, String eye) {
		for (String string : extraDefcons) {
			if (ear.contains(string)) {
				return true;
			}
		}
		return false;
	}

	private Boolean listInStrLearnedEar(String ear, String skin, String eye) {

		for (String string : inputStack) {
			if (ear.contains(string)) {
				return true;
			}
		}
		return false;
	}

	private Boolean listInStrLearnedEye(String ear, String skin, String eye) {
		for (String string : inputStack) {
			if (eye.contains(string)) {
				return true;
			}
		}
		return false;
	}
}
