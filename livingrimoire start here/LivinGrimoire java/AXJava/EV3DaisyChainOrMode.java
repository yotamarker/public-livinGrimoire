package AXJava;

import java.util.ArrayList;

public class EV3DaisyChainOrMode extends TrGEV3{
    // this class connects several logic gates triggers together
    private ArrayList<TrGEV3> trgGates = new ArrayList<TrGEV3>();

    public EV3DaisyChainOrMode(TrGEV3... gates) {
        for (TrGEV3 gate : gates)
        {
            trgGates.add(gate);
        }
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