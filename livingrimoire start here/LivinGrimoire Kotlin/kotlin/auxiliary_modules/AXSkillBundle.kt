package auxiliary_modules

import livinGrimoire.*

class AXSkillBundle(vararg skillsParams: DiSkillV2) {
    private val skills: ArrayList<DiSkillV2> = ArrayList<DiSkillV2>()
    private val tempN: Neuron = Neuron()
    private var kokoro: Kokoro = Kokoro(AbsDictionaryDB())
    fun setKokoro(kokoro: Kokoro) {
        this.kokoro = kokoro
        for (skill in skills) {
            skill.kokoro = this.kokoro
        }
    }

    init {
        for (skill in skillsParams) {
            skill.kokoro = this.kokoro
            skills.add(skill)
        }
    }

    fun addSkill(skill: DiSkillV2): AXSkillBundle {
        // builder pattern
        skill.kokoro = this.kokoro
        skills.add(skill)
        return this
    }

    fun dispenseAlgorithm(ear: String, skin: String, eye: String): AlgorithmV2? {
        for (skill in skills) {
            skill.input(ear, skin, eye)
            skill.output(tempN)
            for (j in 1..5) {
                val temp: Algorithm? = tempN.getAlg(j)
                if (temp != null) {
                    return AlgorithmV2(j, temp)
                }
            }
        }
        return null
    }

    val size: Int
        get() = skills.size
}