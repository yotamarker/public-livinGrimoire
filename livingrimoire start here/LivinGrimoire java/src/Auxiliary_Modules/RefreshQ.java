package Auxiliary_Modules;

public class RefreshQ extends UniqueItemSizeLimitedPriorityQueue{
    public void removeItem(String item){
        super.elements.remove(item);
    }

    @Override
    public void add(String item) {
        // FILO
        if (super.contains(item)){
            removeItem(item);
        }
        super.add(item);
    }
    public void stuff(String data) {
        // FILO 1st in last out
        if (elements.size() == getLimit()) {
            poll();
        }
        elements.add(data);
    }

}
