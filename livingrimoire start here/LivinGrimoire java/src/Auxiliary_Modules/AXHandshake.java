package Auxiliary_Modules;

public class AXHandshake {
    /*example use
            if (handshake.engage(ear)){ // ear reply like: what do you want?/yes
            setVerbatimAlg(4,"now I know you are here");return;
        }
        if (handshake.trigger()){setVerbatimAlg(4,handshake.getUser_name());return;}// user, user!
    * */
    private TrgTime trgTime = new TrgTime();
    private TrgTolerance trgTolerance = new TrgTolerance(10);
    private AXShoutOut shoutOut = new AXShoutOut();
    private String user_name = "user";
    private PercentDripper dripper = new PercentDripper();

    public AXHandshake() {
        // default handshakes (valid reply to shout out)
        shoutOut.handshake = new Responder("what", "yes", "i am here");
    }

    // setters
    public AXHandshake setTimeStamp(String time_stamp){
        // when will the shout-out happen?
        // example time stamp: 9:15
        trgTime.setTime(time_stamp);
        return this;
    }
    public AXHandshake setShoutOutLim(int lim){
        // how many times should user be called for, per shout out?
        trgTolerance.setMaxrepeats(lim);
        return this;
    }
    public AXHandshake setHandShake(Responder responder){
        // which responses would acknowledge the shout-out?
        // such as *see default handshakes for examples suggestions
        shoutOut.handshake = responder;
        return this;
    }
    public AXHandshake setDripperPercent(int n){
        // when shout out to user how frequent will it be?
        dripper.setLimis(n);
        return this;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
    // getters

    public String getUser_name() {
        return user_name;
    }

    public Boolean engage(String ear){
        if (trgTime.alarm()){trgTolerance.reset();}
        // stop shout out
        if (shoutOut.engage(ear)){trgTolerance.disable();return true;}
        return false;
    }
    public Boolean trigger(){
        if (trgTolerance.trigger()){
            if (dripper.drip()){shoutOut.activate();return true;}
        }
        return false;
    }
}
