package com.yotamarker.lgkotlin1;

public class NumToKanji {
    public String getAsKanji(int N) {
        if (N == 0) {
            return "零";
        } else {
            return toKanji(N);
        }
    }

    private String toKanji(int N) {
        String kanji;
        kanji = "enter a number";
        String characters = N + "";
        switch (characters.length()) {
            case 1:
                switch (N) {
                    case 1:
                        kanji = "一";
                        break;
                    case 2:
                        kanji = "二";
                        break;
                    case 3:
                        kanji = "三";
                        break;
                    case 4:
                        kanji = "四";
                        break;
                    case 5:
                        kanji = "五";
                        break;
                    case 6:
                        kanji = "六";
                        break;
                    case 7:
                        kanji = "七";
                        break;
                    case 8:
                        kanji = "八";
                        break;
                    case 9:
                        kanji = "九";
                        break;
                    default:
                        kanji = "";
                        break;

                }

                break;
            case 2:
                switch (N / 10) {
                    case 1:
                        kanji = "十" + " " + toKanji(N % 10);
                        break;
                    case 2:
                        kanji = "二十" + " " + toKanji(N % 10);
                        break;
                    case 3:
                        kanji = "三十" + " " + toKanji(N % 10);
                        break;
                    case 4:
                        kanji = "四十" + " " + toKanji(N % 10);
                        break;
                    case 5:
                        kanji = "五十" + " " + toKanji(N % 10);
                        break;
                    case 6:
                        kanji = "六十" + " " + toKanji(N % 10);
                        break;
                    case 7:
                        kanji = "七十" + " " + toKanji(N % 10);
                        break;
                    case 8:
                        kanji = "八十" + " " + toKanji(N % 10);
                        break;
                    case 9:
                        kanji = "九十" + " " + toKanji(N % 10);
                        break;
                    default:
                        kanji = "";
                        break;
                }

                break;
        }
        return kanji;

    }
}

