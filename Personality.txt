package com.yotamarker.lgkotlin1;

import java.util.ArrayList;
import java.util.Hashtable;

public class Personality {
    /*this class is used in the ChobitV2 c'tor.
it enables loading a complete skill set (a sub class of the personality class)
using 1 line of code. of course you can also select specific skills to add from
the subclasses c'tor. see also Personality1 for example.*/
    protected Kokoro kokoro; // soul
    protected ArrayList<AbsCmdReq> dClassesLv1 = new ArrayList<>();// can engage with anyone
    protected ArrayList<AbsCmdReq> dClassesLv2 = new ArrayList<>();// can engage with friends and work related
    protected ArrayList<AbsCmdReq> dClassesLv3 = new ArrayList<>();// can engage only by user
    protected Permission permission = Permission.newInstance("xxx", "sweetie", "honey");
    protected DPermitter dPermitter = new DPermitter(permission);//TODO
    protected Hashtable<String, Integer> AlgDurations = new Hashtable<>();
    protected Fusion fusion = new Fusion(AlgDurations);
    //fusion.getReqOverload() // an overload of requests on the brain
    //fusion.getRepReq() // someone is negging and asking the same thing over and over again
    /*
    flight or fight skills may need access to the above fusion class booleans
    on the output methode of a skill this skills will load algorithms to the highest priority of the noiron
    which carries algorithms :
    noiron.negativeAlgParts.add(Algorithm)
    * */
    public Personality(AbsDictionaryDB absDictionaryDB) {
        this.kokoro = new Kokoro(absDictionaryDB);
    }
    public Personality() {
        this.kokoro = new Kokoro(new AbsDictionaryDBShadow());
    }

    public ArrayList<AbsCmdReq> getdClassesLv1() {
        return dClassesLv1;
    }

    public ArrayList<AbsCmdReq> getdClassesLv2() {
        return dClassesLv2;
    }

    public ArrayList<AbsCmdReq> getdClassesLv3() {
        return dClassesLv3;
    }

    public Kokoro getKokoro() {
        return kokoro;
    }

    public Permission getPermission() {
        return permission;
    }

    public DPermitter getdPermitter() {
        return dPermitter;
    }

    public Hashtable<String, Integer> getAlgDurations() {
        return AlgDurations;
    }

    public Fusion getFusion() {
        return fusion;
    }
}
