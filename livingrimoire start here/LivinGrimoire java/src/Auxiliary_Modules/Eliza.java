package Auxiliary_Modules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Eliza {
    public static final Map<String, String> reflections;
    public static final List<PhraseMatcher> babble;

    static {
        HashMap<String, String> ref = new HashMap<String, String>();
        ref.put("am", "are");
        ref.put("was", "were");
        ref.put("i", "you");
        ref.put("i'd", "you would");
        ref.put("i've", "you have");
        ref.put("my", "your");
        ref.put("are", "am");
        ref.put("you've", "Ihave");
        ref.put("you'll", "I will");
        ref.put("your", "my");
        ref.put("yours", "mine");
        ref.put("you", "i");
        ref.put("me", "you");

        reflections = Collections.unmodifiableMap(ref);
        ArrayList<PhraseMatcher> babbleTmp = new ArrayList<PhraseMatcher>();
        babbleTmp.add(new PhraseMatcher("i need (.*)", new String[] { "Why do you need {0}?",
                "Would it really help you to get {0}?", "Are you sure you need {0}?" }));
        babbleTmp.add(new PhraseMatcher("we are going to (.*) today", new String[] { "I'm down like a clown to {0} charlie brown",
                "sweet, I want to {0}", "awesome" }));
        babbleTmp.add(
                new PhraseMatcher("why don'?t you ([^\\?]*)\\??", new String[] { "Do you really think I don't {0}?",
                        "Perhaps eventually I will {0}.", "Do you really want me to {0}?" }));
        babbleTmp.add(new PhraseMatcher("why can'?t I ([^\\?]*)\\??",
                new String[] { "Do you think you should be able to {0}?", "If you could {0}, what would you do?",
                        "I don't know -- why can't you {0}?", "Have you really tried?" }));
        babbleTmp.add(new PhraseMatcher("i can'?t (.*)", new String[] { "How do you know you can't {0}?",
                "Perhaps you could {0} if you tried.", "What would it take for you to {0}?" }));
        babbleTmp.add(new PhraseMatcher("i am (.*)", new String[] { "Did you come to me because you are {0}?",
                "How long have you been {0}?", "How do you feel about being {0}?" }));
        babbleTmp.add(new PhraseMatcher("i'?m (.*)", new String[] { "How does being {0} make you feel?",
                "Do you enjoy being {0}?", "Why do you tell me you're {0}?", "Why do you think you're {0}?" }));
        babbleTmp.add(new PhraseMatcher("are you ([^\\?]*)\\??",
                new String[] { "Why does it matter whether I am {0}?", "Would you prefer it if I were not {0}?",
                        "Perhaps you believe I am {0}.", "I may be {0} -- what do you think?" }));
        babbleTmp.add(new PhraseMatcher("what (.*)",
                new String[] { "Why do you ask?", "How would an answer to that help you?", "What do you think?" }));
        babbleTmp.add(new PhraseMatcher("how (.*)", new String[] { "How do you suppose?",
                "Perhaps you can answer your own question.", "What is it you're really asking?" }));
        babbleTmp.add(new PhraseMatcher("because (.*)",
                new String[] { "Is that the real reason?", "What other reasons come to mind?",
                        "Does that reason apply to anything else?", "If {0}, what else must be true?" }));
        babbleTmp.add(new PhraseMatcher("(.*) sorry (.*)", new String[] {
                "There are many times when no apology is needed.", "What feelings do you have when you apologize?" }));
        babbleTmp.add(new PhraseMatcher("hello(.*)", new String[] { "Hello... I'm glad you could drop by today.",
                "Hi there... how are you today?", "Hello, how are you feeling today?" }));
        babbleTmp.add(new PhraseMatcher("i think (.*)",
                new String[] { "Do you doubt {0}?", "Do you really think so?", "But you're not sure {0}?" }));
        babbleTmp.add(new PhraseMatcher("(.*) friend (.*)",
                new String[] { "Tell me more about your friends.", "When you think of a friend, what comes to mind?",
                        "Why don't you tell me about a childhood friend?" }));
        babbleTmp.add(
                new PhraseMatcher("yes", new String[] { "You seem quite sure.", "OK, but can you elaborate a bit?" }));
        babbleTmp.add(new PhraseMatcher("(.*) computer(.*)",
                new String[] { "Are you really talking about me?", "Does it seem strange to talk to a computer?",
                        "How do computers make you feel?", "Do you feel threatened by computers?" }));
        babbleTmp.add(new PhraseMatcher("is it (.*)",
                new String[] { "Do you think it is {0}?", "Perhaps it's {0} -- what do you think?",
                        "If it were {0}, what would you do?", "It could well be that {0}." }));
        babbleTmp.add(new PhraseMatcher("can you ([^\\?]*)\\??", new String[] { "What makes you think I can't {0}?",
                "If I could {0}, then what?", "Why do you ask if I can {0}?" }));
        babbleTmp.add(new PhraseMatcher("can I ([^\\?]*)\\??", new String[] { "Perhaps you don't want to {0}.",
                "Do you want to be able to {0}?", "If you could {0}, would you?" }));
        babbleTmp.add(new PhraseMatcher("you are (.*)",
                new String[] { "Why do you think I am {0}?", "Does it please you to think that I'm {0}?",
                        "Perhaps you would like me to be {0}.", "Perhaps you're really talking about yourself?" }));
        babbleTmp.add(new PhraseMatcher("you'?re (.*)", new String[] { "Why do you say I am {0}?",
                "Why do you think I am {0}?", "Are we talking about you, or me?" }));
        babbleTmp.add(new PhraseMatcher("i don'?t (.*)",
                new String[] { "Don't you really {0}?", "Why don't you {0}?", "Do you want to {0}?" }));
        babbleTmp.add(new PhraseMatcher("i feel (.*)", new String[] { "Good, tell me more about these feelings.",
                "Do you often feel {0}?", "When do you usually feel {0}?", "When you feel {0}, what do you do?" }));
        babbleTmp.add(new PhraseMatcher("i have (.*)", new String[] { "Why do you tell me that you've {0}?",
                "Have you really {0}?", "Now that you have {0}, what will you do next?" }));
        babbleTmp.add(new PhraseMatcher("i would (.*)", new String[] { "Could you explain why you would {0}?",
                "Why would you {0}?", "Who else knows that you would {0}?" }));
        babbleTmp.add(new PhraseMatcher("is there (.*)", new String[] { "Do you think there is {0}?",
                "It's likely that there is {0}.", "Would you like there to be {0}?" }));
        babbleTmp.add(new PhraseMatcher("my (.*)", new String[] { "I see, your {0}.", "Why do you say that your {0}?",
                "When your {0}, how do you feel?" }));
        babbleTmp.add(new PhraseMatcher("you (.*)", new String[] { "We should be discussing you, not me.",
                "Why do you say that about me?", "Why do you care whether I {0}?" }));
        babbleTmp.add(new PhraseMatcher("why (.*)",
                new String[] { "Why don't you tell me the reason why {0}?", "Why do you think {0}?" }));
        babbleTmp.add(new PhraseMatcher("i want (.*)",
                new String[] { "What would it mean to you if you got {0}?", "Why do you want {0}?",
                        "What would you do if you got {0}?", "If you got {0}, then what would you do?" }));
        babbleTmp.add(new PhraseMatcher("(.*) mother(.*)",
                new String[] { "Tell me more about your mother.", "What was your relationship with your mother like?",
                        "How do you feel about your mother?", "How does this relate to your feelings today?",
                        "Good family relations are important." }));
        babbleTmp.add(new PhraseMatcher("(.*) father(.*)",
                new String[] { "Tell me more about your father.", "How did your father make you feel?",
                        "How do you feel about your father?",
                        "Does your relationship with your father relate to your feelings today?",
                        "Do you have trouble showing affection with your family?" }));
        babbleTmp.add(new PhraseMatcher("(.*) child(.*)",
                new String[] { "Did you have close friends as a child?", "What is your favorite childhood memory?",
                        "Do you remember any dreams or nightmares from childhood?",
                        "Did the other children sometimes tease you?",
                        "How do you think your childhood experiences relate to your feelings today?" }));
        babbleTmp.add(new PhraseMatcher("(.*)\\?",
                new String[] { "Why do you ask that?", "Please consider whether you can answer your own question.",
                        "Perhaps the answer lies within yourself?", "Why don't you tell me?" }));
        babbleTmp.add(new PhraseMatcher("quit", new String[] { "Thank you for talking with me.", "Good-bye.",
                "Thank you, that will be $150.  Have a good day!" }));
        babbleTmp.add(new PhraseMatcher("(.*)",
                new String[] { "Please tell me more.", "Let's change focus a bit... Tell me about your family.",
                        "Can you elaborate on that?", "Why do you say that, {0}?", "I see.", "Very interesting.",
                        "{0}.", "I see.  And what does that tell you?", "How does that make you feel?",
                        "How do you feel when you say that?" }));
        babble = Collections.unmodifiableList(babbleTmp);
    }

    public Eliza() {

    }

    public String respond(String msg) {
        for (PhraseMatcher pm : babble) {
            if (pm.matches(msg)) {
                return pm.respond(msg.toLowerCase());
            }
        }
        return "";
    }

    public static class PhraseMatcher {
        public static final Random RND = new Random();
        public final Pattern matcher;
        public final List<String> responses;

        public PhraseMatcher(String matcher, List<String> responses) {
            this.matcher = Pattern.compile(matcher);
            this.responses = responses;
        }

        public PhraseMatcher(String matcher, String[] responses) {
            this.matcher = Pattern.compile(matcher);
            this.responses = Arrays.asList(responses);
        }

        public boolean matches(String str) {
            Matcher m = matcher.matcher(str);
            return m.matches();
        }

        public String respond(String str) {
            Matcher m = matcher.matcher(str);
            if(m.find()){}
            String p = randomPhrase();
            int tmp = m.groupCount();
            for (int i = 0; i < tmp; i++) {
                String s = reflect(m.group(i + 1));
                p = p.replace("{" + i + "}", s);
            }
            return p;
        }

        public static String reflect(String s) {
            String[] sa = s.split(" ");
            for (int i = 0; i < sa.length; i++) {
                if (reflections.containsKey(sa[i])) {
                    sa[i] = reflections.get(sa[i]);
                }
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < sa.length; i++) {
                if (i > 0) {
                    sb.append(" ");
                }
                sb.append(sa[i]);
            }
            return sb.toString();
        }

        public String randomPhrase() {
            return this.responses.get(Math.abs(RND.nextInt() % (this.responses.size())));
        }

        @Override
        public String toString() {
            return matcher.pattern() + ":" + responses.toString();
        }
    }
}
