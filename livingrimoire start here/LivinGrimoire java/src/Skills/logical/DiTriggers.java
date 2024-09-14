package Skills.logical;

import Auxiliary_Modules.UniqueItemsPriorityQue;
import LivinGrimoire.Brain;
import LivinGrimoire.Skill;

public class DiTriggers extends Skill {
    private final UniqueItemsPriorityQue triggers = new UniqueItemsPriorityQue();
    private boolean isRecording = false;
    private final Brain brain;
    private String newCMD = "";

    public DiTriggers(Brain brain) {
        this.brain = brain;
    }

    @Override
    public void input(String ear, String skin, String eye) {
        if(!isRecording){
            if(!ear.isEmpty()){newCMD = ear;isRecording = true;} // not recording and hear something so record
        }else {
            if(!brain.getLogicChobitOutput().isEmpty()){triggers.add(newCMD);} // recording and output detected
            isRecording = false;
        }
        // trigger output (can add alternative code to do this automatically)
        if(ear.equals("random trigger")){
            setSimpleAlg(triggers.getRNDElement());
        }
    }
}
