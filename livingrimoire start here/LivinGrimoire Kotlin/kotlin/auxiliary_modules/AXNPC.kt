package auxiliary_modules

open class AXNPC(replyStockLim: Int, outputChance: Int) {
    var responder = RefreshQ()
    var dripper = PercentDripper()
    var cmdBreaker = AXCmdBreaker("say")

    init {
        responder.limit = replyStockLim
        if (outputChance in 1..100) {
            dripper.setLimis(outputChance)
        }
    }

    fun respond(): String {
        return if (dripper.drip()) {
            responder.rNDElement
        } else ""
    }

    fun respondPlus(plus: Int): String {
        // increase rate of output
        return if (dripper.dripPlus(plus)) {
            responder.rNDElement
        } else ""
    }

    fun learn(ear: String?): Boolean {
        // say hello there : hello there is learned
        val temp = cmdBreaker.extractCmdParam(ear!!)
        if (temp.isEmpty()) {
            return false
        }
        responder.add(temp)
        return true
    }

    fun strRespond(ear: String): String {
        // respond if ear contains a learned input
        if (ear.isEmpty()) {
            return ""
        }
        return if (dripper.drip() && responder.strContainsResponse(ear)) {
            responder.rNDElement
        } else ""
    }

    fun forceRespond(): String {
        return responder.rNDElement
    }

    fun setConjuration(conjuration: String) {
        cmdBreaker.conjuration = conjuration
    }
}
