package AXJava;

public class UniqueItemSizeLimitedPriorityQueue extends UniqueItemsPriorityQue{
    private int limit = 5;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public void add(String item) {
        if(super.size()<limit){
        super.add(item);}
    }

    @Override
    public String poll() {
        String temp = super.poll();
        if (temp == null){
            return "";
        }
        return temp;
    }
}
