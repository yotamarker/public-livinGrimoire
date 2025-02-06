package Auxiliary_Modules;

import java.util.ArrayList;
import java.util.Hashtable;

public class RailChatBot {
    private final Hashtable<String,RefreshQ> dic = new Hashtable<>();
    private String context = "default";

    public RailChatBot() {
        this.dic.put(context,new RefreshQ());
    }

    public void setContext(String context) {
        if (context.isEmpty()) {return;}
        this.context = context;
    }
    public String respondMonolog(String ear){
        // monolog mode
        // recommended use of filter for the output results
        if (ear.isEmpty()){return "";}
        if (!dic.containsKey(ear)){
            dic.put(ear,new RefreshQ());
        }
        String temp = dic.get(ear).getRNDElement();
        if (!temp.isEmpty()){context = temp;}
        return temp;
    }
    public void learn(String ear){
        // use per each think cycle
        if (ear.isEmpty()){return;}
        if (!dic.containsKey(ear)){
            dic.put(ear,new RefreshQ());
            dic.get(context).add(ear);
            context = ear;
            return ;
        }
        dic.get(context).add(ear);
        context = ear;
    }
    public void learnKeyValue(String context, String reply){
        // learn questions and answers/ key values
        if(!dic.containsKey(context)){
            dic.put(context,new RefreshQ());
        }
        if(!dic.containsKey(reply)){
            dic.put(reply,new RefreshQ());
        }
        dic.get(context).add(reply);
    }
    public void feedKeyValuePairs(ArrayList<AXKeyValuePair> kvList){
        if (kvList.isEmpty()){
            return;
        }
        for (AXKeyValuePair kv : kvList) {
            learnKeyValue(kv.getKey(),kv.getValue());
        }
    }
    public String monolog(){
        // succession of outputs without input involved
        return respondMonolog(context);
    }
    public String respondDialog(String ear){
        // dialog mode
        // recommended use of filter for the output results
        if (ear.isEmpty()){return "";}
        if (!dic.containsKey(ear)){
            dic.put(ear,new RefreshQ());
        }
//        if (!temp.isEmpty()){context = temp;}
        return dic.get(ear).getRNDElement();
    }
//    public void learnV2(String ear, ElizaDeducer elizaDeducer){
//        feedKeyValuePairs(elizaDeducer.learn(ear));
//        learn(ear);
//    }
}
