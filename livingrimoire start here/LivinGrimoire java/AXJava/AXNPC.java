package AXJava;

public class AXNPC {
    public RefreshQ responder = new RefreshQ();
    public PercentDripper dripper = new PercentDripper();
    public AXCmdBreaker cmdBreaker = new AXCmdBreaker("say");

    public AXNPC(int replyStockLim, int outputChance) {
        responder.setLimit(replyStockLim);
        if (outputChance >0 && outputChance < 101){
            dripper.setLimis(outputChance);
        }
    }

    public String respond(){
        if (dripper.drip()){
            return responder.getRNDElement();
        }
        return "";
    }
    public String respondPlus(int plus){
        // increase rate of output
        if (dripper.dripPlus(plus)){
            return responder.getRNDElement();
        }
        return "";
    }
    public void learn(String ear){
        // say hello there : hello there is learned
        String temp = cmdBreaker.extractCmdParam(ear);
        if (temp.isEmpty()){return;}
        responder.add(temp);
    }
    public String strRespond(String ear){
        // respond is ear contains a learned input
        if (dripper.drip() && responder.strContainsResponse(ear)){
            return responder.getRNDElement();
        }
        return "";
    }
    public String forceRespond(){
        return responder.getRNDElement();
    }
    public void setConjuration(String conjuration){
        cmdBreaker.conjuration = conjuration;
    }
}
