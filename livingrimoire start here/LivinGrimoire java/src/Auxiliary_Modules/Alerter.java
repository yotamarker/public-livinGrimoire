package Auxiliary_Modules;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Alerter {
    /*
     * the class manages reminders. here reminders are reffered to as spirit
     * bullets.
     *
     * loadReminder(String) : this func has several roles :
     *
     * 1 to input a reminder. examples : remind me to eat at 11:30 remind me to
     * exercise at 18:00 on sunday
     *
     * 2 to forget the reminders : clear reminders
     *
     * 3 reveal the next reminder compared to the time
     *
     * 4 recycle a reminder. normally a reminder gets deleted after it is fired up
     * unless you loadBullet("thank you"), this will reload the reminder.
     *
     * 5 attempting to add a reminder beyond the msgLim will result in the output :
     * too many reminders.
     *
     * loadBullet :
     *
     * this outputs the requested query or the reminder if its time has come. it
     * uses a timegate to prevent a reminder from firing up more than once.
     *
     */
    private TimeGate timeGate = new TimeGate(1);
    private ArrayList<AlerterMsg> msgs = new ArrayList<>();
    private AlerterMsg activeReminder = new AlerterMsg();
    private int cmd = 0;
    private int msgLim = 4;

    private String translateTimes(String ear) {
        String result = ear;
        result = ear.replace("1 on the clock", "1:00");
        result = ear.replace("2 on the clock", "2:00");
        result = ear.replace("3 on the clock", "3:00");
        result = ear.replace("4 on the clock", "4:00");
        result = ear.replace("5 on the clock", "5:00");
        result = ear.replace("6 on the clock", "6:00");
        result = ear.replace("7 on the clock", "7:00");
        result = ear.replace("8 on the clock", "8:00");
        result = ear.replace("9 on the clock", "9:00");
        result = ear.replace("10 on the clock", "10:00");
        result = ear.replace("11 on the clock", "11:00");
        result = ear.replace("12 on the clock", "12:00");
        result = ear.replace("13 on the clock", "13:00");
        result = ear.replace("14 on the clock", "14:00");
        result = ear.replace("15 on the clock", "15:00");
        result = ear.replace("16 on the clock", "16:00");
        result = ear.replace("17 on the clock", "17:00");
        result = ear.replace("18 on the clock", "18:00");
        result = ear.replace("19 on the clock", "19:00");
        result = ear.replace("20 on the clock", "20:00");
        result = ear.replace("21 on the clock", "21:00");
        result = ear.replace("22 on the clock", "22:00");
        result = ear.replace("23 on the clock", "23:00");
        result = ear.replace("24 on the clock", "24:00");
        return result;
    }
    public int getMsgLim() {
        return msgLim;
    }

    public void setMsgLim(int msgLim) {
        this.msgLim = msgLim;
    }
    public void loadBullet(String ear) {
        if (ear.contains("clear reminders")) {
            cmd = 6;
            msgs.clear();
            return;
        }
        if (ear.contains("next reminder")) {
            cmd = 1;
            return;
        }
        if (!timeGate.isClosed() && activeReminder.isActive()) {
            if (ear.contains("thank you")) {
                msgs.add(this.activeReminder);
                activeReminder = new AlerterMsg();
                cmd = 2;
            }
        }
        String ear2 = RegexUtil.extractRegex("(.*)(?=on)", ear);
        if (ear2.isEmpty()) {
            ear2 = ear;
        }
        String msg = RegexUtil.extractRegex("(?<=remind me to)(.*)(?=at)", ear2);
        if (msg.isEmpty()) {
            return;
        }
        if (msgs.size() > msgLim) {
            cmd = 3;
            return;
        }
        String temp2 = translateTimes(ear2);
        String time = RegexUtil.extractRegex(enumRegexGrimoire.simpleTimeStamp,temp2);
        if (RegexUtil.extractRegex("(^([0-9]|[0-1][0-9]|[2][0-3]):([0-5][0-9])$)|(^([0-9]|[1][0-9]|[2][0-3])$)", time)
                .isEmpty()) {
            return;
        }
        if (time.isEmpty()) {
            return;
        }

        AlerterMsg alerterMsg = new AlerterMsg();
        alerterMsg
                .setDay(strContains(ear, "sunday", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday"));
        if (timeUsed(time, alerterMsg.getDay())) {
            return;
        }
        alerterMsg.setTime(time);
        alerterMsg.setMsg(msg);
        // sort the list
        msgs.add(alerterMsg);
        cmd = 5;
        Collections.sort(msgs, new SortByDate());
    }

    private Boolean timeUsed(String t1, String day1) {
        if (msgs.size() > msgLim) {
            cmd = 3;
            return true;
        }
        for (AlerterMsg alerterMsg : msgs) {
            if (alerterMsg.getTime().equals(t1) && alerterMsg.getDay().equals(day1)) {
                cmd = 4;
                return true;
            }
        }
        return false;
    }
    public String loadBullet() {
        switch (cmd) {
            case 1:
                cmd = 0;
                if (msgs.isEmpty()) {
                    return "no reminders";
                }
                AlerterMsg alerterMsgTmp = new AlerterMsg();
                alerterMsgTmp.setDay(TimeUtils.getDayOfDWeek());
                alerterMsgTmp.setTime(TimeUtils.getHoursAsInt() + ":" + TimeUtils.getMinutes());
                SortByDate sortByDateTmp = new SortByDate();
                for (int counter = 0; counter < msgs.size(); counter++) {
                    if (sortByDateTmp.compare(alerterMsgTmp, msgs.get(counter)) == -1) {
                        return msgs.get(counter).toString();
                    }
                }
                return msgs.get(0).toString();
            case 2:
                cmd = 0;
                return "you are welcome";
            case 3:
                cmd = 0;
                return "too many reminders";
            case 4:
                cmd = 0;
                return "a reminder already exists for that time";
            case 5:
                cmd = 0;
                return "yes my majesty";
            case 6:
                cmd = 0;
                return "reminders cleared";
            default:
                break;
        }
        if (!timeGate.isClosed()) {
            return "";
        }
        String tNow = TimeUtils.getHoursAsInt() + ":" + TimeUtils.getMinutes();
        for (AlerterMsg alerterMsg : msgs) {
            if (alerterMsg.getDay().equals(TimeUtils.getDayOfDWeek()) || alerterMsg.getDay().isEmpty()) {
                if (tNow.equals(alerterMsg.getTime())) {
                    timeGate.openGate();
                    this.activeReminder = alerterMsg;
                    msgs.remove(this.activeReminder);
                    return alerterMsg.getMsg();
                }
            }
        }
        return "";
    }

    private static String strContains(String str1, String... a) {
        for (String temp : a) {
            if (str1.contains(temp)) {
                return temp;
            }
        }
        return "";
    }

    private void deleteAllMsgs() {
        msgs.clear();
    }

    static class SortByDate implements Comparator<AlerterMsg> {
        @Override
        public int compare(AlerterMsg a, AlerterMsg b) {
            int a1 = dayStrToInt(a.getDay()) * 10000;
            a1 += Integer.parseInt(a.getTime().replace(":", ""));
            int b1 = dayStrToInt(b.getDay()) * 10000;
            b1 += Integer.parseInt(b.getTime().replace(":", ""));
            int result = 0;
            if (a1 > b1) {
                return 1;
            }
            if (a1 == b1) {
                return 0;
            }
            return -1;
        }

        private int dayStrToInt(String day) {
            switch (day) {
                case "sunday":
                    return 1;
                case "monday":
                    return 2;
                case "tuesday":
                    return 3;
                case "wednesday":
                    return 4;
                case "thursday":
                    return 5;
                case "friday":
                    return 6;
                case "saturday":
                    return 7;
                default:
                    break;
            }
            return 8;
        }
    }
    class AlerterMsg {
        private String day = "";
        private String time = "";
        private String msg = "";

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public Boolean isActive() {
            return !time.isEmpty();
        }

        @Override
        public String toString() {
            if (this.getDay().isEmpty()) {
                return msg + " at " + getTime();
            }
            return msg + " at " + getTime() + " on " + this.getDay();
        }

    }
}
