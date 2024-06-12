package auxiliary_modules

import livinGrimoire.Algorithm
import java.util.*

// (*)Algorithm Dispensers
class AlgDispenser(vararg algorithms: Algorithm) {
    // super class to output an algorithm out of a selection of algorithms
    private val algs: ArrayList<Algorithm> = ArrayList<Algorithm>()
    private var activeAlg = 0
    private val rand = Random()

    init {
        for (alg in algorithms) {
            algs.add(alg)
        }
    }

    fun addAlgorithm(alg: Algorithm): AlgDispenser {
        // builder pattern
        algs.add(alg)
        return this
    }

    fun dispenseAlgorithm(): Algorithm {
        return algs[activeAlg]
    }

    fun rndAld(): Algorithm {
        // return a random algorithm
        return algs[rand.nextInt(algs.size)]
    }

    fun moodAlg(mood: Int) {
        // set output algorithm based on number representing mood
        if (mood > -1 && mood < algs.size) {
            activeAlg = mood
        }
    }

    fun algUpdate(mood: Int, alg: Algorithm) {
        // update an algorithm
        if (!(mood > -1 && mood < algs.size)) {
            return
        }
        algs[mood] = alg
    }

    fun algRemove(mood: Int) {
        // remove an algorithm
        if (!(mood > -1 && mood < algs.size)) {
            return
        }
        algs.removeAt(mood)
    }

    fun cycleAlg() {
        activeAlg++
        if (activeAlg == algs.size) {
            activeAlg = 0
        }
    }
}