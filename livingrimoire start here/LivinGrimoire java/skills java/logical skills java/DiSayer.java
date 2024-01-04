package skills;

import AXJava.AXCmdBreaker;
import LivinGrimoire.DiSkillV2;

public class DiSayer extends DiSkillV2 {
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
