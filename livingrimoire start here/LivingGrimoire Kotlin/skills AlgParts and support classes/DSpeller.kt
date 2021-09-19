package com.yotamarker.lgkotlinfull.skills

import com.yotamarker.lgkotlinfull.LGCore.*
import java.util.*


class DSpeller : AbsCmdReq() {
    private var param = ""
    private var active: Boolean? = null
    private val diSkillUtil = DISkillUtils()
    private val TrgM1: TrgM1 = TrgM1()
    override fun output(noiron: Neuron) {
        when (param) {
            "about" -> {
                param = ""
                noiron.algParts.add(
                        verbatimGorithm(APVerbatim("I am", "the living grimoire", "I was created by", "moti barski")))
                return
            }
            "creator" -> {
                param = ""
                noiron.algParts.add(verbatimGorithm(APVerbatim("I was created by", "moti barski")))
                return
            }
            "hello" -> {
                param = ""
                val playGround = PlayGround()
                val temp: Int = TrgM1.getMood()
                if (temp == 0) {
                    noiron.algParts.add(verbatimGorithm(APVerbatim("good " + playGround.partOfDay())))
                } else {
                    noiron.algParts.add(verbatimGorithm(APVerbatim("what ever")))
                }
                return
            }
            "god mode" -> {
                param = ""
                noiron.algParts.add(verbatimGorithm(APVerbatim("ainzbuff")))
                return
            }
            "hadouken" -> {
                param = ""
                noiron.algParts.add(diSkillUtil.verbatimGorithm("spell", APVerbatim("hadouken")))
                return
            }
            "shouryuken" -> {
                param = ""
                noiron.algParts.add(diSkillUtil.verbatimGorithm("spell", APVerbatim("shouryuken")))
                return
            }
            else -> {
            }
        }
        if (active!!) {
            val maho = APSpell(param)
            val algParts1 = ArrayList<AbsAlgPart>()
            algParts1.add(maho)
            val algorithm = Algorithm("spell", param, algParts1)
            noiron.algParts.add(algorithm)
        }
    }

    override fun input(ear: String, skin: String, eye: String) {
        TrgM1.trigger(ear, skin, eye)
        when (ear) {
            "what is the time", "what is the date", "what is the year", "current seconds", "current minutes", "current hour", "which day is it", "greet" -> {
                active = true
                param = ear
            }
            "what are you" -> param = "about"
            "hello" -> param = "hello"
            "who made you" -> param = "creator"
            "god mode" -> param = "god mode"
            "hadouken", "hadoken" -> param = "shouryuken"
            "shouryuken", "shoryuken" -> param = "hadouken"
            else -> active = false
        }
    }

    private fun verbatimGorithm(itte: AbsAlgPart): Algorithm {
        // returns a simple algorithm for saying sent parameter
        // AbsAlgPart itte = new APVerbatim("I am");
        val representation = "about"
        val algParts1 = ArrayList<AbsAlgPart>()
        algParts1.add(itte)
        return Algorithm("about", representation, algParts1)
    }
}