package AXJava;

import LivinGrimoire.PlayGround;
import LivinGrimoire.RegexUtil;
import LivinGrimoire.enumRegexGrimoire;
import com.sun.org.apache.xpath.internal.operations.Bool;

public class TrgTime {
    String t = "null";
    RegexUtil regexUtil = new RegexUtil();
    PlayGround pl = new PlayGround();
    private Boolean alarm = true;
    public void setTime(String v1){
        t = regexUtil.extractRegex(enumRegexGrimoire.simpleTimeStamp,v1);
    }
    public Boolean alarm(){
        if(alarm){
            String now = pl.getCurrentTimeStamp();
            if(now.equals(t)){
                alarm = false;
                return true;
            }
        }
        alarm = true;
        return false;
    }
}
