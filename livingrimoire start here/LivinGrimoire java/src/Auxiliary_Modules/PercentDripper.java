package Auxiliary_Modules;

public class PercentDripper {
    private final DrawRnd dr = new DrawRnd();
    private int limis = 35;

    public void setLimis(int limis) {
        this.limis = limis;
    }
    public boolean drip(){
        return dr.getSimpleRNDNum(100) < limis;
    }
    public boolean dripPlus(int plus){
        return dr.getSimpleRNDNum(100) < limis + plus;
    }
}
