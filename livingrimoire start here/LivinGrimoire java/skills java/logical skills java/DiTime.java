package skills;

import LivinGrimoire.DiSkillV2;
import LivinGrimoire.PlayGround;

public class DiTime extends DiSkillV2 {
    private PlayGround pl = new PlayGround();

    @Override
    public void input(String ear, String skin, String eye) {
        switch(ear) {
            case "what is the time":
                setVerbatimAlg(4, pl.getCurrentTimeStamp());
                return;
            case "which day is it":
                setVerbatimAlg(4, pl.getDayOfDWeek());
                return;
            case "good morning": case "good night": case "good afternoon": case "good evening":
                setVerbatimAlg(4, "good " + pl.partOfDay());
                return;
            case "which month is it":
                setVerbatimAlg(4, pl.translateMonth(pl.getMonthAsInt()));
                return;
            case "which year is it":
                setVerbatimAlg(4, pl.getYearAsInt()+"");
                return;
            case "what is your time zone":
                setVerbatimAlg(4, pl.getLocal());
                return;
            default:
                // code block
        }
    }
}
