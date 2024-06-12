package auxiliary_modules

class TimeAccumulator(tick: Int) {
    // accumulator ++ each tick minutes interval
    private var timeGate = TimeGate(5)
    var accumulator = 0
        private set

    fun setTick(tick: Int) {
        timeGate.setPause(tick)
    }

    init {
        // accumulation ticker
        timeGate = TimeGate(tick)
        timeGate.openGate()
    }

    fun tick() {
        if (timeGate.isClosed) {
            timeGate.openGate()
            accumulator++
        }
    }

    fun reset() {
        accumulator = 0
    }

    fun decAccumulator() {
        if (accumulator > 0) {
            accumulator--
        }
    }
}