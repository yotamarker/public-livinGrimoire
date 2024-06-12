package auxiliary_modules

class PercentDripper {
    private val dr = DrawRnd()
    private var limis = 35
    fun setLimis(limis: Int) {
        this.limis = limis
    }

    fun drip(): Boolean {
        return dr.getSimpleRNDNum(100) < limis
    }

    fun dripPlus(plus: Int): Boolean {
        return dr.getSimpleRNDNum(100) < limis + plus
    }
}