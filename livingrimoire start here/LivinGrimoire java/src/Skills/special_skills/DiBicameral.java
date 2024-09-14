package Skills.special_skills;

import Auxiliary_Modules.TimedMessages;
import LivinGrimoire.Skill;
import LivinGrimoire.Kokoro;

public class DiBicameral extends Skill {
    /*
    *   DiBicameral bicameral = new DiBicameral();
        bicameral.msgCol.addMSGV2("02:57","test run ok");
        add # for messages that engage other skills
    * */
    public TimedMessages msgCol = new TimedMessages();
    @Override
    public void input(String ear, String skin, String eye) {
        msgCol.tick();
        if(!(kokoro.toHeart.get("dibicameral").equals("null"))){
            kokoro.toHeart.put("dibicameral","null");
        }
        if(msgCol.getMsg()){
            String temp = msgCol.getLastMSG();
            if(!temp.contains("#")){
                setSimpleAlg(temp);
            }
            else {
                kokoro.toHeart.put("dibicameral",temp.replace("#",""));
            }
        }
    }

    @Override
    public void setKokoro(Kokoro kokoro) {
        super.setKokoro(kokoro);
        kokoro.toHeart.put("dibicameral","null");
    }
}
