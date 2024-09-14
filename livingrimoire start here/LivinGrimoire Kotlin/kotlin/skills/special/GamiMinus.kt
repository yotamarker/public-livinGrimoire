package skills.special

import auxiliary_modules.AXGamification
import livinGrimoire.AbsDictionaryDB
import livinGrimoire.Skill
import livinGrimoire.Kokoro
import livinGrimoire.Neuron

class GamiMinus(skill: Skill, axGamification: AXGamification, cost: Int) : Skill() {
    private val axGamification: AXGamification
    private val cost: Int
    private val skill: Skill

    init {
        this.skill = skill
        this.axGamification = axGamification
        this.cost = cost
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
    override var kokoro: Kokoro = Kokoro(AbsDictionaryDB())
        set(value) {
            skill.kokoro = kokoro
            field = value
        }
}