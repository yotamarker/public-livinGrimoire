package chobit;

public class HaMsg {
	public String hideMsg(String msg, String Str1) {
		String jj = "";
		char prev = '@';
		msg = msg.replace(".", "dot");
		msg = msg.toUpperCase();
		char[] stringToCharArray = msg.toCharArray();
		String msgReplica = msg.toLowerCase();
		char[] stringToCharArray2 = msgReplica.toCharArray();
		Str1 = Str1.toLowerCase();
		StringBuilder strBuilder = new StringBuilder(Str1);
		int marker = 0;
		// strBuilder.setCharAt(4, 'x');
		for (int i = 0; i < stringToCharArray.length; i++) {
			char ch = stringToCharArray2[i];
			if (ch == prev && Str1.length() > 1) {
				Str1 = Str1.substring(1);
				marker++;
			}
			prev = ch;
			int n1 = Str1.indexOf(ch);
			if (n1 > -1) {
				marker += n1;
				strBuilder.setCharAt(marker, stringToCharArray[i]);
				Str1 = Str1.substring(n1);
				jj = strBuilder.toString();
			}
			else {
				return "I need a longer string";
			}
		}
		return strBuilder.toString();
	}
}
