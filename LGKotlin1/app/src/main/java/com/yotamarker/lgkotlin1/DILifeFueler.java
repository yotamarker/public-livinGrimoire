package com.yotamarker.lgkotlin1;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DILifeFueler extends DISkill {
    private int mode = 0;

    public DILifeFueler(Kokoro kokoro) {
        super(kokoro);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void input(String ear, String skin, String eye) {
        if (ear.contains("lonely") || ear.contains("sad")) {
            mode = 2;
            return;
        }
        if (ear.contains("give up") || ear.contains("scared")) {
            mode = 1;
            return;
        }
    }

    @Override
    public void output(Neuron noiron) {
        switch (mode) {
            case 1:
                noiron.algParts.add(verbatimGorithm(new APVerbatim("sentimenta", "sentimentc")));
                mode = 0;
                break;
            case 2:
                noiron.algParts
                        .add(verbatimGorithm(new APVerbatim("sentimentb", "sentimentc", "sentimentd", "sentimente")));
                mode = 0;
                break;
            default:
                break;
        }
    }

    public static String regexChecker(String theRegex, String str2Check) {
        Pattern checkRegex = Pattern.compile(theRegex);
        Matcher regexMatcher = checkRegex.matcher(str2Check);
        while (regexMatcher.find()) {
            if (regexMatcher.group().length() != 0) {
                return regexMatcher.group().trim();
            }
        }
        return "";
    }

    private Algorithm verbatimGorithm(AbsAlgPart itte) {
        // returns a simple algorithm for saying sent parameter
        String representation = "lifefuel";
        ArrayList<AbsAlgPart> algParts1 = new ArrayList<>();
        algParts1.add(itte);
        Algorithm algorithm = new Algorithm("lifefuel", representation, algParts1);
        return algorithm;
    }
}