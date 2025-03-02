package auxiliary_modules

import java.util.HashSet

class AXLearnability(tolerance: Int) {
    private var algSent = false

    // problems that may result because of the last deployed algorithm:
    var defcons = HashSet<String>()

    // major chaotic problems that may result because of the last deployed algorithm:
    var defcon5 = HashSet<String>()

    // goals the last deployed algorithm aims to achieve:
    var goals = HashSet<String>()

    // how many failures / problems till the algorithm needs to mutate (change)
    var trgTolerance: TrgTolerance

    init {
        trgTolerance = TrgTolerance(tolerance)
        trgTolerance.reset()
    }

    fun pendAlg() {
        // an algorithm has been deployed
        // call this method when an algorithm is deployed (in a DiSkillV2 object)
        algSent = true
        trgTolerance.trigger()
    }

    fun pendAlgWithoutConfirmation() {
        // an algorithm has been deployed
        algSent = true
        //no need to await for a thank you or check for goal manifestation :
        // trgTolerance.trigger();
        // using this method instead of the default "pendAlg" is the same as
        // giving importance to the stick and not the carrot when learning
        // this method is mosly fitting work place situations
    }

    fun mutateAlg(input: String): Boolean {
        // recommendation to mutate the algorithm ? true/ false
        if (!algSent) {
            return false
        } // no alg sent=> no reason to mutate
        if (goals.contains(input)) {
            trgTolerance.reset()
            algSent = false
            return false
        }
        // goal manifested the sent algorithm is good => no need to mutate the alg
        if (defcon5.contains(input)) {
            trgTolerance.reset()
            algSent = false
            return true
        }
        // ^ something bad happend probably because of the sent alg
        // recommend alg mutation
        if (defcons.contains(input)) {
            algSent = false
            val mutate = !trgTolerance.trigger()
            if (mutate) {
                trgTolerance.reset()
            }
            return mutate
        }
        // ^ negative result, mutate the alg if this occures too much
        return false
    }

    fun resetTolerance() {
        // use when you run code to change algorithms regardless of learnability
        trgTolerance.reset()
    }
}