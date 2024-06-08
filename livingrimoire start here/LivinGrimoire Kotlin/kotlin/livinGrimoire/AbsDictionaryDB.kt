package livinGrimoire

class AbsDictionaryDB {
    fun save(key: String?, value: String?) {
        // save to DB (override me)
    }

    fun load(key: String?): String {
        // override me
        return "null"
    }
}