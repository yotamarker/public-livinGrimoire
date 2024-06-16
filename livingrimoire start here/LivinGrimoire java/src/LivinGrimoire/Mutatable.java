package LivinGrimoire;

public abstract class Mutatable {
    // one part of an algorithm, it is a basic simple action or sub goal
    public Boolean algKillSwitch = false;
    public abstract String action(String ear, String skin, String eye);
    public abstract Boolean completed();
    public  String myName(){
        // Returns the class name
        return this.getClass().getSimpleName();
    }

}
