package auxiliary_modules

class Cycler(var limit: Int) {
    // cycles through numbers limit to 0 non-stop
    var mode = 0
        private set

    init {
        mode = limit
    }

    fun cycleCount(): Int {
        mode--
        if (mode < 0) {
            mode = limit
        }
        return mode
    }

    fun reset() {
        mode = limit
    }

    fun setToZero() {
        mode = 0
    }

    fun sync(n: Int) {
        if (n < -1 || n > limit) {
            return
        }
        mode = n
    }
}