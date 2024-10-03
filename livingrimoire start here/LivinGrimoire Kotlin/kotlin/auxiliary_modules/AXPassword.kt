package auxiliary_modules

class AXPassword {
    // code # to open the gate
    // while gate is open, code can be changed with: code new_number
    var isOpen = false
        private set
    private var maxAttempts = 3

    // return remaining login attempts
    var loginAttempts = maxAttempts
        private set

    // event feature
    // get the code during weekly/monthly event after it has been randomized
    var codeEvent = 0
        private set

    fun codeUpdate(ear: String): Boolean {
        // while the gate is toggled on, the password code can be changed
        if (!isOpen) {
            return false
        }
        if (ear.contains("code")) {
            val temp: String = RegexUtil.extractRegex(enumRegexGrimoire.Integer, ear)
            if (!temp.isEmpty()) {
                codeEvent = temp.toInt()
                return true
            }
        }
        return false
    }

    fun openGate(ear: String) {
        if (ear.contains("code") && loginAttempts > 0) {
            val noCode: String = RegexUtil.extractRegex(enumRegexGrimoire.Integer, ear)
            if (noCode.isEmpty()) {
                return
            }
            val tempCode = noCode.toInt()
            if (tempCode == codeEvent) {
                loginAttempts = maxAttempts
                isOpen = true
            } else {
                loginAttempts--
            }
        }
    }

    fun resetAttempts() {
        // should happen once a day or hour to prevent hacking
        loginAttempts = maxAttempts
    }

    fun closeGate() {
        isOpen = false
    }

    fun closeGate(ear: String) {
        if (ear.contains("close")) {
            isOpen = false
        }
    }

    fun setMaxAttempts(maxAttempts: Int) {
        this.maxAttempts = maxAttempts
    }

    fun getCode(): Int {
        return if (isOpen) {
            codeEvent
        } else -1
    }

    fun randomizeCode(lim: Int, minimumLim: Int) {
        // event feature
        codeEvent = DrawRnd().getSimpleRNDNum(lim) + minimumLim
    }
}