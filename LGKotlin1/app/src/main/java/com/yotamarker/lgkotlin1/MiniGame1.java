package com.yotamarker.lgkotlin1;

public class MiniGame1 {
    private int n = 0;
    private int min = 0;
    private int limit = 1000;
    private int max = limit;
    private ZeroTimeGate zeroTimeGate = new ZeroTimeGate(0);
    private RegexUtil regexUtil = new RegexUtil();

    public void init() {
        n = getRandomInt(limit);
        zeroTimeGate.open(5);
        int min = 0;
        int limit = 1000;
        int max = limit;
    }

    public int getRandomInt(int max) {
        // 3 : 0,1,2
        return (int) Math.floor(Math.random() * Math.floor(max));
    }

    public String play(String ear) {
        if (zeroTimeGate.isClosed()) {
            return "time out you lose";
        }
        String numStr = regexUtil.numberRegex(ear);
        if (numStr.isEmpty()) {
            return "";
        }
        int guess = Integer.parseInt(numStr);
        if (guess == n) {
            return "you win";
        }
        if (min > guess) {
            return "that number is too small you lose";
        }
        if (max < guess) {
            return "that number is too big you lose";
        }
        if (n > guess) {
            min = guess;
            return "guess a bigger number";
        }
        if (n < guess) {
            max=guess;
            return "guess a smaller number";
        }
        return "";
    }
}
