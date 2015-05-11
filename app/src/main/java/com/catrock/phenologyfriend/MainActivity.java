package com.catrock.phenologyfriend;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
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

    // Display Add Observation Plant activity.
    public void showAddObservationPlant(View v) {
        startActivity(new Intent(this, AddObservationPlantActivity.class));
    }

    // Display Add Observation Animal activity.
    public void showAddObservationAnimal(View v) {
        startActivity(new Intent(this, AddObservationAnimalActivity.class));
    }
}
