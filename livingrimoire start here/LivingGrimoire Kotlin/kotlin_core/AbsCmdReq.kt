package com.yotamarker.lgkotlinfull.LGCore

abstract class AbsCmdReq {
    // handle the input per Dclass (a class whose name starts with D)
    abstract fun input(ear: String, skin: String, eye: String)
    fun auto()=false
    // does this skill also engage by time triggers ? is it also a level > 1 type of
    // skill ? if yes
    // override me and return true;
    abstract fun output(noiron: Neuron)
}