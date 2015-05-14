package com.catrock.phenologyfriend;

import android.os.Bundle;
import android.widget.Toast;


public class AddObservationPlantActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_observation_plant);

        WeatherData weatherData= getIntent().getParcelableExtra("weatherDataTag");
//        Toast.makeText(this, weatherData.getTemp(), Toast.LENGTH_LONG).show();
    }

}
