package com.yotamarker.lgkotlin1;

public class DiGateBox extends DiSkillV2{
    // this will make the AI miss the user and also give him a shout out a while before he returns home
    /* a lot of possible beefups here
    * like connecting the output cases to missing persons algorithm
    * or setting up lights and warm the water or make coffee before the user returns
    * as well as exchanging info about the bots and users day
    * */
    private Worry worry;
    private Responder responder = new Responder("bye bye#i shall miss you#have a good day#i love you", (byte)4);
    public DiGateBox(Kokoro kokoro) {
        super(kokoro);
        this.worry = new Worry(kokoro);

    }
    @Override
    public void input(String ear, String skin, String eye) {
        int temp = worry.input(ear, "", "");
        worry.standby();
        worry.setLog(ear);
        if(temp > 0){
            switch(temp) {
                case 1:
                    this.outAlg = this.diSkillUtils.simpleVerbatimAlgorithm("misses_you", "come back");
                    return;
                case 2:
                    this.outAlg = this.diSkillUtils.simpleVerbatimAlgorithm("worried", "come back");
                    return;
                default:
                    // code block
            }
        }
        switch(ear) {
            case "i am home":
                this.outAlg = this.diSkillUtils.simpleVerbatimAlgorithm("welcome4","welcome home");
                return;
            case "when do i get home":
                this.outAlg = this.diSkillUtils.simpleVerbatimAlgorithm("arrival_home",worry.getAtHome());
                return;
            case "i am leaving":
                this.outAlg = this.diSkillUtils.simpleVerbatimAlgorithm("bye",responder.getResponse());
                return;
            case "what was i going to do": case"what did i want to do":
                this.outAlg = this.diSkillUtils.simpleVerbatimAlgorithm("leaver_log","you were going to "+worry.getLog());
                return;
            default:
                // code block
        }
    }
}
