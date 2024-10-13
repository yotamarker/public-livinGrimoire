package auxiliary_modules

import java.util.*

class TrgMinute : TrGEV3 {
    // trigger true at minute once per hour
    private var hour1 = -1
    var minute: Int

    constructor() {
        val rand = Random()
        minute = rand.nextInt(60)
    }

    constructor(minute: Int) {
        this.minute = minute
    }

    override fun trigger(): Boolean {
        val tempHour: Int = TimeUtils.hoursAsInt
        if (tempHour != hour1) {
            if (TimeUtils.minutesAsInt == minute) {
                hour1 = tempHour
                return true
            }
        }
        return false
    }

    override fun reset() {
        hour1 = -1
    }
}