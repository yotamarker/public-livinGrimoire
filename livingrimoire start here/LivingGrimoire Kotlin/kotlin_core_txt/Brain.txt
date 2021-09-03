package com.yotamarker.lgkotlinfull.LGCore

import java.util.*


class Brain(private val action: actionable, mainChobit: ChobitV2, vararg chobits: thinkable) {
    private val chobits = ArrayList<thinkable>()
    private val mainChobit: ChobitV2
    fun doIt(ear: String, skin: String?, eye: String?) {
        var result = ear
        if (mainChobit.standby) {
            result = mainChobit.doIt(ear, skin!!, eye!!)
            action.act(result)
            return
        }
        for (chobit in chobits) {
            if (result.contains("#skin")) {
                result = result.replace("#skin", "")
                result = chobit.think(ear, result, eye!!)
                continue
            }
            if (result.contains("#eye")) {
                result = result.replace("#eye", "")
                result = chobit.think(ear, skin!!, result)
                continue
            }
            result = if (result.isEmpty()) {
                chobit.think(ear, skin!!, eye!!)
            } else {
                chobit.think(result, skin!!, eye!!)
            }
        }
        if (result != ear.toLowerCase()) {
            action.act(result)
        } else {
            action.act("")
        }
    }

    /*
     * chobit hierarchy and responsibilities within the Brain class, which is a
     * Chobit daisy chain.
     *
     * higher lv chobits : reality distortion on drug intake, and input
     * translations(such as languages) lower lv chobits : drug addictions, context
     * sensitive translation of outputs, and primitive behaviors. primitive
     * behaviors : algorithm,s that do not require items and are not dependant on
     * time or place
     */
    init {
        for (chobit in chobits) {
            this.chobits.add(chobit)
        }
        this.mainChobit = mainChobit
    }
}