package chobit;
public abstract class AbsAlgPart {
	// one part of an algorithm, it is a basic simple action or sub goal
	public abstract String action(String ear, String skin, String eye); // return action string
	public abstract Boolean itemize(); // equip with item ?
    public abstract enumFail failure(String input); // failure type
    public abstract Boolean completed(); // has finished ?
    public abstract AbsAlgPart clone();
}