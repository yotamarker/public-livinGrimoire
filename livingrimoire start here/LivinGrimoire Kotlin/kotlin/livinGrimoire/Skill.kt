package livinGrimoire

open class Skill {
    open lateinit var kokoro: Kokoro // consciousness, shallow ref class to enable interskill communications
    protected var outAlg: Algorithm? = null // skills output
    protected var outpAlgPriority = -1 // defcon 1->5

    // skill triggers and algorithmic logic
    open fun input(ear: String, skin: String, eye: String) {}

    // extraction of skill algorithm to run (if there is one)
    open fun output(noiron: Neuron) {
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
        outAlg = simpleVerbatimAlgorithm(*sayThis)
        outpAlgPriority = priority // 1->5 1 is the highest algorithm priority
    }

    protected fun setSimpleAlg(vararg sayThis: String) {
        // based on the setVerbatimAlg method
        // build a simple output algorithm to speak string by string per think cycle
        // uses varargs param
        outAlg = simpleVerbatimAlgorithm(*sayThis)
        outpAlgPriority = 4 // 1->5 1 is the highest algorithm priority
    }

    protected fun setVerbatimAlgFromList(priority: Int, sayThis: ArrayList<String>) {
        // build a simple output algorithm to speak string by string per think cycle
        // uses list param
        outAlg = algBuilder(APVerbatim(sayThis))
        outpAlgPriority = priority // 1->5 1 is the highest algorithm priority
    }

    protected fun algPartsFusion(priority: Int, vararg algParts: Mutatable) {
        // build a custom algorithm out of a chain of algorithm parts(actions)
        outAlg = algBuilder(*algParts)
        outpAlgPriority = priority // 1->5 1 is the highest algorithm priority
    }

    fun pendingAlgorithm(): Boolean {
        // is an algorithm pending?
        return outAlg != null
    }

    // algorithm build methods
    fun algBuilder(vararg algParts: Mutatable): Algorithm {
        // returns an algorithm built with the algPart varargs
        val algParts1 = ArrayList<Mutatable>()
        for (i in algParts.indices) {
            algParts1.add(algParts[i])
        }
        return Algorithm(algParts1)
    }

    // String based algorithm building methods
    fun simpleVerbatimAlgorithm(vararg sayThis: String): Algorithm {
        // returns an algorithm that says the sayThis Strings verbatim per think cycle
        return algBuilder(APVerbatim(*sayThis))
    }

    fun strContainsList(str1: String, items: ArrayList<String>): String {
        // returns the 1st match between words in a string and values in a list.
        for (temp in items) {
            if (str1.contains(temp)) {
                return temp
            }
        }
        return ""
    }
    open fun skillNotes(param: String): String {
        return "notes unknown"
    }
}