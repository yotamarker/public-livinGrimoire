package Skills.special_skills;

import Auxiliary_Modules.AXGamification;
import LivinGrimoire.DiSkillV2;
import LivinGrimoire.Neuron;

public class GamificationN extends DiSkillV2 {
    private AXGamification axGamification;
    private int cost = 3;
    private DiSkillV2 skill;

    public GamificationN(DiSkillV2 skill, GamificationP rewardBank) {
        this.skill = skill;
        axGamification = rewardBank.getAxGamification();
    }

    public GamificationN setCost(int cost) {
        this.cost = cost;return this;
    }

    @Override
    public void input(String ear, String skin, String eye) {
        // engage skill only if a reward is possible
        if (axGamification.surplus(cost)){skill.input(ear, skin, eye);}
    }

    @Override
    public void output(Neuron noiron) {
        // charge reward if an algorithm is pending
        if (skill.pendingAlgorithm()) {axGamification.reward(cost);skill.output(noiron);}
    }
}
