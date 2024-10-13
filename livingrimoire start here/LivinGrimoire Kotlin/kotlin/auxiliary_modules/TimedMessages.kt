package auxiliary_modules

import java.util.*

class TimedMessages {
    var messages: MutableMap<String, String> = HashMap()
    private var lastMSG: String = "nothing"
    var msg = false
        private set

    fun addMSG(ear: String) {
        val tempMSG = RegexUtil.extractRegex("(?<=remind me to).*?(?=at)", ear)
        if (tempMSG.isEmpty()) {
            return
        }
        val timeStamp: String = RegexUtil.extractRegex(enumRegexGrimoire.SimpleTimeStamp, ear)
        if (timeStamp.isEmpty()) {
            return
        }
        messages[timeStamp] = tempMSG
    }

    fun addMSGV2(timeStamp: String, msg: String) {
        messages[timeStamp] = msg
    }

    fun sprinkleMSG(msg: String, amount: Int) {
        for (i in 0 until amount) {
            messages[generateRandomTimestamp()] = msg
        }
    }

    fun clear() {
        messages = HashMap()
    }

    fun tick() {
        val now: String = TimeUtils.currentTimeStamp
        if (messages.containsKey(now)) {
            if (lastMSG != messages[now]) {
                lastMSG = messages[now]!!
                msg = true
            }
        }
    }

    fun getLastMSG(): String {
        msg = false
        return lastMSG
    }

    companion object {
        fun generateRandomTimestamp(): String {
            // Generate a random number of minutes (0 to 59)
            val random = Random()
            val minutes = random.nextInt(60)
            var m = ""
            m = if (minutes > 9) {
                String.format("%d", minutes)
            } else {
                String.format("0%d", minutes)
            }
            val hours = random.nextInt(12)
            return if (hours > 9) {
                String.format("%d:%s", hours, minutes)
            } else String.format("0%d:%s", hours, minutes)
        }
    }
}