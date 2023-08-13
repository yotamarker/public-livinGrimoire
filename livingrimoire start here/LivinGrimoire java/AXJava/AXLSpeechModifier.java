package AXJava;

import java.util.Hashtable;

public class AXLSpeechModifier extends AXLHousing{
    public Hashtable<String,String> dic;

    public AXLSpeechModifier(Hashtable<String, String> dic) {
        this.dic = dic;
    }

    @Override
    public String decorate(String str1) {
        String result = "";
        String[] words = str1.split("\\s+");
        for (int i = 0; i < words.length; i++) {
            words[i] = dic.getOrDefault(words[i], words[i]);
            result = result + " " + words[i];
        }
        return result.trim();
    }
}
