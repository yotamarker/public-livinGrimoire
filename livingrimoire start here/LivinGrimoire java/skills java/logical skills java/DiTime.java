package skills;

import LivinGrimoire.DiSkillV2;
import LivinGrimoire.PlayGround;

public class DiTime extends DiSkillV2 {
    private PlayGround pl = new PlayGround();

    @Override
    public void input(String ear, String skin, String eye) {
        switch(ear) {
            case "what is the date":
                String message = String.format("it is the %s, %s %s", pl.getCurrentMonthDay(), pl.getCurrentMonthName(), pl.getYearAsInt());
                setVerbatimAlg(4, message);
                return;
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
                setSimpleAlg(pl.translateMonth(pl.getMonthAsInt()));
                return;
            case "which year is it":
                setVerbatimAlg(4, pl.getYearAsInt()+"");
                return;
            case "what is your time zone":
                setVerbatimAlg(4, pl.getLocal());
                return;
            case "when is the first":
                setVerbatimAlg(4, pl.nxtDayOnDate(1));
                return;
            case "when is the second":
                setVerbatimAlg(4, pl.nxtDayOnDate(2));
                return;
            case "when is the third":
                setVerbatimAlg(4, pl.nxtDayOnDate(3));
                return;
            case "when is the fourth":
                setVerbatimAlg(4, pl.nxtDayOnDate(4));
                return;
            case "when is the fifth":
                setVerbatimAlg(4, pl.nxtDayOnDate(5));
                return;
            case "when is the sixth":
                setVerbatimAlg(4, pl.nxtDayOnDate(6));
                return;
            case "when is the seventh":
                setVerbatimAlg(4, pl.nxtDayOnDate(7));
                return;
            case "when is the eighth":
                setVerbatimAlg(4, pl.nxtDayOnDate(8));
                return;
            case "when is the ninth":
                setVerbatimAlg(4, pl.nxtDayOnDate(9));
                return;
            case "when is the tenth":
                setVerbatimAlg(4, pl.nxtDayOnDate(10));
                return;
            case "when is the eleventh":
                setVerbatimAlg(4, pl.nxtDayOnDate(11));
                return;
            case "when is the twelfth":
                setVerbatimAlg(4, pl.nxtDayOnDate(12));
                return;
            case "when is the thirteenth":
                setVerbatimAlg(4, pl.nxtDayOnDate(13));
                return;
            case "when is the fourteenth":
                setVerbatimAlg(4, pl.nxtDayOnDate(14));
                return;
            case "when is the fifteenth":
                setVerbatimAlg(4, pl.nxtDayOnDate(15));
                return;
            case "when is the sixteenth":
                setVerbatimAlg(4, pl.nxtDayOnDate(16));
                return;
            case "when is the seventeenth":
                setVerbatimAlg(4, pl.nxtDayOnDate(17));
                return;
            case "when is the eighteenth":
                setVerbatimAlg(4, pl.nxtDayOnDate(18));
                return;
            case "when is the nineteenth":
                setVerbatimAlg(4, pl.nxtDayOnDate(19));
                return;
            case "when is the twentieth":
                setVerbatimAlg(4, pl.nxtDayOnDate(21));
                return;
            case "when is the twenty first":
                setVerbatimAlg(4, pl.nxtDayOnDate(21));
                return;
            case "when is the twenty second":
                setVerbatimAlg(4, pl.nxtDayOnDate(22));
                return;
            case "when is the twenty third":
                setVerbatimAlg(4, pl.nxtDayOnDate(23));
                return;
            case "when is the twenty fourth":
                setVerbatimAlg(4, pl.nxtDayOnDate(24));
                return;
            case "when is the twenty fifth":
                setVerbatimAlg(4, pl.nxtDayOnDate(25));
                return;
            case "when is the twenty sixth":
                setVerbatimAlg(4, pl.nxtDayOnDate(26));
                return;
            case "when is the twenty seventh":
                setVerbatimAlg(4, pl.nxtDayOnDate(27));
                return;
            case "when is the twenty eighth":
                setVerbatimAlg(4, pl.nxtDayOnDate(28));
                return;
            case "when is the twenty ninth":
                setVerbatimAlg(4, pl.nxtDayOnDate(29) == "" ? "never":pl.nxtDayOnDate(29));
                return;
            case "when is the thirtieth":
                setVerbatimAlg(4, pl.nxtDayOnDate(30) == "" ? "never":pl.nxtDayOnDate(30));
                return;
            case "when is the thirty first":
                setVerbatimAlg(4, pl.nxtDayOnDate(31) == "" ? "never":pl.nxtDayOnDate(31));
                return;
            case "incantation 0":
                setSimpleAlg("fly","bless of magic caster","infinity wall", "magic ward holy","life essence");
                return;
            default:
                // code block
        }
    }
}
