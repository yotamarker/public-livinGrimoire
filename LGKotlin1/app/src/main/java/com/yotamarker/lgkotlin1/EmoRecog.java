package com.yotamarker.lgkotlin1;

import java.util.HashSet;

public class EmoRecog {
    private HashSet<String> curious = new HashSet<String>();
    private HashSet<String> excited = new HashSet<String>();
    private HashSet<String> angry = new HashSet<String>();
    private HashSet<String> happy = new HashSet<String>();

    public EmoRecog() {
        excited.add("to");
        curious.add("why");
        curious.add("where");
        curious.add("when");
        curious.add("how");
        curious.add("who");
        curious.add("which");
        curious.add("whose");
        happy.add("good");
        happy.add("awesome");
        happy.add("great");
        happy.add("wonderful");
        happy.add("sweet");
        happy.add("happy");
        angry.add("hell");
        angry.add("crap");
        angry.add("fudge");
        angry.add("angry");
        angry.add("waste");
        angry.add("stupid");
        angry.add("retard");
    }

    public Boolean isCurious(String ear) {
        if (strContains(ear, curious)) {
            return true;
        }
        return false;
    }

    public Boolean isExcited(String ear) {
        if (strContains(ear, excited)) {
            return true;
        }
        return false;
    }

    public Boolean isAngry(String ear) {
        if (strContains(ear, angry)) {
            return true;
        }
        return false;
    }

    public Boolean isHappy(String ear) {
        if (strContains(ear, happy)) {
            return true;
        }
        return false;
    }

    private Boolean strContains(String str1, HashSet<String> containsMe) {
        for (String temp : containsMe) {
            if (str1.contains(temp)) {
                return true;
            }
        }
        return false;
    }
}
