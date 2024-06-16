package LivinGrimoire;

import java.util.ArrayList;
import java.util.Hashtable;

// used to transport algorithms to other classes
public class Neuron {
    private Hashtable<Integer,ArrayList<Algorithm>> defcons = new Hashtable<>();

    public Neuron() {
        for (int i = 1; i < 6; i++) {
            defcons.put(i,new ArrayList<Algorithm>());
        }
    }
    public void insertAlg(int priority, Algorithm alg){
        if ((0< priority) && (priority < 6)){
            if (defcons.get(priority).size() < 4){
                defcons.get(priority).add(alg);
            }
        }
    }
    public Algorithm getAlg(int defcon){
        if (defcons.get(defcon).size() > 0){
            Algorithm temp = defcons.get(defcon).get(0);
            defcons.get(defcon).remove(0);
            return temp;
        }
        return null;
    }
}
