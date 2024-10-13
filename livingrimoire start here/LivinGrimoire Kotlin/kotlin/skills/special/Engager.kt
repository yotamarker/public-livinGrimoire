package skills.special

import auxiliary_modules.*
import livinGrimoire.Skill

class Engager(burpsPerHour: Int, skillToEngage: String) : Skill() {
    // makes the AI burp n  times per hour at random times
    private var burpsPerHour = 2
    private val trgMinute: TrgMinute = TrgMinute(0)
    private var skillToEngage = "unknown"
    private val draw: DrawRndDigits = DrawRndDigits()
    private val burpMinutes: LGFIFO<Int> = LGFIFO()

    init {
        if (burpsPerHour in 1..59) {
            this.burpsPerHour = burpsPerHour
        }
        for (i in 1..59) {
            draw.addElement(i)
        }
        for (i in 0 until burpsPerHour) {
            burpMinutes.add(draw.draw())
        }
        this.skillToEngage = skillToEngage
    }

    fun setSkillToEngage(skillToEngage: String) {
        this.skillToEngage = skillToEngage
    }

    override fun input(ear: String, skin: String, eye: String) {
        // night? do not burp
        if (TimeUtils.partOfDay() == "night") {
            return
        }
        // reset burps
        if (trgMinute.trigger()) {
            burpMinutes.clear()
            draw.reset()
            for (i in 0 until burpsPerHour) {
                burpMinutes.add(draw.draw())
            }
            return
        }
        // burp
        val nowMinutes: Int = TimeUtils.minutesAsInt
        if (burpMinutes.contains(nowMinutes)) {
            burpMinutes.removeItem(nowMinutes)
            this.kokoro.toHeart[skillToEngage] = "engage"
        }
    }
}