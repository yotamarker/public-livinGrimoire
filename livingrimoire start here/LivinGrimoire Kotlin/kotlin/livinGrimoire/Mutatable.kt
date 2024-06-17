package livinGrimoire

open class Mutatable {
    // one part of an algorithm, it is a basic simple action or sub goal
    var algKillSwitch = false
    open fun action(ear: String, skin: String, eye: String): String {return  ""}
    open fun completed(): Boolean {return false}
    fun myName(): String {
        // Returns the class name
        return this.javaClass.simpleName
    }
}