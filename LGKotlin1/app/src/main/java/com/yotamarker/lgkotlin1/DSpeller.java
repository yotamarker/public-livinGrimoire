package com.yotamarker.lgkotlin1;


import java.util.ArrayList;

public class DSpeller extends AbsCmdReq implements Neuronable {
    private String param = "";
    private Boolean active;
    private  DISkillUtils diSkillUtil = new DISkillUtils();
    private TrgM1 TrgM1 = new TrgM1();
    @Override
    public void output(Neuron noiron) {
        switch (this.param) {
            case "about":
                param = "";
                noiron.algParts.add(
                        verbatimGorithm(new APVerbatim("I am", "the living grimoire", "I was created by", "moti barski")));
                return;
            case "creator":
                param = "";
                noiron.algParts.add(verbatimGorithm(new APVerbatim("I was created by", "moti barski")));
                return;
            case "hello":
                param = "";
                PlayGround playGround = new PlayGround();
                int temp = TrgM1.getMood();
                if (temp == 0) {
                    noiron.algParts.add(verbatimGorithm(new APVerbatim("good " + playGround.partOfDay())));
                } else {
                    noiron.algParts.add(verbatimGorithm(new APVerbatim("what ever")));
                }
                return;
            case "god mode":
                param = "";
                noiron.algParts.add(verbatimGorithm(new APVerbatim("ainzbuff")));
                return;
            case "do you like me":
                param = "";
                noiron.algParts.add(verbatimGorithm(new APVerbatim("pogchamp")));
                return;
            case "kiss":
                param = "";
                noiron.algParts.add(verbatimGorithm(new APVerbatim("kiss")));
                return;
            case "hadouken":
                param = "";
                noiron.algParts.add(diSkillUtil.verbatimGorithm("spell",new APVerbatim("hadouken")));
                return;
            case "shouryuken":
                param = "";
                noiron.algParts.add(diSkillUtil.verbatimGorithm("spell",new APVerbatim("shouryuken")));
                return;
            default:
                break;
        }
        if (this.active) {
            APSpell maho = new APSpell(this.param);
            ArrayList<AbsAlgPart> algParts1 = new ArrayList<>();
            algParts1.add(maho);
            Algorithm algorithm = new Algorithm("spell", this.param, algParts1);
            noiron.algParts.add(algorithm);
        }
    }

    @Override
    public void input(String ear, String skin, String eye) {
        TrgM1.trigger(ear, skin, eye);
        switch (ear) {
            case "what is the time":
            case "what is the date":
            case "what is the year":
            case "current seconds":
            case "current minutes":
            case "current hour":
            case "which day is it":
            case "greet":
                active = true;
                this.param = ear;
                break;
            case "what are you":
                this.param = "about";
                break;
            case "hello":
                this.param = "hello";
                break;
            case "who made you":
                this.param = "creator";
                break;
            case "god mode":
                this.param = "god mode";
                break;
            case "do you like me":
                this.param = "do you like me";
                break;
            case "give me a kiss":
                this.param = "kiss";
                break;
            case "hadouken": case"hadoken":
                this.param = "shouryuken";
                break;
            case "shouryuken": case"shoryuken":
                this.param = "hadouken";
                break;
            default:
                active = false;
                break;

        }
    }

    private Algorithm verbatimGorithm(AbsAlgPart itte) {
        // returns a simple algorithm for saying sent parameter
        // AbsAlgPart itte = new APVerbatim("I am");
        String representation = "about";
        ArrayList<AbsAlgPart> algParts1 = new ArrayList<>();
        algParts1.add(itte);
        Algorithm algorithm = new Algorithm("about", representation, algParts1);
        return algorithm;
    }
}

