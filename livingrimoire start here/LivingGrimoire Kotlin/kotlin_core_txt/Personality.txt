package com.yotamarker.lgkotlinfull.LGCore

import java.util.*

open class Personality {
    /*this class is used in the ChobitV2 c'tor.
it enables loading a complete skill set (a sub class of the personality class)
using 1 line of code. of course you can also select specific skills to add from
the subclasses c'tor. see also Personality1 for example.*/
    var kokoro // soul
            : Kokoro
        protected set
    protected var dClassesLv1 = ArrayList<AbsCmdReq>() // can engage with anyone
    protected var dClassesLv2 = ArrayList<AbsCmdReq>() // can engage with friends and work related
    protected var dClassesLv3 = ArrayList<AbsCmdReq>() // can engage only by user
    var permission = Permission.newInstance("xxx", "sweetie", "honey")
        protected set
    protected var dPermitter = DPermitter(permission!!) //TODO
    var algDurations = Hashtable<String, Int>()
        protected set
    var fusion = Fusion(algDurations)
        protected set

    //fusion.getReqOverload() // an overload of requests on the brain
    //fusion.getRepReq() // someone is negging and asking the same thing over and over again
    /*
    flight or fight skills may need access to the above fusion class booleans
    on the output methode of a skill this skills will load algorithms to the highest priority of the noiron
    which carries algorithms :
    noiron.negativeAlgParts.add(Algorithm)
    * */
    constructor(absDictionaryDB: AbsDictionaryDB?) {
        kokoro = Kokoro(absDictionaryDB!!)
    }

    constructor() {
        kokoro = Kokoro(AbsDictionaryDBShadow())
    }

    fun getdClassesLv1(): ArrayList<AbsCmdReq> {
        return dClassesLv1
    }

    fun getdClassesLv2(): ArrayList<AbsCmdReq> {
        return dClassesLv2
    }

    fun getdClassesLv3(): ArrayList<AbsCmdReq> {
        return dClassesLv3
    }

    fun getdPermitter(): DPermitter {
        return dPermitter
    }
}