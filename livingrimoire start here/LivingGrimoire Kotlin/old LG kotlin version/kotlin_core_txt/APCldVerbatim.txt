package com.yotamarker.lgkotlinfull.LGCore

import java.util.*

class APCldVerbatim : AbsAlgPart {
    /*
     * this algorithm part says each past param verbatim
     */
    private var sentences = ArrayList<String>()
    private var at = 0
    private var cldBool: CldBool

    constructor(cldBool: CldBool, vararg sentences: String) {
        for (i in 0 until sentences.size) {
            this.sentences.add(sentences[i])
        }
        if (0 == sentences.size) {
            at = 30
        }
        this.cldBool = cldBool
        this.cldBool.modeActive = true
    }

    constructor(cldBool: CldBool, list1: ArrayList<String>?) {
        sentences = ArrayList(list1!!)
        if (0 == sentences.size) {
            at = 30
        }
        this.cldBool = cldBool
        this.cldBool.modeActive = true
    }

    override fun action(ear: String, skin: String, eye: String): String {
        // TODO Auto-generated method stub
        var axnStr = ""
        if (at < sentences.size) {
            axnStr = sentences[at]
            at++
        }
        cldBool.modeActive = at < sentences.size
        return axnStr
    }

    override fun failure(input: String): enumFail {
        // TODO Auto-generated method stub
        return enumFail.ok
    }

    override fun completed(): Boolean {
        return at >= sentences.size
    }

    override fun clone(): AbsAlgPart {
        // TODO Auto-generated method stub
        return APCldVerbatim(cldBool, sentences)
    }

    override fun itemize(): Boolean {
        // TODO add logic
        // at home
        return true
    }
}