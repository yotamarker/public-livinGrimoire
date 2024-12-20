package skills.special

import auxiliary_modules.AXGamification
import auxiliary_modules.UniqueResponder
import livinGrimoire.Skill

class GamificationSkillBundle : SkillBundle() {
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

    fun addGrindSkill(skill: Skill) {
        axSkillBundle.addSkill(GamiPlus(skill, axGamification, gain))
        for (i in 0..9) {
            notes["triggers"]!!.addResponse("grind " + skill.skillNotes("triggers"))
        }
    }

    fun addCostlySkill(skill: Skill) {
        axSkillBundle.addSkill(GamiMinus(skill, axGamification, cost))
        for (i in 0..9) {
            notes["triggers"]!!.addResponse("grind " + skill.skillNotes("triggers"))
        }
    }
    override fun setDefaultNote() {
        notes["notes"] = UniqueResponder("a bundle of grind and reward skills")
    }
}