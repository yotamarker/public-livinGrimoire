package lgkt

/* it speaks something x times
 * a most basic skill.
 * also fun to make the chobit say what you want
 * */
class APSay(repetitions: Int, param: String) : Mutatable() {
    protected var param: String
    private var at: Int

    init {
//        var repetitions = repetitions
        if (repetitions > 10) {
            at = 10
        }
        else
        {at = repetitions}
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

    override fun clone(): Mutatable {
        return APSay(at, param)
    }
}