package livinGrimoire

class DISkillUtils {
    // alg part based algorithm building methods
    // var args param
    fun algBuilder(vararg algParts: Mutatable): Algorithm {
        // returns an algorithm built with the algPart varargs
        val algParts1 = ArrayList<Mutatable>()
        for (i in algParts.indices) {
            algParts1.add(algParts[i])
        }
        return Algorithm(algParts1)
    }

    // String based algorithm building methods
    fun simpleVerbatimAlgorithm(vararg sayThis: String): Algorithm {
        // returns an algorithm that says the sayThis Strings verbatim per think cycle
        return algBuilder(APVerbatim(*sayThis))
    }

    // String part based algorithm building methods with cloudian (shallow ref object to inform on alg completion)
    fun simpleCloudiandVerbatimAlgorithm(cldBool: CldBool, vararg sayThis: String): Algorithm {
        // returns an algorithm that says the sayThis Strings verbatim per think cycle
        return algBuilder(APCldVerbatim(cldBool, *sayThis))
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