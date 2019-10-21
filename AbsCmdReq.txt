package chobit;
public abstract class AbsCmdReq implements Neuronable {
	// handle the input per Dclass (a class whose name starts with D)
	public abstract void input(String ear, String skin, String eye);

	public Boolean auto() {
		// does this skill also engage by time triggers ? is it also a level > 1 type of
		// skill ? if yes
		// override me and return true;
		return false;
	}
}
