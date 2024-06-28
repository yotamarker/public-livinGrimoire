package skills.logical

import auxiliary_modules.AXGamification
import auxiliary_modules.Responder
import livinGrimoire.DiSkillV2

class DiGamificationScouter(axGamification: AXGamification) : DiSkillV2() {
    private var lim = 2 // minimum for mood
    private val axGamification: AXGamification
    private val noMood: Responder = Responder("bored", "no emotions detected", "neutral")
    private val yesMood: Responder = Responder("operational", "efficient", "mission ready", "awaiting orders")

    init {
        this.axGamification = axGamification
    }

    fun setLim(lim: Int) {
        this.lim = lim
    }

    override fun input(ear: String, skin: String, eye: String) {
        if (ear != "how are you") {
            return
        }
        if (axGamification.counter > lim) {
            setSimpleAlg(yesMood.aResponse)
        } else {
            setSimpleAlg(noMood.aResponse)
        }
    }
}