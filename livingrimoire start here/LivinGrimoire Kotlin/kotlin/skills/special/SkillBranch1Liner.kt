package skills.special

import livinGrimoire.Skill

class SkillBranch1Liner(goal: String, defcon: String, tolerance: Int, vararg skills: Skill) :
    SkillBranch(tolerance) {
    init {
        addGoal(goal)
        addDefcon(defcon)
        for (skill in skills) {
            addSkill(skill)
        }
    }
}