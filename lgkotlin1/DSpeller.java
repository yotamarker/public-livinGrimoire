package com.yotamarker.lgkotlin1;


import java.util.ArrayList;

public class DSpeller extends AbsCmdReq implements Neuronable {
    private String param;
    private Boolean active;

    @Override
    public void output(Neuron noiron) {
        if (this.active) {
            APSpell maho = new APSpell(this.param);
            ArrayList<AbsAlgPart> algParts1 = new ArrayList<>();
            algParts1.add(maho);
            Algorithm algorithm = new Algorithm("spell", this.param, algParts1);
            noiron.algParts.add(algorithm);
        }
    }

    @Override
	public void input(String ear, String skin, String eye) {
        // TODO Auto-generated method stub
		switch (ear) {
            case "what is the time":
            case "what is the date":
            case "what is the year":
            case "current seconds":
            case "current minutes":
            case "current hour":
            case "which day is it":
            case "greet":
                active = true;
			this.param = ear;
                break;
            default:
                active = false;
                break;

        }
    }

}
