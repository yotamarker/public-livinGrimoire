package auxiliary_modules

class TrgEveryNMinutes(
    startTime: String, // trigger returns true every minutes interval, post start time
    private var minutes // minute interval between triggerings
    : Int
) : TrGEV3() {
    private val trgTime: TrgTime
    private var timeStamp = ""

    init {
        timeStamp = startTime
        trgTime = TrgTime()
        trgTime.setTime(startTime)
    }

    fun setMinutes(minutes: Int) {
        if (minutes > -1) {
            this.minutes = minutes
        }
    }

    override fun trigger(): Boolean {
        if (trgTime.alarm()) {
            timeStamp = TimeUtils.getFutureInXMin(minutes)
            trgTime.setTime(timeStamp)
            return true
        }
        return false
    }

    override fun reset() {
        timeStamp = TimeUtils.currentTimeStamp
    }
}