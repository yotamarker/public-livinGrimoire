package chobit;

public class ForumSpoiler {
	public static void ForumSpoiler(String msgFiller, String msg, int spoilers) {
		String s1 = String.format("[SPOILER=\"%s\"]", msgFiller);
		for (int i = 0; i < spoilers; i++) {
			System.out.print(s1);
		}
		System.out.print(msg);
		for (int i = 0; i < spoilers; i++) {
			System.out.print("[/SPOILER]");
		}
	}
}

