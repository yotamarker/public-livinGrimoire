package com.yotamarker.lgkotlin1;

import java.util.ArrayList;

public class DiPrefer extends DISkill {
    //very simple small talk beef up skill
    private String loves = "nothing";
    private String hates = "creamed salad";
    private String reply = "";

    public DiPrefer(Kokoro kokoro) {
        super(kokoro);
        String l1 = kokoro.grimoireMemento.simpleLoad("diprefer_loves");
        if(l1.equals("null")||l1.isEmpty()){this.loves="tea";}
        String h1 = kokoro.grimoireMemento.simpleLoad("diprefer_hates");
        if(h1.equals("null")||h1.isEmpty()){this.hates="fudge";}
    }

    @Override
    public void input(String ear, String skin, String eye) {
        if (ear.isEmpty()) {
            return;
        }
        if (ear.contains("i really hate ")) {
            hates = ear.replace("i really hate ", "");
            reply = "ok";
            kokoro.grimoireMemento.simpleSave("diprefer_hates",hates);
            return;
        } // save
        if (ear.contains("i really love ")) {
            loves = ear.replace("i really love ", "");
            reply = "chii";
            kokoro.grimoireMemento.simpleSave("diprefer_loves",loves);
            return;
        } // save
        if (ear.contains(hates)) {
            reply = "chiiangry";
            return;
        }
        if (ear.contains(loves)) {
            reply = "chiihappy";
            return;
        }
    }

    @Override
    public void output(Neuron noiron) {
        if (!reply.isEmpty()) {
            String temp = reply;
            reply = "";
            APSay apSay = new APSay(1, temp);
            ArrayList<AbsAlgPart> algParts = new ArrayList<>();
            algParts.add(apSay);
            Algorithm algorithm = new Algorithm("prefer", "prefreply", algParts);
            noiron.algParts.add(algorithm);
        }
    }
}
