package LivinGrimoire;

import java.util.ArrayList;

public class DISkillUtils {
    // alg part based algorithm building methodes
    public Algorithm onePartAlgorithm(String algMarker,Mutatable algPart) {
        // returns a simple algorithm containing 1 alg part
        String representation = "rep_" + algMarker;
        ArrayList<Mutatable> algParts1 = new ArrayList<>();
        algParts1.add(algPart);
        Algorithm algorithm = new Algorithm(algMarker, representation, algParts1);
        return algorithm;
    }
    // var args param
    public Algorithm algBuilder(String algMarker, Mutatable... algParts) {
        // returns a simple algorithm for saying sent parameter
        String representation = "rep_" + algMarker;;
        ArrayList<Mutatable> algParts1 = new ArrayList<>();
        for (int i = 0; i < algParts.length; i++) {
            algParts1.add(algParts[i]);
        }
        Algorithm algorithm = new Algorithm(algMarker, representation, algParts1);
        return algorithm;
    }
    public Algorithm onePartAlgorithmWithRepresantation(String algRepresentation, String algMarker,Mutatable algPart) {
        // returns a simple algorithm containing 1 alg part
        String representation = algRepresentation;
        ArrayList<Mutatable> algParts1 = new ArrayList<>();
        algParts1.add(algPart);
        Algorithm algorithm = new Algorithm(algMarker, representation, algParts1);
        return algorithm;
    }
    public Algorithm algBuilderWithRepresantation(String algRepresentation, String algMarker, Mutatable... algParts) {
        // returns a simple algorithm for saying sent parameter
        String representation = algRepresentation;;
        ArrayList<Mutatable> algParts1 = new ArrayList<>();
        for (int i = 0; i < algParts.length; i++) {
            algParts1.add(algParts[i]);
        }
        Algorithm algorithm = new Algorithm(algMarker, representation, algParts1);
        return algorithm;
    }
    // String part based algorithm building methodes
    public Algorithm simpleVerbatimAlgorithm(String algMarker, String... sayThis) {
        // returns alg that says the word string (sayThis)
        return onePartAlgorithm(algMarker, new APVerbatim(sayThis));
    }
    public Algorithm simpleVerbatimAlgorithmWithRepresantation(String algRepresentation, String algMarker, String... sayThis) {
        // returns alg that says the word string (sayThis)
        return onePartAlgorithmWithRepresantation(algRepresentation, algMarker, new APVerbatim(sayThis));
    }
    // String part based algorithm building methodes with cloudian (shallow ref object to inform on alg completion)
    public Algorithm simpleCloudiandVerbatimAlgorithmWithRepresantation(String algRepresentation,CldBool cldBool, String algMarker, String... sayThis) {
        // returns alg that says the word string (sayThis)
        return onePartAlgorithmWithRepresantation(algRepresentation, algMarker, new APCldVerbatim(cldBool, sayThis));
    }
    public Algorithm simpleCloudiandVerbatimAlgorithm(CldBool cldBool, String algMarker, String... sayThis) {
        // returns alg that says the word string (sayThis)
        return onePartAlgorithm(algMarker, new APCldVerbatim(cldBool, sayThis));
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
