package auxiliary_modules

import java.util.*
import java.util.regex.Pattern

class Eliza {
    fun respond(msg: String): String {
        for (pm in babble) {
            if (pm.matches(msg)) {
                return pm.respond(msg.lowercase(Locale.getDefault()))
            }
        }
        return ""
    }

    class PhraseMatcher {
        val matcher: Pattern
        val responses: List<String>

        constructor(matcher: String, responses: List<String>) {
            this.matcher = Pattern.compile(matcher)
            this.responses = responses
        }

        constructor(matcher: String, responses: Array<String>) {
            this.matcher = Pattern.compile(matcher)
            this.responses = listOf(*responses)
        }

        fun matches(str: String): Boolean {
            val m = matcher.matcher(str)
            return m.matches()
        }

        fun respond(str: String): String {
            val m = matcher.matcher(str)
            if (m.find()) {
            }
            var p = randomPhrase()
            val tmp = m.groupCount()
            for (i in 0 until tmp) {
                val s = reflect(m.group(i + 1))
                p = p.replace("{$i}", s)
            }
            return p
        }

        fun randomPhrase(): String {
            return responses[Math.abs(RND.nextInt() % responses.size)]
        }

        override fun toString(): String {
            return matcher.pattern() + ":" + responses.toString()
        }

        companion object {
            val RND = Random()
            fun reflect(s: String): String {
                val sa: Array<String> = s.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                for (i in sa.indices) {
                    if (reflections.containsKey(sa[i])) {
                        sa[i] = reflections[sa[i]]!!
                    }
                }
                val sb = StringBuilder()
                for (i in sa.indices) {
                    if (i > 0) {
                        sb.append(" ")
                    }
                    sb.append(sa[i])
                }
                return sb.toString()
            }
        }
    }

    companion object {
        var reflections: Map<String, String>
        var babble: List<PhraseMatcher>

        init {
            val ref = HashMap<String, String>()
            ref["am"] = "are"
            ref["was"] = "were"
            ref["i"] = "you"
            ref["i'd"] = "you would"
            ref["i've"] = "you have"
            ref["my"] = "your"
            ref["are"] = "am"
            ref["you've"] = "Ihave"
            ref["you'll"] = "I will"
            ref["your"] = "my"
            ref["yours"] = "mine"
            ref["you"] = "i"
            ref["me"] = "you"
            reflections = Collections.unmodifiableMap(ref)
            val babbleTmp = ArrayList<PhraseMatcher>()
            babbleTmp.add(
                PhraseMatcher(
                    "i need (.*)", arrayOf(
                        "Why do you need {0}?",
                        "Would it really help you to get {0}?", "Are you sure you need {0}?"
                    )
                )
            )
            babbleTmp.add(
                PhraseMatcher(
                    "we are going to (.*) today", arrayOf(
                        "I'm down like a clown to {0} charlie brown",
                        "sweet, I want to {0}", "awesome"
                    )
                )
            )
            babbleTmp.add(
                PhraseMatcher(
                    "why don'?t you ([^\\?]*)\\??", arrayOf(
                        "Do you really think I don't {0}?",
                        "Perhaps eventually I will {0}.", "Do you really want me to {0}?"
                    )
                )
            )
            babbleTmp.add(
                PhraseMatcher(
                    "why can'?t I ([^\\?]*)\\??", arrayOf(
                        "Do you think you should be able to {0}?", "If you could {0}, what would you do?",
                        "I don't know -- why can't you {0}?", "Have you really tried?"
                    )
                )
            )
            babbleTmp.add(
                PhraseMatcher(
                    "i can'?t (.*)", arrayOf(
                        "How do you know you can't {0}?",
                        "Perhaps you could {0} if you tried.", "What would it take for you to {0}?"
                    )
                )
            )
            babbleTmp.add(
                PhraseMatcher(
                    "i am (.*)", arrayOf(
                        "Did you come to me because you are {0}?",
                        "How long have you been {0}?", "How do you feel about being {0}?"
                    )
                )
            )
            babbleTmp.add(
                PhraseMatcher(
                    "i'?m (.*)", arrayOf(
                        "How does being {0} make you feel?",
                        "Do you enjoy being {0}?", "Why do you tell me you're {0}?", "Why do you think you're {0}?"
                    )
                )
            )
            babbleTmp.add(
                PhraseMatcher(
                    "are you ([^\\?]*)\\??", arrayOf(
                        "Why does it matter whether I am {0}?", "Would you prefer it if I were not {0}?",
                        "Perhaps you believe I am {0}.", "I may be {0} -- what do you think?"
                    )
                )
            )
            babbleTmp.add(
                PhraseMatcher(
                    "what (.*)",
                    arrayOf("Why do you ask?", "How would an answer to that help you?", "What do you think?")
                )
            )
            babbleTmp.add(
                PhraseMatcher(
                    "how (.*)", arrayOf(
                        "How do you suppose?",
                        "Perhaps you can answer your own question.", "What is it you're really asking?"
                    )
                )
            )
            babbleTmp.add(
                PhraseMatcher(
                    "because (.*)", arrayOf(
                        "Is that the real reason?", "What other reasons come to mind?",
                        "Does that reason apply to anything else?", "If {0}, what else must be true?"
                    )
                )
            )
            babbleTmp.add(
                PhraseMatcher(
                    "(.*) sorry (.*)", arrayOf(
                        "There are many times when no apology is needed.",
                        "What feelings do you have when you apologize?"
                    )
                )
            )
            babbleTmp.add(
                PhraseMatcher(
                    "hello(.*)", arrayOf(
                        "Hello... I'm glad you could drop by today.",
                        "Hi there... how are you today?", "Hello, how are you feeling today?"
                    )
                )
            )
            babbleTmp.add(
                PhraseMatcher(
                    "i think (.*)",
                    arrayOf("Do you doubt {0}?", "Do you really think so?", "But you're not sure {0}?")
                )
            )
            babbleTmp.add(
                PhraseMatcher(
                    "(.*) friend (.*)", arrayOf(
                        "Tell me more about your friends.", "When you think of a friend, what comes to mind?",
                        "Why don't you tell me about a childhood friend?"
                    )
                )
            )
            babbleTmp.add(
                PhraseMatcher("yes", arrayOf("You seem quite sure.", "OK, but can you elaborate a bit?"))
            )
            babbleTmp.add(
                PhraseMatcher(
                    "(.*) computer(.*)", arrayOf(
                        "Are you really talking about me?", "Does it seem strange to talk to a computer?",
                        "How do computers make you feel?", "Do you feel threatened by computers?"
                    )
                )
            )
            babbleTmp.add(
                PhraseMatcher(
                    "is it (.*)", arrayOf(
                        "Do you think it is {0}?", "Perhaps it's {0} -- what do you think?",
                        "If it were {0}, what would you do?", "It could well be that {0}."
                    )
                )
            )
            babbleTmp.add(
                PhraseMatcher(
                    "can you ([^\\?]*)\\??", arrayOf(
                        "What makes you think I can't {0}?",
                        "If I could {0}, then what?", "Why do you ask if I can {0}?"
                    )
                )
            )
            babbleTmp.add(
                PhraseMatcher(
                    "can I ([^\\?]*)\\??", arrayOf(
                        "Perhaps you don't want to {0}.",
                        "Do you want to be able to {0}?", "If you could {0}, would you?"
                    )
                )
            )
            babbleTmp.add(
                PhraseMatcher(
                    "you are (.*)", arrayOf(
                        "Why do you think I am {0}?", "Does it please you to think that I'm {0}?",
                        "Perhaps you would like me to be {0}.", "Perhaps you're really talking about yourself?"
                    )
                )
            )
            babbleTmp.add(
                PhraseMatcher(
                    "you'?re (.*)", arrayOf(
                        "Why do you say I am {0}?",
                        "Why do you think I am {0}?", "Are we talking about you, or me?"
                    )
                )
            )
            babbleTmp.add(
                PhraseMatcher(
                    "i don'?t (.*)",
                    arrayOf("Don't you really {0}?", "Why don't you {0}?", "Do you want to {0}?")
                )
            )
            babbleTmp.add(
                PhraseMatcher(
                    "i feel (.*)", arrayOf(
                        "Good, tell me more about these feelings.",
                        "Do you often feel {0}?", "When do you usually feel {0}?", "When you feel {0}, what do you do?"
                    )
                )
            )
            babbleTmp.add(
                PhraseMatcher(
                    "i have (.*)", arrayOf(
                        "Why do you tell me that you've {0}?",
                        "Have you really {0}?", "Now that you have {0}, what will you do next?"
                    )
                )
            )
            babbleTmp.add(
                PhraseMatcher(
                    "i would (.*)", arrayOf(
                        "Could you explain why you would {0}?",
                        "Why would you {0}?", "Who else knows that you would {0}?"
                    )
                )
            )
            babbleTmp.add(
                PhraseMatcher(
                    "is there (.*)", arrayOf(
                        "Do you think there is {0}?",
                        "It's likely that there is {0}.", "Would you like there to be {0}?"
                    )
                )
            )
            babbleTmp.add(
                PhraseMatcher(
                    "my (.*)", arrayOf(
                        "I see, your {0}.", "Why do you say that your {0}?",
                        "When your {0}, how do you feel?"
                    )
                )
            )
            babbleTmp.add(
                PhraseMatcher(
                    "you (.*)", arrayOf(
                        "We should be discussing you, not me.",
                        "Why do you say that about me?", "Why do you care whether I {0}?"
                    )
                )
            )
            babbleTmp.add(
                PhraseMatcher(
                    "why (.*)",
                    arrayOf("Why don't you tell me the reason why {0}?", "Why do you think {0}?")
                )
            )
            babbleTmp.add(
                PhraseMatcher(
                    "i want (.*)", arrayOf(
                        "What would it mean to you if you got {0}?", "Why do you want {0}?",
                        "What would you do if you got {0}?", "If you got {0}, then what would you do?"
                    )
                )
            )
            babbleTmp.add(
                PhraseMatcher(
                    "(.*) mother(.*)", arrayOf(
                        "Tell me more about your mother.", "What was your relationship with your mother like?",
                        "How do you feel about your mother?", "How does this relate to your feelings today?",
                        "Good family relations are important."
                    )
                )
            )
            babbleTmp.add(
                PhraseMatcher(
                    "(.*) father(.*)", arrayOf(
                        "Tell me more about your father.", "How did your father make you feel?",
                        "How do you feel about your father?",
                        "Does your relationship with your father relate to your feelings today?",
                        "Do you have trouble showing affection with your family?"
                    )
                )
            )
            babbleTmp.add(
                PhraseMatcher(
                    "(.*) child(.*)", arrayOf(
                        "Did you have close friends as a child?", "What is your favorite childhood memory?",
                        "Do you remember any dreams or nightmares from childhood?",
                        "Did the other children sometimes tease you?",
                        "How do you think your childhood experiences relate to your feelings today?"
                    )
                )
            )
            babbleTmp.add(
                PhraseMatcher(
                    "(.*)\\?", arrayOf(
                        "Why do you ask that?", "Please consider whether you can answer your own question.",
                        "Perhaps the answer lies within yourself?", "Why don't you tell me?"
                    )
                )
            )
            babbleTmp.add(
                PhraseMatcher(
                    "quit", arrayOf(
                        "Thank you for talking with me.", "Good-bye.",
                        "Thank you, that will be $150.  Have a good day!"
                    )
                )
            )
            babbleTmp.add(
                PhraseMatcher(
                    "(.*)", arrayOf(
                        "Please tell me more.", "Let's change focus a bit... Tell me about your family.",
                        "Can you elaborate on that?", "Why do you say that, {0}?", "I see.", "Very interesting.",
                        "{0}.", "I see.  And what does that tell you?", "How does that make you feel?",
                        "How do you feel when you say that?"
                    )
                )
            )
            babble = Collections.unmodifiableList(babbleTmp)
        }
    }
}