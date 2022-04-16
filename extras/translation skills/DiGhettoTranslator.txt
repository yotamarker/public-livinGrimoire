package com.yotamarker.lgkotlin1;

public class DiGhettoTranslator extends DISkill {
    private DISkillUtils diSkillUtils = new DISkillUtils();
    private UwU uwu = new UwU();
    private Algorithm algorithm = null;
    private GhettoTranslator ghettoTranslator = new GhettoTranslator();
    private ABDLTranslator abdlTranslator = new ABDLTranslator();
    private int mode = 0;
    public DiGhettoTranslator(Kokoro kokoro) {
        super(kokoro);
    }

    @Override
    public void input(String ear, String skin, String eye) {
        if (ear.isEmpty()) {
            return;
        }
        if (ear.contains("shut up") || ear.contains("talk normal")) {
            mode = 0;
        }
        if (ear.contains("activate black")) {
            mode = 1;
        }
        if (ear.contains("activate pink")) {
            mode = 2;
        }
        if (ear.contains("activate blue")) {
            mode = 3;
        }
        // pink talk
        switch (mode) {
            case 1:
                algorithm = diSkillUtils.customizedVerbatimGorithm("translator",
                        new APSay(1, ghettoTranslator.translate(ear)));
                break;
            case 2:
                algorithm = diSkillUtils.customizedVerbatimGorithm("translator", new APSay(1, uwu.convertToUwU(ear)));
                break;
            case 3:
                algorithm = diSkillUtils.customizedVerbatimGorithm("translator",
                        new APSay(1, abdlTranslator.translatePlusUwU(ear)));
                break;
            default:
                algorithm = diSkillUtils.customizedVerbatimGorithm("translator", new APVerbatim(ear));
                break;
        }

    }

    @Override
    public void output(Neuron noiron) {
        if (algorithm != null) {
            noiron.algParts.add(algorithm);
            algorithm = null;
        }
    }
}

