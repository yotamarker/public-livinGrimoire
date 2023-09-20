package skills;

import AXJava.*;
import LivinGrimoire.DiSkillV2;
import LivinGrimoire.LGFIFO;
import LivinGrimoire.PlayGround;

public class DiSneezer extends DiSkillV2 {
    // the skill simulates sneezing as a result of cold temperature
    private int sneezeLim = 2;
    private TrgMinute trgMinute = new TrgMinute(0);
    private Responder responder1 = new Responder("sneeze","achoo", "atchoo", "achew", "atisshoo");
    private DrawRndDigits chirpMinutes = new DrawRndDigits();
    private LGFIFO<Integer> burpMinutes = new LGFIFO<>();
    private PlayGround pl = new PlayGround();
    public DiSneezer(int sneezeLim) {
        super();
        if((sneezeLim >0)&& (sneezeLim <60)){
            this.sneezeLim = sneezeLim;
        }
        for (int i = 1; i < 60; i++) {
            chirpMinutes.addElement(i);
        }
        for (int i = 0; i < this.sneezeLim; i++) {
            burpMinutes.add(chirpMinutes.draw());
        }
    }

    @Override
    public void input(String ear, String skin, String eye) {
        // reset sneezes, you can use device temperature as an alternative condition
        if (ear.contains("cold")){
            burpMinutes.clear();
            chirpMinutes.reset();
            for (int i = 0; i < sneezeLim; i++) {
                burpMinutes.add(chirpMinutes.draw());
            }
            return;
        }
        // sneeze
        int nowMinutes = pl.getMinutesAsInt();
        if(burpMinutes.contains(nowMinutes)){
            burpMinutes.removeItem(nowMinutes);
            this.outAlg = this.diSkillUtils.simpleVerbatimAlgorithm("petv3",responder1.getAResponse());
            return;
        }
    }
}
