package Auxiliary_Modules;

public class AXConvince {
    private final AXContextCmd req;
    private final Responder reset = new Responder( "reset");
    private int min = 3; // minimum requests till agreement
    private final DrawRnd rnd = new DrawRnd();
    private int counter = 0;
    private Boolean mode = false;
    private int max_eff_to_convince = 6;

    public void setMax_eff_to_convince(int max_eff_to_convince) {
        this.max_eff_to_convince = max_eff_to_convince;
    }

    public AXConvince(AXContextCmd req) {
        this.req = req;
    }

    public int engage(String ear){
        // 0:nothing, 1: no, 2:yes, 3: just been reset to no again
        if(reset.responsesContainsStr(ear)){
            counter = 0;
            mode = false;
            min += rnd.getSimpleRNDNum(max_eff_to_convince);
            return 3;
        }
        if(req.engageCommand(ear)) {
            counter++;
            if(counter < min){return 1;}
            else{mode = true;return 2;} // convinced
        }
        return 0;
    }
    public boolean isConvinced(){return mode;}
}
