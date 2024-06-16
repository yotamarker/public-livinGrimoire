package Auxiliary_Modules;

public class Strategy {
    private final UniqueItemSizeLimitedPriorityQueue activeStrategy; // active strategic options
    public DrawRnd allStrategies; // bank of all strategies. out of this pool active strategies are pulled

    public Strategy(DrawRnd allStrategies) {
        // create the strategy Object with a bank of options
        this.allStrategies = allStrategies;
        this.activeStrategy = new UniqueItemSizeLimitedPriorityQueue();
    }
    public void evolveStrategies(int strategiesLimit){
        // add N strategic options to the active strategies bank, from the total strategy bank
        activeStrategy.setLimit(strategiesLimit);
        String temp = allStrategies.draw();
        for (int i = 0; i < strategiesLimit; i++) {
            if(temp.isEmpty()){
                break;
            }
            activeStrategy.add(temp);
            temp = allStrategies.draw();
        }
        allStrategies.reset();
    }
    public String getStrategy(){
        return this.activeStrategy.getRNDElement();
    }
}
