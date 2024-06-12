package auxiliary_modules

class AXGamification {
    // this auxiliary module can add fun to tasks, skills, and abilities simply by
    // tracking their usage, and maximum use count.
    var counter = 0
        private set
    var max = 0
        private set

    fun resetCount() {
        counter = 0
    }

    fun resetAll() {
        max = 0
        counter = 0
    }

    fun increment() {
        counter++
        if (counter > max) {
            max = counter
        }
    }

    fun incrementBy(n: Int) {
        counter += n
        if (counter > max) {
            max = counter
        }
    }

    fun reward(cost: Int): Boolean {
        // game grind points used for rewards
        // consumables, items or upgrades this makes games fun
        if (cost > counter) {
            return false
        }
        counter -= cost
        return true
    }

    fun surplus(cost: Int): Boolean {
        // has surplus for reward?
        return cost <= counter
    }
}