package com.catrock.phenologyfriend;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.action_settings:
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
        startActivity(new Intent(this, SettingsActivity.class));
    }

    // Display About activity.
    public void showAbout() {
        startActivity(new Intent(this, AboutActivity.class));
    }

    // Display Add New Record activity.
    public void showAddNewRecord(View v) {
        startActivity(new Intent(this, AddNewRecordActivity.class));
    }

    // Display Add Observation Plant activity.
    public void showAddObservationPlant(View v) {
        startActivity(new Intent(this, AddObservationPlantActivity.class));
    }

    // Display Add Observation Animal activity.
    public void showAddObservationAnimal(View v) {
        startActivity(new Intent(this, AddObservationAnimalActivity.class));
    }
}
