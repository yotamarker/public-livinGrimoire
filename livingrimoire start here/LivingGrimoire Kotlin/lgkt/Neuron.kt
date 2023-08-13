package lgkt


// used to transport algorithms to other classes
class Neuron {
    var algParts = ArrayList<Algorithm>()
    var negativeAlgParts = ArrayList<Algorithm>()
    fun empty() {
        algParts.clear()
        negativeAlgParts.clear()
    }
}