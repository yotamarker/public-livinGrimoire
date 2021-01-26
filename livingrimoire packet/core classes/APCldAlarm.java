package com.yotamarker.lgkotlin1;

/*
 * megata kyoujin skill
 * this beauty is an alarm skill
 * it has the ability to go dormant and to get revived when needed
 * by using triggers : eye ear or time. 
*/
public class APCldAlarm extends AbsAlgPart {
	private Cloudian cld;
	private Boolean isComplete = false;
	private enumFail enumfail = enumFail.ok;
	private String zman = "";

	public APCldAlarm(Cloudian cld, String zman) {
		super();
		this.cld = cld;
		this.zman = zman;
		this.cld.setActive(true);
		cld.setMe(4, "ok", "zhakhrix", zman);
	}

	public APCldAlarm(Cloudian cld, String zman, Boolean b1) {
		super();
		this.cld = cld;
		this.zman = zman;
	}

	@Override
	public String action(String ear, String skin, String eye) {
		// TODO Auto-generated method stub
		String result = "";
		if (cld.getFin()) {
			result = "bwahaha";
			isComplete = true;
			cld.reset();
		}
		else if (!cld.getDead()) {
			switch (cld.getCountDown()) {
			case 4:
				result = "ok";
				cld.sleep(0);
				break;
			case 3:
				result = "wake up";
				cld.sleep(2);
				break;
			case 2:
				result = "come on wake up";
				cld.sleep(2);
				break;
			case 1:
				result = "please my darling do wake up";
				cld.sleep(2);
				break;
			default:
				result = "wake up I command you";
				cld.sleep(2);
				break;
			}
			// cld.sleep(2);
			enumfail = enumfail.cloudian;
			cld.setActive(false);
		} else {
			result = "it can not be helped";
			isComplete = true;
			cld.reset();
		}
		
		return result;
	}

	@Override
	public Boolean itemize() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public enumFail failure(String input) {
		// TODO Auto-generated method stub
		return this.enumfail;
	}

	@Override
	public Boolean completed() {
		// TODO Auto-generated method stub
		return this.isComplete;
	}

	@Override
	public AbsAlgPart clone() {
		// TODO Auto-generated method stub
		return new APCldAlarm(this.cld, this.zman, null);
	}

}
