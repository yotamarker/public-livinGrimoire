package Auxiliary_Modules;

import java.util.Calendar;
import java.util.Date;

public class TimeGate {
    // a gate that only stays open for x minutes after it has been set open
    // open gate returns true
    // closed state gate returns false
    // the gate starts closed
    private int pause = 5; //minutes to keep gate closed
    //private Date openedGate = addMinutesToJavaUtilDate(new Date(), pause);
    private Date openedGate = new Date();

    public TimeGate(int minutes) {
        super();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.pause = minutes;
    }
    public TimeGate() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void setPause(int pause) {
        if(pause <60 && pause >0) {
            this.pause = pause;}
    }
    public void openGate() {
        // the gate will stay open for pause minutes
        this.openedGate = addMinutesToJavaUtilDate(new Date(), pause);
    }
    public void close() {
        openedGate = new Date();
    }
    public Boolean isClosed() {
        return openedGate.before(new Date());
    }
    public Boolean isOpen() {
        return !isClosed();
    }
    public void openGate(int minutes) {
        Date now = new Date();
        openedGate = addMinutesToJavaUtilDate(now, minutes);
    }
    private Date addMinutesToJavaUtilDate(Date date, int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minutes);
        return calendar.getTime();
    }
    private Date addSecondsToJavaUtilDate(Date date, int seconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, seconds);
        return calendar.getTime();
    }
    public void openGateforNSeconds(int n){
        this.openedGate = addSecondsToJavaUtilDate(new Date(), n);
    }
}
