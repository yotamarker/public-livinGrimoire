package skills.special

import auxiliary_modules.TimedMessages
import livinGrimoire.AbsDictionaryDB
import livinGrimoire.Skill
import livinGrimoire.Kokoro

class Bicameral : Skill() {
    /*
    *   DiBicameral bicameral = new DiBicameral();
        bicameral.msgCol.addMSGV2("02:57","test run ok");
        add # for messages that engage other skills
    * */
    var msgCol: TimedMessages = TimedMessages()
    override fun input(ear: String, skin: String, eye: String) {
        msgCol.tick()
        if (!kokoro.toHeart.get("dibicameral").equals("null")) {
            kokoro.toHeart.put("dibicameral", "null")
        }
        if (msgCol.msg) {
            val temp: String = msgCol.getLastMSG()
            if (!temp.contains("#")) {
                setSimpleAlg(temp)
            } else {
                kokoro.toHeart.put("dibicameral", temp.replace("#", ""))
            }
        }
    }
    override var kokoro: Kokoro = Kokoro(AbsDictionaryDB())
        set(value) {
            field = value
            kokoro.toHeart.put("dibicameral", "null")
        }
}