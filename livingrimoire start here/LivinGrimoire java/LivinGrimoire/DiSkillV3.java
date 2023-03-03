package LivinGrimoire;

public class DiSkillV3 extends DiSkillV2{
    // algorithm will be loaded with priority to run
    // use for fight or flight type skills
    public DiSkillV3() {
        super();
    }
    // negativeAlgParts take priority to run over algParts
    // (used in the super class DiSkillV2)
    @Override
    public void output(Neuron noiron) {
        if (outAlg != null) {
            noiron.negativeAlgParts.add(outAlg);
            outAlg = null;
        }
    }
}
