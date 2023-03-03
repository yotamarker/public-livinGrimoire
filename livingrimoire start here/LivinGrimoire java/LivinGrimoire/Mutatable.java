package LivinGrimoire;

public abstract class Mutatable {
    // one part of an algorithm, it is a basic simple action or sub goal
    public abstract String action(String ear, String skin, String eye);
    public abstract Boolean completed();
    public abstract Mutatable clone();
    public enumFail failure(String input){
        // Failure type only mutatable may use enumFail.fail
        return enumFail.ok;
    }

    public int getMutationLimit(){
        /*
         * override this to the number of mutations a mutation series can perform, so at
         * least to 1 if you want mutations enabled.
         */
        return 0;
    }
    public  String myName(){
        // Returns the class name
        return this.getClass().getSimpleName();
    }
    public Mutatable mutation(){
        return null;
    }
}
