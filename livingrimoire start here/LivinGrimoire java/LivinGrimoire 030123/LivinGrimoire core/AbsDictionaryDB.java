package LivinGrimoire;

public class AbsDictionaryDB {
    public void save(String key, String value) {
        // save to DB (override me)
    }
    public String load(String key) {
        // override me
        return "null";
    }
}
