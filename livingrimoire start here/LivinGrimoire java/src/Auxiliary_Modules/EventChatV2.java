package Auxiliary_Modules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EventChatV2 {
    private final Map<String, UniqueResponder> dic = new HashMap<>();;

    // Constructor
    public EventChatV2() {
    }

    // Add items
    public void addItems(UniqueResponder ur, String... args) {
        for (String arg : args) {
            dic.put(arg, ur);
        }
    }

    // Add key-value pair
    public void addKeyValue(String key, String value) {
        if (dic.containsKey(key)) {
            dic.get(key).addResponse(value);
        } else {
            dic.put(key, new UniqueResponder(value));
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
}
