package Skills.logical;

import Auxiliary_Modules.TimeUtils;
import LivinGrimoire.Skill;

import java.util.Objects;

public class DiTime extends Skill {

    @Override
    public void input(String ear, String skin, String eye) {
        switch(ear) {
            case "what is the date":
                String message = String.format("it is the %s, %s %s", TimeUtils.getCurrentMonthDay(), TimeUtils.getCurrentMonthName(), TimeUtils.getYearAsInt());
                setVerbatimAlg(4, message);
                return;
            case "what is the time":
                setVerbatimAlg(4, TimeUtils.getCurrentTimeStamp());
                return;
            case "which day is it":
                setVerbatimAlg(4, TimeUtils.getDayOfDWeek());
                return;
            case "good morning": case "good night": case "good afternoon": case "good evening":
                setVerbatimAlg(4, "good " + TimeUtils.partOfDay());
                return;
            case "which month is it":
                setSimpleAlg(TimeUtils.translateMonth(TimeUtils.getMonthAsInt()));
                return;
            case "which year is it":
                setVerbatimAlg(4, TimeUtils.getYearAsInt()+"");
                return;
            case "what is your time zone":
                setVerbatimAlg(4, TimeUtils.getLocal());
                return;
            case "when is the first":
                setVerbatimAlg(4, TimeUtils.nxtDayOnDate(1));
                return;
            case "when is the second":
                setVerbatimAlg(4, TimeUtils.nxtDayOnDate(2));
                return;
            case "when is the third":
                setVerbatimAlg(4, TimeUtils.nxtDayOnDate(3));
                return;
            case "when is the fourth":
                setVerbatimAlg(4, TimeUtils.nxtDayOnDate(4));
                return;
            case "when is the fifth":
                setVerbatimAlg(4, TimeUtils.nxtDayOnDate(5));
                return;
            case "when is the sixth":
                setVerbatimAlg(4, TimeUtils.nxtDayOnDate(6));
                return;
            case "when is the seventh":
                setVerbatimAlg(4, TimeUtils.nxtDayOnDate(7));
                return;
            case "when is the eighth":
                setVerbatimAlg(4, TimeUtils.nxtDayOnDate(8));
                return;
            case "when is the ninth":
                setVerbatimAlg(4, TimeUtils.nxtDayOnDate(9));
                return;
            case "when is the tenth":
                setVerbatimAlg(4, TimeUtils.nxtDayOnDate(10));
                return;
            case "when is the eleventh":
                setVerbatimAlg(4, TimeUtils.nxtDayOnDate(11));
                return;
            case "when is the twelfth":
                setVerbatimAlg(4, TimeUtils.nxtDayOnDate(12));
                return;
            case "when is the thirteenth":
                setVerbatimAlg(4, TimeUtils.nxtDayOnDate(13));
                return;
            case "when is the fourteenth":
                setVerbatimAlg(4, TimeUtils.nxtDayOnDate(14));
                return;
            case "when is the fifteenth":
                setVerbatimAlg(4, TimeUtils.nxtDayOnDate(15));
                return;
            case "when is the sixteenth":
                setVerbatimAlg(4, TimeUtils.nxtDayOnDate(16));
                return;
            case "when is the seventeenth":
                setVerbatimAlg(4, TimeUtils.nxtDayOnDate(17));
                return;
            case "when is the eighteenth":
                setVerbatimAlg(4, TimeUtils.nxtDayOnDate(18));
                return;
            case "when is the nineteenth":
                setVerbatimAlg(4, TimeUtils.nxtDayOnDate(19));
                return;
            case "when is the twentieth":
                setVerbatimAlg(4, TimeUtils.nxtDayOnDate(21));
                return;
            case "when is the twenty first":
                setVerbatimAlg(4, TimeUtils.nxtDayOnDate(21));
                return;
            case "when is the twenty second":
                setVerbatimAlg(4, TimeUtils.nxtDayOnDate(22));
                return;
            case "when is the twenty third":
                setVerbatimAlg(4, TimeUtils.nxtDayOnDate(23));
                return;
            case "when is the twenty fourth":
                setVerbatimAlg(4, TimeUtils.nxtDayOnDate(24));
                return;
            case "when is the twenty fifth":
                setVerbatimAlg(4, TimeUtils.nxtDayOnDate(25));
                return;
            case "when is the twenty sixth":
                setVerbatimAlg(4, TimeUtils.nxtDayOnDate(26));
                return;
            case "when is the twenty seventh":
                setVerbatimAlg(4, TimeUtils.nxtDayOnDate(27));
                return;
            case "when is the twenty eighth":
                setVerbatimAlg(4, TimeUtils.nxtDayOnDate(28));
                return;
            case "when is the twenty ninth":
                setVerbatimAlg(4, Objects.equals(TimeUtils.nxtDayOnDate(29), "") ? "never":TimeUtils.nxtDayOnDate(29));
                return;
            case "when is the thirtieth":
                setVerbatimAlg(4, Objects.equals(TimeUtils.nxtDayOnDate(30), "") ? "never":TimeUtils.nxtDayOnDate(30));
                return;
            case "when is the thirty first":
                setVerbatimAlg(4, Objects.equals(TimeUtils.nxtDayOnDate(31), "") ? "never":TimeUtils.nxtDayOnDate(31));
                return;
            case "incantation 0":
                setSimpleAlg("fly","bless of magic caster","infinity wall", "magic ward holy","life essence");
                return;
            default:
                // code block
        }
    }
    @Override
    public String skillNotes(String param) {
        if (param.equals("notes")) {
            return "gets time date or misc";
        } else if (param.equals("triggers")) {
            String[] triggers = {"what is the time", "which day is it", "what is the date", "evil laugh", "good part of day", "when is the fifth"};
            int randomIndex = (int) (Math.random() * triggers.length);
            return triggers[randomIndex];
        }
        return "time util skill";
    }

}
