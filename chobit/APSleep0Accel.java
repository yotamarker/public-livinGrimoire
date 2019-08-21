package chobit;

public class APSleep0Accel extends AbsAlgPart {
	private Boolean isComplete = false;
	private ChobitAccel.InnerClass actualSleep;

	@Override
	public String action(String ear, String skin, String eye) {
		String puke;
		puke = this.actualSleep.nemure();
		isComplete = true;
		return puke;
	}

	public APSleep0Accel(ChobitAccel.InnerClass actualSleep) {
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
		return new APSleep0Accel(this.actualSleep);
	}

	@Override
	public Boolean itemize() {
		// TODO Auto-generated method stub
		return false;
	}

}

