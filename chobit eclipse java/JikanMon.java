package chobit;

public class JikanMon {
	private PlayGround playGround = new PlayGround();
	private int x = -1;
	private int timePause = 3;

	public Boolean isBlocked() {
		int nowSeconds = playGround.getSecondsAsInt();
		if ((x < 0 || nowSeconds > x) || (x > 60 && nowSeconds > (x - 60))) {
			x = -1;
			return false;
		}
		return true;
	}

	public void block() {
		this.x = playGround.getSecondsAsInt() + timePause;
	}

	public void setTimePause(int timePause) {
		if ((timePause > 0) && (timePause < 30)) {
			this.timePause = timePause;
		}
	}
}
