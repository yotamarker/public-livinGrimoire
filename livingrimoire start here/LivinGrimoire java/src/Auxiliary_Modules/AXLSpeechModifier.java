package Auxiliary_Modules;

import java.util.Hashtable;

public class AXLSpeechModifier extends AXLHousing{
    public Hashtable<String,String> dic;

    public AXLSpeechModifier(Hashtable<String, String> dic) {
        this.dic = dic;
    }

    @Override
    public String decorate(String str1) {
        StringBuilder result = new StringBuilder();
        String[] words = str1.split("\\s+");
        for (int i = 0; i < words.length; i++) {
            words[i] = dic.getOrDefault(words[i], words[i]);
            result.append(" ").append(words[i]);
        }
        return result.toString().trim();
    }
}
