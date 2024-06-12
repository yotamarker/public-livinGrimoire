package auxiliary_modules

open class UniqueItemSizeLimitedPriorityQueue : UniqueItemsPriorityQue() {
    // items in the queue are unique and do not repeat
    // the size of the queue is limited
    var limit = 5

    override fun add(item: String) {
        if (super.size() == limit) {
            super.poll()
        }
        super.add(item)
    }

    override fun poll(): String {
        return super.poll() ?: return ""
    }

    override val rNDElement: String
        get() = super.rNDElement ?: ""
    val asList: ArrayList<String>
        get() = elements
}