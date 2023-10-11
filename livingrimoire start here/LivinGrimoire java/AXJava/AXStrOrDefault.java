package AXJava;

import java.util.ArrayList;

public class AXStrOrDefault {
    public String getOrDefault(String str1, String default1){
        return str1.isEmpty() ? default1 : str1;
    }
}
