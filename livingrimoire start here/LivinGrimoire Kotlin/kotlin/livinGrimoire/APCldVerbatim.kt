package livinGrimoire

class APCldVerbatim : Mutatable {
    /*
     * this algorithm part says each past param verbatim
     */
    private var sentences = ArrayList<String>()
    private var at = 0
    private var cldBool // access via shallow reference
            : CldBool

    constructor(cldBool: CldBool, vararg sentences: String) {
        for (i in sentences.indices) {
            this.sentences.add(sentences[i])
        }
        if (0 == sentences.size) {
            at = 30
        }
        this.cldBool = cldBool
        this.cldBool.modeActive = true
    }

    constructor(cldBool: CldBool, list1: ArrayList<String>) {
        sentences = ArrayList(list1)
        if (0 == sentences.size) {
            at = 30
        }
        this.cldBool = cldBool
        this.cldBool.modeActive = true
    }

    override fun action(ear: String, skin: String, eye: String): String {
        var axnStr = ""
        if (at < sentences.size) {
            axnStr = sentences[at]
            at++
        }
        cldBool.modeActive = at < sentences.size
        return axnStr
    }

    override fun completed(): Boolean {
        return at >= sentences.size
    }
}