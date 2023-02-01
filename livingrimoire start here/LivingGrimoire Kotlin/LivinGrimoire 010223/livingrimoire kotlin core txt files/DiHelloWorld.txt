package lgkt

class DiHelloWorld: DiSkillV2() {
    // hello world skill for testing purposes
    override fun input(ear: String, skin: String, eye: String) {
        when (ear) {
            "hello" -> outAlg = diSkillUtils.simpleVerbatimAlgorithm("test2", "hello world")
        }
    }
}