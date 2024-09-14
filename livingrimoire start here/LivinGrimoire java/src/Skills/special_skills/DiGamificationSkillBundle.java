package Skills.special_skills;

import Auxiliary_Modules.AXGamification;
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
    public void addGrindSkill(Skill skill){
        axSkillBundle.addSkill(new GamiPlus(skill,axGamification,gain));
    }
    public void addCostlySkill(Skill skill){
        axSkillBundle.addSkill(new GamiMinus(skill,axGamification,cost));
    }

    public AXGamification getAxGamification() {
        return axGamification;
    }
}
