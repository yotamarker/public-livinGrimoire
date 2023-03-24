package com.yotamarker.lgkotlinfull.LGCore

import java.util.*

/* handles permissions using 2 names for the chobit :
* name one : permission lv1 (engages with friend)
* name 2 : permission level2 (for doing things with the owner only)
* */
class DPermitter(  /*
     * conjuratis : change pass : oldPass new password newPassword change lv1 name :
     * pass your name is newName change lv2 name : pass you are newName
     */
    var permission: Permission
) : AbsCmdReq() {
    var permissionLevel = 2
        private set
    private var relevantCase = 0
    private val regexer = RegexUtil()
    override fun output(noiron: Neuron) {
        when (relevantCase) {
            1 -> requipSayAlg(noiron, permission.lv1Name)
            2 -> requipSayAlg(noiron, "got it")
            3 -> requipSayAlg(noiron, "new nickname has been established")
            4 -> requipSayAlg(noiron, "new password accepted")
            else -> {
            }
        }
        relevantCase = 0
    }

    override fun input(ear: String, skin: String, eye: String) {
        permission.setPermissionLevel(ear)
        permissionLevel = permission.getPermissionLevel()
        if (ear.contains("what is your name")) {
            relevantCase = 1
            return
        }
        var password = regexer.regexChecker("(\\w+)(?= you are)", ear)
        var newName = regexer.regexChecker("(?<=you are)(.*)", ear)
        if (permission.setLv2Name(password, newName)) {
            relevantCase = 2
            return
        }

        password = regexer.regexChecker("(\\w+)(?= your name is)", ear)
        newName = regexer.regexChecker("(?<=your name is)(.*)", ear)
        if (permission.setLv1Name(password, newName)) {
            relevantCase = 3
            return
        }

        val oldPass = regexer.regexChecker("(\\w+)(?= new password)", ear)
        val newPass = regexer.regexChecker("(?<=new password)(.*)", ear)
        if (permission.setPassword(oldPass, newPass)) {
            relevantCase = 4
            return
        }
    }

    private fun requipSayAlg(noiron: Neuron, replay: String) {
        val itte: AbsAlgPart = APSay(1, replay)
        val algParts1 = ArrayList<AbsAlgPart>()
        algParts1.add(itte)
        val represantation = "say $replay"
        val algorithm = Algorithm("say", represantation, algParts1)
        noiron.algParts.add(algorithm)
    }
}