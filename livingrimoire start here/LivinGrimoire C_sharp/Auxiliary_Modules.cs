using System; // for TimeGate
using System.Text.RegularExpressions;
using System.Collections.Generic;
using System.Text;
public enum EnumRegexGrimoire
{
    email,
    timeStamp,
    int_type,
    double_num,
    repeatedWord,
    phone,
    trackingID,
    IPV4,
    domain,
    number,
    secondlessTimeStamp,
    date_stamp,
    fullDate,
    simpleTimeStamp
}
public class RegexUtil
{
    public Dictionary<EnumRegexGrimoire, string> regexDictionary = new Dictionary<EnumRegexGrimoire, string>();

    public RegexUtil()
    {
        regexDictionary.Add(EnumRegexGrimoire.email, @"[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,6}");
        regexDictionary.Add(EnumRegexGrimoire.timeStamp, "[0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}");
        regexDictionary.Add(EnumRegexGrimoire.simpleTimeStamp, "[0-9]{1,2}:[0-9]{1,2}");
        regexDictionary.Add(EnumRegexGrimoire.secondlessTimeStamp, "[0-9]{1,2}:[0-9]{1,2}");
        regexDictionary.Add(EnumRegexGrimoire.fullDate, "[0-9]{1,4}/[0-9]{1,2}/[0-9]{1,2} [0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}");
        regexDictionary.Add(EnumRegexGrimoire.date_stamp, "[0-9]{1,4}/[0-9]{1,2}/[0-9]{1,2}");
        regexDictionary.Add(EnumRegexGrimoire.double_num, "[-+]?[0-9]*[.,][0-9]*");
        regexDictionary.Add(EnumRegexGrimoire.int_type, "[-+]?[0-9]{1,13}");
        regexDictionary.Add(EnumRegexGrimoire.repeatedWord, "\\b([\\w\\s']+) \\1\\b");
        regexDictionary.Add(EnumRegexGrimoire.phone, "[0]\\d{9}");
        regexDictionary.Add(EnumRegexGrimoire.trackingID, "[A-Z]{2}[0-9]{9}[A-Z]{2}");
        regexDictionary.Add(EnumRegexGrimoire.IPV4, "([0-9]{1,3}\\.){3}[0-9]{1,3}");
        regexDictionary.Add(EnumRegexGrimoire.domain, "[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}");
        regexDictionary.Add(EnumRegexGrimoire.number, "\\d+(\\.\\d+)?");

    }

    public string ExtractRegex(string regexStr, string ear)
    {
        Regex regex = new Regex(regexStr);
        Match match = regex.Match(ear);

        if (match.Success)
        {
            return match.Value;
        }
        else
        {
            return string.Empty;
        }
    }

    public string ExtractRegex(EnumRegexGrimoire regexStr, string ear)
    {
        Regex regex = new Regex(regexDictionary[regexStr]);
        Match match = regex.Match(ear);

        if (match.Success)
        {
            return match.Value;
        }
        else
        {
            return string.Empty;
        }
    }
}
public class TimeGate
{
    private int pause = 5; // minutes to keep gate closed
    private DateTime openedGate = DateTime.Now;

    public TimeGate(int minutes)
    {
        System.Threading.Thread.Sleep(100);
        this.pause = minutes;
    }

    public TimeGate()
    {
        System.Threading.Thread.Sleep(100);
    }

    public void SetPause(int pause)
    {
        if (pause < 60 && pause > 0)
        {
            this.pause = pause;
        }
    }

    public void OpenGate()
    {
        // The gate will stay open for pause minutes.
        this.openedGate = this.openedGate.AddMinutes(pause);
    }

    public void Close()
    {
        openedGate = new DateTime();
    }

    public bool IsClosed()
    {
        return DateTime.Compare(openedGate, DateTime.Now) < 0;
    }

    public bool IsOpen()
    {
        return !IsClosed();
    }

    public void OpenGate(int minutes)
    {
        DateTime now = new DateTime();
        this.openedGate = this.openedGate.AddMinutes(minutes);
    }
}
public class LGFIFO<T>
{
    // First-in-first-out queue
    private List<T> elements = new List<T>();

    public List<T> GetElements()
    {
        return this.elements;
    }

    public void SetElements(List<T> elements)
    {
        this.elements = elements;
    }

    public void Add(T item)
    {
        elements.Add(item);
    }

    public int Size()
    {
        return elements.Count;
    }

    public T Peek()
    {
        if (Size() == 0)
        {
            return default(T);
        }
        return elements[0];
    }

    public T Poll()
    {
        if (Size() == 0)
        {
            return default(T);
        }
        T result = elements[0];
        elements.RemoveAt(0);
        return result;
    }

    public void RemoveItem(T item)
    {
        if (elements.Contains(item))
        {
            elements.Remove(item);
        }
    }

    public void Clear()
    {
        elements.Clear();
    }

    private Random rand = new Random();

    public T GetRandomElement()
    {
        if (elements.Count == 0)
        {
            return default(T);
        }
        return elements[rand.Next(elements.Count)];
    }

    public bool Contains(T input)
    {
        return elements.Contains(input);
    }

    public bool IsEmpty()
    {
        return elements.Count == 0;
    }

    public void Remove(T element)
    {
        elements.Remove(element);
    }

    public IEnumerator<T> GetEnumerator()
    {
        return elements.GetEnumerator();
    }
}
public class TimeUtils
{
    public static string GetCurrentTimeStamp()
    {
        // Get the current time
        DateTime currentTime = DateTime.Now;

        // Format the time as "HH:mm"
        string formattedTime = currentTime.ToString("HH:mm");

        // Return the formatted time
        return formattedTime;
    }
    public static int GetMonthAsInt()
    {
        // Get the current date
        DateTime currentDate = DateTime.Now;

        // Extract the month part
        int currentMonth = currentDate.Month;

        // Return the month as an integer
        return currentMonth;
    }

    public static int GetDayOfTheMonthAsInt()
    {
        // Get the current date
        DateTime currentDate = DateTime.Now;

        // Extract the day of the month
        int dayOfMonth = currentDate.Day;

        // Return the day as an integer
        return dayOfMonth;
    }

    public static int GetCurrentYear()
    {
        int currentYear = DateTime.Now.Year;
        return currentYear;
    }

    public static string GetMinutes()
    {
        // Get the current time
        DateTime currentTime = DateTime.Now;

        // Extract the minutes part
        string minutes = currentTime.ToString("mm");

        // Return the minutes as a string
        return minutes;
    }
    public static string GetSeconds()
    {
        // Get the current time
        DateTime currentTime = DateTime.Now;

        // Extract the seconds part
        string seconds = currentTime.ToString("ss");

        // Return the seconds as a string
        return seconds;
    }

    public static int GetCurrentDayOfWeekAsInt()
    {
        // Get the current culture
        System.Globalization.CultureInfo myCulture = System.Globalization.CultureInfo.CurrentCulture;

        // Get the day of the week for today
        DayOfWeek dayOfWeek = myCulture.Calendar.GetDayOfWeek(DateTime.Today);

        // Convert to an integer (1 to 7)
        int dayNumber = (int)dayOfWeek + 1;

        // Handle Sunday (7) as a special case
        if (dayNumber == 8)
        {
            dayNumber = 1;
        }

        return dayNumber;
    }

    public static string GetDayOfDWeek()
    {
        int n = GetCurrentDayOfWeekAsInt();
        switch (n)
        {
            case 1:
                return "sunday";
            case 2:
                return "monday";
            case 3:
                return "tuesday";
            case 4:
                return "wednesday";
            case 5:
                return "thursday";
            case 6:
                return "friday";
            case 7:
                return "saturday";
            default:
                return "Invalid day number";
        }
    }
    public static int GetSecondsAsInt()
    {
        return DateTime.Now.Second;
    }

    public static int GetMinutesAsInt()
    {
        return DateTime.Now.Minute;
    }

    public static int GetHoursAsInt()
    {
        return DateTime.Now.Hour;
    }

    public static string GetFutureInXMin(int minutes)
    {
        // Get the current time
        DateTime currentTime = DateTime.Now;

        // Format the time as "HH:mm"
        string formattedTime = currentTime.AddMinutes(minutes).ToString("HH:mm");

        // Return the formatted time
        return formattedTime;
    }

    public static string GetPastInXMin(int minutes)
    {
        // Get the current time
        DateTime currentTime = DateTime.Now;

        // Format the time as "HH:mm"
        string formattedTime = currentTime.AddMinutes(-minutes).ToString("HH:mm");

        // Return the formatted time
        return formattedTime;
    }
    public static string TranslateMonth(int month1)
    {
        string dMonth = "";

        switch (month1)
        {
            case 1:
                dMonth = "January";
                break;
            case 2:
                dMonth = "February";
                break;
            case 3:
                dMonth = "March";
                break;
            case 4:
                dMonth = "April";
                break;
            case 5:
                dMonth = "May";
                break;
            case 6:
                dMonth = "June";
                break;
            case 7:
                dMonth = "July";
                break;
            case 8:
                dMonth = "August";
                break;
            case 9:
                dMonth = "September";
                break;
            case 10:
                dMonth = "October";
                break;
            case 11:
                dMonth = "November";
                break;
            case 12:
                dMonth = "December";
                break;
            default:
                // Handle invalid month values (optional)
                dMonth = "Invalid Month";
                break;
        }

        return dMonth;
    }
    public static string TranslateMonthDay(int day1)
    {
        string dday = "";
        switch (day1)
        {
            case 1:
                dday = "first_of";
                break;
            case 2:
                dday = "second_of";
                break;
            case 3:
                dday = "third_of";
                break;
            case 4:
                dday = "fourth_of";
                break;
            case 5:
                dday = "fifth_of";
                break;
            case 6:
                dday = "six_of";
                break;
            case 7:
                dday = "seventh_of";
                break;
            case 8:
                dday = "eighth_of";
                break;
            case 9:
                dday = "nineth_of";
                break;
            case 10:
                dday = "tenth_of";
                break;
            case 11:
                dday = "eleventh_of";
                break;
            case 12:
                dday = "twelveth_of";
                break;
            case 13:
                dday = "thirteenth_of";
                break;
            case 14:
                dday = "fourteenth_of";
                break;
            case 15:
                dday = "fifteenth_of";
                break;
            case 16:
                dday = "sixteenth_of";
                break;
            case 17:
                dday = "seventeenth_of";
                break;
            case 18:
                dday = "eighteenth_of";
                break;
            case 19:
                dday = "nineteenth_of";
                break;
            case 20:
                dday = "twentyth_of";
                break;
            case 21:
                dday = "twentyfirst_of";
                break;
            case 22:
                dday = "twentysecond_of";
                break;
            case 23:
                dday = "twentythird_of";
                break;
            case 24:
                dday = "twentyfourth_of";
                break;
            case 25:
                dday = "twentyfifth_of";
                break;
            case 26:
                dday = "twentysixth_of";
                break;
            case 27:
                dday = "twentyseventh_of";
                break;
            case 28:
                dday = "twentyeighth_of";
                break;
            case 29:
                dday = "twentynineth_of";
                break;
            case 30:
                dday = "thirtyth_of";
                break;
            case 31:
                dday = "thirtyfirst_of";
                break;
            default:
                dday = "Invalid_day";
                break;
        }
        return dday;
    }
    public static bool SmallToBig(params int[] a)
    {
        for (int i = 0; i < a.Length - 1; i++)
        {
            if (!(a[i] < a[i + 1]))
            {
                return false;
            }
        }
        return true;
    }

    public static bool IsDayTime()
    {
        int hour = GetHoursAsInt();
        return hour > 5 && hour < 19;
    }

    public static string PartOfDay()
    {
        int hour = GetHoursAsInt();

        if (SmallToBig(5, hour, 12))
        {
            return "morning";
        }
        else if (SmallToBig(11, hour, 17))
        {
            return "afternoon";
        }
        else if (SmallToBig(16, hour, 21))
        {
            return "evening";
        }
        else
        {
            return "night";
        }
    }

    public static bool IsNight()
    {
        int hour = GetHoursAsInt();
        return hour > 20 || hour < 6;
    }
    public static string GetYesterday()
    {
        int n = GetCurrentDayOfWeekAsInt();
        switch (n)
        {
            case 2:
                return "sunday";
            case 3:
                return "monday";
            case 4:
                return "tuesday";
            case 5:
                return "wednesday";
            case 6:
                return "thursday";
            case 7:
                return "friday";
            case 1:
                return "saturday";
            default:
                return "Invalid day number";
        }
    }

    public static string GetTomorrow()
    {
        int n = GetCurrentDayOfWeekAsInt();
        switch (n)
        {
            case 7:
                return "sunday";
            case 1:
                return "monday";
            case 2:
                return "tuesday";
            case 3:
                return "wednesday";
            case 4:
                return "thursday";
            case 5:
                return "friday";
            case 6:
                return "saturday";
            default:
                return "Invalid day number";
        }
    }
    public static string GetLocalTimeZone()
    {
        TimeZoneInfo localTimeZone = TimeZoneInfo.Local;
        return localTimeZone.Id;
    }
    public static bool IsLeapYear(int year)
    {
        // Divisible by 4.
        bool b1 = (year % 4 == 0);

        // Divisible by 4, not by 100, or divisible by 400.
        return b1 && (year % 100 != 0 || year % 400 == 0);
    }

    public static string GetCurrentMonthName()
    {
        return TranslateMonth(GetMonthAsInt());
    }

    public static string GetCurrentMonthDay()
    {
        return TranslateMonthDay(GetDayOfTheMonthAsInt());
    }


    public static string FindDay(int month, int day, int year)
    {
        // Validate input: Ensure day is within a valid range (1 to 31).
        if (day > 31)
        {
            return "";
        }

        // Check specific months with 30 days.
        if (day > 30 && (month == 4 || month == 6 || month == 9 || month == 11))
        {
            return "";
        }

        // Check February for leap year.
        if (month == 2)
        {
            if (IsLeapYear(year))
            {
                if (day > 29)
                {
                    return "";
                }
            }
            else if (day > 28)
            {
                return "";
            }
        }

        DateTime localDate = new DateTime(year, month, day);
        DayOfWeek dayOfWeek = localDate.DayOfWeek;
        return dayOfWeek.ToString().ToLower();
    }

    public static string NextDayOnDate(int dayOfMonth)
    {
        // Get the weekday on the next dayOfMonth.
        int today = DateTime.Now.Day;
        int nextMonth = DateTime.Now.Month;
        int nextYear = DateTime.Now.Year;

        if (today <= dayOfMonth)
        {
            return FindDay(nextMonth, dayOfMonth, nextYear);
        }
        else if (nextMonth != 12) // December?
        {
            return FindDay(nextMonth + 1, dayOfMonth, nextYear);
        }

        return FindDay(1, dayOfMonth, nextYear + 1);
    }

}
