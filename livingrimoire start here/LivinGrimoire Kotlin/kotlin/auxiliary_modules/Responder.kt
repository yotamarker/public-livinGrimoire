package auxiliary_modules

import java.util.*

class Responder(vararg replies: String) {
    // simple random response dispenser
    private val responses = ArrayList<String>()
    private val rand = Random()

    init {
        Collections.addAll(responses, *replies)
    }

    val aResponse: String
        get() = if (responses.isEmpty()) {
            ""
        } else responses[rand.nextInt(responses.size)]

    fun responsesContainsStr(str: String): Boolean {
        return responses.contains(str)
    }

    fun strContainsResponse(str: String): Boolean {
        var result = false
        for (tempStr in responses) {
            if (str.contains(tempStr)) {
                result = true
                break
            }
        }
        return result
    }

    fun addResponse(s1: String) {
        responses.add(s1)
    }
}