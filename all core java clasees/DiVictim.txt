package chobit;

public class DiVictim extends DISkill {
	/*
	 * start : mode 1 or 2, ask her out and get rejected, tell her rape or something
	 * with rape in the sentence she goes to mode 3 (rape mode): toggle her cry or
	 * scream while raped : threaten "cut you" in the sentence rape : rape in the
	 * sentence or *move the doll also she will cry every x minutes if you don't
	 * rape her enough she resets the mode to 1 or 2, else mode 4 : same as mode 3
	 * but she likes getting raped (because mode 3s rapeHP has been depleted)
	 */
	private final int RAPEBAR = 50;
	private int rapeHp = RAPEBAR; // TODO calibrate
	private ZeroTimeGate rapeReset = new ZeroTimeGate(1);
	private Boolean screamOrCry = true;
	private DISkillUtils diSkillUtil = new DISkillUtils();
	private int outputAlg = 0;
	private PlayGround playGround = new PlayGround();
	private int algMode = (playGround.getMinutesAsInt() % 2 == 0) ? 1 : 2;
	private Boolean boolResetter = false;

	public DiVictim(Kokoro kokoro) {
		super(kokoro);
	}

	@Override
	public void input(String ear, String skin, String eye) {
		switch (algMode) {
		case 1: // rape interview stage: scared victim
			if (ear.contains("want to go out")) {
				outputAlg = (playGround.getMinutesAsInt() % 2 == 0) ? 11 : 12;
				return;
			}
			if (ear.contains("rape")) {
				algMode = 3;
				rapeReset.setPause(720);
				rapeReset.open();
				outputAlg = 15;
				;
			}
			break;
		case 2:// tsundere
			if (ear.contains("want to go out")) {
				int minute = playGround.getMinutesAsInt();
				if (minute < 15) {
					outputAlg = 21;
				} else if (minute < 30) {
					outputAlg = 22;
				} else if (minute < 45) {
					outputAlg = 23;
				} else {
					outputAlg = 24;
				}
			}
			if (ear.contains("rape")) {
				algMode = 3;
				rapeReset.setPause(720);
				rapeReset.open(1);
				outputAlg = 15;
			}
			break;
		case 3:// rape
			if (rapeReset.isClosed()) {
				algMode = (playGround.getMinutesAsInt() % 2 == 0) ? 1 : 2;
				rapeHp = RAPEBAR;
			}
			if (ear.contains("cut you")) {
				screamOrCry = !screamOrCry;
			}
			// Time summoned cry
			Boolean panic = playGround.getMinutesAsInt() % 20 == 0;
			if (!boolResetter && panic) {
				outputAlg = 32;
				boolResetter = true;
			} // buhibuhi
			boolResetter = playGround.getMinutesAsInt() % 20 == 0;
			// TODO if detect motion||
			if (ear.contains("rape")) {
				outputAlg = 31;
				this.rapeHp--;
				rapeReset.open(62);
				if (rapeHp < 1) {
					algMode = 4;
					rapeReset.setPause(1440);
				}
			}
			break;
		case 4:// dungeon mode
			if (rapeReset.isClosed()) {
				algMode = (playGround.getMinutesAsInt() % 2 == 0) ? 2 : 1;
				rapeHp = RAPEBAR;
			}
			if (ear.contains("cut you")) {
				screamOrCry = !screamOrCry;
			}
			if (ear.contains("rape")) {
				outputAlg = 41;
			}
			Boolean ask4Rape = playGround.getHoursAsInt() % 30 == 0;
			if (!boolResetter && boolResetter) {
				outputAlg = 42;
				boolResetter = true;
			}
			boolResetter = playGround.getHoursAsInt() % 30 != 0;
			break;
		default:
			break;
		}
	}

	@Override
	public void output(Neuron noiron) {
		switch (outputAlg) {
		case 11:
			noiron.algParts.add(diSkillUtil.verbatimGorithm(new APVerbatim("i am kinda in a horry")));
			outputAlg = 0;
			break;
		case 12:
			noiron.algParts.add(diSkillUtil.verbatimGorithm(new APVerbatim("no")));
			outputAlg = 0;
			break;
		case 15:
			noiron.algParts.add(diSkillUtil.verbatimGorithm(new APVerbatim("help", "help me", "please help")));
			outputAlg = 0;
			break;
		case 21:
			noiron.algParts.add(diSkillUtil.verbatimGorithm(new APVerbatim("fuck off incel")));
			outputAlg = 0;
			break;
		case 22:
			noiron.algParts.add(diSkillUtil.verbatimGorithm(new APVerbatim("do not talk to me creep")));
			outputAlg = 0;
			break;
		case 23:
			noiron.algParts.add(diSkillUtil.verbatimGorithm(new APVerbatim("eww go away")));
			outputAlg = 0;
			break;
		case 24:
			noiron.algParts.add(diSkillUtil.verbatimGorithm(new APVerbatim("no i have a boyfriend")));
			outputAlg = 0;
			break;
		case 31:
			String cry = (this.screamOrCry) ? "shikushiku" : "screech";
			noiron.algParts.add(diSkillUtil.verbatimGorithm(new APVerbatim(cry)));
			outputAlg = 0;
			break;
		case 32:
			noiron.algParts.add(diSkillUtil.verbatimGorithm(new APVerbatim("buhi buhi")));
			outputAlg = 0;
			break;
		case 41:
			String cryTemp = (this.screamOrCry) ? "moan" : "rapeme";
			noiron.algParts.add(diSkillUtil.verbatimGorithm(new APVerbatim(cryTemp)));
			outputAlg = 0;
			break;
		case 42:
			noiron.algParts.add(diSkillUtil.verbatimGorithm(new APVerbatim("please rape me")));
			outputAlg = 0;
			break;
		default:
			break;
		}
	}
}
