package Auxiliary_Modules;

import java.util.*;

import java.util.ArrayList;
import java.util.List;

public class LimUniqueResponder {
    private final List<String> responses;
    private UniqueRandomGenerator urg= new UniqueRandomGenerator(0);
    private final int lim;

    // Constructor
    public LimUniqueResponder(int lim) {
        responses = new ArrayList<>();
        this.lim = lim;
    }

    // Method to get a response
    public String getAResponse() {
        if (responses.isEmpty()) {
            return "";
        }
        return responses.get(urg.getUniqueRandom());
    }

    // Method to check if responses contain a string
    public boolean responsesContainsStr(String item) {
        return responses.contains(item);
    }

    // Method to check if a string contains any response
    public boolean strContainsResponse(String item) {
        for (String response : responses) {
            if (response.isEmpty()) {
                continue;
            }
            if (item.contains(response)) {
                return true;
            }
        }
        return false;
    }

    // Method to add a response
    public void addResponse(String s1) {
        if(this.responses.size() > lim - 1){responses.remove(0);}
        if (!responses.contains(s1)) {
            responses.add(s1);
            urg = new UniqueRandomGenerator(responses.size());
        }
    }
    public void addResponses(String... replies){
        for (String value : replies) {
            addResponse(value);
        }
    }
    public String getSavableStr() {
        return String.join("_", responses);
    }
    public String getLastItem() {
        if (responses.isEmpty()) {
            return "";
        }
        return responses.get(responses.size() - 1);
    }

}


