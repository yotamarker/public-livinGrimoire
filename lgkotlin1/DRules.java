package com.yotamarker.lgkotlin1;


import java.util.ArrayList;
import java.util.Hashtable;

public class DRules extends AbsCmdReq implements Neuronable {
    // sends a sleepgorithm out. triggered at sleep hour.
    private AbsAlgPart apSleep;
    private PlayGround playGround = new PlayGround();
    private Hashtable<String, String> zettaiRules = new Hashtable<>();
    private BlockTime blockTime = new BlockTime();
    private String todo = "";
    // private int sleepMinute = 0;
    private Chobit.InnerClass actualSleep;

    public DRules(APSleep apSleep, Chobit.InnerClass actualSleep) {
        super();
        this.apSleep = apSleep;
        int sleepTime = apSleep.getWakeTime() - 2;
        if (sleepTime > 24) {
            sleepTime -= 24;
        }
        zettaiRules.put(sleepTime + ":00", "sleep");
        this.actualSleep = actualSleep;
        // can add 01 02 minutes
        // this.sleepHour = sleepHour;
    }

    @Override
    public void output(Neuron noiron) {
        switch (todo) {
            case "sleep":
                ArrayList<AbsAlgPart> algParts1 = new ArrayList<>();
                APSleep0 apSleep0 = new APSleep0(actualSleep);
                algParts1.add(apSleep0);
                algParts1.add(this.apSleep);
                Algorithm algorithm = new Algorithm("sleep", "going to sleep", algParts1);
                noiron.algParts.add(algorithm);
                break;

            default:
                break;
        }
    }

    @Override
	public void input(String ear, String skin, String eye) {
        // TODO Auto-generated method stub
        int h = playGround.getHoursAsInt();
        int m = playGround.getMinutesAsInt();
        String strH = (h < 10) ? "0" + h : h + "";
        String strM = (m < 10) ? "0" + m : m + "";
        ;
        String Time = strH + ":" + strM;
        // zettaiRules.put(Time, "sleep"); // for testing
        Boolean boolean1 = zettaiRules.containsKey(Time);
        if ((!blockTime.unBlock(h, m)) && boolean1) {
            todo = zettaiRules.get(Time);
            blockTime.setBlock(h, m);
        }
    }
}
