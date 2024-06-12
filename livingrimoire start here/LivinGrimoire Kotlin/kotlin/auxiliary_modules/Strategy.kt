package auxiliary_modules

class Strategy( // bank of all strategies. out of this pool active strategies are pulled
    var allStrategies: DrawRnd
) {
    private val activeStrategy // active strategic options
            : UniqueItemSizeLimitedPriorityQueue = UniqueItemSizeLimitedPriorityQueue()

    init {
        // create the strategy Object with a bank of options
    }

    fun evolveStrategies(strategiesLimit: Int) {
        // add N strategic options to the active strategies bank, from the total strategy bank
        activeStrategy.limit = strategiesLimit
        var temp = allStrategies.draw()
        for (i in 0 until strategiesLimit) {
            if (temp.isEmpty()) {
                break
            }
            activeStrategy.add(temp)
            temp = allStrategies.draw()
        }
        allStrategies.reset()
    }

    val strategy: String
        get() = activeStrategy.rNDElement
}