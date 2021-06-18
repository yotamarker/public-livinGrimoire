package com.yotamarker.lgkotlin1;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class TrgSpark extends AbsTrgMood {
    /*
     * the parrot chirps for attention it gets it, responds than it chirps more and
     * also replies to attention but not infinitely
     */
    private PlayGround playGround = new PlayGround();
    private int tolerance = 10;
    private int inhib = -1;
    private String[] outputKeys = { "chiinormal", "chiihappy", "chiiexcited", "chiicurious", "chiiangry", "the time",
            "hello", "pet me", "hadouken", "hadouken" }; // represent output algs or strings
    private Random rand = new Random();
    private ArrayDeque<String> inputs = new ArrayDeque<>(); // detects repeating phrases like a parrot
    private ArrayList<String> learnedOutputs = new ArrayList<>(); // saves most recent repeated phrases
    private String output = "";
    private Boolean initGreet = true;// 1st attention input in a cycle ?
    private EmoRecog emoRecog = new EmoRecog();
    public TrgSpark() {
        // c'tor
        for (int i = 0; i < 5; i++) {
            inputs.add("narf");
            learnedOutputs.add("chiinormal");
        }
    }

    public TrgSpark(String favoriteWord) {
        // c'tor with giving the parrot a favorite word
        for (int i = 0; i < 5; i++) {
            inputs.add("narf");
            learnedOutputs.add("chiinormal");
        }
        outputKeys[outputKeys.length - 2] = favoriteWord;
    }
    @Override
    public void trigger(String ear, String skin, String eye) {
        // replenish command
        if (ear.equals("yo")||ear.equals("howdy")) {
            replenish();
            return;
        }
        // inhibition
        if (inhib == playGround.getHoursAsInt() || playGround.isNight()) {
            tolerance = 0;
            return;
        }
        else {
            inhib = -1;
        }
        if (doShutUp(ear)) {
            // expansion point shut up
            shutUp();
            return;
        }
        // detect attention
        if (!ear.isEmpty()) {
            if (inputs.contains(ear) || mood != 2) {
                // type 2 attention detection (known repeated phrases)
                mood = 2;
                if (tolerance > 0) {
                    // expansion point friend detected
                    if (initGreet) {
                        initGreet = false;
                        initFriendDetected();
                    } else {
                        friendDetected(ear);
                    }
                }
                // learn new repeated phrase :
                if (!learnedOutputs.contains(ear)) {
                    learnedOutputs.add(ear);
                    Collections.shuffle(learnedOutputs);
                    learnedOutputs.remove(learnedOutputs.size() - 1);
                }

            }
            else {
                // type 1 attention detection
                mood = 1;
                if (tolerance > 0) {
                    // expansion point getting attention
                    if (initGreet) {
                        initGreet = false;
                        initAttentionDetected();
                    } else {
                        attentionDetected(ear);
                    }
                }
            }
            inputs.poll();
            inputs.add(ear);
        }
        if (tolerance == 0) {
            mood = 0;
        }

    }
    public Boolean isTriggered() {
        return mood > 0;
    }

    public String getOutput() {
        // reply to attention gotten
        if (!output.isEmpty()) {
            String temp = output;
            output = "";
            return temp;
        }
        // expansion point want more attention
        if (tolerance > 0) {

            switch (mood) {
                case 1:
                    int x = rand.nextInt(100);
                    if (x < outputKeys.length) {
                        tolerance--;
                        output = response(x);
                    }
                    // inhibition
                    if (tolerance == 0) {
                        inhib = playGround.getHoursAsInt();
                        mood = 0;
                    }
                    break;
                case 2:
                    int x2 = rand.nextInt(50);
                    if (x2 < outputKeys.length) {
                        tolerance--;
                        output = response(x2);
                    }
                    // inhibition
                    if (tolerance == 0) {
                        inhib = playGround.getHoursAsInt();
                        mood = 0;
                    }
                    break;

                default:
                    break;
            }
        }
        String temp = output;
        output = "";
        return temp;
    }

    private String response(int x) {
        switch (x) {
            case 0:
                return learnedOutputs.get(rand.nextInt(learnedOutputs.size()));
            case 5:
                return playGround.getCurrentTimeStamp();
            default:
                return outputKeys[x];
        }
    }

    public void isStandBy(Boolean sBoolean) {
        // expansion point output on standby
        tolerance = 10;
        // output = "pfft";
    }

    @Override
    public int getMood() {
        // TODO
        return mood;
    }

    // responses expansions
    private void shutUp() {
        inhib = playGround.getHoursAsInt();
        //output = "ok";
        tolerance = 0;
    }

    private void replenish() {
        // expansion point replenish
        tolerance = 10;
        output = "hello";
        inhib = -1;
        mood = 2;
    }

    private void friendDetected(String ear) {
        // expansion friend detected
        if (emoRecog.isAngry(ear)) {
            output = "chiiangry";
            return;
        }
        if (emoRecog.isCurious(ear)) {
            output = "chiicurious";
            return;
        }
        if (emoRecog.isExcited(ear)) {
            output = "chiiexcited";
            return;
        }
        if (emoRecog.isHappy(ear)) {
            output = "chiihappy";
            return;
        }
        int x = rand.nextInt(outputKeys.length);
        output = response(x);
    }

    private void initFriendDetected() {
        // expansion friend detected
        int x = rand.nextInt(outputKeys.length);
        output = response(x);
        output = output + " " + output;
    }

    private void attentionDetected(String ear) {
        // expansion attention detected
        if (emoRecog.isAngry(ear)) {
            output = "chiiangry";
            return;
        }
        if (emoRecog.isCurious(ear)) {
            output = "chiicurious";
            return;
        }
        if (emoRecog.isExcited(ear)) {
            output = "chiiexcited";
            return;
        }
        if (emoRecog.isHappy(ear)) {
            output = "chiihappy";
            return;
        }
        int x = rand.nextInt(outputKeys.length);
        output = response(x);
    }

    private void initAttentionDetected() {
        // expansion attention detected
        int x = rand.nextInt(outputKeys.length);
        output = response(x);
        output = output + " " + output;
    }
    private Boolean doShutUp(String ear) {
        // returns if there was a request to shut up
        switch (ear) {
            case "ok":
            case "okay":
            case "stop":
            case "stop it":
            case "shut up":
            case "be quiet":output = "ok";return true;
            case "bye":output = "bye bye";case "bye bye":case "bye-bye":output = "bye";
                return true;
        }
        return false;
    }

    public void forceLearn(String sayThis) {
        // title
        learnedOutputs.add(sayThis);
        Collections.shuffle(learnedOutputs);
        learnedOutputs.remove(learnedOutputs.size() - 1);
    }
}
