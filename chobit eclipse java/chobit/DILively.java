package chobit;

public class DILively extends DISkill {
	private int counter = 0;
	private TimeGate timeGate = new TimeGate();
	public DILively(Kokoro kokoro) {
		super(kokoro);
		timeGate.setPause(5);// 5 minutes
	}

	@Override
	public void input(String ear, String skin, String eye) {
		if (!timeGate.isClosed()) {
			counter = 0;
			timeGate.close();
		}
		if (!ear.isEmpty()) {
			counter++;
		} // can add special skin input
		String emotion = "";
		if (counter > 5) {
			emotion = "4";
		} else if (counter > 3) {
			emotion = "3";
		} else if (counter > 1) {
			emotion = "2";
		}

	}
	}
