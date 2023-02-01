package lgkt

open class DiSkillV2 {
    var kokoro =
        Kokoro(AbsDictionaryDB()) // consciousness, shallow ref class to enable interskill communications
    protected var diSkillUtils = DISkillUtils()
    protected var outAlg: Algorithm? = null // skills output

    // skill triggers and algorithmic logic
    open fun input(ear: String, skin: String, eye: String) {}

    // extraction of skill algorithm to run (if there is one)
    open fun output(noiron: Neuron) {
        if (outAlg != null) {
            noiron.algParts.add(outAlg!!)
            outAlg = null
        }
    }
}