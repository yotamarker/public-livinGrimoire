package Skills.logical;

import Auxiliary_Modules.AXContextCmd;
import Auxiliary_Modules.PhraseInflector;
import Auxiliary_Modules.QuestionChecker;
import Auxiliary_Modules.RailBot;
import LivinGrimoire.Skill;

public class DiRail extends Skill {
    // DiRail skill for testing purposes
    private final RailBot railBot;
    public AXContextCmd monologer = new AXContextCmd();
    public DiRail(int lim) {
        super();
        railBot = new RailBot(lim);
        monologer.contextCommands.add("talk more");
        monologer.commands.add("more");
    }
    public static boolean endsWithOk(String input) {
        return input != null && input.endsWith("ok");
    }
    public static String stripOk(String input) {
        return input.substring(0, input.length() - 2);
    }


    @Override
    public void input(String ear, String skin, String eye) {
        if(ear.isEmpty()){return;}
        // Add this line to ignore questions
        if(QuestionChecker.isQuestion(ear)){return;}
        if(monologer.engageCommand(ear)){
            String t1 = railBot.monolog();
            if(!t1.isEmpty()){
                setSimpleAlg(PhraseInflector.inflectPhrase(t1));return;
            }
        }
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

