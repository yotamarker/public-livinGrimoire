package Auxiliary_Modules;

public class TrgTolerance extends TrGEV3{
    // this boolean gate will return true till depletion or reset()
    private int repeats = 0;
    private int maxrepeats; //2 recomended
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
        repeats = maxrepeats;
    }

    @Override
    public Boolean trigger() {
        // will return true till depletion or reset()
        repeats--;
        return repeats > 0;
    }
    public void disable(){
        repeats = 0;
    }
}
