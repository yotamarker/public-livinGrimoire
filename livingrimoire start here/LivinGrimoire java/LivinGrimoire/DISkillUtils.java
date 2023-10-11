package LivinGrimoire;

import java.util.ArrayList;

public class DISkillUtils {
    // alg part based algorithm building methods
    // var args param
    public Algorithm algBuilder(Mutatable... algParts) {
        // returns an algorithm built with the algPart varargs
        ArrayList<Mutatable> algParts1 = new ArrayList<>();
        for (int i = 0; i < algParts.length; i++) {
            algParts1.add(algParts[i]);
        }
        Algorithm algorithm = new Algorithm(algParts1);
        return algorithm;
    }
    // String based algorithm building methods
    public Algorithm simpleVerbatimAlgorithm(String... sayThis) {
        // returns an algorithm that says the sayThis Strings verbatim per think cycle
        return algBuilder(new APVerbatim(sayThis));
    }
    // String part based algorithm building methods with cloudian (shallow ref object to inform on alg completion)
    public Algorithm simpleCloudiandVerbatimAlgorithm(CldBool cldBool, String... sayThis) {
        // returns an algorithm that says the sayThis Strings verbatim per think cycle
        return algBuilder(new APCldVerbatim(cldBool, sayThis));
    }
    public String strContainsList(String str1, ArrayList<String> items) {
        // returns the 1st match between words in a string and values in a list.
        for (String temp : items) {
            if (str1.contains(temp)) {
                return temp;
            }
        }
        return "";
    }
}
