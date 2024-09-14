package skills.special

import auxiliary_modules.AXSkillBundle
import auxiliary_modules.AlgorithmV2
import livinGrimoire.AbsDictionaryDB
import livinGrimoire.Skill
import livinGrimoire.Kokoro

open class SkillBundle : Skill() {
    protected val axSkillBundle: AXSkillBundle = AXSkillBundle()
    override fun input(ear: String, skin: String, eye: String) {
        val a1: AlgorithmV2 = axSkillBundle.dispenseAlgorithm(ear, skin, eye) ?: return
        this.outAlg = a1.getAlg()
        this.outpAlgPriority = a1.priority
    }

    override var kokoro: Kokoro = Kokoro(AbsDictionaryDB())
        set(value) {
            field = value
            kokoro.toHeart.put("dibicameral", "null")
        }

    fun addSkill(skill: Skill) {
        axSkillBundle.addSkill(skill)
    }
}