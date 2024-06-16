package Auxiliary_Modules;

import java.util.Hashtable;

public class Catche {
    // limited sized dictionary
    private int limit;
    private UniqueItemSizeLimitedPriorityQueue keys;
    private Hashtable<String,String> d1 = new Hashtable<>();
    public Catche(int size) {
        this.limit = size;
        this.keys = new UniqueItemSizeLimitedPriorityQueue();
        this.keys.setLimit(size);
    }
    public void insert(String key,String value){
        // update
        if (d1.contains(key)){
            d1.put(key,value);
            return;
        }
        // insert
        if (keys.size() == limit){
            String temp = keys.peak();
            d1.remove(key);
        }
        keys.add(key);
        d1.put(key,value);
    }
    public void clear() {
        keys.clear();
        d1.clear();
    }
    public String read(String key){
        return d1.getOrDefault(key,"null");
    }
}
