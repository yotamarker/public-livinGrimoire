package Auxiliary_Modules;

public class TrgTime {
    String t = "null";
    private Boolean alarm = true;
    public void setTime(String v1){
        t = RegexUtil.extractRegex(enumRegexGrimoire.simpleTimeStamp,v1);
    }
    public Boolean alarm(){
        String now = TimeUtils.getCurrentTimeStamp();
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
