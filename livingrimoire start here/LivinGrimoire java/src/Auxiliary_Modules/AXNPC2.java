package Auxiliary_Modules;

public class AXNPC2 extends AXNPC{
    public AnnoyedQue annoyedQue = new AnnoyedQue(5);
    public AXNPC2(int replyStockLim, int outputChance) {
        super(replyStockLim, outputChance);
    }
    public void strLearn(String ear){
        // learns inputs containing strings that are repeatedly used by others
        annoyedQue.learn(ear);
        if (annoyedQue.isAnnoyed(ear)){
            this.responder.add(ear);
        }
    }
}
