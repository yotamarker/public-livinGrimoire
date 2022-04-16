package chobit;

public class DiTglrMommy extends DISkill {
	private RUSureGate ruSureGate;
	private String outString = "";
	private DISkillUtils diSkillUtils = new DISkillUtils();
	private String conjuration = "mommy";

	public DiTglrMommy(Kokoro kokoro) {
		super(kokoro);
		ruSureGate = new RUSureGate("are you sure", "can you be my mommy", "really", "this is what you want",
				"i will be your mommy");
		ruSureGate.setAffirmations("i am sure", "ok", "no problem");
	}
	@Override
	public void input(String ear, String skin, String eye) {
		outString = ruSureGate.loadBullet(ear);
		if (!outString.isEmpty()) {
			boolean b1 = ruSureGate.getUnlocked();
			if (b1) {
				kokoro.toHeart.put(conjuration, conjuration + " on");
				return;
			}
		}
		if (ear.contains("you are not my mommy")) {
			kokoro.toHeart.put(conjuration, conjuration + " off");
			outString = "you dislike it";
			return;
		}
	}
	@Override
	public void output(Neuron noiron) {
		if (!outString.isEmpty()) {
			noiron.algParts.add(diSkillUtils.verbatimGorithm(new APVerbatim(outString)));
			outString = "";
		}
	}
}
