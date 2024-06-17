package skills.special

import auxiliary_modules.AXSkillBundle
import auxiliary_modules.AlgorithmV2
import livinGrimoire.AbsDictionaryDB
import livinGrimoire.DiSkillV2
import livinGrimoire.Kokoro

class DiSkillBundle : DiSkillV2() {
    private val axSkillBundle: AXSkillBundle = AXSkillBundle()
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

    fun addSkill(skill: DiSkillV2) {
        axSkillBundle.addSkill(skill)
    }
}