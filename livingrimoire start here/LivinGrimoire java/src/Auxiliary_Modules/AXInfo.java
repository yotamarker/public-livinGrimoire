package Auxiliary_Modules;

import java.util.Hashtable;

public class AXInfo {
    private Hashtable<String,String> kv = new Hashtable<>(); // key value
    private Hashtable<String,String> qv = new Hashtable<>(); // question as key to value
    public void insert(String key, String value){
        // override me
        kv.put(key, value);
        ChatBot cBot = new ChatBot(5);
        cBot.addSentence("how much does item # cost");
        cBot.addParam("item",key);
        qv.put(cBot.talk(), value);
    }
    public String getAnswer(String question){
        // override me
        ChatBot cBot = new ChatBot(5);
        cBot.addSentence("that would cost you price #");
        cBot.addSentence("the price is price #");
        cBot.addSentence("only price # $");
        cBot.addParam("price",qv.getOrDefault(question, "hmm"));
        return cBot.talk();
    }
    public String gevalue(String key){
        return kv.getOrDefault(key, "i do not know");
    }
}
