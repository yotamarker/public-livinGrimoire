package auxiliary_modules

import java.util.*

object QuestionChecker {
    private val QUESTION_WORDS: MutableSet<String> = HashSet()

    init {
        // Initialize the set of question words
        val words = arrayOf(
            "what", "who", "where", "when", "why", "how",
            "is", "are", "was", "were", "do", "does", "did",
            "can", "could", "would", "will", "shall", "should",
            "have", "has", "am", "may", "might"
        )
        QUESTION_WORDS.addAll(listOf(*words))
    }

    fun isQuestion(input: String?): Boolean {
        if (input == null || input.trim { it <= ' ' }.isEmpty()) {
            return false
        }
        val trimmed = input.trim { it <= ' ' }.lowercase(Locale.getDefault())

        // Check for question mark
        if (trimmed.endsWith("?")) {
            return true
        }

        // Extract the first word
        val firstSpace = trimmed.indexOf(' ')
        var firstWord = if (firstSpace == -1) trimmed else trimmed.substring(0, firstSpace)

        // Check for contractions like "who's"
        if (firstWord.contains("'")) {
            firstWord = firstWord.substring(0, firstWord.indexOf("'"))
        }

        // Check if first word is a question word
        return QUESTION_WORDS.contains(firstWord)
    }
}