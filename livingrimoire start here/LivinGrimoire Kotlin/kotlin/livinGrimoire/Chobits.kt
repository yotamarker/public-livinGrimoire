package livinGrimoire

class Chobits {
    protected var dClasses = ArrayList<Skill>()
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

    fun addSkill(skill: Skill): Chobits {
        // add a skill (builder design patterned func))
        skill.kokoro = kokoro
        dClasses.add(skill)
        return this
    }

    fun clearSkills() {
        // remove all skills
        dClasses.clear()
    }

    fun addSkills(vararg skills: Skill) {
        for (skill in skills) {
            skill.kokoro = kokoro
            dClasses.add(skill)
        }
    }

    fun removeSkill(skill: Skill) {
        dClasses.remove(skill)
    }

    fun containsSkill(skill: Skill): Boolean {
        return dClasses.contains(skill)
    }

    fun think(ear: String?, skin: String?, eye: String?): String {
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