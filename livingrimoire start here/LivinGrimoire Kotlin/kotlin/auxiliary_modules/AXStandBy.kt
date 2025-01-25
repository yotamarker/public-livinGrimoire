package auxiliary_modules

class AXStandBy(pause: Int) {
    private val tg: TimeGate = TimeGate(pause)

    init {
        tg.openGate()
    }

    fun standBy(ear: String): Boolean {
        // only returns true after pause minutes of no input
        if (ear.isNotEmpty()) {
            // restart count
            tg.openGate()
            return false
        }
        if (tg.isClosed) {
            // time out without input, stand by is true
            tg.openGate()
            return true
        }
        return false
    }
}
