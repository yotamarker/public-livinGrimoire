package com.yotamarker.lgkotlinfull.LGCore

import java.util.*

/* all action data goes through here
* detects negatives such as : repetition, pain on various levels and failures
* serves as a database for memories, convos and alg generations
* can trigger revenge algs
* checks for % of difference in input for exploration type algs
* */
class Kokoro(absDictionaryDB: AbsDictionaryDB) {
    var emot = ""
    var pain = Hashtable<String, Int>()
    var grimoireMemento: GrimoireMemento
    var toHeart = Hashtable<String, String>()
    var fromHeart = Hashtable<String, String>()
    var standBy = false
    fun getPain(BijuuName: String): Int {
        return pain.getOrDefault(BijuuName, 0)
    }

    init {
        grimoireMemento = GrimoireMemento(absDictionaryDB)
    }
}