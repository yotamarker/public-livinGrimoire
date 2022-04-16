package com.yotamarker.lgkotlin1;

import java.util.ArrayList;

public class ComplimentRecog {
    private ArrayList<String> containsMe1 = new ArrayList<String>();
    private ArrayList<String> containsMe2 = new ArrayList<String>();

    public ComplimentRecog() {
        containsMe1.add("stop thinking about you");
        containsMe1.add("you are truly");
        containsMe1.add("you are really good at");
        containsMe1.add("i appreciate your");
        containsMe1.add("you would make a really great");
        containsMe1.add("you are an awesome");
        containsMe1.add("you are a smart");
        containsMe1.add("you deserve a");
        containsMe1.add("you have cute");
        containsMe1.add("you have a cute");
        containsMe1.add("you have a remarkable");
        containsMe1.add("you are good");
        containsMe1.add("the world would be so boring without you");
        containsMe1.add("you are beautiful");
        containsMe1.add("you are so strong");
        containsMe1.add("you make me feel whole");
        containsMe1.add("you are so special to me");
        containsMe1.add("you are the light of my life");
        containsMe1.add("you have great");

        containsMe2.add("are my world");
        containsMe2.add("are my entire world");
        containsMe2.add("wow");
        containsMe2.add("hug");
        containsMe2.add("special");
        containsMe2.add("are perfect");

    }

    public String isCompliment(String str1) {
        if(strContains(str1, containsMe1)||(str1.contains("you")&&strContains(str1, containsMe2))) {
            int x = getRandomInt(3);
            switch (x) {
                case 0:
                    return "i am high performance";
                case 1:
                    return "well i am high performance";
                case 2:
                    return "i love when you simp for me";
            }
        }
        return "";
    }

    private Boolean strContains(String str1, ArrayList<String> containsMe) {
        for (String temp : containsMe) {
            if (str1.contains(temp)) {
                return true;
            }
        }
        return false;
    }

    public int getRandomInt(int max) {
        // 3 : 0,1,2
        return (int) Math.floor(Math.random() * Math.floor(max));
    }
}
