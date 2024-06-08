package livinGrimoire

import java.util.*

/* this class enables:
communication between skills
utilization of a database for skills
in skill monitoring of which Mutatable was last run by the AI (consciousness)
this class is a built-in attribute in skill objects.
* */
class Kokoro(absDictionaryDB: AbsDictionaryDB?) {
    var emot = ""
    var grimoireMemento: GrimoireMemento
    var toHeart = Hashtable<String, String>()

    init {
        grimoireMemento = GrimoireMemento(absDictionaryDB!!)
    }
}