package com.yotamarker.lgkotlin1;

/* it speaks something x times
 * a most basic skill.
 * also fun to make the chobit say what you want
 * */
public class APSay extends AbsAlgPart {
    protected String param;
    private int at;

    public APSay(int at, String param) {
        super();
        if (at > 10) {
            at = 10;
        }
        this.at = at;
        this.param = param;
    }

    @Override
	public String action(String ear, String skin, String eye) {
        // TODO Auto-generated method stub
        String axnStr = "";
        if (this.at > 0) {
			if (!ear.equalsIgnoreCase(param)) {
                axnStr = param;
                at--;
            }
        }
        return axnStr;
    }

    @Override
    public enumFail failure(String input) {
        // TODO Auto-generated method stub
        return enumFail.ok;
    }

    @Override
    public Boolean completed() {
        // TODO Auto-generated method stub
        return at < 1;
    }

    @Override
    public AbsAlgPart clone() {
        // TODO Auto-generated method stub
        return new APSay(this.at, this.param);
    }

	@Override
	public Boolean itemize() {
		// TODO add logic
		// at home
		return true;
	}
}
