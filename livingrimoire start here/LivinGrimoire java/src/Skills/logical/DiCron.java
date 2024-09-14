package Skills.logical;

import Auxiliary_Modules.Cron;
import LivinGrimoire.Skill;

public class DiCron extends Skill {
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
