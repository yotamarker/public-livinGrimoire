package Auxiliary_Modules;

import java.util.Hashtable;

public class PersistentQuestion {
    private Boolean isActive = false;
    private String mode = "yes"; // key mode
    private Hashtable<String, DrawRnd>dic = new Hashtable<>();
    private OutputDripper outputDripper = new OutputDripper(1);
    private String loggedAnswer = ""; // only used in log() which replaces process()

    public String getLoggedAnswer() {
        return loggedAnswer;
    }

    public void setLoggedAnswer(String loggedAnswer) {
        this.loggedAnswer = loggedAnswer;
    }

    public void addPath(String answer, DrawRnd nags){
        dic.put(answer,nags);
    }
    public void activate(){isActive = true;}
    public void deActivate(){
        isActive = false;
        dic.get(mode).reset();
    }
    public Boolean isActive(){
        return isActive;
    }
    public String process(String inp){
        // got answer?
        if (dic.containsKey(inp)){
            mode = inp;
            isActive = false;
            dic.get(mode).reset();
            return "okay"; // can extend code to reply key, rnd finalizer
        }
        // nag for answer
        if (!outputDripper.drip()) {return "";}
        String result = dic.get(mode).draw();
        if (!result.isEmpty()){return result;}
        else {dic.get(mode).reset();isActive = false; return "i see";}
    }
    public String log(String inp){
        // got answer?
        if (dic.containsKey(inp)){
            mode = inp;
            loggedAnswer = inp;
            isActive = false;
            dic.get(mode).reset();
            return "okay"; // can extend code to reply key, rnd finalizer
        }
        if (!inp.isEmpty()){
            loggedAnswer = inp;
            isActive = false;
            dic.get(mode).reset();
            return "okay"; // can extend code to reply key, rnd finalizer
        }
        // nag for answer
        if (!outputDripper.drip()) {return "";}
        String result = dic.get(mode).draw();
        if (!result.isEmpty()){return result;}
        else {dic.get(mode).reset();isActive = false; return "i see";}
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        if (dic.containsKey(mode)){
        this.mode = mode;}
    }
    public void setPause(int pause){
        // set pause between question to wait for answer
        this.outputDripper.setLimit(pause);
    }
}
