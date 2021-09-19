package com.yotamarker.lgkotlinfull.LGCore

import java.util.*

class TimeGate {
    // a gate that only opens x minutes after it has been set
    private var pause = 5 //minutes to keep gate closed
    private var openedGate = addMinutesToJavaUtilDate(Date(), pause)

    constructor(minutes: Int) : super() {
        try {
            Thread.sleep(100)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        pause = minutes
    }

    constructor() {}

    fun openGate() {
        openedGate = Date()
    }

    val isClosed: Boolean
        get() = !openedGate.before(Date())

    fun close() {
        openedGate = addMinutesToJavaUtilDate(Date(), pause)
    }

    fun close(minutes: Int) {
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
}