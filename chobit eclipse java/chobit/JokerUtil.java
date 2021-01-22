package chobit;

public class JokerUtil {
	public void prepareParamList(String multiLine) {
		String[] arrOfStr = multiLine.split("\r\n");

		for (String a : arrOfStr) {
			String filtered = a.replace("[", "");
			filtered = filtered.replace("]", " #");
			filtered = filtered.trim();
			System.out.println("temp.add(\"" + filtered + "\");");
		}
	}

	public void prepareSentencesList(String multiLine) {
		String[] arrOfStr = multiLine.split("\r\n");

		for (String a : arrOfStr) {
			String filtered = a.replace("[", "");
			filtered = filtered.replace("]", " #");
			filtered = filtered.trim();
			System.out.println("sentences.add(\"" + filtered + "\");");
		}
	}

	public void loadList(String multiLine, String title) {
		String s1 = String.format("//%s list", title);
		System.out.println(s1);
		prepareParamList(multiLine);
		loadList(title);
	}
	public void loadList(String title) {
		String s1 = String.format("wordToList.put(\"%s\", new ArrayList<String>(temp));", title);
		System.out.println(s1);
		System.out.println("temp.clear();");
	}
}
