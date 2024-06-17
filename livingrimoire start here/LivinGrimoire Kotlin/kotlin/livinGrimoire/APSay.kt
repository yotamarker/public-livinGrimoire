package livinGrimoire

/* it speaks something x times
 * a most basic skill.
 * also fun to make the chobit say what you want
 * */
class APSay(repetitions: Int, param: String) : Mutatable() {
    protected var param: String
    private var at: Int

    init {
        at = if (repetitions > 10) 10 else repetitions
        this.param = param
    }

    override fun action(ear: String, skin: String, eye: String): String {
        var axnStr = ""
        if (at > 0) {
            if (!ear.equals(param, ignoreCase = true)) {
                axnStr = param
                at--
            }
        }
        return axnStr
    }

    override fun completed(): Boolean {
        return at < 1
    }
}