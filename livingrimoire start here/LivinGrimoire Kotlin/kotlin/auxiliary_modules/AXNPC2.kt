package auxiliary_modules

class AXNPC2(replyStockLim: Int, outputChance: Int) : AXNPC(replyStockLim, outputChance) {
    var annoyedQue = AnnoyedQue(5)
    fun strLearn(ear: String?) {
        // learns inputs containing strings that are repeatedly used by others
        annoyedQue.learn(ear!!)
        if (annoyedQue.isAnnoyed(ear)) {
            responder.add(ear)
        }
    }
}