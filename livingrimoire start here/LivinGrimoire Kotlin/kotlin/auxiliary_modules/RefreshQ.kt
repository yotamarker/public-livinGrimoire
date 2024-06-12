package auxiliary_modules

class RefreshQ : UniqueItemSizeLimitedPriorityQueue() {
    override fun removeItem(item: String) {
        super.elements.remove(item)
    }

    override fun add(item: String) {
        // FILO
        if (super.contains(item)) {
            removeItem(item)
        }
        super.add(item)
    }
}