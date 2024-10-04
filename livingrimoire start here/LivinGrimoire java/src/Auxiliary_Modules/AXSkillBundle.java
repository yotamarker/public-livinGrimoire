package Auxiliary_Modules;

import LivinGrimoire.*;

import java.util.ArrayList;

public class AXSkillBundle {
    private final ArrayList<Skill> skills = new ArrayList<>();
    private final Neuron tempN = new Neuron();
    private Kokoro kokoro = new Kokoro(new AbsDictionaryDB());

    public void setKokoro(Kokoro kokoro) {
        this.kokoro = kokoro;
        for (Skill skill : skills) {
            skill.setKokoro(this.kokoro);
        }
    }

    public AXSkillBundle(Skill...skillsParams) {
        for (Skill skill : skillsParams)
        {
            skill.setKokoro(this.kokoro);
            skills.add(skill);
        }
    }
    public AXSkillBundle addSkill(Skill skill){
        // builder pattern
        skill.setKokoro(this.kokoro);
        skills.add(skill);
        return this;
    }
    public AlgorithmV2 dispenseAlgorithm(String ear, String skin, String eye){
        for (Skill skill : skills) {
            skill.input(ear, skin, eye);
            skill.output(tempN);
            for (int j = 1; j < 6; j++) {
                Algorithm temp = tempN.getAlg(j);
                if (temp != null) {
                    return new AlgorithmV2(j,temp);
                }
            }
        }
        return null;
    }
    public int getSize(){return skills.size();}
}