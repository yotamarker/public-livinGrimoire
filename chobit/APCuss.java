package chobit;

// a simple cuss action
public class APCuss extends AbsAlgPart {
    private int doer = 1;
	private String cuss, evil; // evil = the trigger of the anger
	// as long as evil exists the cussing goes on.
    private Boolean isComplete = false;

    public APCuss(String cuss, String evil) {
        super();
        this.cuss = cuss;
        this.evil = evil;
    }

    @Override
	public String action(String ear, String skin, String eye) {
        String axnStr = "";
        if (doer < 10) {
            axnStr = evil;
            doer++;
        } else {
            doer = 0;
        }
		if (doer == 0 && evil != ear) {
            isComplete = true;
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
        return this.isComplete;
    }

    @Override
    public AbsAlgPart clone() {
        // TODO Auto-generated method stub
        return new APCuss(cuss, evil);
    }

	@Override
	public Boolean itemize() {
		// TODO Auto-generated method stub
		return false;
	}

}
