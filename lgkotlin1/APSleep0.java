package com.yotamarker.lgkotlin1;

/* activate the nemure action
 * of the chobit class
 * where the saving and loading of data against a data base should be
 * */
public class APSleep0 extends AbsAlgPart {
    private Boolean isComplete = false;
    private Chobit.InnerClass actualSleep;

    @Override
	public String action(String ear, String skin, String eye) {
        String puke;
        puke = this.actualSleep.nemure();
        isComplete = true;
        return puke;
    }

    public APSleep0(Chobit.InnerClass actualSleep) {
        super();
        this.actualSleep = actualSleep;
    }

    @Override
    public enumFail failure(String input) {
        // TODO Auto-generated method stub
        return enumFail.ok;
    }

    @Override
    public Boolean completed() {
        // TODO Auto-generated method stub
        return isComplete;
    }

    @Override
    public AbsAlgPart clone() {
        // TODO Auto-generated method stub
        return new APSleep0(this.actualSleep);
    }

	@Override
	public Boolean itemize() {
		// TODO Auto-generated method stub
		return false;
	}

}
