package Skills.special_skills;

import Auxiliary_Modules.AXGamification;
import LivinGrimoire.DiSkillV2;

public class DiGamificationSkillBundle extends DiSkillBundle{
    private final AXGamification axGamification = new AXGamification();
    private int gain = 1;
    private int cost = 3;
    public void setGain(int gain) {
        if (gain >0){
            this.gain = gain;}
    }

    public void setCost(int cost) {
        if (cost > 0){this.cost = cost;}
    }
    public void addGrindSkill(DiSkillV2 skill){
        axSkillBundle.addSkill(new GamiPlus(skill,axGamification,gain));
    }
    public void addCostlySkill(DiSkillV2 skill){
        axSkillBundle.addSkill(new GamiMinus(skill,axGamification,cost));
    }

    public AXGamification getAxGamification() {
        return axGamification;
    }
}
