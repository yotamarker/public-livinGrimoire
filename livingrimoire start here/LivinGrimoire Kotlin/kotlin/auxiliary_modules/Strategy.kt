package auxiliary_modules

class Strategy(private val allStrategies: UniqueResponder, private val strategiesLim: Int) {
    private val activeStrategy: UniqueItemSizeLimitedPriorityQueue

    // Constructor
    init {
        activeStrategy = UniqueItemSizeLimitedPriorityQueue()
        activeStrategy.limit = strategiesLim
        for (i in 0 until strategiesLim) {
            activeStrategy.add(allStrategies.getAResponse())
        }
    }

    // Evolve strategies
    fun evolveStrategies() {
        for (i in 0 until strategiesLim) {
            activeStrategy.add(allStrategies.getAResponse())
        }
    }

    val strategy: String
        // Get strategy
        get() = activeStrategy.rNDElement
}