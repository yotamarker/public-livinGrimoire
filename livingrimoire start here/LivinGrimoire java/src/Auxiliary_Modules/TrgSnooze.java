package Auxiliary_Modules;

public class TrgSnooze extends TrGEV3{
    // this boolean gate will return true per minute interval
    // max repeats times.
    private int repeats = 0;
    private int maxrepeats; //2 recomended
    private Boolean snooze = true;
    private int snoozeInterval = 5;
    public TrgSnooze(int maxrepeats) {
        this.maxrepeats = maxrepeats;
    }

    public void setSnoozeInterval(int snoozeInterval) {
        if((snoozeInterval > 1)&&(snoozeInterval<11)){
            this.snoozeInterval = snoozeInterval;
        }
    }
    public void setMaxrepeats(int maxrepeats) {
        this.maxrepeats = maxrepeats;
        reset();
    }
    @Override
    public void reset() {
        // refill trigger
        // engage this code when an alarm clock was engaged to enable snoozing
        repeats = maxrepeats;
    }

    @Override
    public Boolean trigger() {
        // trigger a snooze alarm?
        int minutes = TimeUtils.getMinutesAsInt();
        if(minutes%snoozeInterval !=0){
            snooze = true;
            return false;
        }
        if ((repeats > 0)&&(snooze)) {
            snooze = false;
            repeats--;
            return true;
        }
        return false;
    }
    public void disable(){
        // engage this method to stop the snoozing
        repeats = 0;
    }
}
