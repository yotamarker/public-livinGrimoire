package com.yotamarker.lgkotlin1;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;

import java.util.ArrayList;

public class DIJoker extends DISkill {
	private byte algToSend = 0;
	private Joker joker = new JokerIncel();

	public DIJoker(Kokoro kokoro) {
		super(kokoro);
	}

	@Override
	public void input(String ear, String skin, String eye) {
		switch (ear) {
		case "talk":
			algToSend = 1;
			setSentAlg(true);
			break;
		case "speak":
			algToSend = 1;
			setSentAlg(true);
			break;

		case "joke":
			algToSend = 1;
			setSentAlg(true);
			break;
		default:
			break;
		}
	}

	@Override
	public void output(Neuron noiron) {
		switch (this.algToSend) {
		case 1:
			this.algToSend = 0;
			noiron.algParts.add(getJoke());
			break;

		default:
			break;
		}
	}

	private Algorithm getJoke() {
		AbsAlgPart itte = new Chi(this.kokoro, this.getClass().getSimpleName(),
				new APSay(1, this.joker.generateJoke()));
		String representation = "joke";
		ArrayList<AbsAlgPart> algParts1 = new ArrayList<>();
		algParts1.add(itte);
		Algorithm algorithm = new Algorithm("joke", representation, algParts1);
		return algorithm;
	}
}
