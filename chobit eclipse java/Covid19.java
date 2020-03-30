package chobit;

import java.util.ArrayList;

public class Covid19 {

	private static String infected = "unknown";
	private static Boolean hasBeenUpdated = false;

	public static void updateStatistics() {
		// DownloadTask task = new DownloadTask();
		// String s1 = String.format(
		// "http://openweathermap.org/data/2.5/weather?lat=%.04f&lon=%.04f&appid=b6907d289e10d714a6e88b30761fae22",
		// xyTitan.getX(), xyTitan.getY());
		// task.execute(s1);
		infected = "50"; // delete me
		hasBeenUpdated = true; // put me in onpost task
	}

	public static ArrayList<String> getCovidStats() {
		// returns vital weather info
		ArrayList<String> result = new ArrayList<String>();
		result.add("yes your majesty");
		result.add(infected);
		result.add("teehee");
		hasBeenUpdated = false;
		return result;
	}

	public static Boolean getHasBeenUpdated() {
		return hasBeenUpdated;
	}
	// public static class DownloadTask extends AsyncTask<String,Void,String> {
	//
	// @Override
	// protected String doInBackground(String... urls) {
	// String result = "";
	// URL url;
	// HttpURLConnection urlConnection = null;
	//
	// try {
	//
	// url = new URL(urls[0]);
	// urlConnection = (HttpURLConnection) url.openConnection();
	// InputStream in = urlConnection.getInputStream();
	// InputStreamReader reader = new InputStreamReader(in);
	// int data = reader.read();
	//
	// while (data != -1) {
	// char current = (char) data;
	// result += current;
	// data = reader.read();
	// }
	//
	// return result;
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// return null;
	// }
	// }

	// @Override
	// protected void onPostExecute(String s) {
	// super.onPostExecute(s);
	//
	// try {
	// JSONObject jsonObject = new JSONObject(s);
	//
	// String weatherInfo = jsonObject.getString("weather");
	//
	// //Log.i("Weather content", weatherInfo);
	//
	// JSONArray arr = new JSONArray(weatherInfo);
	//
	// for (int i=0; i < arr.length(); i++) {
	// JSONObject jsonPart = arr.getJSONObject(i);
	// weatherWarning = jsonPart.getString("main");
	// //Log.i("main",weatherWarning);
	// currentWeather = jsonPart.getString("description");
	// //Log.i("description",jsonPart.getString("description"));
	//
	// }
	// String temp = jsonObject.getString("main");
	// JSONObject jsonObject2 = new JSONObject(temp);
	// //degrees = jsonObject2.getString("temp");
	// degrees = jsonObject2.getDouble("temp");
	// hasBeenUpdated = true;
	// //Log.i("maalot",temp);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// }
	// }
}
