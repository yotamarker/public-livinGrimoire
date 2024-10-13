package Skills.special_skills;

import Auxiliary_Modules.DrawRndDigits;
import Auxiliary_Modules.LGFIFO;
import Auxiliary_Modules.TimeUtils;
import Auxiliary_Modules.TrgMinute;
import LivinGrimoire.Skill;

public class DiEngager extends Skill {
    // makes the AI burp n  times per hour at random times
    private int burpsPerHour = 2;
    private TrgMinute trgMinute = new TrgMinute(0);
    private String skillToEngage = "unknown";
    private DrawRndDigits draw = new DrawRndDigits();
    private LGFIFO<Integer> burpMinutes = new LGFIFO<>();
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
            this.kokoro.toHeart.put(skillToEngage, "engage");
            return;
        }
    }
}