package Auxiliary_Modules;

public class Cycler {
    // cycles through numbers limit to 0 non-stop
    private int cycler;
    private int limit;

    public Cycler(int limit) {
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
    public int cycleCount() {
        cycler--;
        if (cycler < 0) {
            cycler = limit;
        }
        return cycler;
    }

    public void reset() {
        cycler = limit;
    }
    public void setToZero(){cycler = 0;}
    public void sync(int n){
        if((n<-1)||(n>limit)){
            return;
        }
        cycler = n;
    }
    public int getMode(){return cycler;}
}
