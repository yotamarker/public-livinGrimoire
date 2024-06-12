package auxiliary_modules

class AnnoyedQue(queLim: Int) {
    private val q1 = RefreshQ()
    private val q2 = RefreshQ()

    init {
        q1.limit = queLim
        q2.limit = queLim
    }

    fun learn(ear: String) {
        if (q1.contains(ear)) {
            q2.add(ear)
            return
        }
        q1.add(ear)
    }

    fun isAnnoyed(ear: String): Boolean {
        return q2.strContainsResponse(ear)
    }
}