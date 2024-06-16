package Auxiliary_Modules;

import java.util.ArrayList;
import java.util.Collections;

public class ElizaDeducerInitializer extends ElizaDeducer{
    public ElizaDeducerInitializer() {
        ArrayList<PhraseMatcher> babbleTmp = new ArrayList<PhraseMatcher>();
        ArrayList<AXKeyValuePair> kvs = new ArrayList<>();
        kvs.add(new AXKeyValuePair("what is {0}","{0} is {1}"));
        kvs.add(new AXKeyValuePair("explain {0}","{0} is {1}"));
        babbleTmp.add(new PhraseMatcher("(.*) is (.*)", kvs));
        ArrayList<AXKeyValuePair> or1 = new ArrayList<>(); // simple or
        or1.add(new AXKeyValuePair("{0}","{2}"));
        or1.add(new AXKeyValuePair("{1}","{2}"));
        babbleTmp.add(new PhraseMatcher("if (.*) or (.*) than (.*)", or1)); // end or1
        ArrayList<AXKeyValuePair> if1 = new ArrayList<>(); // simple if
        if1.add(new AXKeyValuePair("{0}","{1}"));
        babbleTmp.add(new PhraseMatcher("if (.*) and (.*) than (.*)", if1)); // end if1
        ArrayList<AXKeyValuePair> because1 = new ArrayList<>(); // simple because
        because1.add(new AXKeyValuePair("{1}","i guess {0}"));
        babbleTmp.add(new PhraseMatcher("(.*) because (.*)", because1)); // end because
        babble2 = Collections.unmodifiableList(babbleTmp);
    }
}
