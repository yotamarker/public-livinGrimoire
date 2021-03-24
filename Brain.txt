package com.yotamarker.lgkotlin1;

import java.util.ArrayList;

public class Brain {
    private ArrayList<thinkable> chobits = new ArrayList<>();
    private actionable action;

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
    public Brain(actionable action, thinkable...chobits) {
        super();
        this.action = action;
        for (thinkable chobit : chobits) {
            this.chobits.add(chobit);
        }
    }

    public void doIt(String ear, String skin, String eye) {
        String result = ear;
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
                chobit.think(ear, skin, eye);
            } else {
                result = chobit.think(result, skin, eye);
            }
        }
        action.act(result);
    }
}
