package lgkt

import java.util.*


class Chobits : thinkable() {
    protected var dClasses = ArrayList<DiSkillV2>()

    // think cycles run duration per algorithm
    // use this method for saving run times if you wish
    // algDurations are shallow ref to Fusions' algDurations
    // shorter algDurations give algorithms run priority in case several algorithms(sent by skills) want to run
    // at the same time
    // think cycles run duration per algorithm
    // use this method for saving run times if you wish
    // algorithms fusion (polymarization)
    var algDurations = Hashtable<String, Int>()
    var fusion: Fusion
        protected set

    // region essential DClasses
    // endregion
    protected var noiron: Neuron

    // use this for telepathic communication between different chobits objects
    // several chobits can use the same soul
    // this enables telepathic communications
    // between chobits in the same project
    // added :
    var kokoro = Kokoro(AbsDictionaryDB()) // consciousness
    protected var lastOutput = ""

    // standBy phase 260320
    protected var timeGate = TimeGate()

    init {
        // c'tor
        fusion = Fusion(algDurations)
        noiron = Neuron()
    }

    /* set the chobit database
        the database is built as a key value dictionary
        the database can be used with by the Kokoro attribute
    * */
    fun setDataBase(absDictionaryDB: AbsDictionaryDB) {
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

    fun setPause(pause: Int) {
        // set standby timegate pause.
        // pause time without output from the chobit
        // means the standby attribute will be true for a moment.
        // it is the equivelant of the chobit being bored
        // the standby attribute can be accessed via the kokoro
        // object within a skill if needed
        timeGate.setPause(pause)
    }

    override fun think(ear: String, skin: String, eye: String): String {
        // the input will be processed by the chobits' skills
        var ear = ear
        ear = translateIn(ear)
        for (dCls in dClasses) {
            inOut(dCls, ear, skin, eye)
        }
        fusion.setAlgQueue(noiron)
        return translateOut(fusion.act(ear, skin, eye))
    }

    protected fun inOut(dClass: DiSkillV2, ear: String, skin: String, eye: String) {
        dClass.input(ear, skin, eye) // new
        dClass.output(noiron)
    }

    protected fun translateIn(earIn: String): String {
        // makes sure the chobit doesn't feedback on her own output
        return if (earIn == lastOutput) {
            ""
        } else earIn
    }

    protected fun translateOut(outResult: String): String {
        // save last output served
        if (!outResult.isEmpty()) {
            lastOutput = outResult
            timeGate.openGate()
            kokoro.standby = false
        } else {
            if (timeGate.isClosed) {
                kokoro.standby = true
                timeGate.openGate()
            } else {
                kokoro.standby = false
            }
        }
        return outResult
    }

}