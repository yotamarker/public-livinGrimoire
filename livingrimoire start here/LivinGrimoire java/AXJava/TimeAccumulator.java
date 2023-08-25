package AXJava;

import LivinGrimoire.TimeGate;

public class TimeAccumulator {
    // accumulator ++ each tick minutes interval
    private TimeGate timeGate = new TimeGate(5);
    private int accumulator = 0;
    public void setTick(int tick) {
        timeGate.setPause(tick);
    }

    public TimeAccumulator(int tick) {
        // accumulation ticker
        this.timeGate = new TimeGate(tick);
        timeGate.openGate();
    }

    public int getAccumulator() {
        return accumulator;
    }
    public void tick(){
        if (timeGate.isClosed()){
            timeGate.openGate();
            accumulator ++;
        }
    }
    public void reset(){accumulator = 0;}
}
