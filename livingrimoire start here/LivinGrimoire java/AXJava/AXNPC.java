package AXJava;

public class AXNPC {
    public Responder responder = new Responder();
    public PercentDripper dripper = new PercentDripper();
    public String respond(){
        if (dripper.drip()){
            return responder.getAResponse();
        }
        return "";
    }
    public String respondPlus(int plus){
        // increase rate of output
        if (dripper.dripPlus(plus)){
            return responder.getAResponse();
        }
        return "";
    }
}
