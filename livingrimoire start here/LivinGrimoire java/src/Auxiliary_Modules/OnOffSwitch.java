package Auxiliary_Modules;

public class OnOffSwitch {
    private Boolean mode = false;
    private final TimeGate timeGate = new TimeGate(5);
    private Responder on = new Responder("on","talk to me");
    private Responder off = new Responder("off","stop","shut up", "shut it","whatever","whateva");

    public void setPause(int minutes) {
        this.timeGate.setPause(minutes);
    }

    public void setOn(Responder on) {
        this.on = on;
    }

    public void setOff(Responder off) {
        this.off = off;
    }

    public Boolean getMode(String ear){
        if (on.responsesContainsStr(ear)){
            timeGate.openGate();
            mode = true;
            return true;
        } else if (off.responsesContainsStr(ear)) {
            timeGate.close();
            mode = false;
        }
        if (timeGate.isClosed()){mode = false;}
        return mode;
    }
}
