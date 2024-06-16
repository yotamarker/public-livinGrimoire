package Auxiliary_Modules;

import java.util.Hashtable;

public class AXMachineCode {
    // common code lines used in machine code to declutter machine code
    public Hashtable<String,Integer> dic = new Hashtable<>();
    public AXMachineCode addKeyValuePair(String key, int value){
        dic.put(key,value);
        return this;
    }
    public int getMachineCodeFor(String key){
        return dic.getOrDefault(key,-1);
    }
}
