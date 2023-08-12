package AXJava;

import java.util.ArrayList;
import java.util.Random;

public class Responder {
    // simple random response dispenser
    private ArrayList<String> responses = new ArrayList<String>();
    private Random rand = new Random();
    public Responder(String... replies) {
        for (int i = 0; i < replies.length; i++) {
            responses.add(replies[i]);
        }
    }
    public String getAResponse(){
        if (responses.isEmpty()){
            return "";
        }
        return responses.get(rand.nextInt(responses.size()));
    }
    public Boolean responsesContainsStr(String str){
        return responses.contains(str);
    }
    public Boolean strContainsResponse(String str){
        Boolean result = false;
        for (String tempStr: responses) {
            if (str.contains(tempStr)){
                result = true;
                break;
            }
        }
        return result;
    }
    public void addResponse(String s1){responses.add(s1);}
}
