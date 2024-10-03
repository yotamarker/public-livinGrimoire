package auxiliary_modules

class TrgTime {
    var t = "null"
    var pl: TimeUtils = TimeUtils()
    private var alarm = true
    fun setTime(v1: String) {
        t = RegexUtil.extractRegex(enumRegexGrimoire.SimpleTimeStamp, v1)
    }

    fun alarm(): Boolean {
        val now: String = pl.currentTimeStamp
        if (alarm) {
            if (now == t) {
                alarm = false
                return true
            }
        }
        if (now != t) {
            alarm = true
        }
        return false
    }
}