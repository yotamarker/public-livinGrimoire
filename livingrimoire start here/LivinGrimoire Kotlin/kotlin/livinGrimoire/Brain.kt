package livinGrimoire

/*********
 * intro *
 *
 *
 * up until now, the LivinGrimoire was on par with the matrix learn scene.
 * one line of code to add one skill.
 *
 * that is great, that is sci-fi turned real, that is the most significant coding achievement in the history of time.
 *
 * but hey why stop there? why only be on par with the matrix and the human brain ?
 * what is beyond the matrix level? you already know
 *
 * cyberpunk>the matrix.
 * one line of code to add a skill, but ALSO! 1 line of code to add a hardware capability.
 *
 *
 * Atributes*
 *
 *
 * the logicChobit is a Chobits attribute with logic skills. these skills have algorithmic logic,
 * and thinking patterns.
 *
 * the hardwareChobit is a Chobit attribute with hardware skills. these skills access the
 * hardware capabilities of the machine.
 * for example: output printing, sending mail, sending SMS, making a phone call, taking
 * a photo, accessing GPIO pins, opening a program, fetching the weather and so on.
 *
 *
 * special attributes*
 *
 *
 * in some cases the hardware chobit may want to send a message to the logic chobit,
 * for example to give feedback on hardware components. this is handled by the bodyInfo
 * String.
 *
 * the emot attribute is the chobit's current emotion.
 *
 * the logicChobitOutput is the chobit's last output.
 *
 *
 * hardware skill types*
 *
 *
 * assembly style: these skills are triggered by strings with certain wild card characters
 * for example: #open browser
 *
 * funnel: these are triggered by strings without wild cards.
 * for example: "hello world"->prints hello world
 *
 *
 * example use*
 *
 * DiSysOut is an example of a hardware skill
 *
 * see Brain main for example use of the cyberpunk Software Design Pattern
 */
class Brain {
    var logicChobit: Chobits
    var hardwareChobit: Chobits
    var emotion = ""
        private set
    var bodyInfo = ""
        private set
    var logicChobitOutput = ""
        private set

    init {
        logicChobit = Chobits()
        hardwareChobit = Chobits()
        hardwareChobit.kokoro = logicChobit.kokoro
    }

    fun doIt(ear: String, skin: String, eye: String) {
        logicChobitOutput = if (!bodyInfo.isEmpty()) {
            logicChobit.think(ear, bodyInfo, eye)
        } else {
            logicChobit.think(ear, skin, eye)
        }
        emotion = logicChobit.soulEmotion
        // case: hardware skill wishes to pass info to logical chobit
        bodyInfo = hardwareChobit.think(logicChobitOutput, skin, eye)
    }
    fun addLogicalSkill(skill: Skill) {
        logicChobit.addSkill(skill)
    }

    fun addHardwareSkill(skill: Skill) {
        hardwareChobit.addSkill(skill)
    }
}