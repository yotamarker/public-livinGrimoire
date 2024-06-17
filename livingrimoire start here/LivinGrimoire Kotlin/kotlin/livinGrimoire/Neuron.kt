package livinGrimoire

import java.util.*

// used to transport algorithms to other classes
class Neuron {
    private val defcons = Hashtable<Int, ArrayList<Algorithm>>()

    init {
        for (i in 1..5) {
            defcons[i] = ArrayList()
        }
    }

    fun insertAlg(priority: Int, alg: Algorithm) {
        if (priority in 1..5) {
            if (defcons[priority]!!.size < 4) {
                defcons[priority]!!.add(alg)
            }
        }
    }

    fun getAlg(defcon: Int): Algorithm? {
        if (defcons[defcon]!!.size > 0) {
            val temp = defcons[defcon]!![0]
            defcons[defcon]!!.removeAt(0)
            return temp
        }
        return null
    }
}