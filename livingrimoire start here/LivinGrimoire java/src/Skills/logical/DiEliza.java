package Skills.logical;

import Auxiliary_Modules.AXCmdBreaker;
import Auxiliary_Modules.Eliza;
import LivinGrimoire.Skill;

public class DiEliza extends Skill {
    private Eliza eliza = new Eliza();
    private AXCmdBreaker cmdBreaker = new AXCmdBreaker("pal");
    @Override
    public void input(String ear, String skin, String eye) {
        String result = cmdBreaker.extractCmdParam(ear);
        if (!result.isEmpty()){
            setSimpleAlg(eliza.respond(result));
        }
    }
}
