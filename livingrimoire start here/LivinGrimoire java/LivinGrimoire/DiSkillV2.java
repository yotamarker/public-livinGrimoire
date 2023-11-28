package LivinGrimoire;

import java.util.ArrayList;

public class DiSkillV2 {
    protected Kokoro kokoro = new Kokoro(new AbsDictionaryDB()); // consciousness, shallow ref class to enable interskill communications
    protected DISkillUtils diSkillUtils = new DISkillUtils();
    protected Algorithm outAlg = null; // skills output
    protected int outpAlgPriority = -1; // defcon 1->5

    public DiSkillV2() {
        super();
    }
    // skill triggers and algorithmic logic
    public void input(String ear, String skin, String eye) {
    }
    // extraction of skill algorithm to run (if there is one)
    public void output(Neuron noiron) {
        if (outAlg != null) {
            noiron.insertAlg(this.outpAlgPriority,outAlg);
            outpAlgPriority = -1;
            outAlg = null;
        }
    }
    public void setKokoro(Kokoro kokoro) {
        // use this for telepathic communication between different chobits objects
        this.kokoro = kokoro;
    }
    // in skill algorithm building shortcut methods:
    protected void setVerbatimAlg(int priority, String... sayThis){
        // build a simple output algorithm to speak string by string per think cycle
        // uses varargs param
        this.outAlg = this.diSkillUtils.simpleVerbatimAlgorithm(sayThis);
        this.outpAlgPriority = priority; // 1->5 1 is the highest algorithm priority
    }
    protected void setVerbatimAlgFromList(int priority, ArrayList<String> sayThis){
        // build a simple output algorithm to speak string by string per think cycle
        // uses list param
        this.outAlg = this.diSkillUtils.algBuilder(new APVerbatim(sayThis));
        this.outpAlgPriority = priority; // 1->5 1 is the highest algorithm priority
    }
    protected void algPartsFusion(int priority,Mutatable... algParts){
        // build a custom algorithm out of a chain of algorithm parts(actions)
        this.outAlg = this.diSkillUtils.algBuilder(algParts);
        this.outpAlgPriority = priority; // 1->5 1 is the highest algorithm priority
    }
    public Boolean pendingAlgorithm(){
        // is an algorithm pending?
        return this.outAlg != null;
    }
}
