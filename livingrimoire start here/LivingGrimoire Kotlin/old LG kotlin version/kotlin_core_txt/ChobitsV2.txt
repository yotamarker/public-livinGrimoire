package com.yotamarker.lgkotlinfull.LGCore

import java.util.*

class ChobitV2(personality: Personality) : thinkable {
    protected var emot:String = "" // emotion
        get() {
            // emot (emotion for display)
            var x1 = emot
            when (emot) {
                "APCuss " -> x1 = "angry"
                "APDirtyTalk" -> x1 = "grinny"
                "APMoan" -> x1 = "horny"
                "APSay" -> x1 = "speaking"
                "APSleep0" -> x1 = "dreaming"
                "APSleep" -> x1 = "asleep"
                "APSpell" -> x1 = "blank"
                else -> {
                }
            }
            emot = ""
            return x1
        }
    protected var dClassesLv1: ArrayList<AbsCmdReq>
    protected var dClassesLv2 // can engage with friends and work related
            : ArrayList<AbsCmdReq>
    protected var dClassesLv3 // can engage only by user
            : ArrayList<AbsCmdReq>
    protected var dClassesAuto = ArrayList<AbsCmdReq>() // automatically added and engaged by time

    // algorithms fusion (polymarization)
    protected var AlgDurations: Hashtable<String, Int>
    protected var fusion: Fusion

    // region essential DClasses
    protected var permission: Permission
    protected var dPermitter: DPermitter

    // endregion
    protected var noiron: Neuron

    // sleep vars :
    protected var activePerson = Person()

    // added :
    protected var kokoro // soul
            : Kokoro
    protected var master = Person()
    protected var lastOutput = ""

    // standBy phase 260320
    protected var timeGate = TimeGate()
    fun loadPersonality(personality: Personality) {
        AlgDurations = personality.algDurations
        fusion = personality.fusion
        kokoro = personality.kokoro
        permission = personality.permission!!
        dPermitter = personality.getdPermitter()
        noiron = Neuron()
        dClassesLv1 = personality.getdClassesLv1()
        dClassesLv2 = personality.getdClassesLv2()
        dClassesLv3 = personality.getdClassesLv3()
        dClassesAuto = ArrayList()
        formAutoClassesList()
    }

    protected fun formAutoClassesList() {
        // adds automatic skills so they can be engaged by time
        for (dCls in dClassesLv2) {
            if (dCls.auto()) {
                dClassesAuto.add(dCls)
            }
        }
        for (dCls in dClassesLv3) {
            if (dCls.auto()) {
                dClassesAuto.add(dCls)
            }
        }
    }

    fun doIt(ear: String, skin: String, eye: String): String {
        var ear = ear
        ear = translateIn(ear)
        for (dCls in dClassesAuto) {
            inOut(dCls, "", skin, eye)
        }
        for (dCls in dClassesLv1) {
            inOut(dCls, ear, skin, eye)
        }
        if (dPermitter.permissionLevel > 0) {
            // works with friends
            for (dCls in dClassesLv2) {
                inOut(dCls, ear, skin, eye)
            }
        }
        if (dPermitter.permissionLevel > 1) {
            // only works with owner
            for (dCls in dClassesLv3) {
                inOut(dCls, ear, skin, eye)
            }
        }
        fusion.setAlgQueue(noiron)
        return translateOut(fusion.act(ear, skin, eye))
    }

    val soulEmotion: String
        get() = kokoro.emot
    protected fun inOut(dClass: AbsCmdReq, ear: String, skin: String, eye: String) {
        dClass.input(ear!!, skin!!, eye!!) // new
        dClass.output(noiron)
    }

    protected fun translateIn(earIn: String): String {
        // makes sure the chobit doesn't feedback on her own output
        return if (earIn == lastOutput) {
            ""
        } else earIn
    }

    protected fun translateOut(outResult: String): String {
        // save last output served
        if (!outResult.isEmpty()) {
            lastOutput = outResult
            timeGate.close()
            kokoro.standBy = false
        } else {
            if (!timeGate.isClosed) {
                kokoro.standBy = true
                timeGate.close()
            } else {
                kokoro.standBy = false
            }
        }
        return outResult
    }

    override fun think(ear: String, skin: String, eye: String): String {
        return doIt(ear, skin, eye)
    }

    val standby: Boolean
        get() = kokoro.standBy

    init {
        AlgDurations = personality.algDurations
        fusion = personality.fusion
        kokoro = personality.kokoro
        permission = personality.permission!!
        dPermitter = personality.getdPermitter()
        noiron = Neuron()
        dClassesLv1 = personality.getdClassesLv1()
        dClassesLv2 = personality.getdClassesLv2()
        dClassesLv3 = personality.getdClassesLv3()
        formAutoClassesList()
    }
}