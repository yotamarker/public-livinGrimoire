import Auxiliary_Modules.DrawRnd;
import LivinGrimoire.DiSysOut;
import Auxiliary_Modules.RussianWordGems;
import LivinGrimoire.Brain;
import LivinGrimoire.DiHelloWorld;
import Skills.logical.*;
import Skills.special_skills.DiBicameral;
import Skills.special_skills.DiGamificationSkillBundle;
import Skills.special_skills.DiSkillBundle;
import Skills.special_skills.SkillBranch;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Brain b1 = new Brain();
        b1.addHardwareSkill(new DiSysOut());
        DiGamificationSkillBundle dsb = new DiGamificationSkillBundle();
        dsb.addGrindSkill(new DiHelloWorld());
        dsb.addCostlySkill(new DiTime());
        b1.addLogicalSkill(dsb);
        b1.addLogicalSkill(new DiGamificationScouter(dsb.getAxGamification()));
        b1.addLogicalSkill(new DiSayer());
        b1.addLogicalSkill(new DiEliza());
        b1.addLogicalSkill(new DiJumbler());
        b1.addLogicalSkill(new DiRailChatBot());
        b1.addLogicalSkill(new DiBlabberV5());
        SkillBranch sb = new SkillBranch(3);
        sb.addDefcon("lame");
        sb.addGoal("thanks");
        sb.addSkill(new DiSmoothie0());
        sb.addSkill(new DiSmoothie1());
        b1.addLogicalSkill(sb);
        b1.doIt("say roar","","");
        b1.doIt("","","");
        b1.addLogicalSkill(new RussianWordGems().retSkill());
//        b1.logicChobit.addSkill(new DiHoneyBunny());
        b1.addLogicalSkill(new DiAlarmer());
        b1.addLogicalSkill(new DiMemoryGame());
        b1.addLogicalSkill(new DiOneWorder());
        b1.addLogicalSkill(new DiAware(b1.logicChobit,"sarval","owly"));
        b1.addLogicalSkill(new DiBurstEliza());
        DiBicameral bicameral = new DiBicameral();
        bicameral.msgCol.addMSGV2("03:13","test run ok");
        b1.addLogicalSkill(bicameral);
        bicameral.msgCol.addMSGV2("13:05","#yandere");
        b1.addLogicalSkill(new DiYandere("fuki"));
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter input");
        String str1 = scanner.nextLine();
        while (!str1.equals("exit")){
            b1.doIt(str1,"","");
            str1 = scanner.nextLine();
        }
    }
}