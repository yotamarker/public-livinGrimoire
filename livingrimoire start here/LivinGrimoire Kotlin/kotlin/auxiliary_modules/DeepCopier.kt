package auxiliary_modules

class DeepCopier {
    fun copyList(original: ArrayList<String>?): ArrayList<String> {
        return ArrayList(original)
    }

    fun copyListOfInts(original: ArrayList<Int>?): ArrayList<Int> {
        return ArrayList(original)
    }
}