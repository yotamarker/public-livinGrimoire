package Auxiliary_Modules;

import java.util.HashMap;
import java.util.Map;

public class PhraseInflector {

    // Maps for pronoun and verb inflection
    private static final Map<String, String> inflectionMap = new HashMap<>();

    static {
        // Pronoun mappings
        inflectionMap.put("i", "you");
        inflectionMap.put("me", "you");
        inflectionMap.put("my", "your");
        inflectionMap.put("mine", "yours");
        inflectionMap.put("you", "i"); // Default inflection
        inflectionMap.put("your", "my");
        inflectionMap.put("yours", "mine");

        // Verb conjugations
        inflectionMap.put("am", "are");
        inflectionMap.put("are", "am");
        inflectionMap.put("was", "were");
        inflectionMap.put("were", "was");

        // Contractions
        inflectionMap.put("i'd", "you would");
        inflectionMap.put("i've", "you have");
        inflectionMap.put("you've", "I have");
        inflectionMap.put("you'll", "I will");
    }

    // Function to inflect a phrase
    public static String inflectPhrase(String phrase) {
        String[] words = phrase.split(" ");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            String lowerWord = word.toLowerCase();
            String inflectedWord = word; // Default to the original word

            // Check if the word needs to be inflected
            if (inflectionMap.containsKey(lowerWord)) {
                inflectedWord = inflectionMap.get(lowerWord);

                // Special case for "you"
                if (lowerWord.equals("you")) {
                    // Inflect to "me" if it's at the end of the sentence or after a verb
                    if (i == words.length - 1 || (i > 0 && isVerb(words[i - 1].toLowerCase()))) {
                        inflectedWord = "me";
                    } else {
                        inflectedWord = "I";
                    }
                }
            }

            // Preserve capitalization
            if (Character.isUpperCase(word.charAt(0))) {
                inflectedWord = inflectedWord.substring(0, 1).toUpperCase() + inflectedWord.substring(1);
            }

            result.append(inflectedWord).append(" ");
        }

        return result.toString().trim();
    }

    // Helper function to check if a word is a verb
    private static boolean isVerb(String word) {
        return word.equals("am") || word.equals("are") || word.equals("was") || word.equals("were") ||
                word.equals("have") || word.equals("has") || word.equals("had") ||
                word.equals("do") || word.equals("does") || word.equals("did");
    }
}

