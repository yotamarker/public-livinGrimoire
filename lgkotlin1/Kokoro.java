package com.yotamarker.lgkotlin1;

import java.util.Hashtable;

/* all action data goes through here
 * detects negatives such as : repetition, pain on various levels and failures
 * serves as a database for memories, convos and alg generations
 * can trigger revenge algs
 * checks for % of difference in input for exploration type algs
 * */
public class Kokoro {
	Hashtable<String, Integer> pain = new Hashtable<>();

	public int getPain(String BijuuName) {
		return pain.getOrDefault(BijuuName, 0);
	}

	public void in(Chi chi) {
	}
	public void out(Boolean isCompleted, enumFail failure) {
	}
}
