package Auxiliary_Modules;

import java.util.HashMap;
import java.util.Map;

public class EventChat {
    private final Map<String, UniqueResponder> dic;

    // Constructor
    public EventChat(UniqueResponder ur, String... args) {
        dic = new HashMap<>();
        for (String arg : args) {
            dic.put(arg, ur);
        }
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
    // Get response
    public String response(String in1) {
        return dic.containsKey(in1) ? dic.get(in1).getAResponse() : "";
    }
}


