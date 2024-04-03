package skills;

import AXJava.AXCmdBreaker;
import AXJava.Eliza;
import AXJava.Responder;
import LivinGrimoire.DiSkillV2;
import LivinGrimoire.TimeGate;

public class DiBurstEliza extends DiSkillV2 {
    private Eliza eliza = new Eliza();
    private String trig = "listen";
    private Responder off = new Responder("chill","shut up");
    private TimeGate timeGate = new TimeGate(5);
    public void setBurstDuration(int t){
        if (t < 1){
            return;
        }
        timeGate.setPause(t);
    }
    @Override
    public void input(String ear, String skin, String eye) {
        if (ear.equals(trig)){
            timeGate.openGate();
            setSimpleAlg("listening");
            return;
        }
        if (timeGate.isOpen()){
            if(off.strContainsResponse(ear)){
                setSimpleAlg("got it");
                timeGate.close();
                return;
            }
            if(ear.isEmpty()){
                return;
            }
            setSimpleAlg(eliza.respond(ear));
        }
    }
}
