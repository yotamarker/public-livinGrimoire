package chobit;
public abstract class AbsCmdReq implements Neuronable {
	// handle the input per Dclass (a class whose name starts with D)
	public abstract void input(String ear, String skin, String eye);
}
