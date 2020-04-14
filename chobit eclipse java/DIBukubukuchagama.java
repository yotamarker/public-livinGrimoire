package chobit;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DIBukubukuchagama extends DISkill {
	// simple timer alarm
	private TimeGate timeGate = new TimeGate();
	private int mode = 0;

	public DIBukubukuchagama(Kokoro kokoro) {
		super(kokoro);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void input(String ear, String skin, String eye) {
		if (ear.contains("reminder")) {
			// "\\d+"
			String minutes = regexChecker("\\d+", ear);
			if (!minutes.isEmpty()) {
				int intMinutes = Integer.parseInt(minutes);
				mode = 1;
				timeGate.setPause(intMinutes);
			}
		}
	}

	@Override
	public void output(Neuron noiron) {
		switch (mode) {
		case 1:
			noiron.algParts.add(verbatimGorithm(new APVerbatim("alarm set")));
			timeGate.close();
			mode = 2;
			break;
		case 2:
			if (!timeGate.isClosed()) {
				noiron.algParts.add(verbatimGorithm(new APVerbatim("oniichan alarm")));
				mode = 0;
			}
			break;
		default:
			break;
		}
	}

	public static String regexChecker(String theRegex, String str2Check) {
		Pattern checkRegex = Pattern.compile(theRegex);
		Matcher regexMatcher = checkRegex.matcher(str2Check);
		while (regexMatcher.find()) {
			if (regexMatcher.group().length() != 0) {
				return regexMatcher.group().trim();
			}
		}
		return "";
	}

	private Algorithm verbatimGorithm(AbsAlgPart itte) {
		// returns a simple algorithm for saying sent parameter
		String representation = "bukubukuchagama";
		ArrayList<AbsAlgPart> algParts1 = new ArrayList<>();
		algParts1.add(itte);
		Algorithm algorithm = new Algorithm("bukubukuchagama", representation, algParts1);
		return algorithm;
	}
}
