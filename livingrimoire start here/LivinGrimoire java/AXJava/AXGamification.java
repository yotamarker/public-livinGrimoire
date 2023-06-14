package AXJava;

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
    public void increament(){
        counter++;
        if (counter > max) {
            max = counter;
        }
    }
}
