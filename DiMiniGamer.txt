package chobit;

public class DiMiniGamer extends DISkill {
	private int gameMode = 0;
	private DISkillUtils diSkillUtils = new DISkillUtils();
	private String outStr = "";
	private MiniGame1 miniGame1 = new MiniGame1();
	public DiMiniGamer(Kokoro kokoro) {
		super(kokoro);
	}

	@Override
	public void input(String ear, String skin, String eye) {
		if (ear.contains("i want to play a little game")) {
			gameMode = 1;
			outStr = "yes your majesty";
			miniGame1.init();
			return;
		}
		switch (gameMode) {
		case 1:
			outStr = miniGame1.play(ear);
			if (outStr.isEmpty() || outStr.contains("you lose") || outStr.contains("you win")) {
				gameMode = 0;
			}
			break;
		default:
			break;
		}
	}

	@Override
	public void output(Neuron noiron) {
		if (!outStr.isEmpty()) {
			String temp = outStr;
			outStr = "";
			noiron.algParts.add(diSkillUtils.verbatimGorithm("mini game", new APVerbatim(temp)));
		}
	}
}
