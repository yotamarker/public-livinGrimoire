package com.yotamarker.lgkotlinfull.skills;

public abstract class AbsTrgMood {
    protected int mood = 0;
    // sub classes names should start with TrgM
    public abstract void trigger(String ear, String skin, String eye);

    public void reset() {
        mood = 0;
    }

    public abstract int getMood();
}
