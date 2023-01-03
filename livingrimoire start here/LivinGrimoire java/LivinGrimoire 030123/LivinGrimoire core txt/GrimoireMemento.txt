package LivinGrimoire;

import java.util.Hashtable;

public class GrimoireMemento {
    private Hashtable<String, String> rootToAPNumDic = new Hashtable<>();
    private Hashtable<String, Mutatable> APNumToObjDic = new Hashtable<>();
    private AbsDictionaryDB absDictionaryDB;

    public GrimoireMemento(AbsDictionaryDB absDictionaryDB) {
        super();
        this.absDictionaryDB = absDictionaryDB;
    }

    public Mutatable load(Mutatable obj) {
        /*
         * load final mutation from memory of obj
         */
        String objName = obj.getClass().getSimpleName();
        String objRoot = objName.replaceAll("\\d+", "");
        // if not in active DB try adding from external DB
        if (!rootToAPNumDic.containsKey(objRoot)) {
            String temp = this.absDictionaryDB.load(objRoot);
            if (temp != "null") {
                rootToAPNumDic.put(objRoot, temp);
            }
        }
        if (!rootToAPNumDic.containsKey(objRoot)) {
            rootToAPNumDic.put(objRoot, objName);
            return obj;
        }

        if (rootToAPNumDic.get(objRoot).equals(objName)) {
            return obj;
        } else {
            String APNum = rootToAPNumDic.get(objRoot);
            if (APNumToObjDic.containsKey(APNum)) {
                return APNumToObjDic.get(APNum).clone();
            } else {
                loadMutations(obj, objName, objRoot);
                return APNumToObjDic.get(APNum).clone();
            }
        }
    }

    public void reqquipMutation(String mutationAPName) {
        // save mutation
        rootToAPNumDic.put(mutationAPName.replaceAll("\\d+", ""), mutationAPName);
        this.absDictionaryDB.save(mutationAPName.replaceAll("\\d+", ""), mutationAPName);
    }

    private void loadMutations(Mutatable obj, String objName, String objRoot) {
        // make sure all the AP mutation sets of obj are present
        // this assumes the last mutation mutates into the prime mutation
        Mutatable mutant;
        String end = objName;
        do {
            APNumToObjDic.put(obj.getClass().getSimpleName(), obj.clone());
            mutant = (Mutatable) obj;
            obj = mutant.mutation();
        }
        while (!end.equals(obj.getClass().getSimpleName()));
    }
    public String simpleLoad(String key){
        return this.absDictionaryDB.load(key);
    }
    public void simpleSave(String key, String value){
        if(key.startsWith("AP")||key.isEmpty()||value.isEmpty()){return;}
        this.absDictionaryDB.save(key,value);
    }
}