package auxiliary_modules

class AXKeyValuePair {
    var key = ""
    var value = ""

    constructor()
    constructor(key: String, value: String) {
        this.key = key
        this.value = value
    }

    override fun toString(): String {
        return "$key;$value"
    }
}