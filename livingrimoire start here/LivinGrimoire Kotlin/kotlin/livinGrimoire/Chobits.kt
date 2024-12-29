package livinGrimoire

class Chobits {
    var dClasses = ArrayList<Skill>()
    var fusion: Fusion
        protected set
    protected var noiron: Neuron

    // use this for telepathic communication between different chobits objects
    // several chobits can use the same soul
    // this enables telepathic communications
    // between chobits in the same project
    var kokoro = Kokoro(AbsDictionaryDB()) // consciousness
    private var isThinking = false
    private val awareSkills = ArrayList<Skill>()

    init {
        // c'tor
        fusion = Fusion()
        noiron = Neuron()
    }

    fun setDataBase(absDictionaryDB: AbsDictionaryDB) {
        kokoro = Kokoro(absDictionaryDB)
    }

    fun addSkill(skill: Skill): Chobits {
        // add a skill (builder design patterned func))
        if (isThinking) {
            return this
        }
        skill.kokoro = kokoro
        dClasses.add(skill)
        return this
    }

    fun addSkillAware(skill: Skill): Chobits {
        // add a skill with Chobit Object in their constructor
        skill.kokoro = kokoro
        awareSkills.add(skill)
        return this
    }

    fun clearSkills() {
        // remove all skills
        if (isThinking) {
            return
        }
        dClasses.clear()
    }

    fun addSkills(vararg skills: Skill) {
        if (isThinking) {
            return
        }
        for (skill in skills) {
            skill.kokoro = kokoro
            dClasses.add(skill)
        }
    }

    fun removeSkill(skill: Skill) {
        if (isThinking) {
            return
        }
        dClasses.remove(skill)
    }

    fun containsSkill(skill: Skill): Boolean {
        return dClasses.contains(skill)
    }

    fun think(ear: String, skin: String, eye: String): String {
        isThinking = true
        for (dCls in dClasses) {
            inOut(dCls, ear, skin, eye)
        }
        isThinking = false
        for (dCls2 in awareSkills) {
            inOut(dCls2, ear, skin, eye)
        }
        fusion.loadAlgs(noiron)
        return fusion.runAlgs(ear, skin, eye)
    }

    val soulEmotion: String
        get() =// get the last active AlgPart name
        // the AP is an action, and it also represents
            // an emotion
            fusion.emot

    protected fun inOut(dClass: Skill, ear: String?, skin: String?, eye: String?) {
        dClass.input(ear!!, skin!!, eye!!) // new
        dClass.output(noiron)
    }

    val skillList: ArrayList<String>
        get() {
            val result = ArrayList<String>()
            for (skill in dClasses) {
                result.add(skill.javaClass.simpleName)
            }
            return result
        }
}