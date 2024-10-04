package Auxiliary_Modules;

import java.util.Enumeration;
import java.util.Hashtable;

public class AXStrategy {
    /* this auxiliary module is used to output strategies based on context
        can be used for battles, and games
        upon pain/lose use the evolve methode to update to different new active strategies
        check for battle state end externaly (opponent state/hits on rival counter)
    a dictionary of strategies*/
    private final int lim;
    private final Hashtable<String,Strategy> strategies = new Hashtable<>();

    public AXStrategy(int lim) {
        // limit of active strategies (pulled from all available strategies)
        this.lim = lim;
    }

    public void addStrategy(String context, DrawRnd techniques){
        // add strategies per context
        Strategy temp = new Strategy(techniques);
        temp.evolveStrategies(lim);
        this.strategies.put(context,temp);
    }
    public void evolve(){
        // replace active strategies
        Enumeration<String> e = this.strategies.keys();
        String key;
        while (e.hasMoreElements()){
            key = e.nextElement();
            this.strategies.get(key).evolveStrategies(lim);
        }
    }
    public String process(String context){
        // process input, return action based on game context now
        if(this.strategies.containsKey(context)){
            return this.strategies.get(context).getStrategy();
        }
        return "";
    }
}
