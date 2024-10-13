package Auxiliary_Modules;

import java.util.Random;

public class TrgMinute extends TrGEV3{
    // trigger true at minute once per hour
    private int hour1 = -1;
    int minute;
    public TrgMinute() {
        Random rand = new Random();
        minute = rand.nextInt(60);
    }

    public TrgMinute(int minute) {
        this.minute = minute;
    }

    @Override
    public Boolean trigger() {
        int tempHour = TimeUtils.getHoursAsInt();
        if(tempHour!=hour1){
            if(TimeUtils.getMinutesAsInt() == minute){
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
