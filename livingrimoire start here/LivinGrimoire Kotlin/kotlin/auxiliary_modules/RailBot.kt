package auxiliary_modules

import livinGrimoire.Kokoro

class RailBot @JvmOverloads constructor(limit: Int = 5) {
    private val ec: EventChatV2
    private var context: String = "stand by"
    private var elizaWrapper: ElizaDBWrapper? = null // Starts null (no DB)

    init {
        ec = EventChatV2(limit)
    }

    /**
     * Enables database features. Must be called before any save/load operations.
     * If never called, RailBot works in memory-only mode.
     */
    fun enableDBWrapper() {
        if (elizaWrapper == null) {
            elizaWrapper = ElizaDBWrapper()
        }
    }

    fun disableDBWrapper() {
        elizaWrapper = null
    }

    fun setContext(context: String) {
        if (context.isEmpty()) {
            return
        }
        this.context = context
    }

    private fun respondMonolog(ear: String): String {
        if (ear.isEmpty()) {
            return ""
        }
        val temp = ec.response(ear)
        if (!temp.isEmpty()) {
            context = temp
        }
        return temp
    }

    fun learn(ear: String) {
        if (ear.isEmpty() || ear == context) {
            return
        }
        ec.addKeyValue(context, ear)
        context = ear
    }

    fun monolog(): String {
        return respondMonolog(context)
    }

    fun respondDialog(ear: String): String {
        return ec.response(ear)
    }

    fun respondLatest(ear: String): String {
        return ec.responseLatest(ear)
    }

    fun learnKeyValue(context: String, reply: String) {
        ec.addKeyValue(context, reply)
    }

    fun feedKeyValuePairs(kvList: List<AXKeyValuePair>) {
        if (kvList.isEmpty()) {
            return
        }
        for (kv in kvList) {
            learnKeyValue(kv.key, kv.value)
        }
    }

    // save/load functionalities
    fun saveLearnedData(kokoro: Kokoro) {
        if (elizaWrapper == null) {
            return
        }
        elizaWrapper!!.sleepNSave(ec, kokoro)
    }

    private fun loadableMonologMechanics(ear: String?, kokoro: Kokoro): String {
        // loads data if available
        if (ear!!.isEmpty()) {
            return ""
        }
        val temp = elizaWrapper!!.respond(ear, ec, kokoro)
        if (!temp.isEmpty()) {
            context = temp
        }
        return temp
    }

    fun loadableMonolog(kokoro: Kokoro): String {
        return if (elizaWrapper == null) {
            monolog()
        } else loadableMonologMechanics(context, kokoro)
    }

    fun loadableDialog(ear: String, kokoro: Kokoro): String {
        return if (elizaWrapper == null) {
            respondDialog(ear)
        } else elizaWrapper!!.respond(ear, ec, kokoro)
    }
}