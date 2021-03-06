package com.yotamarker.lgkotlin1;

import java.util.ArrayList;
import java.util.Random;

public class DIBurper extends DISkill {
    ArrayList<Integer> minutesToBurp = new ArrayList<Integer>();
    private PlayGround playGround = new PlayGround();
    private Random randomGenerator = new Random();
    private String burpArr[] = { "burp1", "burp2", "burp3" };
    private Boolean algToGo = false;
    private int lastBurpMinute = 70;
    public DIBurper(Kokoro kokoro) {
        super(kokoro);
        minutesToBurp.clear();
        int randomInt = randomGenerator.nextInt(60) + 1; // how many burps this hour
        for (int i = 0; i < randomInt; i++) {
            randomInt = randomGenerator.nextInt(60) + 1; // burp minute, add x random burps
            if (!minutesToBurp.contains(randomInt)) {
                minutesToBurp.add(randomInt);
            }
        }
    }

    @Override
    public void input(String ear, String skin, String eye) {
        int minutes = playGround.getMinutesAsInt();
        if (minutes == 0) {
            minutesToBurp.clear();
            int randomInt = randomGenerator.nextInt(60) + 1; // how many burps this hour
            for (int i = 0; i < randomInt; i++) {
                randomInt = randomGenerator.nextInt(60) + 1; // burp minute, add x random burps
                if (!minutesToBurp.contains(randomInt)) {
                    minutesToBurp.add(randomInt);
                }
            }
        } else {
            if (minutesToBurp.contains(minutes)&&(lastBurpMinute!=minutes)) {
                lastBurpMinute = minutes;
                algToGo = true;
                this.setSentAlg(true);
            }
        }
    }

    @Override
    public void output(Neuron noiron) {
        if (algToGo) {
            algToGo = false;

            noiron.algParts.add(burp());
        }
    }

    private Algorithm burp() {
        int x2 = randomGenerator.nextInt(3);
        AbsAlgPart itte = new APSay(1, this.burpArr[x2]);
        String representation = "burp";
        ArrayList<AbsAlgPart> algParts1 = new ArrayList<>();
        algParts1.add(itte);
        Algorithm algorithm = new Algorithm("burp", representation, algParts1);
        return algorithm;
    }
    @Override
    public Boolean auto() {
        // TODO Auto-generated method stub
        return true;
    }
}
