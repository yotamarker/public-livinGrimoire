package auxiliary_modules

class AnnoyedQue(queLim: Int) {
    private val q1 = RefreshQ()
    private val q2 = RefreshQ()
    private val stuffedQue = RefreshQ()

    init {
        q1.limit = queLim
        q2.limit = queLim
        stuffedQue.limit = queLim
    }

    fun learn(ear: String) {
        if (q1.contains(ear)) {
            q2.add(ear)
            stuffedQue.stuff(ear)
            return
        }
        q1.add(ear)
    }

    fun isAnnoyed(ear: String?): Boolean {
        return q2.strContainsResponse(ear!!)
    }

    fun reset() {
        // Insert unique throwaway strings to reset the state
        for (i in 0 until q1.limit) {
            learn("throwaway_string_$i")
        }
    }

    fun AnnoyedLevel(ear: String, level: Int): Boolean {
        var count = 0
        for (item in stuffedQue.elements) {
            if (item == ear) {
                count++
            }
        }
        return count > level
    }
}