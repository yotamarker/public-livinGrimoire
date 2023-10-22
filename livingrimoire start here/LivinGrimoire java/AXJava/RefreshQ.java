package AXJava;

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
}
