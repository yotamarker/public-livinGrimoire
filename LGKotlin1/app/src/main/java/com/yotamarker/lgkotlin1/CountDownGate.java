package com.yotamarker.lgkotlin1;

public class CountDownGate {
    private int cycler = 0;
    private int limit;

    public CountDownGate(int limit) {
        super();
        this.limit = limit;
        cycler = limit;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
    public Boolean countingDown() {
        cycler--;
        if (cycler < 1) {
            cycler = -1;
            return false;
        }
        return true;
    }

    public void reset() {
        cycler = limit;
    }

    public void setToOff() {
        cycler = 0;
    }
}
