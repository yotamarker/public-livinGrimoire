package lgkt


class APVerbatim : Mutatable {
    /*
     * this algorithm part says each past param verbatim
     */
    private var sentences = ArrayList<String>()
    private var at = 0

    constructor(vararg sentences: String) {
        for (i in sentences.indices) {
            this.sentences.add(sentences[i])
        }
        if (0 == sentences.size) {
            at = 30
        }
    }

    constructor(list1: ArrayList<String>) {
        sentences = ArrayList(list1)
        if (0 == sentences.size) {
            at = 30
        }
    }

    override fun action(ear: String, skin: String, eye: String): String {
        var axnStr = ""
        if (at < sentences.size) {
            axnStr = sentences[at]
            at++
        }
        return axnStr
    }

    override fun failure(input: String): enumFail {
        return enumFail.ok
    }

    override fun completed(): Boolean {
        return at >= sentences.size
    }

    override fun clone(): Mutatable {
        return APVerbatim(sentences)
    }
}