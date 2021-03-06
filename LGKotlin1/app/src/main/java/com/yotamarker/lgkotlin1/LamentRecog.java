package com.yotamarker.lgkotlin1;

public class LamentRecog {
    private String[] lamentations = { "ouch", "what the heck", "what the hell" };
    private DISkillUtils diSkillUtils = new DISkillUtils();
    private AlgDispenser algDispenser;

    public LamentRecog() {
        algDispenser = new AlgDispenser(5, diSkillUtils.customizedVerbatimGorithm("me_sorry1", new APVerbatim("sorry")),
                diSkillUtils.customizedVerbatimGorithm("me_sorry2", new APVerbatim("what is the problem")),null);
    }

    public Algorithm lamentResponder(String ear) {
        if (ear.isEmpty()) {
            return null;
        }
        for (int i = 0; i < lamentations.length; i++) {
            if (ear.equals(lamentations[i])) {
                return this.algDispenser.dispenseAlg();
            }
        }
        return null;
    }
}
