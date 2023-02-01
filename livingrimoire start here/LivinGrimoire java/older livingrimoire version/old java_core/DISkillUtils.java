package com.yotamarker.lgkotlin1;

import java.util.ArrayList;

public class DISkillUtils {
    public Algorithm verbatimGorithm(AbsAlgPart itte) {
        // returns a simple algorithm containing 1 alg part
        String representation = "util";
        ArrayList<AbsAlgPart> algParts1 = new ArrayList<>();
        algParts1.add(itte);
        Algorithm algorithm = new Algorithm("util", representation, algParts1);
        return algorithm;
    }

    public Algorithm verbatimGorithm(String algMarker, AbsAlgPart itte) {
        // returns a simple algorithm for saying sent parameter
        String representation = "util";
        ArrayList<AbsAlgPart> algParts1 = new ArrayList<>();
        algParts1.add(itte);
        Algorithm algorithm = new Algorithm("util", representation, algParts1);
        return algorithm;
    }

    public Algorithm customizedVerbatimGorithm(String algMarker, AbsAlgPart itte) {
        // the most stable and advanced algorithm builder
        // returns a simple algorithm containing 1 alg part
        String representation = "r_" + algMarker;
        ArrayList<AbsAlgPart> algParts1 = new ArrayList<>();
        algParts1.add(itte);
        Algorithm algorithm = new Algorithm(algMarker, representation, algParts1);
        return algorithm;
    }

    public Algorithm customizedVerbatimGorithm(String algMarker, AbsAlgPart... itte) {
        // the most stable and advanced algorithm builder
        // returns a simple algorithm containing 1 alg part
        String representation = "r_" + algMarker;
        ArrayList<AbsAlgPart> algParts1 = new ArrayList<>();
        for (int i = 0; i < itte.length; i++) {
            algParts1.add(itte[i]);
        }
        Algorithm algorithm = new Algorithm(algMarker, representation, algParts1);
        return algorithm;
    }

    public Algorithm simpleVerbatimAlgorithm(String algMarker, String... sayThis) {
        // returns alg that says the word string (sayThis)
        return customizedVerbatimGorithm(algMarker, new APVerbatim(sayThis));
    }

    public Algorithm simpleCloudiandVerbatimAlgorithm(CldBool cldBool, String algMarker, String... sayThis) {
        // returns alg that says the word string (sayThis)
        return customizedVerbatimGorithm(algMarker, new APCldVerbatim(cldBool, sayThis));
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