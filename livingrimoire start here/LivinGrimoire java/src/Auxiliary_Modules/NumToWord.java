package Auxiliary_Modules;

public class NumToWord {
    private String convert(int n){
        String result = "enter a number";
        String nStr = n +"";
        switch(nStr.length()){
            case 1:
                switch(n){
                    case 1: result = "one";break;
                    case 2:result="two";break;
                    case 3:result="three";break;
                    case 4:result="four";break;
                    case 5:result="five";break;
                    case 6:result="six";break;
                    case 7:result="seven";break;
                    case 8:result="eight";break;
                    case 9:result="nine";break;
                    default: result = "";break;
                }break;
            case 2:
                switch (n/10){
                    case 1:
                        result = "ten" + " " +  convert(n % 10);
                        break;
                    case 2:
                        result = "twenty" + " " +  convert(n % 10); break;
                    case 3:
                        result = "thirty" + " " +  convert(n % 10); break;
                    case 4:
                        result = "forty" + " " +  convert(n % 10); break;
                    case 5:
                        result = "fifty" + " " +  convert(n % 10); break;
                    case 6:
                        result = "Sixty" + " " +  convert(n % 10); break;
                    case 7:
                        result = "seventy" + " " +  convert(n % 10); break;
                    case 8:
                        result = "eighty" + " " +  convert(n % 10); break;
                    case 9:
                        result = "ninety" + " " +  convert(n % 10); break;
                    default:
                        result = ""; break;
                }break;
            case 3:
                switch (n / 100)
                {
                    case 1:
                        result = "one hundred" + " " + convert(n % 100);
                        break;
                    case 2:
                        result = "two hundred" + " " + convert(n % 100); break;
                    case 3:
                        result = "three hundred" + " " + convert(n % 100); break;
                    case 4:
                        result = "four hundred" + " " + convert(n % 100); break;
                    case 5:
                        result = "five hundred" + " " + convert(n % 100); break;
                    case 6:
                        result = "six hundred" + " " + convert(n % 100); break;
                    case 7:
                        result = "seven hundred" + " " + convert(n % 100); break;
                    case 8:
                        result = "eight hundred" + " " + convert(n % 100); break;
                    case 9:
                        result = "nine hundred" + " " + convert(n % 100); break;
                    default:
                        result = ""; break;
                }
                break;
            case 4:
                switch (n / 1000){
                    case 1:
                        result = "one Thousand" + " " + convert(n % 1000);
                        break;
                    case 2:
                        result = "two Thousand" + " " + convert(n % 1000); break;
                    case 3:
                        result = "three Thousand" + " " + convert(n % 1000); break;
                    case 4:
                        result = "four Thousand" + " " + convert(n % 1000); break;
                    case 5:
                        result = "five Thousand" + " " + convert(n % 1000); break;
                    case 6:
                        result = "six Thousand" + " " + convert(n % 1000); break;
                    case 7:
                        result = "seven Thousand" + " " + convert(n % 1000); break;
                    case 8:
                        result = "eight Thousand" + " " + convert(n % 1000); break;
                    case 9:
                        result = "nine Thousand" + " " + convert(n % 1000); break;
                    default:
                        result = ""; break;
                }
                break;
            case 5:
                String temp1 = convert(n/1000) + " thousand"; temp1 = temp1 + " " + convert(n% 1000);
                result = temp1;
                break;
            case 6:
                String temp2 = convert(n/1000)+ " thousand";
                temp2 = temp2 + " " + convert(n% 1000);
                result = temp2;
                break;
            case 7:
                switch (n / 1000000)
                {
                    case 1:
                        result = "one million" + " " + convert(n % 1000000);
                        break;
                    case 2:
                        result = "two million" + " " + convert(n % 1000000); break;
                    case 3:
                        result = "three million" + " " + convert(n % 1000000); break;
                    case 4:
                        result = "four million" + " " + convert(n % 1000000); break;
                    case 5:
                        result = "five million" + " " + convert(n % 1000000); break;
                    case 6:
                        result = "six million" + " " + convert(n % 1000000); break;
                    case 7:
                        result = "seven million" + " " + convert(n % 1000000); break;
                    case 8:
                        result = "eight million" + " " + convert(n % 1000000); break;
                    case 9:
                        result = "nine million" + " " + convert(n % 1000000); break;
                    default:
                        result = ""; break;
                }
                break;
             }
         return result;
    }
    private String repairText(String n){
        String res = n;

        res = res.replace("ten one", "eleven");

        res = res.replace("ten two", "twelve");

        res = res.replace("ten one", "thirteen");

        res = res.replace("ten one", "fourteen");

        res = res.replace("ten one", "fifteen");

        res = res.replace("ten one", "sixteen");

        res = res.replace("ten one", "seventeen");

        res = res.replace("ten one", "eighteen");

        res = res.replace("ten one", "nineteen");

        return res;
    }
    public String wordification(int n){
        String result = convert(n);
        result = repairText(result);
        return result;
    }
}
