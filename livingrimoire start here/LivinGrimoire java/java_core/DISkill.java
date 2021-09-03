package com.yotamarker.lgkotlin1;

public class DISkill extends AbsCmdReq {
	protected Boolean sentAlg = false; // accessed by sub cls
	private Boolean output = false; // accessed by the DISkill container a Bijuu cls
	// String ofSkill;
	protected Kokoro kokoro; // accessed by sub cls

	public void setSentAlg(Boolean sentAlg) {
		this.sentAlg = sentAlg;
	}
	// in sub cls : person ?
	public DISkill(Kokoro kokoro) {
		super();
		// this.ofSkill = ofSkill;
		this.kokoro = kokoro;
	}
	@Override
	public void output(Neuron noiron) {
		// set sentAlg = true if an alg is to be sent

	}


	@Override
	public void input(String ear, String skin, String eye) {
		// TODO Auto-generated method stub

	}

	public Boolean getOutput() {
		Boolean result = this.output;
		this.output = false;
		return result;
	}

	public void setOutput(Boolean output) {
		this.output = output;
	}

	public Boolean getSentAlg() {
		Boolean result = this.sentAlg;
		this.sentAlg = false;
		return result;
	}
}
