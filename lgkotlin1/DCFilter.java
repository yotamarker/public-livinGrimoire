package com.yotamarker.lgkotlin1;
import java.util.HashSet;

// data class (DC). just a variable.
public class DCFilter {
	public HashSet<String> danger = new HashSet<>();
	public Boolean imutable = true;
	public String prevKey = "";
	public int ignoreCounter = 0;
	public DCStrPair<String> pair = new DCStrPair<>();;
	public int actioNum = 0;

	public DCFilter() {
		super();
		this.pair.key = "";
		this.pair.value = "";
	}

}
