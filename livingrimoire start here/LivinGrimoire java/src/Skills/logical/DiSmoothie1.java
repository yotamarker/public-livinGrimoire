package Skills.logical;

import Auxiliary_Modules.AXContextCmd;
import Auxiliary_Modules.DrawRnd;
import Auxiliary_Modules.Responder;
import LivinGrimoire.Skill;

public class DiSmoothie1 extends Skill {
    private Responder base = new Responder("grapefruits", "oranges",  "apples", "peaches", "melons", "pears", "carrot");
    private DrawRnd thickeners = new DrawRnd("bananas", "mango", "strawberry", "pineapple", "dates");
    private AXContextCmd cmd = new AXContextCmd();

    public DiSmoothie1() {
        cmd.contextCommands.add("recommend a smoothie");
        cmd.commands.add("yuck");
        cmd.commands.add("lame");
        cmd.commands.add("nah");
        cmd.commands.add("no");
    }

    @Override
    public void input(String ear, String skin, String eye) {
        if (cmd.engageCommand(ear)){
            setSimpleAlg(String.format("use %s as a base than add %s and %s", base.getAResponse(),thickeners.draw(),thickeners.draw()));
            thickeners.reset();
        }
    }
    @Override
    public String skillNotes(String param) {
        if (param.equals("notes")) {
            return "thick smoothie recipe recommender";
        } else if (param.equals("triggers")) {
            return "recommend a smoothie";
        }
        return "smoothie skill";
    }
}
