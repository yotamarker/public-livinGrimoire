package Auxiliary_Modules;
import LivinGrimoire.*;

public class LGTypeConverter {
    public int convertToInt(String v1){
        String temp = RegexUtil.extractRegex(enumRegexGrimoire.integer, v1);
        if (temp.isEmpty()) {return 0;}
        return Integer.parseInt(temp);
    }
    public Double convertToDouble(String v1){
        String temp = RegexUtil.extractRegex(enumRegexGrimoire.double_num, v1);
        if (temp.isEmpty()) {return 0.0;}
        return Double.parseDouble(temp);
    }
}
