package auxiliary_modules

import java.util.*

open class LGFIFO<T> {
    //first in first out queue
    var elements = ArrayList<T>()

    open fun add(item: T) {
        elements.add(item)
    }

    fun size(): Int {
        return elements.size
    }

    open fun peak(): T? {
        return if (size() == 0) {
            null
        } else elements[0]
    }

    open fun poll(): T? {
        if (size() == 0) {
            return null
        }
        val result = elements[0]
        elements.removeAt(0)
        return result
    }

    open fun removeItem(item: T) {
        if (elements.contains(item)) {
            elements.remove(item)
        }
    }

    fun clear() {
        elements.clear()
    }

    private val rand = Random()
    open val rNDElement: T?
        get() = if (elements.isEmpty()) {
            null
        } else elements[rand.nextInt(elements.size)]

    operator fun contains(input: T): Boolean {
        return elements.contains(input)
    }

    val isEmpty: Boolean
        get() = elements.isEmpty()

    fun remove(element: T) {
        elements.remove(element)
    }

    operator fun iterator(): Iterator<T> {
        return elements.iterator()
    }
}