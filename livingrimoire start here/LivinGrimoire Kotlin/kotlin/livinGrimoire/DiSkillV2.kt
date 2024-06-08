package livinGrimoire

open class DiSkillV2 {
    public var kokoro =
        Kokoro(AbsDictionaryDB()) // consciousness, shallow ref class to enable interskill communications
    protected var diSkillUtils = DISkillUtils()
    protected var outAlg: Algorithm? = null // skills output
    protected var outpAlgPriority = -1 // defcon 1->5

    // skill triggers and algorithmic logic
    open fun input(ear: String, skin: String, eye: String) {}

    // extraction of skill algorithm to run (if there is one)
    fun output(noiron: Neuron) {
        if (outAlg != null) {
            noiron.insertAlg(outpAlgPriority, outAlg!!)
            outpAlgPriority = -1
            outAlg = null
        }
    }

    // in skill algorithm building shortcut methods:
    protected fun setVerbatimAlg(priority: Int, vararg sayThis: String) {
        // build a simple output algorithm to speak string by string per think cycle
        // uses varargs param
        outAlg = diSkillUtils.simpleVerbatimAlgorithm(*sayThis)
        outpAlgPriority = priority // 1->5 1 is the highest algorithm priority
    }

    protected fun setSimpleAlg(vararg sayThis: String) {
        // based on the setVerbatimAlg method
        // build a simple output algorithm to speak string by string per think cycle
        // uses varargs param
        outAlg = diSkillUtils.simpleVerbatimAlgorithm(*sayThis)
        outpAlgPriority = 4 // 1->5 1 is the highest algorithm priority
    }

    protected fun setVerbatimAlgFromList(priority: Int, sayThis: ArrayList<String>) {
        // build a simple output algorithm to speak string by string per think cycle
        // uses list param
        outAlg = diSkillUtils.algBuilder(APVerbatim(sayThis))
        outpAlgPriority = priority // 1->5 1 is the highest algorithm priority
    }

    protected fun algPartsFusion(priority: Int, vararg algParts: Mutatable) {
        // build a custom algorithm out of a chain of algorithm parts(actions)
        outAlg = diSkillUtils.algBuilder(*algParts)
        outpAlgPriority = priority // 1->5 1 is the highest algorithm priority
    }

    fun pendingAlgorithm(): Boolean {
        // is an algorithm pending?
        return outAlg != null
    }
}