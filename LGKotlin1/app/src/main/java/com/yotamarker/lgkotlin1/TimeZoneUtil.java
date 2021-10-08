package com.yotamarker.lgkotlin1;

public class TimeZoneUtil {
    PlayGround pg = new PlayGround();
    static GMTList gmtList = new GMTList();
    public static String timeAt(int myGmt, int targetUTC) {
        PlayGround pGround = new PlayGround();
        String result = "";
        int hour = pGround.getHoursAsInt() + targetUTC - myGmt;
        if (hour < 0) {
            hour = 24 + hour;
        } else if (hour > 24) {
            hour = hour - 24;
        }
        result = hour + ":";
        String minutesStr = "";
        int minutes = pGround.getMinutesAsInt();
        if (minutes < 10) {
            minutesStr = "0" + minutes;
        } else {
            minutesStr = minutes + "";
        }
        result = result + minutesStr;
        return result;
    }

    public static String timeAt(int targetUTC) {
        PlayGround pGround = new PlayGround();
        int myGmt = pGround.getGMT();
        String result = "";
        int hour = pGround.getHoursAsInt() + targetUTC - myGmt;
        if (hour < 0) {
            hour = 24 + hour;
        } else if (hour > 24) {
            hour = hour - 24;
        }
        result = hour + ":";
        String minutesStr = "";
        int minutes = pGround.getMinutesAsInt();
        if (minutes < 10) {
            minutesStr = "0" + minutes;
        } else {
            minutesStr = minutes + "";
        }
        result = result + minutesStr;
        return result;
    }

    public static String timeAt(String location) {
        PlayGround pGround = new PlayGround();
        int myGmt = pGround.getGMT();
        int targetUTC = gmtList.getDST(location);
        String result = "";
        int hour = pGround.getHoursAsInt() + targetUTC - myGmt;
        if (hour < 0) {
            hour = 24 + hour;
        } else if (hour > 24) {
            hour = hour - 24;
        }
        if (hour < 10) {
            result = "0" + hour + ":";
        } else {
            result = hour + ":";
        }
        String minutesStr = "";
        int minutes = pGround.getMinutesAsInt();
        if (minutes < 10) {
            minutesStr = "0" + minutes;
        } else {
            minutesStr = minutes + "";
        }
        result = result + minutesStr;
        return result;
    }
}
