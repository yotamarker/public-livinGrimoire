package LivinGrimoire;

public class Fusion {
    private String emot = "";
    private String result = "";
    private Cerabellum[] ceraArr = new Cerabellum[5];

    public Fusion() {
        for (int i = 0; i < 5; i++) {
            ceraArr[i] = new Cerabellum();
        }
    }

    public String getEmot() {
        return emot;
    }
    public void loadAlgs(Neuron neuron){
        for (int i = 1; i < 6; i++) {
            if (!ceraArr[i-1].isActive()){
                Algorithm temp = neuron.getAlg(i);
                if (temp != null){
                    ceraArr[i-1].setAlgorithm(temp);
                }
            }
        }
    }
    public String runAlgs(String ear, String skin, String eye){
        result = "";
        for (int i = 0; i < 5; i++) {
            if (!ceraArr[i].isActive()){
                continue;
            }
            result = ceraArr[i].act(ear,skin,eye);
            ceraArr[i].advanceInAlg();
            emot = ceraArr[i].getEmot();
            ceraArr[i].deActivation(); // deactivation if Mutatable.algkillswitch = true
            return result;
        }
        emot = "";
        return result;
    }
}
