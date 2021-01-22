package chobit;

public class Currency {
	// base always = 1
	private static Double shekel = 3.4334;
	private static Double euro = 1.0;
	// private static Double dollar = 36.0;
	private static Boolean hasBeenUpdated = false;

	public static void updateCurrency() {
		// get weather with JSON
		hasBeenUpdated = true;
	}

	public static Double exchangeRate(String coin) {
		// returns vital weather info
		switch (coin) {
		case "shekel":
			return shekel;
		case "dollar":
			return 1.0;// base
		case "euro":
			return euro;
		default:
			break;
		}
		return 0.0;
	}

	public static Boolean getHasBeenUpdated() {
		Boolean result = hasBeenUpdated;
		hasBeenUpdated = false;
		return result;
	}
}
