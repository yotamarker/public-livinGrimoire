package skills;

import AXJava.Cron;
import AXJava.Responder;
import LivinGrimoire.DiSkillV2;
import LivinGrimoire.RegexUtil;

public class DiAlarmer extends DiSkillV2 {
    private Responder off = new Responder("off","stop","shut up", "shut it","alarm off","cancel alarm");
    private RegexUtil regexUtil = new RegexUtil();
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
        String temp = regexUtil.extractRegex("(?<=set alarm to\\s)([0-9]{1,2}:[0-9]{1,2})", ear);
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
