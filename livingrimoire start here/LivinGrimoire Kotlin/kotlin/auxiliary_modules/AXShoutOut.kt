package auxiliary_modules

class AXShoutOut {
    private var isActive = false
    var handshake = Responder()
    fun activate() {
        // make engage-able
        isActive = true
    }

    fun engage(ear: String): Boolean {
        if (ear.isEmpty()) {
            return false
        }
        if (isActive) {
            if (handshake.strContainsResponse(ear)) {
                isActive = false
                return true // shout out was replied!
            }
        }
        // unrelated reply to shout out, shout out context is outdated
        isActive = false
        return false
    }
}