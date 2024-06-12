package auxiliary_modules

class AXStringSplit {
    // may be used to prepare data before saving or after loading
    // the advantage is less data fields. for example: {skills: s1_s2_s3}
    private var spChar = "_"
    fun setSpChar(spChar: String) {
        this.spChar = spChar
    }

    fun split(s1: String): Array<String> {
        return s1.split(spChar.toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
    }

    fun stringBuilder(sArr: Array<String?>): String {
        val sb = StringBuilder()
        for (i in 0 until sArr.size - 1) {
            sb.append(sArr[i])
            sb.append(spChar)
        }
        sb.append(sArr[sArr.size - 1])
        return sb.toString()
    }
}