package com.yotamarker.lgkotlin1;

public class DiSkillV2 extends AbsCmdReq {
    protected Kokoro kokoro; // consciousness, shallow ref class to enable interskill communications
    protected DISkillUtils diSkillUtils = new DISkillUtils();
    protected Algorithm outAlg = null; // skills output

    public DiSkillV2(Kokoro kokoro) {
        super();
        this.kokoro = kokoro;
    }

    @Override
    public void input(String ear, String skin, String eye) {
    }
    @Override
    public final void output(Neuron noiron) {
        if (outAlg != null) {
            noiron.algParts.add(outAlg);
            outAlg = null;
        }
    }
}

