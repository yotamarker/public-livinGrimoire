package chobit;

import java.util.ArrayList;

public class DICurrency extends DISkill {
	private int mode = 0;
	private double sum = 0.0;
	private RegexUtil regexUtil = new RegexUtil();
	private String[] cases = { "shekel to dollar", "shekel to euro", "dollar to euro", "dollar to shekel",
			"euro to dollar", "euro to shekel" };
	public DICurrency(Kokoro kokoro) {
		super(kokoro);
	}

	@Override
	public void input(String ear, String skin, String eye) {
		for (int i = 0; i < cases.length; i++) {
			if (ear.contains(cases[i])) {
				conversionCase(cases[i]);
				Currency.updateCurrency();
				sum = Double.parseDouble(regexUtil.regexChecker("[-+]?[0-9]*\\.?[0-9]*", ear));
			}
		}
	}

	private Boolean conversionCase(String trigger) {
		Boolean result = false;
		switch (trigger) {
		case "shekel to dollar":
			result = true;
			this.mode = 1;
			break;
		case "shekel to euro":
			result = true;
			this.mode = 2;
			break;
		case "dollar to euro":
			result = true;
			this.mode = 3;
			break;
		case "dollar to shekel":
			result = true;
			this.mode = 4;
			break;
		case "euro to dollar":
			result = true;
			this.mode = 5;
			break;
		case "euro to shekel":
			result = true;
			this.mode = 6;
			break;
		default:
			break;
		}
		return result;
	}
	@Override
	public void output(Neuron noiron) {
		switch (mode) {
		case 1:
			if (Currency.getHasBeenUpdated()) {
			mode = 0;
				sum = sum / Currency.exchangeRate("shekel");
				sum = sum * Currency.exchangeRate("dollar");
				String appendCurrency = "dollars";
				if (sum == 1) {
					appendCurrency = "dollar";
				}
				noiron.algParts.add(verbatimGorithm(appendCurrency));
				sum = 0.0;
			}
			break;
		case 2:
			if (Currency.getHasBeenUpdated()) {
				mode = 0;
				sum = sum / Currency.exchangeRate("shekel");
				sum = sum * Currency.exchangeRate("euro");
				String appendCurrency = "euros";
				if (sum == 1) {
					appendCurrency = "euro";
				}
				noiron.algParts.add(verbatimGorithm(appendCurrency));
				sum = 0.0;
			}
			break;
		case 3:
			if (Currency.getHasBeenUpdated()) {
				mode = 0;
				sum = sum / Currency.exchangeRate("dollar");
				sum = sum * Currency.exchangeRate("euro");
				String appendCurrency = "euros";
				if (sum == 1) {
					appendCurrency = "euro";
				}
				noiron.algParts.add(verbatimGorithm(appendCurrency));
				sum = 0.0;
			}
			break;
		case 4:
			if (Currency.getHasBeenUpdated()) {
				mode = 0;
				sum = sum / Currency.exchangeRate("dollar");
				sum = sum * Currency.exchangeRate("shekel");
				String appendCurrency = "shekels";
				if (sum == 1) {
					appendCurrency = "shekel";
				}
				noiron.algParts.add(verbatimGorithm(appendCurrency));
				sum = 0.0;
			}
			break;
		case 5:
			if (Currency.getHasBeenUpdated()) {
				mode = 0;
				sum = sum / Currency.exchangeRate("euro");
				sum = sum * Currency.exchangeRate("dollar");
				String appendCurrency = "dollars";
				if (sum == 1) {
					appendCurrency = "dollar";
				}
				noiron.algParts.add(verbatimGorithm(appendCurrency));
				sum = 0.0;
			}
			break;
		case 6:
			if (Currency.getHasBeenUpdated()) {
				mode = 0;
				sum = sum / Currency.exchangeRate("euro");
				sum = sum * Currency.exchangeRate("shekel");
				String appendCurrency = "shekels";
				if (sum == 1) {
					appendCurrency = "shekel";
				}
				noiron.algParts.add(verbatimGorithm(appendCurrency));
				sum = 0.0;
			}
			break;
		default:
			break;
		}
	}

	private Algorithm verbatimGorithm(String append) {
		// returns a simple algorithm for saying sent parameter
		String s1 = String.format("%.02f", this.sum);
		AbsAlgPart itte = new APVerbatim(s1 + " " + append);
		String representation = "currency";
		ArrayList<AbsAlgPart> algParts1 = new ArrayList<>();
		algParts1.add(itte);
		Algorithm algorithm = new Algorithm("currency", representation, algParts1);
		return algorithm;
	}
}
