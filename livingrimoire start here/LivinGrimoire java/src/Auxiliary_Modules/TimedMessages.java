package Auxiliary_Modules;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TimedMessages {
    public Map<String, String> messages = new HashMap<>();
    private final TimeUtils playGround = new TimeUtils();
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
    public void addMSGV2(String timeStamp, String msg){
        messages.put(timeStamp, msg);
    }
    public void sprinkleMSG(String msg, int amount){
        for (int i = 0; i < amount; i++) {
            messages.put(generateRandomTimestamp(), msg);
        }
    }
    public static String generateRandomTimestamp() {
        // Generate a random number of minutes (0 to 59)
        Random random = new Random();
        int minutes = random.nextInt(60);
        String m = "";
        if(minutes>9){
            m = String.format("%d", minutes);
        }else{
            m = String.format("0%d", minutes);
        }
        int hours = random.nextInt(12);
        if(hours>9){
            return String.format("%d:%s", hours, minutes);
        }
        return String.format("0%d:%s", hours, minutes);
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
