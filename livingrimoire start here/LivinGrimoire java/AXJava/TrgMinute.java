package AXJava;

import LivinGrimoire.PlayGround;

import java.util.Random;

public class TrgMinute extends TrGEV3{
    // trigger true at minute once per hour
    private int hour1 = -1;
    int minute;
    private final PlayGround pl = new PlayGround();
    public TrgMinute() {
        Random rand = new Random();
        minute = rand.nextInt(60);
    }

    public TrgMinute(int minute) {
        this.minute = minute;
    }

    @Override
    public Boolean trigger() {
        int tempHour = pl.getHoursAsInt();
        if(tempHour!=hour1){
            if(pl.getMinutesAsInt() == minute){
                hour1 = tempHour;
                return true;
            }
        }
        return false;
    }

    @Override
    public void reset() {
        hour1 = -1;
    }
}
