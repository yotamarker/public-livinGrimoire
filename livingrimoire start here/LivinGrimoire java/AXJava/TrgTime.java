package AXJava;

import LivinGrimoire.PlayGround;
import LivinGrimoire.RegexUtil;
import LivinGrimoire.enumRegexGrimoire;

public class TrgTime {
    String t = "null";
    RegexUtil regexUtil = new RegexUtil();
    PlayGround pl = new PlayGround();
    private Boolean alarm = true;
    public void setTime(String v1){
        t = regexUtil.extractRegex(enumRegexGrimoire.simpleTimeStamp,v1);
    }
    public Boolean alarm(){
        String now = pl.getCurrentTimeStamp();
        if(alarm){
            if(now.equals(t)){
                alarm = false;
                return true;
            }
        }
        if(!now.equals(t)){
            alarm = true;
        }
        return false;
    }
}
