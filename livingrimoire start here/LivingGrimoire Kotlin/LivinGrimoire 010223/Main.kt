import lgkt.*
import kotlin.concurrent.thread

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
    val neo:Chobits = Chobits()
    neo.addSkill(DiHelloWorld())
    println(neo.think("hello","",""))
}