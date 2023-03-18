package AXJava;

public class AXLearnability {
    private Boolean algSent = false;
    // problems that may result because of the last deployed algorithm:
    public UniqueItemSizeLimitedPriorityQueue defcons = new UniqueItemSizeLimitedPriorityQueue();// default size = 5
    // major chaotic problems that may result because of the last deployed algorithm:
    public UniqueItemSizeLimitedPriorityQueue defcon5 = new UniqueItemSizeLimitedPriorityQueue();
    // goals the last deployed algorithm aims to achieve:
    public UniqueItemSizeLimitedPriorityQueue goals = new UniqueItemSizeLimitedPriorityQueue();
    // how many failures / problems till the algorithm needs to mutate (change)
    public TrgTolerance trgTolerance;

    public AXLearnability(int tolerance) {
        this.trgTolerance = new TrgTolerance(tolerance);
    }
    public void pendAlg(){
        // an algorithm has been deployed
        // call this method when an algorithm is deployed (in a DiSkillV2 object)
        algSent = true;
        trgTolerance.trigger();
    }
    public void pendAlgWithoutConfirmation(){
        // an algorithm has been deployed
        algSent = true;
        //no need to await for a thank you or check for goal manifestation :
        // trgTolerance.trigger();
        // using this method instead of the default "pendAlg" is the same as
        // giving importance to the stick and not the carrot when learning
        // this method is mosly fitting work place situations
    }
    public Boolean mutateAlg(String input){
        // recommendation to mutate the algorithm ? true/ false
        if (!algSent) {return false;} // no alg sent=> no reason to mutate
        if (goals.contains(input)){trgTolerance.reset();algSent = false;return false;}
        // goal manifested the sent algorithm is good => no need to mutate the alg
        if (defcon5.contains(input)) {trgTolerance.reset();algSent = false; return true;}
        // ^ something bad happend probably because of the sent alg
        // recommend alg mutation
        if (defcons.contains(input)){algSent = false;return trgTolerance.trigger();}
        // ^ negative result, mutate the alg if this occures too much
        return false;
    }
}
