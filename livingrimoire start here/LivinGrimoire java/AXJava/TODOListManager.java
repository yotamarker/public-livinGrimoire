package AXJava;

public class TODOListManager {
    private UniqueItemSizeLimitedPriorityQueue q1 = new UniqueItemSizeLimitedPriorityQueue();
    private UniqueItemSizeLimitedPriorityQueue backup = new UniqueItemSizeLimitedPriorityQueue();
    private DrawRnd d1 = new DrawRnd();

    public TODOListManager(int todoLim) {
        q1.setLimit(todoLim);
        backup.setLimit(todoLim);
    }

    public void addElement(String e1){
        q1.add(e1);
    }
    public String getTask(){
        String temp = q1.poll();
        if(!temp.isEmpty()){backup.add(temp);}
        return temp;
    }
    public String getOldTask(){
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
}
