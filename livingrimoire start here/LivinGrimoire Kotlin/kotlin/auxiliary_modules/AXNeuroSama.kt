package auxiliary_modules

class AXNeuroSama    // the higher the rate the less likely to decorate outputs
    (private val rate: Int) {
    private val nyaa = Responder(" heart", " heart", " wink", " heart heart heart")
    private val rnd = DrawRnd()
    fun decorate(output: String): String {
        if (output.isEmpty()) {
            return output
        }
        return if (rnd.getSimpleRNDNum(rate) == 0) {
            output + nyaa.aResponse
        } else output
    }
}