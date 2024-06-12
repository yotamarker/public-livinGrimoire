package auxiliary_modules

class TrgTolerance( //2 recomended
    private var maxrepeats: Int
) : TrGEV3() {
    // this boolean gate will return true till depletion or reset()
    private var repeats = 0

    fun setMaxrepeats(maxrepeats: Int) {
        this.maxrepeats = maxrepeats
        reset()
    }

    override fun reset() {
        // refill trigger
        repeats = maxrepeats
    }

    override fun trigger(): Boolean {
        // will return true till depletion or reset()
        repeats--
        return repeats > 0
    }

    fun disable() {
        repeats = 0
    }
}