package auxiliary_modules

class AXCmdBreaker( // separate command parameter from the command
    var conjuration: String
) {
    fun extractCmdParam(s1: String): String {
        return if (s1.contains(conjuration)) {
            s1.replace(conjuration, "").trim { it <= ' ' }
        } else ""
    }
}