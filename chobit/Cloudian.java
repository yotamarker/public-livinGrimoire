package chobit;

/*a trigger for reviving a dormant algorithm
 * there should be a cloudian per APCld (alg part cloudian user)
 * */
public class Cloudian {
	private Boolean active = false;
	private Algorithm algorithm = null;
	private String timeTrig = ""; // time trigger
	private String earTrig = "zhakhrixxer";
	private String eyeTrig = "zhakhrixxer";
	private int countDown = 0;
	private Boolean dead = false;

	public Boolean getDead() {
		return dead;
	}

	private Boolean fin = false;

	public Boolean getFin() {
		return fin;
	}

	private PlayGround playGround = new PlayGround();

	public Boolean updateStat(String ear, String eye) {
		Boolean result = false;
		if (ear.contains(earTrig) || eye.contains(eyeTrig)) {
			fin = true;
			result = true;
		}
		if (playGround.getCurrentTimeStamp().equals(this.timeTrig)) {
			result = true;
		}
		result = result && !active;
		if (result) {
			active = true;
		}
		return result;
	}

	public int getCountDown() {
		return countDown;
	}

	public void setCountDown(int countDown) {
		this.countDown = countDown;
	}

	public void reset() {
		active = false;
		algorithm = null;
		timeTrig = ""; // time trigger
		earTrig = "zhakhrixxer";
		eyeTrig = "zhakhrixxer";
		countDown = 0;
		dead = false;
		fin = false;
	}

	public void setMe(int count, String ear, String eye, String setAlmTo) {
		this.countDown = count;
		this.earTrig = ear;
		this.eyeTrig = eye;
		this.timeTrig = setAlmTo;
	}

	public void sleep(int dormantFor) {
		if (this.countDown > 0) {
			this.countDown--;
		} else {
			this.dead = true;
		}
		// this.timeTrig = playGround.getFutureInXMin(dormantFor);
		this.timeTrig = playGround.getFutureFromXInYMin(dormantFor, this.timeTrig);
	}
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Algorithm getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(Algorithm algorithm) {
		this.algorithm = algorithm;
	}

	public String getTimeTrig() {
		return timeTrig;
	}

	public void setTimeTrig(String timeTrig) {
		this.timeTrig = timeTrig;
	}

	public String getEarTrig() {
		return earTrig;
	}

	public void setEarTrig(String earTrig) {
		this.earTrig = earTrig;
	}

	public String getEyeTrig() {
		return eyeTrig;
	}

	public void setEyeTrig(String eyeTrig) {
		this.eyeTrig = eyeTrig;
	}

}
