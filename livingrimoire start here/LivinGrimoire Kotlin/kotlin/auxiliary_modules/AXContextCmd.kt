package auxiliary_modules

class AXContextCmd {
    // engage on commands
    // when commands are engaged, context commans can also engage
    var commands = UniqueItemSizeLimitedPriorityQueue()
    var contextCommands = UniqueItemSizeLimitedPriorityQueue()
    var trgTolerance = false
    fun engageCommand(s1: String): Boolean {
        if (s1.isEmpty()) {
            return false
        }
        if (contextCommands.contains(s1)) {
            trgTolerance = true
            return true
        }
        if (trgTolerance && !commands.contains(s1)) {
            trgTolerance = false
            return false
        }
        return trgTolerance
    }

    fun disable() {
        // context commands are disabled till next engagement with a command
        trgTolerance = false
    }
}