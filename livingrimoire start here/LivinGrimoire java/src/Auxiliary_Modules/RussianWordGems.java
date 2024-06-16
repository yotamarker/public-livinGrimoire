package Auxiliary_Modules;

import Skills.logical.DiBlueCrystal;

import java.util.HashMap;

public class RussianWordGems {
    private DiBlueCrystal o1 = new DiBlueCrystal();
    public RussianWordGems() {
        HashMap<String, String> map = new HashMap<>();
        map.put("привет", "hello");
        map.put("доброе утро", "good morning");
        map.put("книга", "book");
        o1.addCategory(map);
    }
    public DiBlueCrystal retSkill(){
        return o1;
    }
}
