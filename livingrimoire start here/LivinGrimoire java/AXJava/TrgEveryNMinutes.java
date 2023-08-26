package AXJava;

import LivinGrimoire.PlayGround;

import java.util.Random;

public class TrgEveryNMinutes extends TrGEV3{
    // trigger returns true every minutes interval, post start time
    private int minutes; // minute interval between triggerings
    private PlayGround pl = new PlayGround();
    private TrgTime trgTime;
    private String timeStamp = "";

    public TrgEveryNMinutes(String startTime, int minutes) {
        this.minutes = minutes;
        this.timeStamp = startTime;
        trgTime = new TrgTime();
        trgTime.setTime(startTime);
    }

    public void setMinutes(int minutes) {
            if (minutes > -1) {
            this.minutes = minutes;}
    }

    @Override
    public Boolean trigger() {
        if (trgTime.alarm()){
            timeStamp = pl.getFutureInXMin(minutes);
            trgTime.setTime(timeStamp);
            return true;
        }
        return false;
    }

    @Override
    public void reset() {
        timeStamp = pl.getCurrentTimeStamp();
    }
}
