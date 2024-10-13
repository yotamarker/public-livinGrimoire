package Auxiliary_Modules;

public class TrgParrot {
    // simulates a parrot chirp trigger mechanism
    // as such this trigger is off at night
    // in essence this trigger says: I am here, are you here? good.
    private TrgTolerance tolerance = new TrgTolerance(3);
    private Responder silencer = new Responder("ok","okay","stop","shut up","quiet");

    public void setTolerance(int limit) {
        if(limit>0){
        this.tolerance = new TrgTolerance(limit);}
    }

    public Boolean trigger(Boolean standBy, String ear){
        // relies on the Kokoro standby boolean
        // no input or output for a set amount of time results with a true
        // and replenishing the trigger.
        if(TimeUtils.isNight()){
            // is it night? I will be quite
            return false;
        }
        // you want the bird to shut up?
        if(silencer.responsesContainsStr(ear)){
            tolerance.disable();
            return false;
        }
        // no input or output for a while?
        if(standBy){
            // I will chirp
            tolerance.reset();
            return true;
        }
        // we are handshaking?
        if(!ear.isEmpty()){
            // I will reply chirp till it grows old for me (a set amount of times till reset)
            return tolerance.trigger();
        }
        return false;
    }
}
