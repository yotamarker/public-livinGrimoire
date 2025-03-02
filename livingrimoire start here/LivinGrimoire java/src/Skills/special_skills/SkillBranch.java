package Skills.special_skills;

import Auxiliary_Modules.AXLearnability;
import Auxiliary_Modules.AlgorithmV2;
import Auxiliary_Modules.SkillHubAlgDispenser;
import LivinGrimoire.Skill;
import LivinGrimoire.Kokoro;

import java.util.Hashtable;

public class SkillBranch extends Skill {
    // unique skill used to bind similar skills
    /*
    * contains collection of skills
    * mutates active skill if detects conjuration
    * mutates active skill if algorithm results in
    * negative feedback
    * positive feedback negates active skill mutation
    * */
    private final Hashtable<String,Integer> skillRef = new Hashtable<>();
    private final SkillHubAlgDispenser skillHub = new SkillHubAlgDispenser();
    private final AXLearnability ml;

    public SkillBranch(int tolerance) {
        ml = new AXLearnability(tolerance);
    }

    @Override
    public void input(String ear, String skin, String eye) {
        // conjuration alg morph
        if (skillRef.contains(ear)){
            skillHub.setActiveSkillWithMood(skillRef.get(ear));
            setSimpleAlg("hmm");
        }
        // machine learning alg morph
        if (ml.mutateAlg(ear)){
            skillHub.cycleActiveSkill();
            setSimpleAlg("hmm");
        }
        // alg engage
        AlgorithmV2 a1 = skillHub.dispenseAlgorithm(ear,skin,eye);
        if(a1 == null){return;}
        this.outAlg = a1.getAlg();
        this.outpAlgPriority = a1.getPriority();
        ml.pendAlg();
    }
    public void addSkill(Skill skill){
        skillHub.addSkill(skill);
    }
    public void addReferencedSkill(Skill skill, String conjuration){
        // the conjuration string will engage it's respective skill
        skillHub.addSkill(skill);
        skillRef.put(conjuration, skillHub.getSize());
    }
    // learnability params
    public void addDefcon(String defcon){ml.defcons.add(defcon);}
    public void addGoal(String goal){ml.goals.add(goal);}
    // while alg is pending, cause alg mutation ignoring learnability tolerance:
    public void addDefconLV5(String defcon5){ml.defcon5.add(defcon5);}
    @Override
    public void setKokoro(Kokoro kokoro) {
        super.setKokoro(kokoro);
        skillHub.setKokoro(kokoro);
    }
    @Override
    public String skillNotes(String param) {
        return this.skillHub.activeSkillRef().skillNotes(param);
    }

}
