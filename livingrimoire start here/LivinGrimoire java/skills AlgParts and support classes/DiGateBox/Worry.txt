package com.yotamarker.lgkotlin1;

public class Worry extends GrimoireModule {
    private PlayGround playGround = new PlayGround();
    private String atHome = ""; // time to expect user to be home
    private int counter = 0;
    private PheonixSender sendGate = new PheonixSender();
    private Boolean limiter = false;// makes the AI worry only once
    private Boolean worry = false;
    private String log = "";
    private Kokoro kokoro;

    public String getAtHome() {
        return atHome;
    }

    public Worry(Kokoro kokoro) {
        this.kokoro = kokoro;
        this.atHome = this.kokoro.grimoireMemento.simpleLoad("worry_time");
    }

    @Override
    public int input(String ear, String skin, String eye) {
        // got home ? save time
        if (ear.equals("i am home")) {
            limiter = false;
            atHome = playGround.getPastInXMin(20);
            this.kokoro.grimoireMemento.simpleSave("worry_time",atHome);
            counter = 0;
            worry = false;
            return 0;
        }
        // hmm user should be home soon
        if (playGround.getCurrentTimeStamp().equals(atHome) && !limiter) {
            // send only once
            if (sendGate.sendable()) {
                limiter = true;
                return 1;
            }
        }
        // user is late I am worried :
        if (worry) {
            worry = false;
            return 2;
        } // worry algorithm
        return 0;
    }

    public void standby() {
        sendGate.standByReset();
        if (limiter) {
            if (counter > 7) {
                return;
            }
            if (counter == 7) {
                worry = true;
            }
            counter++;
        }
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        if (log.startsWith("i am going to")) {
            this.log = log.replace("i am going to", "");
        }
    }
}
