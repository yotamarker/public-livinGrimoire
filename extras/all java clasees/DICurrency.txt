package com.yotamarker.lgkotlin1;

import java.util.ArrayList;

public class DICurrency extends DISkill {
    private int mode = 0;
    private double sum = 0.0;
    private RegexUtil regexUtil = new RegexUtil();
    private String[] cases = { "shekel to dollar", "shekel to euro", "dollar to euro", "dollar to shekel",
            "euro to dollar", "euro to shekel" , "d to s", "days to seconds", "s to d", "seconds to days"};
    private String[] offLineCases = { "kg to shekel", "shekel to kg"};
    public DICurrency(Kokoro kokoro) {
        super(kokoro);
    }

    @Override
    public void input(String ear, String skin, String eye) {
        if(ear.contains("to")){
            Boolean online = true;
            for (int i = 0; i < offLineCases.length; i++) {
                if (ear.contains(offLineCases[i])) {
                    offLineConversionCase(offLineCases[i]);
                    sum = Double.parseDouble(regexUtil.regexChecker("[-+]?[0-9]*\\.?[0-9]*", ear));
                    online = false;
                    break;
                }
            }
            if(online){
                for (int i = 0; i < cases.length; i++) {
                    if (ear.contains(cases[i])) {
                        conversionCase(cases[i]);
                        Currency.updateCurrency();
                        sum = Double.parseDouble(regexUtil.regexChecker("[-+]?[0-9]*\\.?[0-9]*", ear));
                        break;
                    }
                }
            }
        }
    }
    private Boolean offLineConversionCase(String trigger) {
        Boolean result = false;
        switch (trigger) {
            case "kg to shekel":
                result = true;
                this.mode = 7;
                break;
            case "shekel to kg":
                result = true;
                this.mode = 8;
                break;
            default:
                break;
        }
        return result;
    }
    private Boolean conversionCase(String trigger) {
        Boolean result = false;
        switch (trigger) {
            case "shekel to dollar":
                result = true;
                this.mode = 1;
                break;
            case "shekel to euro":
                result = true;
                this.mode = 2;
                break;
            case "dollar to euro":
                result = true;
                this.mode = 3;
                break;
            case "dollar to shekel":
                result = true;
                this.mode = 4;
                break;
            case "euro to dollar":
                result = true;
                this.mode = 5;
                break;
            case "euro to shekel":
                result = true;
                this.mode = 6;
                break;
            case "d to s":
            case "days to seconds":
                result = true;
                this.mode = 9;
                break;
            case "s to d":
            case "seconds to days":
                result = true;
                this.mode = 10;
                break;
            default:
                break;
        }
        return result;
    }
    @Override
    public void output(Neuron noiron) {
        switch (mode) {
            case 1:
                if (Currency.getHasBeenUpdated()) {
                    mode = 0;
                    sum = sum / Currency.exchangeRate("shekel");
                    sum = sum * Currency.exchangeRate("dollar");
                    String appendCurrency = "dollars";
                    if (sum == 1) {
                        appendCurrency = "dollar";
                    }
                    noiron.algParts.add(verbatimGorithm(appendCurrency));
                    sum = 0.0;
                }
                break;
            case 2:
                if (Currency.getHasBeenUpdated()) {
                    mode = 0;
                    sum = sum / Currency.exchangeRate("shekel");
                    sum = sum * Currency.exchangeRate("euro");
                    String appendCurrency = "euros";
                    if (sum == 1) {
                        appendCurrency = "euro";
                    }
                    noiron.algParts.add(verbatimGorithm(appendCurrency));
                    sum = 0.0;
                }
                break;
            case 3:
                if (Currency.getHasBeenUpdated()) {
                    mode = 0;
                    sum = sum / Currency.exchangeRate("dollar");
                    sum = sum * Currency.exchangeRate("euro");
                    String appendCurrency = "euros";
                    if (sum == 1) {
                        appendCurrency = "euro";
                    }
                    noiron.algParts.add(verbatimGorithm(appendCurrency));
                    sum = 0.0;
                }
                break;
            case 4:
                if (Currency.getHasBeenUpdated()) {
                    mode = 0;
                    sum = sum / Currency.exchangeRate("dollar");
                    sum = sum * Currency.exchangeRate("shekel");
                    String appendCurrency = "shekels";
                    if (sum == 1) {
                        appendCurrency = "shekel";
                    }
                    noiron.algParts.add(verbatimGorithm(appendCurrency));
                    sum = 0.0;
                }
                break;
            case 5:
                if (Currency.getHasBeenUpdated()) {
                    mode = 0;
                    sum = sum / Currency.exchangeRate("euro");
                    sum = sum * Currency.exchangeRate("dollar");
                    String appendCurrency = "dollars";
                    if (sum == 1) {
                        appendCurrency = "dollar";
                    }
                    noiron.algParts.add(verbatimGorithm(appendCurrency));
                    sum = 0.0;
                }
                break;
            case 6:
                if (Currency.getHasBeenUpdated()) {
                    mode = 0;
                    sum = sum / Currency.exchangeRate("euro");
                    sum = sum * Currency.exchangeRate("shekel");
                    String appendCurrency = "shekels";
                    if (sum == 1) {
                        appendCurrency = "shekel";
                    }
                    noiron.algParts.add(verbatimGorithm(appendCurrency));
                    sum = 0.0;
                }
                break;
            case 7:
                    mode = 0;
                    sum = sum / .0041;
                    sum = sum / 10; // div agorot to shekel 10 agorot = 1 shekel
                    String appendCurrency = "shekels";
                    if (sum == 1) {
                        appendCurrency = "shekel";
                    }
                    noiron.algParts.add(verbatimGorithm(appendCurrency));
                    sum = 0.0;
                break;
            case 8:
                mode = 0;
                sum = sum * .0041;
                sum = sum * 10; // div agorot to shekel 10 agorot = 1 shekel
                String appendCurrency2 = " kilogram";
                String s1 = String.format("%.03f", this.sum);
                s1 = floatTrimmer(s1);
                noiron.algParts.add(verbatimGorithm(new APVerbatim(s1 + appendCurrency2)));
                sum = 0.0;
                break;
            case 9:
                mode = 0;
                sum = sum * 3600 * 24;
                noiron.algParts.add(verbatimGorithmPlain("seconds"));
                sum = 0.0;
                break;
            case 10:
                mode = 0;
                sum = sum / 3600 / 24;
                noiron.algParts.add(verbatimGorithmPlain("days"));
                sum = 0.0;
                break;
            default:
                break;
        }
    }

    private String floatTrimmer(String str1) {
        String tempStr = str1.substring(str1.length() - 1);
        if (!str1.contains(".")) {
            return str1;
        }
        if (tempStr.equals("0") || tempStr.equals(".")) {
            return floatTrimmer(str1.substring(0, str1.length() - 1));
        }
        return str1;
    }
    private Algorithm verbatimGorithm(String append) {
        // returns a simple algorithm for saying sent parameter
        String s1 = String.format("%.02f", this.sum);
        s1 = floatTrimmer(s1);
        AbsAlgPart itte = new APVerbatim(s1 + " " + append);
        String representation = "currency";
        ArrayList<AbsAlgPart> algParts1 = new ArrayList<>();
        algParts1.add(itte);
        Algorithm algorithm = new Algorithm("currency", representation, algParts1);
        return algorithm;
    }
    private Algorithm verbatimGorithm(AbsAlgPart itte) {
        // returns a simple algorithm for saying sent parameter
        String representation = "bukubukuchagama";
        ArrayList<AbsAlgPart> algParts1 = new ArrayList<>();
        algParts1.add(itte);
        Algorithm algorithm = new Algorithm("bukubukuchagama", representation, algParts1);
        return algorithm;
    }
    private Algorithm verbatimGorithmPlain(String append) {
        // returns a simple algorithm for saying sent parameter
        // handles integer sums
        String s1 = String.format("%.01f", this.sum);
        s1 = floatTrimmer(s1);
        AbsAlgPart itte = new APVerbatim(s1 + " " + append);
        String representation = "currency";
        ArrayList<AbsAlgPart> algParts1 = new ArrayList<>();
        algParts1.add(itte);
        Algorithm algorithm = new Algorithm("currency", representation, algParts1);
        return algorithm;
    }
}



