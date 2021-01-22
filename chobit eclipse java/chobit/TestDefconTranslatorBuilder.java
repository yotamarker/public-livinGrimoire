package chobit;

import java.util.ArrayList;

public class TestDefconTranslatorBuilder {
	public static DefconTranslator testBuild() {
		ArrayList<String> defcons = new ArrayList<String>();
		defcons.add("shit");
		return new DefconTranslator(defcons);
	}
}
