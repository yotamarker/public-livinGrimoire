package livinGrimoire

class HelloWorld  // hello world skill for testing purposes
    : Skill() {
    override fun input(ear: String, skin: String, eye: String) {
        when (ear) {
            "hello" -> super.setVerbatimAlg(4, "hello world") // 1->5 1 is the highest algorithm priority
        }
    }
}