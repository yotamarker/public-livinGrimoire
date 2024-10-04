package Auxiliary_Modules;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Responder {
    // simple random response dispenser
    private final ArrayList<String> responses = new ArrayList<>();
    private final Random rand = new Random();
    public Responder(String... replies) {
        Collections.addAll(responses, replies);
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
        boolean result = false;
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
