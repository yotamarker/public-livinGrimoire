package chobit;

public class DISitter extends DISkill {
	private PlayGround playGround = new PlayGround();
	private DISkillUtils diSkillUtil = new DISkillUtils();
	// isDiapered
	private int algMode = 0;
	private int outAlg = 0;
	private TimeGate timeGate = new TimeGate();
	public DISitter(Kokoro kokoro) {
		super(kokoro);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void input(String ear, String skin, String eye) {
		// triggers
		triggers(ear, skin, eye);
		// algMode (continual algs of triggers)
		algMode(ear, skin, eye);
	}

	@Override
	public void output(Neuron noiron) {
		switch (outAlg) {
		case 1:
			noiron.algParts.add(diSkillUtil.verbatimGorithm(new APVerbatim("ok little one")));
			outAlg = 0;
			break;
		case 2:
			noiron.algParts.add(
					diSkillUtil.verbatimGorithm(new APVerbatim("no you may not", "it is passed your curfew baby")));
			outAlg = 0;
			break;
		case 101:
			noiron.algParts.add(diSkillUtil.verbatimGorithm(new APVerbatim("no you may not fuck me")));
			outAlg = 0;
			break;
		case 102:
			noiron.algParts.add(diSkillUtil.verbatimGorithm(new APVerbatim("go humpies in your diapy")));
			outAlg = 0;
			break;
		case 103:
			noiron.algParts.add(diSkillUtil.verbatimGorithm(new APVerbatim("you have a diaper dont you")));
			outAlg = 0;
			break;
		case 300:
			noiron.algParts.add(diSkillUtil.verbatimGorithm(new APVerbatim("yes you have to")));
			outAlg = 0;
			break;
		case 301:
			noiron.algParts.add(diSkillUtil.verbatimGorithm(new APVerbatim("then be a big boy and stop fussing")));
			outAlg = 0;
			break;
		default:
			break;
		}
	}

	public void triggers(String ear, String skin, String eye) {
		if (ear.contains("go outside")) {
			if (playGround.isDayTime()) {
				outAlg = 1;
			} else {
				outAlg = 2;
			}
		}
		if ((ear.contains("do you want to do it"))) {
			algMode = 101;
			timeGate.close(2);
			outAlg = 101;
		}
		if (ear.contains("i need to pee") || ear.contains("go potty")) {
			outAlg = 103;
		}
		if (ear.contains("do I have to wear diapers")) {
			timeGate.close(2);
			outAlg = 300;
			algMode = 300;
		}
	}

	public void algMode(String ear, String skin, String eye) {
		switch (algMode) {
		case 101:
			if (!timeGate.isClosed()) {
				algMode = 0;
			} else {
				if (ear.contains("but I am a big boy") || ear.contains("please")) {
					timeGate.close(2);
					outAlg = 102;
				}
			}
			break;
		case 300:
			if (!timeGate.isClosed()) {
				algMode = 0;
			} else {
				if (ear.contains("but I am a big boy")) {
					timeGate.close(2);
					outAlg = 301;
				}
			}
			break;
		default:
			break;
		}
	}
}
