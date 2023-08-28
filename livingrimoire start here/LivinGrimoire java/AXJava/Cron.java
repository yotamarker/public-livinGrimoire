package AXJava;

import LivinGrimoire.PlayGround;

import java.util.Random;

public class Cron extends TrGEV3{
    // triggers true, limit times, after initial time, and every minutes interval
    // counter resets at initial time, assuming trigger method was run
    int minutes; // minute interval between triggerings
    private PlayGround pl = new PlayGround();
    private TrgTime trgTime;
    private String timeStamp = "";
    private String initialTimeStamp = "";
    private int limit;
    private int counter = 0;

    public Cron(String startTime, int minutes, int limit) {
        this.minutes = minutes;
        this.timeStamp = startTime;
        this.initialTimeStamp = startTime;
        trgTime = new TrgTime();
        trgTime.setTime(startTime);
        this.limit = limit;
        if(limit<0){this.limit = 1;}
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        if (limit>-1){
        this.limit = limit;}
    }

    public int getCounter() {
        return counter;
    }

    public void setMinutes(int minutes) {
        if (minutes > -1) {
            this.minutes = minutes;}
    }
    @Override
    public Boolean trigger() {
        // delete counter = 0 if you don't want the trigger to work the next day
        if (counter == limit) {trgTime.setTime(initialTimeStamp);counter = 0;return false;}
        if (trgTime.alarm()){
            timeStamp = pl.getFutureInXMin(minutes);
            trgTime.setTime(timeStamp);
            counter++;
            return true;
        }
        return false;
    }
    @Override
    public void reset() {
        // manual trigger reset
        counter = 0;
    }
}
