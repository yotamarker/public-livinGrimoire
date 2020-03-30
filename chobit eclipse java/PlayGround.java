package chobit;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

// times and stuff
public class PlayGround {
    // int foo = Integer.parseInt(myString);
    public String getCurrentTimeStamp() {
        // SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//
        // dd/MM/yyyy
        SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm");// dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }

    public String getMinutes() {
        // SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//
        // dd/MM/yyyy
        SimpleDateFormat sdfDate = new SimpleDateFormat("mm");// dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }

    public String getSeconds() {
        // SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//
        // dd/MM/yyyy
        SimpleDateFormat sdfDate = new SimpleDateFormat("ss");// dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }

    public String getDayOfDWeek() {
        Date now = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(now);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        return convertToDay(dayOfWeek);
    }

    private String convertToDay(Integer d) {
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

    public String getSpecificTime(enumTimes timeType) {
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
        String strDate = sdfDate.format(now);
        return strDate;
    }

    public int getSecondsAsInt() {
        // SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//
        // dd/MM/yyyy
        SimpleDateFormat sdfDate = new SimpleDateFormat("ss");// dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return Integer.parseInt(strDate);
    }

    public int getMinutesAsInt() {
        // SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//
        // dd/MM/yyyy
        SimpleDateFormat sdfDate = new SimpleDateFormat("mm");// dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return Integer.parseInt(strDate);
    }

    public int getHoursAsInt() {
        // SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//
        // dd/MM/yyyy
        SimpleDateFormat sdfDate = new SimpleDateFormat("HH");// dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return Integer.parseInt(strDate);
    }

	public String getFutureInXMin(int x) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, x);
		SimpleDateFormat df = new SimpleDateFormat("HH:mm");
		return df.format(cal.getTime());
	}

	public int getFutureHour(int startHour, int addedHours) {
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

	public String translateMonth(int month1) {
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

	public String translateMonthDay(int day1) {
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

	public String timeInXMinutes(int x) {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MINUTE, x);
		return now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE);
	}
}
