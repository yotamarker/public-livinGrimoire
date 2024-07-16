import auxiliary_modules.DrawRnd
import livinGrimoire.*
import skills.logical.DiSmoothie0
import skills.logical.DiSmoothie1
import skills.logical.DiTime
import skills.special.DiSkillBundle
import skills.special.SkillBranch


fun main(args: Array<String>) {
    val b1:Brain = Brain()
    b1.addHardwareSkill(DiSysOut())
    val skillBundle: DiSkillBundle = DiSkillBundle()
    skillBundle.addSkill(DiHelloWorld())
    skillBundle.addSkill(DiTime())
    b1.addLogicalSkill(skillBundle)
    b1.doIt("incantation 0", "", "")
    b1.doIt("", "", "")
    b1.doIt("", "", "")
    b1.doIt("hello", "", "")
    b1.doIt("what is the time", "", "")
    b1.doIt("", "", "")
    b1.doIt("", "", "")
    b1.doIt("", "", "")
    b1.doIt("", "", "")
    b1.doIt("", "", "")
    b1.doIt("hello", "", "")
    b1.doIt("", "", "")
    // skill branch test
    val t = SkillBranch(3)
    t.addDefcon("lame")
    t.addGoal("thanks")
    t.addSkill(DiSmoothie0())
    t.addSkill(DiSmoothie1())
    b1.addLogicalSkill(t)
    b1.doIt("recommend a smoothie", "", "")
    b1.doIt("lame", "", "")
    b1.doIt("recommend a smoothie", "", "")
    b1.doIt("lame", "", "")
    b1.doIt("lame", "", "")
    b1.doIt("lame", "", "")
    b1.doIt("lame", "", "")
    b1.doIt("", "", "")
    b1.doIt("recommend a smoothie", "", "")
    b1.doIt("recommend a smoothie", "", "")
    b1.doIt("recommend a smoothie", "", "")
    b1.doIt("recommend a smoothie", "", "")
    println("Program arguments: ${args.joinToString()}")
}