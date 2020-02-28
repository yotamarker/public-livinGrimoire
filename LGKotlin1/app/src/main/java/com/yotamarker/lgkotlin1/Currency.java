package com.yotamarker.lgkotlin1;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Currency {
    // uses API url to get a jason with currencys and update exchange rates, using euro as the base currency
    // base always = 1
    public static Double shekel = 10.0;
    //public static Double euro = 50.0;//base
    public static Double dollar = 36.0;
    private static Boolean hasBeenUpdated = false;

    public static void updateCurrency() {
        // get weather with JSON
        DownloadTask task = new DownloadTask();
        String s1 = "https://api.exchangeratesapi.io/latest";
        task.execute(s1);
        hasBeenUpdated = true;
    }

    public static Double exchangeRate(String coin) {
        // returns vital weather info
        switch (coin) {
            case "shekel":
                return shekel;
            case "dollar":
                return dollar;
            case "euro":
                return 1.0;//base currency
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
                JSONObject rates =jsonObject.getJSONObject("rates");
                Double ils = new Double(rates.getString("ILS").replace(",",""));
                Currency.shekel = ils;
                Double usd = new Double(rates.getString("USD").replace(",",""));
                Currency.dollar = usd;
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
