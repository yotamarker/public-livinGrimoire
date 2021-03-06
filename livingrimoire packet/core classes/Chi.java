package com.yotamarker.lgkotlin1;

public class Chi extends AbsAlgPart implements Mutatable {
	/*
	 * an adaptor pattern to the alg part, it also has the kokoro consiousness
	 * object to be aware throughout the program of what is happening all action
	 * data goes through this soul.
	 */
	public Kokoro kokoro;
	public String ofSkill;
	public AbsAlgPart aPart;

	public Chi(Kokoro kokoro, String ofSkill, AbsAlgPart aPart) {
		super();
		this.kokoro = kokoro;
		this.ofSkill = ofSkill;
		this.aPart = kokoro.grimoireMemento.load(aPart);
	}

	public String actualAction(String ear, String skin, String eye) {
		return aPart.action(ear, skin, eye);
	}
	@Override
	public String action(String ear, String skin, String eye) {
		kokoro.in(this);
		String result = actualAction(ear, skin, eye);
		kokoro.out(completed(), failure(""));
		return result;
	}

	@Override
	public Boolean itemize() {
		// TODO Auto-generated method stub
		return aPart.itemize();
	}

	@Override
	public enumFail failure(String input) {
		// TODO Auto-generated method stub
		return aPart.failure(input);
	}

	@Override
	public Boolean completed() {
		// TODO Auto-generated method stub
		return aPart.completed();
	}

	@Override
	public AbsAlgPart clone() {
		// TODO Auto-generated method stub
		return new Chi(kokoro, this.ofSkill, aPart.clone());
	}
	@Override
	public int getMutationLimit() {
		// TODO Auto-generated method stub
		return aPart.getMutationLimit();
	}

	@Override
	public AbsAlgPart mutation() {
		Mutatable mutant = (Mutatable) aPart;
		AbsAlgPart tempAP = mutant.mutation();
		kokoro.grimoireMemento.reqquipMutation(tempAP.getClass().getSimpleName());
		return new Chi(kokoro, this.ofSkill, tempAP);
	}

	@Override
	public String myName() {
		// TODO Auto-generated method stub
		return aPart.myName();
	}
}
