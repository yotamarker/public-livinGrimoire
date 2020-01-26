package com.yotamarker.lgkotlin1;

public class APAttentionForAnyReply extends AbsAlgPart {

	private int pauseCounter;
	private int barkCounter;
	private int pauselim;
	private int barklim;
	private int stopCounter;
	private String bark;

	public APAttentionForAnyReply(int pauselim, int barklim, int stopCounter, String bark) {
		super();
		this.pauselim = pauselim;
		this.barklim = barklim;
		this.barkCounter = barklim;
		this.stopCounter = stopCounter;
		this.bark = bark;
	}

	private Boolean barkMode = true;

	@Override
	public String action(String ear, String skin, String eye) {
		// TODO Auto-generated method stub
		if (!ear.isEmpty()) {
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
		return new APAttentionForAnyReply(this.pauselim, this.barklim, this.stopCounter, this.bark);
	}

}

