package Skills.logical;

import Auxiliary_Modules.Responder;
import Auxiliary_Modules.TrgArgue;
import LivinGrimoire.Skill;

public class DiArguer extends Skill {
    private TrgArgue argue = new TrgArgue();
    private Responder r1 = new Responder(); // replies against argument
    private Responder r2 = new Responder(); // replies for insistence
    private String finale = "number"; // replies after argueLim insistances
    private int argueLim = 13;

    public void setArgue(TrgArgue argue) {
        this.argue = argue;
    }

    public void setR1(Responder r1) {
        this.r1 = r1;
    }

    public void setR2(Responder r2) {
        this.r2 = r2;
    }

    public void setFinale(String finale) {
        this.finale = finale;
    }

    public void setArgueLim(int argueLim) {
        this.argueLim = argueLim;
    }

    @Override
    public void input(String ear, String skin, String eye) {
        if (argue.engageCommand(ear)== 0){return;}
        if (argue.engageCommand(ear) == 1){setSimpleAlg(r1.getAResponse());}
        else {
            if((argue.getCounter() > argueLim) && (argue.getCounter() < argueLim + 5)){setSimpleAlg(finale.replace("number",argue.getCounter() + 5 + ""));return;}
            setSimpleAlg(r2.getAResponse());
        }
    }
}
