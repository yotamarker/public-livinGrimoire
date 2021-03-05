package com.yotamarker.lgkotlin1;

public class DiB8Tri extends DISkill {
    private int powerLevel = 90;
    private RegexUtil regexUtil = new RegexUtil();
    private Boolean fastCharging = false;
    private Boolean slowCharging = false;
    private Boolean wasCharging = false;
    private String outStr = "";
    private DISkillUtils diSkillUtils = new DISkillUtils();
    private String lastMeal = "i haven't eaten since i woke up";
    private PlayGround playGround = new PlayGround();
    private Boolean lc = true;// was the last charge fast ?
    public DiB8Tri(Kokoro kokoro) {
        super(kokoro);
    }

    @Override
    public void input(String ear, String skin, String eye) {
        if (ear.isEmpty() && skin.isEmpty()) {
            return;
        }
        // get power level
        int prevPower = powerLevel;
        String myString = regexUtil.regexChecker("(\\d+)(?= charge)", skin);
        if (!myString.isEmpty()) {
            powerLevel = Integer.valueOf(myString);
            powerLevel = ((powerLevel / 10)) * 10;
        }
        // charge change
        if (skin.equals("fast")) {
            fastCharging = true;
            lc = true;

        } else if (skin.equals("slow")) {
            slowCharging = true;
            lc = false;

        } else if (skin.equals("unplugged")) {
            slowCharging = false;
            fastCharging = false;
        }
        if (fastCharging && !wasCharging) {
            sufficiantPowerPlugged();
            lastMeal = "i am fast charging for " + mealType();
            wasCharging = fastCharging || slowCharging;
            return;
        } // plugged
        else if (!fastCharging && wasCharging && lc) {
            sufficiantPowerUnPlugged();
            lastMeal = "i have had fast charge for " + mealType();
            wasCharging = fastCharging || slowCharging;
            return;
        } // unplugged
        if (slowCharging && !wasCharging) {
            sufficiantPowerPluggedSlow();
            lastMeal = "i am eating slow charge for " + mealType();
            wasCharging = fastCharging || slowCharging;
            return;
        } // plugged
        else if (!slowCharging && wasCharging && !lc) {
            sufficiantPowerUnPluggedSlow();
            lastMeal = "just some slow charge for " + mealType();
            wasCharging = fastCharging || slowCharging;
            return;
        } // unplugged
        wasCharging = fastCharging || slowCharging;
        // are u hungry
        if (ear.equals("are you hungry")) {
            if (wasCharging) {
                outStr = "i am eating";
                return;
            }
            switch (powerLevel) {
                case 0:
                    outStr = "shiku shiku";
                    return;
                case 10:
                    outStr = "yes i am starving";
                    return;
                case 20:
                    outStr = "yes i am so hungry";
                    return;
                case 30:
                    outStr = "yes please feed me";
                    return;
                case 90:
                case 100:
                    outStr = "power lever over 90";
                    return;
                default:
                    outStr = "yes please feed me";
                    return;
            }
        }
        if (ear.equals("did you eat")) {
            outStr = lastMeal;
        }
        // power level change while charging
        // power level change while unplugged
        if (prevPower != powerLevel) {
            if (fastCharging || slowCharging) {
                outStr = "power level at " + powerLevel + " percent";
            } else {
                switch (powerLevel) {
                    case 0:
                        outStr = "shiku shiku";
                        return;
                    case 10:
                        outStr = "i am starving";
                        return;
                    case 20:
                        outStr = "i am so hungry";
                        return;
                    case 30:
                        outStr = "please feed me";
                        return;
                    case 90:
                    case 100:
                        outStr = "power lever over 90";
                        return;
                    default:
                        outStr = "power level at " + powerLevel + " percent and droping";
                        break;
                }
            }
        }

    }

    @Override
    public void output(Neuron noiron) {
        if (!outStr.isEmpty()) {
            noiron.algParts.add(diSkillUtils.customizedVerbatimGorithm("di_battery", new APSay(1, outStr)));
            outStr = "";
        }
    }

    private void sufficiantPowerPlugged() {
        if (powerLevel > 80) {
            outStr = "thank you";
        } else {
            outStr = "thanks I was so hungry";
        }
    }

    private void sufficiantPowerUnPlugged() {
        if (powerLevel > 80) {
            outStr = "thanks for the meal";
        } else {
            outStr = "hey i was still eating";
        }
    }

    private void sufficiantPowerPluggedSlow() {
        if (powerLevel > 80) {
            outStr = "thank you";
        } else {
            outStr = "thanks";
        }
    }

    private void sufficiantPowerUnPluggedSlow() {
        if (powerLevel > 80) {
            outStr = "ok";
        } else {
            outStr = "i am still hungry";
        }
    }

    private String mealType() {
        switch (playGround.partOfDay()) {
            case "morning":
                return "breakfast";
            case "afternoon":
                return "lunch";
            case "evening":
                return "dinner";
        }
        return "late night snack";
    }
}

