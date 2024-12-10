package Skills.logical;

import Auxiliary_Modules.Responder;
import Auxiliary_Modules.UniqueRandomGenerator;
import LivinGrimoire.Chobits;
import LivinGrimoire.Skill;

import java.util.ArrayList;

public class DiAware extends Skill {
    private Chobits chobit;
    private String name;
    private String summoner = "user";
    private ArrayList<String> skills = new ArrayList<String>();
    public Responder replies;
    private UniqueRandomGenerator skillDex = null;
    private int skillForInfo = 0;
    public DiAware(Chobits chobit, String name, String summoner) {
        this.chobit = chobit;
        this.name = name;
        this.summoner = summoner;
        this.replies = new Responder("what","yes", "listening", name + " listening", name + " ready, what’s the plan?", "What’s the word, bird?");
    }

    @Override
    public void input(String ear, String skin, String eye) {
        switch(ear) {
            case "what can you do":
                if (this.skillDex == null) {
                    this.skillDex = new UniqueRandomGenerator(this.chobit.getSkillList().size());
                }
                this.skillForInfo = this.skillDex.getUniqueRandom();
                this.setSimpleAlg(this.chobit.dClasses.get(this.skillForInfo).getClass().getSimpleName() + " " + this.chobit.dClasses.get(this.skillForInfo).skillNotes("notes"));
                break;
            case "skill triggers":
                this.setSimpleAlg(this.chobit.dClasses.get(this.skillForInfo).skillNotes("triggers"));
                break;
            case "what is your name":
                setSimpleAlg(this.name);
                return;
            case "name summoner":
                setSimpleAlg(this.summoner);
                return;
            case "how do you feel":
                // handle in hardware skill in hardwer chobit
                this.kokoro.toHeart.put("last_ap", chobit.getSoulEmotion());
                return;
            case "test":
                setSimpleAlg(this.replies.getAResponse());
                return;
        }
        if (ear.equals(name)){
            setSimpleAlg(this.replies.getAResponse());
        }
    }
}
