package chobit;

/* an easy way to add an action to any exact input
 * */
public class APSpell extends AbsAlgPart {
    PlayGround playGround = new PlayGround();
    String[] morningGreet = { "good morning", "hi", "hadouken", "]" };
    private String param = "";
    private Boolean isCompleted = false;
    public APSpell(String param) {
        super();
        this.param = param;
    }

    private String greet() {
        String axnStr = "";
        int random = (int) (Math.random() * 20);
        if ((random < morningGreet.length - 1) && (random != 0)) {
            axnStr = morningGreet[0] + " " + morningGreet[random];
            String temp = morningGreet[0];
            morningGreet[0] = morningGreet[random];
            morningGreet[random] = temp;
        } else {
            axnStr = morningGreet[0];
        }
        return axnStr;
    }

    @Override
	public String action(String ear, String skin, String eye) {
        String axnStr = "";
        switch (this.param) {
            case "what is the time":
                axnStr = playGround.getCurrentTimeStamp();
                break;
            case "what is the date":
                axnStr = playGround.getSpecificTime(enumTimes.DATE);
                break;
            case "what is the year":
                axnStr = playGround.getSpecificTime(enumTimes.YEAR);
                break;
            case "current seconds":
                axnStr = playGround.getSpecificTime(enumTimes.SECONDS);
                break;
            case "current minutes":
                axnStr = playGround.getSpecificTime(enumTimes.MINUTES);
                break;
            case "current hour":
                axnStr = playGround.getSpecificTime(enumTimes.HOUR);
                break;
            case "which day is it":
                axnStr = playGround.getDayOfDWeek();
                break;
            case "greet":
                axnStr = greet();
                break;
            default:
                break;

        }
        isCompleted = true;
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
        return isCompleted;
    }

    @Override
    public AbsAlgPart clone() {
        // TODO Auto-generated method stub

        return new APSpell(this.param);
    }

	@Override
	public Boolean itemize() {
		// TODO Auto-generated method stub
		return false;
	}
}
