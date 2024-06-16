package Skills.special_skills;

import Auxiliary_Modules.AXGamification;
import LivinGrimoire.DiSkillV2;
import LivinGrimoire.Neuron;

public class GamificationP extends DiSkillV2 {
    // the grind side of the game, see GamificationN for the reward side
    private int gain = 1;
    private DiSkillV2 skill;
    private AXGamification axGamification = new AXGamification();

    public GamificationP(DiSkillV2 skill) {
        this.skill = skill;
    }

    public void setGain(int gain) {
        if (gain >0){
            this.gain = gain;}
    }

    public AXGamification getAxGamification() {
        // shallow ref
        return axGamification;
    }

    @Override
    public void input(String ear, String skin, String eye) {
        skill.input(ear, skin, eye);
    }

    @Override
    public void output(Neuron noiron) {
        // skill activation increases gaming credits
        if (skill.pendingAlgorithm()) {axGamification.incrementBy(gain);}
        skill.output(noiron);
    }
}
