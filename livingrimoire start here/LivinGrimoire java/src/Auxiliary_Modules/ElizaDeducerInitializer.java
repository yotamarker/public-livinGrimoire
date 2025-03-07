package Auxiliary_Modules;

public class ElizaDeducerInitializer extends ElizaDeducer {

    public ElizaDeducerInitializer(int lim) {
        // recommended lim = 5; it's the limit of responses per key in the eventchat dictionary
        // the purpose of the lim is to make saving and loading data easier
        super(lim);
        initializeBabble2();
    }

    private void initializeBabble2() {
        addPhraseMatcher(
                "(.*) is (.*)",
                "what is {0}", "{0} is {1}",
                "explain {0}", "{0} is {1}"
        );

        addPhraseMatcher(
                "if (.*) or (.*) than (.*)",
                "{0}", "{2}",
                "{1}", "{2}"
        );

        addPhraseMatcher(
                "if (.*) and (.*) than (.*)",
                "{0}", "{1}"
        );

        addPhraseMatcher(
                "(.*) because (.*)",
                "{1}", "i guess {0}"
        );
    }
}