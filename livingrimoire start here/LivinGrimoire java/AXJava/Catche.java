package AXJava;

import java.util.Hashtable;

public class Catche {
    private Hashtable<String,String> dic1 = new Hashtable<>();
    private int limit = 3;

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getLimit() {
        return limit;
    }

    public Boolean containsKey(String str1){
        return dic1.containsKey(str1);
    }
    public void overwriteInsert(String oldKey,String key, String value){
        int index = dicIndex.get(oldKey);
        dicIndex.put(key,index);
        dicIndex.remove(oldKey);
        indexDic.remove(index);
        indexDic.put(index,key);
        dic1.remove(oldKey);
        dic1.put(key,value);
    }
    public Boolean hasRoom(){
        return limit > dic1.size();
    }
    private Hashtable<Integer,String> indexDic = new Hashtable<>();
    private Hashtable<String, Integer> dicIndex = new Hashtable<>();
    public void Insert(String key, String value){
        if (hasRoom()) {
            int index = dic1.size();
            dic1.put(key,value);
            indexDic.put(index,key);
            dicIndex.put(key,index);
        }
    }
    public void insertAt(int position, String key, String value){
        if (!(position < limit) || !(position > -1)){
            return;
        }
        String oldkey = indexDic.get(position);
        dic1.remove(oldkey);
        dic1.put(key,value);
        indexDic.put(position, key);
        dicIndex.remove(oldkey);
        dicIndex.put(key,position);
    }
    public String getItem(String key){
        return dic1.getOrDefault(key,"");
    }
    public String getItem(int position){
        if (!(position < limit) || !(position > -1)){
            return "";
        }
        String key = indexDic.get(position);
        return dic1.getOrDefault(key,"");
    }
}
