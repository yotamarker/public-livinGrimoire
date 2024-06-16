package Auxiliary_Modules;

import java.util.HashMap;
import java.util.Map;

public class AXFunnelResponder {
    // funnel strings into replies
    private Map<String, Responder> dic;

    public AXFunnelResponder() {
        this.dic = new HashMap<>();
    }

    public AXFunnelResponder addKV(String key, Responder value) {
        // add key-value pair
        dic.put(key, value);
        return this;
    }

    public String funnel(String key) {
        // default funnel = key
        if (dic.containsKey(key)){
            return dic.get(key).getAResponse();
        }
        return key;
    }
    public String funnelOrNothing(String key) {
        // default funnel = ""
        if (dic.containsKey(key)){
            return dic.get(key).getAResponse();
        }
        return "";
    }
}
