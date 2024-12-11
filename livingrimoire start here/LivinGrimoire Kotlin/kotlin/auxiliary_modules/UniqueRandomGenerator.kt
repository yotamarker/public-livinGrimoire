package auxiliary_modules

class UniqueRandomGenerator(n1: Int) {
    private val numbers: List<Int> = List(n1) { it }
    private var remainingNumbers: MutableList<Int> = mutableListOf()

    init {
        reset()
    }

    fun reset() {
        remainingNumbers = numbers.toMutableList()
        remainingNumbers.shuffle()
    }

    fun getUniqueRandom(): Int {
        if (remainingNumbers.isEmpty()) {
            reset()
        }
        return remainingNumbers.removeAt(remainingNumbers.size - 1)
    }
}
