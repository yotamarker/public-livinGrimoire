package com.yotamarker.lgkotlinfull.LGCore

class DISkill// this.ofSkill = ofSkill;
// in sub cls : person ?
(  // String ofSkill;
        protected var kokoro // accessed by sub cls
        : Kokoro) : AbsCmdReq() {
    protected var sentAlg = false // accessed by sub cls
        get() {val result = sentAlg
            sentAlg = false
            return result}                    // getter
        set(value) { field = value }      // setter
    var output = false // accessed by the DISkill container a Bijuu cls
        get() {
            val result = field
            field = false
            return result
        }
    override fun output(noiron: Neuron) {
        // set sentAlg = true if an alg is to be sent
    }

    override fun input(ear: String, skin: String, eye: String) {
        // TODO Auto-generated method stub
    }
}