package com.yotamarker.lgkotlinfull.LGCore

import java.util.*


class ChobitsLight(personality: PersonalityLight) : thinkable {
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

    // algorithms fusion (polymarization)
    protected var AlgDurations: Hashtable<String, Int>
    protected var fusion: Fusion

    // region essential DClasses
    protected var permission: Permission? = null
    protected var dPermitter: DPermitter? = null

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
    protected var timeGate = ZeroTimeGate()
    fun loadPersonality(personality: PersonalityLight) {
        AlgDurations = personality.algDurations
        fusion = personality.fusion
        kokoro = personality.kokoro
        noiron = Neuron()
        dClassesLv1 = personality.getdClassesLv1()
    }

    fun doIt(ear: String, skin: String?, eye: String?): String {
        var ear = ear
        ear = translateIn(ear)
        for (dCls in dClassesLv1) {
            inOut(dCls, ear, skin, eye)
        }
        fusion.setAlgQueue(noiron)
        return translateOut(fusion.act(ear, skin, eye))
    }

    protected fun inOut(dClass: AbsCmdReq, ear: String?, skin: String?, eye: String?) {
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
            timeGate.open()
            kokoro.standBy = false
        } else {
            if (timeGate.isClosed) {
                kokoro.standBy = true
                timeGate.open()
            } else {
                kokoro.standBy = false
            }
        }
        return outResult
    }

    override fun think(ear: String, skin: String, eye: String): String {
        return doIt(ear, skin, eye)
    }

    init {
        AlgDurations = personality.algDurations
        fusion = personality.fusion
        kokoro = personality.kokoro
        noiron = Neuron()
        dClassesLv1 = personality.getdClassesLv1()
    }
}