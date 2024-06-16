package Auxiliary_Modules;

import java.util.ArrayList;

public class UniqueItemSizeLimitedPriorityQueue extends UniqueItemsPriorityQue{
    // items in the queue are unique and do not repeat
    // the size of the queue is limited
    private int limit = 5;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public void add(String item) {
        if(super.size() == limit){
            super.poll();}
        super.add(item);
    }

    @Override
    public String poll() {
        String temp = super.poll();
        if (temp == null){
            return "";
        }
        return temp;
    }

    @Override
    public String getRNDElement() {
        String temp = super.getRNDElement();
        if (temp == null){
            return "";
        }
        return temp;
    }

    public ArrayList<String> getAsList(){
        return getElements();
    }
}
