package com.catrock.phenologyfriend;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ArrayAdapter;

public class MainActivity extends Activity implements WeatherResultReceiver.Receiver {

    private WeatherResultReceiver mReceiver;

    final String wu_url = "http://api.wunderground.com/api/APIKEY/geolookup/conditions/q/NY/Garrison.json";

    public WeatherData weatherData;

    private ListView listView = null;

    private ArrayAdapter arrayAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Update last observation dates.
        TextView plantLatest = (TextView) findViewById(R.id.mainLatestPlantValue);
        TextView animalLatest = (TextView) findViewById(R.id.mainLatestAnimalValue);
        plantLatest.setText("May 08, 2015 - 10:33AM");
        animalLatest.setText("May 04, 2015 - 3:43PM");

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String wuapi_key = prefs.getString("credentials_wuapi_key", "");
        if (wuapi_key.length() > 0) {

            /* Starting Weather Service */
            mReceiver = new WeatherResultReceiver(new Handler());
            mReceiver.setReceiver(this);
            Intent intent = new Intent(Intent.ACTION_SYNC, null, this, WeatherService.class);

            /* Send optional extras to Weather IntentService */
            intent.putExtra("url", wu_url);
            intent.putExtra("receiver", mReceiver);
            intent.putExtra("wuapi_key", wuapi_key);

            // Start the Intent Service
            startService(intent);
        }
        else {
            Toast.makeText(this, "Please configure your API Keys.\nGo to Settings>Credentials/API Keys", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.action_preferences:
                showSettings();
                return(true);
            case R.id.action_about:
                showAbout();
                return(true);
        }

        return super.onOptionsItemSelected(item);
    }

    // Display Settings activity.
    public void showSettings() {
        Intent intent = new Intent (this, Preferences.class);
        startActivity(intent);
    }

    // Display About activity.
    public void showAbout() {
        Intent intent = new Intent (this, AboutActivity.class);
        startActivity(intent);
    }

    // Display Add Observation Plant activity.
    public void showAddObservationPlant(View v) {
        Intent intent = new Intent (this, AddObservationPlantActivity.class);
        intent.putExtra("weatherDataTag", weatherData);

        startActivity(intent);
    }

    // Display Add Observation Animal activity.
    public void showAddObservationAnimal(View v) {
        Intent intent = new Intent (this, AddObservationAnimalActivity.class);
        startActivity(intent);
    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {
        switch (resultCode) {
            case WeatherService.STATUS_RUNNING:

                setProgressBarIndeterminateVisibility(true);
                break;
            case WeatherService.STATUS_FINISHED:
                /* Hide progress & extract result from bundle */
                setProgressBarIndeterminateVisibility(false);

                String[] results = resultData.getStringArray("result");
                WeatherData result= new WeatherData(results[0], results[1], results[2]);
                weatherData = result;

                Toast.makeText(this, "Weather data received.", Toast.LENGTH_LONG).show();
                Toast.makeText(this, "Weather: " + weatherData.getWeather() + "\n" + weatherData.getTemperature() + " | Hum: " + weatherData.getHumidity(), Toast.LENGTH_LONG).show();

                /* Update ListView with result */
//                arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_2, results);
//                listView.setAdapter(arrayAdapter);

                break;
            case WeatherService.STATUS_ERROR:
                /* Handle the error */
                String error = resultData.getString(Intent.EXTRA_TEXT);
                Toast.makeText(this, error, Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        Log.d(getClass().getSimpleName(), "onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.d(getClass().getSimpleName(), "onResume()");
    }

    @Override
    public void onPause() {
        Log.d(getClass().getSimpleName(), "onPause()");

        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d(getClass().getSimpleName(), "onStop()");

        super.onStop();
    }

    @Override
    public void onDestroy() {
        Log.d(getClass().getSimpleName(), "onDestroy()");

        super.onDestroy();
    }
}
