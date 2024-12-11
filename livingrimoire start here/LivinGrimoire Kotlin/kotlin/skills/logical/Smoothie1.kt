package skills.logical

import auxiliary_modules.AXContextCmd
import auxiliary_modules.DrawRnd
import auxiliary_modules.Responder
import livinGrimoire.Skill

class Smoothie1 : Skill() {
    private val base: Responder = Responder("grapefruits", "oranges", "apples", "peaches", "melons", "pears", "carrot")
    private val thickeners: DrawRnd = DrawRnd("bananas", "mango", "strawberry", "pineapple", "dates")
    private val cmd: AXContextCmd = AXContextCmd()

    init {
        cmd.contextCommands.add("recommend a smoothie")
        cmd.commands.add("yuck")
        cmd.commands.add("lame")
        cmd.commands.add("nah")
        cmd.commands.add("no")
    }

    override fun input(ear: String, skin: String, eye: String) {
        if (cmd.engageCommand(ear)) {
            setSimpleAlg(
                java.lang.String.format(
                    "use %s as a base than add %s and %s",
                    base.aResponse,
                    thickeners.draw(),
                    thickeners.draw()
                )
            )
            thickeners.reset()
        }
    }
    override fun skillNotes(param: String): String {
        if (param == "notes") {
            return "thick smoothie recipe recommender"
        } else if (param == "triggers") {
            return "recommend a smoothie"
        }
        return "smoothie skill"
    }
}