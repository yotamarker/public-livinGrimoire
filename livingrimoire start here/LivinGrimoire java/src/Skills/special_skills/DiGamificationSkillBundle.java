package Skills.special_skills;

import Auxiliary_Modules.AXGamification;
import Auxiliary_Modules.UniqueResponder;
import LivinGrimoire.Skill;

public class DiGamificationSkillBundle extends DiSkillBundle{
    private final AXGamification axGamification = new AXGamification();
    private int gain = 1;
    private int cost = 2;
    public void setGain(int gain) {
        if (gain >0){
            this.gain = gain;}
    }

    public void setCost(int cost) {
        if (cost > 0){this.cost = cost;}
    }
    public void addGrindSkill(Skill skill) {
        axSkillBundle.addSkill(new GamiPlus(skill, axGamification, gain));
        for (int i = 0; i < 10; i++) {
            notes.get("triggers").addResponse("grind " + skill.skillNotes("triggers"));
        }
    }

    public void addCostlySkill(Skill skill) {
        axSkillBundle.addSkill(new GamiMinus(skill, axGamification, cost));
        for (int i = 0; i < 10; i++) {
            notes.get("triggers").addResponse("grind " + skill.skillNotes("triggers"));
        }
    }


    public AXGamification getAxGamification() {
        return axGamification;
    }
    @Override
    public void setDefaultNote() {
        notes.put("notes", new UniqueResponder("a bundle of grind and reward skills"));
    }

}
