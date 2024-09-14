package Skills.logical;


import Auxiliary_Modules.RegexUtil;
import Auxiliary_Modules.Responder;
import LivinGrimoire.Skill;


public class DiMemoryGame extends Skill {
    int score = 0;
    Boolean gameOn = false;
    String gameStr = "";
    Responder gameChars = new Responder("r","g","b","y");
    private RegexUtil regexUtil = new RegexUtil();
    @Override
    public void input(String ear, String skin, String eye) {
        if (ear.equals("memory game on")){
            gameOn = true;
            score = 0;
            gameStr= gameChars.getAResponse();
            setSimpleAlg(gameStr);
        }
        if (gameOn){
            String temp = regexUtil.extractRegex("^[rgby]+$",ear);
            if(!temp.isEmpty()){
                if(temp.equals(gameStr)){
                    temp = gameChars.getAResponse();
                    gameStr += temp;
                    score++;
                    setSimpleAlg(temp);
                }else{
                    gameOn = false;
                    setSimpleAlg("you scored "+ score);
                    score = 0;
                }
            }
        }
    }
}
