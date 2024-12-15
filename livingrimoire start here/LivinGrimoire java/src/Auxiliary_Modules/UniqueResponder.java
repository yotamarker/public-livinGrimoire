package Auxiliary_Modules;

import java.util.*;

import java.util.ArrayList;
import java.util.List;

public class UniqueResponder {
    private final List<String> responses;
    private UniqueRandomGenerator urg;

    // Constructor
    public UniqueResponder(String... replies) {
        responses = new ArrayList<>();
        urg = new UniqueRandomGenerator(replies.length);
        responses.addAll(Arrays.asList(replies));
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
        if (!responses.contains(s1)) {
            responses.add(s1);
            urg = new UniqueRandomGenerator(responses.size());
        }
    }
}


