package chobit;
import java.util.ArrayList;
import java.util.Hashtable;

// script holder for the dirty talk AP
public class MockDB {
    public Hashtable<String, String> hashtable;

    public MockDB() {
        super();
        ArrayList<String> AR1 = new ArrayList<>();
        AR1.add("start");
        AR1.add("hi honey");
        AR1.add("I love you");
        AR1.add("oh honey your dick so beutiful");
        AR1.add("would you like dinner a shower me ?");
        AR1.add("oh fuck yeah");
        AR1.add("please more honey");
        AR1.add("thank you");
        AR1.add("thank you so much");
        AR1.add("me so happy");
        AR1.add("me love you long time");
        AR1.add("chupa chupa");
        AR1.add("lick lick lick");
        String at = "start";
        this.hashtable = new Hashtable<>();
        for (String string : AR1) {
            hashtable.put(at, string);
            at = string;
        }

    }

    public Hashtable<String, String> getHashtable() {
        return (Hashtable<String, String>) hashtable.clone();
    }
}
