package auxiliary_modules

import java.util.*
import java.util.regex.Pattern
import kotlin.collections.ArrayList

open class ElizaDeducer(lim: Int) {
    /*
    * this class populates a special chat dictionary
    * based on the matches added via its addPhraseMatcher function
    * see subclass ElizaDeducerInitializer for example:
    * ElizaDeducer ed = new ElizaDeducerInitializer(2); // 2 = limit of replies per input
    * */
    var babble2: MutableList<PhraseMatcher>
    private val patternIndex: MutableMap<String, MutableList<PhraseMatcher>>
    private val responseCache: MutableMap<String, List<AXKeyValuePair>>
    val ec2 // chat dictionary, use getter for access. hardcoded replies can also be added
            : EventChatV2

    init {
        babble2 = ArrayList()
        patternIndex = HashMap()
        responseCache = HashMap()
        ec2 = EventChatV2(lim)
    }

    fun learn(msg: String) {
        // populate EventChat dictionary
        // Check cache first
        if (responseCache.containsKey(msg)) {
            ec2.addKeyValues(ArrayList(responseCache[msg]!!))
        }

        // Search for matching patterns
        val potentialMatchers = getPotentialMatchers(msg)
        for (pm in potentialMatchers) {
            if (pm.matches(msg)) {
                val response = pm.respond(msg)
                responseCache[msg] = response
                ec2.addKeyValues(response)
            }
        }
    }

    fun learnedBool(msg: String): Boolean {
        // same as learn method but returns true if it learned new replies
        var learned = false
        // populate EventChat dictionary
        // Check cache first
        if (responseCache.containsKey(msg)) {
            ec2.addKeyValues(ArrayList(responseCache[msg]!!))
            learned = true
        }

        // Search for matching patterns
        val potentialMatchers = getPotentialMatchers(msg)
        for (pm in potentialMatchers) {
            if (pm.matches(msg)) {
                val response = pm.respond(msg)
                responseCache[msg] = response
                ec2.addKeyValues(response)
                learned = true
            }
        }
        return learned
    }

    fun respond(str1: String?): String {
        return ec2.response(str1!!)
    }

    fun respondLatest(str1: String?): String {
        // get most recent reply/data
        return ec2.responseLatest(str1!!)
    }

    private fun getPotentialMatchers(msg: String): List<PhraseMatcher> {
        val potentialMatchers: MutableList<PhraseMatcher> = ArrayList()
        for (key in patternIndex.keys) {
            if (msg.contains(key)) {
                potentialMatchers.addAll(patternIndex[key]!!)
            }
        }
        return potentialMatchers
    }

    fun addPhraseMatcher(pattern: String, vararg kvPairs: String?) {
        val kvs = ArrayList<AXKeyValuePair>()
        var i = 0
        while (i < kvPairs.size) {
            kvs.add(AXKeyValuePair(kvPairs[i]!!, kvPairs[i + 1]!!))
            i += 2
        }
        val matcher = PhraseMatcher(pattern, kvs)
        babble2.add(matcher)
        indexPattern(pattern, matcher)
    }

    private fun indexPattern(pattern: String, matcher: PhraseMatcher) {
        for (word in pattern.split("\\s+".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()) {
            patternIndex.computeIfAbsent(
                word
            ) { k: String? -> ArrayList() }.add(matcher)
        }
    }

    class PhraseMatcher(matcher: String, val responses: List<AXKeyValuePair>) {
        val matcher: Pattern

        init {
            this.matcher = Pattern.compile(matcher)
        }

        fun matches(str: String?): Boolean {
            val m = matcher.matcher(str)
            return m.matches()
        }

        fun respond(str: String): ArrayList<AXKeyValuePair> {
            val m = matcher.matcher(str)
            val result = ArrayList<AXKeyValuePair>()
            if (m.find()) {
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
            }
            return result
        }
    }
}