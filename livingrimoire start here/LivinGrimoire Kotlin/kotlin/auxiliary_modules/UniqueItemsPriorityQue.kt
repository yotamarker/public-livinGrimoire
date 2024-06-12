package auxiliary_modules

open class UniqueItemsPriorityQue : LGFIFO<String>() {
    // a priority queue without repeating elements
    override fun add(item: String) {
        if (!super.contains(item)) {
            super.add(item)
        }
    }

    override fun peak(): String {
        return super.peak() ?: return ""
    }

    fun strContainsResponse(str: String): Boolean {
        var result = false
        for (tempStr in elements) {
            if (str.contains(tempStr)) {
                result = true
                break
            }
        }
        return result
    }
}