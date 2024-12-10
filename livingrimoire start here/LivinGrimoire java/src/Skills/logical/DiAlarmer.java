package Skills.logical;

import Auxiliary_Modules.Cron;
import Auxiliary_Modules.RegexUtil;
import Auxiliary_Modules.Responder;
import LivinGrimoire.Skill;

public class DiAlarmer extends Skill {
    private Responder off = new Responder("alarm off","cancel alarm");
    private Cron cron = new Cron("", 3,3);

    public void setCron(Cron cron) {
        this.cron = cron;
    }

    @Override
    public void input(String ear, String skin, String eye) {
        // turn off alarm
        if (off.responsesContainsStr(ear)){
            cron.turnOff();
            setSimpleAlg("alarm is now off");
            return;
        }
        String temp = RegexUtil.extractRegex("(?<=set alarm to\\s)([0-9]{1,2}:[0-9]{1,2})", ear);
        if (!temp.isEmpty()){
            cron.setStartTime(temp);
            setSimpleAlg("alarm set to "+ temp);
            return;
        }
        if(cron.triggerWithoutRenewal()){
            setSimpleAlg("beep");
        }
    }
}
