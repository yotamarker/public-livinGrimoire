package skills;

import AXJava.*;
import LivinGrimoire.DiSkillV2;
import LivinGrimoire.LGFIFO;
import LivinGrimoire.PlayGround;

public class DiPetV3 extends DiSkillV2 {
    // chirp, learn replies and reply back occasionally.
    private int chirpsLim = 2;
    private TrgMinute trgMinute = new TrgMinute(0);
    private Responder1Word responder1 = new Responder1Word();
    private DrawRndDigits chirpMinutes = new DrawRndDigits();
    private LGFIFO<Integer> burpMinutes = new LGFIFO<>();
    private PlayGround pl = new PlayGround();
    public DiPetV3(int chirpsLim) {
        super();
        if((chirpsLim >0)&& (chirpsLim <60)){
            this.chirpsLim = chirpsLim;
        }
        for (int i = 1; i < 60; i++) {
            chirpMinutes.addElement(i);
        }
        for (int i = 0; i < this.chirpsLim; i++) {
            burpMinutes.add(chirpMinutes.draw());
        }
    }

    @Override
    public void input(String ear, String skin, String eye) {
        // night? return;
        if (pl.partOfDay().equals("night")){
            return;
        }
        // reset chirps as hoyr starts
        if (trgMinute.trigger()){
            burpMinutes.clear();
            chirpMinutes.reset();
            for (int i = 0; i <chirpsLim ; i++) {
                burpMinutes.add(chirpMinutes.draw());
            }
            return;
        }
        // chirp
        int nowMinutes = pl.getMinutesAsInt();
        if(burpMinutes.contains(nowMinutes)){
            burpMinutes.removeItem(nowMinutes);
            this.outAlg = this.diSkillUtils.simpleVerbatimAlgorithm("petv3",responder1.getAResponse());
            return;
        }
        // chirp back to input
        if(responder1.contains(ear)){
            int n = chirpMinutes.getSimpleRNDNum(100);
            if (n<35){
                this.outAlg = this.diSkillUtils.simpleVerbatimAlgorithm("burp_hkn",responder1.getAResponse());
            }
        }
        // listen n learn recent single words
        responder1.listen(ear);
    }
}
