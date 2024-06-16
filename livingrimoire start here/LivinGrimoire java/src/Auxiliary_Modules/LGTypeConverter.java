package Auxiliary_Modules;
import LivinGrimoire.*;

public class LGTypeConverter {
    private final RegexUtil r1 = new RegexUtil();
    public int convertToInt(String v1){
        String temp = r1.extractRegex(enumRegexGrimoire.integer, v1);
        if (temp.isEmpty()) {return 0;}
        return Integer.parseInt(temp);
    }
    public Double convertToDouble(String v1){
        String temp = r1.extractRegex(enumRegexGrimoire.double_num, v1);
        if (temp.isEmpty()) {return 0.0;}
        return Double.parseDouble(temp);
    }
}
