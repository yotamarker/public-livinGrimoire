package skills;

import AXJava.AXSkillBundle;
import AXJava.AlgorithmV2;
import LivinGrimoire.DiSkillV2;
import LivinGrimoire.Kokoro;

public class DiSkillBundle extends DiSkillV2 {
    private final AXSkillBundle axSkillBundle = new AXSkillBundle();
    @Override
    public void input(String ear, String skin, String eye) {
        AlgorithmV2 a1 = axSkillBundle.dispenseAlgorithm(ear, skin, eye);
        if(a1 == null){return;}
        this.outAlg = a1.getAlg();
        this.outpAlgPriority = a1.getPriority();
    }

    @Override
    public void setKokoro(Kokoro kokoro) {
        super.setKokoro(kokoro);
        axSkillBundle.setKokoro(kokoro);
    }
    public void addSkill(DiSkillV2 skill){
        axSkillBundle.addSkill(skill);
    }
}
