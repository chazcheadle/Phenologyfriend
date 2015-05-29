package com.catrock.phenologyfriend;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class AddObservationPlantActivity extends Activity {

    CustomAutoCompleteView plantAutocomplete;

    ArrayAdapter<SpecimenData> myPlantAutocompleteAdapter;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(getClass().getSimpleName(), "onCreate()");
        setContentView(R.layout.activity_add_observation_plant);

        WeatherData weatherData= getIntent().getParcelableExtra("/weatherDataTag");
        if (weatherData != null) {
            Toast.makeText(this, weatherData.getTemperature(), Toast.LENGTH_LONG).show();
        }
        try{

            // instantiate database handler
           // db = new DatabaseHelper;

            // put sample data to database
          //  insertSampleData();

            // autocompletetextview is in activity_main.xml
            plantAutocomplete = (CustomAutoCompleteView) findViewById(R.id.addPlantAutocomplete);

            plantAutocomplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View arg1, int pos, long id) {

                    RelativeLayout rl = (RelativeLayout) arg1;
                    TextView tv = (TextView) rl.getChildAt(0);
                    plantAutocomplete.setText(tv.getText().toString());

                }

            });

            // add the listener so it will tries to suggest while the user types
            plantAutocomplete.addTextChangedListener(new CustomAutoCompleteTextChangedListener(this));

            // ObjectItemData has no value at first
            SpecimenData[] specimenData = new SpecimenData[0];

            // set the custom ArrayAdapter
            myPlantAutocompleteAdapter = new Adapter(this, R.layout.specimen_autocomplete, specimenData);
            plantAutocomplete.setAdapter(myPlantAutocompleteAdapter);

        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
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

        db.addSpecimen(new SpecimenData("NY-10101", 1, "Callinectes sapidus", "Blue crab", "A crab that is blue"));

/*        List<SpecimenData> specimens = db.getAllSpecimens();
        SpecimenData specimen = specimens.get(0);
        Log.d(getClass().getSimpleName(), specimen.getCommonName());*/

        List<SpecimenData> specimens = db.autoCompleteList("Callinectes");
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
