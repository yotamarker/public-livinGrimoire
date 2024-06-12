package auxiliary_modules

class Differ {
    var powerLevel = 90
        private set
    var difference = 0
        private set

    fun clearPowerLVDifference() {
        difference = 0
    }

    fun samplePowerLV(pl: Int) {
        // pl is the current power level
        difference = pl - powerLevel
        powerLevel = pl
    }
}