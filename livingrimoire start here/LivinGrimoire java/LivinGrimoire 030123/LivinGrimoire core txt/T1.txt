package LivinGrimoire;

public class T1 extends Mutatable {
    Boolean isCompleted = false;
    @Override
    public String action(String ear, String skin, String eye) {
        this.isCompleted = true;
        return "I am a T1 object";
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
        System.out.println("T1 cloning another T1 object");
        return new T1();
    }

    @Override
    public Mutatable mutation() {
        System.out.println("T1 mutating into T2");
        return new T2(); // modify to t2
    }
}
