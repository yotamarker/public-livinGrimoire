package skills.special

import auxiliary_modules.AXLearnability
import auxiliary_modules.AlgorithmV2
import auxiliary_modules.SkillHubAlgDispenser
import livinGrimoire.AbsDictionaryDB
import livinGrimoire.Kokoro
import livinGrimoire.Skill
import java.util.*

open class SkillBranch(tolerance: Int) : Skill() {
    // unique skill used to bind similar skills
    /*
    * contains collection of skills
    * mutates active skill if detects conjuration
    * mutates active skill if algorithm results in
    * negative feedback
    * positive feedback negates active skill mutation
    * */
    private val skillRef = Hashtable<String, Int>()
    private val skillHub: SkillHubAlgDispenser = SkillHubAlgDispenser()
    private val ml: AXLearnability

    init {
        ml = AXLearnability(tolerance)
    }

    override fun input(ear: String, skin: String, eye: String) {
        // conjuration alg morph
        if (skillRef.contains(ear)) {
            skillHub.setActiveSkillWithMood(skillRef[ear]!!)
            setSimpleAlg("hmm")
        }
        // machine learning alg morph
        if (ml.mutateAlg(ear)) {
            skillHub.cycleActiveSkill()
            setSimpleAlg("hmm")
        }
        // alg engage
        val a1: AlgorithmV2 = skillHub.dispenseAlgorithm(ear, skin, eye) ?: return
        this.outAlg = a1.getAlg()
        this.outpAlgPriority = a1.priority
        ml.pendAlg()
    }

    fun addSkill(skill: Skill) {
        skillHub.addSkill(skill)
    }

    fun addReferencedSkill(skill: Skill, conjuration: String) {
        // the conjuration string will engage it's respective skill
        skillHub.addSkill(skill)
        skillRef[conjuration] = skillHub.size
    }

    // learnability params
    fun addDefcon(defcon: String) {
        ml.defcons.add(defcon)
    }

    fun addGoal(goal: String) {
        ml.goals.add(goal)
    }

    // while alg is pending, cause alg mutation ignoring learnability tolerance:
    fun addDefconLV5(defcon5: String) {
        ml.defcon5.add(defcon5)
    }

    override var kokoro: Kokoro = Kokoro(AbsDictionaryDB())
        set(value) {
            field = value
            kokoro.toHeart.put("dibicameral", "null")
        }
    override fun skillNotes(param: String): String {
        return skillHub.activeSkillRef().skillNotes(param)
    }
}