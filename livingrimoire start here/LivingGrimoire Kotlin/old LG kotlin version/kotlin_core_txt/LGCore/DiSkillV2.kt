package com.yotamarker.lgkotlinfull.LGCore

class DiSkillV2(  // consciousness, shallow ref class to enable interskill communications
        protected var kokoro: Kokoro) : AbsCmdReq() {
    protected var diSkillUtils = DISkillUtils()
    protected var outAlg: Algorithm? = null // skills output
    override fun input(ear: String, skin: String, eye: String) {}
    override fun output(noiron: Neuron) {
        if (outAlg != null) {
            noiron.algParts.add(outAlg!!)
            outAlg = null
        }
    }
}