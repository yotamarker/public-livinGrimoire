package Skills.logical;

import Auxiliary_Modules.OnOffSwitch;
import Auxiliary_Modules.PercentDripper;
import Auxiliary_Modules.RegexUtil;
import Auxiliary_Modules.Responder;
import LivinGrimoire.Skill;

public class DiHoneyBunny extends Skill {
    private RegexUtil regexUtil = new RegexUtil();
    public OnOffSwitch onOffSwitch = new OnOffSwitch();
    private String user = "user";
    public PercentDripper drip = new PercentDripper();
    private Responder responses = new Responder("user", "i love you user", "hadouken", "shoryuken", "user is a honey bunny", "hadoken user", "shoryukens user", "i demand attention", "hey user","uwu");
    private int buffer = 10;
    private int bufferCounter = 0;
    private Boolean bool1 = false;

    public void setBuffer(int buffer) {
        this.buffer = buffer;
    }

    public DiHoneyBunny() {
        onOffSwitch.setOn(new Responder("honey bunny"));
    }

    public void setResponses(Responder responses) {
        this.responses = responses;
    }

    @Override
    public void input(String ear, String skin, String eye) {
        if(!ear.isEmpty()){
            bufferCounter = 0;
            String temp = regexUtil.extractRegex("(?<=my name is\\s)(.*)", ear);
            if (!temp.isEmpty()){user = temp;setSimpleAlg("got it "+user);return;}
        } else if (bufferCounter < buffer) {bufferCounter++;}
        bool1 = onOffSwitch.getMode(ear);
        if (bool1 && drip.drip()){
            if(bufferCounter > buffer -1){
                setSimpleAlg(responses.getAResponse().replace("user",user));
            }
        }
    }
}
