package auxiliary_modules

import java.util.*

class DrawRnd(vararg values: String) {
    // draw a random element, then take said element out
    private var strings = ArrayList<String>()
    private val stringsSource = ArrayList<String>()
    private val rand = Random()
    fun addElement(element: String) {
        strings.add(element)
        stringsSource.add(element)
    }

    fun draw(): String {
        if (strings.isEmpty()) {
            return ""
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

    private val tc = LGTypeConverter()

    init {
        for (i in values.indices) {
            strings.add(values[i])
            stringsSource.add(values[i])
        }
    }

    fun drawAsInt(): Int {
        if (strings.isEmpty()) {
            return 0
        }
        val rand = Random()
        val x = rand.nextInt(strings.size)
        val element = strings[x]
        strings.removeAt(x)
        return tc.convertToInt(element)
    }

    fun reset() {
        val dc = DeepCopier()
        strings = dc.copyList(stringsSource)
    }

    val isEmptied: Boolean
        get() = strings.isEmpty()
    fun renewableDraw(): String? {
        // using this method assumes at least 1 element was added to the module
        // otherwise you can expect an error!
        if (strings.isEmpty()) {
            reset()
        }
        val x = rand.nextInt(strings.size)
        val element = strings[x]
        strings.removeAt(x)
        return element
    }
}