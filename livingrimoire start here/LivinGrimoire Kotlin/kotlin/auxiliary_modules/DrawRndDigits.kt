package auxiliary_modules

import livinGrimoire.DeepCopier
import java.util.*

class DrawRndDigits(vararg values: Int) {
    // draw a random integer, then take said element out
    private var strings = ArrayList<Int>()
    private val stringsSource = ArrayList<Int>()
    private val rand = Random()

    init {
        for (i in values.indices) {
            strings.add(values[i])
            stringsSource.add(values[i])
        }
    }

    fun addElement(element: Int) {
        strings.add(element)
        stringsSource.add(element)
    }

    fun draw(): Int {
        if (strings.isEmpty()) {
            return -1
        }
        val x = rand.nextInt(strings.size)
        val element = strings[x]
        strings.removeAt(x)
        return element
    }

    fun getSimpleRNDNum(bound: Int): Int {
        // return 0->bound-1
        return rand.nextInt(bound)
    }

    fun reset() {
        val dc = DeepCopier()
        strings = dc.copyListOfInts(stringsSource)
    }
}