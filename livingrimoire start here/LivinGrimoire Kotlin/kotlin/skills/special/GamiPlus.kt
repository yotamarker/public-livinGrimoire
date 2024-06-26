package skills.special

import auxiliary_modules.AXGamification
import livinGrimoire.DiSkillV2
import livinGrimoire.Neuron

class GamiPlus(skill: DiSkillV2, axGamification: AXGamification, gain: Int) : DiSkillV2() {
    // the grind side of the game, see GamificationN for the reward side
    private val gain: Int
    private val skill: DiSkillV2
    private val axGamification: AXGamification

    init {
        skill.kokoro = this.kokoro
        this.skill = skill
        this.axGamification = axGamification
        this.gain = gain
    }

    override fun input(ear: String, skin: String, eye: String) {
        skill.input(ear, skin, eye)
    }

    override fun output(noiron: Neuron) {
        // skill activation increases gaming credits
        if (skill.pendingAlgorithm()) {
            axGamification.incrementBy(gain)
        }
        skill.output(noiron)
    }
}