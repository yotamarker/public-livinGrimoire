package LivinGrimoire;

public class DiSkillV2 {
    protected Kokoro kokoro = new Kokoro(new AbsDictionaryDB()); // consciousness, shallow ref class to enable interskill communications
    protected DISkillUtils diSkillUtils = new DISkillUtils();
    protected Algorithm outAlg = null; // skills output

    public DiSkillV2() {
        super();
    }
    // skill triggers and algorithmic logic
    public void input(String ear, String skin, String eye) {
    }
    // extraction of skill algorithm to run (if there is one)
    public void output(Neuron noiron) {
        if (outAlg != null) {
            noiron.algParts.add(outAlg);
            outAlg = null;
        }
    }
    public void setKokoro(Kokoro kokoro) {
        // use this for telepathic communication between different chobits objects
        this.kokoro = kokoro;
    }
}
