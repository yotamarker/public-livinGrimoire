package com.yotamarker.lgkotlin1;

import java.util.Random;

public class APSoulConvo extends AbsAlgPart {
    private String reply = "";
    private ComplimentRecog complimentRecog = new ComplimentRecog();
    private EmoRecog emorecog = new EmoRecog();
    private String[] replies = { "chiinormal", "chiihappy", "chiiexcited", "chiicurious", "chiiangry" };
    private Random rand = new Random();
    private CldBool cldBool;
    private ZeroTimeGate timeGate;

    public APSoulConvo(CldBool cldBool, ZeroTimeGate timeGate) {
        super();
        this.cldBool = cldBool;
        this.timeGate = timeGate;
    }

    @Override
    public String action(String ear, String skin, String eye) {
        reply = "";
        // exit conditions :
        if (timeGate.isClosed()) {
            cldBool.setModeActive(false);
            return "";
        }
        switch (ear) {
            case "ok":
            case "okay":
            case "stop":
            case "stop it":
            case "shut up":
            case "be quiet":
                cldBool.setModeActive(false);
                return "";
        }
        if(skin.contains("pain")){cldBool.setModeActive(false);return "";}
        if (ear.isEmpty()) {
            return "";
        }
        // determine reply, renew gate
        timeGate.open(1);
        reply = complimentRecog.isCompliment(ear);
        if (!reply.isEmpty()) {
            return reply;
        }
        if (emorecog.isCurious(ear)) {
            reply = "chiicurious";
            return reply;
        }
        if (emorecog.isAngry(ear)) {
            reply = "chiiangry";
            return reply;
        }
        if (emorecog.isHappy(ear)) {
            reply = "chiihappy";
            return reply;
        }
        if (emorecog.isExcited(ear)) {
            reply = "chiiexcited";
            return reply;
        }
        int x = rand.nextInt(replies.length);
        reply = replies[x];
        return reply;
    }

    @Override
    public Boolean itemize() {
        return false;
    }

    @Override
    public enumFail failure(String input) {
        return enumFail.ok;
    }

    @Override
    public Boolean completed() {
        return !this.cldBool.getModeActive();
    }

    @Override
    public AbsAlgPart clone() {
        return new APSoulConvo(this.cldBool, this.timeGate);
    }

}
