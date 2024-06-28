package skills.special

import auxiliary_modules.AXGamification
import livinGrimoire.DiSkillV2

class DiGamificationSkillBundle : DiSkillBundle() {
    val axGamification = AXGamification()
    private var gain = 1
    private var cost = 2
    fun setGain(gain: Int) {
        if (gain > 0) {
            this.gain = gain
        }
    }

    fun setCost(cost: Int) {
        if (cost > 0) {
            this.cost = cost
        }
    }

    fun addGrindSkill(skill: DiSkillV2) {
        axSkillBundle.addSkill(GamiPlus(skill, axGamification, gain))
    }

    fun addCostlySkill(skill: DiSkillV2) {
        axSkillBundle.addSkill(GamiMinus(skill, axGamification, cost))
    }
}