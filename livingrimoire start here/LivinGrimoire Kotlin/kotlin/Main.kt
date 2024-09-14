import livinGrimoire.*
import skills.logical.Smoothie0
import skills.logical.Smoothie1
import skills.logical.Time
import skills.special.SkillBundle
import skills.special.SkillBranch


fun main(args: Array<String>) {
    val b1:Brain = Brain()
    b1.addHardwareSkill(SysOut())
    val skillBundle: SkillBundle = SkillBundle()
    skillBundle.addSkill(HelloWorld())
    skillBundle.addSkill(Time())
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
    t.addSkill(Smoothie0())
    t.addSkill(Smoothie1())
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