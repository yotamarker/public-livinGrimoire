package auxiliary_modules

class Cron(
    startTime: String, // triggers true, limit times, after initial time, and every minutes interval
    // counter resets at initial time, assuming trigger method was run
    private var minutes // minute interval between triggerings
    : Int, limit: Int
) : TrGEV3() {
    private val trgTime: TrgTime
    private var timeStamp = ""
    private var initialTimeStamp = ""
    private var limit: Int
    var counter = 0
        private set

    init {
        timeStamp = startTime
        initialTimeStamp = startTime
        trgTime = TrgTime()
        trgTime.setTime(startTime)
        this.limit = limit
        if (limit < 0) {
            this.limit = 1
        }
    }

    fun getLimit(): Int {
        return limit
    }

    fun setLimit(limit: Int) {
        if (limit > -1) {
            this.limit = limit
        }
    }

    fun setMinutes(minutes: Int) {
        if (minutes > -1) {
            this.minutes = minutes
        }
    }

    override fun trigger(): Boolean {
        // delete counter = 0 if you don't want the trigger to work the next day
        if (counter == limit) {
            trgTime.setTime(initialTimeStamp)
            counter = 0
            return false
        }
        if (trgTime.alarm()) {
            timeStamp = TimeUtils.getFutureInXMin(minutes)
            trgTime.setTime(timeStamp)
            counter++
            return true
        }
        return false
    }

    fun triggerWithoutRenewal(): Boolean {
        // delete counter = 0 if you don't want the trigger to work the next day
        if (counter == limit) {
            trgTime.setTime(initialTimeStamp)
            return false
        }
        if (trgTime.alarm()) {
            timeStamp = TimeUtils.getFutureInXMin(minutes)
            trgTime.setTime(timeStamp)
            counter++
            return true
        }
        return false
    }

    override fun reset() {
        // manual trigger reset
        counter = 0
    }

    fun setStartTime(t1: String) {
        initialTimeStamp = t1
        timeStamp = t1
        trgTime.setTime(t1)
        counter = 0
    }

    fun turnOff() {
        counter = limit
    }
}