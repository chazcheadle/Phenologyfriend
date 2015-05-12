package com.catrock.phenologyfriend;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {



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
        if (wuapi_key != "") {
            retrieveWeatherData();
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
        WeatherData obj= new WeatherData("78.5","46");

        Intent intent = new Intent (this, AddObservationPlantActivity.class);
        intent.putExtra("weatherDataTag", (Parcelable) obj);

        startActivity(intent);
    }

    // Display Add Observation Animal activity.
    public void showAddObservationAnimal(View v) {
        Intent intent = new Intent (this, AddObservationAnimalActivity.class);
        startActivity(intent);
    }

    // Retrieve weather data.
    public void retrieveWeatherData() {

    }
}
