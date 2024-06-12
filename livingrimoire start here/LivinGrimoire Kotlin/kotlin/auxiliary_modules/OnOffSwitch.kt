package auxiliary_modules

class OnOffSwitch {
    private var mode = false
    private val timeGate: TimeGate = TimeGate(5)
    private var on = Responder("on", "talk to me")
    private var off = Responder("off", "stop", "shut up", "shut it", "whatever", "whateva")
    fun setPause(minutes: Int) {
        timeGate.setPause(minutes)
    }

    fun setOn(on: Responder) {
        this.on = on
    }

    fun setOff(off: Responder) {
        this.off = off
    }

    fun getMode(ear: String?): Boolean {
        if (on.responsesContainsStr(ear!!)) {
            timeGate.openGate()
            mode = true
            return true
        } else if (off.responsesContainsStr(ear)) {
            timeGate.close()
            mode = false
        }
        if (timeGate.isClosed) {
            mode = false
        }
        return mode
    }
}