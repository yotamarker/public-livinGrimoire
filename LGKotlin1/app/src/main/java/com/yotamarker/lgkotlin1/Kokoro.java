package com.yotamarker.lgkotlin1;

import java.util.Hashtable;

/* all action data goes through here
 * detects negatives such as : repetition, pain on various levels and failures
 * serves as a database for memories, convos and alg generations
 * can trigger revenge algs
 * checks for % of difference in input for exploration type algs
 * */
public class Kokoro {
    private String emot = "";

    public String getEmot() {
        return emot;
    }

    public void setEmot(String emot) {
        this.emot = emot;
    }

    Hashtable<String, Integer> pain = new Hashtable<>();
	public GrimoireMemento grimoireMemento;
	public Hashtable<String, String> toHeart = new Hashtable<>();
	public Hashtable<String, String> fromHeart = new Hashtable<>();
	public Boolean standBy = false;
	public Kokoro(AbsDictionaryDB absDictionaryDB) {
		super();
		this.grimoireMemento = new GrimoireMemento(absDictionaryDB);
	}

	public int getPain(String BijuuName) {
		return pain.getOrDefault(BijuuName, 0);
	}

	public void in(Chi chi) {
	}
	public void out(Boolean isCompleted, enumFail failure) {
	}
}
