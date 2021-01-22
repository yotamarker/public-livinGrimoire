package chobit;

public class UwU {
	/*
	 * l -> w r -> w n -> ny ove -> uv ! -> UwU
	 */
	public String convertToUwU(String str1) {
		String uwu = str1;
		uwu = uwu.replace("l", "w");
		uwu = uwu.replace("r", "w");
		uwu = uwu.replace("no", "nyo");
		uwu = uwu.replace("ne", "nye");
		uwu = uwu.replace("ni", "nyi");
		uwu = uwu.replace("nu", "nyu");
		uwu = uwu.replace("na", "nya");
		uwu = uwu.replace("ove", "uv");
		uwu = uwu.replace("!", "^w^");
		uwu = uwu.replace("?", "OwO");
		return uwu;
	}
}
