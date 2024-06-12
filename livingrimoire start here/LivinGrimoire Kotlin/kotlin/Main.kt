import livinGrimoire.*
import skills.DiTime


fun main(args: Array<String>) {
    val b1:Brain = Brain()
    b1.hardwareChobit.addSkill(DiSysOut())
    b1.logicChobit.addSkill(DiTime())
    b1.doIt("incantation 0", "", "")
    b1.doIt("", "", "")
    b1.doIt("", "", "")
    b1.doIt("what is the time", "", "")
    b1.doIt("", "", "")
    b1.doIt("", "", "")
    b1.doIt("", "", "")
    b1.doIt("", "", "")
    b1.doIt("", "", "")
    b1.doIt("", "", "")
    b1.doIt("", "", "")
    println("Program arguments: ${args.joinToString()}")
}