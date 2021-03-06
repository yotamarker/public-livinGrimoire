package com.yotamarker.lgkotlin1;

public class DiParrot extends DiSkillV2 {
    /*
     * this skill expaands on the AI getting attention it works much like a parrot
     * does on detecting attention she engages for more attention say hey baby to
     * refill or wait 1 hour say shut up or ok to shut her up for an hour like a
     * parrot this skill is inactive at night she will learn repeating words and may
     * use them for attention
     */
    private TrgSpark trgSpark = new TrgSpark();
    private String out1 = "";
    private CldBool cldBool = new CldBool();
    public DiParrot(Kokoro kokoro) {
        super(kokoro);
    }

    @Override
    public void input(String ear, String skin, String eye) {
        // if(ear.contains("say")) {
        // String material = ear.replace("say", "");
        // trgSpark.forceLearn(material);
        // }
        trgSpark.isStandBy(kokoro.standBy);
        trgSpark.trigger(ear, skin, eye);
        if (!cldBool.getModeActive()) {
            out1 = trgSpark.getOutput();
        }
        if (!out1.isEmpty() && !cldBool.getModeActive()) {
            this.outAlg = this.diSkillUtils.simpleCloudiandVerbatimAlgorithm(cldBool, "parrot", out1);
            // this.outAlg = this.diSkillUtils.simpleVerbatimAlgorithm("parrot_skill",
            // out1);
            out1 = "";
        }
    }
}
