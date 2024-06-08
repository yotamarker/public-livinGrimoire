package livinGrimoire

class DiSysOut : DiSkillV2() {
    override fun input(ear: String, skin: String, eye: String) {
        if (!ear.isEmpty() and !ear.contains("#")) {
            println(ear)
        }
    }
}