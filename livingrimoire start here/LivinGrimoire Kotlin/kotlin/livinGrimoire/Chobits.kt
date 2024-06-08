package livinGrimoire

class Chobits : thinkable() {
    protected var dClasses = ArrayList<DiSkillV2>()
    var fusion: Fusion
        protected set
    protected var noiron: Neuron

    // use this for telepathic communication between different chobits objects
    // several chobits can use the same soul
    // this enables telepathic communications
    // between chobits in the same project
    var kokoro = Kokoro(AbsDictionaryDB()) // consciousness

    init {
        // c'tor
        fusion = Fusion()
        noiron = Neuron()
    }

    fun setDataBase(absDictionaryDB: AbsDictionaryDB?) {
        kokoro = Kokoro(absDictionaryDB)
    }

    fun addSkill(skill: DiSkillV2): Chobits {
        // add a skill (builder design patterned func))
        skill.kokoro = kokoro
        dClasses.add(skill)
        return this
    }

    fun clearSkills() {
        // remove all skills
        dClasses.clear()
    }

    fun addSkills(vararg skills: DiSkillV2) {
        for (skill in skills) {
            skill.kokoro = kokoro
            dClasses.add(skill)
        }
    }

    fun removeSkill(skill: DiSkillV2) {
        dClasses.remove(skill)
    }

    fun containsSkill(skill: DiSkillV2): Boolean {
        return dClasses.contains(skill)
    }

    override fun think(ear: String, skin: String, eye: String): String {
        for (dCls in dClasses) {
            inOut(dCls, ear, skin, eye)
        }
        fusion.loadAlgs(noiron)
        return fusion.runAlgs(ear, skin, eye)
    }

    val soulEmotion: String
        get() =// get the last active AlgPart name
        // the AP is an action, and it also represents
            // an emotion
            fusion.emot

    protected fun inOut(dClass: DiSkillV2, ear: String, skin: String, eye: String) {
        dClass.input(ear, skin, eye) // new
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