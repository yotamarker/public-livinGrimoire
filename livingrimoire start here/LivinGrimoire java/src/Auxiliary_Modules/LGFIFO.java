package Auxiliary_Modules;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class LGFIFO <T>{
    //first in first out queue
    protected ArrayList<T> elements = new ArrayList<>();
    public void add(T item){
        elements.add(item);
    }
    public int size(){
        return elements.size();
    }
    public T peak(){
        if(size() == 0){
            return null;
        }
        return elements.get(0);
    }
    public T poll(){
        if(size() == 0){
            return null;
        }
        T result = elements.get(0);
        elements.remove(0);
        return result;
    }
    public void removeItem(T item){
        elements.remove(item);
    }
    public void clear(){
        elements.clear();
    }
    private Random rand = new Random();
    public T getRNDElement(){
        if (elements.isEmpty()) {
            return null;
        }
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
    public void setElements(ArrayList<T> elements) {
        this.elements = elements;
    }
}
