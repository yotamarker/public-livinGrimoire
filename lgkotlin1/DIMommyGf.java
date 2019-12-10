package com.yotamarker.lgkotlin1;

import java.util.ArrayList;

public class DIMommyGf extends DISkill {
	public Person master;
	private Boolean exeAlg;

	public DIMommyGf(Kokoro kokoro, Person owner) {
		super(kokoro);
		this.master = owner;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void input(String ear, String skin, String eye) {
		if (ear.contains("imprint master")) {
			this.exeAlg = true;
			this.setSentAlg(true);
		}
	}

	@Override
	public void output(Neuron noiron) {
		// TODO Auto-generated method stub

		if (this.exeAlg) {
			AbsAlgPart itte = new Chi(this.kokoro, this.getClass().getSimpleName(), new APImprintMaster(this.master));
			String representation = "imprintmaster";
			ArrayList<AbsAlgPart> algParts1 = new ArrayList<>();
			algParts1.add(itte);
			Algorithm algorithm = new Algorithm("imprintmaster", representation, algParts1);
			noiron.algParts.add(algorithm);
			exeAlg = false;
		}
	}
}
