package Auxiliary_Modules;

public class AXInputWaiter {
    // wait for any input
    private final TrgTolerance trgTolerance;

    public AXInputWaiter(int tolerance) {
        this.trgTolerance = new TrgTolerance(tolerance);
        trgTolerance.reset();
    }
    public void reset(){
        trgTolerance.reset();
    }
    public Boolean wait(String s1){
        // return true till any input detected or till x times of no input detection
        if (!s1.isEmpty()){
            trgTolerance.disable();
            return false;
        }
        return trgTolerance.trigger();
    }
    public void setWait(int timesToWait){
        trgTolerance.setMaxrepeats(timesToWait);
    }
}
