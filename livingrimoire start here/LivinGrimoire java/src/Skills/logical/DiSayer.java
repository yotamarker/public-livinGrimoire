package Skills.logical;

import Auxiliary_Modules.AXCmdBreaker;
import LivinGrimoire.Skill;

public class DiSayer extends Skill {
    private AXCmdBreaker cmdBreaker = new AXCmdBreaker("say");
    private String command = "";
    @Override
    public void input(String ear, String skin, String eye) {
        command = cmdBreaker.extractCmdParam(ear);
        if (!command.isEmpty()){
            setSimpleAlg(command);
            command = "";
        }
    }
}
