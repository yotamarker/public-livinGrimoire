package Skills.logical;

import Auxiliary_Modules.Responder;
import Auxiliary_Modules.TimeGate;
import LivinGrimoire.Mutatable;

public class APSleep extends Mutatable {
    protected TimeGate timeGate = new TimeGate();
    public Responder wakeners;
    public Boolean done = false;

    public APSleep(Responder wakeners, int sleep_minutes) {
        this.wakeners = wakeners;
        timeGate.openGate();
        timeGate.setPause(sleep_minutes);
    }

    @Override
    public String action(String ear, String skin, String eye) {
        if (wakeners.responsesContainsStr(ear) || timeGate.isClosed()){
            done = true;
            return "waking up";
        }
        if (!ear.isEmpty()){return "zzz";}
        return "";
    }

    @Override
    public Boolean completed() {
        return done;
    }
}
