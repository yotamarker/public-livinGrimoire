package skills;

import LivinGrimoire.Chobits;
import LivinGrimoire.DiSkillV2;

import java.util.ArrayList;

public class DiAware extends DiSkillV2 {
    private Chobits chobit;
    private String name;
    private String summoner = "user";
    private ArrayList<String> skills = new ArrayList<String>();

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
                break;
            case "what is your name":
                setSimpleAlg(this.name);
                break;
            case "name summoner":
                setSimpleAlg(this.summoner);
                break;
            case "how do you feel":
                // handle in hardware skill in hardwer chobit
                this.kokoro.toHeart.put("last_ap", chobit.getSoulEmotion());
                break;
        }
    }
}
