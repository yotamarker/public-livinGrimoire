package Auxiliary_Modules;

public class InputFilter {
    // filter out non-relevant input
    // or filter in relevant data
    public String filter(String ear, String skin, String eye){
        // override me
        return "";
    }
    public AXKeyValuePair filter(String ear){
        // override me : key = context/category, value: param
        return new AXKeyValuePair();
    }
}
