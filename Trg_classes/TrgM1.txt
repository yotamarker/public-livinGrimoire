package com.yotamarker.lgkotlin1;

import java.util.ArrayDeque;

public class TrgM1 extends AbsTrgMood {
    /*
     * set mood with trigger func repeated elements will set mood to annoyes (1) you
     * after the reading (0) OoooWeee !
     */
    private ArrayDeque<String> inputs = new ArrayDeque<>();
    public TrgM1() {
        for (int i = 0; i < 5; i++) {
            inputs.add("narf");
        }
    }
    @Override
    public void trigger(String ear, String skin, String eye) {
        if (ear.isEmpty()) {
            return;
        }
        if (inputs.contains(ear)) {
            mood = 1;
        }
        inputs.poll();
        inputs.add(ear);
    }

    @Override
    public int getMood() {
        int temp = mood;
        reset();
        if(temp!=0){
            inputs.clear();
        for (int i = 0; i < 5; i++) {
            inputs.add("narf");
        }}
        return temp;
    }
}

