package com.yotamarker.lgkotlin1;

public class Empathy extends DiSkillV2{
    //part of the ME or self awareness skill series
    private LamentRecog lamentRecog = new LamentRecog();
    public Empathy(Kokoro kokoro) {
        super(kokoro);
    }
    @Override
    public void input(String ear, String skin, String eye) {
        this.outAlg = lamentRecog.lamentResponder(ear);
    }

}
