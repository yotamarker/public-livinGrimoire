package lgkt

class DExplorer {
    private var failureCounter = 0
    private var prevAP = ""
    fun mutate(cera: Cerabellum, failure: enumFail) {
        var AP = cera.emot
        /*
         * group relies on a naming convention each class in a mutation series must have
         * the same class name concated with a number : APMoan1, APMoan2, APMaon3 ...
         */AP = AP.replace("\\d+".toRegex(), "")
        // give up ? :
        if (prevAP.contains(AP) && failure.toString() != enumFail.ok.toString()) {
            failureCounter++
            if (failureCounter > cera.mutationLimitOfActiveAlgPart) {
                cera.setActive(false)
                // this.failureCounter = 0;
            }
        } else {
            if (!prevAP.contains(AP)) {
                failureCounter = 0
            }
        }
        prevAP = AP
        when (failure) {
            enumFail.fail -> {
                cera.alg!!.algParts[cera.at] = cera.alg!!.algParts[cera.at].mutation()!!
            }

            enumFail.cloudian -> cera.setActive(false)
            else -> {}
        }
    }
}