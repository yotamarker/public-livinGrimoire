package skills;

import AXJava.AXCmdBreaker;
import AXJava.Eliza;
import LivinGrimoire.DiSkillV2;

public class DiEliza extends DiSkillV2 {
    private Eliza eliza = new Eliza();
    private AXCmdBreaker cmdBreaker = new AXCmdBreaker("baby");
    private String result = "";
    @Override
    public void input(String ear, String skin, String eye) {
        result = cmdBreaker.extractCmdParam(ear);
        if (!result.isEmpty()){
            setSimpleAlg(eliza.respond(result));
            result = "";
        }
    }
}
