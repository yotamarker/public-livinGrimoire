package Auxiliary_Modules;

import LivinGrimoire.*;

import java.util.ArrayList;
import java.util.Random;

public class SkillHubAlgDispenser {
//     super class to output an algorithm out of a selection of skills
//      engage the hub with dispenseAlg and return the value to outAlg attribute
//      of the containing skill (which houses the skill hub)
//      this module enables using a selection of 1 skill for triggers instead of having the triggers engage on multible skill
//       the methode is ideal for learnability and behavioral modifications
//       use a learnability auxiliary module as a condition to run an active skill shuffle or change methode
//       (rndAlg , cycleAlg)
//       moods can be used for specific cases to change behavior of the AGI, for example low energy state
//       for that use (moodAlg)
    private final ArrayList<Skill> skills = new ArrayList<>();
    private int activeSkill = 0;
    private final Neuron tempN = new Neuron();
    private final Random rand = new Random();
    private Kokoro kokoro = new Kokoro(new AbsDictionaryDB());
    public SkillHubAlgDispenser(Skill...skillsParams) {
        for (Skill skill : skillsParams)
        {
            skill.setKokoro(this.kokoro);
            skills.add(skill);
        }
    }
    public void setKokoro(Kokoro kokoro) {
        this.kokoro = kokoro;
        for (Skill skill : skills) {
            skill.setKokoro(this.kokoro);
        }
    }
    public SkillHubAlgDispenser addSkill(Skill skill){
        // builder pattern
        skill.setKokoro(this.kokoro);
        skills.add(skill);
        return this;
    }
    public AlgorithmV2 dispenseAlgorithm(String ear, String skin, String eye){
        // return value to outAlg param of (external) summoner DiskillV2
        skills.get(activeSkill).input(ear,skin,eye);
        skills.get(activeSkill).output(tempN);
        for (int i = 1; i < 6; i++) {
            Algorithm temp = tempN.getAlg(i);
            if (temp != null){
                return new AlgorithmV2(i,temp);
            }
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
    public int getSize(){return skills.size();}
    public Skill activeSkillRef() {
        return this.skills.get(this.activeSkill);
    }

}
