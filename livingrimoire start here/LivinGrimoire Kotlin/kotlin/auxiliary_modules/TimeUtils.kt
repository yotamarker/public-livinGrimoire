package auxiliary_modules

import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

// times and stuff
class TimeUtils {
    companion object {
        val currentTimeStamp: String
            // int foo = Integer.parseInt(myString);
            get() {
                // SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//
                // dd/MM/yyyy
                val sdfDate = SimpleDateFormat("HH:mm") // dd/MM/yyyy
                val now = Date()
                return sdfDate.format(now)
            }
        val monthAsInt: Int
            get() = Calendar.getInstance()[Calendar.MONTH]
        private val dayOfTheMonthAsInt: Int
            get() = Calendar.getInstance()[Calendar.DAY_OF_MONTH]
        val yearAsInt: Int
            get() = Calendar.getInstance()[Calendar.YEAR]
        val dayAsInt: Int
            get() = Calendar.getInstance()[Calendar.DAY_OF_WEEK]
        val minutes: String
            get() {
                // SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//
                // dd/MM/yyyy
                val sdfDate = SimpleDateFormat("mm") // dd/MM/yyyy
                val now = Date()
                return sdfDate.format(now)
            }
        val seconds: String
            get() {
                // SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//
                // dd/MM/yyyy
                val sdfDate = SimpleDateFormat("ss") // dd/MM/yyyy
                val now = Date()
                return sdfDate.format(now)
            }
        val dayOfDWeek: String
            get() {
                val now = Date()
                val c = Calendar.getInstance()
                c.time = now
                val dayOfWeek = c[Calendar.DAY_OF_WEEK]
                return convertToDay(dayOfWeek)
            }

        private fun convertToDay(d: Int): String {
            var result = ""
            when (d) {
                1 -> result = "sunday"
                2 -> result = "monday"
                3 -> result = "tuesday"
                4 -> result = "wednesday"
                5 -> result = "thursday"
                6 -> result = "friday"
                7 -> result = "saturday"
                else -> {}
            }
            return result
        }

        fun getSpecificTime(timeType: enumTimes): String {
            // SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//
            // dd/MM/yyyy
            val sdfDate: SimpleDateFormat
            var format = ""
            when (timeType) {
                enumTimes.DATE -> {
                    val cal = Calendar.getInstance()
                    val dayOfMonth = cal[Calendar.DAY_OF_MONTH]
                    val month = cal[Calendar.MONTH]
                    val year = cal[Calendar.YEAR]
                    return translateMonthDay(dayOfMonth) + " " + translateMonth(month + 1) + " " + year
                }

                enumTimes.HOUR -> format = "HH"
                enumTimes.SECONDS -> format = "ss"
                enumTimes.MINUTES -> format = "mm"
                enumTimes.YEAR -> format = "yyyy"
                else -> {}
            }
            sdfDate = SimpleDateFormat(format)
            val now = Date()
            return sdfDate.format(now)
        }

        val secondsAsInt: Int
            get() {
                // SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//
                // dd/MM/yyyy
                val sdfDate = SimpleDateFormat("ss") // dd/MM/yyyy
                val now = Date()
                val strDate = sdfDate.format(now)
                return strDate.toInt()
            }
        val minutesAsInt: Int
            get() {
                // SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//
                // dd/MM/yyyy
                val sdfDate = SimpleDateFormat("mm") // dd/MM/yyyy
                val now = Date()
                val strDate = sdfDate.format(now)
                return strDate.toInt()
            }
        val hoursAsInt: Int
            get() {
                // SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//
                // dd/MM/yyyy
                val sdfDate = SimpleDateFormat("HH") // dd/MM/yyyy
                val now = Date()
                val strDate = sdfDate.format(now)
                return strDate.toInt()
            }

        fun getFutureInXMin(x: Int): String {
            val cal = Calendar.getInstance()
            cal.add(Calendar.MINUTE, x)
            val df = SimpleDateFormat("HH:mm")
            return df.format(cal.time)
        }

        fun getPastInXMin(x: Int): String {
            val cal = Calendar.getInstance()
            cal.add(Calendar.MINUTE, -1 * x)
            val df = SimpleDateFormat("HH:mm")
            return df.format(cal.time)
        }

        fun getFutureHour(startHour: Int, addedHours: Int): Int {
            return (startHour + addedHours) % 25
        }

        fun getFutureFromXInYMin(x: Int, y: String?): String {
            val cal = Calendar.getInstance()
            val sdf = SimpleDateFormat("HH:mm")
            try {
                cal.time = sdf.parse(y)
            } catch (e: ParseException) {
                // TODO Auto-generated catch block
                e.printStackTrace()
            } // all done
            cal.add(Calendar.MINUTE, x)
            return sdf.format(cal.time)
        }

        fun translateMonth(month1: Int): String {
            var dMonth = ""
            when (month1) {
                1 -> dMonth = "January"
                2 -> dMonth = "February"
                3 -> dMonth = "March"
                4 -> dMonth = "April"
                5 -> dMonth = "May"
                6 -> dMonth = "June"
                7 -> dMonth = "July"
                8 -> dMonth = "August"
                9 -> dMonth = "September"
                10 -> dMonth = "October"
                11 -> dMonth = "November"
                12 -> dMonth = "December"
                else -> {}
            }
            return dMonth
        }

        private fun translateMonthDay(day1: Int): String {
            var dday = ""
            when (day1) {
                1 -> dday = "first_of"
                2 -> dday = "second_of"
                3 -> dday = "third_of"
                4 -> dday = "fourth_of"
                5 -> dday = "fifth_of"
                6 -> dday = "sixth_of"
                7 -> dday = "seventh_of"
                8 -> dday = "eighth_of"
                9 -> dday = "nineth_of"
                10 -> dday = "tenth_of"
                11 -> dday = "eleventh_of"
                12 -> dday = "twelveth_of"
                13 -> dday = "thirteenth_of"
                14 -> dday = "fourteenth_of"
                15 -> dday = "fifteenth_of"
                16 -> dday = "sixteenth_of"
                17 -> dday = "seventeenth_of"
                18 -> dday = "eighteenth_of"
                19 -> dday = "nineteenth_of"
                20 -> dday = "twentyth_of"
                21 -> dday = "twentyfirst_of"
                22 -> dday = "twentysecond_of"
                23 -> dday = "twentythird_of"
                24 -> dday = "twentyfourth_of"
                25 -> dday = "twentyfifth_of"
                26 -> dday = "twentysixth_of"
                27 -> dday = "twentyseventh_of"
                28 -> dday = "twentyeighth_of"
                29 -> dday = "twentynineth_of"
                30 -> dday = "thirtyth_of"
                31 -> dday = "thirtyfirst_of"
                else -> {}
            }
            return dday
        }

        fun timeInXMinutes(x: Int): String {
            val now = Calendar.getInstance()
            now.add(Calendar.MINUTE, x)
            return now[Calendar.HOUR_OF_DAY].toString() + ":" + now[Calendar.MINUTE]
        }

        val isDayTime: Boolean
            get() {
                val hour = hoursAsInt
                return hour in 6..18
            }

        fun partOfDay(): String {
            val hour = hoursAsInt
            if (smallToBig(5, hour, 12)) {
                return "morning"
            }
            if (smallToBig(11, hour, 17)) {
                return "afternoon"
            }
            return if (smallToBig(16, hour, 21)) {
                "evening"
            } else "night"
        }

        val isNight: Boolean
            get() {
                val hour = hoursAsInt
                return hour > 20 || hour < 6
            }

        private fun smallToBig(vararg a: Int): Boolean // return true if input nums decend in value
        {
            for (i in 0 until a.size - 1) {
                if (a[i] >= a[i + 1]) {
                    return false
                }
            }
            return true
        }

        val tomorrow: String
            get() {
                val now = Date()
                val c = Calendar.getInstance()
                c.time = now
                val dayOfWeek = c[Calendar.DAY_OF_WEEK]
                return if (dayOfWeek == 7) {
                    "sunday"
                } else convertToDay(dayOfWeek + 1)
            }
        val yesterday: String
            get() {
                val now = Date()
                val c = Calendar.getInstance()
                c.time = now
                val dayOfWeek = c[Calendar.DAY_OF_WEEK]
                return if (dayOfWeek == 1) {
                    "saturday"
                } else convertToDay(dayOfWeek - 1)
            }
        val gMT: Int
            get() {
                val now = Calendar.getInstance()

                // get current TimeZone using getTimeZone method of Calendar class
                val timeZone = now.timeZone

                // display current TimeZone using getDisplayName() method of TimeZone class
                val x = if (TimeZone.getDefault().inDaylightTime(Date())) 1 else 0
                return timeZone.rawOffset / 3600000 + x
            }
        val local: String
            get() {
                val now = Calendar.getInstance()

                // get current TimeZone using getTimeZone method of Calendar class
                val timeZone = now.timeZone

                // display current TimeZone using getDisplayName() method of TimeZone class
                return "Current TimeZone is : " + timeZone.displayName
            }

        private fun findDay(month: Int, day: Int, year: Int): String {
            // get weekday from date
            if (day > 31) {
                return ""
            }
            if (day > 30) {
                if (month == 4 || month == 6 || month == 9 || month == 11) {
                    return ""
                }
            }
            if (month == 2) {
                if (isLeapYear(yearAsInt)) {
                    if (day > 29) {
                        return ""
                    }
                }
                if (day > 28) {
                    return ""
                }
            }
            val localDate = LocalDate.of(year, month, day)
            val dayOfWeek = localDate.dayOfWeek
            return dayOfWeek.toString().lowercase(Locale.getDefault())
        }

        fun nxtDayOnDate(dayOfMonth: Int): String {
            // get the weekday on the next dayOfMonth
            val today = dayOfTheMonthAsInt
            if (today <= dayOfMonth) {
                return findDay(monthAsInt + 1, dayOfMonth, yearAsInt)
            } else if (monthAsInt != 11) { // december?
                return findDay(monthAsInt + 2, dayOfMonth, yearAsInt)
            }
            return findDay(1, dayOfMonth, yearAsInt + 1)
        }

        private fun isLeapYear(year: Int): Boolean {

            // divisible by 4
            val isLeapYear: Boolean = year % 4 == 0

            // divisible by 4, not by 100, or divisible by 400
            return isLeapYear && (year % 100 != 0 || year % 400 == 0)
        }

        val currentMonthName: String
            get() = translateMonth(monthAsInt + 1)
        val currentMonthDay: String
            get() = translateMonthDay(dayOfTheMonthAsInt)
    }
}