package Skills.logical;

import Auxiliary_Modules.AXContextCmd;
import Auxiliary_Modules.DrawRnd;
import LivinGrimoire.Skill;

public class DiSmoothie0 extends Skill {
    private DrawRnd draw = new DrawRnd("grapefruits", "oranges",  "apples", "peaches", "melons", "pears", "carrot");
    private AXContextCmd cmd = new AXContextCmd();

    public DiSmoothie0() {
        cmd.contextCommands.add("recommend a smoothie");
        cmd.commands.add("yuck");
        cmd.commands.add("lame");
        cmd.commands.add("nah");
        cmd.commands.add("no");
    }

    @Override
    public void input(String ear, String skin, String eye) {
        if (cmd.engageCommand(ear)){
            setSimpleAlg(String.format("%s and  %s", draw.draw(), draw.draw()));
            draw.reset();
        }
    }
}
