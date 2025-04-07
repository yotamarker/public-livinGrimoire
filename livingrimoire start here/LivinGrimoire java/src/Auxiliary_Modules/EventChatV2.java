package Auxiliary_Modules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class EventChatV2 {
    private final Map<String, LimUniqueResponder> dic = new HashMap<>();
    private final HashSet<String> modifiedKeys = new HashSet<>();
    private final int lim;

    // Constructor
    public EventChatV2(int lim) {
        this.lim = lim;
    }

    public HashSet<String> getModifiedKeys() {
        return modifiedKeys;
    }
    public Boolean keyExists(String key){
        // if the key was active true is returned
        return modifiedKeys.contains(key);
    }

    // Add items
    public void addItems(LimUniqueResponder ur, String... args) {
        for (String arg : args) {
            dic.put(arg, ur.clone());
        }
    }


    public void addFromDB(String key, String value){
        if (value.isEmpty()||value.equals("null")){return;}
        AXStringSplit tool1 = new AXStringSplit();
        String[] values = tool1.split(value);
        if (!dic.containsKey(key)){dic.put(key, new LimUniqueResponder(lim));}
        for (String item : values) {
            dic.get(key).addResponse(item);
        }
    }
    // Add key-value pair
    public void addKeyValue(String key, String value) {
        modifiedKeys.add(key);
        if (dic.containsKey(key)) {
            dic.get(key).addResponse(value);
        } else {
            dic.put(key, new LimUniqueResponder(lim));
            dic.get(key).addResponse(value);
        }
    }
    public void addKeyValues(ArrayList<AXKeyValuePair> elizaResults) {
        for (AXKeyValuePair pair : elizaResults) {
            // Access the key and value of each AXKeyValuePair object
            addKeyValue(pair.getKey(),pair.getValue());
        }
    }
    // Get response
    public String response(String in1) {
        return dic.containsKey(in1) ? dic.get(in1).getAResponse() : "";
    }
    public String responseLatest(String in1) {
        return dic.containsKey(in1) ? dic.get(in1).getLastItem() : "";
    }
    public String getSaveStr(String key) {
        return dic.get(key).getSavableStr();
    }
}
