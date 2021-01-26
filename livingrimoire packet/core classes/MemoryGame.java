package com.yotamarker.lgkotlin1;

import java.util.ArrayList;

public class MemoryGame {
    private int index = 0;
    private ArrayList<String> colors = new ArrayList<>();
    private ArrayList<String> baseColors = new ArrayList<>();

    public MemoryGame() {
        baseColors.add("red");
        baseColors.add("blue");
        baseColors.add("black");
        baseColors.add("yellow");
    }

    public String reset() {
        index = 0;
        colors = new ArrayList<>();
        colors.add(genRndColor());
        return colors.get(0);
    }

    public String play(String input) {
        String[] spliten = input.split(" ");
        String strTemp = "";
        for (String str1 : spliten) {
            strTemp = strContainsList(str1);
            if (!strTemp.isEmpty()) {
                if (colors.get(index).equals(strTemp)) {
                    index++;// boolean for ok return
                    if (index == colors.size()) {
                        index = 0;
                        colors.add(genRndColor());
                        return getGameStr();
                    }
                } else {
                    return "you have reached level " + colors.size();
                }
            }
        }
        return "";
    }
    private String genRndColor() {
        int x = (int) Math.floor(Math.random() * 4);
        switch (x) {
            case 0:
                return "red";
            case 1:
                return "blue";
            case 2:
                return "black";
            case 3:
                return "yellow";
        }
        return"";
    }

    public String strContainsList(String str1) {
        for (String temp : baseColors) {
            if (str1.contains(temp)) {
                return temp;
            }
        }
        return "";
    }

    public String getGameStr() {
        String result = "";
        for (String string : colors) {
            result += string + " ";
        }
        return result;
    }
}
