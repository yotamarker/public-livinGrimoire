package skills;

import AXJava.DrawRndDigits;
import AXJava.Responder;
import AXJava.TrgMinute;
import LivinGrimoire.DiSkillV2;
import LivinGrimoire.LGFIFO;
import LivinGrimoire.PlayGround;

public class DiEngager extends DiSkillV2 {
    // makes the AI burp n  times per hour at random times
    private int burpsPerHour = 2;
    private TrgMinute trgMinute = new TrgMinute(0);
    private String skillToEngage = "unknown";
    private DrawRndDigits draw = new DrawRndDigits();
    private LGFIFO<Integer> burpMinutes = new LGFIFO<>();
    private PlayGround pl = new PlayGround();
    public DiEngager(int burpsPerHour, String skillToEngage) {
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
        this.skillToEngage = skillToEngage;
    }

    public void setSkillToEngage(String skillToEngage) {
        this.skillToEngage = skillToEngage;
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
            this.kokoro.toHeart.put(skillToEngage, "engage");
            return;
        }
    }
}