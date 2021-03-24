package com.yotamarker.lgkotlin1;

import java.util.HashMap;

public class ABDLTranslator {
    private HashMap<String, String> dic = new HashMap<>();
    private UwU uwu = new UwU();

    public ABDLTranslator() {
        dic.put("and", "an");
        dic.put("all", "aw");
        dic.put("banana", "baana");
        dic.put("bottle", "ba-ba");
        dic.put("bear", "bar");
        dic.put("pacifier", "binkie");
        dic.put("bird", "biwdie");
        dic.put("blanket", "bwankie");
        dic.put("injury", "boo-boo");
        dic.put("mistake", "oops");
        dic.put("rabbit", "bunny");
        dic.put("bought", "bot");
        dic.put("goodbye", "bye bye");
        dic.put("feces", "ca ca");
        dic.put("car", "caw");
        dic.put("chips", "chik");
        dic.put("train", "choo choo");
        dic.put("father", "da da");
        dic.put("mother", "ma ma");
        dic.put("that", "dat");
        dic.put("dinner", "din din");
        dic.put("dog", "doggy");
        dic.put("don't", "don");
        dic.put("dont", "don");
        dic.put("do not", "don");
        dic.put("doll", "dowy");
        dic.put("duck", "duckie");
        dic.put("diaper", "dypi");
        dic.put("even", "eben");
        dic.put("fish", "fishwee");
        dic.put("butterfly", "fwuttabye");
        dic.put("butterflies", "fwuttabyes");
        dic.put("his", "hims");
        dic.put("horse", "howsie");
        dic.put("cat", "keecat");
        dic.put("nurse", "na na");
        dic.put("goodnight", "nite nite");
        dic.put("yummy", "numy");
        dic.put("ouch", "owie");
        dic.put("penis", "peepee");
        dic.put("butt", "po po");
        dic.put("toilette", "potty");
        dic.put("sandwich", "samich");
        dic.put("potato", "tato");
        dic.put("urine", "wee wees");
        dic.put("stomack", "tummy");
        dic.put("abdoman", "tum tum");
        dic.put("agreement", "uh huh");
        dic.put("walk", "wawkies");
        dic.put("with", "wid");
        dic.put("offensive", "yuck");
        dic.put("vagina", "fu fu");
        dic.put("ass", "butt");
    }

    public String translate(String str1) {
        String[] arrOfStr = str1.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arrOfStr.length; i++) {
            arrOfStr[i] = dic.getOrDefault(arrOfStr[i], arrOfStr[i]);
            sb.append(arrOfStr[i]);
            sb.append(" ");
        }
        return sb.toString();
    }

    public String translatePlusUwU(String str1) {
        String[] arrOfStr = str1.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arrOfStr.length; i++) {
            arrOfStr[i] = dic.getOrDefault(arrOfStr[i], arrOfStr[i]);
            sb.append(arrOfStr[i]);
            sb.append(" ");
        }
        return uwu.convertToUwU(sb.toString());
    }
}

