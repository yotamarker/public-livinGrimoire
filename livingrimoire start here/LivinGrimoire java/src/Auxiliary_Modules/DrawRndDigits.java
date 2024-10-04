package Auxiliary_Modules;

import java.util.ArrayList;
import java.util.Random;

public class DrawRndDigits {
    // draw a random integer, then take said element out
    private ArrayList<Integer> strings = new ArrayList<>();
    private ArrayList<Integer> stringsSource = new ArrayList<>();
    private Random rand = new Random();
    public DrawRndDigits(int... values) {
        for (int value : values) {
            strings.add(value);
            stringsSource.add(value);
        }
    }
    public void addElement(int element){
        strings.add(element);
        stringsSource.add(element);
    }
    public int draw(){
        if (strings.isEmpty()) {return -1;}

        int x = rand.nextInt(strings.size());
        int element = strings.get(x);
        strings.remove(x);
        return element;
    }
    public int getSimpleRNDNum(int bound){
        // return 0->bound-1
        return rand.nextInt(bound);
    }
    public void reset(){
        DeepCopier dc = new DeepCopier();
        strings = dc.copyListOfInts(stringsSource);
    }
}
