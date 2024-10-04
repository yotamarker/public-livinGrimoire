package Auxiliary_Modules;

import java.util.ArrayList;
import java.util.Collections;

public class EV3DaisyChainOrMode extends TrGEV3{
    // this class connects several logic gates triggers together
    private ArrayList<TrGEV3> trgGates = new ArrayList<>();

    public EV3DaisyChainOrMode(TrGEV3... gates) {
        Collections.addAll(trgGates, gates);
    }

    @Override
    public void input(String ear, String skin, String eye) {
        for (TrGEV3 gate : trgGates)
        {
            gate.input(ear, skin, eye);
        }
    }

    @Override
    public void reset() {
        for (TrGEV3 gate : trgGates)
        {
            gate.reset();
        }
    }

    @Override
    public Boolean trigger() {
        for (TrGEV3 gate : trgGates)
        {
            if(gate.trigger()){
                // at least 1 gate is engaged
                return true;
            }
        }
        // all gates are not engaged
        return false;
    }
}