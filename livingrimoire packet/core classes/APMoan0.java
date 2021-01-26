package com.yotamarker.lgkotlin1;


import java.util.ArrayList;
import java.util.Random;

public class APMoan0 extends AbsAlgPart implements Mutatable {
    protected Random rand = new Random();
    protected final int failed = 3;
    protected enumFail failure = enumFail.ok;
    protected boolean isComplete = false;
    protected ArrayList<String> interactions;
    protected ArrayList<String> moans;
    protected ArrayList<String> groans;
    protected ArrayList<String> moanList;
    protected int noInputCounter = 0;

    public APMoan0() {
        super();
        moanList = new ArrayList<>();
        moanList.add("1");
        moanList.add("2");
        interactions = new ArrayList<>();
        moans = new ArrayList<>();
        groans = new ArrayList<>();
    }

    @Override
    public AbsAlgPart mutation() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
	public String action(String ear, String skin, String eye) {
		if (!ear.equals("")) {
            noInputCounter = 0;
        }
		if (ear.contains("thank you")) {
            isComplete = true;
            return "fuck you very much";
        }
		if (ear.equals("")) {
            noInputCounter++;
            if (noInputCounter > failed) {
                failure = enumFail.fail;
            }
        } //
		else if (interactions.contains(ear)) {
            return moans.get(rand.nextInt(moans.size()));
        }
        return groans.get(rand.nextInt(groans.size()));
    }

    @Override
    public enumFail failure(String input) {
        // TODO Auto-generated method stub
        return this.failure;
    }

    @Override
    public Boolean completed() {
        // TODO Auto-generated method stub
        return isComplete;
    }

    @Override
    public AbsAlgPart clone() {
        // TODO Auto-generated method stub
        return null;
    }

	@Override
	public Boolean itemize() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getMutationLimit() {
		// TODO Auto-generated method stub
		return 1;
	}

}
