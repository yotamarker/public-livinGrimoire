package Auxiliary_Modules;

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
    public boolean learn(String ear){
        // say hello there : hello there is learned
        String temp = cmdBreaker.extractCmdParam(ear);
        if (temp.isEmpty()){return false;}
        responder.add(temp);
        return true;
    }
    public String strRespond(String ear){
        // respond if ear contains a learned input
        if (ear.isEmpty()){return "";}
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
