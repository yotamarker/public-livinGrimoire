package auxiliary_modules

import java.util.*

object PhraseInflector {
    // Maps for pronoun and verb inflection
    private val inflectionMap: MutableMap<String, String> = HashMap()

    init {
        // Pronoun mappings
        inflectionMap["i"] = "you"
        inflectionMap["me"] = "you"
        inflectionMap["my"] = "your"
        inflectionMap["mine"] = "yours"
        inflectionMap["you"] = "i" // Default inflection
        inflectionMap["your"] = "my"
        inflectionMap["yours"] = "mine"

        // Verb conjugations
        inflectionMap["am"] = "are"
        inflectionMap["are"] = "am"
        inflectionMap["was"] = "were"
        inflectionMap["were"] = "was"

        // Contractions
        inflectionMap["i'd"] = "you would"
        inflectionMap["i've"] = "you have"
        inflectionMap["you've"] = "I have"
        inflectionMap["you'll"] = "I will"
    }

    // Function to inflect a phrase
    fun inflectPhrase(phrase: String): String {
        val words = phrase.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val result = StringBuilder()
        for (i in words.indices) {
            val word = words[i]
            val lowerWord = word.lowercase(Locale.getDefault())
            var inflectedWord: String? = word // Default to the original word

            // Check if the word needs to be inflected
            if (inflectionMap.containsKey(lowerWord)) {
                inflectedWord = inflectionMap[lowerWord]

                // Special case for "you"
                if (lowerWord == "you") {
                    // Inflect to "me" if it's at the end of the sentence or after a verb
                    inflectedWord =
                        if (i == words.size - 1 || i > 0 && isVerb(words[i - 1].lowercase(Locale.getDefault()))) {
                            "me"
                        } else {
                            "I"
                        }
                }
            }

            // Preserve capitalization
            if (Character.isUpperCase(word[0])) {
                inflectedWord =
                    inflectedWord!!.substring(0, 1).uppercase(Locale.getDefault()) + inflectedWord.substring(1)
            }
            result.append(inflectedWord).append(" ")
        }
        return result.toString().trim { it <= ' ' }
    }

    // Helper function to check if a word is a verb
    private fun isVerb(word: String): Boolean {
        return word == "am" || word == "are" || word == "was" || word == "were" || word == "have" || word == "has" || word == "had" || word == "do" || word == "does" || word == "did"
    }
}