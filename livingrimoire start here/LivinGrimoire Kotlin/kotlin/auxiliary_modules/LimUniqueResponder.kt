package auxiliary_modules

class LimUniqueResponder(private val lim: Int) {
    private val responses: MutableList<String>
    private var urg = UniqueRandomGenerator(0)

    // Constructor
    init {
        responses = ArrayList()
    }

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
        if (responses.size > lim - 1) {
            responses.removeAt(0)
        }
        if (!responses.contains(s1)) {
            responses.add(s1)
            urg = UniqueRandomGenerator(responses.size)
        }
    }

    fun addResponses(vararg replies: String) {
        for (value in replies) {
            addResponse(value)
        }
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