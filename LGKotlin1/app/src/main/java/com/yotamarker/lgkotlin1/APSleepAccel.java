package com.yotamarker.lgkotlin1;

public class APSleepAccel extends AbsAlgPart {
	// waker = wake
	private PlayGround playGround = new PlayGround();
	private int wakeTime; // hour

	public void setWakeTime(int wakeTime) {
		this.wakeTime = wakeTime;
	}

	private Boolean isComplete = false;

	public APSleepAccel(int wakeTime) {
		super();
		this.wakeTime = wakeTime;
	}

	@Override
	public String action(String ear, String skin, String eye) {
		// TODO Auto-generated method stub
		String axnStr = "";
		if (this.isComplete) {
			return axnStr;
		}
		if ((wakeTime == playGround.getHoursAsInt()) || (ear.contains("wake"))) {
			isComplete = true;
			axnStr = "waking";
			return axnStr;
		}
		if (!ear.isEmpty()) {
			axnStr = "sleeping";
		}
		return axnStr;
	}

	public int getWakeTime() {
		return wakeTime;
	}

	@Override
	public enumFail failure(String input) {
		// TODO Auto-generated method stub
		return enumFail.ok;
	}

	@Override
	public Boolean completed() {
		// TODO Auto-generated method stub
		return this.isComplete;
	}

	@Override
	public AbsAlgPart clone() {
		// TODO Auto-generated method stub
		return new APSleep(this.wakeTime);
	}

	@Override
	public Boolean itemize() {
		// TODO Auto-generated method stub
		return false;
	}

}