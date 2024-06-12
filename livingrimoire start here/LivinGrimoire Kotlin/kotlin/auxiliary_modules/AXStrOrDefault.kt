package auxiliary_modules

class AXStrOrDefault {
    fun getOrDefault(str1: String, default1: String): String {
        return str1.ifEmpty { default1 }
    }
}