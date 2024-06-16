package Auxiliary_Modules;

public class TODOListManager {
    /* manages to do tasks.
    q1 tasks are mentioned once, and forgotten
    backup tasks are the memory of recently mentioned tasks
    * */
    private final UniqueItemSizeLimitedPriorityQueue q1 = new UniqueItemSizeLimitedPriorityQueue();
    private final UniqueItemSizeLimitedPriorityQueue backup = new UniqueItemSizeLimitedPriorityQueue();

    public TODOListManager(int todoLim) {
        q1.setLimit(todoLim);
        backup.setLimit(todoLim);
    }

    public void addTask(String e1){
        q1.add(e1);
    }
    public String getTask(){
        String temp = q1.poll();
        if(!temp.isEmpty()){backup.add(temp);}
        return temp;
    }
    public String getOldTask(){
        // task graveyard (tasks you've tackled already)
        return backup.getRNDElement();
    }
    public void clearAllTasks(){
        q1.clear();
        backup.clear();
    }
    public void clearTask(String task){
        q1.removeItem(task);
        backup.removeItem(task);
    }
    public Boolean containsTask(String task){
        return backup.contains(task);
    }
}
