package auxiliary_modules

import livinGrimoire.Algorithm

class AlgorithmV2(priority: Int, alg: Algorithm) {
    var priority = 4
    private var alg: Algorithm? = null

    init {
        this.priority = priority
        this.alg = alg
    }

    fun getAlg(): Algorithm? {
        return alg
    }

    fun setAlg(alg: Algorithm?) {
        this.alg = alg
    }
}