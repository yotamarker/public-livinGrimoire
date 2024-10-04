package Auxiliary_Modules;

public class OutputDripper {
    // drips true once every limit times
    // shushes the waifubot enough time to hear a reply from user
    private int cycler;
    private int limit; // set to 1 for on off effect

    public OutputDripper(int limit) {
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
    public Boolean drip() {
        cycler--;
        if (cycler < 0) {
            cycler = limit;
        }
        return cycler == 0;
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
}
