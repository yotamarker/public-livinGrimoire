package skills;

import AXJava.AXContextCmd;
import AXJava.Magic8Ball;
import LivinGrimoire.DiSkillV2;

public class DiMagic8Ball extends DiSkillV2 {
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
}
