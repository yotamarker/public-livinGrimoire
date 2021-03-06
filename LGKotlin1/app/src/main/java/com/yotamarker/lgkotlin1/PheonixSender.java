package com.yotamarker.lgkotlin1;

public class PheonixSender {
    // this class returns true only once per time stamp
    Boolean wasSent = false;
    String sentTime = "";
    private PlayGround pl = new PlayGround();

    public Boolean sendable() {
        if (!wasSent) {
            wasSent = true;
            sentTime = pl.getCurrentTimeStamp();
            return true;
        }
        return false;
    }

    public void standByReset() {
        // reset after alg minute will have passed so that the alg wont
        // sent every think cycle at send time
        if (!sentTime.equals(pl.getCurrentTimeStamp()))
            wasSent = false;
    }
}
