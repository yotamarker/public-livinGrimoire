package LivinGrimoire;

public class Cerabellum {
    // runs an algorithm
    private int fin;
    private int at;
    private enumFail failType;
    private Boolean incrementAt = false;

    public void advanceInAlg() {
        if (incrementAt) {
            incrementAt = false;
            at++;
            if (at == fin) {
                isActive = false;
            }
        }
    }

    public int getAt() {
        return at;
    }

    public Algorithm alg;
    private boolean isActive = false;
    private String emot = "";

    public String getEmot() {
        return emot;
    }
    public boolean setAlgorithm(Algorithm algorithm) {
        if (!isActive && (!algorithm.getAlgParts().isEmpty())) {
            this.alg = algorithm;
            this.at = 0;
            this.fin = algorithm.getSize();
            this.isActive = true;
            this.emot = alg.getAlgParts().get(at).myName(); // updated line
            return false;
        }
        return true;
    }

    public boolean isActive() {
        return isActive;
    }

    public boolean setActive(Boolean b1) {
        return isActive = b1;
    }
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String act(String ear, String skin, String eye) {
        String axnStr = "";
        if (!isActive) {
            return axnStr;
        }
        if (at < fin) {
            axnStr = alg.getAlgParts().get(at).action(ear, skin, eye);
            this.emot = alg.getAlgParts().get(at).myName();
            if (alg.getAlgParts().get(at).completed()) {
                incrementAt = true;
            }
        }
        return axnStr;
    }

    public int getMutationLimitOfActiveAlgPart() {
        return alg.getAlgParts().get(at).getMutationLimit();
    }
    public enumFail getFailType() {
        return alg.getAlgParts().get(at).failure("");
    }
}
