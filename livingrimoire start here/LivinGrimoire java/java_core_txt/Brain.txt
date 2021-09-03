package com.yotamarker.lgkotlin1;

import java.util.ArrayList;

public class Brain {
    private ArrayList<thinkable> chobits = new ArrayList<>();
    private actionable action;
    private ChobitV2 mainChobit;

    /*
     * chobit hierarchy and responsibilities within the Brain class, which is a
     * Chobit daisy chain.
     *
     * higher lv chobits : reality distortion on drug intake, and input
     * translations(such as languages) lower lv chobits : drug addictions, context
     * sensitive translation of outputs, and primitive behaviors. primitive
     * behaviors : algorithm,s that do not require items and are not dependant on
     * time or place
     */
    public Brain(actionable action,ChobitV2 mainChobit, thinkable...chobits) {
        super();
        this.action = action;
        for (thinkable chobit : chobits) {
            this.chobits.add(chobit);
        }
        this.mainChobit=mainChobit;
    }

    public void doIt(String ear, String skin, String eye) {
        String result = ear;
        if(mainChobit.getStandby()){
            result=mainChobit.doIt(ear, skin, eye);
            action.act(result);
            return;
        }
        for (thinkable chobit : chobits) {
            if (result.contains("#skin")) {
                result = result.replace("#skin", "");
                result = chobit.think(ear, result, eye);
                continue;
            }
            if (result.contains("#eye")) {
                result = result.replace("#eye", "");
                result = chobit.think(ear, skin, result);
                continue;
            }
            if (result.isEmpty()) {
                result = chobit.think(ear, skin, eye);
            } else {
                result = chobit.think(result, skin, eye);
            }
        }
        if(!result.equals(ear.toLowerCase())){
        action.act(result);}else{action.act("");}
    }
}
