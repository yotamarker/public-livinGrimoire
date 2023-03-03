package AXJava;

import LivinGrimoire.LGFIFO;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class RepeatedElements {
    // detects repeating elements
    private LGFIFO<String> p1 = new LGFIFO<String>();
    private LGFIFO<String> p2 = new LGFIFO<String>();
    private int queLimit = 5;

    public int getQueLimit() {
        return queLimit;
    }

    public void setQueLimit(int queLimit) {
        if(!(queLimit>0)){return;}
        this.queLimit = queLimit;
    }
    public void input(String in1){
        if(in1.isEmpty()){return;}
        if(p1.contains(in1)){
            p2.add(in1);
            if(p2.size()>queLimit){
                p2.poll();
            }
        }
        p1.add(in1);
        if(p1.size()>queLimit){
            p1.poll();
        }
    }

    public Boolean contains(String in1){
        // is in1 a repeating element?
        return p2.contains(in1);
    }
    public String getRNDElement(){
        return p2.getRNDElement();
    }
}
