package com.yotamarker.lgkotlinfull.LGCore

/* it speaks something x times
 * a most basic skill.
 * also fun to make the chobit say what you want
 * */
class APSay(at: Int, param: String) : AbsAlgPart() {
    protected var param: String
    private var at: Int
    override fun action(ear: String, skin: String, eye: String): String {
        var axnStr = ""
        if (at > 0) {
            if (!ear.equals(param, ignoreCase = true)) {
                axnStr = param
                at--
            }
        }
        return axnStr
    }

    override fun failure(input: String): enumFail {
        return enumFail.ok
    }

    override fun completed(): Boolean {
        return at < 1
    }

    override fun clone(): AbsAlgPart {
        return APSay(at, param)
    }

    override fun itemize(): Boolean {
        // at home
        return true
    }

    init {
        var at = at
        if (at > 10) {
            at = 10
        }
        this.at = at
        this.param = param
    }
}