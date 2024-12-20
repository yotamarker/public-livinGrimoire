package Skills.logical;


import Auxiliary_Modules.AXContextCmd;
import Auxiliary_Modules.Magic8Ball;
import LivinGrimoire.Skill;

public class DiMagic8Ball extends Skill {
    public Magic8Ball magic8Ball = new Magic8Ball();
    // skill toggle params:
    public AXContextCmd skillToggler = new AXContextCmd();
    private Boolean isActive = true;

    public DiMagic8Ball() {
        skillToggler.contextCommands.add("toggle eliza");
        skillToggler.contextCommands.add("toggle magic 8 ball");
        skillToggler.contextCommands.add("toggle magic eight ball");
        skillToggler.commands.add("again");skillToggler.commands.add("repeat");
    }

    @Override
    public void input(String ear, String skin, String eye) {
        // toggle the skill off/on
        if(skillToggler.engageCommand(ear)){isActive = !isActive;setVerbatimAlg(4, isActive? "skill activated":"skill inactivated");return;}
        if (!isActive){return;}
        // skill logic:
        if (magic8Ball.engage(ear)){
            setVerbatimAlg(4, magic8Ball.reply());
        }
    }
    @Override
    public String skillNotes(String param) {
        if ("notes".equals(param)) {
            return "magic 8 ball";
        } else if ("triggers".equals(param)) {
            return "ask a question starting with should I or will I";
        }
        return "note unavailable";
    }
}
