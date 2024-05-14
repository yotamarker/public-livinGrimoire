package skills;

import AXJava.TimedMessages;
import LivinGrimoire.DiSkillV2;

public class DiBicameral extends DiSkillV2 {
    /*
    *   DiBicameral bicameral = new DiBicameral();
        bicameral.msgCol.addMSGV2("02:57","test run ok");
        add # for messages that engage other skills
    * */
    public TimedMessages msgCol = new TimedMessages();
    @Override
    public void input(String ear, String skin, String eye) {
        msgCol.tick();
        if(!(kokoro.toHeart.getOrDefault("dibicameral","null").equals("null"))){
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
}
