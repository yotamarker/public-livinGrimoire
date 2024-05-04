package AXJava;

import LivinGrimoire.PlayGround;
import LivinGrimoire.RegexUtil;
import LivinGrimoire.enumRegexGrimoire;

import java.util.HashMap;
import java.util.Map;

public class TimedMessages {
    public Map<String, String> messages = new HashMap<>();
    private final PlayGround playGround = new PlayGround();
    private String lastMSG = "nothing";
    private Boolean msg = false;
    public void addMSG(String ear){
        RegexUtil ru1 = new RegexUtil();
        String tempMSG = ru1.extractRegex("(?<=remind me to).*?(?=at)",ear);
        if(tempMSG.isEmpty()){return;}
        String timeStamp = ru1.extractRegex(enumRegexGrimoire.simpleTimeStamp,ear);
        if(timeStamp.isEmpty()){return;}
        messages.put(timeStamp, tempMSG);
    }
    public void clear(){messages = new HashMap<>();}
    public void tick(){
        String now = this.playGround.getCurrentTimeStamp();
        if(this.messages.containsKey(now)){
            if(!lastMSG.equals(messages.get(now))){
                lastMSG = messages.get(now);
                msg = true;
            }
        }
    }

    public String getLastMSG() {
        msg = false;
        return lastMSG;
    }

    public Boolean getMsg() {
        return msg;
    }
}
