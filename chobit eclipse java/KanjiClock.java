package chobit;

public class KanjiClock {
	private NumToKanji kanjiConverter = new NumToKanji();
	private PlayGround playGround = new PlayGround();

	public String timeInKanji() {
		String result = "";
		result = kanjiConverter.getAsKanji(playGround.getHoursAsInt())
				+ ":" + kanjiConverter.getAsKanji(playGround.getMinutesAsInt());
		return result;
	}
}
