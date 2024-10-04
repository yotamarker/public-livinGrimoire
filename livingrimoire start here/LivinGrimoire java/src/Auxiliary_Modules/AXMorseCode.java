package Auxiliary_Modules;

class AXMorseCode {
    public String toMorse(String msg) {return code(msg);}
    public String toHuman(String msg) {return decoder(msg);}
    public static String code(String msg) {
        char[] arr = msg.toCharArray();
        StringBuilder result = new StringBuilder();
        for (char c : arr) {

            result.append(charToMorse(c));
        }
        return result.toString();
    }
    private static String charToMorse(char x) {
        String result="";
        switch (x) {
            case 'a': result = "*-";break;
            case 'b': result = "-***";break;
            case 'c': result = "-*-*";break;
            case 'd': result = "-**";break;
            case 'e': result = "*";break;
            case 'f': result = "**-*";break;
            case 'g': result = "--*";break;
            case 'h': result = "****";break;
            case 'i': result = "**";break;
            case 'j': result = "*---";break;
            case 'k': result = "-*-";break;
            case 'l': result = "*-**";break;
            case 'm': result = "--";break;
            case 'n': result = "-*";break;
            case 'o': result = "---";break;
            case 'p': result = "*--*";break;
            case 'q': result = "--*-";break;
            case 'r': result = "*-*";break;
            case 's': result = "***";break;
            case 't': result = "-";break;
            case 'u': result = "**-";break;
            case 'v': result = "***-";break;
            case 'w': result = "*--";break;
            case 'x': result = "-**-";break;
            case 'y': result = "-*--";break;
            case 'z': result = "--**";break;
            case '0': result = "-----";break;
            case '1': result = "*----";break;
            case '2': result = "**---";break;
            case '3': result = "***--";break;
            case '4': result = "****-";break;
            case '5': result = "*****";break;
            case '6': result = "-****";break;
            case '7': result = "--***";break;
            case '8': result = "---**";break;
            case '9': result = "----*";break;
            case '.': result = "*-*-*-";break;
            case ',': result = "--**--";break;
            case '?': result = "**--**";break;
            case ' ': result = "/";break;

            default:
                break;
        }
        return result + "/";
    }
    public static String decoder(String msg) {

        StringBuilder morseChr = new StringBuilder();
        StringBuilder result = new StringBuilder();
        char[] arr = msg.toCharArray();
        for (char c : arr) {
            if (c != '/') {
                morseChr.append(c);
            } else {
                result.append(morseCharToChar(morseChr.toString()));
                morseChr = new StringBuilder();
            }
        }
        result = new StringBuilder(result.toString().replaceAll("  ", " "));
        return result.toString();
    }
    private static char morseCharToChar(String x) {
        char result='@';
        switch (x) {
            case "*-": result = 'a';break;
            case "-***": result = 'b';break;
            case "-*-*": result = 'c';break;
            case "-**": result = 'd';break;
            case "*": result = 'e';break;
            case "**-*": result ='f' ;break;
            case "--*": result = 'g';break;
            case "****": result = 'h';break;
            case "**": result = 'i';break;
            case "*---": result = 'j';break;
            case "-*-": result = 'k';break;
            case "*-**": result = 'l';break;
            case "--": result = 'm';break;
            case "-*": result = 'n';break;
            case "---": result = 'o';break;
            case "*--*": result = 'p';break;
            case "--*-": result = 'q';break;
            case "*-*": result = 'r';break;
            case "***": result = 's';break;
            case "-": result = 't';break;
            case "**-": result = 'u';break;
            case "***-": result = 'v';break;
            case "*--": result = 'w';break;
            case "-**-": result = 'x';break;
            case "-*--": result = 'y';break;
            case "--**": result = 'z';break;
            case "-----": result = '0';break;
            case "*----": result = '1';break;
            case "**---": result = '2';break;
            case "***--": result = '3';break;
            case "****-": result = '4';break;
            case "*****": result = '5';break;
            case "-****": result = '6';break;
            case "--***": result = '7';break;
            case "---**": result = '8';break;
            case "----*": result = '9';break;
            case "*-*-*-": result = '.';break;
            case "--**--": result =',' ;break;
            case "**--**": result = '?';break;
            case "/": result = ' ';break;

            default:
                break;
        }
        if(result == '@') {result = ' ';}
        return result;
    }
}
