package skills

import auxiliary_modules.AXGamification
import livinGrimoire.DiSkillV2
import livinGrimoire.Neuron

class GamificationN(skill: DiSkillV2, rewardBank: GamificationP) : DiSkillV2() {
    private val axGamification: AXGamification
    private var cost = 3
    private val skill: DiSkillV2

    init {
        this.skill = skill
        axGamification = rewardBank.getAxGamification()
    }

    fun setCost(cost: Int): GamificationN {
        this.cost = cost
        return this
    }

    override fun input(ear: String, skin: String, eye: String) {
        // engage skill only if a reward is possible
        if (axGamification.surplus(cost)) {
            skill.input(ear, skin, eye)
        }
    }

    override fun output(noiron: Neuron) {
        // charge reward if an algorithm is pending
        if (skill.pendingAlgorithm()) {
            axGamification.reward(cost)
            skill.output(noiron)
        }
    }
}