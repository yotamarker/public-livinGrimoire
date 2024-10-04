package Auxiliary_Modules;

import java.util.HashMap;
import java.util.Map;

public class AXFunnel {
    // funnel many inputs to fewer or one input
    // allows using command variations in skills
    private final Map<String, String> dic;
    private String defaultStr;

    public AXFunnel() {
        this.dic = new HashMap<>();
        this.defaultStr = "default";
    }

    public void setDefault(String defaultStr) {
        this.defaultStr = defaultStr;
    }

    public AXFunnel addKV(String key, String value) {
        // add key-value pair
        dic.put(key, value);
        return this;
    }

    public AXFunnel addK(String key) {
        // add key with default value
        dic.put(key, this.defaultStr);
        return this;
    }

    public String funnel(String key) {
        // get value from dictionary or return the key itself as default
        return dic.getOrDefault(key, key);
    }
    public String funnel_or_empty(String key) {
        // get value from dictionary or return ""
        return dic.getOrDefault(key, "");
    }
}
