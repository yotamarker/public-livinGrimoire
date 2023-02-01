package com.yotamarker.lgkotlinfull.LGCore

import java.util.*

class APVerbatim : AbsAlgPart {
    /*
	 * this algorithm part says each past param verbatim
	 */
    private var sentences = ArrayList<String>()
    private var at = 0

    constructor(vararg sentences: String) {
        for (i in 0 until sentences.size) {
            this.sentences.add(sentences[i])
        }
        if (0 == sentences.size) {
            at = 30
        }
    }

    constructor(list1: ArrayList<String>) {
        sentences = ArrayList(list1!!)
        if (0 == sentences.size) {
            at = 30
        }
    }

    override fun action(ear: String, skin: String, eye: String): String {
        // TODO Auto-generated method stub
        var axnStr = ""
        if (at < sentences.size) {
            axnStr = sentences[at]
            at++
        }
        return axnStr
    }

    override fun failure(input: String): enumFail {
        // TODO Auto-generated method stub
        return enumFail.ok
    }

    override fun completed(): Boolean {
        // TODO Auto-generated method stub
        return at >= sentences.size
    }

    override fun clone(): AbsAlgPart {
        // TODO Auto-generated method stub
        return APVerbatim(sentences)
    }

    override fun itemize(): Boolean {
        // TODO add logic
        // at home
        return true
    }
}