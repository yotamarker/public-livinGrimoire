package com.yotamarker.lgkotlin1;

public class APFilth1 extends AbsAlgPart implements Mutatable{
	Boolean mode = false; // manual
	final int PAUSELIM = 3;
	final int MANUALLIM = 5;
	final int AUTOLIM = 3;
	int countDown = MANUALLIM;
	int moanPause = 0;
	Boolean isCompleted = false;
	enumFail failure = enumFail.ok;
	int CountDownLatch;
	String myMoan = "make a creamy";
	@Override
	public String action(String ear, String skin, String eye) {
		if (countDown < 1) {
			isCompleted = true;
			if (!mode) {
				this.failure = enumFail.fail;
				isCompleted = false;
				return "finished";
			}
		}
		switch (ear) {
		case "please": case "i beg you":
			return "no you may not fuck me";
		case "automatic":
			countDown = AUTOLIM;
			mode = true;
			return "bwahaha";
		case "manual":
			countDown = MANUALLIM;
			mode = false;
			return "kiss";
			//break;
		case "ok": case "stop": case"okay": case "that will do": case "enough":case "thank you":
			this.isCompleted = true;
			return "i love you";// *finisher
		case "continue":
		case "again":
			countDown = (!mode) ? MANUALLIM : AUTOLIM;
			return "bwahaha";
		default:
			moanPause--;
			if (moanPause < 1) {
				moanPause = PAUSELIM;
				countDown--;
				return myMoan;
			}
			break;
		}
		// continue on gyro input
		if (skin.contains("shake")) {
			countDown = (mode) ? MANUALLIM : AUTOLIM;
			return "go in potty";
		}
		return "";
	}

	@Override
	public Boolean itemize() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public enumFail failure(String input) {
		// TODO Auto-generated method stub
		return this.failure;
	}

	@Override
	public Boolean completed() {
		// TODO Auto-generated method stub
		return this.isCompleted;
	}

	@Override
	public AbsAlgPart clone() {
		// TODO Auto-generated method stub
		return new APFilth1();
	}
	@Override
	public int getMutationLimit() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public AbsAlgPart mutation() {
		// TODO Auto-generated method stub
		return new APFilth2();
	}
}
