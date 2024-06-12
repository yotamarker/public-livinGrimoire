package auxiliary_modules

class LGTypeConverter {
    private val r1: RegexUtil = RegexUtil()
    fun convertToInt(v1: String): Int {
        val temp: String = r1.extractRegex(enumRegexGrimoire.Integer, v1)
        return if (temp.isEmpty()) {
            0
        } else temp.toInt()
    }

    fun convertToDouble(v1: String): Double {
        val temp: String = r1.extractRegex(enumRegexGrimoire.Double_num, v1)
        return if (temp.isEmpty()) {
            0.0
        } else temp.toDouble()
    }
}