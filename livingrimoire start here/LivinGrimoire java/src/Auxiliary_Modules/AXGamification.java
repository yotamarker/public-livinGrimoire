package Auxiliary_Modules;

public class AXGamification {
    // this auxiliary module can add fun to tasks, skills, and abilities simply by
    // tracking their usage, and maximum use count.
    private int counter = 0;
    private int max = 0;

    public int getCounter() {
        return counter;
    }

    public int getMax() {
        return max;
    }
    public void resetCount(){
        counter = 0;
    }
    public void resetAll(){
        max = 0;
        counter = 0;
    }
    public void increment(){
        counter++;
        if (counter > max) {
            max = counter;
        }
    }
    public void incrementBy(int n){
        counter+= n;
        if (counter > max) {
            max = counter;
        }
    }
    public Boolean reward(int cost){
        // game grind points used for rewards
        // consumables, items or upgrades this makes games fun
        if (cost>counter){return false;}
        counter-=cost;
        return true;
    }
    public Boolean surplus(int cost){
        // has surplus for reward?
        if (cost>counter){return false;}
        return true;
    }
}
