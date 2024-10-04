package Auxiliary_Modules;

public class ChangeDetector {
    private String A;
    private String B;
    private int prev = -1;

    public ChangeDetector(String a, String b) {
        A = a;
        B = b;
    }
    public int detectChange(String ear){
        // a->b ret 2; b->1 ret 1; else ret 0
        if (ear.isEmpty()){
            return 0;
        }
        int current;
        if (ear.contains(A)){
            current = 1;
        } else if (ear.contains(B)) {
            current = 2;
        }
        else{
            return 0;
        }
        int result = 0;
        if((current == 1)&& (prev == 2)){
            result = 1;
        }
        if((current == 2)&& (prev == 1)){
            result = 2;
        }
        prev = current;
        return result;
    }
}
