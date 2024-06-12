package auxiliary_modules

import java.util.*

class RailChatBot {
    private val dic = Hashtable<String, RefreshQ>()
    private var context = "default"

    init {
        dic[context] = RefreshQ()
    }

    fun setContext(context: String) {
        if (context.isEmpty()) {
            return
        }
        this.context = context
    }

    fun respondMonolog(ear: String): String {
        // monolog mode
        // recommended use of filter for the output results
        if (ear.isEmpty()) {
            return ""
        }
        if (!dic.containsKey(ear)) {
            dic[ear] = RefreshQ()
        }
        val temp: String = dic[ear]!!.rNDElement
        if (!temp.isEmpty()) {
            context = temp
        }
        return temp
    }

    fun learn(ear: String) {
        // use per each think cycle
        if (ear.isEmpty()) {
            return
        }
        if (!dic.containsKey(ear)) {
            dic[ear] = RefreshQ()
            dic[context]!!.add(ear)
            context = ear
            return
        }
        dic[context]!!.add(ear)
        context = ear
    }

    fun learnKeyValue(context: String, reply: String) {
        // learn questions and answers/ key values
        if (!dic.containsKey(context)) {
            dic[context] = RefreshQ()
        }
        if (!dic.containsKey(reply)) {
            dic[reply] = RefreshQ()
        }
        dic[context]!!.add(reply)
    }

    fun feedKeyValuePairs(kvList: ArrayList<AXKeyValuePair>) {
        if (kvList.isEmpty()) {
            return
        }
        for (kv in kvList) {
            learnKeyValue(kv.key, kv.value)
        }
    }

    fun monolog(): String {
        // succession of outputs without input involved
        return respondMonolog(context)
    }

    fun respondDialog(ear: String): String {
        // dialog mode
        // recommended use of filter for the output results
        if (ear.isEmpty()) {
            return ""
        }
        if (!dic.containsKey(ear)) {
            dic[ear] = RefreshQ()
        }
        //        if (!temp.isEmpty()){context = temp;}
        return dic[ear]!!.rNDElement
    }

    fun learnV2(ear: String, elizaDeducer: ElizaDeducer) {
        feedKeyValuePairs(elizaDeducer.respond(ear))
        learn(ear)
    }
}