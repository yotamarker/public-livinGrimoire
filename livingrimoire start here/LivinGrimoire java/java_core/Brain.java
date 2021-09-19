package com.yotamarker.lgkotlin1;

public class Brain{
    private thinkable chi;
    private actionable MVC;
    public Brain(actionable MVC, thinkable chobit) {
        chi=chobit;this.MVC = MVC;
    }

    public void doIt(String ear, String skin, String eye){
        String result = chi.think(ear,skin,eye);
        MVC.act(result);
    }
}
