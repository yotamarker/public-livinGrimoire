package skills;

import AXJava.AXLearnability;
import AXJava.SkillHubAlgDispenser;
import LivinGrimoire.DiSkillV2;

import java.util.Hashtable;

public class SkillBranch extends DiSkillV2 {
    // unique skill used to bind similar skills
    /*
    * contains collection of skills
    * mutates active skill if detects conjuration
    * mutates active skill if algorithm results in
    * negative feedback
    * positive feedback negates active skill mutation
    * */
    private Hashtable<String,Integer> skillRef = new Hashtable<>();
    private SkillHubAlgDispenser skillHub = new SkillHubAlgDispenser();
    private AXLearnability ml;
    private int algPriority = 4;

    public void setAlgPriority(int algPriority) {
        this.algPriority = algPriority;
    }

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
        outAlg = skillHub.dispenseAlgorithm(ear,skin,eye);
        if (!(outAlg == null)){ml.pendAlg();outpAlgPriority = algPriority;}
    }
    public void addSkill(DiSkillV2 skill){
        skillHub.addSkill(skill);
    }
    public void addReferencedSkill(DiSkillV2 skill, String conjuration){
        // the conjuration string will engage it's respective skill
        skillHub.addSkill(skill);
        skillRef.put(conjuration, skillHub.getSize());
    }
    // learnability params
    public void addDefcon(String defcon){ml.defcons.add(defcon);}
    public void addGoal(String goal){ml.defcons.add(goal);}
    // while alg is pending, cause alg mutation ignoring learnability tolerance:
    public void addDefconLV5(String defcon5){ml.defcons.add(defcon5);}
}
