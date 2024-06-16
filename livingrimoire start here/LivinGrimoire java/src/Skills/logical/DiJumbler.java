package Skills.logical;

import Auxiliary_Modules.AXCmdBreaker;
import LivinGrimoire.DiSkillV2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DiJumbler extends DiSkillV2 {
    private AXCmdBreaker cmdBreaker = new AXCmdBreaker("jumble");
    private String temp = "";
    @Override
    public void input(String ear, String skin, String eye) {
        temp = cmdBreaker.extractCmdParam(ear);
        if (temp.isEmpty()){return;}
        setSimpleAlg(jumbleString(temp));
        temp = "";
    }
    public String jumbleString(String str) {
        List<String> letters = Arrays.asList(str.split(""));
        Collections.shuffle(letters);
        String jumbledStr = String.join("", letters);
        return jumbledStr;
    }
}
