package com.yotamarker.lgkotlinfull.LGCore

import java.util.*


class ZeroTimeGate {
    // a gate that only opens x minutes after it has been set
    private var pause = 5
    private var openedGate = Date()
    private var checkPoint = Date()

    constructor(minutes: Int) : super() {
        pause = minutes
        try {
            Thread.sleep(100)
        } catch (e: InterruptedException) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    constructor() {}

    val isClosed: Boolean
        get() = openedGate.before(Date())
    val isOpen: Boolean
        get() = !openedGate.before(Date())

    fun open() {
        openedGate = addMinutesToJavaUtilDate(Date(), pause)
    }

    fun open(minutes: Int) {
        val now = Date()
        openedGate = addMinutesToJavaUtilDate(now, minutes)
    }

    private fun addMinutesToJavaUtilDate(date: Date, minutes: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.add(Calendar.MINUTE, minutes)
        return calendar.time
    }

    fun setPause(pause: Int) {
        if (pause < 60 && pause > 0) {
            this.pause = pause
        }
    }

    fun resetCheckPoint() {
        checkPoint = Date()
    }

    fun givenTwoDateTimesInJava8_whenDifferentiatingInSeconds_thenWeGetTen(): Int {
        val now = Date()
        val diff = now.time - checkPoint.time
        val diffSeconds = diff / 1000 % 60
        return diffSeconds.toInt()
    }
}