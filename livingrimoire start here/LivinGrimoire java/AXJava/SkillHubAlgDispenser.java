package AXJava;

import LivinGrimoire.Algorithm;
import LivinGrimoire.DiSkillV2;
import LivinGrimoire.Neuron;

import java.util.ArrayList;
import java.util.Random;

public class SkillHubAlgDispenser {
    // super class to output an algorithm out of a selection of skills
    //  engage the hub with dispenseAlg and return the value to outAlg attribute
    //  of the containing skill (which houses the skill hub)
    //  this module enables using a selection of 1 skill for triggers instead of having the triggers engage on multible skill
    //   the methode is ideal for learnability and behavioral modifications
    //   use a learnability auxiliary module as a condition to run an active skill shuffle or change methode
    //   (rndAlg , cycleAlg)
    //   moods can be used for specific cases to change behavior of the AGI, for example low energy state
    //   for that use (moodAlg)
    private ArrayList<DiSkillV2> skills = new ArrayList<DiSkillV2>();
    private int activeSkill = 0;
    private Neuron tempN = new Neuron();;
    private Random rand = new Random();
    public SkillHubAlgDispenser(DiSkillV2...skillsParams) {
        for (DiSkillV2 skill : skillsParams)
        {
            skills.add(skill);
        }
    }
    public SkillHubAlgDispenser addSkill(DiSkillV2 skill){
        // builder pattern
        skills.add(skill);
        return this;
    }
    public Algorithm dispenseAlgorithm(String ear, String skin, String eye){
        // return value to outAlg param of (external) summoner DiskillV2
        skills.get(activeSkill).input(ear,skin,eye);
        tempN.empty();
        skills.get(activeSkill).output(tempN);
        if (!tempN.negativeAlgParts.isEmpty()){
            return tempN.negativeAlgParts.get(0);
        }
        if (!tempN.algParts.isEmpty()){
            return tempN.algParts.get(0);
        }
        return null;
    }
    public void randomizeActiveSkill(){
        activeSkill = rand.nextInt(skills.size());
    }
    public void setActiveSkillWithMood(int mood){
        // mood integer represents active skill
        // different mood = different behavior
        if ((mood>-1) && mood < (skills.size())){
            activeSkill = mood;
        }
    }
    public void cycleActiveSkill(){
        // changes active skill
        // I recommend this method be triggered with a Learnability or SpiderSense object
        activeSkill++;
        if (activeSkill == skills.size()){
            activeSkill = 0;
        }
    }
}
