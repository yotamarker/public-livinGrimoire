package auxiliary_modules

class AXConvince(private val req: AXContextCmd) {
    private val reset = Responder("reset")
    private var min = 3 // minimum requests till agreement
    private val rnd = DrawRnd()
    private var counter = 0
    var isConvinced = false
        private set
    private var max_eff_to_convince = 6
    fun setMax_eff_to_convince(max_eff_to_convince: Int) {
        this.max_eff_to_convince = max_eff_to_convince
    }

    fun engage(ear: String): Int {
        // 0:nothing, 1: no, 2:yes, 3: just been reset to no again
        if (reset.responsesContainsStr(ear)) {
            counter = 0
            isConvinced = false
            min += rnd.getSimpleRNDNum(max_eff_to_convince)
            return 3
        }
        if (req.engageCommand(ear)) {
            counter++
            return if (counter < min) {
                1
            } else {
                isConvinced = true
                2
            } // convinced
        }
        return 0
    }
}