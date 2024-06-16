import LivinGrimoire.DiSysOut;
import Auxiliary_Modules.RussianWordGems;
import LivinGrimoire.Brain;
import LivinGrimoire.DiHelloWorld;
import Skills.logical.*;
import Skills.special_skills.DiBicameral;
import Skills.special_skills.DiSkillBundle;
import Skills.special_skills.SkillBranch;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Brain b1 = new Brain();
        b1.hardwareChobit.addSkill(new DiSysOut());
        DiSkillBundle dsb = new DiSkillBundle();
        dsb.addSkill(new DiHelloWorld());
        dsb.addSkill(new DiTime());
        b1.logicChobit.addSkill(dsb);
        b1.logicChobit.addSkill(new DiSayer());
        b1.logicChobit.addSkill(new DiEliza());
        b1.logicChobit.addSkill(new DiJumbler());
        b1.logicChobit.addSkill(new DiRailChatBot());
        b1.logicChobit.addSkill(new DiBlabberV5());
        SkillBranch sb = new SkillBranch(3);
        sb.addDefcon("lame");
        sb.addGoal("thanks");
        sb.addSkill(new DiSmoothie0());
        sb.addSkill(new DiSmoothie1());
        b1.logicChobit.addSkill(sb);
        b1.doIt("say roar","","");
        b1.doIt("","","");
        b1.logicChobit.addSkill(new RussianWordGems().retSkill());
//        b1.logicChobit.addSkill(new DiHoneyBunny());
        b1.logicChobit.addSkill(new DiAlarmer());
        b1.logicChobit.addSkill(new DiMemoryGame());
        b1.logicChobit.addSkill(new DiOneWorder());
        b1.logicChobit.addSkill(new DiAware(b1.logicChobit,"sarval","owly"));
        b1.logicChobit.addSkill(new DiBurstEliza());
        DiBicameral bicameral = new DiBicameral();
        bicameral.msgCol.addMSGV2("03:13","test run ok");
        b1.logicChobit.addSkill(bicameral);
        bicameral.msgCol.addMSGV2("22:57","#yandere");
        b1.logicChobit.addSkill(new DiYandere("fuki"));
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter input");
        String str1 = scanner.nextLine();
        while (!str1.equals("exit")){
            b1.doIt(str1,"","");
            str1 = scanner.nextLine();
        }
    }
}