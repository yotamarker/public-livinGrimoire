package Auxiliary_Modules;

import LivinGrimoire.Algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

// (*)Algorithm Dispensers
public class AlgDispenser {
// super class to output an algorithm out of a selection of algorithms
    private ArrayList<Algorithm> algs = new ArrayList<>();
    private int activeAlg = 0;
    private Random rand = new Random();

    public AlgDispenser(Algorithm...algorithms) {
        Collections.addAll(algs, algorithms);
    }
    public AlgDispenser addAlgorithm(Algorithm alg){
        // builder pattern
        algs.add(alg);
        return this;
    }
    public Algorithm dispenseAlgorithm(){
        return algs.get(activeAlg);
    }
    public Algorithm rndAld(){
        // return a random algorithm
        return algs.get(rand.nextInt(algs.size()));
    }
    public void moodAlg(int mood){
        // set output algorithm based on number representing mood
        if ((mood>-1) && mood < (algs.size())){
            activeAlg = mood;
        }
    }
    public void algUpdate(int mood,Algorithm alg){
        // update an algorithm
        if (!((mood>-1) && mood < (algs.size()))){
            return;
        }
        algs.set(mood,alg);
    }
    public void algRemove(int mood){
        // remove an algorithm
        if (!((mood>-1) && mood < (algs.size()))){
            return;
        }
        algs.remove(mood);
    }
    public void cycleAlg(){
        activeAlg++;
        if (activeAlg == algs.size()){
            activeAlg = 0;
        }
    }
}
