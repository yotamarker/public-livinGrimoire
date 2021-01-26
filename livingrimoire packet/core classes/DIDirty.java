package com.yotamarker.lgkotlin1;

import java.util.ArrayList;

public class DIDirty extends DISkill {
	private Boolean exeAlg = false;

	public DIDirty(Kokoro kokoro) {
		super(kokoro);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void input(String ear, String skin, String eye) {
		if (ear.contains("horny")) {
			this.exeAlg = true;
			this.setSentAlg(true);
		}
	}

	@Override
	public void output(Neuron noiron) {
		if (this.exeAlg) {
			AbsAlgPart AP1 = new Chi(this.kokoro, this.getClass().getSimpleName(), new APSay(1, "cum toilet"));
			AbsAlgPart AP2 = new Chi(this.kokoro, this.getClass().getSimpleName(), new APFilth1());
			String representation = "dirty";
			ArrayList<AbsAlgPart> algParts1 = new ArrayList<>();
			algParts1.add(AP1);
			algParts1.add(AP2);
			Algorithm algorithm = new Algorithm("dirty", representation, algParts1);
			noiron.algParts.add(algorithm);
			exeAlg = false;
		}
	}
}
