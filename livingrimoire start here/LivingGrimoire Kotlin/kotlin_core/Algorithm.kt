package com.yotamarker.lgkotlinfull.LGCore

import java.util.*


// a step by step plan to achieve a goal
class Algorithm(// *constract with string and goal
        var goal: String, val representation: String, algParts: ArrayList<AbsAlgPart>) : Comparable<Algorithm> {
    var algParts = ArrayList<AbsAlgPart>()
    val size: Int
        get() = algParts.size

     fun clone(): Algorithm {
        val parts = ArrayList<AbsAlgPart>()
        for (absAlgPart in algParts) {
            parts.add(absAlgPart.clone())
        }
        return Algorithm(goal, representation, parts)
    }

     override operator fun compareTo(o: Algorithm): Int {
        // TODO Auto-generated method stub
        return 0
    }

    init {
        this.algParts = algParts
    }
}