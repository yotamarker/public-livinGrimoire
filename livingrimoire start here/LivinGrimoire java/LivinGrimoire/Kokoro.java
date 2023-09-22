package LivinGrimoire;

import java.util.Hashtable;

/* all action data goes through here
 * detects negatives such as : repetition, pain on various levels and failures
 * serves as a database for memories, convos and alg generations
 * can trigger revenge algs
 * checks for % of difference in input for exploration type algs
 * */
public class Kokoro {
    private String emot = "";

    public String getEmot() {
        return emot;
    }

    public void setEmot(String emot) {
        this.emot = emot;
    }
    public GrimoireMemento grimoireMemento;
    public Hashtable<String, String> toHeart = new Hashtable<>();
    public Kokoro(AbsDictionaryDB absDictionaryDB) {
        super();
        this.grimoireMemento = new GrimoireMemento(absDictionaryDB);
    }
}
