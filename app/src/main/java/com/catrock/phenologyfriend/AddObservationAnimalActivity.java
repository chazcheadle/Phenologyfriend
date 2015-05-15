package com.catrock.phenologyfriend;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;


public class AddObservationAnimalActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(getClass().getSimpleName(), "onCreate()");
        setContentView(R.layout.activity_add_observation_animal);
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
