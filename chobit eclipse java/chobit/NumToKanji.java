package chobit;

public class NumToKanji {
	public String getAsKanji(int N) {
		if (N == 0) {
			return "0";
		} else {
			return toKanji(N);
		}
	}

	private String toKanji(int N) {
		String kanji;
		kanji = "enter a number";
		String characters = N + "";
		switch (characters.length()) {
		case 1:
			switch (N) {
			case 1:
				kanji = "ichi";
				break;
			case 2:
				kanji = "ni";
				break;
			case 3:
				kanji = "san";
				break;
			case 4:
				kanji = "yon";
				break;
			case 5:
				kanji = "go";
				break;
			case 6:
				kanji = "roku";
				break;
			case 7:
				kanji = "shichi";
				break;
			case 8:
				kanji = "hachi";
				break;
			case 9:
				kanji = "kyuu";
				break;
			default:
				kanji = "";
				break;

			}

			break;
		case 2:
			switch (N / 10) {
			case 1:
				kanji = "ju" + " " + toKanji(N % 10);
				break;
			case 2:
				kanji = "ni-ju" + " " + toKanji(N % 10);
				break;
			case 3:
				kanji = "san-ju" + " " + toKanji(N % 10);
				break;
			case 4:
				kanji = "yon-ju" + " " + toKanji(N % 10);
				break;
			case 5:
				kanji = "go-ju" + " " + toKanji(N % 10);
				break;
			case 6:
				kanji = "roku-ju" + " " + toKanji(N % 10);
				break;
			case 7:
				kanji = "nana-ju" + " " + toKanji(N % 10);
				break;
			case 8:
				kanji = "hachi-ju" + " " + toKanji(N % 10);
				break;
			case 9:
				kanji = "kyu-ju" + " " + toKanji(N % 10);
				break;
			default:
				kanji = "";
				break;
			}

			break;
		}
		return kanji;

	}
}
