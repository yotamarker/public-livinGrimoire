package auxiliary_modules

class UniqueResponder(vararg replies: String) {
    private val responses: MutableList<String> = mutableListOf()
    private var urg: UniqueRandomGenerator = UniqueRandomGenerator(replies.size)

    init {
        for (response in replies) {
            responses.add(response)
        }
    }

    fun getAResponse(): String {
        if (responses.isEmpty()) {
            return ""
        }
        return responses[urg.getUniqueRandom()]
    }

    fun responsesContainsStr(item: String): Boolean {
        return responses.contains(item)
    }

    fun strContainsResponse(item: String): Boolean {
        for (response in responses) {
            if (response.isEmpty()) {
                continue
            }
            if (item.contains(response)) {
                return true
            }
        }
        return false
    }

    fun addResponse(s1: String) {
        if (!responses.contains(s1)) {
            responses.add(s1)
            urg = UniqueRandomGenerator(responses.size)
        }
    }
}
