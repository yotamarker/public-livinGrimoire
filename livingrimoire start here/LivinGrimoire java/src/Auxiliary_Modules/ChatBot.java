package Auxiliary_Modules;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

public class ChatBot {
    /*
    * chatbot example use in main :
    *         // init
        ChatBot chatbot = new ChatBot(5);
        // set param categories (init stage):
        chatbot.addParam("name","jinpachi");
        chatbot.addParam("name","sakura");
        chatbot.addParam("verb","eat");
        chatbot.addParam("verb","code");
        // learn sentence at init stage(optiona):
        chatbot.addSentence("i can verb #");
        // learning params post init stage (code is running)
        chatbot.learnParam("ryu is a name");
        chatbot.learnParam("ken is a name");
        chatbot.learnParam("drink is a verb");
        chatbot.learnParam("rest is a verb");
        // learn sentences from input during code run stage
        chatbot.learnV2("hello ryu i like to code");
        chatbot.learnV2("greetings ken");
        for (int i = 1; i < 10; i++) {
            // gen speech
            System.out.println(chatbot.talk());
            // share params for other chatbot to learn
            System.out.println(chatbot.getALoggedParam());
            }
    * */
    protected RefreshQ sentences = new RefreshQ();
    protected Hashtable<String, RefreshQ> wordToList = new Hashtable<>(); // params
    protected Hashtable<String, String> allParamRef = new Hashtable<>();
    protected int paramLim = 5;
    protected RefreshQ loggedParams = new RefreshQ();
    private String conjuration = "is a";

    public ChatBot(int logParamLim) {
        loggedParams.setLimit(logParamLim);
    }

    public void setConjuration(String conjuration) {
        this.conjuration = conjuration;
    }

    public void setSentencesLim(int lim){sentences.setLimit(lim);}

    public void setParamLim(int paramLim) {
        this.paramLim = paramLim;
    }

    public Hashtable<String, RefreshQ> getWordToList() {
        return wordToList;
    }

    public String talk() {
        String result = sentences.getRNDElement();
        return clearRecursion(result);
    }

    private String clearRecursion(String result) {
        ArrayList<String> params = new ArrayList<String>();
        params = RegexUtil.extractAllRegexes("(\\w+)(?= #)", result);
        for (String strI : params) {
            UniqueItemSizeLimitedPriorityQueue temp = wordToList.get(strI);
            String s1 = temp.getRNDElement();
            result = result.replace(strI + " #", s1);
        }
        if (!result.contains("#")) {
            return result;
        } else {
            return clearRecursion(result);
        }
    }
    // learn on init:
    public void addParam(String category, String value){
        if(!(wordToList.containsKey(category))){
            RefreshQ temp = new RefreshQ();
            temp.setLimit(paramLim);
            wordToList.put(category, temp);
        }
        wordToList.get(category).add(value);
        allParamRef.put(value,category); // for learnV2
    }
    // same as the addParam but only the latest parameter is saved
    // used for topics, names, cases where 1 latest parameter is needed
    public void addSubject(String category, String value){
        if(!(wordToList.containsKey(category))){
            RefreshQ temp = new RefreshQ();
            temp.setLimit(1);
            wordToList.put(category, temp);
        }
        wordToList.get(category).add(value);
        allParamRef.put(value,category); // for learnV2
    }
    public void addParam(AXKeyValuePair kv){
        if(!(wordToList.containsKey(kv.getKey()))){
            RefreshQ temp = new RefreshQ();
            temp.setLimit(paramLim);
            wordToList.put(kv.getKey(), temp);
        }
        wordToList.get(kv.getKey()).add(kv.getValue());
        allParamRef.put(kv.getValue(),kv.getKey()); // for learnV2
    }
    // chatbot.addSentence("hello name #");
    public void addSentence(String sentence){
        this.sentences.add(sentence);
    }
    // chatbot.learn("hello name i like to verb");
    public void learn(String s1){
        s1 = " "+s1;
        for (String key : wordToList.keySet()) {
            s1 = s1.replace(" "+ key, String.format(" %s #", key));
        }
        sentences.add(s1.trim());
    }
    // learn while code is running
    public Boolean learnV2(String s1){
        // returns true if sentence has params
        // meaning sentence has been learnt
        String OGStr = s1;
        s1 = " "+s1;
        for (String key : allParamRef.keySet()) {
            s1 = s1.replace(" "+ key, String.format(" %s #", allParamRef.get(key)));
        }
        s1 = s1.trim();
        if (!(OGStr.equals(s1))){
        sentences.add(s1.trim());return true;}
        return false;
    }
    public void learnParam(String s1){
        if (!s1.contains(conjuration)){return;}
        String category = RegexUtil.extractRegex("(?<=" + conjuration + ")(.*)",s1);
        if(!(wordToList.containsKey(category))){return;}
        String param = s1.replace(conjuration +" "+category,"").trim();
        wordToList.get(category).add(param);
        allParamRef.put(param,category);
        loggedParams.add(s1);
    }
    // add key value pair collected by an AXPrompt object
    public void addParamFromAXPrompt(AXKeyValuePair kv){
        if(!(wordToList.containsKey(kv.getKey()))){
            return;
        }
        wordToList.get(kv.getKey()).add(kv.getValue());
        allParamRef.put(kv.getValue(),kv.getKey()); // for learnV2
    }
    // load entire RefreshQ of parameters
    // example : list of nicknames per name
    // this special use case requires a specialized Object to retain
    // a set topic(name) and {category, param1#param2#...}(converted into a que)
    public void addRefreshQ(String category, RefreshQ q1){
        if(!(wordToList.containsKey(category))){
            return;
        }
        wordToList.put(category, q1);
    }
    public String getALoggedParam(){return  loggedParams.getRNDElement();}
}