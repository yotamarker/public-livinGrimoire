package com.yotamarker.lgkotlin1;

public class InputAccel implements Runnable {
	// AbsCmdReq dClass, String ear, String skin, String eye
	private AbsCmdReq dClass;
	private String ear, skin, eye;

	public InputAccel(AbsCmdReq dClass, String ear, String skin, String eye) {
		super();
		this.dClass = dClass;
		this.ear = ear;
		this.skin = skin;
		this.eye = eye;
	}

	@Override
	public void run() {
		dClass.input(ear, skin, eye);
	}

}
