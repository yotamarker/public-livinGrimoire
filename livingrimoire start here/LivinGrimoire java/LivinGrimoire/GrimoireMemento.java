package LivinGrimoire;

import java.util.Hashtable;

public class GrimoireMemento {
    private AbsDictionaryDB absDictionaryDB;

    public GrimoireMemento(AbsDictionaryDB absDictionaryDB) {
        super();
        this.absDictionaryDB = absDictionaryDB;
    }
    public String simpleLoad(String key){
        return this.absDictionaryDB.load(key);
    }
    public void simpleSave(String key, String value){
        if(key.startsWith("AP")||key.isEmpty()||value.isEmpty()){return;}
        this.absDictionaryDB.save(key,value);
    }
}