package com.yotamarker.lgkotlin1;

public class Grammer {
    public String toggleWords(String sentence, String w1, String w2) {
        if (sentence.contains(w1)) {
            return sentence.replace(w1, w2);
        } else {
            if (sentence.contains(w2)) {
                return sentence.replace(w2, w1);
            }
        }
        return sentence;
    }
}
