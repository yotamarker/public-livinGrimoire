package Skills.logical;

import Auxiliary_Modules.AXGamification;
import Auxiliary_Modules.Responder;
import LivinGrimoire.Skill;

public class DiGamificationScouter extends Skill {
    private int lim = 2; // minimum for mood
    private final AXGamification axGamification;
    private final Responder noMood = new Responder("bored","no emotions detected","neutral");
    private final Responder yesMood = new Responder("operational", "efficient","mission ready","awaiting orders");

    public DiGamificationScouter(AXGamification axGamification) {
        this.axGamification = axGamification;
    }

    public void setLim(int lim) {
        this.lim = lim;
    }

    @Override
    public void input(String ear, String skin, String eye) {
        if (!ear.equals("how are you")){
            return;
        }
        if(axGamification.getCounter()>lim){
            setSimpleAlg(yesMood.getAResponse());
        }
        else{
            setSimpleAlg(noMood.getAResponse());
        }
    }
}
