package com.yotamarker.lgkotlinfull.LGCore

import java.util.*

class DISkillUtils {
    fun verbatimGorithm(itte: AbsAlgPart): Algorithm {
        // returns a simple algorithm containing 1 alg part
        val representation = "util"
        val algParts1 = ArrayList<AbsAlgPart>()
        algParts1.add(itte)
        return Algorithm("util", representation, algParts1)
    }

    fun verbatimGorithm(algMarker: String, itte: AbsAlgPart): Algorithm {
        // returns a simple algorithm for saying sent parameter
        val representation = "util"
        val algParts1 = ArrayList<AbsAlgPart>()
        algParts1.add(itte)
        return Algorithm("util", representation, algParts1)
    }

    fun customizedVerbatimGorithm(algMarker: String, itte: AbsAlgPart): Algorithm {
        // the most stable and advanced algorithm builder
        // returns a simple algorithm containing 1 alg part
        val representation = "r_$algMarker"
        val algParts1 = ArrayList<AbsAlgPart>()
        algParts1.add(itte)
        return Algorithm(algMarker, representation, algParts1)
    }

    fun customizedVerbatimGorithm(algMarker: String, vararg itte: AbsAlgPart): Algorithm {
        // the most stable and advanced algorithm builder
        // returns a simple algorithm containing 1 alg part
        val representation = "r_$algMarker"
        val algParts1 = ArrayList<AbsAlgPart>()
        for (i in 0 until itte.size) {
            algParts1.add(itte[i])
        }
        return Algorithm(algMarker, representation, algParts1)
    }

    fun simpleVerbatimAlgorithm(algMarker: String, vararg sayThis: String): Algorithm {
        // returns alg that says the word string (sayThis)
        return customizedVerbatimGorithm(algMarker, APVerbatim(*sayThis))
    }

    fun simpleCloudiandVerbatimAlgorithm(cldBool: CldBool, algMarker: String, vararg sayThis: String): Algorithm {
        // returns alg that says the word string (sayThis)
        return customizedVerbatimGorithm(algMarker, APCldVerbatim(cldBool!!, *sayThis))
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
}