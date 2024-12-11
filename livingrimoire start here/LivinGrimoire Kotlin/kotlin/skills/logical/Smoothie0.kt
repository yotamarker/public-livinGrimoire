package skills.logical

import auxiliary_modules.AXContextCmd
import auxiliary_modules.DrawRnd
import livinGrimoire.Skill

class Smoothie0 : Skill() {
    private val draw: DrawRnd = DrawRnd("grapefruits", "oranges", "apples", "peaches", "melons", "pears", "carrot")
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
            setSimpleAlg(java.lang.String.format("%s and  %s", draw.draw(), draw.draw()))
            draw.reset()
        }
    }
    override fun skillNotes(param: String): String {
        if (param == "notes") {
            return "smoothie recipe recommender"
        } else if (param == "triggers") {
            return "recommend a smoothie"
        }
        return "smoothie skill"
    }
}