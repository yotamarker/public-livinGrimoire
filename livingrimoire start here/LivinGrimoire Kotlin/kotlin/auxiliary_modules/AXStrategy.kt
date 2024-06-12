package auxiliary_modules

import java.util.*

class AXStrategy    // limit of active strategies (pulled from all available strategies)
    ( /* this auxiliary module is used to output strategies based on context
        can be used for battles, and games
        upon pain/lose use the evolve methode to update to different new active strategies
        check for battle state end externaly (opponent state/hits on rival counter)
    a dictionary of strategies*/
      private val lim: Int
) {
    private val strategies = Hashtable<String, Strategy>()
    fun addStrategy(context: String, techniques: DrawRnd?) {
        // add strategies per context
        val temp = Strategy(techniques!!)
        temp.evolveStrategies(lim)
        strategies[context] = temp
    }

    fun evolve() {
        // replace active strategies
        val e = strategies.keys()
        var key: String
        while (e.hasMoreElements()) {
            key = e.nextElement()
            strategies[key]!!.evolveStrategies(lim)
        }
    }

    fun process(context: String): String {
        // process input, return action based on game context now
        return if (strategies.containsKey(context)) {
            strategies[context]!!.strategy
        } else ""
    }
}