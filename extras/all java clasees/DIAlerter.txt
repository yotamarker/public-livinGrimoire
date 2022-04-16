package chobit;

public class DIAlerter extends DISkill {
	private Alerter alerter = new Alerter();
	private DISkillUtils diSkillUtils = new DISkillUtils();
	protected Algorithm outputAlg = null;
	private Grammer grammer = new Grammer();
	public DIAlerter(Kokoro kokoro) {
		super(kokoro);
	}

	@Override
	public void input(String ear, String skin, String eye) {
		alerter.loadBullet(ear);
		String result = alerter.loadBullet();
		if (result.isEmpty()) {
			return;
		}
		result = grammer.toggleWords(result, "my", "your");
		outputAlg = diSkillUtils.verbatimGorithm(new APVerbatim(result));
	}

	@Override
	public void output(Neuron noiron) {
		if (!isNull(this.outputAlg)) {
			noiron.algParts.add(this.outputAlg);
			this.outputAlg = null;
		}
	}

	@Override
	public Boolean auto() {
		return true;
	}

	private boolean isNull(Object obj) {
		return obj == null;
	}
}
