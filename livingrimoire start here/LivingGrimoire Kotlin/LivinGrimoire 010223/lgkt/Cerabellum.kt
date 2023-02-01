package lgkt

open class Cerabellum {
    // runs an algorithm
    private var fin = 0
    var at = 0
        private set
    val failType: enumFail?
        get() = alg!!.algParts[at].failure("")
    private var incrementAt = false
    fun advanceInAlg() {
        if (incrementAt) {
            incrementAt = false
            at++
            if (at == fin) {
                isActive = false
            }
        }
    }

    var alg: Algorithm? = null
    var isActive = false
    var emot = ""
        private set

    fun setAlgorithm(algorithm: Algorithm): Boolean {
        if (!isActive && !algorithm.algParts.isEmpty()) {
            alg = algorithm
            at = 0
            fin = algorithm.size
            isActive = true
            emot = alg!!.algParts[at].myName() // updated line
            return false
        }
        return true
    }

    fun setActive(b1: Boolean): Boolean {
        return b1.also { isActive = it }
    }

    open fun act(ear: String, skin: String, eye: String): String {
        var axnStr = ""
        if (!isActive) {
            return axnStr
        }
        if (at < fin) {
            axnStr = alg!!.algParts[at].action(ear, skin, eye)
            emot = alg!!.algParts[at].myName()
            if (alg!!.algParts[at].completed()) {
                incrementAt = true
            }
        }
        return axnStr
    }

    val mutationLimitOfActiveAlgPart: Int
        get() = alg!!.algParts[at].getMutationLimit()
}