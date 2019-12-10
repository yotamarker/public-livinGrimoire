package com.yotamarker.lgkotlin1;
public abstract class AbsAlgPart {
	// one part of an algorithm, it is a basic simple action or sub goal
	public abstract String action(String ear, String skin, String eye); // return action string
	public abstract Boolean itemize(); // equip with item ?
    public abstract enumFail failure(String input); // failure type
    public abstract Boolean completed(); // has finished ?
    public abstract AbsAlgPart clone();
	public int getMutationLimit() {
		/*
		 * override this to the number of mutations a mutation series can perform, so at
		 * least to 1 if you want mutations enabled.
		 */
		return 0;
	}
}