package livinGrimoire

class Cerabellum {
    // runs an algorithm
    private var fin = 0
    var at = 0
        private set
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
        private set
    var emot = ""
        private set

    fun setAlgorithm(algorithm: Algorithm): Boolean {
        if (!isActive && !algorithm.algParts.isEmpty()) {
            alg = algorithm
            at = 0
            fin = algorithm.size
            isActive = true
            emot = alg!!.algParts.get(at).myName() // updated line
            return false
        }
        return true
    }

    fun act(ear: String, skin: String, eye: String): String {
        var axnStr = ""
        if (!isActive) {
            return axnStr
        }
        if (at < fin) {
            axnStr = alg!!.algParts.get(at).action(ear, skin, eye)
            emot = alg!!.algParts.get(at).myName()
            if (alg!!.algParts.get(at).completed()) {
                incrementAt = true
            }
        }
        return axnStr
    }

    fun deActivation() {
        isActive = isActive && !alg!!.algParts.get(at).algKillSwitch
    }
}