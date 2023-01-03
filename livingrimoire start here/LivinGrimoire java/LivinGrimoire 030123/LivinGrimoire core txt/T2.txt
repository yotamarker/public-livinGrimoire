package LivinGrimoire;

public class T2 extends Mutatable {
    Boolean isCompleted = false;
    @Override
    public String action(String ear, String skin, String eye) {
        this.isCompleted = true;
        return "I am a T2 object";
    }

    @Override
    public int getMutationLimit() {
        return 1;
    }

    @Override
    public Boolean completed() {
        return this.isCompleted;
    }

    @Override
    public Mutatable clone() {
        System.out.println("T2 cloning another T2 object");
        return new T2();
    }

    @Override
    public Mutatable mutation() {
        System.out.println("T2 mutating into T1");
        return new T2(); // modify to t2
    }
}
