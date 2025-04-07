package auxiliary_modules

class LimUniqueResponder(private val lim: Int) {
    private var responses: MutableList<String> = ArrayList()
    private var urg = UniqueRandomGenerator(0)

    val aResponse: String
        // Method to get a response
        get() = if (responses.isEmpty()) {
            ""
        } else responses[urg.getUniqueRandom()]

    // Method to check if responses contain a string
    fun responsesContainsStr(item: String): Boolean {
        return responses.contains(item)
    }

    // Method to check if a string contains any response
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

    // Method to add a response
    fun addResponse(s1: String) {
        if (responses.contains(s1)) {
            responses.remove(s1)
            responses.add(s1)
            return
        }
        if (responses.size > lim - 1) {
            responses.removeAt(0)
        } else {
            urg = UniqueRandomGenerator(responses.size + 1)
        }
        responses.add(s1)
    }

    fun addResponses(vararg replies: String) {
        for (value in replies) {
            addResponse(value)
        }
    }

    fun clone(): LimUniqueResponder {
        val clonedResponder = LimUniqueResponder(lim)
        clonedResponder.responses = java.util.ArrayList(responses)
        clonedResponder.urg = UniqueRandomGenerator(clonedResponder.responses.size)
        return clonedResponder
    }

    val savableStr: String
        get() = java.lang.String.join("_", responses)
    val lastItem: String
        get() {
            return if (responses.isEmpty()) {
                ""
            } else responses[responses.size - 1]
        }
}