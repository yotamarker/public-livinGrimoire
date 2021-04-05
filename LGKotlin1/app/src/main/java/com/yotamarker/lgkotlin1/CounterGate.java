package com.yotamarker.lgkotlin1;

class CounterDownGate {
    private int cycler = 0;
    private int limit;

    public CounterDownGate(int limit) {
        super();
        this.limit = limit;
        cycler=limit;
    }

    public int getLimit() {
        return limit;
    }

    public int getCycler() {
        return cycler;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
    public Boolean countingDown() {
        cycler--;
        if (cycler < 1) {
            return false;
        }
        return true;
    }
    public void reset(){cycler=limit;}
}
