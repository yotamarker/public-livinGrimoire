package Skills.special_skills;

import Auxiliary_Modules.AXCmdBreaker;
import LivinGrimoire.Chobits;
import LivinGrimoire.Skill;

import java.util.Hashtable;

public class TheShell extends Skill {
    public Chobits shellChobit = new Chobits();
    protected Chobits logicChobit;
    protected Chobits hardwareChobit;
    protected Hashtable<String,Skill> logicSkills = new Hashtable<>(); // all logic skills
    protected Hashtable<String,Skill> hardwareSkills = new Hashtable<>(); // all hardware skills
    protected AXCmdBreaker installer = new AXCmdBreaker("install");
    protected AXCmdBreaker uninstaller = new AXCmdBreaker("abolish");
    protected String temp = "";

    public TheShell(Sh_Brain b1) {
        this.logicChobit = b1.logicChobit;
        this.hardwareChobit = b1.hardwareChobit;
        shellChobit.addSkill(this);
    }

    public void addLogicSkill(String skillName, Skill skill){
        logicSkills.put(skillName,skill);
    }
    public void addHardwareSkill(String skillName, Skill skill){
        hardwareSkills.put(skillName,skill);
    }
    // shell methods
    private int sh_addSkill(String skillKey){
        if (!(logicSkills.containsKey(skillKey)||hardwareSkills.containsKey(skillKey))){
            return 0; // skill does not exist
        }
        // find the skill:
        if(logicSkills.containsKey(skillKey)){
            Skill ref = logicSkills.get(skillKey);
            if(logicChobit.containsSkill(ref)){
                return 1; // logic skill already installed
            }
            logicChobit.addSkill(ref);
            return 2; // logic skill has been installed
        }
        Skill ref = hardwareSkills.get(skillKey);
        if(hardwareChobit.containsSkill(ref)){
            return 3; // hardware skill already installed
        }
        hardwareChobit.addSkill(ref);
        return 4; // hardware skill has been installed
    }
    private int sh_removeSkill(String skillKey){
        if (!(logicSkills.containsKey(skillKey)||hardwareSkills.containsKey(skillKey))){
            return 0; // skill does not exist
        }
        if(logicSkills.containsKey(skillKey)){
            Skill ref = logicSkills.get(skillKey);
            if(logicChobit.containsSkill(ref)){
                logicChobit.removeSkill(ref);
                return 1; // logic skill has been uninstalled
            }
            return 2; // logic skill is not installed
        }
        Skill ref = hardwareSkills.get(skillKey);
        if(hardwareChobit.containsSkill(ref)){
            hardwareChobit.removeSkill(ref);
            return 3; // hardware skill has been uninstalled
        }
        return 4; // hardware skill is not installed
    }
    private void sh_removeAllSkills(){logicChobit.clearSkills();hardwareChobit.clearSkills();}
    @Override
    public void input(String ear, String skin, String eye) {
        if (ear.equals("remove all skills")){
            sh_removeAllSkills();
            return;
        }
        temp = installer.extractCmdParam(ear);
        if (!temp.isEmpty()){
            switch (sh_addSkill(temp)){
                case 0:
                    setVerbatimAlg(4, "skill does not exist");
                    break;
                case 1:
                    setVerbatimAlg(4, "logic skill already installed");
                    break;
                case 2:
                    setVerbatimAlg(4, "logic skill has been installed");
                    break;
                case 3:
                    setVerbatimAlg(4, "hardware skill already installed");
                    break;
                case 4:
                    setVerbatimAlg(4, "hardware skill has been installed");
                    break;
            }
            temp = "";
            return;
        }
        temp = uninstaller.extractCmdParam(ear);
        if (!temp.isEmpty()){
            switch (sh_removeSkill(temp)){
                case 0:
                    setVerbatimAlg(4, "skill does not exist");
                    break;
                case 1:
                    setVerbatimAlg(4, "logic skill has been uninstalled");
                    break;
                case 2:
                    setVerbatimAlg(4, "logic skill is not installed");
                    break;
                case 3:
                    setVerbatimAlg(4, "hardware skill has been uninstalled");
                    break;
                case 4:
                    setVerbatimAlg(4, "hardware skill is not installed");
                    break;
            }
            temp = "";
        }
    }
}
