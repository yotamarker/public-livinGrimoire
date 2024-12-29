package LivinGrimoire;

import java.util.ArrayList;

public class Chobits {
    public ArrayList<Skill> dClasses = new ArrayList<>();
    protected Fusion fusion;
    protected Neuron noiron;
    protected Kokoro kokoro = new Kokoro(new AbsDictionaryDB()); // consciousness
    private boolean isThinking = false;
    private final ArrayList<Skill> awareSkills = new ArrayList<>();
    public Chobits() {
        // c'tor
        super();
        this.fusion = new Fusion();
        noiron = new Neuron();
    }
    public void setDataBase(AbsDictionaryDB absDictionaryDB) {
        this.kokoro = new Kokoro(absDictionaryDB);
    }
    public Chobits addSkill(Skill skill){
        // add a skill (builder design patterned func))
        if (this.isThinking) {
            return this;
        }
        skill.setKokoro(this.kokoro);
        this.dClasses.add(skill);
        return this;
    }
    public Chobits addSkillAware(Skill skill) {
        // add a skill with Chobit Object in their constructor
        skill.setKokoro(this.kokoro);
        this.awareSkills.add(skill);
        return this;
    }
    public void clearSkills(){
        // remove all skills
        if (this.isThinking) {
            return;
        }
        this.dClasses.clear();
    }
    public void addSkills(Skill... skills){
        if (this.isThinking) {
            return;
        }
        for(Skill skill:skills){
            skill.setKokoro(this.kokoro);
            this.dClasses.add(skill);
        }
    }
    public void removeSkill(Skill skill){
        if (this.isThinking) {
            return;
        }
        dClasses.remove(skill);
    }
    public Boolean containsSkill(Skill skill){
        return dClasses.contains(skill);
    }
    public String think(String ear, String skin, String eye) {
        this.isThinking = true;
        for (Skill dCls : dClasses) {
            inOut(dCls, ear, skin, eye);
        }
        this.isThinking = false;
        for (Skill dCls2 : awareSkills) {
            inOut(dCls2, ear, skin, eye);
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
    protected void inOut(Skill dClass, String ear, String skin, String eye) {
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
    public ArrayList<String> getSkillList(){
        ArrayList<String> result = new ArrayList<>();
        for (Skill skill: this.dClasses) {
            result.add(skill.getClass().getSimpleName());
        }
        return result;
    }
}
