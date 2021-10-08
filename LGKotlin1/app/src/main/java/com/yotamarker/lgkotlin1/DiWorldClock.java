package com.yotamarker.lgkotlin1;

public class DiWorldClock extends DiSkillV2 {
    private TimeZoneUtil timeZoneUtil = new TimeZoneUtil();

    public DiWorldClock(Kokoro kokoro) {
        super(kokoro);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void input(String ear, String skin, String eye) {
        if (!ear.contains("what is the time at ")) {
            return;
        }
        String loacation = ear.replace("what is the time at ", "");
        this.outAlg = this.diSkillUtils.simpleVerbatimAlgorithm("world_clock", timeZoneUtil.timeAt(loacation));
    }

}
