package LivinGrimoire;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class LGFIFO <T>{
    //first in first out queue
    private ArrayList<T> elements = new ArrayList<T>();
    public void add(T item){
        elements.add(item);
    }
    public int size(){
        return elements.size();
    }
    public T poll(){
        T result = elements.get(0);
        elements.remove(0);
        return result;
    }
    private Random rand = new Random();
    public T getRNDElement(){
        return  elements.get(rand.nextInt(elements.size()));
    }
    public boolean contains(T input){
        return elements.contains(input);
    }
    public Boolean isEmpty(){
        return elements.isEmpty();
    }
    public void remove(T element){
        elements.remove(element);
    }
    public Iterator<T> iterator(){
        return elements.iterator();
    }

    public ArrayList<T> getElements() {
        return elements;
    }
}
