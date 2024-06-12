package auxiliary_modules

import java.util.*

class TimeGate {
    // a gate that only stays open for x minutes after it has been set open
    // open gate returns true
    // closed state gate returns false
    // the gate starts closed
    private var pause = 5 //minutes to keep gate closed

    //private Date openedGate = addMinutesToJavaUtilDate(new Date(), pause);
    private var openedGate = Date()

    constructor(minutes: Int) : super() {
        try {
            Thread.sleep(100)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        pause = minutes
    }

    constructor() {
        try {
            Thread.sleep(100)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    fun setPause(pause: Int) {
        if (pause < 60 && pause > 0) {
            this.pause = pause
        }
    }

    fun openGate() {
        // the gate will stay open for pause minutes
        openedGate = addMinutesToJavaUtilDate(Date(), pause)
    }

    fun close() {
        openedGate = Date()
    }

    val isClosed: Boolean
        get() = openedGate.before(Date())
    val isOpen: Boolean
        get() = !isClosed

    fun openGate(minutes: Int) {
        val now = Date()
        openedGate = addMinutesToJavaUtilDate(now, minutes)
    }

    private fun addMinutesToJavaUtilDate(date: Date, minutes: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.add(Calendar.MINUTE, minutes)
        return calendar.time
    }

    private fun addSecondsToJavaUtilDate(date: Date, seconds: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.add(Calendar.SECOND, seconds)
        return calendar.time
    }

    fun openGateforNSeconds(n: Int) {
        openedGate = addSecondsToJavaUtilDate(Date(), n)
    }
}