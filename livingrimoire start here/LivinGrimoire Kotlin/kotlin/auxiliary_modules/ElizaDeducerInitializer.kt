package auxiliary_modules

import java.util.*

class ElizaDeducerInitializer : ElizaDeducer() {
    init {
        val babbleTmp = ArrayList<PhraseMatcher>()
        val kvs = ArrayList<AXKeyValuePair>()
        kvs.add(AXKeyValuePair("what is {0}", "{0} is {1}"))
        kvs.add(AXKeyValuePair("explain {0}", "{0} is {1}"))
        babbleTmp.add(PhraseMatcher("(.*) is (.*)", kvs))
        val or1 = ArrayList<AXKeyValuePair>() // simple or
        or1.add(AXKeyValuePair("{0}", "{2}"))
        or1.add(AXKeyValuePair("{1}", "{2}"))
        babbleTmp.add(PhraseMatcher("if (.*) or (.*) than (.*)", or1)) // end or1
        val if1 = ArrayList<AXKeyValuePair>() // simple if
        if1.add(AXKeyValuePair("{0}", "{1}"))
        babbleTmp.add(PhraseMatcher("if (.*) and (.*) than (.*)", if1)) // end if1
        val because1 = ArrayList<AXKeyValuePair>() // simple because
        because1.add(AXKeyValuePair("{1}", "i guess {0}"))
        babbleTmp.add(PhraseMatcher("(.*) because (.*)", because1)) // end because
        babble2 = Collections.unmodifiableList(babbleTmp)
    }
}