package AXJava;

import java.util.ArrayList;

public class AXStrOrDefault {
    public String getOrDefault(String str1, String default1){
        if(str1.isEmpty()){
            return default1;
        }
        return str1;
    }
}
