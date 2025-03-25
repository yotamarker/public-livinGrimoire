package Skills.logical;

import Auxiliary_Modules.PhraseInflector;
import Auxiliary_Modules.RailBot;
import LivinGrimoire.Skill;

public class DiRail extends Skill {
    // DiRail skill for testing purposes
    private final RailBot railBot = new RailBot();
    public DiRail() {
        super();
    }
    public static boolean endsWithOk(String input) {
        return input != null && input.endsWith("ok");
    }
    public static String stripOk(String input) {
        return input.substring(0, input.length() - 2);
    }


    @Override
    public void input(String ear, String skin, String eye) {
        if(!endsWithOk(ear)){return;}
        String temp = stripOk(ear);
        String temp2 = railBot.respondDialog(temp);
        if(!temp2.isEmpty()){setSimpleAlg(PhraseInflector.inflectPhrase(temp2));}
        railBot.learn(temp);
    }

    @Override
    public String skillNotes(String param) {
        if ("notes".equals(param)) {
            return "experimental chatbot";
        } else if ("triggers".equals(param)) {
            return "end input with the word ok";
        }
        return "note unavailable";
    }
}

