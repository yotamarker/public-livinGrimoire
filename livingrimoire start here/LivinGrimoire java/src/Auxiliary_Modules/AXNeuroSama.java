package Auxiliary_Modules;

public class AXNeuroSama {
    private final Responder nyaa = new Responder(" heart", " heart", " wink", " heart heart heart");
    private final DrawRnd rnd = new DrawRnd();
    private final int rate;

    public AXNeuroSama(int rate) {
        // the higher the rate the less likely to decorate outputs
        this.rate = rate;
    }

    public String decorate(String output){
        if (output.isEmpty()){
            return output;
        }
        if(rnd.getSimpleRNDNum(rate) == 0){
            return output + nyaa.getAResponse();
        }
        return output;
    }
}
