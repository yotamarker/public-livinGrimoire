package skills.logical

import auxiliary_modules.TimeUtils
import livinGrimoire.Skill

class Time : Skill() {
    override fun input(ear: String, skin: String, eye: String) {
        when (ear) {
            "what is the date" -> {
                val message = java.lang.String.format(
                    "it is the %s, %s %s",
                    TimeUtils.currentMonthDay,
                    TimeUtils.currentMonthName,
                    TimeUtils.yearAsInt
                )
                setVerbatimAlg(4, message)
                return
            }

            "what is the time" -> {
                setVerbatimAlg(4, TimeUtils.currentTimeStamp)
                return
            }

            "which day is it" -> {
                setVerbatimAlg(4, TimeUtils.dayOfDWeek)
                return
            }

            "good morning", "good night", "good afternoon", "good evening" -> {
                setVerbatimAlg(4, "good " + TimeUtils.partOfDay())
                return
            }

            "which month is it" -> {
                setSimpleAlg(TimeUtils.translateMonth(TimeUtils.monthAsInt))
                return
            }

            "which year is it" -> {
                setVerbatimAlg(4, TimeUtils.yearAsInt.toString() + "")
                return
            }

            "what is your time zone" -> {
                setVerbatimAlg(4, TimeUtils.local)
                return
            }

            "when is the first" -> {
                setVerbatimAlg(4, TimeUtils.nxtDayOnDate(1))
                return
            }

            "when is the second" -> {
                setVerbatimAlg(4, TimeUtils.nxtDayOnDate(2))
                return
            }

            "when is the third" -> {
                setVerbatimAlg(4, TimeUtils.nxtDayOnDate(3))
                return
            }

            "when is the fourth" -> {
                setVerbatimAlg(4, TimeUtils.nxtDayOnDate(4))
                return
            }

            "when is the fifth" -> {
                setVerbatimAlg(4, TimeUtils.nxtDayOnDate(5))
                return
            }

            "when is the sixth" -> {
                setVerbatimAlg(4, TimeUtils.nxtDayOnDate(6))
                return
            }

            "when is the seventh" -> {
                setVerbatimAlg(4, TimeUtils.nxtDayOnDate(7))
                return
            }

            "when is the eighth" -> {
                setVerbatimAlg(4, TimeUtils.nxtDayOnDate(8))
                return
            }

            "when is the ninth" -> {
                setVerbatimAlg(4, TimeUtils.nxtDayOnDate(9))
                return
            }

            "when is the tenth" -> {
                setVerbatimAlg(4, TimeUtils.nxtDayOnDate(10))
                return
            }

            "when is the eleventh" -> {
                setVerbatimAlg(4, TimeUtils.nxtDayOnDate(11))
                return
            }

            "when is the twelfth" -> {
                setVerbatimAlg(4, TimeUtils.nxtDayOnDate(12))
                return
            }

            "when is the thirteenth" -> {
                setVerbatimAlg(4, TimeUtils.nxtDayOnDate(13))
                return
            }

            "when is the fourteenth" -> {
                setVerbatimAlg(4, TimeUtils.nxtDayOnDate(14))
                return
            }

            "when is the fifteenth" -> {
                setVerbatimAlg(4, TimeUtils.nxtDayOnDate(15))
                return
            }

            "when is the sixteenth" -> {
                setVerbatimAlg(4, TimeUtils.nxtDayOnDate(16))
                return
            }

            "when is the seventeenth" -> {
                setVerbatimAlg(4, TimeUtils.nxtDayOnDate(17))
                return
            }

            "when is the eighteenth" -> {
                setVerbatimAlg(4, TimeUtils.nxtDayOnDate(18))
                return
            }

            "when is the nineteenth" -> {
                setVerbatimAlg(4, TimeUtils.nxtDayOnDate(19))
                return
            }

            "when is the twentieth" -> {
                setVerbatimAlg(4, TimeUtils.nxtDayOnDate(21))
                return
            }

            "when is the twenty first" -> {
                setVerbatimAlg(4, TimeUtils.nxtDayOnDate(21))
                return
            }

            "when is the twenty second" -> {
                setVerbatimAlg(4, TimeUtils.nxtDayOnDate(22))
                return
            }

            "when is the twenty third" -> {
                setVerbatimAlg(4, TimeUtils.nxtDayOnDate(23))
                return
            }

            "when is the twenty fourth" -> {
                setVerbatimAlg(4, TimeUtils.nxtDayOnDate(24))
                return
            }

            "when is the twenty fifth" -> {
                setVerbatimAlg(4, TimeUtils.nxtDayOnDate(25))
                return
            }

            "when is the twenty sixth" -> {
                setVerbatimAlg(4, TimeUtils.nxtDayOnDate(26))
                return
            }

            "when is the twenty seventh" -> {
                setVerbatimAlg(4, TimeUtils.nxtDayOnDate(27))
                return
            }

            "when is the twenty eighth" -> {
                setVerbatimAlg(4, TimeUtils.nxtDayOnDate(28))
                return
            }

            "when is the twenty ninth" -> {
                setVerbatimAlg(4, if (TimeUtils.nxtDayOnDate(29) == "") "never" else TimeUtils.nxtDayOnDate(29))
                return
            }

            "when is the thirtieth" -> {
                setVerbatimAlg(4, if (TimeUtils.nxtDayOnDate(30) == "") "never" else TimeUtils.nxtDayOnDate(30))
                return
            }

            "when is the thirty first" -> {
                setVerbatimAlg(4, if (TimeUtils.nxtDayOnDate(31) == "") "never" else TimeUtils.nxtDayOnDate(31))
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
    override fun skillNotes(param: String): String {
        if (param == "notes") {
            return "gets time date or misc"
        } else if (param == "triggers") {
            val triggers = arrayOf(
                "what is the time",
                "which day is it",
                "what is the date",
                "evil laugh",
                "good part of day",
                "when is the fifth"
            )
            val randomIndex = (Math.random() * triggers.size).toInt()
            return triggers[randomIndex]
        }
        return "time util skill"
    }
}