package auxiliary_modules

import java.util.*
import java.util.regex.Pattern
import kotlin.collections.ArrayList

open class ElizaDeducer {
    lateinit var babble2: List<PhraseMatcher>
    fun respond(msg: String): ArrayList<AXKeyValuePair> {
        for (pm in babble2) {
            if (pm.matches(msg)) {
                return pm.respond(msg)
            }
        }
        return ArrayList()
    }

    inner class PhraseMatcher(matcher: String, val responses: List<AXKeyValuePair>) {
        val matcher: Pattern

        init {
            this.matcher = Pattern.compile(matcher)
        }

        fun matches(str: String): Boolean {
            val m = matcher.matcher(str)
            return m.matches()
        }

        fun respond(str: String): ArrayList<AXKeyValuePair> {
            val m = matcher.matcher(str)
            if (m.find()) {
            }
            val result = ArrayList<AXKeyValuePair>()
            val tmp = m.groupCount()
            for (kv in responses) {
                val tempKV = AXKeyValuePair(kv.key, kv.value)
                for (i in 0 until tmp) {
                    val s = m.group(i + 1)
                    tempKV.key = tempKV.key.replace("{$i}", s).lowercase(Locale.getDefault())
                    tempKV.value = tempKV.value.replace("{$i}", s).lowercase(Locale.getDefault())
                }
                result.add(tempKV)
            }
            return result
        }
    }
}