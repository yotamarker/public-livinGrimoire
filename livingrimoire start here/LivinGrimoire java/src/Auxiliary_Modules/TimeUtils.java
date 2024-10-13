package Auxiliary_Modules;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

// times and stuff
public class TimeUtils {
    // int foo = Integer.parseInt(myString);
    public static String getCurrentTimeStamp() {
        // SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//
        // dd/MM/yyyy
        SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm");// dd/MM/yyyy
        Date now = new Date();
        return sdfDate.format(now);
    }

    public static int getMonthAsInt() {
        return Calendar.getInstance().get(Calendar.MONTH);
    }

    public static int getDayOfTheMonthAsInt() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    public static int getYearAsInt() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }
    public static int getDayAsInt() {
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
    }
    public static String getMinutes() {
        // SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//
        // dd/MM/yyyy
        SimpleDateFormat sdfDate = new SimpleDateFormat("mm");// dd/MM/yyyy
        Date now = new Date();
        return sdfDate.format(now);
    }

    public static String getSeconds() {
        // SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//
        // dd/MM/yyyy
        SimpleDateFormat sdfDate = new SimpleDateFormat("ss");// dd/MM/yyyy
        Date now = new Date();
        return sdfDate.format(now);
    }

    public static String getDayOfDWeek() {
        Date now = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(now);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        return convertToDay(dayOfWeek);
    }

    private static String convertToDay(Integer d) {
        String result = "";
        switch (d) {
            case 1:
                result = "sunday";
                break;
            case 2:
                result = "monday";
                break;
            case 3:
                result = "tuesday";
                break;
            case 4:
                result = "wednesday";
                break;
            case 5:
                result = "thursday";
                break;
            case 6:
                result = "friday";
                break;
            case 7:
                result = "saturday";
                break;
            default:
                break;
        }
        return result;
    }

    public static String getSpecificTime(enumTimes timeType) {
        // SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//
        // dd/MM/yyyy
        SimpleDateFormat sdfDate;
        String format = "";
        switch (timeType) {
            case DATE:
                Calendar cal = Calendar.getInstance();
                int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
                int Month = cal.get(Calendar.MONTH);
                int year = cal.get(Calendar.YEAR);
                return translateMonthDay(dayOfMonth) + " " + translateMonth(Month + 1) + " " + year;
            case HOUR:
                format = "HH";
                break;
            case SECONDS:
                format = "ss";
                break;
            case MINUTES:
                format = "mm";
                break;
            case YEAR:
                format = "yyyy";
                break;
            default:
                break;
        }
        sdfDate = new SimpleDateFormat(format);
        Date now = new Date();
        return sdfDate.format(now);
    }

    public static int getSecondsAsInt() {
        // SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//
        // dd/MM/yyyy
        SimpleDateFormat sdfDate = new SimpleDateFormat("ss");// dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return Integer.parseInt(strDate);
    }

    public static int getMinutesAsInt() {
        // SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//
        // dd/MM/yyyy
        SimpleDateFormat sdfDate = new SimpleDateFormat("mm");// dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return Integer.parseInt(strDate);
    }

    public static int getHoursAsInt() {
        // SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//
        // dd/MM/yyyy
        SimpleDateFormat sdfDate = new SimpleDateFormat("HH");// dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return Integer.parseInt(strDate);
    }

    public static String getFutureInXMin(int x) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, x);
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        return df.format(cal.getTime());
    }

    public static String getPastInXMin(int x) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, -1 * x);
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        return df.format(cal.getTime());
    }

    public static int getFutureHour(int startHour, int addedHours) {
        return (startHour + addedHours) % 25;
    }
    public String getFutureFromXInYMin(int x, String y) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        try {
            cal.setTime(sdf.parse(y));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } // all done
        cal.add(Calendar.MINUTE, x);
        return sdf.format(cal.getTime());
    }

    public static String translateMonth(int month1) {
        String dMonth = "";
        switch (month1) {
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
                break;
        }
        return dMonth;
    }

    public static String translateMonthDay(int day1) {
        String dday = "";
        switch (day1) {
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
                dday = "sixth_of";
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
                break;
        }
        return dday;
    }

    public static String timeInXMinutes(int x) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MINUTE, x);
        return now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE);
    }

    public static Boolean isDayTime() {
        int hour = getHoursAsInt();
        return hour > 5 && hour < 19;
    }

    public static String partOfDay() {
        int hour = getHoursAsInt();
        if (smallToBig(5, hour, 12)) {
            return "morning";
        }
        if (smallToBig(11, hour, 17)) {
            return "afternoon";
        }
        if (smallToBig(16, hour, 21)) {
            return "evening";
        }
        return "night";
    }

    public static Boolean isNight() {
        int hour = getHoursAsInt();
        return hour>20||hour<6;
    }
    public static Boolean smallToBig(int... a)
    // return true if input nums decend in value
    {
        for (int i = 0; i < a.length - 1; i++) {
            if (!(a[i] < a[i + 1])) {
                return false;
            }
        }
        return true;
    }

    public static String getTomorrow() {
        Date now = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(now);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 7) {
            return "sunday";
        }
        return convertToDay(dayOfWeek + 1);
    }

    public static String getYesterday() {
        Date now = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(now);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1) {
            return "saturday";
        }
        return convertToDay(dayOfWeek - 1);
    }
    public static int getGMT() {
        Calendar now = Calendar.getInstance();
        // display current TimeZone using getDisplayName() method of TimeZone class
        int x = TimeZone.getDefault().inDaylightTime(new Date()) ? 1 : 0;
        return now.getTimeZone().getRawOffset() / 3600000 + x;
    }

    public static String getLocal() {
        Calendar now = Calendar.getInstance();

        // get current TimeZone using getTimeZone method of Calendar class
        TimeZone timeZone = now.getTimeZone();

        // display current TimeZone using getDisplayName() method of TimeZone class
        return "Current TimeZone is : " + timeZone.getDisplayName();
    }
    public static String findDay(int month, int day, int year) {
        // get weekday from date
        if (day > 31){
            return "";
        }
        if (day > 30){
            if ((month == 4)||(month == 6)||(month == 9)||(month == 11)){return "";}
        }
        if(month == 2){
            if(isLeapYear(getYearAsInt())){
                if (day > 29){
                    return "";
                }
            }
            if(day > 28){
                return "";
            }
        }
        LocalDate localDate = LocalDate.of(year, month, day);
        java.time.DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        return dayOfWeek.toString().toLowerCase();
    }
    public static String nxtDayOnDate(int dayOfMonth){
        // get the weekday on the next dayOfMonth
        int today = getDayOfTheMonthAsInt();
        if (today <= dayOfMonth){
            return findDay(getMonthAsInt() + 1,dayOfMonth,getYearAsInt());
        } else if (!(getMonthAsInt() == 11)) { // december?
            return findDay(getMonthAsInt() + 2,dayOfMonth,getYearAsInt());
        }
        return findDay(1,dayOfMonth,getYearAsInt()+1);
    }
    public static Boolean isLeapYear(int year){
        boolean isLeapYear;

        // divisible by 4
        isLeapYear = (year % 4 == 0);

        // divisible by 4, not by 100, or divisible by 400
        return isLeapYear && (year % 100 != 0 || year % 400 == 0);
    }
    public static String getCurrentMonthName(){
        return translateMonth(getMonthAsInt()+1);
    }
    public static String getCurrentMonthDay(){
        return translateMonthDay(getDayOfTheMonthAsInt());
    }
}
