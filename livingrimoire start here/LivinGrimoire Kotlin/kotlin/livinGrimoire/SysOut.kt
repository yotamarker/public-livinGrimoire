package livinGrimoire

class SysOut : Skill() {
    override fun input(ear: String, skin: String, eye: String) {
        if (!ear.isEmpty() and !ear.contains("#")) {
            println(ear)
        }
    }
}