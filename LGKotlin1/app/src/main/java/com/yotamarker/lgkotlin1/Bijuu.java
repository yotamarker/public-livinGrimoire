package com.yotamarker.lgkotlin1;

import java.util.ArrayList;

public class Bijuu extends AbsCmdReq {
	/*
	 * a container cls for a list of DIskills
	 */
	protected ArrayList<DISkill> dSkills = new ArrayList<>();
	private Kokoro kokoro;
	final int constTolerance = 3;
	private int tolerance = constTolerance;
	private Boolean enabled = true;
	private Boolean fastBreak = true;
	protected Boolean isAutomatic = false;
	protected Person person;

	public Bijuu(Person master, Kokoro kokoro, DISkill... skills) {
		super();
		this.kokoro = kokoro;
		this.person = person;
		for (DISkill i : skills) {
			dSkills.add(i);
		}
		for (DISkill i : skills) {
			if (i.auto()) {
				isAutomatic = true;
				break;
			}
		}
	}

	public void modeFlip() {
		// pain = *repetition/ actual high level pain
		// sets weather the Bijuu is active or not
		tolerance -= kokoro.getPain(this.getClass().getSimpleName());
		if (tolerance < 1) {
			this.enabled = !this.enabled;
		}
	}

	@Override
	public void input(String ear, String skin, String eye) {
		if (enabled) {
			// if Bijuu enabled
			for (DISkill dISkill : dSkills) {
				dISkill.input(ear, skin, eye);
				if (dISkill.getSentAlg()) {
					/*
					 * found an alg ! exit the loop ! I dont need another alg !!
					 */
					dISkill.setOutput(true);
					// hey, DIskill, remind me you have an alg waiting for pickup
					fastBreak = false;
					// dont skip alg pick up stage.
					break;
				}
			}
		}
		else {
			reenable(ear, skin, eye); // maybe I should be revived
		}
	}

	@Override
	public void output(Neuron noiron) {
		// TODO Auto-generated method stub
		if (!fastBreak) {
			// if alg waiting for pick up
			fastBreak = true; // reset
			for (DISkill dISkill : dSkills) {
				if (dISkill.getOutput()) {
					// found the alg
					dISkill.output(noiron);
					// OK done, bye
					break;
				}
			}
		}
	}

	public void reenable(String ear, String skin, String eye) {
		if (ear.contains("pain") || skin.contains("pain")) {
			tolerance -= 1;
			if (tolerance < 1) {
				this.enabled = true;
			}
		}
	}

	@Override
	public Boolean auto() {
		// TODO Auto-generated method stub
		return isAutomatic;
	}
}
