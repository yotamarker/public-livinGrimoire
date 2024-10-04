package Auxiliary_Modules;

import java.util.ArrayList;

public class DeepCopier {
    public ArrayList<String> copyList(ArrayList<String> original){
        return new ArrayList<>(original);
    }
    public ArrayList<Integer> copyListOfInts(ArrayList<Integer> original){
        return new ArrayList<>(original);
    }
}
