package com.yotamarker.lgkotlinfull.LGCore

//D class responsible for exploring :
// learning, mutating algorithms, requiping APs with objects or skill mods
// the sould resides here
class DExplorer : AbsCmdReq() {
    private var failureCounter = 0
    private var prevAP = ""
    override fun output(noiron: Neuron) {
        // TODO Auto-generated method stub
    }

    override fun input(ear: String, skin: String, eye: String) {
        // TODO Auto-generated method stub
    }

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
                cera.isActive = false
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
                val mutant = cera.alg!!.algParts[cera.at] as Mutatable
                cera.alg!!.algParts[cera.at] = mutant.mutation()
            }
            enumFail.cloudian -> cera.isActive = false
            else -> {
            }
        }
    }
}