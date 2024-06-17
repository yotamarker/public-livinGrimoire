package skills.special

import auxiliary_modules.AXGamification
import livinGrimoire.DiSkillV2
import livinGrimoire.Neuron

class GamificationP(skill: DiSkillV2) : DiSkillV2() {
    // the grind side of the game, see GamificationN for the reward side
    private var gain = 1
    private val skill: DiSkillV2
    private val axGamification: AXGamification = AXGamification()

    init {
        this.skill = skill
    }

    fun setGain(gain: Int) {
        if (gain > 0) {
            this.gain = gain
        }
    }

    fun getAxGamification(): AXGamification {
        // shallow ref
        return axGamification
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