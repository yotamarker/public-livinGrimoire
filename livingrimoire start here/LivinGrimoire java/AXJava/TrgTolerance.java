package AXJava;

import java.util.Random;

public class TrgTolerance extends TrGEV3{
    // this boolean gate will return true till depletion or reset()
    private int repeats = 0;
    private int maxrepeats = 2;
    private Random rand = new Random();
    public TrgTolerance(int maxrepeats) {
        this.maxrepeats = maxrepeats;
    }

    public void setMaxrepeats(int maxrepeats) {
        this.maxrepeats = maxrepeats;
        reset();
    }

    @Override
    public void reset() {
        // refill trigger
        repeats = rand.nextInt(maxrepeats);
    }

    @Override
    public Boolean trigger() {
        // will return true till depletion or reset()
        if (repeats > 0) {
            repeats--;
            return true;
        }
        return false;
    }
    public void disable(){
        repeats = 0;
    }
}
