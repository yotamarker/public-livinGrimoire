package AXJava;

public class AXContextCmd {
    // engage on commands
    // when commands are engaged, context commans can also engage
    public UniqueItemSizeLimitedPriorityQueue commands = new UniqueItemSizeLimitedPriorityQueue();
    public UniqueItemSizeLimitedPriorityQueue contextCommands = new UniqueItemSizeLimitedPriorityQueue();
    public TrgTolerance trgTolerance  = new TrgTolerance(3);
    public Boolean engageCommand(String s1){
        if (commands.contains(s1)){
            trgTolerance.reset();
            return true;
        }
        if (!trgTolerance.trigger()){
            return false;
        }
        return contextCommands.contains(s1);
    }
    public void setInputWait(int thinkCycles){
        trgTolerance.setMaxrepeats(thinkCycles);
    }
    public void disable(){
        // context commands are disabled till next engagement with a command
        trgTolerance.disable();
    }
}
