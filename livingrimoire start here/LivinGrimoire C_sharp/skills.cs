public class DiTime : DiSkillV2
{
    public DiTime()
        : base()
    {
    }

    public override void Input(string ear, string skin, string eye)
    {
        switch (ear)
        {
            case "what is the date":
                SetVerbatimAlg(4, $"{TimeUtils.GetCurrentMonthDay()} {TimeUtils.GetCurrentMonthName()} {TimeUtils.GetCurrentYear()}");
                break;
            case "what is the time":
                SetVerbatimAlg(4, TimeUtils.GetCurrentTimeStamp());
                break;
            case "honey bunny":
                SetVerbatimAlg(4, "bunny honey");
                break;
            case "which day is it":
                SetVerbatimAlg(4, TimeUtils.GetDayOfDWeek());
                break;
            case "good morning":
            case "good night":
            case "good afternoon":
            case "good evening":
                SetVerbatimAlg(4, $"good {TimeUtils.PartOfDay()}"); // fstring
                break;
            case "which month is it":
                SetVerbatimAlg(4, TimeUtils.GetCurrentMonthName());
                break;
            case "which year is it":
                SetVerbatimAlg(4, $"{TimeUtils.GetCurrentYear()}");
                break;
            case "what is your time zone":
                SetVerbatimAlg(4, TimeUtils.GetLocalTimeZone());
                break;
            case "incantation 0":
                SetVerbatimAlg(5, "fly", "bless of magic caster", "infinity wall", "magic ward holy", "life essen");
                break;
        }
    }
}