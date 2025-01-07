package Auxiliary_Modules;

import java.util.HashMap;
import java.util.Map;

class EventChat {
    private final Map<String, UniqueResponder> dic;

    public EventChat(UniqueResponder ur, String... args) {
        dic = new HashMap<>();
        for (String arg : args) {
            dic.put(arg, ur);
        }
    }

    public String response(String in1) {
        UniqueResponder ur = dic.get(in1);
        return ur != null ? ur.getAResponse() : "";
    }
}

