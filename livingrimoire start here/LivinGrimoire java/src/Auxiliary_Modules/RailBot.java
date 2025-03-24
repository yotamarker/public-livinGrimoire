package Auxiliary_Modules;

public class RailBot {
    private final EventChatV2 ec;
    private String context;

    public RailBot(int limit) {
        ec = new EventChatV2(limit);
    }

    public RailBot() {
        this(5);
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
}
