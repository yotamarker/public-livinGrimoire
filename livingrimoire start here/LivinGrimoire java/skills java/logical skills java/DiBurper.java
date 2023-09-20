package skills;

import AXJava.DrawRndDigits;
import AXJava.Responder;
import AXJava.TrgMinute;
import AXJava.UniqueItemSizeLimitedPriorityQueue;
import LivinGrimoire.DiSkillV2;
import LivinGrimoire.LGFIFO;
import LivinGrimoire.PlayGround;

public class DiBurper extends DiSkillV2 {
    // makes the AI burp n  times per hour at random times
    private int burpsPerHour = 2;
    private TrgMinute trgMinute = new TrgMinute(0);
    private Responder responder1 = new Responder("burp","burp2","burp3");
    private DrawRndDigits draw = new DrawRndDigits();
    private LGFIFO<Integer> burpMinutes = new LGFIFO<>();
    private PlayGround pl = new PlayGround();
    public DiBurper(int burpsPerHour) {
        super();
        if((burpsPerHour >0)&& (burpsPerHour <60)){
            this.burpsPerHour = burpsPerHour;
        }
        for (int i = 1; i < 60; i++) {
            draw.addElement(i);
        }
        for (int i = 0; i < burpsPerHour; i++) {
            burpMinutes.add(draw.draw());
        }
    }

    @Override
    public void input(String ear, String skin, String eye) {
        // night? do not burp
        if (pl.partOfDay().equals("night")){
            return;
        }
        // reset burps
        if (trgMinute.trigger()){
            burpMinutes.clear();
            draw.reset();
            for (int i = 0; i < burpsPerHour; i++) {
                burpMinutes.add(draw.draw());
            }
            return;
        }
        // burp
        int nowMinutes = pl.getMinutesAsInt();
        if(burpMinutes.contains(nowMinutes)){
            burpMinutes.removeItem(nowMinutes);
            this.outAlg = this.diSkillUtils.simpleVerbatimAlgorithm("burp",responder1.getAResponse());
            return;
        }
    }
}
