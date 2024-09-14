package skills.logical

import auxiliary_modules.TimeUtils
import livinGrimoire.Skill

class Time : Skill() {
    private val pl: TimeUtils = TimeUtils()
    override fun input(ear: String, skin: String, eye: String) {
        when (ear) {
            "what is the date" -> {
                val message = java.lang.String.format(
                    "it is the %s, %s %s",
                    pl.currentMonthDay,
                    pl.currentMonthName,
                    pl.yearAsInt
                )
                setVerbatimAlg(4, message)
                return
            }

            "what is the time" -> {
                setVerbatimAlg(4, pl.currentTimeStamp)
                return
            }

            "which day is it" -> {
                setVerbatimAlg(4, pl.dayOfDWeek)
                return
            }

            "good morning", "good night", "good afternoon", "good evening" -> {
                setVerbatimAlg(4, "good " + pl.partOfDay())
                return
            }

            "which month is it" -> {
                setSimpleAlg(pl.translateMonth(pl.monthAsInt))
                return
            }

            "which year is it" -> {
                setVerbatimAlg(4, pl.yearAsInt.toString() + "")
                return
            }

            "what is your time zone" -> {
                setVerbatimAlg(4, pl.local)
                return
            }

            "when is the first" -> {
                setVerbatimAlg(4, pl.nxtDayOnDate(1))
                return
            }

            "when is the second" -> {
                setVerbatimAlg(4, pl.nxtDayOnDate(2))
                return
            }

            "when is the third" -> {
                setVerbatimAlg(4, pl.nxtDayOnDate(3))
                return
            }

            "when is the fourth" -> {
                setVerbatimAlg(4, pl.nxtDayOnDate(4))
                return
            }

            "when is the fifth" -> {
                setVerbatimAlg(4, pl.nxtDayOnDate(5))
                return
            }

            "when is the sixth" -> {
                setVerbatimAlg(4, pl.nxtDayOnDate(6))
                return
            }

            "when is the seventh" -> {
                setVerbatimAlg(4, pl.nxtDayOnDate(7))
                return
            }

            "when is the eighth" -> {
                setVerbatimAlg(4, pl.nxtDayOnDate(8))
                return
            }

            "when is the ninth" -> {
                setVerbatimAlg(4, pl.nxtDayOnDate(9))
                return
            }

            "when is the tenth" -> {
                setVerbatimAlg(4, pl.nxtDayOnDate(10))
                return
            }

            "when is the eleventh" -> {
                setVerbatimAlg(4, pl.nxtDayOnDate(11))
                return
            }

            "when is the twelfth" -> {
                setVerbatimAlg(4, pl.nxtDayOnDate(12))
                return
            }

            "when is the thirteenth" -> {
                setVerbatimAlg(4, pl.nxtDayOnDate(13))
                return
            }

            "when is the fourteenth" -> {
                setVerbatimAlg(4, pl.nxtDayOnDate(14))
                return
            }

            "when is the fifteenth" -> {
                setVerbatimAlg(4, pl.nxtDayOnDate(15))
                return
            }

            "when is the sixteenth" -> {
                setVerbatimAlg(4, pl.nxtDayOnDate(16))
                return
            }

            "when is the seventeenth" -> {
                setVerbatimAlg(4, pl.nxtDayOnDate(17))
                return
            }

            "when is the eighteenth" -> {
                setVerbatimAlg(4, pl.nxtDayOnDate(18))
                return
            }

            "when is the nineteenth" -> {
                setVerbatimAlg(4, pl.nxtDayOnDate(19))
                return
            }

            "when is the twentieth" -> {
                setVerbatimAlg(4, pl.nxtDayOnDate(21))
                return
            }

            "when is the twenty first" -> {
                setVerbatimAlg(4, pl.nxtDayOnDate(21))
                return
            }

            "when is the twenty second" -> {
                setVerbatimAlg(4, pl.nxtDayOnDate(22))
                return
            }

            "when is the twenty third" -> {
                setVerbatimAlg(4, pl.nxtDayOnDate(23))
                return
            }

            "when is the twenty fourth" -> {
                setVerbatimAlg(4, pl.nxtDayOnDate(24))
                return
            }

            "when is the twenty fifth" -> {
                setVerbatimAlg(4, pl.nxtDayOnDate(25))
                return
            }

            "when is the twenty sixth" -> {
                setVerbatimAlg(4, pl.nxtDayOnDate(26))
                return
            }

            "when is the twenty seventh" -> {
                setVerbatimAlg(4, pl.nxtDayOnDate(27))
                return
            }

            "when is the twenty eighth" -> {
                setVerbatimAlg(4, pl.nxtDayOnDate(28))
                return
            }

            "when is the twenty ninth" -> {
                setVerbatimAlg(4, if (pl.nxtDayOnDate(29) == "") "never" else pl.nxtDayOnDate(29))
                return
            }

            "when is the thirtieth" -> {
                setVerbatimAlg(4, if (pl.nxtDayOnDate(30) == "") "never" else pl.nxtDayOnDate(30))
                return
            }

            "when is the thirty first" -> {
                setVerbatimAlg(4, if (pl.nxtDayOnDate(31) == "") "never" else pl.nxtDayOnDate(31))
                return
            }

            "incantation 0" -> {
//                this.setVerbatimAlg(5,"fly", "bless of magic caster", "infinity wall", "magic ward holy", "life essence")
                setSimpleAlg("fly", "bless of magic caster", "infinity wall", "magic ward holy", "life essence")
                return
            }

            else -> {}
        }
    }
}