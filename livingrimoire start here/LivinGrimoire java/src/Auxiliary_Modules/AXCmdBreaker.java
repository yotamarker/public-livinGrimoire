package Auxiliary_Modules;

public class AXCmdBreaker {
    // separate command parameter from the command
    public String conjuration;

    public AXCmdBreaker(String conjuration) {
        this.conjuration = conjuration;
    }
    public String extractCmdParam(String s1){
        if (s1.contains(conjuration)){
            return s1.replace(conjuration,"").trim();
        }
        return "";
    }
}
