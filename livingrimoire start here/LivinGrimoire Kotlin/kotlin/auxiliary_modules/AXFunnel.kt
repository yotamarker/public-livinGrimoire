package auxiliary_modules

import java.util.HashMap

class AXFunnel {
    // funnel many inputs to fewer or one input
    // allows using command variations in skills
    private val dic: MutableMap<String, String>
    private var defaultStr: String

    init {
        dic = HashMap()
        defaultStr = "default"
    }

    fun setDefault(defaultStr: String) {
        this.defaultStr = defaultStr
    }

    fun addKV(key: String, value: String): AXFunnel {
        // add key-value pair
        dic[key] = value
        return this
    }

    fun addK(key: String): AXFunnel {
        // add key with default value
        dic[key] = defaultStr
        return this
    }

    fun funnel(key: String): String {
        // get value from dictionary or return the key itself as default
        return dic.getOrDefault(key, key)
    }
    fun funnelOrEmpty(key: String): String {
        // get value from dictionary or return ""
        return dic.getOrDefault(key, "")
    }
}