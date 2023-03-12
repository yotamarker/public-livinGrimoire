package AXJava;

import LivinGrimoire.LGFIFO;

public class UniqueItemsPriorityQue extends LGFIFO<String>{
    // a priority queue without repeating elements
    @Override
    public void add(String item) {
        if(!super.contains(item)){
        super.add(item);}
    }
}
