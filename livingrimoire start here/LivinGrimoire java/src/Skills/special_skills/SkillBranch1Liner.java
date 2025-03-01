package Skills.special_skills;

import LivinGrimoire.Skill;

public class SkillBranch1Liner extends SkillBranch {
    public SkillBranch1Liner(String goal, String defcon, int tolerance, Skill... skills) {
        super(tolerance);
        this.addGoal(goal);
        this.addDefcon(defcon);
        for (Skill skill : skills) {
            this.addSkill(skill);
        }
    }
}

