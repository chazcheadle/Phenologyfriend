package com.catrock.phenologyfriend;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;


public class AddObservationPlantActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(getClass().getSimpleName(), "onCreate()");
        setContentView(R.layout.activity_add_observation_plant);

        WeatherData weatherData= getIntent().getParcelableExtra("/weatherDataTag");
        if (weatherData != null) {
            Toast.makeText(this, weatherData.getTemperature(), Toast.LENGTH_LONG).show();
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
        DatabaseHelper db=DatabaseHelper.getInstance(this);
        Log.d(getClass().getSimpleName(), db.getDatabaseName());
        List<SpecimenData> specimens = db.getAllSpecimens();
        Log.d(getClass().getSimpleName(), "Results: " + String.valueOf(specimens.size()));
        SpecimenData specimen = specimens.get(0);
        Log.d(getClass().getSimpleName(), specimen.getScientificName());
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
