package Auxiliary_Modules;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class QuestionChecker {
    private static final Set<String> QUESTION_WORDS = new HashSet<>();

    static {
        // Initialize the set of question words
        String[] words = {
                "what", "who", "where", "when", "why", "how",
                "is", "are", "was", "were", "do", "does", "did",
                "can", "could", "would", "will", "shall", "should",
                "have", "has", "am", "may", "might"
        };
        QUESTION_WORDS.addAll(Arrays.asList(words));
    }

    public static boolean isQuestion(String input) {
        if (input == null || input.trim().isEmpty()) {
            return false;
        }

        String trimmed = input.trim().toLowerCase();

        // Check for question mark
        if (trimmed.endsWith("?")) {
            return true;
        }

        // Extract the first word
        int firstSpace = trimmed.indexOf(' ');
        String firstWord = firstSpace == -1 ? trimmed : trimmed.substring(0, firstSpace);

        // Check for contractions like "who's"
        if (firstWord.contains("'")) {
            firstWord = firstWord.substring(0, firstWord.indexOf("'"));
        }

        // Check if first word is a question word
        return QUESTION_WORDS.contains(firstWord);
    }
}
