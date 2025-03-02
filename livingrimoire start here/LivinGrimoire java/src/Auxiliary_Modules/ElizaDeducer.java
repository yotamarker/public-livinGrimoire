package Auxiliary_Modules;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ElizaDeducer {
    /*
    * this class populates a special chat dictionary
    * based on the matches added via its addPhraseMatcher function
    * see subclass ElizaDeducerInitializer for example:
    * ElizaDeducer ed = new ElizaDeducerInitializer(2); // 2 = limit of replies per input
    * */
    public List<PhraseMatcher> babble2;
    private final java.util.Map<String, List<PhraseMatcher>> patternIndex;
    private final java.util.Map<String, List<AXKeyValuePair>> responseCache;
    private final EventChatV2 ec2; // chat dictionary, use getter for access. hardcoded replies can also be added

    public ElizaDeducer(int lim) {
        babble2 = new ArrayList<>();
        patternIndex = new java.util.HashMap<>();
        responseCache = new java.util.HashMap<>();
        ec2 = new EventChatV2(lim);
    }

    public EventChatV2 getEc2() {
        return ec2;
    }

    public void learn(String msg) {
        // populate EventChat dictionary
        // Check cache first
        if (responseCache.containsKey(msg)) {
            ec2.addKeyValues(new ArrayList<>(responseCache.get(msg)));
        }

        // Search for matching patterns
        List<PhraseMatcher> potentialMatchers = getPotentialMatchers(msg);
        for (PhraseMatcher pm : potentialMatchers) {
            if (pm.matches(msg)) {
                ArrayList<AXKeyValuePair> response = pm.respond(msg);
                responseCache.put(msg, response);
                ec2.addKeyValues(response);
            }
        }
    }
    public boolean learnedBool(String msg) {
        // same as learn method but returns true if it learned new replies
        boolean learned = false;
        // populate EventChat dictionary
        // Check cache first
        if (responseCache.containsKey(msg)) {
            ec2.addKeyValues(new ArrayList<>(responseCache.get(msg)));
            learned = true;
        }

        // Search for matching patterns
        List<PhraseMatcher> potentialMatchers = getPotentialMatchers(msg);
        for (PhraseMatcher pm : potentialMatchers) {
            if (pm.matches(msg)) {
                ArrayList<AXKeyValuePair> response = pm.respond(msg);
                responseCache.put(msg, response);
                ec2.addKeyValues(response);
                learned = true;
            }
        }
        return learned;
    }
    public String respond(String str1){
        return ec2.response(str1);
    }
    public String respondLatest(String str1){
        // get most recent reply/data
        return ec2.responseLatest(str1);
    }

    private List<PhraseMatcher> getPotentialMatchers(String msg) {
        List<PhraseMatcher> potentialMatchers = new ArrayList<>();
        for (String key : patternIndex.keySet()) {
            if (msg.contains(key)) {
                potentialMatchers.addAll(patternIndex.get(key));
            }
        }
        return potentialMatchers;
    }

    public void addPhraseMatcher(String pattern, String... kvPairs) {
        ArrayList<AXKeyValuePair> kvs = new ArrayList<>();
        for (int i = 0; i < kvPairs.length; i += 2) {
            kvs.add(new AXKeyValuePair(kvPairs[i], kvPairs[i + 1]));
        }
        PhraseMatcher matcher = new PhraseMatcher(pattern, kvs);
        babble2.add(matcher);
        indexPattern(pattern, matcher);
    }

    private void indexPattern(String pattern, PhraseMatcher matcher) {
        for (String word : pattern.split("\\s+")) {
            patternIndex.computeIfAbsent(word, k -> new ArrayList<>()).add(matcher);
        }
    }

    public static class PhraseMatcher {
        public final Pattern matcher;
        public final List<AXKeyValuePair> responses;

        public PhraseMatcher(String matcher, List<AXKeyValuePair> responses) {
            this.matcher = Pattern.compile(matcher);
            this.responses = responses;
        }

        public boolean matches(String str) {
            Matcher m = matcher.matcher(str);
            return m.matches();
        }

        public ArrayList<AXKeyValuePair> respond(String str) {
            Matcher m = matcher.matcher(str);
            ArrayList<AXKeyValuePair> result = new ArrayList<>();
            if (m.find()) {
                int tmp = m.groupCount();
                for (AXKeyValuePair kv : this.responses) {
                    AXKeyValuePair tempKV = new AXKeyValuePair(kv.getKey(), kv.getValue());
                    for (int i = 0; i < tmp; i++) {
                        String s = m.group(i + 1);
                        tempKV.setKey(tempKV.getKey().replace("{" + i + "}", s).toLowerCase());
                        tempKV.setValue(tempKV.getValue().replace("{" + i + "}", s).toLowerCase());
                    }
                    result.add(tempKV);
                }
            }
            return result;
        }
    }
}
