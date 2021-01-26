package com.yotamarker.lgkotlin1;

import java.util.ArrayList;
import java.util.Random;

public class DIGamer extends DISkill {
	private int mode = 0;
	private Random rand = new Random();
	public DIGamer(Kokoro kokoro) {
		super(kokoro);
	}

	@Override
	public void input(String ear, String skin, String eye) {
		switch (ear) {
		case "coin toss":
			mode = 1;
			break;
		case "dice roll":
			mode = 2;
			break;
		default:
			break;
		}
	}

	@Override
	public void output(Neuron noiron) {
		switch (this.mode) {
		case 1:
			int x = rand.nextInt(2);
			String sTemp = x == 0 ? "heads" : "tails";
			noiron.algParts.add(verbatimGorithm(new APVerbatim(sTemp)));
			mode = 0;
			break;
		case 2:
			int x2 = rand.nextInt(6) + 1;
			String sTemp2 = x2 + "";
			noiron.algParts.add(verbatimGorithm(new APVerbatim(sTemp2)));
			mode = 0;
			break;
		default:
			break;
		}

}

	private Algorithm verbatimGorithm(AbsAlgPart itte) {
		// returns a simple algorithm for saying sent parameter
		// AbsAlgPart itte = new APVerbatim("I am");
		String representation = "about";
		ArrayList<AbsAlgPart> algParts1 = new ArrayList<>();
		algParts1.add(itte);
		Algorithm algorithm = new Algorithm("about", representation, algParts1);
		return algorithm;
	}
}
