package AXJava;

public class AXNeuroSama {
    private Responder nyaa = new Responder(" heart", " heart", " wink", " heart heart heart");
    private DrawRnd rnd = new DrawRnd();
    private int rate;

    public AXNeuroSama(int rate) {
        // the higher the rate the less likely to decorate outputs
        this.rate = rate;
    }

    public String decorate(String output){
        if (output.isEmpty()){
            return output;
        }
        String result = "";
        if(rnd.getSimpleRNDNum(rate) == 0){
            return output + nyaa.getAResponse();
        }
        return output;
    }
}
