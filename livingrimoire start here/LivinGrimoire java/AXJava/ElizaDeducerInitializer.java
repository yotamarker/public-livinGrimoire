package AXJava;

import java.util.ArrayList;
import java.util.Collections;

public class ElizaDeducerInitializer extends ElizaDeducer{
    public ElizaDeducerInitializer() {
        ref.put("am", "are");
        ref.put("was", "were");
        ref.put("i", "you");
        ref.put("i'd", "you would");
        ref.put("i've", "you have");
        ref.put("my", "your");
        ref.put("are", "am");
        ref.put("you've", "Ihave");
        ref.put("you'll", "I will");
        ref.put("your", "my");
        ref.put("yours", "mine");
        ref.put("you", "i");
        ref.put("me", "you");
        reflections = Collections.unmodifiableMap(ref);
        ArrayList<PhraseMatcher> babbleTmp = new ArrayList<PhraseMatcher>();
        ArrayList<AXKeyValuePair> kvs = new ArrayList<>();
        kvs.add(new AXKeyValuePair("what is a {0}","{0} is a {1}"));
        kvs.add(new AXKeyValuePair("explain {0}","{0} is a {1}"));
        babbleTmp.add(new PhraseMatcher("(.*) is (.*)", kvs));
        babble2 = Collections.unmodifiableList(babbleTmp);
    }
}
