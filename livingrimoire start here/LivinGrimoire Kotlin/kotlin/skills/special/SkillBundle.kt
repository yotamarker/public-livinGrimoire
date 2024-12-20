package skills.special

import auxiliary_modules.AXSkillBundle
import auxiliary_modules.AlgorithmV2
import auxiliary_modules.UniqueResponder
import livinGrimoire.AbsDictionaryDB
import livinGrimoire.Kokoro
import livinGrimoire.Skill
import java.util.*

open class SkillBundle : Skill() {
    protected val axSkillBundle: AXSkillBundle = AXSkillBundle()
    protected val notes: Hashtable<String, UniqueResponder> = object : Hashtable<String, UniqueResponder>() {
        init {
            put("triggers", UniqueResponder())
        }
    }
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

    open fun addSkill(skill: Skill) {
        axSkillBundle.addSkill(skill)
        for (i in 0..9) {
            notes["triggers"]!!.addResponse("grind " + skill.skillNotes("triggers"))
        }
    }
    open fun manualAddResponse(key: String, value: String) {
        notes.computeIfAbsent(key) { k: String ->
            UniqueResponder(
                value
            )
        }.addResponse(value)
    }
    open fun setDefaultNote() {
        notes["notes"] = UniqueResponder("a bundle of several skills")
    }

    override fun skillNotes(param: String): String {
        return if (notes.containsKey(param)) {
            notes[param]!!.getAResponse()
        } else "notes unavailable"
    }
}