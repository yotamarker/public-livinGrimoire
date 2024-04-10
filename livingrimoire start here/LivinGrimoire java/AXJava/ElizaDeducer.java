package AXJava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ElizaDeducer {
    public  Map<String, String> reflections;
    public  List<PhraseMatcher> babble2;
    public HashMap<String, String> ref = new HashMap<String, String>();
    public ElizaDeducer() {
        // init values in subclass
        // see ElizaDeducerInitializer for example
        // example input ountput based on ElizaDeducerInitializer values :
        // elizaDeducer.respond("a is a b")
        // [what is a a;a is a b, explain a;a is a b]
    }

    public ArrayList<AXKeyValuePair> respond(String msg) {
        for (PhraseMatcher pm : babble2) {
            if (pm.matches(msg)) {
                return pm.respond(msg);
            }
        }
        return new ArrayList<AXKeyValuePair>();
    }

    public class PhraseMatcher {
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
            if (m.find()){}
            ArrayList<AXKeyValuePair> result = new ArrayList<AXKeyValuePair>();
            int tmp = m.groupCount();
            for (AXKeyValuePair kv : this.responses) {
                AXKeyValuePair tempKV= new AXKeyValuePair(kv.getKey(),kv.getValue());
                for (int i = 0; i < tmp; i++) {
                    String s = reflect(m.group(i + 1),i);
                    tempKV.setKey(tempKV.getKey().replace("{" + i + "}", s).toLowerCase());
                    tempKV.setValue(tempKV.getValue().replace("{" + i + "}", s).toLowerCase());
                }
                result.add(tempKV);
            }
            return result;
        }

        public String reflect(String s, int index) {
            String[] sa = s.split(" ");
            if (reflections.containsKey(sa[index])) {
                sa[index] = reflections.get(sa[index]);
            }
            return sa[index];
        }
    }
}
