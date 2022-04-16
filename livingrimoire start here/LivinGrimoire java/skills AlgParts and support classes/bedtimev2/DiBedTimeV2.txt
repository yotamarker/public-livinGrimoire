package com.yotamarker.lgkotlin1;

public class DiBedTimeV2 extends DiSkillV2 {
    //simple ABDL bed time skill
    private PheonixSender pheonixSender = new PheonixSender();
    private PheonixSender pheonixSender2 = new PheonixSender();
    private PlayGround playGround = new PlayGround();
    private Responder responder = new Responder(
            "bad boy#lullabye#go to bed now#be a good boy for me#mommy knows best#time for beddy bye#you need your rest baby");
    private Responder responder2 = new Responder(
            "bad boy#go to bed now#you just earned corner time little boy#hush hush baby go back to bed#march to your room mister#i am going to spank you over my knee");
    private int log = 0;

    public DiBedTimeV2(Kokoro kokoro) {
        super(kokoro);
        String temp = kokoro.grimoireMemento.simpleLoad("bedtime_log");
        switch (temp) {
            case "1":
                log = 1;
                break;
            case "2":
                log = 2;
                break;
            default:
                log = 0;
                break;
        }
    }

    @Override
    public void input(String ear, String skin, String eye) {
        // convo n logs
        pheonixSender.standByReset();// reset send only once gate
        if (playGround.isNight()) {
            pheonixSender2.standByReset();
            if (playGround.getHoursAsInt() == 2 && playGround.getMinutesAsInt() < 10 && ear.contains("mommy")) {
                outAlg = this.diSkillUtils.simpleVerbatimAlgorithm("bed_v2", responder2.getResponse());
                kokoro.grimoireMemento.simpleSave("bedtime_log","2");
                return;
            }
            switch (ear) {
                case "i am not tired":
                case "i do not want to sleep":
                case "shut up":
                    outAlg = this.diSkillUtils.simpleVerbatimAlgorithm("bed_v2", responder.getResponse(),
                            responder.getResponse());
                    kokoro.grimoireMemento.simpleSave("bedtime_log","1");
                    return;
                default:
                    break;
            }
            if (playGround.getCurrentTimeStamp().equals("02:05") && pheonixSender2.sendable()) {
                outAlg = this.diSkillUtils.simpleVerbatimAlgorithm("bed_ecchi", "ecchi 1");
                return;
            }
        }
        //bed time lullabye or log
        if (playGround.getCurrentTimeStamp().equals("20:00") && pheonixSender.sendable()) {
            switch (log) {
                case 1:
                    outAlg = this.diSkillUtils.simpleVerbatimAlgorithm("bed_v2", "it is bedtime, and no fussing this time little one");
                    kokoro.grimoireMemento.simpleSave("bedtime_log","0");
                    return;
                case 2:
                    outAlg = this.diSkillUtils.simpleVerbatimAlgorithm("bed_v2", "it is bedtime, and do not interrupt me like yesterday");
                    kokoro.grimoireMemento.simpleSave("bedtime_log","0");
                    return;

                default:
                    outAlg = this.diSkillUtils.simpleVerbatimAlgorithm("bed_v2", "lullabye");
                    return;
            }
        }

    }
}
