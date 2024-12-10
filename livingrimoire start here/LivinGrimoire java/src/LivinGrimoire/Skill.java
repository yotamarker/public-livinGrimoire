package LivinGrimoire;

import java.util.ArrayList;
import java.util.Arrays;

public class Skill {
    protected Kokoro kokoro = null; // consciousness, shallow ref class to enable interskill communications
    protected Algorithm outAlg = null; // skills output
    protected int outpAlgPriority = -1; // defcon 1->5

    public Skill() {
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
        this.outAlg = simpleVerbatimAlgorithm(sayThis);
        this.outpAlgPriority = priority; // 1->5 1 is the highest algorithm priority
    }
    protected void setSimpleAlg(String... sayThis){
        // based on the setVerbatimAlg method
        // build a simple output algorithm to speak string by string per think cycle
        // uses varargs param
        this.outAlg = simpleVerbatimAlgorithm(sayThis);
        this.outpAlgPriority = 4; // 1->5 1 is the highest algorithm priority
    }
    protected void setVerbatimAlgFromList(int priority, ArrayList<String> sayThis){
        // build a simple output algorithm to speak string by string per think cycle
        // uses list param
        this.outAlg = algBuilder(new APVerbatim(sayThis));
        this.outpAlgPriority = priority; // 1->5 1 is the highest algorithm priority
    }
    protected void algPartsFusion(int priority,Mutatable... algParts){
        // build a custom algorithm out of a chain of algorithm parts(actions)
        this.outAlg = algBuilder(algParts);
        this.outpAlgPriority = priority; // 1->5 1 is the highest algorithm priority
    }
    public Boolean pendingAlgorithm(){
        // is an algorithm pending?
        return this.outAlg != null;
    }
    // algorithm build methods
    public Algorithm algBuilder(Mutatable... algParts) {
        // returns an algorithm built with the algPart varargs
        ArrayList<Mutatable> algParts1 = new ArrayList<>(Arrays.asList(algParts));
        return new Algorithm(algParts1);
    }
    // String based algorithm building methods
    public Algorithm simpleVerbatimAlgorithm(String... sayThis) {
        // returns an algorithm that says the sayThis Strings verbatim per think cycle
        return algBuilder(new APVerbatim(sayThis));
    }
    public String strContainsList(String str1, ArrayList<String> items) {
        // returns the 1st match between words in a string and values in a list.
        for (String temp : items) {
            if (str1.contains(temp)) {
                return temp;
            }
        }
        return "";
    }
    public String skillNotes(String param) {
        return "notes unknown";
    }

}
