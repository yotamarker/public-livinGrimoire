package chobit;

import java.util.ArrayList;

public class Weather {
	private static String degrees = "0 degrees";
	private static String currentWeather = "unknown";
	private static String weatherWarning = "sunny weather";
	private static Boolean hasBeenUpdated = false;

	public static void updateWeather(String city) {
		// get weather with JSON
		hasBeenUpdated = true;
	}
	public static ArrayList<String> getWeatherList() {
		// returns vital weather info
		ArrayList<String> result = new ArrayList<String>();
		result.add(degrees);
		result.add(currentWeather);
		result.add(weatherWarning);
		hasBeenUpdated = false;
		return result;
	}

	public static Boolean getHasBeenUpdated() {
		return hasBeenUpdated;
	}

}
