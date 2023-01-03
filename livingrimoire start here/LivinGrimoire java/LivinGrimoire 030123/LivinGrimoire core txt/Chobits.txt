package LivinGrimoire;

import java.util.ArrayList;
import java.util.Hashtable;

public class Chobits extends thinkable {
    protected ArrayList<DiSkillV2> dClasses = new ArrayList<>();;
    // algorithms fusion (polymarization)
    protected Hashtable<String, Integer> algDurations = new Hashtable<>();;
    protected Fusion fusion;
    // region essential DClasses
    // endregion
    protected Neuron noiron;
    // added :
    protected Kokoro kokoro = new Kokoro(new AbsDictionaryDB()); // consciousness
    protected String lastOutput = "";
    // standBy phase 260320
    protected TimeGate timeGate = new TimeGate();
    public Chobits() {
        // c'tor
        super();
        this.fusion = new Fusion(this.algDurations);
        noiron = new Neuron();
    }
    /* set the chobit database
        the database is built as a key value dictionary
        the database can be used with by the Kokoro attribute
    * */
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
    public void setPause(int pause){
        // set standby timegate pause.
        // pause time without output from the chobit
        // means the standby attribute will be true for a moment.
        // it is the equivelant of the chobit being bored
        // the standby attribute can be accessed via the kokoro
        // object within a skill if needed
        this.timeGate.setPause(pause);
    }
    @Override
    public String think(String ear, String skin, String eye) {
        // the input will be processed by the chobits' skills
        ear = translateIn(ear);
        for (DiSkillV2 dCls : dClasses) {
            inOut(dCls, ear, skin, eye);
        }
        fusion.setAlgQueue(noiron);
        return translateOut(fusion.act(ear, skin, eye));
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

    protected String translateIn(String earIn) {
        // makes sure the chobit doesn't feedback on her own output
        if (earIn.equals(lastOutput)) {
            return "";
        }
        return earIn;
    }

    protected String translateOut(String outResult) {
        // save last output served
        if (!outResult.isEmpty()) {
            lastOutput = outResult;
            this.timeGate.openGate();
            this.kokoro.standBy = false;
        }
        // standBy :
        else {
            if (this.timeGate.isClosed()) {
                this.kokoro.standBy = true;
                this.timeGate.openGate();
            } else {
                this.kokoro.standBy = false;
            }
        }
        return outResult;
    }
    public Boolean getStandby() {
        // this is an under use method
        // only use this for testing
        return kokoro.standBy;
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

    public Hashtable<String, Integer> getAlgDurations() {
        // think cycles run duration per algorithm
        // use this method for saving run times if you wish
        return algDurations;
    }

    public void setAlgDurations(Hashtable<String, Integer> algDurations) {
        // think cycles run duration per algorithm
        // use this method for saving run times if you wish
        // algDurations are shallow ref to Fusions' algDurations
        // shorter algDurations give algorithms run priority in case several algorithms(sent by skills) want to run
        // at the same time
        this.algDurations = algDurations;
    }

    public Fusion getFusion() {
        return fusion;
    }
}
