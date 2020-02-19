package com.yotamarker.lgkotlin1;

import java.util.ArrayList;

public class DIWeather extends DISkill {
	// tells the weather
	private Boolean where = false;
	public DIWeather(Kokoro kokoro) {
		super(kokoro);
	}

	@Override
	public void input(String ear, String skin, String eye) {
		if (ear.contains("weather")) {
			String test = ear; // v2 use gps
			String city = test.substring(test.lastIndexOf(" ") + 1);// v2 use gps
			Weather.updateWeather(city); // weather ready to go.
		}
		else if (ear.contains("where am i")) {where = true;}
	}

	@Override
	public void output(Neuron noiron) {
		if (Weather.getHasBeenUpdated()) {
			noiron.algParts.add(verbatimGorithm());
		}
		else if(where){where=false;noiron.algParts.add(placeStater());}
	}

	private Algorithm verbatimGorithm() {
		// returns a simple algorithm for saying sent parameter
		AbsAlgPart itte = new APVerbatim(Weather.getWeatherList());
		String representation = "weather";
		ArrayList<AbsAlgPart> algParts1 = new ArrayList<>();
		algParts1.add(itte);
		Algorithm algorithm = new Algorithm("weather", representation, algParts1);
		return algorithm;
	}
	private Algorithm placeStater() {
		// returns a simple algorithm for saying sent parameter
		AbsAlgPart itte = new APVerbatim("we are at",Tokoro.placeName);
		String representation = "atplace";
		ArrayList<AbsAlgPart> algParts1 = new ArrayList<>();
		algParts1.add(itte);
		Algorithm algorithm = new Algorithm("atplace", representation, algParts1);
		return algorithm;
	}
}
