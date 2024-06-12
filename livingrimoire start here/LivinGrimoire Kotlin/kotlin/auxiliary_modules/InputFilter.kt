package auxiliary_modules

class InputFilter {
    // filter out non-relevant input
    // or filter in relevant data
    fun filter(ear: String, skin: String, eye: String): String {
        // override me
        return ""
    }

    fun filter(ear: String): AXKeyValuePair {
        // override me : key = context/category, value: param
        return AXKeyValuePair()
    }
}