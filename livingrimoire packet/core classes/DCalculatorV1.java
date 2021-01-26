package com.yotamarker.lgkotlin1;

import java.util.ArrayList;

public class DCalculatorV1 extends AbsCmdReq implements Neuronable {
	private StrCalculatorV2 strCalculatorV2 = new StrCalculatorV2();
	private String Result = "";
	private final String[] ops = { "+", "-", "/", "*" };
	@Override
	public void output(Neuron noiron) {
		// TODO Auto-generated method stub
		if (!Result.isEmpty()) {
			AbsAlgPart itte = new APSay(1, Result);
			String representation = "calcexp";
			ArrayList<AbsAlgPart> algParts1 = new ArrayList<>();
			algParts1.add(itte);
			Algorithm algorithm = new Algorithm("calcexp", representation, algParts1);
			noiron.algParts.add(algorithm);
			Result = "";
		}
	}

	@Override
	public void input(String ear, String skin, String eye) {
		// TODO Auto-generated method stub
		for (String c : ops) {
			if (ear.contains(c)) {
				Result = strCalculatorV2.calcExp(ear);
				return;
			}
		}

	}

}
