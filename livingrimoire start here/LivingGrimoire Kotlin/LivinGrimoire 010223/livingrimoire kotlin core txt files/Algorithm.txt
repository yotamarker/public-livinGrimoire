package lgkt


// a step-by-step plan to achieve a goal
class Algorithm(goal: String, representation: String, algParts: ArrayList<Mutatable>) {
    // *constract with string and goal
    val goal: String
    val representation: String
    var algParts = ArrayList<Mutatable>()

    init {
        this.goal = if (goal.isEmpty()) "unknown" else goal
        this.representation = if (representation.isEmpty()) "unknown" else representation
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
        return Algorithm(goal, representation, parts)
    }
}