package lgkt


class DISkillUtils {
    // alg part based algorithm building methodes
    fun onePartAlgorithm(algMarker: String, algPart: Mutatable): Algorithm {
        // returns a simple algorithm containing 1 alg part
        val representation = "rep_$algMarker"
        val algParts1 = ArrayList<Mutatable>()
        algParts1.add(algPart)
        return Algorithm(algMarker, representation, algParts1)
    }

    // var args param
    fun algBuilder(algMarker: String, vararg algParts: Mutatable): Algorithm {
        // returns a simple algorithm for saying sent parameter
        val representation = "rep_$algMarker"
        val algParts1 = ArrayList<Mutatable>()
        for (i in algParts.indices) {
            algParts1.add(algParts[i])
        }
        return Algorithm(algMarker, representation, algParts1)
    }

    fun onePartAlgorithmWithRepresantation(
        algRepresentation: String,
        algMarker: String,
        algPart: Mutatable
    ): Algorithm {
        // returns a simple algorithm containing 1 alg part
        val algParts1 = ArrayList<Mutatable>()
        algParts1.add(algPart)
        return Algorithm(algMarker!!, algRepresentation, algParts1)
    }

    fun algBuilderWithRepresantation(
        algRepresentation: String,
        algMarker: String,
        vararg algParts: Mutatable
    ): Algorithm {
        // returns a simple algorithm for saying sent parameter
        val algParts1 = ArrayList<Mutatable>()
        for (i in algParts.indices) {
            algParts1.add(algParts[i])
        }
        return Algorithm(algMarker!!, algRepresentation, algParts1)
    }

    // String part based algorithm building methodes
    fun simpleVerbatimAlgorithm(algMarker: String, vararg sayThis: String): Algorithm {
        // returns alg that says the word string (sayThis)
        return onePartAlgorithm(algMarker, APVerbatim(*sayThis))
    }

    fun simpleVerbatimAlgorithmWithRepresantation(
        algRepresentation: String,
        algMarker: String,
        vararg sayThis: String
    ): Algorithm {
        // returns alg that says the word string (sayThis)
        return onePartAlgorithmWithRepresantation(algRepresentation, algMarker, APVerbatim(*sayThis))
    }

    // String part based algorithm building methodes with cloudian (shallow ref object to inform on alg completion)
    fun simpleCloudiandVerbatimAlgorithmWithRepresantation(
        algRepresentation: String,
        cldBool: CldBool,
        algMarker: String,
        vararg sayThis: String
    ): Algorithm {
        // returns alg that says the word string (sayThis)
        return onePartAlgorithmWithRepresantation(algRepresentation, algMarker, APCldVerbatim(cldBool, *sayThis))
    }

    fun simpleCloudiandVerbatimAlgorithm(cldBool: CldBool, algMarker: String, vararg sayThis: String): Algorithm {
        // returns alg that says the word string (sayThis)
        return onePartAlgorithm(algMarker, APCldVerbatim(cldBool, *sayThis))
    }

    fun strContainsList(str1: String, items: ArrayList<String>): String {
        // returns the 1st match between words in a string and values in a list.
        for (temp in items) {
            if (str1.contains(temp)) {
                return temp
            }
        }
        return ""
    }
}