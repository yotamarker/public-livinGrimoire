package Auxiliary_Modules;

public class Differ {
    private int powerLevel = 90;
    private int difference = 0;

    public int getPowerLevel() {
        return powerLevel;
    }

    public int getDifference() {
        return difference;
    }
    public void clearPowerLVDifference(){
        difference = 0;
    }
    public void samplePowerLV(int pl){
        // pl is the current power level
        difference = pl - powerLevel;
        powerLevel = pl;
    }
}
