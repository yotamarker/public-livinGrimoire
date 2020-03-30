package com.yotamarker.lgkotlin1;

import java.util.Calendar;
import java.util.Date;

public class TimeGate {
    // a gate that only opens x minutes after it has been set
    private int pause = 10; //minutes to keep gate closed
    private Date openedGate = addMinutesToJavaUtilDate(new Date(), pause);

    public TimeGate(int minutes) {
        super();
        this.pause = minutes;
    }
    public TimeGate() {
    }
    public Boolean isClosed() {
        return !openedGate.before(new Date());
    }
    public void close() {
        this.openedGate = addMinutesToJavaUtilDate(new Date(), pause);
    }
    public void close(int minutes) {
        Date now = new Date();
        openedGate = addMinutesToJavaUtilDate(now, minutes);
    }
    private Date addMinutesToJavaUtilDate(Date date, int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minutes);
        return calendar.getTime();
    }
    public void setPause(int pause) {
        if(pause <60 && pause >0) {
            this.pause = pause;}
    }
}
