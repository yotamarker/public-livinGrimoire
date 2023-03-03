package AXJava;

public class Cycler {
    // cycles through numbers limit to 0 non-stop
    private int cycler = 0;
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
}
