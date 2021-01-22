package chobit;

public class DIEliza extends DISkill {
	private Eliza eliza = new Eliza();
	private String outputStr = "";
	private DISkillUtils diSkillUtil = new DISkillUtils();
	private TimeGate timeGate = new TimeGate();
	public DIEliza(Kokoro kokoro) {
		super(kokoro);
	}

	@Override
	public void input(String ear, String skin, String eye) {
		if (ear.contains("hello baby")) {
			outputStr = eliza.respond("hello");
			timeGate.close(1);
			return;
		}
		if (timeGate.isClosed() && !ear.isEmpty()) {
			outputStr = eliza.respond(ear);
			timeGate.close(1);
		}
	}

	@Override
	public void output(Neuron noiron) {
		if (!outputStr.isEmpty()) {
			noiron.algParts.add(diSkillUtil.verbatimGorithm(new APVerbatim(outputStr)));
			outputStr = "";
		}
	}
}
