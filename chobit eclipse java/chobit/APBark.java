package chobit;

public class APBark extends AbsAlgPart {
	// bark x times
	// pause y times
	// cycle z times
	// or till user present
	private int pauseCounter;
	private int barkCounter;
	private int pauselim;
	private int barklim;
	private int stopCounter;
	private String bark;

	public APBark(int stopCounter, Person master) {
		super();
		this.stopCounter = stopCounter * 2;
		this.master = master;
		pauselim = 4;
		barklim = 3;
		pauseCounter = pauselim;
		barkCounter = barklim;
		bark = "user";
	}

	public APBark(int stopCounter, String bark, Person master) {
		super();
		this.stopCounter = stopCounter;
		pauselim = 4;
		barklim = 3;
		pauseCounter = pauselim;
		barkCounter = barklim;
		this.bark = bark;
		this.master = master;
	}
	public APBark(int pauselim, int barklim, int stopCounter, String bark, Person master) {
		super();
		this.pauselim = pauselim;
		this.barklim = barklim;
		this.barkCounter = barklim;
		this.stopCounter = stopCounter;
		this.bark = bark;
		this.master = master;
	}

	private Boolean barkMode = true;
	private Person master;
	@Override
	public String action(String ear, String skin, String eye) {
		// TODO Auto-generated method stub
		if (ear.contains(master.getJutsu())) {
			stopCounter = 0;
			return "";
		}
		if (barkMode) {
			barkCounter--;
			if (barkCounter < 1) {
				barkMode = !barkMode;
				pauseCounter = pauselim;
				stopCounter--;
			}
			return bark;
		} else {
			pauseCounter--;
			if (pauseCounter < 1) {
				barkMode = !barkMode;
				barkCounter = barklim;
				stopCounter--;
			}
			return "";
		}
	}



	@Override
	public Boolean itemize() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public enumFail failure(String input) {
		// TODO Auto-generated method stub
		return enumFail.ok;
	}

	@Override
	public Boolean completed() {
		// TODO Auto-generated method stub
		return stopCounter < 1;
	}

	@Override
	public AbsAlgPart clone() {
		return new APBark(this.pauselim, this.barklim, this.stopCounter, this.bark, this.master);
	}

}
