package LivinGrimoire;

import java.util.ArrayList;
import java.util.Hashtable;

public class Chobits extends thinkable {
    protected ArrayList<DiSkillV2> dClasses = new ArrayList<>();;
    protected Fusion fusion;
    protected Neuron noiron;
    protected Kokoro kokoro = new Kokoro(new AbsDictionaryDB()); // consciousness
    public Chobits() {
        // c'tor
        super();
        this.fusion = new Fusion();
        noiron = new Neuron();
    }
    public void setDataBase(AbsDictionaryDB absDictionaryDB) {
        this.kokoro = new Kokoro(absDictionaryDB);
    }
    public Chobits addSkill(DiSkillV2 skill){
        // add a skill (builder design patterned func))
        skill.setKokoro(this.kokoro);
        this.dClasses.add(skill);
        return this;
    }
    public void clearSkills(){
        // remove all skills
        this.dClasses.clear();
    }
    public void addSkills(DiSkillV2... skills){
        for(DiSkillV2 skill:skills){
            skill.setKokoro(this.kokoro);
            this.dClasses.add(skill);
        }
    }
    @Override
    public String think(String ear, String skin, String eye) {
        for (DiSkillV2 dCls : dClasses) {
            inOut(dCls, ear, skin, eye);
        }
        fusion.loadAlgs(noiron);
        return fusion.runAlgs(ear, skin, eye);
    }

    public String getSoulEmotion() {
        // get the last active AlgPart name
        // the AP is an action, and it also represents
        // an emotion
        return fusion.getEmot();
    }
    protected void inOut(DiSkillV2 dClass, String ear, String skin, String eye) {
        dClass.input(ear, skin, eye); // new
        dClass.output(noiron);
    }

    public Kokoro getKokoro() {
        // several chobits can use the same soul
        // this enables telepathic communications
        // between chobits in the same project
        return kokoro;
    }

    public void setKokoro(Kokoro kokoro) {
        // use this for telepathic communication between different chobits objects
        this.kokoro = kokoro;
    }
    public Fusion getFusion() {
        return fusion;
    }
}
