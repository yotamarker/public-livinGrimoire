package Auxiliary_Modules;

import java.util.ArrayList;
import java.util.Collections;

public class EV3DaisyChainAndMode extends TrGEV3{
    // this class connects several logic gates triggers together
    private ArrayList<TrGEV3> trgGates = new ArrayList<>();

    public EV3DaisyChainAndMode(TrGEV3... gates) {
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
            if(!gate.trigger()){
                // not all gates return true
                return false;
            }
        }
        // all gates return true
        return true;
    }
}
