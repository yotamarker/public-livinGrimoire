package Skills.logical;

import Auxiliary_Modules.*;
import LivinGrimoire.Skill;

public class DiBurper extends Skill {
    // makes the AI burp n  times per hour at random times
    private int burpsPerHour = 2;
    private TrgMinute trgMinute = new TrgMinute(0);
    private Responder responder1 = new Responder("burp","burp2","burp3");
    private DrawRndDigits draw = new DrawRndDigits();
    private LGFIFO<Integer> burpMinutes = new LGFIFO<>();
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

    public void setResponder1(Responder responder1) {
        // set sounds on burp event
        this.responder1 = responder1;
    }

    @Override
    public void input(String ear, String skin, String eye) {
        // night? do not burp
        if (TimeUtils.partOfDay().equals("night")){
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
        int nowMinutes = TimeUtils.getMinutesAsInt();
        if(burpMinutes.contains(nowMinutes)){
            burpMinutes.removeItem(nowMinutes);
            setSimpleAlg(responder1.getAResponse());
        }
    }
}
