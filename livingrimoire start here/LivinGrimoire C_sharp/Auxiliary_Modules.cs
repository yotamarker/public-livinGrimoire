using System; // for TimeGate
using System.Text.RegularExpressions;
using System.Collections.Generic;
using System.Text;
public class DeepCopier
{
    public List<string> DeepCopyStringList(List<string> originalList)
    {
        // Create a new list and copy each element from the original list.
        List<string> copiedList = new List<string>(originalList.Count);
        foreach (string item in originalList)
        {
            copiedList.Add(item);
        }

        // Return the deep-copied list.
        return copiedList;
    }

    public List<int> DeepCopyIntList(List<int> originalList)
    {
        // Create a new list and copy each element from the original list.
        List<int> copiedList = new List<int>(originalList.Count);
        foreach (int item in originalList)
        {
            copiedList.Add(item);
        }

        // Return the deep-copied list.
        return copiedList;
    }
}
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
    public void openGateforNSeconds(int n)
    {
        DateTime now = new DateTime();
        this.openedGate = this.openedGate.AddSeconds(n);
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
public class AlgDispenser
{
    // Super class to output an algorithm out of a selection of algorithms
    private List<Algorithm> algs = new List<Algorithm>();
    private int activeAlg = 0;
    private Random rand = new Random();

    public AlgDispenser(params Algorithm[] algorithms)
    {
        foreach (Algorithm alg in algorithms)
        {
            algs.Add(alg);
        }
    }

    public AlgDispenser AddAlgorithm(Algorithm alg)
    {
        // Builder pattern
        algs.Add(alg);
        return this;
    }

    public Algorithm DispenseAlgorithm()
    {
        return algs[activeAlg];
    }

    public Algorithm RndAld()
    {
        // Return a random algorithm
        return algs[rand.Next(algs.Count)];
    }

    public void MoodAlg(int mood)
    {
        // Set output algorithm based on number representing mood
        if (mood >= 0 && mood < algs.Count)
        {
            activeAlg = mood;
        }
    }

    public void AlgUpdate(int mood, Algorithm alg)
    {
        // Update an algorithm
        if (mood >= 0 && mood < algs.Count)
        {
            algs[mood] = alg;
        }
    }

    public void AlgRemove(int mood)
    {
        // Remove an algorithm
        if (mood >= 0 && mood < algs.Count)
        {
            algs.RemoveAt(mood);
        }
    }

    public void CycleAlg()
    {
        activeAlg++;
        if (activeAlg == algs.Count)
        {
            activeAlg = 0;
        }
    }
}
public class UniqueItemsPriorityQueue : LGFIFO<string>
{
    public new void Add(string item)
    {
        if (!base.Contains(item))
        {
            base.Add(item);
        }
    }

    public string Peak()
    {
        string temp = base.Peek();
        return temp ?? "";
    }

    public bool StrContainsResponse(string str)
    {
        foreach (string tempStr in GetElements())
        {
            if (str.Contains(tempStr))
            {
                return true;
            }
        }
        return false;
    }
}
public class UniqueItemSizeLimitedPriorityQueue : UniqueItemsPriorityQueue
{
    private int _limit = 5;

    public int Limit
    {
        get { return _limit; }
        set { _limit = value; }
    }

    public new void Add(string item)
    {
        if (base.Size() == Limit)
        {
            base.Poll();
        }
        base.Add(item);
    }

    public new string Poll()
    {
        string temp = base.Poll();
        return temp ?? "";
    }

    public string GetRNDElement()
    {
        string temp = base.GetRandomElement();
        return temp ?? "";
    }

    public List<string> GetAsList()
    {
        return GetElements();
    }
}
public class RefreshQ : UniqueItemSizeLimitedPriorityQueue
{
    public new void RemoveItem(string item)
    {
        base.GetElements().Remove(item);
    }

    public new void Add(string item)
    {
        // FILO
        if (base.Contains(item))
        {
            RemoveItem(item);
        }
        base.Add(item);
    }
}
public class AnnoyedQue
{
    private RefreshQ q1 = new RefreshQ();
    private RefreshQ q2 = new RefreshQ();

    public AnnoyedQue(int queLim)
    {
        q1.Limit = queLim;
        q2.Limit = queLim;
    }

    public void Learn(string ear)
    {
        if (q1.Contains(ear))
        {
            q2.Add(ear);
            return;
        }
        q1.Add(ear);
    }

    public bool IsAnnoyed(string ear)
    {
        return q2.StrContainsResponse(ear);
    }
}
public class AXCmdBreaker
{
    // Separate command parameter from the command
    public string conjuration;

    public AXCmdBreaker(string conjuration)
    {
        this.conjuration = conjuration;
    }

    public string ExtractCmdParam(string s1)
    {
        if (s1.Contains(conjuration))
        {
            return s1.Replace(conjuration, "").Trim();
        }
        return "";
    }
}
public class AXFunnel
{
    // Funnel many inputs to fewer or one input
    // Allows using command variations in skills
    private Dictionary<string, string> dic = new Dictionary<string, string>();
    private string defaultStr = "default";

    public AXFunnel()
    {
        // Constructor initializes the dictionary and default value
        dic = new Dictionary<string, string>();
        defaultStr = "default";
    }

    public void SetDefault(string defaultStr)
    {
        // Set the default value
        this.defaultStr = defaultStr;
    }

    public AXFunnel AddKV(string key, string value)
    {
        // Add key-value pair to the dictionary
        dic[key] = value;
        return this;
    }

    public AXFunnel AddK(string key)
    {
        // Add key with default value to the dictionary
        dic[key] = defaultStr;
        return this;
    }

    public string Funnel(string key)
    {
        // Get value from dictionary or return the key itself as default
        return dic.ContainsKey(key) ? dic[key] : key;
    }
}
public class AXGamification
{
    // This auxiliary module can add fun to tasks, skills, and abilities simply by
    // tracking their usage and maximum use count.
    private int counter = 0;
    private int max = 0;

    public int GetCounter()
    {
        return counter;
    }

    public int GetMax()
    {
        return max;
    }

    public void ResetCount()
    {
        counter = 0;
    }

    public void ResetAll()
    {
        max = 0;
        counter = 0;
    }

    public void Increment()
    {
        counter++;
        if (counter > max)
        {
            max = counter;
        }
    }

    public void IncrementBy(int n)
    {
        counter += n;
        if (counter > max)
        {
            max = counter;
        }
    }

    public bool Reward(int cost)
    {
        // Game grind points used for rewards
        // Consumables, items, or upgrades – this makes games fun
        if (cost > counter)
        {
            return false;
        }
        counter -= cost;
        return true;
    }

    public bool Surplus(int cost)
    {
        // Has surplus for reward?
        if (cost > counter)
        {
            return false;
        }
        return true;
    }
}
public class AXKeyValuePair
{
    private string key = "";
    private string value = "";

    public AXKeyValuePair()
    {
    }

    public AXKeyValuePair(string key, string value)
    {
        this.key = key;
        this.value = value;
    }

    public string GetKey()
    {
        return key;
    }

    public void SetKey(string key)
    {
        this.key = key;
    }

    public string GetValue()
    {
        return value;
    }

    public void SetValue(string value)
    {
        this.value = value;
    }

    public override string ToString()
    {
        return key + ";" + value;
    }
}
public class TrGEV3
{
    // Advanced boolean gates with internal logic.
    // These ease connecting common logic patterns, acting as triggers.

    public void Reset()
    {
        // Reset logic gate state.
        // Implement your reset logic here.
    }

    public void Input(string ear, string skin, string eye)
    {
        // Process input data.
        // Implement your input logic here.
    }

    public bool Trigger()
    {
        // Determine whether the trigger condition is met.
        // Implement your trigger logic here.
        return false;
    }
}
public class TrgTolerance : TrGEV3
{
    // This boolean gate will return true until depletion or reset.
    private int repeats = 0;
    private int maxrepeats; // Recommended value: 2

    public TrgTolerance(int maxrepeats)
    {
        this.maxrepeats = maxrepeats;
    }

    public void SetMaxrepeats(int maxrepeats)
    {
        this.maxrepeats = maxrepeats;
        Reset();
    }

    public new void Reset()
    {
        // Refill trigger
        repeats = maxrepeats;
    }

    public new bool Trigger()
    {
        // Will return true until depletion or reset.
        repeats--;
        if (repeats > 0)
        {
            return true;
        }
        return false;
    }

    public void Disable()
    {
        repeats = 0;
    }
}
public class AXLearnability
{
    private bool algSent = false;
    public UniqueItemSizeLimitedPriorityQueue defcons = new UniqueItemSizeLimitedPriorityQueue(); // Default size = 5
    public UniqueItemSizeLimitedPriorityQueue defcon5 = new UniqueItemSizeLimitedPriorityQueue();
    public UniqueItemSizeLimitedPriorityQueue goals = new UniqueItemSizeLimitedPriorityQueue();
    public TrgTolerance trgTolerance;

    public AXLearnability(int tolerance)
    {
        this.trgTolerance = new TrgTolerance(tolerance);
        trgTolerance.Reset();
    }

    public void PendAlg()
    {
        algSent = true;
        trgTolerance.Trigger();
    }

    public void PendAlgWithoutConfirmation()
    {
        algSent = true;
    }

    public bool MutateAlg(string input)
    {
        if (!algSent)
        {
            return false; // No alg sent => no reason to mutate
        }
        if (goals.Contains(input))
        {
            trgTolerance.Reset();
            algSent = false;
            return false;
        }
        if (defcon5.Contains(input))
        {
            trgTolerance.Reset();
            algSent = false;
            return true;
        }
        if (defcons.Contains(input))
        {
            algSent = false;
            bool mutate = !trgTolerance.Trigger();
            if (mutate)
            {
                trgTolerance.Reset();
            }
            return mutate;
        }
        return false;
    }

    public void ResetTolerance()
    {
        trgTolerance.Reset();
    }
}
public class AXLHousing
{
    public virtual string Decorate(string str1)
    {
        // Override me
        return "";
    }
}
public class LGTypeConverter
{
    private RegexUtil r1 = new RegexUtil();

    public int ConvertToInt(string v1)
    {
        string temp = r1.ExtractRegex(EnumRegexGrimoire.int_type, v1);
        if (string.IsNullOrEmpty(temp))
        {
            return 0;
        }
        return int.Parse(temp);
    }

    public double ConvertToDouble(string v1)
    {
        string temp = r1.ExtractRegex(EnumRegexGrimoire.double_num, v1);
        if (string.IsNullOrEmpty(temp))
        {
            return 0.0;
        }
        return double.Parse(temp);
    }
}
public class DrawRnd
{
    // Draw a random element, then remove said element.
    private List<string> strings = new List<string>();
    private List<string> stringsSource = new List<string>();
    private Random rand = new Random();

    public DrawRnd(params string[] values)
    {
        foreach (string value in values)
        {
            strings.Add(value);
            stringsSource.Add(value);
        }
    }

    public void AddElement(string element)
    {
        strings.Add(element);
        stringsSource.Add(element);
    }

    public string Draw()
    {
        if (strings.Count == 0)
        {
            return "";
        }

        int x = rand.Next(strings.Count);
        string element = strings[x];
        strings.RemoveAt(x);
        return element;
    }

    public int GetSimpleRNDNum(int bound)
    {
        // Return 0 to bound-1
        return rand.Next(bound);
    }

    private LGTypeConverter tc = new LGTypeConverter();

    public int DrawAsInt()
    {
        if (strings.Count == 0)
        {
            return 0;
        }

        int x = rand.Next(strings.Count);
        string element = strings[x];
        strings.RemoveAt(x);
        return tc.ConvertToInt(element);
    }

    public void Reset()
    {
        DeepCopier dc = new DeepCopier();
        strings = dc.DeepCopyStringList(stringsSource);
    }

    public bool IsEmptied()
    {
        return strings.Count == 0;
    }
}
public class DrawRndDigits
{
    private List<int> strings = new List<int>();
    private List<int> stringsSource = new List<int>();
    private Random rand = new Random();

    public DrawRndDigits(params int[] values)
    {
        foreach (int value in values)
        {
            strings.Add(value);
            stringsSource.Add(value);
        }
    }

    public void AddElement(int element)
    {
        strings.Add(element);
        stringsSource.Add(element);
    }

    public int Draw()
    {
        if (strings.Count == 0)
        {
            return -1;
        }

        int x = rand.Next(strings.Count);
        int element = strings[x];
        strings.RemoveAt(x);
        return element;
    }

    public int GetSimpleRNDNum(int bound)
    {
        // Return a random integer in the range 0 to bound-1
        return rand.Next(bound);
    }

    public void Reset()
    {
        DeepCopier dc = new DeepCopier();
        strings = dc.DeepCopyIntList(stringsSource);
    }
}
public class Responder
{
    // Simple random response dispenser
    private List<string> responses = new List<string>();
    private Random rand = new Random();

    public Responder(params string[] replies)
    {
        foreach (string reply in replies)
        {
            responses.Add(reply);
        }
    }

    public string GetAResponse()
    {
        if (responses.Count == 0)
        {
            return "";
        }
        return responses[rand.Next(responses.Count)];
    }

    public bool ResponsesContainsStr(string str)
    {
        return responses.Contains(str);
    }

    public bool StrContainsResponse(string str)
    {
        foreach (string tempStr in responses)
        {
            if (str.Contains(tempStr))
            {
                return true;
            }
        }
        return false;
    }

    public void AddResponse(string s1)
    {
        responses.Add(s1);
    }
}
public class AXNeuroSama
{
    private Responder nyaa = new Responder(" heart", " heart", " wink", " heart heart heart");
    private DrawRnd rnd = new DrawRnd();
    private int rate;

    public AXNeuroSama(int rate)
    {
        // The higher the rate, the less likely to decorate outputs
        this.rate = rate;
    }

    public string Decorate(string output)
    {
        if (output == "")
        {
            return output;
        }
        if (rnd.GetSimpleRNDNum(rate) == 0)
        {
            return output + nyaa.GetAResponse();
        }
        return output;
    }
}
public class PercentDripper
{
    private DrawRnd dr = new DrawRnd();
    private int limis = 35;

    public void SetLimis(int limis)
    {
        this.limis = limis;
    }

    public bool Drip()
    {
        return dr.GetSimpleRNDNum(100) < limis;
    }

    public bool DripPlus(int plus)
    {
        return dr.GetSimpleRNDNum(100) < limis + plus;
    }
}
public class AXNPC
{
    public RefreshQ responder = new RefreshQ();
    public PercentDripper dripper = new PercentDripper();
    public AXCmdBreaker cmdBreaker = new AXCmdBreaker("say");

    public AXNPC(int replyStockLim, int outputChance)
    {
        responder.Limit = replyStockLim;
        if (outputChance > 0 && outputChance < 101)
        {
            dripper.SetLimis(outputChance);
        }
    }

    public string Respond()
    {
        if (dripper.Drip())
        {
            return responder.GetRNDElement();
        }
        return "";
    }

    public string RespondPlus(int plus)
    {
        // Increase rate of output
        if (dripper.DripPlus(plus))
        {
            return responder.GetRNDElement();
        }
        return "";
    }

    public bool Learn(string ear)
    {
        // Say hello there: hello there is learned
        string temp = cmdBreaker.ExtractCmdParam(ear);
        if (string.IsNullOrEmpty(temp))
        {
            return false;
        }
        responder.Add(temp);
        return true;
    }

    public string StrRespond(string ear)
    {
        // Respond if ear contains a learned input
        if (string.IsNullOrEmpty(ear))
        {
            return "";
        }
        if (dripper.Drip() && responder.StrContainsResponse(ear))
        {
            return responder.GetRNDElement();
        }
        return "";
    }

    public string ForceRespond()
    {
        return responder.GetRNDElement();
    }

    public void SetConjuration(string conjuration)
    {
        cmdBreaker.conjuration = conjuration;
    }
}
public class AXNPC2 : AXNPC
{
    public AnnoyedQue annoyedQue = new AnnoyedQue(5);

    public AXNPC2(int replyStockLim, int outputChance) : base(replyStockLim, outputChance)
    {
    }

    public void StrLearn(string ear)
    {
        // Learns inputs containing strings that are repeatedly used by others
        annoyedQue.Learn(ear);
        if (annoyedQue.IsAnnoyed(ear))
        {
            this.responder.Add(ear);
        }
    }
}
public class AXPassword
{
    // code # to open the gate
    // while gate is open, code can be changed with: code new_number
    private bool isOpen = false;
    private int maxAttempts = 3;
    private int loginAttempts;
    private RegexUtil regexUtil = new RegexUtil();
    private int code = 0;

    public bool CodeUpdate(string ear)
    {
        // while the gate is toggled on, the password code can be changed
        if (!isOpen)
        {
            return false;
        }
        if (ear.Contains("code"))
        {
            string temp = regexUtil.ExtractRegex(EnumRegexGrimoire.int_type, ear);
            if (!string.IsNullOrEmpty(temp))
            {
                code = int.Parse(temp);
                return true;
            }
        }
        return false;
    }

    public void OpenGate(string ear)
    {
        if (ear.Contains("code") && (loginAttempts > 0))
        {
            string noCode = regexUtil.ExtractRegex(EnumRegexGrimoire.int_type, ear);
            if (!string.IsNullOrEmpty(noCode))
            {
                int tempCode = int.Parse(noCode);
                if (tempCode == code)
                {
                    loginAttempts = maxAttempts;
                    isOpen = true;
                }
                else
                {
                    loginAttempts--;
                }
            }
        }
    }

    public bool IsGateOpen()
    {
        return isOpen;
    }
    public void ResetAttempts()
    {
        // Should happen once a day or hour to prevent hacking
        loginAttempts = maxAttempts;
    }

    public int GetLoginAttempts()
    {
        // Return remaining login attempts
        return loginAttempts;
    }

    public void CloseGate()
    {
        isOpen = false;
    }

    public void CloseGate(string ear)
    {
        if (ear.Contains("close"))
        {
            isOpen = false;
        }
    }

    public void SetMaxAttempts(int maxAttempts)
    {
        this.maxAttempts = maxAttempts;
    }

    public int GetCode()
    {
        if (isOpen)
        {
            return code;
        }
        return -1;
    }

    public void RandomizeCode(int lim, int minimumLim)
    {
        code = new DrawRnd().GetSimpleRNDNum(lim) + minimumLim;
    }

    public int GetCodeEvent()
    {
        // Event feature
        // Get the code during weekly/monthly event after it has been randomized
        return code;
    }

}
public class Prompt
{
    private RegexUtil regexUtil = new RegexUtil();
    public AXKeyValuePair kv = new AXKeyValuePair();
    private string prompt = "";
    private string regex = "";

    public Prompt()
    {
        kv.SetKey("default");
    }

    public string GetPrompt()
    {
        return prompt;
    }

    public void SetPrompt(string prompt)
    {
        this.prompt = prompt;
    }

    public bool Process(string in1)
    {
        kv.SetValue(regexUtil.ExtractRegex(regex, in1));
        return kv.GetValue() == ""; // Is prompt still active?
    }

    public AXKeyValuePair GetKv()
    {
        return kv;
    }

    public void SetRegex(string regex)
    {
        this.regex = regex;
    }
}
public class AXPrompt
{
    private bool isActive = false;
    private int index = 0;
    private List<Prompt> prompts = new List<Prompt>();
    private AXKeyValuePair kv = null;

    public void AddPrompt(Prompt p1)
    {
        prompts.Add(p1);
    }

    public string GetPrompt()
    {
        if (prompts.Count == 0)
        {
            return "";
        }
        return prompts[index].GetPrompt();
    }

    public void Process(string in1)
    {
        if (prompts.Count == 0 || !isActive)
        {
            return;
        }
        bool b1 = prompts[index].Process(in1);
        if (!b1)
        {
            kv = prompts[index].GetKv();
            index++;
        }
        if (index == prompts.Count)
        {
            isActive = false;
        }
    }

    public bool GetActive()
    {
        return isActive;
    }

    public AXKeyValuePair GetKv()
    {
        if (kv == null)
        {
            return null;
        }
        AXKeyValuePair temp = new AXKeyValuePair();
        temp.SetKey(kv.GetKey());
        temp.SetValue(kv.GetValue());
        kv = null;
        return temp;
    }

    public void Activate()
    {
        // Reset
        isActive = true;
        index = 0;
    }

    public void Deactivate()
    {
        // Reset
        isActive = false;
        index = 0;
    }
}
public class AXShoutOut
{
    private bool isActive = false;
    public Responder handshake = new Responder();

    public void Activate()
    {
        // Make engage-able
        isActive = true;
    }

    public bool Engage(string ear)
    {
        if (string.IsNullOrEmpty(ear))
        {
            return false;
        }
        if (isActive)
        {
            if (handshake.StrContainsResponse(ear))
            {
                isActive = false;
                return true; // Shout out was replied!
            }
        }
        // Unrelated reply to shout out, shout out context is outdated
        isActive = false;
        return false;
    }
}
public class Strategy
{
    private UniqueItemSizeLimitedPriorityQueue activeStrategy; // Active strategic options
    private DrawRnd allStrategies; // Bank of all strategies. Out of this pool, active strategies are pulled

    public Strategy(DrawRnd allStrategies)
    {
        // Create the Strategy Object with a bank of options
        this.allStrategies = allStrategies;
        this.activeStrategy = new UniqueItemSizeLimitedPriorityQueue();
    }

    public void EvolveStrategies(int strategiesLimit)
    {
        // Add N strategic options to the active strategies bank from the total strategy bank
        activeStrategy.Limit = strategiesLimit;
        string temp = allStrategies.Draw();
        for (int i = 0; i < strategiesLimit; i++)
        {
            if (string.IsNullOrEmpty(temp))
            {
                break;
            }
            activeStrategy.Add(temp);
            temp = allStrategies.Draw();
        }
        allStrategies.Reset();
    }

    public string GetStrategy()
    {
        return activeStrategy.GetRNDElement();
    }
}
public class AXStrategy
{
    // This auxiliary module is used to output strategies based on context.
    // It can be used for battles and games.
    // Upon pain/loss, use the evolve method to update to different new active strategies.
    // Check for battle state end externally (opponent state/hits on rival counter).
    // A dictionary of strategies.

    private int lim;
    private Dictionary<string, Strategy> strategies = new Dictionary<string, Strategy>();

    public AXStrategy(int lim)
    {
        // Limit of active strategies (pulled from all available strategies).
        this.lim = lim;
    }

    public void AddStrategy(string context, DrawRnd techniques)
    {
        // Add strategies per context.
        Strategy temp = new Strategy(techniques);
        temp.EvolveStrategies(lim);
        this.strategies.Add(context, temp);
    }

    public void Evolve()
    {
        // Replace active strategies.
        foreach (KeyValuePair<string, Strategy> kvp in this.strategies)
        {
            string key = kvp.Key;
            this.strategies[key].EvolveStrategies(lim);
        }
    }

    public string Process(string context)
    {
        // Process input and return action based on game context now.
        if (this.strategies.ContainsKey(context))
        {
            return this.strategies[context].GetStrategy();
        }
        return "";
    }
}
public class AXStringSplit
{
    // May be used to prepare data before saving or after loading.
    // The advantage is fewer data fields. For example: {skills: s1_s2_s3}
    private string spChar = "_";

    public void SetSpChar(string spChar)
    {
        this.spChar = spChar;
    }

    public string[] Split(string s1)
    {
        return s1.Split(new string[] { spChar }, StringSplitOptions.None);
    }

    public string StringBuilder(string[] sArr)
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sArr.Length - 1; i++)
        {
            sb.Append(sArr[i]);
            sb.Append(this.spChar);
        }
        sb.Append(sArr[sArr.Length - 1]);
        return sb.ToString();
    }
}
public class AXStrOrDefault
{
    public static string GetOrDefault(string str1, string default1)
    {
        return string.IsNullOrEmpty(str1) ? default1 : str1;
    }
}
public class TrgTime
{
    private string t = "null";
    private RegexUtil regexUtil = new RegexUtil();
    private bool alarm = true;

    public void SetTime(string v1)
    {
        t = regexUtil.ExtractRegex(EnumRegexGrimoire.simpleTimeStamp, v1);
    }

    public bool CheckAlarm()
    {
        string now = TimeUtils.GetCurrentTimeStamp();
        if (alarm)
        {
            if (now == t)
            {
                alarm = false;
                return true;
            }
        }
        if (now != t)
        {
            alarm = true;
        }
        return false;
    }
}
public class Cron : TrGEV3
{
    // Triggers true, limit times, after initial time, and every minutes interval
    // Counter resets at initial time, assuming trigger method was run
    private int minutes; // Minute interval between triggerings
    private TrgTime trgTime;
    private string timeStamp = "";
    private string initialTimeStamp = "";
    private int limit;
    private int counter = 0;

    public Cron(string startTime, int minutes, int limit)
    {
        this.minutes = minutes;
        this.timeStamp = startTime;
        this.initialTimeStamp = startTime;
        trgTime = new TrgTime();
        trgTime.SetTime(startTime);
        this.limit = limit;
        if (limit < 0)
        {
            this.limit = 1;
        }
    }

    public int GetLimit()
    {
        return limit;
    }

    public void SetLimit(int limit)
    {
        if (limit > -1)
        {
            this.limit = limit;
        }
    }

    public int GetCounter()
    {
        return counter;
    }
    public void SetMinutes(int minutes)
    {
        if (minutes > -1)
        {
            this.minutes = minutes;
        }
    }

    public new bool Trigger()
    {
        // Delete counter = 0 if you don't want the trigger to work the next day
        if (counter == limit)
        {
            trgTime.SetTime(initialTimeStamp);
            counter = 0;
            return false;
        }
        if (trgTime.CheckAlarm())
        {
            timeStamp = TimeUtils.GetFutureInXMin(minutes);
            trgTime.SetTime(timeStamp);
            counter++;
            return true;
        }
        return false;
    }

    public bool TriggerWithoutRenewal()
    {
        // Delete counter = 0 if you don't want the trigger to work the next day
        if (counter == limit)
        {
            trgTime.SetTime(initialTimeStamp);
            return false;
        }
        if (trgTime.CheckAlarm())
        {
            timeStamp = TimeUtils.GetFutureInXMin(minutes);
            trgTime.SetTime(timeStamp);
            counter++;
            return true;
        }
        return false;
    }

    public new void Reset()
    {
        // Manual trigger reset
        counter = 0;
    }

    public void SetStartTime(string t1)
    {
        initialTimeStamp = t1;
        timeStamp = t1;
        trgTime.SetTime(t1);
        counter = 0;
    }

    public void TurnOff()
    {
        counter = limit;
    }

}
public class Cycler
{
    private int cycler = 0;
    private int limit;

    public Cycler(int limit)
    {
        this.limit = limit;
        cycler = limit;
    }

    public int GetLimit()
    {
        return limit;
    }

    public void SetLimit(int limit)
    {
        this.limit = limit;
    }

    public int CycleCount()
    {
        cycler--;
        if (cycler < 0)
        {
            cycler = limit;
        }
        return cycler;
    }

    public void Reset()
    {
        cycler = limit;
    }

    public void SetToZero()
    {
        cycler = 0;
    }

    public void Sync(int n)
    {
        if (n < -1 || n > limit)
        {
            return;
        }
        cycler = n;
    }

    public int GetMode()
    {
        return cycler;
    }
}
public class Differ
{
    private int powerLevel = 90;
    private int difference = 0;

    public int GetPowerLevel()
    {
        return powerLevel;
    }

    public int GetDifference()
    {
        return difference;
    }

    public void ClearPowerLVDifference()
    {
        difference = 0;
    }

    public void SamplePowerLV(int pl)
    {
        // pl is the current power level
        difference = pl - powerLevel;
        powerLevel = pl;
    }
}
public class Eliza
{
    private static Dictionary<string, string> reflections = new Dictionary<string, string>
    {
        {"am", "are"},
        {"was", "were"},
        {"i", "you"},
        {"i'd", "you would"},
        {"i've", "you have"},
        {"my", "your"},
        {"are", "am"},
        {"you've", "I have"},
        {"you'll", "I will"},
        {"your", "my"},
        {"yours", "mine"},
        {"you", "i"},
        {"me", "you"}
    };
    public class PhraseMatcher
    {
        private Regex matcher;
        private List<string> responses;
        public string context = "";
        public string param = "";
        public string infoRequest = "";

        public PhraseMatcher(string matcher, List<string> responses)
        {
            this.matcher = new Regex(matcher);
            this.responses = responses;
        }

        public bool Matches(string s)
        {
            return this.matcher.IsMatch(s);
        }

        public string Respond(string s)
        {
            Match m = this.matcher.Match(s);
            this.context = this.matcher.ToString(); // context
            string p = this.RandomPhrase();
            for (int i = 0; i < m.Groups.Count - 1; i++)
            {
                string groupValue = Reflect(m.Groups[i + 1].Value);
                this.param = groupValue; // param
                this.infoRequest = p; // more info request
                p = p.Replace("{" + i + "}", groupValue);
            }
            return p;
        }

        public static string Reflect(string s)
        {
            string[] words = s.Split(' ');
            for (int i = 0; i < words.Length; i++)
            {
                if (Eliza.reflections.ContainsKey(words[i]))
                {
                    words[i] = Eliza.reflections[words[i]];
                }
            }
            return string.Join(" ", words);
        }

        public string RandomPhrase()
        {
            Random rand = new Random();
            int randomIndex = Math.Abs(rand.Next(0, this.responses.Count));
            return this.responses[randomIndex];
        }
        public override string ToString()
        {
            return $"{this.matcher.ToString()}: {string.Join(", ", this.responses)}";
        }

    }
    public List<PhraseMatcher> Babble { get; } = new List<PhraseMatcher>
    {
        new PhraseMatcher("i need (.*)", new List<string>
        {
            "Why do you need {0}?",
            "Would it really help you to get {0}?",
            "Are you sure you need {0}?"
        })
    };

    public string Respond(string msg)
    {
        foreach (var pm in Babble)
        {
            if (pm.Matches(msg))
            {
                return pm.Respond(msg.ToLower());
            }
        }
        return "";
    }
}
public class InputFilter
{
    // Filter out non-relevant input or filter in relevant data

    public string Filter(string ear, string skin, string eye)
    {
        // Override me
        return "";
    }

    public AXKeyValuePair Filter(string ear)
    {
        // Override me: key = context/category, value: param
        return new AXKeyValuePair();
    }
}
public class Magic8Ball
{
    private Responder questions = new Responder();
    private Responder answers = new Responder();

    public void SetQuestions(Responder questions)
    {
        this.questions = questions;
    }

    public void SetAnswers(Responder answers)
    {
        this.answers = answers;
    }

    public Responder GetQuestions()
    {
        return questions;
    }

    public Responder GetAnswers()
    {
        return answers;
    }

    public Magic8Ball()
    {
        // Answers
        answers.AddResponse("It is certain");
        answers.AddResponse("It is decidedly so");
        answers.AddResponse("Without a doubt");
        answers.AddResponse("Yes definitely");
        answers.AddResponse("You may rely on it");
        answers.AddResponse("As I see it, yes");
        answers.AddResponse("Most likely");
        answers.AddResponse("Outlook good");
        answers.AddResponse("Yes");
        answers.AddResponse("Signs point to yes");
        answers.AddResponse("Reply hazy, try again");
        answers.AddResponse("Ask again later");
        answers.AddResponse("Better not tell you now");
        answers.AddResponse("Cannot predict now");
        answers.AddResponse("Concentrate and ask again");
        answers.AddResponse("Don’t count on it");
        answers.AddResponse("My reply is no");
        answers.AddResponse("My sources say no");
        answers.AddResponse("Outlook not so good");
        answers.AddResponse("Very doubtful");

        // Questions
        questions = new Responder("will i", "can i expect", "should i", "may i", "is it a good idea", "will it be a good idea for me to", "is it possible", "future hold", "will there be");
    }

    public bool Engage(string ear)
    {
        if (string.IsNullOrEmpty(ear))
        {
            return false;
        }
        if (questions.StrContainsResponse(ear))
        {
            return true;
        }
        return false;
    }

    public string Reply()
    {
        return answers.GetAResponse();
    }
}
public class OnOffSwitch
{
    private bool mode;
    private TimeGate timeGate;
    private Responder turnOn;
    private Responder off;

    public OnOffSwitch()
    {
        mode = false;
        timeGate = new TimeGate(5);
        turnOn = new Responder("on", "talk to me");
        off = new Responder("off", "stop", "shut up", "shut it", "whatever", "whateva");
    }

    public void SetPause(int minutes)
    {
        this.timeGate.SetPause(minutes);
    }

    public void SetOn(Responder onResponder)
    {
        this.turnOn = onResponder;
    }

    public void SetOff(Responder offResponder)
    {
        this.off = offResponder;
    }

    public bool GetMode(string ear)
    {
        if (this.turnOn.ResponsesContainsStr(ear))
        {
            this.timeGate.OpenGate();
            this.mode = true;
            return true;
        }
        else if (this.off.ResponsesContainsStr(ear))
        {
            this.timeGate.Close();
            this.mode = false;
        }
        if (this.timeGate.IsClosed())
        {
            this.mode = false;
        }
        return this.mode;
    }
}
public class RailChatBot
{
    private Dictionary<string, RefreshQ> dic = new Dictionary<string, RefreshQ>();
    private string context = "default";

    public RailChatBot()
    {
        dic.Add(context, new RefreshQ());
    }

    public void SetContext(string context)
    {
        if (!string.IsNullOrEmpty(context))
        {
            this.context = context;
        }
    }

    public string RespondMonolog(string ear)
    {
        // Monolog mode
        // Recommended use of filter for the output results
        if (string.IsNullOrEmpty(ear))
        {
            return "";
        }
        if (!dic.ContainsKey(ear))
        {
            dic.Add(ear, new RefreshQ());
        }
        string temp = dic[ear].GetRNDElement();
        if (!string.IsNullOrEmpty(temp))
        {
            context = temp;
        }
        return temp;
    }
    public void Learn(string ear)
    {
        // Use per each think cycle
        if (string.IsNullOrEmpty(ear))
        {
            return;
        }
        if (!dic.ContainsKey(ear))
        {
            dic.Add(ear, new RefreshQ());
            dic[context].Add(ear);
            context = ear;
            return;
        }
        dic[context].Add(ear);
        context = ear;
    }

    public void LearnKeyValue(string context, string reply)
    {
        // Learn questions and answers/key values
        if (!dic.ContainsKey(context))
        {
            dic.Add(context, new RefreshQ());
        }
        if (!dic.ContainsKey(reply))
        {
            dic.Add(reply, new RefreshQ());
        }
        dic[context].Add(reply);
    }

    public string Monolog()
    {
        // Succession of outputs without input involved
        return RespondMonolog(context);
    }

    public string RespondDialog(string ear)
    {
        // Dialog mode
        // Recommended use of filter for the output results
        if (string.IsNullOrEmpty(ear))
        {
            return "";
        }
        if (!dic.ContainsKey(ear))
        {
            dic.Add(ear, new RefreshQ());
        }
        string temp = dic[ear].GetRNDElement();
        return temp;
    }
    public void FeedKeyValuePairs(List<AXKeyValuePair> kvList)
    {
        if (kvList.Count == 0)
        {
            return;
        }
        foreach (AXKeyValuePair kv in kvList)
        {
            LearnKeyValue(kv.GetKey(), kv.GetValue());
        }
    }
    public void LearnV2(string ear, ElizaDeducer eliza_deducer)
    {
        FeedKeyValuePairs(eliza_deducer.Respond(ear));
        Learn(ear);
    }
}
public class SkillHubAlgDispenser
{
    // Super class to output an algorithm out of a selection of skills
    // Engage the hub with dispenseAlg and return the value to outAlg attribute
    // of the containing skill (which houses the skill hub)
    // This module enables using a selection of 1 skill for triggers instead of having the triggers engage on multiple skills
    // The method is ideal for learnability and behavioral modifications
    // Use a learnability auxiliary module as a condition to run an active skill shuffle or change method
    // (rndAlg, cycleAlg)
    // Moods can be used for specific cases to change behavior of the AGI, for example low energy state
    // For that use (moodAlg)

    private readonly List<DiSkillV2> skills = new List<DiSkillV2>();
    private int activeSkill = 0;
    private readonly Neuron tempN = new Neuron();
    private readonly Random rand = new Random();
    private Kokoro kokoro = new Kokoro(new AbsDictionaryDB());

    public SkillHubAlgDispenser(params DiSkillV2[] skillsParams)
    {
        foreach (var skill in skillsParams)
        {
            skill.SetKokoro(this.kokoro);
            skills.Add(skill);
        }
    }

    public void SetKokoro(Kokoro kokoro)
    {
        this.kokoro = kokoro;
        foreach (var skill in skills)
        {
            skill.SetKokoro(this.kokoro);
        }
    }

    public SkillHubAlgDispenser AddSkill(DiSkillV2 skill)
    {
        // Builder pattern
        skill.SetKokoro(this.kokoro);
        skills.Add(skill);
        return this;
    }

    public AlgorithmV2 DispenseAlgorithm(string ear, string skin, string eye)
    {
        // Return value to outAlg param of (external) summoner DiskillV2
        skills[activeSkill].Input(ear, skin, eye);
        skills[activeSkill].Output(tempN);
        for (int i = 1; i < 6; i++)
        {
            Algorithm temp = tempN.GetAlg(i);
            if (temp != null)
            {
                return new AlgorithmV2(i, temp);
            }
        }
        return null;
    }

    public void RandomizeActiveSkill()
    {
        activeSkill = rand.Next(skills.Count);
    }

    public void SetActiveSkillWithMood(int mood)
    {
        // Mood integer represents active skill
        // Different mood = different behavior
        if (mood > -1 && mood < skills.Count)
        {
            activeSkill = mood;
        }
    }

    public void CycleActiveSkill()
    {
        // Changes active skill
        // I recommend this method be triggered with a Learnability or SpiderSense object
        activeSkill++;
        if (activeSkill == skills.Count)
        {
            activeSkill = 0;
        }
    }

    public int GetSize()
    {
        return skills.Count;
    }
}
public class SpiderSense
{
    // Enables event prediction
    private bool spiderSense = false;
    private UniqueItemSizeLimitedPriorityQueue events = new UniqueItemSizeLimitedPriorityQueue();
    private UniqueItemSizeLimitedPriorityQueue alerts = new UniqueItemSizeLimitedPriorityQueue();
    private string prev = "";

    public SpiderSense AddEvent(string @event)
    {
        events.Add(@event);
        return this;
    }

    public void Learn(string in1)
    {
        if (alerts.Contains(in1))
        {
            spiderSense = true;
            return;
        }
        if (events.Contains(in1))
        {
            alerts.Add(prev);
            return;
        }
        prev = in1;
    }

    public bool GetSpiderSense()
    {
        bool temp = spiderSense;
        spiderSense = false;
        return temp;
    }

    public List<string> GetAlertsShallowCopy()
    {
        return alerts.GetElements();
    }

    public List<string> GetAlertsClone()
    {
        DeepCopier dc = new DeepCopier();
        return dc.DeepCopyStringList(alerts.GetElements());
    }

    public void ClearAlerts()
    {
        alerts.Clear();
    }
}
public class TimeAccumulator
{
    // Accumulator ++ each tick minutes interval
    private TimeGate timeGate = new TimeGate(5);
    private int accumulator = 0;

    public void SetTick(int tick)
    {
        timeGate.SetPause(tick);
    }

    public TimeAccumulator(int tick)
    {
        // Accumulation ticker
        this.timeGate = new TimeGate(tick);
        timeGate.OpenGate();
    }

    public int GetAccumulator()
    {
        return accumulator;
    }

    public void Tick()
    {
        if (timeGate.IsClosed())
        {
            timeGate.OpenGate();
            accumulator++;
        }
    }

    public void Reset()
    {
        accumulator = 0;
    }

    public void DecAccumulator()
    {
        if (accumulator > 0)
        {
            accumulator--;
        }
    }
}
public class TODOListManager
{
    // Manages to-do tasks.
    // q1 tasks are mentioned once and forgotten.
    // backup tasks are the memory of recently mentioned tasks.
    private UniqueItemSizeLimitedPriorityQueue q1 = new UniqueItemSizeLimitedPriorityQueue();
    private UniqueItemSizeLimitedPriorityQueue backup = new UniqueItemSizeLimitedPriorityQueue();

    public TODOListManager(int todoLim)
    {
        q1.Limit = todoLim;
        backup.Limit = todoLim;
    }

    public void AddTask(string e1)
    {
        q1.Add(e1);
    }

    public string GetTask()
    {
        string temp = q1.Poll();
        if (!string.IsNullOrEmpty(temp))
        {
            backup.Add(temp);
        }
        return temp;
    }

    public string GetOldTask()
    {
        // Task graveyard (tasks you've tackled already)
        return backup.GetRNDElement();
    }

    public void ClearAllTasks()
    {
        q1.Clear();
        backup.Clear();
    }

    public void ClearTask(string task)
    {
        q1.RemoveItem(task);
        backup.RemoveItem(task);
    }

    public bool ContainsTask(string task)
    {
        return backup.Contains(task);
    }
}
public class TrgArgue
{
    public UniqueItemSizeLimitedPriorityQueue commands = new UniqueItemSizeLimitedPriorityQueue();
    public UniqueItemSizeLimitedPriorityQueue contextCommands = new UniqueItemSizeLimitedPriorityQueue();
    private bool trgTolerance = false;
    private int counter = 0; // Count argues/requests made in succession
    // (Breaking point of argument can be established (argue till counter == N))

    public int GetCounter()
    {
        return counter;
    }

    public int EngageCommand(string s1)
    {
        // 0 -> no engagement
        // 1 -> engaged boolean gate (request made)
        // 2 -> engaged argument: consecutive request made (request in succession after a previous request)
        if (string.IsNullOrEmpty(s1))
        {
            return 0;
        }
        if (contextCommands.Contains(s1))
        {
            if (trgTolerance)
            {
                counter++;
            }
            trgTolerance = true;
            return 1;
        }
        if (trgTolerance)
        {
            if (!commands.StrContainsResponse(s1))
            {
                trgTolerance = false;
                counter = 0;
                return 0;
            }
            else
            {
                counter++;
                return 2;
            }
        }
        return 0;
    }

    public void Disable()
    {
        // Context commands are disabled until the next engagement with a command
        trgTolerance = false;
    }
}
public class TrgEveryNMinutes : TrGEV3
{
    // Trigger returns true every minutes interval, post start time
    private int minutes; // Minute interval between triggerings
    private TrgTime trgTime;
    private string timeStamp = "";

    public TrgEveryNMinutes(string startTime, int minutes)
    {
        this.minutes = minutes;
        this.timeStamp = startTime;
        trgTime = new TrgTime();
        trgTime.SetTime(startTime);
    }

    public void SetMinutes(int minutes)
    {
        if (minutes > -1)
        {
            this.minutes = minutes;
        }
    }

    public new bool Trigger()
    {
        if (trgTime.CheckAlarm())
        {
            timeStamp = TimeUtils.GetFutureInXMin(minutes);
            trgTime.SetTime(timeStamp);
            return true;
        }
        return false;
    }

    public new void Reset()
    {
        timeStamp = TimeUtils.GetCurrentTimeStamp();
    }
}
public class TrgMinute : TrGEV3
{
    // Trigger returns true at minute once per hour
    private int hour1 = -1;
    private int minute;
    private Random rand = new Random();

    public TrgMinute()
    {
        minute = rand.Next(60);
    }

    public TrgMinute(int minute)
    {
        this.minute = minute;
    }

    public new bool Trigger()
    {
        int tempHour = TimeUtils.GetHoursAsInt();
        if (tempHour != hour1)
        {
            if (TimeUtils.GetMinutesAsInt() == minute)
            {
                hour1 = tempHour;
                return true;
            }
        }
        return false;
    }

    public new void Reset()
    {
        hour1 = -1;
    }
}
public class TrgSnooze : TrGEV3
{
    // This boolean gate will return true per minute interval
    // max repeats times.
    private int repeats = 0;
    private int maxrepeats; // 2 recommended
    private bool snooze = true;
    private int snoozeInterval = 5;

    public TrgSnooze(int maxrepeats)
    {
        this.maxrepeats = maxrepeats;
    }

    public void SetSnoozeInterval(int snoozeInterval)
    {
        if (snoozeInterval > 1 && snoozeInterval < 11)
        {
            this.snoozeInterval = snoozeInterval;
        }
    }

    public void SetMaxrepeats(int maxrepeats)
    {
        this.maxrepeats = maxrepeats;
        Reset();
    }

    public new void Reset()
    {
        // Refill trigger
        // Engage this code when an alarm clock was engaged to enable snoozing
        repeats = maxrepeats;
    }

    public new bool Trigger()
    {
        // Trigger a snooze alarm?
        int minutes = TimeUtils.GetMinutesAsInt();
        if (minutes % snoozeInterval != 0)
        {
            snooze = true;
            return false;
        }
        if (repeats > 0 && snooze)
        {
            snooze = false;
            repeats--;
            return true;
        }
        return false;
    }

    public void Disable()
    {
        // Engage this method to stop the snoozing
        repeats = 0;
    }
}
public class AXContextCmd
{
    // Engage on commands
    // When commands are engaged, context commands can also engage
    public UniqueItemSizeLimitedPriorityQueue commands = new UniqueItemSizeLimitedPriorityQueue();
    public UniqueItemSizeLimitedPriorityQueue contextCommands = new UniqueItemSizeLimitedPriorityQueue();
    public bool trgTolerance = false;

    public bool EngageCommand(string s1)
    {
        if (string.IsNullOrEmpty(s1))
        {
            return false;
        }
        if (contextCommands.Contains(s1))
        {
            trgTolerance = true;
            return true;
        }
        if (trgTolerance && !commands.Contains(s1))
        {
            trgTolerance = false;
            return false;
        }
        return trgTolerance;
    }

    public void Disable()
    {
        // Context commands are disabled till next engagement with a command
        trgTolerance = false;
    }
}
public class AXConvince
{
    private AXContextCmd req;
    private Responder reset = new Responder("reset");
    private int min = 3; // Minimum requests till agreement
    private int max = 6;
    private DrawRnd rnd = new DrawRnd();
    private int counter = 0;
    private bool mode = false;

    public AXConvince(AXContextCmd req)
    {
        this.req = req;
    }

    public int Engage(string ear)
    {
        // 0: nothing, 1: no, 2: yes, 3: just been reset to no again
        if (reset.ResponsesContainsStr(ear))
        {
            counter = 0;
            mode = false;
            min += rnd.GetSimpleRNDNum(max);
            return 3;
        }
        if (req.EngageCommand(ear))
        {
            counter++;
            if (counter < min)
            {
                return 1;
            }
            else
            {
                mode = true;
                return 2; // Convinced
            }
        }
        return 0;
    }

    public bool IsConvinced()
    {
        return mode;
    }
}
public class ChangeDetector
{
    private string A;
    private string B;
    private int prev = -1;

    public ChangeDetector(string a, string b)
    {
        A = a;
        B = b;
    }

    public int DetectChange(string ear)
    {
        // a->b return 2; b->a return 1; else return 0
        if (string.IsNullOrEmpty(ear))
        {
            return 0;
        }

        int current = -1;
        if (ear.Contains(A))
        {
            current = 1;
        }
        else if (ear.Contains(B))
        {
            current = 2;
        }
        else
        {
            return 0;
        }

        int result = 0;
        if (current == 1 && prev == 2)
        {
            result = 1;
        }
        if (current == 2 && prev == 1)
        {
            result = 2;
        }

        prev = current;
        return result;
    }
}
public class ElizaDeducer
{
    public List<PhraseMatcher> babble2 = new List<PhraseMatcher>();

    public ElizaDeducer()
    {
        // init values in subclass
        // see ElizaDeducerInitializer for example
        // example input ountput based on ElizaDeducerInitializer values :
        // elizaDeducer.respond("a is a b")
        // [what is a a;a is a b, explain a;a is a b]
    }

    public List<AXKeyValuePair> Respond(string msg)
    {
        foreach (PhraseMatcher pm in babble2)
        {
            if (pm.Matches(msg))
            {
                return pm.Respond(msg);
            }
        }
        return new List<AXKeyValuePair>();
    }

    public class PhraseMatcher
    {
        public readonly Regex matcher;
        public readonly List<AXKeyValuePair> responses;

        public PhraseMatcher(string matcher, List<AXKeyValuePair> responses)
        {
            this.matcher = new Regex(matcher);
            this.responses = responses;
        }

        public bool Matches(string str)
        {
            Match m = matcher.Match(str);
            return m.Success;
        }

        public List<AXKeyValuePair> Respond(string str)
        {
            Match m = matcher.Match(str);
            if (m.Success)
            {
                List<AXKeyValuePair> result = new List<AXKeyValuePair>();
                int tmp = m.Groups.Count;
                foreach (AXKeyValuePair kv in responses)
                {
                    AXKeyValuePair tempKV = new AXKeyValuePair(kv.GetKey(), kv.GetValue());
                    for (int i = 0; i < tmp - 1; i++)
                    {
                        string s = m.Groups[i + 1].Value;
                        tempKV.SetKey(tempKV.GetKey().Replace("{" + i + "}", s).ToLower());
                        tempKV.SetValue(tempKV.GetValue().Replace("{" + i + "}", s).ToLower());
                    }
                    result.Add(tempKV);
                }
                return result;
            }
            return new List<AXKeyValuePair>();
        }
    }
}
public class ElizaDeducerInitializer : ElizaDeducer
{
    public ElizaDeducerInitializer()
    {
        List<PhraseMatcher> babbleTmp = new List<PhraseMatcher>();
        List<AXKeyValuePair> kvs = new List<AXKeyValuePair>();
        kvs.Add(new AXKeyValuePair("what is a {0}", "{0} is a {1}"));
        kvs.Add(new AXKeyValuePair("explain {0}", "{0} is a {1}"));
        babbleTmp.Add(new PhraseMatcher("(.*) is a (.*)", kvs));
        babble2 = babbleTmp;
    }
}
public class Excluder
{
    private readonly List<string> startsWith = new List<string>();
    private readonly List<string> endsWith = new List<string>();

    public void AddStartsWith(string s1)
    {
        if (!startsWith.Contains($"^({s1}).*"))
        {
            startsWith.Add($"^({s1}).*");
        }
    }

    public void AddEndsWith(string s1)
    {
        if (!endsWith.Contains($"(.*)(?={s1})"))
        {
            endsWith.Add($"(.*)(?={s1})");
        }
    }

    public bool Exclude(string ear)
    {
        // Assuming RegexUtil is defined elsewhere
        RegexUtil r1 = new RegexUtil();

        foreach (string tempStr in startsWith)
        {
            if (r1.ExtractRegex(tempStr, ear).Length > 0)
            {
                return true;
            }
        }

        foreach (string tempStr in endsWith)
        {
            if (r1.ExtractRegex(tempStr, ear).Length > 0)
            {
                return true;
            }
        }

        return false;
    }
}
public class TimedMessages
{
    public Dictionary<string, string> Messages { get; } = new Dictionary<string, string>();
    private string lastMSG = "nothing";
    private bool msg = false;

    public void AddMSG(string ear)
    {
        RegexUtil ru1 = new RegexUtil();
        string tempMSG = ru1.ExtractRegex("(?<=remind me to).*?(?=at)", ear);
        if (string.IsNullOrEmpty(tempMSG))
        {
            return;
        }
        string timeStamp = ru1.ExtractRegex(EnumRegexGrimoire.simpleTimeStamp, ear);
        if (string.IsNullOrEmpty(timeStamp))
        {
            return;
        }
        Messages[timeStamp] = tempMSG;
    }

    public void AddMSGV2(string timeStamp, string msg)
    {
        Messages[timeStamp] = msg;
    }

    public void SprinkleMSG(string msg, int amount)
    {
        for (int i = 0; i < amount; i++)
        {
            Messages[GenerateRandomTimestamp()] = msg;
        }
    }

    public static string GenerateRandomTimestamp()
    {
        Random random = new Random();
        int minutes = random.Next(60);
        string m = minutes > 9 ? minutes.ToString() : $"0{minutes}";
        int hours = random.Next(12);
        return hours > 9 ? $"{hours}:{m}" : $"0{hours}:{m}";
    }

    public void Clear()
    {
        Messages.Clear();
    }

    public void Tick()
    {
        string now = TimeUtils.GetCurrentTimeStamp();
        if (Messages.ContainsKey(now))
        {
            if (!lastMSG.Equals(Messages[now]))
            {
                lastMSG = Messages[now];
                msg = true;
            }
        }
    }

    public string GetLastMSG()
    {
        msg = false;
        return lastMSG;
    }

    public bool GetMsg()
    {
        return msg;
    }
}
public class AlgorithmV2
{
    private int priority = 4;
    private Algorithm alg = null;

    public AlgorithmV2(int priority, Algorithm alg)
    {
        this.priority = priority;
        this.alg = alg;
    }

    public int GetPriority()
    {
        return priority;
    }

    public void SetPriority(int priority)
    {
        this.priority = priority;
    }

    public Algorithm GetAlg()
    {
        return alg;
    }

    public void SetAlg(Algorithm alg)
    {
        this.alg = alg;
    }
}
public class AXSkillBundle
{
    private List<DiSkillV2> skills = new List<DiSkillV2>();
    private Neuron tempN = new Neuron();
    private Kokoro kokoro = new Kokoro(new AbsDictionaryDB());

    public void SetKokoro(Kokoro kokoro)
    {
        this.kokoro = kokoro;
        foreach (DiSkillV2 skill in skills)
        {
            skill.SetKokoro(this.kokoro);
        }
    }

    public AXSkillBundle(params DiSkillV2[] skillsParams)
    {
        foreach (DiSkillV2 skill in skillsParams)
        {
            skill.SetKokoro(this.kokoro);
            skills.Add(skill);
        }
    }

    public AXSkillBundle AddSkill(DiSkillV2 skill)
    {
        // Builder pattern
        skill.SetKokoro(this.kokoro);
        skills.Add(skill);
        return this;
    }

    public AlgorithmV2 DispenseAlgorithm(string ear, string skin, string eye)
    {
        foreach (DiSkillV2 skill in skills)
        {
            skill.Input(ear, skin, eye);
            skill.Output(tempN);
            for (int j = 1; j <= 5; j++)
            {
                Algorithm temp = tempN.GetAlg(j);
                if (temp != null)
                {
                    return new AlgorithmV2(j, temp);
                }
            }
        }
        return null;
    }

    public int GetSize()
    {
        return skills.Count;
    }
}
