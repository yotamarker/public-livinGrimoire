package Auxiliary_Modules;

import java.util.ArrayList;
import java.util.Collections;

public class AXLHub {
    // hubs many reply decorators, language translators, encriptors and other string modifiers
    // decorate(str) to decorate string using the active string decorator
    private Cycler cycler;
    private DrawRnd drawRnd = new DrawRnd();
    private int size;
    private ArrayList<AXLHousing> nyaa = new ArrayList<>();
    private int activeNyaa = 0;
    public AXLHub(AXLHousing...nyaa) {
        Collections.addAll(this.nyaa, nyaa);
        size = this.nyaa.size();
        cycler = new Cycler(size -1);
        cycler.setToZero();
    }
    public String decorate(String str1){
        return nyaa.get(activeNyaa).decorate(str1);
    }
    public void cycleDecoration(){
        activeNyaa = cycler.cycleCount();
    }
    public void randomizeDecoration(){
        activeNyaa = drawRnd.getSimpleRNDNum(size);
    }
    public void modeDecoration(int mode){
        if(mode < -1){return;}
        if(mode >= nyaa.size()){return;}
        activeNyaa = mode;
    }
}
