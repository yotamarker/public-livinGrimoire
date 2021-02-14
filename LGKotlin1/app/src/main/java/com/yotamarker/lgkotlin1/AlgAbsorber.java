package com.yotamarker.lgkotlin1;

public class AlgAbsorber {
    /*
     * used for skills that output up to 1 regular non defcon alg this is useful for
     * upgrading skills, you can capsulate the old skill than utilize it in the new
     * skill
     */
    private Algorithm algorithm = null;
    private DISkill myDiskyll;

    public AlgAbsorber(DISkill myDiskyll) {
        super();
        this.myDiskyll = myDiskyll;
    }

    public void absorbAlg(String ear, String skin, String eye) {
        Neuron n1 = new Neuron();
        myDiskyll.input(ear, skin, eye);
        myDiskyll.output(n1);
        if (!n1.algParts.isEmpty()) {
            algorithm = n1.algParts.get(0);
        }
    }

    public Algorithm getAlgorithm() {
        return algorithm;
    }

    public void resetAlgorithm() {
        this.algorithm = null;
    }

}
