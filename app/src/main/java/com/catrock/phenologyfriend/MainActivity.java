package com.catrock.phenologyfriend;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ArrayAdapter;

public class MainActivity extends Activity implements DownloadResultReceiver.Receiver {

    private DownloadResultReceiver mReceiver;

    final String url = "http://javatechig.com/api/get_category_posts/?dev=1&slug=android";
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
        if (!wuapi_key.isEmpty()) {
//            retrieveWeatherData(wuapi_key);

            /* Starting Download Service */
            mReceiver = new DownloadResultReceiver(new Handler());
            mReceiver.setReceiver(this);
            Intent intent = new Intent(Intent.ACTION_SYNC, null, this, DownloadService.class);

            /* Send optional extras to Download IntentService */
            intent.putExtra("url", url);
            intent.putExtra("receiver", mReceiver);
            intent.putExtra("requestId", 101);

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
        WeatherData obj= new WeatherData("78.5","46");

        Intent intent = new Intent (this, AddObservationPlantActivity.class);
        intent.putExtra("weatherDataTag", obj);

        startActivity(intent);
    }

    // Display Add Observation Animal activity.
    public void showAddObservationAnimal(View v) {
        Intent intent = new Intent (this, AddObservationAnimalActivity.class);
        startActivity(intent);
    }

    // Retrieve weather data.
    public void retrieveWeatherData(String api_key) {
//        Toast.makeText(this, api_key, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {
        switch (resultCode) {
            case DownloadService.STATUS_RUNNING:

                setProgressBarIndeterminateVisibility(true);
                break;
            case DownloadService.STATUS_FINISHED:
                /* Hide progress & extract result from bundle */
                setProgressBarIndeterminateVisibility(false);

                String[] results = resultData.getStringArray("result");
Toast.makeText(this, results[1], Toast.LENGTH_LONG).show();
                WeatherData obj= new WeatherData(results[0],results[1]);

                /* Update ListView with result */
//                arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_2, results);
//                listView.setAdapter(arrayAdapter);

                break;
            case DownloadService.STATUS_ERROR:
                /* Handle the error */
                String error = resultData.getString(Intent.EXTRA_TEXT);
                Toast.makeText(this, error, Toast.LENGTH_LONG).show();
                break;
        }
    }
}
