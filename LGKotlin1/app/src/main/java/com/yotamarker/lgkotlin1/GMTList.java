package com.yotamarker.lgkotlin1;

import java.util.HashMap;

public class GMTList {
    private HashMap<String, Integer> targets = new HashMap<String, Integer>();

    public GMTList() {
        super();
        targets.put("alabama", -5);
        targets.put("alaska", -8);
        targets.put("alaska", -9);
        targets.put("alaska", -8);
        targets.put("arizona", -7);
        targets.put("arizona", -6);
        targets.put("arkansas", -5);
        targets.put("california", -7);
        targets.put("colorado", -6);
        targets.put("connecticut", -4);
        targets.put("los angeles", -7);
        targets.put("denver", -6);
        targets.put("delaware", -4);
        targets.put("washington", -4);
        targets.put("florida", -4);
        targets.put("jacksonville", -4);
        targets.put("georgia", -4);
        targets.put("hawaii", -10);
        targets.put("idaho", -6);
        targets.put("illinois", -5);
        targets.put("indiana", -4);
        targets.put("evansville", -5);
        targets.put("iowa", -5);
        targets.put("kansas", -5);
        targets.put("goodland", -6);
        targets.put("louisville", -4);
        targets.put("kentucky", -5);
        targets.put("louisiana", -5);
        targets.put("maine", -4);
        targets.put("maryland", -4);
        targets.put("massachusetts", -4);
        targets.put("michigan", -4);
        targets.put("iron mountain", -5);
        targets.put("minnesota", -5);
        targets.put("mississippi", -5);
        targets.put("missouri", -5);
        targets.put("montana", -6);
        targets.put("nebraska", -5);
        targets.put("scottsbluff", -6);
        targets.put("las vegas", -7);
        targets.put("nevad", -6);
        targets.put("manchester", -4);
        targets.put("new jerse", -4);
        targets.put("new mexico", -6);
        targets.put("new york", -4);
        targets.put("north carolina", -4);
        targets.put("north dakota", -5);
        targets.put("fargo", -5);
        targets.put("dickinson", -6);
        targets.put("ohio", -4);
        targets.put("oklahoma", -5);
        targets.put("orego", -7);
        targets.put("ontario", -6);
        targets.put("pennsylvania", -4);
        targets.put("philadelphia", -4);
        targets.put("rhode island", -4);
        targets.put("south carolina", -4);
        targets.put("south dakota", -5);
        targets.put("rapid city", -6);
        targets.put("tennessee", -5);
        targets.put("knoxville", -4);
        targets.put("texas", -5);
        targets.put("el paso", -6);
        targets.put("utah ", -6);
        targets.put("vermont", -4);
        targets.put("virginia", -4);
        targets.put("seattle", -7);
        targets.put("west virginia", -4);
        targets.put("wisconsin", -5);
        targets.put("wyoming", -6);
        targets.put("aland islands", 3);
        targets.put("albania", 2);
        targets.put("andorra", 2);
        targets.put("australia", 11);
        targets.put("bahamas", 4);
        targets.put("belgium", 2);
        targets.put("bermuda", -3);
        targets.put("bulgaria", 3);
        targets.put("chile", -3);
        targets.put("croatia", 2);
        targets.put("cyprus", 3);
        targets.put("czechia", 2);
        targets.put("denmark", 2);
        targets.put("estonia", 3);
        targets.put("denmark", 1);
        targets.put("fiji", 13);
        targets.put("france", 2);
        targets.put("germany", 2);
        targets.put("gibraltar", 2);
        targets.put("greece", 3);
        targets.put("greenland", -2);
        targets.put("haiti", -4);
        targets.put("hungary", 2);
        targets.put("ireland", 1);
        targets.put("israel", 3);
        targets.put("italy", 2);
        targets.put("jersey", 1);
        targets.put("jordan", 3);
        targets.put("kosovo", 2);
        targets.put("latvia", 3);
        targets.put("japan", 9);
        targets.put("shinjuku", 9);
        targets.put("lebanon", 3);
        targets.put("liechtenstein", 2);
        targets.put("lithuania", 3);
        targets.put("luxembourg", 2);
        targets.put("malta", 2);
        targets.put("mexico", -5);
        targets.put("moldova", 3);
        targets.put("montenegro", 2);
        targets.put("monaco", 2);
        targets.put("netherlands", 2);
        targets.put("new zealand", 13);
        targets.put("norway", 2);
        targets.put("paraguay", -3);
        targets.put("poland", 2);
        targets.put("portugal", 1);
        targets.put("romania", 3);
        targets.put("samoa", 14);
        targets.put("san marino", 2);
        targets.put("serbia	", 2);
        targets.put("slovakia", 2);
        targets.put("spain", 2);
        targets.put("sweden", 2);
        targets.put("switzerland", 2);
        targets.put("syria", 3);
        targets.put("ukraine", 3);
        targets.put("united kingdom", 1);
        targets.put("vatican city", 2);

    }

    public Boolean existsInList(String location) {
        return targets.containsKey(location);
    }

    public int getDST(String location) {
        return targets.getOrDefault(location, 0);
    }
}
