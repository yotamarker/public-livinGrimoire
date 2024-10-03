package auxiliary_modules

class LGTypeConverter {
    fun convertToInt(v1: String): Int {
        val temp: String = RegexUtil.extractRegex(enumRegexGrimoire.Integer, v1)
        return if (temp.isEmpty()) {
            0
        } else temp.toInt()
    }

    fun convertToDouble(v1: String): Double {
        val temp: String = RegexUtil.extractRegex(enumRegexGrimoire.Double_num, v1)
        return if (temp.isEmpty()) {
            0.0
        } else temp.toDouble()
    }
}