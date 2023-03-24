package com.yotamarker.lgkotlinfull.LGCore


open class Cerabellum {
    // runs an algorithm
    private var fin = 0
    var at = 0
        private set
    val failType: enumFail
        get() = alg!!.algParts.get(at).failure("")
        //get() = alg.getAlgParts().get(at).failure("")
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
        if (!isActive && algorithm.algParts != null) {
            alg = algorithm
            at = 0
            fin = algorithm.size
            isActive = true
            emot = alg!!.algParts.get(at).myName()
            return false
        }
        return true
    }

    open fun act(ear: String, skin: String, eye: String): String {
        var axnStr = ""
        if (!isActive) {
            return axnStr
        }
        if (at < fin) {
            axnStr = alg!!.algParts.get(at).action(ear, skin, eye)
            emot = alg!!.algParts.get(at).myName()
            if (alg!!.algParts.get(at).completed()!!) {
                incrementAt = true
                // at++;
                // if (at == fin) {
                // isActive = false;
                // }
            }
        }
        return axnStr
    }

    val mutationLimitOfActiveAlgPart: Int
        get() = alg!!.algParts.get(at).getMutationLimit()
}