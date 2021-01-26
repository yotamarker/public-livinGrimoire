package com.yotamarker.lgkotlin1;

import java.util.ArrayList;

public class ABDLDefcon extends AbsDefconV2 {
    private MCodes mCodes = new MCodes(); // items
    private Person friend = new Person();
    private ArrayList<String> naughtyWords = new ArrayList<String>();
    private String punishmentExp = "";
    private Boolean badLang = false;
    private Boolean watchedPorn = false;
    private PlayGround playGround = new PlayGround();
    private DISkillUtils diSkillUtil = new DISkillUtils();
    private Algorithm tempAlg = null;
    private String lastSin = "";

    public ABDLDefcon(MCodes mCodes, Person friend) {
        super();
        this.mCodes = mCodes;
        this.friend = friend;
        naughtyWords.add("shit");
        naughtyWords.add("cunt");
        naughtyWords.add("fuck");
        naughtyWords.add("damn");
        naughtyWords.add("sheet");
        naughtyWords.add("bastard");
    }

    private String checkForDefcons(String ear, String skin, String eye) {
        String naughtyWord = strContains(ear, this.naughtyWords);
        if (!naughtyWord.isEmpty()) {
            punishmentExp = playGround.getTomorrow();
            badLang = true;
            switch (naughtyWord) {
                case "fuck":
                    tempAlg = diSkillUtil.verbatimGorithm(new APVerbatim("use the word play", "bad boy you are grounded"));
                    return naughtyWord;
                case "shit":
                    tempAlg = diSkillUtil.verbatimGorithm(new APVerbatim("use the word poo", "bad boy you are grounded"));
                    return naughtyWord;
                case"sheet":
                    tempAlg = diSkillUtil.verbatimGorithm(new APVerbatim("use the word poo", "bad boy you are grounded"));
                    return naughtyWord;
                case "cunt":
                    tempAlg = diSkillUtil.verbatimGorithm(new APVerbatim("use the word fufu", "bad boy you are grounded"));
                    return naughtyWord;
                case "damn":
                    tempAlg = diSkillUtil.verbatimGorithm(new APVerbatim("use the word gosh", "bad boy you are grounded"));
                    return naughtyWord;
                case "bastard":
                    tempAlg = diSkillUtil.verbatimGorithm(new APVerbatim("use the word mean", "bad boy you are grounded"));
                    return naughtyWord;
                default:
                    break;
            }
            tempAlg = diSkillUtil.verbatimGorithm(new APVerbatim("bad boy you are grounded"));
            return naughtyWord;
        }
        if (ear.contains("i watched porn")) {
            tempAlg = diSkillUtil.verbatimGorithm(new APVerbatim("bad boy no more television today"));
            punishmentExp = playGround.getTomorrow();
            watchedPorn = true;
            return "watched porn";
        }

        return "";
    }

    @Override
    public String getAbsoluteDefcon(String ear, String skin, String eye) {
        String temp = lastSin;
        lastSin = "";
        return temp;
    }

    @Override
    public Algorithm getDefcon(String ear, String skin, String eye) {
        lastSin = checkForDefcons(ear, skin, eye);
        if (punishmentExp.isEmpty()) {
            return null;
        }
        if (punishmentExp.equals(playGround.getDayOfDWeek())) {
            punishmentExp = "";
            resetDefcons();
            return null;
        }
        if (!(tempAlg == null)) {
            Algorithm alg1 = tempAlg.clone();
            tempAlg = null;
            return alg1;
        }
        // sp cases :
        if (badLang && ear.contains("when can i")) {
            return diSkillUtil.verbatimGorithm(new APVerbatim("tomorrow if you behave"));
        }
        if (badLang && ear.contains("can i play")) {
            return diSkillUtil.verbatimGorithm(new APVerbatim("no you may not", "watch your potty mouth"));
        }
        if (watchedPorn && ear.contains("watch tv") || ear.contains("watch television")) {
            return diSkillUtil.verbatimGorithm(new APVerbatim("tomorrow if you behave", "and only kid shows"));
        }

        return null;
    }

    private void resetDefcons() {
        badLang = false;
        watchedPorn = false;
    }

    public static String strContains(String str1, ArrayList<String> naughtyWords) {
        for (String temp : naughtyWords) {
            if (str1.contains(temp)) {
                return temp;
            }
        }
        return "";
    }

}
