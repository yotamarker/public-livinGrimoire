package Auxiliary_Modules;

import java.util.ArrayList;

public class ElizaDeducerInitializer extends ElizaDeducer {

    public ElizaDeducerInitializer() {
        babble2 = new ArrayList<>();
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