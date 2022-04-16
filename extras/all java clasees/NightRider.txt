package com.yotamarker.lgkotlin1

class NightRider {
    private var display = "---------------"
    private val DISPLAYPRIME = display
    private var i = 0
    private var dir: Byte = 1
    private val char1 = '-'
    private val char2 = '+'
    private var mode = 1
    private var siren1 = ""
    private var siren2 = ""
    private var sirenMode = false
    private var battleMode = ""
    fun setMode(mode: Int) {
        if (mode < 1 || mode > 10) {
            this.mode = 1
            return
        }
        this.mode = mode
    }
    fun setMode(mode: String) {
        when (mode) {
            "2" -> setMode(2)
            "3" -> setMode(3)
            "4" -> setMode(4)
            else -> {
            }
        }
    }
    fun getDisplay(): String {
        var result = ""
        when (mode) {
            1 -> result = mode1()
            2 -> result = mode2()
            3 -> result = mode3()
            4 -> result = mode4()
            5 -> result = mode5()
            6 -> result = mode6()
            7 -> result = mode7()
            8 -> result = mode8()
            9 -> result = mode9()
            10 -> result = mode10()
            else -> {
            }
        }
        return result
    }

    private fun mode1(): String { // default night rider mode
        display = display.substring(0, i) + char1 + display.substring(i + 1)
        i += dir.toInt()
        display = display.substring(0, i) + char2 + display.substring(i + 1)
        if (i == display.length - 1 || i == 0) {
            dir = (dir * -1).toByte()
        }
        return display
    }

    private fun mode2(): String { // wind shield wiper mode
        val half = display.length / 2
        display = display.substring(0, i) + char1 + display.substring(i + 1)
        display = display.substring(0, i + half) + char1 + display.substring(i + 1 + half)
        i += dir.toInt()
        display = display.substring(0, i) + char2 + display.substring(i + 1)
        display = display.substring(0, i + half) + char2 + display.substring(i + half + 1)
        if (i == display.length / 2 - 1 || i == 0) {
            dir = (dir * -1).toByte()
        }
        return display
    }

    private fun mode3(): String { // hilix mode
        val max = display.length - 1
        display = display.substring(0, i) + char1 + display.substring(i + 1)
        display = display.substring(0, max - i) + char1 + display.substring(max - i + 1)
        i += dir.toInt()
        display = display.substring(0, i) + char2 + display.substring(i + 1)
        display = display.substring(0, max - i) + char2 + display.substring(max - i + 1)
        if (i == display.length / 2 - 1 || i == 0) {
            dir = (dir * -1).toByte()
        }
        return display
    }

    private fun mode4(): String { // beads mode
        if (i % 2 == 0) {
            display = display.substring(0, i) + char1 + display.substring(i + 1)
        }
        i += dir.toInt()
        if (i == display.length - 1 || i == 0) {
            display = DISPLAYPRIME
        }
        display = display.substring(0, i) + char2 + display.substring(i + 1)
        if (i == display.length - 1 || i == 0) {
            dir = (dir * -1).toByte()
        }
        return display
    }

    private fun mode5(): String { // siren mode
        sirenMode = !sirenMode
        return if (sirenMode) {
            siren1 + siren2
        } else {
            siren2 + siren1
        }
    }

    private fun mode6(): String { // fill and clear mode
        i += dir.toInt()
        if (i == display.length - 1 || i == 0) {
            display = DISPLAYPRIME
        }
        display = display.substring(0, i) + char2 + display.substring(i + 1)
        if (i == display.length - 1 || i == 0) {
            dir = (dir * -1).toByte()
        }
        return display
    }

    private fun mode7(): String { // battle mode !!!!!!
        sirenMode = !sirenMode
        return if (sirenMode) {
            DISPLAYPRIME
        } else {
            battleMode
        }
    }

    private fun mode8(): String { // fat mode _000----
        var temp = ""
        display = display.substring(0, i) + char1 + display.substring(i + 1)
        i += dir.toInt()
        display = display.substring(0, i) + char2 + display.substring(i + 1)
        temp = if (i > 0) {
            display.substring(0, i - 1) + char2 + display.substring(i - 1 + 1)
        } else {
            display
        }
        if (i == display.length - 1 || i == 0) {
            dir = (dir * -1).toByte()
        }
        return temp
    }

    private fun mode9(): String { // L to R
        display = display.substring(0, i) + char1 + display.substring(i + 1)
        if (i == display.length - 1) {
            i = -1
        }
        i += dir.toInt()
        display = display.substring(0, i) + char2 + display.substring(i + 1)
        return display
    }

    private fun mode10(): String { // R to L
        display = display.substring(0, i) + char1 + display.substring(i + 1)
        if (i == 0) {
            i = display.length
        }
        i += -1
        display = display.substring(0, i) + char2 + display.substring(i + 1)
        return display
    }

    init {
        for (i in 0 until display.length / 2) {
            siren1 += char1.toString()
            siren2 += char2.toString()
            battleMode += "!!"
        }
    }
}
