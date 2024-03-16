package skills;

import AXJava.Responder;
import LivinGrimoire.Chobits;
import LivinGrimoire.DiSkillV2;

import java.util.ArrayList;

public class DiAware extends DiSkillV2 {
    private Chobits chobit;
    private String name;
    private String summoner = "user";
    private ArrayList<String> skills = new ArrayList<String>();
    public Responder replies = new Responder("what","yes", "listening", name + " listening", name + " ready, what’s the plan?", "What’s the word, bird?");
    public DiAware(Chobits chobit, String name, String summoner) {
        this.chobit = chobit;
        this.name = name;
        this.summoner = summoner;
    }

    @Override
    public void input(String ear, String skin, String eye) {
        switch(ear) {
            case "list skills":
                skills = chobit.getSkillList();
                setVerbatimAlgFromList(4, skills);
                return;
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
