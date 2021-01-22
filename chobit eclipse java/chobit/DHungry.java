package chobit;
import java.util.ArrayList;

public class DHungry extends AbsCmdReq implements Neuronable {
	// basic skill created, lets add it to the living grimoire :D
	/*
	 * this is some new skill I made for the MGTOW Artificial General Intelligence
	 * this agi platform enables the programmer to finish a project with one code
	 * line by simply adding the skill new skills are published to the site, pick
	 * skills and add them to your agi instance ok so watch how easy it is
	 */
	private String reply = "";
	private int powerLv = 100;
	private int threshHold = 60;
	private RegexUtil regexUtil = new RegexUtil();

	@Override
	public void output(Neuron noiron) {
		// TODO Auto-generated method stub
		if (reply != "") {
			AbsAlgPart itte = new APSay(1, reply);
			AbsAlgPart itte2 = new APSay(1, "shiku shiku");
			String representation = "hungry reply";
			ArrayList<AbsAlgPart> algParts1 = new ArrayList<>();
			algParts1.add(itte);
			if (reply.equals("i am hungry")) {
				algParts1.add(itte2);
			}

			Algorithm algorithm = new Algorithm("hungry reply", representation, algParts1);
			noiron.algParts.add(algorithm);
			reply = "";
			return;
		}
		if (powerLv > 90) {
			threshHold = 60;
		}
		if (powerLv < threshHold) {
			threshHold = ((powerLv / 10) - 1) * 10;
			AbsAlgPart itte = new APSay(1, "I am hungry power level at" + threshHold + " percent");
			String representation = "hungry";
			ArrayList<AbsAlgPart> algParts1 = new ArrayList<>();
			algParts1.add(itte);
			Algorithm algorithm = new Algorithm("eat", representation, algParts1);
			noiron.algParts.add(algorithm);
		}
	}

	@Override
	public void input(String ear, String skin, String eye) {
		// TODO Auto-generated method stub
		if (ear.contains("are you hungry")) {
			if (powerLv < 91) {
				reply = "i am hungry";
			} else if (powerLv > 90) {
				reply = "power level over 90";
			}
			return;
		}
		String myString = regexUtil.regexChecker("(\\d+)(?= charge)", skin);
		if (myString != "") {
			powerLv = Integer.parseInt(myString);
		}
	}

}
