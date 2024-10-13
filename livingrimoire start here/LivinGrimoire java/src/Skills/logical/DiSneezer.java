package Skills.logical;


import Auxiliary_Modules.*;
import LivinGrimoire.Skill;


public class DiSneezer extends Skill {
    // the skill simulates sneezing as a result of cold temperature
    private int sneezeLim = 2;
    private TrgMinute trgMinute = new TrgMinute(0);
    private Responder responder1 = new Responder("sneeze","achoo", "atchoo", "achew", "atisshoo");
    private DrawRndDigits chirpMinutes = new DrawRndDigits();
    private LGFIFO<Integer> burpMinutes = new LGFIFO<>();
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
        int nowMinutes = TimeUtils.getMinutesAsInt();
        if(burpMinutes.contains(nowMinutes)){
            burpMinutes.removeItem(nowMinutes);
            this.outAlg = simpleVerbatimAlgorithm("petv3",responder1.getAResponse());
            return;
        }
    }
}
