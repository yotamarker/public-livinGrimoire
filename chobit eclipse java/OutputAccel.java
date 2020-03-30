package chobit;

public class OutputAccel implements Runnable {
	// AbsCmdReq dClass, String ear, String skin, String eye
	private AbsCmdReq dClass;
	public Neuron noiron;

	public OutputAccel(AbsCmdReq dClass, Neuron noiron) {
		super();
		this.dClass = dClass;
		this.noiron = noiron;
	}

	@Override
	public void run() {
		dClass.output(noiron);
	}

}
