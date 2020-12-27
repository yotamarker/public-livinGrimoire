package com.yotamarker.lgkotlin1;

import java.util.ArrayList;

public class DiTglrSkill extends DISkill {
    // this toggles a skill, logically a level 1 skill
    /*
     * thus one can use hidden skills without the chobits hidden name and use select
     * cases rather than string.contains for a speed beef up as well
     */
    private ArrayList<String> conjurtions = new ArrayList<String>();
    private String outString = "";
    private DISkillUtils diSkillUtils = new DISkillUtils();

    public DiTglrSkill(Kokoro kokoro, String... conjurationsTemp) {
        super(kokoro);
        for (int i = 0; i < conjurationsTemp.length; i++) {
            this.conjurtions.add(conjurationsTemp[i]);
        }
    }

    private String strContainsList(String str1) {
        for (String temp : conjurtions) {
            if (str1.contains(temp)) {
                return temp;
            }
        }
        return "";
    }
    @Override
    public void input(String ear, String skin, String eye) {
        // toggle :
        if (ear.contains("dislike")) {
            String conjurati = strContainsList(ear);
            if (!conjurati.isEmpty()) {
                kokoro.toHeart.put(conjurati, conjurati + " off");
                outString = "you dislike it";
                return;
            }
        }
        if (ear.contains("like")) {
            String conjurati = strContainsList(ear);
            if (!conjurati.isEmpty()) {
                kokoro.toHeart.put(conjurati, conjurati + " on");
                outString = "ok";
                return;
            }
        }
    }

    @Override
    public void output(Neuron noiron) {
        if (!outString.isEmpty()) {
            noiron.algParts.add(diSkillUtils.verbatimGorithm(new APVerbatim(outString)));
            outString = "";
        }
    }
}
