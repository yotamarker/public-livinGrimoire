package Skills.logical;

import Auxiliary_Modules.Responder;
import Auxiliary_Modules.TrgTime;
import LivinGrimoire.APVerbatim;
import LivinGrimoire.Skill;

public class DiSleep extends Skill {
    private final int sleep_duration_minutes;
    private final Responder wakeners;
    private final TrgTime trgTime = new TrgTime();
    // trgtime
    public DiSleep(int sleep_duration_minutes, Responder wakeners) {
        this.sleep_duration_minutes = sleep_duration_minutes;
        this.wakeners = wakeners;
        trgTime.setTime("00:00");
    }

    public DiSleep setTrgTime(String sleep_time_stamp) {
        this.trgTime.setTime(sleep_time_stamp);
        return this;
    }

    @Override
    public void input(String ear, String skin, String eye) {
        if(trgTime.alarm()){
            APVerbatim announce = new APVerbatim("initializing sleep");
            APSleep apSleep = new APSleep(wakeners, sleep_duration_minutes);
            this.algPartsFusion(2,announce,apSleep);
        }
    }
}
