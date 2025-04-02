package Auxiliary_Modules;

import LivinGrimoire.Kokoro;

public class RailBot {
    private final EventChatV2 ec;
    private String context = "stand by";
    private ElizaDBWrapper elizaWrapper = null; // Starts null (no DB)

    public RailBot(int limit) {
        ec = new EventChatV2(limit);
    }

    public RailBot() {
        this(5);
    }
    /**
     * Enables database features. Must be called before any save/load operations.
     * If never called, RailBot works in memory-only mode.
     */
    public void enableDBWrapper() {
        if (elizaWrapper == null) {
            elizaWrapper = new ElizaDBWrapper();
        }
    }
    public void disableDBWrapper() {
        elizaWrapper = null;
    }

    public void setContext(String context) {
        if (context.isEmpty()) {
            return;
        }
        this.context = context;
    }

    private String respondMonolog(String ear) {
        if (ear.isEmpty()) {
            return "";
        }
        String temp = ec.response(ear);
        if (!temp.isEmpty()) {
            this.context = temp;
        }
        return temp;
    }

    public void learn(String ear) {
        if (ear.isEmpty() || ear.equals(this.context)) {
            return;
        }
        ec.addKeyValue(this.context, ear);
        this.context = ear;
    }

    public String monolog() {
        return respondMonolog(this.context);
    }

    public String respondDialog(String ear) {
        return ec.response(ear);
    }
    public String respondLatest(String ear) {
        return ec.responseLatest(ear);
    }

    public void learnKeyValue(String context, String reply) {
        ec.addKeyValue(context,reply);
    }

    public void feedKeyValuePairs(java.util.List<AXKeyValuePair> kvList) {
        if (kvList.isEmpty()) {
            return;
        }
        for (AXKeyValuePair kv : kvList) {
            learnKeyValue(kv.getKey(), kv.getValue());
        }
    }
    // save/load functionalities
    public void saveLearnedData(Kokoro kokoro){
        if (elizaWrapper == null) {return;}
        elizaWrapper.sleepNSave(ec, kokoro);
    }
    private String loadableMonologMechanics(String ear, Kokoro kokoro) {
        // loads data if available
        if (ear.isEmpty()) {
            return "";
        }
        String temp = elizaWrapper.respond(ear,ec,kokoro);
        if (!temp.isEmpty()) {
            this.context = temp;
        }
        return temp;
    }
    public String loadableMonolog(Kokoro kokoro) {
        if(elizaWrapper == null){return monolog();}
        return loadableMonologMechanics(this.context,kokoro);
    }
    public String loadableDialog(String ear, Kokoro kokoro) {
        if(elizaWrapper == null){return respondDialog(ear);}
        return elizaWrapper.respond(ear,ec,kokoro);
    }

}
