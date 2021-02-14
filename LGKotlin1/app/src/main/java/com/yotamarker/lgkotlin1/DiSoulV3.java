package com.yotamarker.lgkotlin1;

import java.util.ArrayList;

public class DiSoulV3 extends DISkill {
    //wish granter series skill. provides small talk, the same kind one have with a smart pet
    private DISkillUtils diSkillUtils = new DISkillUtils();
    private CldBool cldBool = new CldBool();
    private ZeroTimeGate timeGate = new ZeroTimeGate(1);
    private Boolean launchAlg = false;
    public DiSoulV3(Kokoro kokoro) {
        super(kokoro);
    }

    @Override
    public void input(String ear, String skin, String eye) {
        if (ear.equals("hi") && !cldBool.getModeActive()) {
            cldBool.setModeActive(true);
            launchAlg = true;
            return;
        }
    }

    @Override
    public void output(Neuron noiron) {
        if (launchAlg) {
            launchAlg = false;
            APSay apSay = new APSay(1, "hadouken");
            timeGate.open(1);
            APSoulConvo apSoulConvo = new APSoulConvo(this.cldBool, this.timeGate);
            ArrayList<AbsAlgPart> algParts = new ArrayList<>();
            algParts.add(apSay);
            algParts.add(apSoulConvo);
            Algorithm algorithm = new Algorithm("soulconvo", "soulv3", algParts);
            noiron.algParts.add(algorithm);
        }
    }
}
