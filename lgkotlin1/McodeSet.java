package com.yotamarker.lgkotlin1;
import java.util.ArrayList;
import java.util.Random;

public class McodeSet {
	public String name;
	public Mcode activeMcode;
	public ArrayList<Mcode> skills = new ArrayList<>();
	public int marker = 0;

	public void mutateOuter() {
		Random rand = new Random();
		activeMcode = skills.get(rand.nextInt(skills.size()));
	}
}
