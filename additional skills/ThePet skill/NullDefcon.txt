package chobit;

public class NullDefcon extends AbsDefconV2 {

	public NullDefcon(MCodes mCodes, Person friend) {
		super();
	}

	private String checkForDefcons(String ear, String skin, String eye) {
		return "";
	}

	@Override
	public String getAbsoluteDefcon(String ear, String skin, String eye) {
		return "";
	}

	@Override
	public Algorithm getDefcon(String ear, String skin, String eye) {
		return null;
	}
}
