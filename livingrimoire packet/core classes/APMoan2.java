package com.yotamarker.lgkotlin1;



public class APMoan2 extends APMoan0 {

    public APMoan2() {
        super();
        interactions.add("do it");
        moans.add("lick it");
        moans.add("harder");
        groans.add("i heart you");
        groans.add("dear");
    }

    @Override
    public AbsAlgPart clone() {
        // TODO Auto-generated method stub
        return new APMoan2();
    }

    @Override
    public AbsAlgPart mutation() {
        // TODO Auto-generated method stub
        APMoan0 mutant = new APMoan1();
        switch (rand.nextInt(moanList.size() - 1)) {
            case 1:
                mutant = new APMoan1();
                break;
            case 2:
                mutant = new APMoan2();
                break;
            default:
                break;
        }
        return mutant;
    }
}