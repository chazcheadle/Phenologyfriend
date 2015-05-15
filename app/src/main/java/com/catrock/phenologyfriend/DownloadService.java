package com.catrock.phenologyfriend;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import android.os.Bundle;
import android.os.ResultReceiver;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadService extends IntentService {

    public static final int STATUS_RUNNING = 0;
    public static final int STATUS_FINISHED = 1;
    public static final int STATUS_ERROR = 2;

    private static final String TAG = "DownloadService";

    public DownloadService() {
        super(DownloadService.class.getName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Log.d(TAG, "Service Started!");

        final ResultReceiver receiver = intent.getParcelableExtra("receiver");
        String url = intent.getStringExtra("url");
        String wuapi_key = intent.getStringExtra("wuapi_key");

        Bundle bundle = new Bundle();

        if (!TextUtils.isEmpty(url)) {
            /* Update UI: Download Service is Running */
            receiver.send(STATUS_RUNNING, Bundle.EMPTY);

            try {
                String[] results = downloadData(url, wuapi_key);

                /* Sending result back to activity */
                if (null != results && results.length > 0) {
                    bundle.putStringArray("result", results);
                    receiver.send(STATUS_FINISHED, bundle);
                }
            } catch (Exception e) {

                /* Sending error message back to activity */
                bundle.putString(Intent.EXTRA_TEXT, e.toString());
                Log.d(TAG, e.toString());
                receiver.send(STATUS_ERROR, bundle);
            }
        }
        Log.d(TAG, "Service Stopping!");
        this.stopSelf();
    }

    private String[] downloadData(String requestUrl, String wuapi_key) throws IOException, DownloadException {
        InputStream inputStream = null;
        HttpURLConnection urlConnection = null;

        // Replace API Key
        requestUrl = requestUrl.replace("APIKEY", wuapi_key);
        Log.d(TAG, requestUrl);

        /* forming th java.net.URL object */
        URL url = new URL(requestUrl);
        urlConnection = (HttpURLConnection) url.openConnection();

        /* optional request header */
        urlConnection.setRequestProperty("Content-Type", "application/json");

        /* optional request header */
        urlConnection.setRequestProperty("Accept", "application/json");

        /* for Get request */
        urlConnection.setRequestMethod("GET");
        int statusCode = urlConnection.getResponseCode();

        /* 200 represents HTTP OK */
        if (statusCode == 200) {
            inputStream = new BufferedInputStream(urlConnection.getInputStream());
            String response = convertInputStreamToString(inputStream);
            String[] results = parseResult(response);
            return results;
        } else {
            Log.d(TAG, "Failed to fetch data!!");

            throw new DownloadException("Failed to fetch data!!");

        }
    }

    private String convertInputStreamToString(InputStream inputStream) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";

        while ((line = bufferedReader.readLine()) != null) {
            result += line;
        }

            /* Close Stream */
        if (null != inputStream) {
            inputStream.close();
        }

        return result;
    }

    private String[] parseResult(String result) {

        String temperature;
        String humidity;

        String[] weatherData = null;
        try {
            JSONObject response = new JSONObject(result);
            JSONObject current_observation = response.optJSONObject("current_observation");
            temperature = current_observation.getString("temperature_string");
            humidity = current_observation.getString("temperature_string");
            Log.d(TAG, "Temperature: " + temperature);
            Log.d(TAG, "Humidity: " + humidity);
            weatherData = new String[] {temperature, humidity};


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return weatherData;
    }

    public class DownloadException extends Exception {

        public DownloadException(String message) {
            super(message);
        }

        public DownloadException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}