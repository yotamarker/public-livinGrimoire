package Skills.special_skills;

import Auxiliary_Modules.AXGamification;
import LivinGrimoire.DiSkillV2;
import LivinGrimoire.Kokoro;
import LivinGrimoire.Neuron;

public class GamiPlus extends DiSkillV2 {
    // the grind side of the game, see GamificationN for the reward side
    private final int gain;
    private final DiSkillV2 skill;
    private final AXGamification axGamification;

    public GamiPlus(DiSkillV2 skill,AXGamification axGamification, int gain) {
        this.skill = skill;
        this.axGamification = axGamification;
        this.gain = gain;
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

    @Override
    public void setKokoro(Kokoro kokoro) {
        this.skill.setKokoro(kokoro);
    }
}
