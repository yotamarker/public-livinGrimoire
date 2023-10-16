package skills;

import LivinGrimoire.Brain;
import LivinGrimoire.DiSkillV2;

public class Sh_Brain extends Brain {
    private TheShell shell = new TheShell(this);
    private String temp = "";
    public void addLogicSkill(String skillName, DiSkillV2 skill){
        shell.addLogicSkill(skillName, skill);
    }
    public void addHardwareSkill(String skillName, DiSkillV2 skill){
        shell.addHardwareSkill(skillName, skill);
    }

    public void setShell(TheShell shell) {
        // for using TheShell skill sub class objects with different input
        // method logic
        this.shell = shell;
    }

    @Override
    public void doIt(String ear, String skin, String eye) {
        temp = shell.shellChobit.think(ear, skin, eye);
        if(temp.isEmpty()){
            super.doIt(ear, skin, eye);
        }
        hardwareChobit.think(temp,skin,eye);
    }
}
