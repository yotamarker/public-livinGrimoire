package Auxiliary_Modules;

public class TrgTime {
    String t = "null";
    TimeUtils pl = new TimeUtils();
    private Boolean alarm = true;
    public void setTime(String v1){
        t = RegexUtil.extractRegex(enumRegexGrimoire.simpleTimeStamp,v1);
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
