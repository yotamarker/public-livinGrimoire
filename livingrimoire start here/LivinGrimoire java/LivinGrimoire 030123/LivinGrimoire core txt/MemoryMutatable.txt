package LivinGrimoire;

public class MemoryMutatable extends Mutatable{
    /*
     * an adaptor pattern to the Mutatable (algorithm part)
     * the object will load the last mutated state which is the last and optimized
     * mutation used.
     * upon mutation the new last mutation is saved so it can be loaded for the next time
     * mutations happen in the DExplorer class and triggered when the Mutatbles' failure method
     * returns enumFail.failure
     * you can code said enumFail.failure to return under chosen conditions in the action method of
     * the MemoryMutatable object. (sub class of this class)
     */
    public Kokoro kokoro;
    public Mutatable aPart;

    public MemoryMutatable(Kokoro kokoro, Mutatable aPart) {
        super();
        this.kokoro = kokoro;
        // load the last saved mutatable
        this.aPart = kokoro.grimoireMemento.load(aPart);
    }

    public String actualAction(String ear, String skin, String eye) {
        return aPart.action(ear, skin, eye);
    }
    @Override
    public String action(String ear, String skin, String eye) {
        kokoro.in(this);
        String result = actualAction(ear, skin, eye);
        kokoro.out(completed(), failure(""));
        return result;
    }
    @Override
    public enumFail failure(String input) {
        return aPart.failure(input);
    }

    @Override
    public Boolean completed() {
        return aPart.completed();
    }

    @Override
    public Mutatable clone() {
        return new MemoryMutatable(kokoro, aPart.clone());
    }
    @Override
    public int getMutationLimit() {
        return aPart.getMutationLimit();
    }

    @Override
    public Mutatable mutation() {
        // upon mutation the last mutation is saved
        Mutatable mutant = (Mutatable) aPart;
        Mutatable tempAP = mutant.mutation();
        kokoro.grimoireMemento.reqquipMutation(tempAP.getClass().getSimpleName());
        return new MemoryMutatable(kokoro,  tempAP);
    }

    @Override
    public String myName() {
        return aPart.myName();
    }
}
