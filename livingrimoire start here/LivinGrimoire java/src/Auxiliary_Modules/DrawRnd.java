package Auxiliary_Modules;

import java.util.ArrayList;
import java.util.Random;

public class DrawRnd {
    // draw a random element, then take said element out
    private ArrayList<String> strings = new ArrayList<>();
    private final ArrayList<String> stringsSource = new ArrayList<>();
    private final Random rand = new Random();
    public DrawRnd(String... values) {
        for (String value : values) {
            strings.add(value);
            stringsSource.add(value);
        }
    }
    public void addElement(String element){
        strings.add(element);
        stringsSource.add(element);
    }
    public String draw(){
        if (strings.isEmpty()) {return "";}

        int x = rand.nextInt(strings.size());
        String element = strings.get(x);
        strings.remove(x);
        return element;
    }
    public int getSimpleRNDNum(int bound){
        // return 0->bound-1
        return rand.nextInt(bound);
    }
    private final LGTypeConverter tc = new LGTypeConverter();
    public int drawAsInt(){
        if (strings.isEmpty()) {return 0;}
        Random rand = new Random();
        int x = rand.nextInt(strings.size());
        String element = strings.get(x);
        strings.remove(x);
        return tc.convertToInt(element);
    }
    public void reset(){
        DeepCopier dc = new DeepCopier();
        strings = dc.copyList(stringsSource);
    }
    public boolean isEmptied(){return strings.isEmpty();}
    public String renewableDraw(){
        // using this method assumes at least 1 element was added to the module
        // otherwise you can expect an error!
        if (strings.isEmpty()) {reset();}
        int x = rand.nextInt(strings.size());
        String element = strings.get(x);
        strings.remove(x);
        return element;
    }
}
