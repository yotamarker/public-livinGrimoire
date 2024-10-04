package Auxiliary_Modules;

public class ButtonEngager {
    // detect if a button was pressed
    // this class disables phisical button engagement while it remains being pressed
    Boolean prev_state = false;
    public Boolean engage(Boolean btnState){
        // send true for pressed state
        if(prev_state != btnState){
            prev_state = btnState;
            return btnState;
        }
        return false;
    }
}
