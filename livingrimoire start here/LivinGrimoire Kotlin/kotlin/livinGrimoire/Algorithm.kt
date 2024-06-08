package livinGrimoire

// a step-by-step plan to achieve a goal
class Algorithm(algParts: ArrayList<Mutatable>) {
    var algParts = ArrayList<Mutatable>()

    init {
        this.algParts = algParts
    }

    val size: Int
        get() = algParts.size

    fun clone(): Algorithm {
        // returns a deep copy algorithm
        val parts = ArrayList<Mutatable>()
        for (absAlgPart in algParts) {
            parts.add(absAlgPart.clone())
        }
        return Algorithm(parts)
    }
}