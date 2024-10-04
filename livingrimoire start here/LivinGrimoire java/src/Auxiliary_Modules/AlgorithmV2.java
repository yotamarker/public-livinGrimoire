package Auxiliary_Modules;

import LivinGrimoire.Algorithm;

public class AlgorithmV2 {
    private int priority;
    private Algorithm alg;

    public AlgorithmV2(int priority, Algorithm alg) {
        this.priority = priority;
        this.alg = alg;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Algorithm getAlg() {
        return alg;
    }

    public void setAlg(Algorithm alg) {
        this.alg = alg;
    }
}
