package auxiliary_modules

class TrgSnooze( //2 recomended
    private var maxrepeats: Int
) : TrGEV3() {
    // this boolean gate will return true per minute interval
    // max repeats times.
    private var repeats = 0
    private var snooze = true
    private var snoozeInterval = 5
    fun setSnoozeInterval(snoozeInterval: Int) {
        if (snoozeInterval in 2..10) {
            this.snoozeInterval = snoozeInterval
        }
    }

    fun setMaxrepeats(maxrepeats: Int) {
        this.maxrepeats = maxrepeats
        reset()
    }

    override fun reset() {
        // refill trigger
        // engage this code when an alarm clock was engaged to enable snoozing
        repeats = maxrepeats
    }

    override fun trigger(): Boolean {
        // trigger a snooze alarm?
        val minutes: Int = TimeUtils.minutesAsInt
        if (minutes % snoozeInterval != 0) {
            snooze = true
            return false
        }
        if (repeats > 0 && snooze) {
            snooze = false
            repeats--
            return true
        }
        return false
    }

    fun disable() {
        // engage this method to stop the snoozing
        repeats = 0
    }
}