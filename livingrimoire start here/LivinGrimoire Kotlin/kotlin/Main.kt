import livinGrimoire.*

fun main(args: Array<String>) {
    val n:Mutatable = APVerbatim("hello","world")
    println(n.action("","",""))
    println(n.action("","",""))
    println(n.action("","",""))
    val c1:Chobits = Chobits()
    c1.addSkill(DiHelloWorld())
    println(c1.think("hello","",""))
    println(c1.think("","",""))
    val b1:Brain = Brain()
    b1.logicChobit.addSkill(DiHelloWorld())
    b1.hardwareChobit.addSkill(DiSysOut())
    b1.doIt("hello","","")
    b1.doIt("","","")
    println("Program arguments: ${args.joinToString()}")
}