package skills.special

import auxiliary_modules.AXGamification
import livinGrimoire.AbsDictionaryDB
import livinGrimoire.Skill
import livinGrimoire.Kokoro
import livinGrimoire.Neuron

class GamiPlus(skill: Skill, axGamification: AXGamification, gain: Int) : Skill() {
    // the grind side of the game, see GamificationN for the reward side
    private val gain: Int
    private val skill: Skill
    private val axGamification: AXGamification

    init {
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
    override var kokoro: Kokoro = Kokoro(AbsDictionaryDB())
        set(value) {
            skill.kokoro = kokoro
            field = value
        }
}