package Skills.special_skills;

import Auxiliary_Modules.AXGamification;
import LivinGrimoire.DiSkillV2;
import LivinGrimoire.Neuron;

public class GamiMinus extends DiSkillV2 {
    private final AXGamification axGamification;
    private final int cost;
    private final DiSkillV2 skill;

    public GamiMinus(DiSkillV2 skill, AXGamification axGamification, int cost) {
        skill.setKokoro(this.kokoro);
        this.skill = skill;
        this.axGamification = axGamification;
        this.cost = cost;
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
