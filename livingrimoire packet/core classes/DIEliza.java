package com.yotamarker.lgkotlin1;

public class DIEliza extends DISkill {
    private Eliza eliza = new Eliza();
    private String outputStr = "";
    private DISkillUtils diSkillUtil = new DISkillUtils();

    public DIEliza(Kokoro kokoro) {
        super(kokoro);
    }

    @Override
    public void input(String ear, String skin, String eye) {
        if (ear.contains("baby")) {
            outputStr = eliza.respond(ear.replace("baby", ""));
        }
    }

    @Override
    public void output(Neuron noiron) {
        if (!outputStr.isEmpty()) {
            noiron.algParts.add(diSkillUtil.verbatimGorithm(new APVerbatim(outputStr)));
            outputStr = "";
        }
    }
}
