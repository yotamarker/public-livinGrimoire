package Skills.logical;

import Auxiliary_Modules.AXCmdBreaker;
import Auxiliary_Modules.AXContextCmd;
import Auxiliary_Modules.DrawRnd;
import LivinGrimoire.Skill;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class DiBlueCrystal extends Skill {
    // language learning game skill
    private ArrayList<HashMap<String, String>> categories = new ArrayList<>();
    private DrawRnd quiz = new DrawRnd();
    private ArrayList<String> keyList = new ArrayList<>();
    private int categoryIndex = 0;
    private AXContextCmd teach = new AXContextCmd();
    private AXContextCmd quizMe = new AXContextCmd();
    private String expectedSolution = "";
    private AXCmdBreaker itMeans = new AXCmdBreaker("it means");
    private int score = 0;
    private int lvUpAt = 10;

    public DiBlueCrystal() {
        teach.contextCommands.add("teach");
        teach.commands.add("more");
        teach.commands.add("next");
        teach.commands.add("again");
        quizMe.contextCommands.add("quiz");
        quizMe.commands.add("more");
        quizMe.commands.add("next");
        quizMe.commands.add("again");
    }

    public void setLvUpAt(int lvUpAt) {
        this.lvUpAt = lvUpAt;
    }

    public void addCategory(HashMap<String, String> category){
        categories.add(category);
        if(categories.size() > 1){return;}
        keyList = new ArrayList<>(categories.get(0).keySet());
    }

    @Override
    public void input(String ear, String skin, String eye) {
        if (ear.isEmpty()){return;}
        // get word gem
        if (teach.engageCommand(ear)){
            Random rand = new Random();
            int randomIndex = rand.nextInt(keyList.size());
            String wordGem = keyList.get(randomIndex);
            String translation = categories.get(categoryIndex).get(wordGem);
            quiz.addElement(wordGem);
            setSimpleAlg(String.format("%s means %s", wordGem, translation));
            return;
        }
        // manual category change:
        if(ear.equals("next category")){
            nxtCategory();
            setSimpleAlg("ok");
            return;
        }
        // quiz user
        if(quizMe.engageCommand(ear)){
            String question = quiz.draw();
            if (question.isEmpty()){
                Random rand = new Random();
                question = keyList.get(rand.nextInt(keyList.size()));
            }
            setSimpleAlg(String.format("what does %s mean", question));
            expectedSolution = "it means " + categories.get(categoryIndex).get(question);
            return;
        }
        // get score
        if(ear.equals("score")){
            setSimpleAlg(String.format("your score is %d of %d", score, lvUpAt));
            return;
        }
        // get current level
        if(ear.equals("level")){
            setSimpleAlg(String.format("your level is %d ", categoryIndex));
            return;
        }
        // answer
        if(ear.contains("it means") && (!expectedSolution.isEmpty())){
            if(ear.equals(expectedSolution)){
                expectedSolution = "";
                // correct solution
                score++;
                // level up?
                if(score == lvUpAt){score = 0;nxtCategory();setSimpleAlg("leveled up");
                }
                else {setSimpleAlg("correct");
                }
            }
            else {
                expectedSolution = "";
                score = 0;
                setSimpleAlg("bu bu wrong answer");
            }
            return;
        }
    }
    private void nxtCategory(){
        score = 0;
        categoryIndex++;
        if (categoryIndex == categories.size()){categoryIndex = 0;}
        if(categories.isEmpty()){return;}
        keyList = new ArrayList<>(categories.get(categoryIndex).keySet());
    }
}
