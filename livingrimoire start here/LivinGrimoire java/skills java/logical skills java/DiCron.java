package skills;

import AXJava.Cron;
import LivinGrimoire.DiSkillV2;

public class DiCron extends DiSkillV2 {
    private String sound = "snore";
    private Cron cron = new Cron("12:05",40,2);//Cron(String startTime, int minutes, int limit)
    // setters
    public DiCron setSound(String sound) {
        this.sound = sound;
        return this;
    }

    public DiCron setCron(Cron cron) {
        this.cron = cron;
        return this;
    }

    @Override
    public void input(String ear, String skin, String eye) {
        if (cron.trigger()){
            setVerbatimAlg(4,sound);
        }
    }
}
