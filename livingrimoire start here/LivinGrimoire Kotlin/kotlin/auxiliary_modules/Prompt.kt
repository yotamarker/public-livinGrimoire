package auxiliary_modules

class Prompt {
    var regexUtil = RegexUtil()
    var kv = AXKeyValuePair()
    var prompt = ""
    private var regex = ""

    init {
        kv.key = "default"
    }

    fun process(in1: String): Boolean {
        kv.value = regexUtil.extractRegex(regex, in1)
        return kv.value.isEmpty() // is prompt still active?
    }

    fun setRegex(regex: String) {
        this.regex = regex
    }
}