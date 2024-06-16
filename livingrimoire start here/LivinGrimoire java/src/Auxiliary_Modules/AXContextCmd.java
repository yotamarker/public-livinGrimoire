package Auxiliary_Modules;

public class AXContextCmd {
    // engage on commands
    // when commands are engaged, context commans can also engage
    public UniqueItemSizeLimitedPriorityQueue commands = new UniqueItemSizeLimitedPriorityQueue();
    public UniqueItemSizeLimitedPriorityQueue contextCommands = new UniqueItemSizeLimitedPriorityQueue();
    public Boolean trgTolerance  = false;
    public Boolean engageCommand(String s1){
        if (s1.isEmpty()){return false;}
        if (contextCommands.contains(s1)){
            trgTolerance = true;
            return true;
        }
        if (trgTolerance && !commands.contains(s1)){
            trgTolerance = false;
            return false;
        }
        return trgTolerance;
    }
    public void disable(){
        // context commands are disabled till next engagement with a command
        trgTolerance = false;
    }
}
