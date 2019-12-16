package com.yotamarker.lgkotlin1;

/*becomes false for 5 minutes from the setBlock time
 * */
public class BlockTime {
    private int h = -1;
    private int m = -1;
    private int blockMinutes = 5;

    public void setBlock(int hour, int minutes) {
        this.m = minutes + blockMinutes;
        this.h = hour;
        if (m > 60) {
            m -= 60;
            h += 1;
            if (h > 24) {
                h -= 24;
            }
        }
    }

    public Boolean unBlock(int hour, int minutes) {
        if (h == -1) {
            return false;
        }
        if ((hour == this.h) && (minutes > this.m)) {
            h = -1;
            m = -1;
            return false;
        }
        return true;
    }
}
