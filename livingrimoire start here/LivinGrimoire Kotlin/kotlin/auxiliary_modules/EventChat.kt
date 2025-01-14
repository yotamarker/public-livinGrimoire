package auxiliary_modules

class EventChat(ur: UniqueResponder, vararg args: String) {
    private val dic: MutableMap<String, UniqueResponder>

    // Constructor
    init {
        dic = HashMap()
        for (arg in args) {
            dic[arg] = ur
        }
    }

    // Add items
    fun addItems(ur: UniqueResponder, vararg args: String) {
        for (arg in args) {
            dic[arg] = ur
        }
    }

    // Add key-value pair
    fun addKeyValue(key: String, value: String?) {
        if (dic.containsKey(key)) {
            dic[key]!!.addResponse(value!!)
        } else {
            dic[key] = UniqueResponder(value!!)
        }
    }

    // Get response
    fun response(in1: String): String {
        return if (dic.containsKey(in1)) dic[in1]!!.getAResponse() else ""
    }
}
