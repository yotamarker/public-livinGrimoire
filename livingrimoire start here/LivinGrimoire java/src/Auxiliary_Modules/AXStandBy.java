package Auxiliary_Modules;

public class AXStandBy {
    private final TimeGate tg;

    public AXStandBy(int pause) {
        this.tg = new TimeGate(pause);
        this.tg.openGate();
    }

    public boolean standBy(String ear) {
        // only returns true after pause minutes of no input
        if (ear.length() > 0) {
            // restart count
            this.tg.openGate();
            return false;
        }
        if (this.tg.isClosed()) {
            // time out without input, stand by is true
            this.tg.openGate();
            return true;
        }
        return false;
    }
}

