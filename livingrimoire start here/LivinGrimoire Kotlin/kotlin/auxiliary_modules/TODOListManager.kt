package auxiliary_modules

class TODOListManager(todoLim: Int) {
    /* manages to do tasks.
    q1 tasks are mentioned once, and forgotten
    backup tasks are the memory of recently mentioned tasks
    * */
    private val q1 = UniqueItemSizeLimitedPriorityQueue()
    private val backup = UniqueItemSizeLimitedPriorityQueue()

    init {
        q1.limit = todoLim
        backup.limit = todoLim
    }

    fun addTask(e1: String?) {
        q1.add(e1!!)
    }

    val task: String
        get() {
            val temp = q1.poll()
            if (!temp.isEmpty()) {
                backup.add(temp)
            }
            return temp
        }
    val oldTask: String
        get() =// task graveyard (tasks you've tackled already)
            backup.rNDElement

    fun clearAllTasks() {
        q1.clear()
        backup.clear()
    }

    fun clearTask(task: String) {
        q1.removeItem(task)
        backup.removeItem(task)
    }

    fun containsTask(task: String): Boolean {
        return backup.contains(task)
    }
}