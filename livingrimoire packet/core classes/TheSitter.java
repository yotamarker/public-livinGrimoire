package com.yotamarker.lgkotlin1;

import java.util.ArrayList;

public class TheSitter extends TheSkill {
    /*
     * prayer1 : Thank You, God, for Loving Me! The sun is setting, dear Father, and
     * it’s time to go to bed. Thank you, God, for loving me from my toesies to my
     * head. Tomorrow will be another fun and silly and busy day. Please keep me
     * safe as I run and jump and giggle and sing and play.
     */
    private DISitter diSitter = null;
    private InstaConvo instaConvo = new InstaConvo();
    // tick trigger values :
    private String sent1 = "";
    private int algMode = 0;
    private ZeroTimeGate tGSitter = new ZeroTimeGate(0);
    // end tick triggers
    public TheSitter(Kokoro kokoro, String clsName) {
        super(kokoro, null, null, clsName);
        ArrayList<String> items = new ArrayList<String>();
        items.add("pacifier");
        items.add("diaper");
        Person friend = new Person();
        MCodes mCodes = new MCodes();
        AbsDefconV2 absDefconV2 = new ABDLDefcon(mCodes, friend);
        this.setFriend(friend);
        this.setmCodes(mCodes);
        this.setAbsDefCon(absDefconV2);
        this.setItems(items);
        // absorbtion of old skill :
        this.diSitter = new DISitter(kokoro);
        // insta convos set up
        instaConvo.loadBullet("i love you", "i love you too").loadBullet("i love you", "really", "of course kido");
        instaConvo.loadBullet("what is your objective", "to nurse and protect");
    }

    @Override
    protected void trgAction(String ear, String skin, String eye) {
        // absorb old diskill capabilities
        outputAlg = absorbedSkill(ear, skin, eye, diSitter);
        if (outputAlg != null) {
            return;
        }
        if (this.friend.getActive()) {
            String ic1 = instaConvo.converse(ear.toLowerCase());
            if (!ic1.isEmpty()) {
                outputAlg = diSkillUtil.verbatimGorithm(new APVerbatim(ic1));
                return;
            }
        }
    }
    @Override
    protected void trgExplore(String ear, String skin, String eye) {
        String now = playGround.getCurrentTimeStamp();
        if (!sent1.equals(now)) {
            sent1 = "";
            switch (now) {
                case "05:00":
                    if (playGround.getMonthAsInt() == 1) {
                        this.friend.deleteFriend();
                    }
                    sent1 = "05:00";
                    return;
                case "01:00":
                    outputAlg = diSkillUtil.verbatimGorithm(new APVerbatim("filthx1"));// masturbatin
                    sent1 = "01:00";
                    algMode = 1;// context
                    tGSitter.open(50);
                    return;
                case "19:05":
                    sent1 = "19:05";
                    outputAlg = diSkillUtil.verbatimGorithm(new APVerbatim("prayer1"));
                    return;
                default:
                    break;
            }
            // end if
        }
        switch (algMode) {
            case 1:
                if (tGSitter.isClosed() || ear.contains("no") || ear.contains("problem")) {
                    algMode = 0;
                }
                if (ear.contains("mommy")) {
                    outputAlg = diSkillUtil
                            .verbatimGorithm(
                                    new APVerbatim("bad boy interupting", "go stand in the corner for 10 minutes"));
                    return;
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void trgPreserve(String ear, String skin, String eye) {


    }

    @Override
    public Boolean auto() {
        return true;
    }
}

