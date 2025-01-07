package auxiliary_modules

class EventChat(ur: UniqueResponder, vararg args: String) {
    private val dic: MutableMap<String, UniqueResponder> = mutableMapOf()

    init {
        for (arg in args) {
            dic[arg] = ur
        }
    }

    fun response(in1: String): String {
        return dic[in1]?.getAResponse() ?: ""
    }
}
