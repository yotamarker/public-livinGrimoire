package livinGrimoire

class GrimoireMemento(private val absDictionaryDB: AbsDictionaryDB) {
    fun simpleLoad(key: String): String {
        return absDictionaryDB.load(key)
    }

    fun simpleSave(key: String, value: String) {
        if (key.isEmpty() || value.isEmpty()) {
            return
        }
        absDictionaryDB.save(key, value)
    }
}