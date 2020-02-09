package com.yotamarker.lgkotlin1;

import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Weather {
	private static Double degrees = 0.0;
	private static String currentWeather = "unknown";
	private static String weatherWarning = "sunny weather";
	private static Boolean hasBeenUpdated = false;

	public static void updateWeather(String city) {
		DownloadTask task = new DownloadTask();
		Coordinates xyTitan = cityToCoordinates(city);
		String s1 = String.format(
				"http://openweathermap.org/data/2.5/weather?lat=%.04f&lon=%.04f&appid=b6907d289e10d714a6e88b30761fae22",
				xyTitan.getX(), xyTitan.getY());
		task.execute(s1);

	}
	private static  Coordinates cityToCoordinates(String city){
		switch (city) {
			case "pripyat":
				return  new Coordinates(51.4061, 30.0571);
			default:
//default statement sequence
		}
		return  new Coordinates(32.0809, 34.7806); // replace with GPS
	}
	public static ArrayList<String> getWeatherList() {
		// returns vital weather info
		ArrayList<String> result = new ArrayList<String>();
		result.add("yes your majesty");
		result.add(decorateDegrees(degrees));
		//result.add(decorateCurrentWeather(currentWeather));
		result.add(decorateWarning(weatherWarning));
		hasBeenUpdated = false;
		return result;
	}
	private static String decorateDegrees(Double degrees){
			if(degrees >3 && degrees < 14){return  "cold brrr";}
			else if(degrees > 25 && degrees < 30){return "it is hot";}
		return  degrees +"degrees";
	}
	private static String decorateWarning(String warn){
		if(warn.toLowerCase().contains("storm")){return "stormy weather";}
		if(warn.toLowerCase().contains("rain")){return "it is going to rain";}
		if(warn.toLowerCase().contains("haze")){return "haze";}
		if(warn.toLowerCase().contains("fog")){return "foggy weather";}
		if(warn.toLowerCase().contains("snow")){return "snowy weather";}
		if(warn.toLowerCase().contains("cloud")){return "cloudy weather";}
		if(warn.toLowerCase().contains("rain")){return "rainy weather";}
		if(warn.toLowerCase().contains("downpoor")){return "take an umbrella";}
		if(warn.toLowerCase().contains("clear")||warn.contains("sun")){return "sunny weather";}
		return  warn;
	}
	private static String decorateCurrentWeather(String cur){
		if(cur.contains("snow")){return "snowy weather";}
		if(cur.contains("cloud")){return "cloudy weather";}
		if(cur.contains("rain")){return "rainy weather";}
		if(cur.contains("downpoor")){return "take an umbrella";}
		if(cur.contains("clear")||cur.contains("sun")){return "clear weather";}
		return  cur;
	}
	public static Boolean getHasBeenUpdated() {
		return hasBeenUpdated;
	}
	public static class DownloadTask extends AsyncTask<String,Void,String> {

		@Override
		protected String doInBackground(String... urls) {
			String result = "";
			URL url;
			HttpURLConnection urlConnection = null;

			try {

				url = new URL(urls[0]);
				urlConnection = (HttpURLConnection) url.openConnection();
				InputStream in = urlConnection.getInputStream();
				InputStreamReader reader = new InputStreamReader(in);
				int data = reader.read();

				while (data != -1) {
					char current = (char) data;
					result += current;
					data = reader.read();
				}

				return result;

			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}

		@Override
		protected void onPostExecute(String s) {
			super.onPostExecute(s);

			try {
				JSONObject jsonObject = new JSONObject(s);

				String weatherInfo = jsonObject.getString("weather");

				//Log.i("Weather content", weatherInfo);

				JSONArray arr = new JSONArray(weatherInfo);

				for (int i=0; i < arr.length(); i++) {
					JSONObject jsonPart = arr.getJSONObject(i);
					weatherWarning = jsonPart.getString("main");
					//Log.i("main",weatherWarning);
					currentWeather = jsonPart.getString("description");
					//Log.i("description",jsonPart.getString("description"));

				}
				String temp = jsonObject.getString("main");
				JSONObject jsonObject2 = new JSONObject(temp);
				//degrees = jsonObject2.getString("temp");
				degrees = jsonObject2.getDouble("temp");
				hasBeenUpdated = true;
				//Log.i("maalot",temp);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
}
