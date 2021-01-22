package chobit;

public class DiMemoryGame extends DISkill {
	private int gameMode = 0;
	private DISkillUtils diSkillUtils = new DISkillUtils();
	private String outStr = "";
	private MemoryGame memoryGame = new MemoryGame();

	public DiMemoryGame(Kokoro kokoro) {
		super(kokoro);
	}

	@Override
	public void input(String ear, String skin, String eye) {
		if (ear.contains("play simon")) {
			gameMode = 1;
			outStr = memoryGame.reset();
			return;
		}
		switch (gameMode) {
		case 1:
			outStr = memoryGame.play(ear);
			if (outStr.contains("you have reached level")) {
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
